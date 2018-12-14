
package commons.utils;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * 转换器 1:将JavaBean 转换成Map、JSONObject 2:将JSONObject 转换成Map
 * 
 * @author tjhq_whm
 */
@Component
public class ConvertUtil {
    /**
     * 将javaBean转换成Map (取得javabean的域名做map的key值做对应的value)
     * 
     * @param javaBean javaBean
     * @return Map对象
     */
    public static Map toMap(Object javaBean)  {
    	Map beanMap = new HashMap();
    	try{
    		beanMap = BeanUtils.describe(javaBean);
    		if (beanMap.containsKey("class")){
    			beanMap.remove("class");
    		}
    		return beanMap;
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		return beanMap;
    	}   
    }

    /**
     * 将bean转换成格式如["",""...]的数组
     * 
     * @param po
     * @param fields 字段（域）列表
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String getValueString(Object po, String[] fields) {
        Map mRet_ = toMap(po);
        ArrayList arrTmp_ = new ArrayList();
        for (int i = 0; i < fields.length; i++) {
            String sTmp_ = (String) mRet_.get(fields[i]);
            arrTmp_.add(sTmp_.equals("") ? "\"\"" : "\"" + sTmp_ + "\"");
        }
        return arrTmp_.toString();
    }

    /**
     * 将元素为bean的数组转换成格式如[["",""...],["",""...],["",""...]]的字符串数组
     * 
     * @param lstPO
     * @param fields 字段（域）列表
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String getValueString(List lstPO, String[] fields) {
        ArrayList arrRet_ = new ArrayList();
        for (int j = 0; j < lstPO.size(); j++) {
            Object po = lstPO.get(j);
            Map mRet_ = toMap(po);
            ArrayList arrTmp_ = new ArrayList();
            for (int i = 0; i < fields.length; i++) {
                String sTmp_ = (String) mRet_.get(fields[i]);
                arrTmp_.add(sTmp_.equals("") ? "\"\"" : "\"" + sTmp_ + "\"");
            }
            arrRet_.add(arrTmp_);
        }
        return arrRet_.toString();
    }

    /**
     * 将json对象转换成Map
     * 
     * @param jsonObject json对象
     * @return Map对象
     * @throws JSONException
     */
    @SuppressWarnings("unchecked")
    public static Map toMap(JSONObject jsonObject) throws JSONException {
        Map result = new HashMap();
        Iterator iterator = jsonObject.keys();
        String key = null;
        String value = null;
        while (iterator.hasNext()) {
            key = (String) iterator.next();

            value = jsonObject.getString(key);

            result.put(key, value);
        }
        return result;
    }

    /**
     * 将javaBean转换成JSONObject
     * 
     * @param bean javaBean
     * @return json对象
     */
    public static JSONObject toJSON(Object bean) {
        return new JSONObject(toMap(bean));
    }

    /**
     * 将map转换成Javabean
     * 
     * @param javabean javaBean
     * @param data map数据
     */
    public static Object toJavaBean(Object javabean, Map data) {
        Method[] methods = javabean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            try {
                if (method.getName().startsWith("set")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("set") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    method.invoke(javabean, new Object[] {data.get(field) });
                }
            } catch (Exception e) {
            }
        }
        return javabean;
    }

    /**
     * JSONObject到javaBean
     * 
     * @param bean javaBean
     * @return json对象
     * @throws ParseException json解析异常
     * @throws JSONException
     */
    public static void toJavaBean(Object javabean, String data) throws ParseException, JSONException {
        JSONObject jsonObject = new JSONObject(data);
        Map datas = toMap(jsonObject);
        toJavaBean(javabean, datas);
    }
}

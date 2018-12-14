package commons.setting.input.web;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.setting.input.po.RefBean;
import com.tjhq.commons.setting.input.po.RefColumn;

public class ConverTables {

	public static String IS_TABLE = "SELECT COUNT(1) FROM USER_TABLES WHERE TABLE_NAME='%s'";
    public static String IS_VIEW = "SELECT COUNT(1) FROM USER_VIEWS WHERE VIEW_NAME='%s'";
    
    /**
     * 浮动表查询条件 * 为模板数据  1 未发布 2 已发布 3 已更新 4 已删除 5 删除发布
     */
    public static String float_where = "AGENCYID = \'*\' AND SYNSTATUS NOT IN (\'4\',\'5\')";
    
    public static String float_publish = "AGENCYID = \'*\' AND SYNSTATUS NOT IN (\'2\',\'5\')";
    
	/**
	 * @Description 是否编辑列2.0
	 * @param label
	 * @param columnName
	 * @param edit
	 * @param width 宽度 等于0 不设置
	 */
	public static Column ColumnEditNew(String label, String columnName,
			boolean edit,int width) {
		Column column = new Column();
		column.setColumnID(columnName);
		column.setColumnName(label);
		column.setColumnDBName(columnName);
		column.setAlias(label);
		column.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		column.setShowType(ShowType.SHOW_TYPE_HTML);
		column.setReadOnly(!edit);
		column.setVisible(true);
		
		if(width>0){
			column.setDataLength(width);
		}	
		return column;
	}

	/**
	 * @Description 是否隐藏列2.0
	 * @param label
	 * @param columnName
	 * @param visible
	 */
	public static Column ColumnVisableNew(String label, String columnName,
			boolean visible) {
		Column column = new Column();
		column.setColumnID(columnName);
		column.setColumnName(label);
		column.setColumnDBName(columnName);
		column.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		column.setShowType(ShowType.SHOW_TYPE_HTML);
		column.setDataLength(32);
		column.setKey(true);
		column.setOrderID(0);
		column.setNullable(false);
		column.setVisible(false);
		
		return column;
	}
	 
	/**
	 * @Description 是否编辑列
	 * @param label
	 * @param columnName
	 * @param edit
	 * @param width 宽度 等于0 不设置
	 */
	public static Column ColumnEdit(String label, String columnName,
			boolean edit,int width) {
		Column column = new Column();
		column.setColumnName(columnName == null?"":columnName);
		column.setReadOnly(edit);
		if(width>0){
			column.setWidth(width);
		}	
		return column;
	}

	/**
	 * @Description 是否隐藏列
	 * @param label
	 * @param columnName
	 * @param visible
	 */
	public static Column ColumnVisable(String label, String columnName,
			boolean visible) {
		Column column = new Column();
		column.setColumnName(columnName == null?"":columnName);
		column.setVisible(visible);
		return column;
	}
	/**
	 * @Description 创建引用列Bean
	 * @param label
	 * @param columnName
	 * @param data Map数据 || JSON 数据
	 * @param edit 是否可编辑
	 * @param idField 
	 * @param refShowFormat
	 * @param width 宽度 等于0 不设置
	 * @return
	 */
	public static RefColumn RefColumnBean(String label, String columnName,
			Map<String,String[]> data, boolean edit, String idField, String refShowFormat,int width) {

		RefColumn refColumn = new RefColumn();
		refColumn.setColumnName(columnName);
		refColumn.setReadOnly(!edit);
		if(width>0){
			refColumn.setWidth(width);	
		}
		// 使用po对象创建下拉列表数据
		RefBean refBean = new RefBean();
		List dataList = toJavaBean(refBean, data);

		refColumn.setIdField(idField);
		refColumn.setRefShowFormat("#" + refShowFormat);
		refColumn.setDataList(dataList);
		
		return refColumn;
	}
	//JSON数据
	public static RefColumn RefColumnBean(String label, String columnName,
			String data, boolean edit, String idField, String refShowFormat,int width) {

		RefColumn refColumn = new RefColumn();
		refColumn.setColumnName(columnName);
		refColumn.setReadOnly(!edit);
		if(width>0){
			refColumn.setWidth(width);	
		}
		// 使用po对象创建下拉列表数据
		List dataList = jsonToMap(data);
		refColumn.setIdField(idField);
		refColumn.setRefShowFormat("#" + refShowFormat);
		refColumn.setDataList(dataList);
		
		return refColumn;
	}

	/**
	 * @Description 创建引用列List
	 * @param label
	 * @param columnName
	 * @param data List集合
	 * @param edit 是否可编辑
	 * @param idField
	 * @param superIdField
	 * @param refShowFormat
	 * @param width 宽度 等于0 不设置
	 * @return
	 */
	public static RefColumn RefColumnList(String label, String columnName,List dataList, 
			boolean edit, String idField, String superIdField,String refShowFormat,int width) {
		
		RefColumn refColumn = new RefColumn();
		refColumn.setColumnName(columnName);
		refColumn.setReadOnly(!edit);
		if(width>0){
			refColumn.setWidth(width);
		}
		refColumn.setIdField(idField);
		refColumn.setSuperIdField(superIdField);
		refColumn.setRefShowFormat("#" + refShowFormat);
		refColumn.setDataList(dataList);
		return refColumn;
	}

	/**
	 * @Description 将map转换成JAVABEAN
	 * @param javabean
	 * @param data
	 * @returns
	 */
	public static List toJavaBean(Object javabean, Map<String, String[]> data) {
		List objList = new ArrayList();

		Class classType = javabean.getClass();
		Field[] fields = classType.getDeclaredFields();// 得到对象中的字段
		for (int j = 0; j < data.get("f1").length; j++) {
			try {
				// 每次循环时，重新实例化一个与传过来的对象类型一样的对象
				javabean = classType.getConstructor(new Class[] {}).newInstance(new Object[] {});
				for (int i = 0; i < data.size(); i++) {
					Field field = fields[i];
					String fieldName = field.getName();
					String[] value = null;
					// 根据字段类型决定结果集中使用哪种get方法从数据中取到数据
					if (field.getType().equals(String.class)) {
						value = data.get(fieldName);
						// 获得属性的首字母并转换为大写，与setXXX对应
						String firstLetter = fieldName.substring(0, 1).toUpperCase();
						String setMethodName = "set" + firstLetter+ fieldName.substring(1);
						Method setMethod = classType.getMethod(setMethodName,new Class[] { field.getType() });
						setMethod.invoke(javabean, new Object[] { value[j] });// 调用对象的setXXX方法
					}
				}
				objList.add(javabean);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return objList;
	}
	
	/**
     * @Description 将JSON格式数据转换成导入数据集合
     * @param json
     * @return List
     * @throws IOException 读写异常
     * @throws Exception 异常
     */
    public static List jsonToMap(String jsonList) {
        List l = new ArrayList();
        try{
        	JSONArray jsonArray = new JSONArray(jsonList);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Map map = new HashMap();
                Iterator keyIter = jsonObject.keys();
                while (keyIter.hasNext()) {
                    String key = (String) keyIter.next();
                    Object value = (Object) jsonObject.get(key);
                    if (null != value && JSONObject.NULL != value) {
                        map.put(key, value.toString());
                    } else {
                        map.put(key, null);
                    }
                }
                l.add(map);
            }	
        }catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}     
        return l;
    }


	/**
	 * 转换时对map中的key里的字符串会做忽略处理的正则串
	 */
	private static final String OMIT_REG = "_";

	/**
	 * 将map集合转换成Bean集合，Bean的属性名与map的key值对应时不区分大小写，并对map中key做忽略OMIT_REG正则处理
	 * 
	 * @param <E>
	 * @param cla
	 * @param mapList
	 * @return
	 */
	public static <E> List<E> toBeanList(Class<E> cla, List<Map<String, Object>> mapList) {
		List<E> list = new ArrayList<E>(mapList.size());
		for (Map<String, Object> map : mapList) {
			E obj = toBean(cla, map);
			list.add(obj);
		}
		return list;
	}

	/**
	 * 将map转换成Bean，Bean的属性名与map的key值对应时不区分大小写，并对map中key做忽略OMIT_REG正则处理
	 * 
	 * @param <E>
	 * @param cla
	 * @param map
	 * @return
	 */
	public static <E> E toBean(Class<E> cla, Map<String, Object> map) {
		// 创建对象
		E obj = null;
		try {
			obj = cla.newInstance();
			if (obj == null) {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// 处理map的key
		Map<String, Object> newmap = new HashMap<String, Object>();
		for (Map.Entry<String, Object> en : map.entrySet()) {
			newmap.put("set"+ en.getKey().trim().replaceAll(OMIT_REG, "").toLowerCase(), en.getValue());
		}
		// 进行值装入
		Method[] ms = cla.getMethods();
		for (Method method : ms) {
			String mname = method.getName().toLowerCase();
			if (mname.startsWith("set")) {
				Class[] clas = method.getParameterTypes();
				Object v = newmap.get(mname);
				if (v != null && clas.length == 1) {
					try {
						method.invoke(obj, v);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
		return obj;
	}
	/**
	 * map中key 转换为 UpperCase
	 * @param map
	 * @return
	 * @throws Exception
	 */
    public static Map<String,Object> mapToUpperCase(Map<String,Object> map){
		// 处理map的key
		Map<String, Object> newMap= new HashMap<String, Object>();
		for (Map.Entry<String, Object> en : map.entrySet()) {
			newMap.put(en.getKey().trim().toUpperCase(), en.getValue());
		}
		return newMap;
    }
    /**
     * 去除字符串中 最后一个字符
     * @param character 字符串
     * @param remove 需要去除的字符
     * @return
     */
	public static String removeLastChar(String character,String remove){
		//substring
		if(character.indexOf(remove) != -1)
			character = character.substring(0, character.lastIndexOf(remove));
		return character;
	}
	/**
	 * 判断字符串是否为空
	 * @param character
	 * @return
	 */
	public static boolean isNotNull(Object character){

		return null != character && !("").equals(character) ? true : false;
	}
	/**
	 * 判断List是否为空
	 * @param list
	 * @return
	 */
	public static boolean isNotNullList(List list){

		return null != list && list.size() > 0 ? true : false;
	}
	/**
	 * 合并 两个 Map
	 * @param m1
	 * @param m2
	 * @return
	 */
	public static Map<String, Object> mergeMap(Map<String, Object> map1,Map<String, Object> map2) {
		for (Map.Entry<String, Object> en : map2.entrySet()) {
			map1.put(en.getKey(), en.getValue());		
		}
		return map1;
	}
	/**
	 * 比较 两个 Map
	 * @param map1
	 * @param map2
	 * @return
	 */
	public static boolean compareMap(Map<String, Object> map1, Map<String, Object> map2) {
	    for (Map.Entry<String, Object> en : map1.entrySet()) {
	    	String key = en.getKey();
	        if (map2.containsKey(key)) {
	        	//如果不为空 判断 值是否相同
	        	if(ConverTables.isNotNull(map1.get(key)) && ConverTables.isNotNull(map2.get(key))){
	        		boolean contain = map1.get(key).equals(map2.get(key));
		            if(!contain) return false;
	        	}else{
	        		return false;
	        	}
	        }
	    }
	    return true;
	}
	/**
	 * 处理 可更新列 用于 对比数据
	 * @param dataMap
	 * @param pattern
	 * @return
	 */
	public static Map<String, Object> processCanUpdate(Map<String, Object> dataMap , Pattern pattern){
		Map<String, Object> temp = new HashMap<String, Object>();
		
		for (Map.Entry<String, Object> en : dataMap.entrySet()) {
			if (pattern.matcher(en.getKey()).find()) temp.put(en.getKey(),en.getValue());
		}
		return temp;
	}
	 
}

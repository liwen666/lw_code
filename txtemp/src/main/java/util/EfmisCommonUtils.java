package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang3.StringUtils;

public class EfmisCommonUtils {

	/**
	 * 从mapParam中替换key为replaceParam的key的值为replaceParam的值
	 * @Title: handleMapParam
	 * @Description: 从mapParam中替换key为replaceParam的key的值为replaceParam的值
	 * @param mapParam 源业务参数Map
	 * @param replaceParam 要替换的Map，如将
	 * @return Map<String,String>
	 */
	public static Map<String, String> handleMapParam(Map<String, String> mapParam, Map<String, String> replaceParam) {
		if (null == replaceParam || replaceParam.size() <= 0) {
			return mapParam;
		}
		for (String key : replaceParam.keySet()) {
			if (mapParam.containsKey(key)) {
				mapParam.put(key, replaceParam.get(key));
			}
		}
		return mapParam;
	}

	/**
	 * 验证resultMap集合中是否含有key为paramKey的值，有就输出
	 * @Title: getParamValByMap
	 * @Description: 验证resultMap集合中是否含有key为paramKey的值，有就输出
	 * @param resultMap 目标集合
	 * @param paramKey key
	 * @return String
	 */
	public static String getParamValByMap(Map<String, Object> resultMap, String paramKey) {
		if (null == resultMap || resultMap.size() <= 0) {
			return "";
		}
		if (resultMap.containsKey(paramKey)) {
			return (String) resultMap.get(paramKey);
		}
		return "";
	}
	
	/**
	 * 验证resultMap集合中是否含有key为paramKey的值，有就输出
	 * @Title: getParamValByStrMap
	 * @Description: 验证resultMap集合中是否含有key为paramKey的值，有就输出
	 * @param resultMap 目标集合
	 * @param paramKey key
	 * @return String
	 */
	public static String getParamValByStrMap(Map<String, String> resultMap, String paramKey) {
		if (null == resultMap || resultMap.size() <= 0) {
			return "";
		}
		if (resultMap.containsKey(paramKey)) {
			return resultMap.get(paramKey);
		}
		return "";
	}

	/**
	 * 通过key查询map集合中map的键为key的值
	 * @Title: getParamValByLstMap
	 * @Description: 通过key查询map集合中map的键为key的值,如果集合为空或者集合大小大于1，返回空
	 * @param lstTragetDocParams
	 * @param key
	 * @return String
	 */
	public static String getParamValByLstMap(List<Map<String, Object>> lstTragetDocParams, String key) {
		if (null != lstTragetDocParams && lstTragetDocParams.size() == 1) {
			return getParamValByMap(lstTragetDocParams.get(0), key);
		}
		return "";
	}
	
	/**
	 * 将字符串转为listMap格式数据
	 * @Title: strToLstMap
	 * @Description: 将字符串转为listMap格式数据
	 * @param str
	 * @return List<Map<String,Object>>
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> strToLstMap(String str) throws JSONException {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		JSONArray array = JSONArray.fromObject(str);
		return JSONArray.toList(array, new HashMap<String, Object>(), new JsonConfig());
	}
	
	/**
	 * 将字符串转为Map格式数据
	 * @Title: strToMap
	 * @Description: 将字符串转为Map格式数据
	 * @param str
	 * @return Map<String,Object>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> strToMap(String str) throws JSONException {
		if (StringUtils.isEmpty(str)) {
			return new HashMap<String, Object>();
		}
		JSONObject obj = JSONObject.fromObject(str);
		return (Map<String, Object>)obj;
	}
	
}

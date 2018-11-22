package common.servlet;

import java.util.HashMap;
import java.util.Map;

import com.exception.po.UserInfo;


public class VariableContext {
	
	private static final ThreadLocal<Map<String, String>> variableContext = new ThreadLocal<Map<String,String>>();
	private static final ThreadLocal<UserInfo> userInfoContext = new ThreadLocal<UserInfo>();
	
	public static void setVariable(Map<String, String> var) {
		variableContext.set(var);
	}
	
	public static void putVariable(String key, String value) {
		Map<String, String> var = variableContext.get();
		if (var == null) {
			var = new HashMap<String, String>();
		}
		var.put(key, value);
		variableContext.set(var);
	}
	
	public static Map<String, String> getVariable() {
		return variableContext.get();
	}
	
	public static String getVariable(String key) {
		Map<String, String> var = variableContext.get();
		if (var != null && var.containsKey(key))
			return var.get(key);
		else 
			return null;
	}
	
	public static void putUserInfo(UserInfo userInfo) {
	    userInfoContext.set(userInfo);
	}
	
	public static UserInfo getUserInfo() {
	    return userInfoContext.get();
	}
}

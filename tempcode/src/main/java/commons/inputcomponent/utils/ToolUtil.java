package commons.inputcomponent.utils;


/**
 * @author Administrator
 * @version 1.0
 */
public class ToolUtil {


	public static String getUUID() {
		return java.util.UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

	public static boolean toBoolean(String content) {
		if (content == null)
			return false;
		return content.equalsIgnoreCase("1") ? true : false;
	}

	public static Integer getInteger(Integer value) {
		return value == null ? 0 : value;
	}

}
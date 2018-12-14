
package commons.inputcomponent.extend.secu;

import java.util.HashMap;
import java.util.Map;

public class ReplaceParamter {

    public static String execute(String secuStr, Map<String, Object> extProperties) {
        if (secuStr == null || extProperties == null || extProperties.size() == 0) {
            return secuStr;
        }

        String paramName = "";
        int findFromIndex = 0;
        int findEndIndex = 0;
        while ((findFromIndex = secuStr.indexOf("$", findFromIndex + 1)) != -1
                && (findEndIndex = secuStr.indexOf("$", findFromIndex + 1)) != -1) {
            paramName = secuStr.substring(findFromIndex + 1, findEndIndex);
            if (extProperties.containsKey(paramName)) {
                secuStr = secuStr.replaceAll("\\$" + paramName + "\\$", String
                        .valueOf(extProperties.get(paramName)));
            }

            findFromIndex = findEndIndex;
        }
        return secuStr;
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ABC", "111111");
        //execute("AGENCYID = '$ABC$' AND DOCID = '$DEF$' ", map);
        execute("AGENCYID = '111$' AND DOCID = '2222' ", map);
    }
}

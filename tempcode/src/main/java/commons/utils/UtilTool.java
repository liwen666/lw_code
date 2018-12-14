
package commons.utils;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 该类是解决刷新界面时需要弹出重新提交界面
 * 
 * @author
 */
public class UtilTool {
    public static Map getParameterMap(HttpServletRequest request) {
        Map paramMap = new LinkedHashMap();
        String pathInfoStr = request.getPathInfo();

        if (pathInfoStr != null && pathInfoStr.length() > 0) {
            if (!pathInfoStr.endsWith("/"))
                pathInfoStr += "/";

            int current = pathInfoStr.indexOf('/');
            int last = current;
            while ((current = pathInfoStr.indexOf('/', last + 1)) != -1) {
                String element = pathInfoStr.substring(last + 1, current);
                last = current;
                if (element.charAt(0) == '~' && element.indexOf('=') > -1) {
                    String name = element.substring(1, element.indexOf('='));
                    String value = element.substring(element.indexOf('=') + 1);
                    paramMap.put(name, value);
                }
            }
        }

        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String name = (String) e.nextElement();
            paramMap.put(name, request.getParameter(name));
        }

        if (paramMap.size() == 0) {
            Map multiPartMap = (Map) request.getAttribute("multiPartMap");
            if (multiPartMap != null && multiPartMap.size() > 0) {
                paramMap.putAll(multiPartMap);
            }
        }
        return paramMap;
    }

    public static String requestParameter(HttpServletRequest request) {
        String fromstr = "<form name='request' method='POST'>";
        Enumeration requestKeys = request.getParameterNames();
        Map requestValues = UtilTool.getParameterMap(request);
        String rkey = null;
        for (; requestKeys.hasMoreElements();) {
            rkey = (String) requestKeys.nextElement();
            fromstr += "<input name='" + rkey + "' value='" + requestValues.get(rkey) + "' type='hidden'>";
        }
        fromstr += "</form><script>function reload(){request.submit()}</script>";
        return fromstr;
    }

}

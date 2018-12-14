package commons.excel2.util;

import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

	/*private static HashMap allprogressMap = new HashMap();

	public static void clearProgressMap() {
		allprogressMap.clear();
	}

	public static void setProgressBar(HttpServletRequest request) {
		String progressGUID = (String) request.getParameter("progressguid");
		if (progressGUID != null) {
			HashMap progressMap = (HashMap) allprogressMap.get("progressGUID_" + progressGUID);
			if (progressMap == null) {
				progressMap = new HashMap();
				allprogressMap.put("progressGUID_" + progressGUID, progressMap);
			}
			progressMap.put("curent_progressText", request.getAttribute("curent_progressText"));
			int curent_progressValue = ((Integer) request.getAttribute("curent_progressValue")).intValue();
			int total_progressValue = ((Integer) request.getAttribute("total_progressValue"));
			int cur_percentage = curent_progressValue * 100/ total_progressValue;
			if (cur_percentage > 99){
				cur_percentage = 99;
			}
			int old_progressValue = progressMap.get("curent_progressValue") == null ? 0 : ((Integer) progressMap.get("curent_progressValue")).intValue();
			if (cur_percentage >= old_progressValue) {
				progressMap.put("curent_progressValue", cur_percentage);
			}
		}
	}

	public static void setProgressBarEND(HttpServletRequest request) {
		String progressGUID = (String) request.getParameter("progressguid");
		if (progressGUID != null) {
			HashMap progressMap = (HashMap) allprogressMap.get("progressGUID_"
					+ progressGUID);
			if (progressMap == null) {
				progressMap = new HashMap();
				allprogressMap.put("progressGUID_" + progressGUID, progressMap);
			}
			progressMap.put("curent_progressText", request
					.getAttribute("curent_progressText"));
			int curent_progressValue = ((Integer) request
					.getAttribute("curent_progressValue")).intValue();
			int total_progressValue = ((Integer) request
					.getAttribute("total_progressValue"));
			int cur_percentage = curent_progressValue * 100
					/ total_progressValue;
			if (cur_percentage > 100)
				cur_percentage = 100;
			int old_progressValue = progressMap.get("curent_progressValue") == null ? 0
					: ((Integer) progressMap.get("curent_progressValue"))
							.intValue();
			if (cur_percentage >= old_progressValue) {
				progressMap.put("curent_progressValue", cur_percentage);
			}
		}
	}

	public static HashMap getProgressBar(HttpServletRequest request) {
		HashMap progressMap = null;
		String progressGUID = (String) request.getParameter("progressguid");
		if (progressGUID != null) {
			progressMap = (HashMap) allprogressMap.get("progressGUID_"
					+ progressGUID);
			if (progressMap != null) {
				Integer iCurrent_progressValue = (Integer) progressMap
						.get("curent_progressValue");
				if (iCurrent_progressValue != null) {
					int curent_progressValue = iCurrent_progressValue
							.intValue();
					if (curent_progressValue == 100) {
						allprogressMap.remove("progressGUID_" + progressGUID);
					}
				}
			}
		}
		return progressMap;
	}

	public static void progressAdd(HttpServletRequest request, int iadd) {
		if (iadd == 0)
			return;
		Object object = request.getAttribute("curent_progressValue");
		if (object == null) {
			object = 0;
		}
		int cur_progressValue = ((Integer) object).intValue() + iadd;
		request.setAttribute("curent_progressValue", cur_progressValue);
		setProgressBar(request);
	}*/

    public static List<String> getControlFields_A1() {
    	List<String> controlFields = new ArrayList<String>();
        controlFields.add("FDCODE");
        controlFields.add("ISDJ");
        controlFields.add("ISHZ");
        controlFields.add("ISLEAF");
        controlFields.add("ISQZX");
        controlFields.add("ISUPDATE");
        controlFields.add("LEVELNO");
        controlFields.add("SUPERID");
        
		return controlFields;
	}

}
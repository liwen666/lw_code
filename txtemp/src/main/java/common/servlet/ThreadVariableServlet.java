package common.servlet;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

import com.exception.po.UserInfo;


public class ThreadVariableServlet extends DispatcherServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 任务ID
	    String taskID = request.getParameter("taskID");
	    VariableContext.putVariable("taskID", taskID);
	    
	    // appID
	    String appID = request.getParameter("currentAppID");
	    if (null == appID || appID.equals("")) {
	        appID = request.getParameter("appID");
	    }
	    VariableContext.putVariable("appID", appID);
		
		// 预算年度
		String finYear = request.getParameter("finYear"); // 业务系统预算年度
		if (null == finYear || "".equals(finYear.trim())) { 
		    finYear = request.getParameter("settingyear"); // 适配OA传递参数
		}
		if (null == finYear || "".equals(finYear.trim())) {
		    finYear = (String)request.getAttribute("finYear");
		}
		VariableContext.putVariable("finYear", finYear);
		
		// 公文ID
		String docID = request.getParameter("current_DocID");
		if (docID == null || docID.equals("")) {
			docID = request.getParameter("docID"); 
		}
		VariableContext.putVariable("docID", docID);
		
		// 地区regionCode
        String regionCode = request.getParameter("regionCode");
		if (null == regionCode || "".equals(regionCode.trim())) {
			regionCode = (String)request.getAttribute("regionCode");
		}
        if (regionCode != null && !regionCode.equals("")) {
            VariableContext.putVariable("regionCode", regionCode);
        }
		
		setRemoteUserInfo();
		
		super.service(request, response);
	}
	
	private void setRemoteUserInfo() {
	    UserDTO remoteUserInfo = SecureUtil.getCurrentUser();
	    if (remoteUserInfo == null) {
	        VariableContext.putUserInfo(null);
	        return;
	    }
        UserInfo userInfo = new UserInfo(remoteUserInfo.getCode(), remoteUserInfo.getName(), remoteUserInfo
                .getUsertype(), remoteUserInfo.getIssys(), remoteUserInfo.getAgency(), remoteUserInfo
                .getDivision(), remoteUserInfo.getGuid(), remoteUserInfo.getUpagencyid(), remoteUserInfo
                .getUpagencyCode(), remoteUserInfo.getIsadmin(), remoteUserInfo.getOrganid(), remoteUserInfo
                .getAdmdiv(), remoteUserInfo.getProvince(), SecureUtil.getUserSelectProvince(), SecureUtil.getUserSelectYear());
        VariableContext.putUserInfo(userInfo);
    }
	
	

}


package util;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;
import gov.mof.fasp2.sec.syncuserinfo.manager.IUserSyncManager;

import com.exception.po.UserInfo;
import com.longtu.framework.util.ServiceFactory;
import common.servlet.VariableContext;

public class UserUtil {

    public static UserInfo getUserInfo() {
        return getRemoteUserInfo();
    }
    
    public static UserInfo getUserInfo(String tokenid) {
        return getRemoteUserInfo(tokenid);
    }

    private static UserInfo getRemoteUserInfo() {
        if (VariableContext.getUserInfo() == null) {
            UserDTO remoteUserInfo = SecureUtil.getCurrentUser();
            if (remoteUserInfo == null) {
            	return null;
            }
            UserInfo userInfo = new UserInfo(remoteUserInfo.getCode(), remoteUserInfo.getName(), remoteUserInfo
                    .getUsertype(), remoteUserInfo.getIssys(), remoteUserInfo.getAgency(), remoteUserInfo
                    .getDivision(), remoteUserInfo.getGuid(), remoteUserInfo.getUpagencyid(), remoteUserInfo
                    .getUpagencyCode(), remoteUserInfo.getIsadmin(), remoteUserInfo.getOrganid(), remoteUserInfo
                    .getAdmdiv(), remoteUserInfo.getProvince(), SecureUtil.getUserSelectProvince(), SecureUtil.getUserSelectYear());
            VariableContext.putUserInfo(userInfo);
            return userInfo;
        }

        return VariableContext.getUserInfo();
    }
    
    private static UserInfo getRemoteUserInfo(String tokenid) {
		try{
			IUserSyncManager ius = (IUserSyncManager) ServiceFactory
    				.getBean("fasp.ca.UserSyncManager");
			gov.mof.fasp2.sec.syncuserinfo.UserInfo user = ius.getUser(tokenid);
			UserDTO remoteUserInfo = user.getUser();
			if (remoteUserInfo == null) {
				VariableContext.putUserInfo(null);
            	return null;
            }
            UserInfo userInfo = new UserInfo(remoteUserInfo.getCode(), remoteUserInfo.getName(), remoteUserInfo
                    .getUsertype(), remoteUserInfo.getIssys(), remoteUserInfo.getAgency(), remoteUserInfo
                    .getDivision(), remoteUserInfo.getGuid(), remoteUserInfo.getUpagencyid(), remoteUserInfo
                    .getUpagencyCode(), remoteUserInfo.getIsadmin(), remoteUserInfo.getOrganid(), remoteUserInfo
                    .getAdmdiv(), remoteUserInfo.getProvince(), SecureUtil.getUserSelectProvince(), SecureUtil.getUserSelectYear());
            VariableContext.putUserInfo(userInfo);
            return userInfo;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
    }
}


package commons.utils;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import com.tjhq.commons.servlet.VariableContext;

public class UserUtil {

    public static UserInfo getUserInfo() {
        return getRemoteUserInfo();
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
}

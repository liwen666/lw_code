/**
 * @Title: CacheKeyProvider.java
 * @Copyright (C) 2016 太极华青
 * @Description:
 * @Revision 1.0 2016-6-1  CAOK
 */

package commons.cache.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dao.BusiDataSource;
import com.tjhq.commons.inputcomponent.utils.dao.IUtilDAO;
import com.tjhq.commons.servlet.VariableContext;
import com.tjhq.commons.utils.UserUtil;

/**
 * @ClassName: CacheKeyProvider
 * @Description: 缓存KEY生成
 * @author: CAOK 2016-6-1 下午01:34:36
 */
@Service
@Transactional(readOnly = true)
public class CacheKeyProvider implements ICacheKeyProvider {

    Logger logger = Logger.getLogger(CacheKeyProvider.class);

    String finYearSql = "SELECT FINYEAR FROM DICT_T_BUDGETYEAR WHERE PROVINCE = ''{0}''";
    
    String appID = null;

    @Resource
    private IUtilDAO utilDAO;

    /**
     * @return utilDAO
     */
    public IUtilDAO getUtilDAO() {
        return utilDAO;
    }

    /**
     * @param utilDAO 要设置的 utilDAO
     */
    public void setUtilDAO(IUtilDAO utilDAO) {
        this.utilDAO = utilDAO;
    }

    /**
     * .
     * <p>
     * Title: getNewKeys
     * </p>
     * <p>
     * Description:
     * </p>
     * @param oldKeys
     * @return
     * @see com.tjhq.commons.cache.utils.ICacheKeyProvider#getNewKeys(String[])
     */
    @Override
    public String[] getNewKeys(String[] oldKeys) {
        String[] newKeys = new String[oldKeys.length + 2];
        newKeys[0] = getProvince();
        newKeys[1] = getFinYear(newKeys[0]);

        for (int i = 0; i < oldKeys.length; i++) {
            newKeys[i + 2] = oldKeys[i];
        }
        return newKeys;
    }

    private String getProvince() {
        String appID = getSystemAppID();
        String key = "";

        if ("BGT".equals(appID)) {
            key = getBGTProvince();
        } else if ("BAS".equals(appID)) {
            key = getBASProvince();
        } else if ("SPF".equals(appID)) {
            key = getSPFProvince();
        } else if ("KPI".equals(appID)) {
            key = getKPIProvince();
        } else if ("INDI".equals(appID)) {
            key = getINDIProvince();
        } else {
            key = getDefaultProvince();
        }

        return key;

    }

    private String getBGTProvince() {
        return getUserProvince();
    }

    private String getBASProvince() {
        return getDefaultProvince();
    }

    private String getSPFProvince() {
        String province = "";
        if (VariableContext.getVariable("regionCode") != null) {
        	province = VariableContext.getVariable("regionCode");
            logger.debug("Change regionCode:" + VariableContext.getVariable("regionCode"));
        }
        if (province == null || "".equals(province)) {
            province = getUserProvince();
        }

        if (province == null || "".equals(province)) {
            province = getDefaultProvince();
        }

        return province;
    }

    private String getKPIProvince() {
        String province = "";
//        String docID = getDocID();
        if (VariableContext.getVariable("regionCode") != null) {
            province = VariableContext.getVariable("regionCode");
            logger.debug(" KPI Cache regionCode:" + province);
        }
        
        if (province == null || "".equals(province)) {
            province = getUserProvince();
        }

        if (province == null || "".equals(province)) {
            province = getDefaultProvince();
        }

        return province;
    }

    private String getINDIProvince() {
        return getUserProvince();
    }

    private String getUserProvince() {
        String userProvince = "";
        // 如果是财政用户，取提升后的地区
        if (UserUtil.getUserInfo().getUserType() == 1) {
            userProvince = UserUtil.getUserInfo().getUpAgencyCode();
        } else {
            userProvince = UserUtil.getUserInfo().getUserSelectProvince();
        }

        return userProvince;
    }

    private String getDefaultProvince() {
        String defaultProvince = "";
        try {
            defaultProvince = getUtilDAO().queryStringValue("SELECT PKG_CONSTANTS.GETDEFAULTPROVINCE FROM DUAL");
        } catch (Exception e) {
            logger.debug("PKG_CONSTANTS.GETDEFAULTPROVINCE not Exists");
            e.printStackTrace();
        }
        return defaultProvince;
    }

    private String getDocID() {
        String docID = "";
        if (VariableContext.getVariable("docID") != null) {
            docID = VariableContext.getVariable("docID");
            logger.debug("Cache docID:" + VariableContext.getVariable("docID"));
        }
        return docID;
    }

    private String getFinYear(String province) {

        String key = VariableContext.getVariable("finYear");

        if (null != key && !"".equals(key.trim())) {
            return key;
        }

        try {
            key = getUtilDAO().queryStringValue(MessageFormat.format(finYearSql, province));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Not find default finYear , sql : {0}", MessageFormat.format(
                    finYearSql, province)));
            e.printStackTrace();
        }

        if (key == null || "".equals(key.trim())) {
            logger.error(MessageFormat.format("Not find default finYear , sql : {0}", MessageFormat.format(
                    finYearSql, province)));
        }

        return key;

    }

    private String getSystemAppID() {

        if (null != VariableContext.getVariable("appID") && !"".equals(VariableContext.getVariable("appID"))) {
            return VariableContext.getVariable("appID");
        }
        if (appID != null) {
            return appID;
        }
        Properties prop = new Properties();
        try {
            prop.load(BusiDataSource.class.getResourceAsStream("/application.properties"));
            if (prop.containsKey("system.appid")) {
                appID = prop.getProperty("system.appid");
                return appID;
            }
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

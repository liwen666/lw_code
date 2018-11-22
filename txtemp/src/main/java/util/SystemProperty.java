/**
 * @Title: SystemProperty.java
 * @Copyright (C) 2015 太极华青
 * @Description:
 * @Revision 1.0 2015-12-23  CAOK
 */
 

package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName: SystemProperty
 * @Description: Description of this class
 * @author: CAOK 2015-12-23 下午02:26:58
 */

public class SystemProperty {
    
    static String defaultProvince = null;
    static String appID = null;
    
    public static String getSystemProvince() {
        if (defaultProvince != null) {
            return defaultProvince;
        }
        Properties prop = getProperties();
        if (prop.containsKey("system.province")) {
            defaultProvince = prop.getProperty("system.province");
            return defaultProvince;
        }
        return null;
    }
    
    public static String getSystemAppID() {
        if (appID != null) {
            return appID;
        }
        Properties prop = getProperties();
        if (prop.containsKey("system.appid")) {
            appID = prop.getProperty("system.appid");
            return appID;
        }
        return null;
    }
    
    private static Properties getProperties() {
        Properties prop = new Properties();
        try {
            prop.load(SystemProperty.class.getResourceAsStream("/application.properties"));
            return prop;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    
}

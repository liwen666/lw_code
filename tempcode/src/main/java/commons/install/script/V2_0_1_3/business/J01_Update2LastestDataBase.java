package commons.install.script.V2_0_1_3.business;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import com.longtu.framework.install.IJavaScript;
import com.tjhq.commons.dao.BusiDataSource;
import com.tjhq.commons.execscript.external.service.IExtScriptService;
import com.tjhq.commons.install.service.IInitSqlXmlService;
import com.tjhq.commons.install.service.impl.InstallContext;
import com.tjhq.commons.install.service.impl.InstallContextHolder;
import com.tjhq.commons.utils.ApplicationContextUtil;

public class J01_Update2LastestDataBase implements IJavaScript {
	String appID = null;

	@Override
	public void execute(DataSource dataSource) throws Exception {
		try {
			InstallContext installContext = InstallContextHolder
					.getInstallContext();
			if (installContext == null) {
				installContext = new InstallContext();
				InstallContextHolder.setInstallContext(installContext);
			}
			IInitSqlXmlService initSqlXmlService = (IInitSqlXmlService) ApplicationContextUtil
					.getBean("initSqlXmlService");
			String defaultYear = initSqlXmlService.getInstallDefaultYear();
			String defaultProvince = initSqlXmlService.getInstallDefaultProvince();
			installContext.setProvinceCode(defaultProvince);
			installContext.setYear(defaultYear);
			InstallContextHolder.setInstallContext(installContext);
			if (appID == null || appID.equals("")) {
				Properties prop = new Properties();
				try {
					prop.load(BusiDataSource.class
							.getResourceAsStream("/application.properties"));
					if (prop.containsKey("system.appid")) {
						appID = prop.getProperty("system.appid");
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			IExtScriptService extScriptService = (IExtScriptService) ApplicationContextUtil
					.getBean("extScriptService");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("appID", appID);
			extScriptService.execScript(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Update To LastestDataBase Fail");
		} finally {
            InstallContextHolder.setInstallContext(null);
        }
	}

}

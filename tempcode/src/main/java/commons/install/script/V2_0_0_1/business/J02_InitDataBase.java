package commons.install.script.V2_0_0_1.business;

import javax.sql.DataSource;

import com.longtu.framework.install.IJavaScript;
import com.tjhq.commons.install.service.IInitSqlXmlService;
import com.tjhq.commons.install.service.impl.InstallContext;
import com.tjhq.commons.install.service.impl.InstallContextHolder;
import com.tjhq.commons.utils.ApplicationContextUtil;

public class J02_InitDataBase implements IJavaScript {

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
			//判断是否已经存在全局常量包，存在就是安装过，跳过
			if (initSqlXmlService.isExistsDBObject("PKG_CONSTANTS")){
				return;
			}
			String defaultYear = initSqlXmlService.getInstallDefaultYear();
			String defaultProvince = initSqlXmlService.getInstallDefaultProvince();
			String dbLinkName = initSqlXmlService.getInstallFaspDBUser();
			initSqlXmlService.initDataBase(defaultYear, defaultProvince,
					dbLinkName);
			initSqlXmlService.compileInvalidObjects();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Init DataBase Fail");
		} finally {
            InstallContextHolder.setInstallContext(null);
        }
	}

}

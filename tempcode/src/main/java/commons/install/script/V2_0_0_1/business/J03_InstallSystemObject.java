package commons.install.script.V2_0_0_1.business;

import javax.sql.DataSource;

import com.longtu.framework.install.IJavaScript;
import com.tjhq.commons.install.service.IInitSqlXmlService;
import com.tjhq.commons.install.service.impl.InitSqlXmlService;
import com.tjhq.commons.install.service.impl.InstallContext;
import com.tjhq.commons.install.service.impl.InstallContextHolder;
import com.tjhq.commons.utils.ApplicationContextUtil;

public class J03_InstallSystemObject implements IJavaScript {

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
			String dbLinkName = initSqlXmlService.getInstallFaspDBUser();
			//判断是否已经存在业务表登记，存在就是安装过，跳过
			if (initSqlXmlService.isExistsDBObject("DICT_T_MODEL")){
				return;
			}
			for (int i = 0; i < InitSqlXmlService.EXPORT_OBJECTS.length; i++) {
				initSqlXmlService.createObjects("1",
						InitSqlXmlService.EXPORT_OBJECTS[i], defaultYear,
						defaultProvince, dbLinkName);
			}
			initSqlXmlService.compileInvalidObjects();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Install SystemObject Fail");
		} finally {
            InstallContextHolder.setInstallContext(null);
        }
	}

}

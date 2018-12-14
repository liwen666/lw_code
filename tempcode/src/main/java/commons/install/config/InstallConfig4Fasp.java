package commons.install.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import com.longtu.framework.install.InstallConfig;
import com.tjhq.commons.dao.BusiDataSource;

public class InstallConfig4Fasp implements InstallConfig {
	String appID = null;
	String appVersion = null;
	DataSource dataSource;
	
	@Override
	public void expandPartition() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getAppid() {
		if (appID != null) {
			return appID.toLowerCase();
		}
		Properties prop = new Properties();
		try {
			prop.load(BusiDataSource.class
					.getResourceAsStream("/application.properties"));
			if (prop.containsKey("system.appid")) {
				appID = prop.getProperty("system.appid");
				return appID.toLowerCase();
			}
			return null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getInstallScriptURI() {
		// TODO Auto-generated method stub
		return "com.tjhq.commons.install.script";
	}

	@Override
	public String getInstallVersion() {
		return "V2_0_0_1";
	}

	@Override
	public String getSpecial() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataSource getTagDataSource(String dataSourceName) {
		return dataSource;
	}

	@Override
	public boolean needReboot() {
		// TODO Auto-generated method stub
		return false;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}

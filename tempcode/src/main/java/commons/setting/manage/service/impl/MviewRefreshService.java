package commons.setting.manage.service.impl;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tjhq.commons.setting.manage.service.IMviewRefreshService;
import com.tjhq.commons.sql.dao.ICommonDao;

@Service
public class MviewRefreshService implements IMviewRefreshService {
	/*@Resource
	private IManageMapper manageMapper;*/
	@Resource
	private ICommonDao commonDao;

	@Override
	public void execute() {
		this.refreshMview();
	}

	@Override
	public void refreshMview() {
		//manageMapper.mviewRefresh();
		//String userID = "";
	      //PROVINCE = PKG_CONSTANTS.GETDEFAULTPROVINCE
		//String agencyID = this.getSystemDefaultProvince();
		//String finYear = "";
		
		//String querySql = "select GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID') from dual";
		//Map<String, Object> queryMap = commonDao.queryForMap(querySql);
		//System.out.println("----------------------------------------------------------------------------refreshMview---------------------"+queryMap);

        /*commonDao.excute("select GLOBAL_MULTYear_CZ.Secu_f_GLOBAL_SetPARM" +
                "('',PKG_CONSTANTS.GETDEFAULTPROVINCE,'',PKG_CONSTANTS.GETDEFAULTPROVINCE) from dual");*/
	    this.setSystemDefaultInfo();
		String sql = "BEGIN DBMS_MVIEW.REFRESH('CODE_M_AGENCY','C'); DBMS_MVIEW.REFRESH('CODE_M_AGENCY_SPF','C'); DBMS_MVIEW.REFRESH('BD_M_SYSORG','C');END; ";
		commonDao.excute(sql);
	}
	/**
	 * 设置系统默认分区信息
	 */
	private void setSystemDefaultInfo(){
        commonDao.excute("select GLOBAL_MULTYear_CZ.Secu_f_GLOBAL_SetPARM" +
                "('',PKG_CONSTANTS.GETDEFAULTPROVINCE," +
                " (SELECT FINYEAR FROM DICT_T_BUDGETYEAR WHERE PROVINCE = PKG_CONSTANTS.GETDEFAULTPROVINCE)," +
                "PKG_CONSTANTS.GETDEFAULTPROVINCE) from dual");
	}
	/**
	 * 从配置文件中获取系统默认分区地区
	 * @return
	 */
	@SuppressWarnings("unused")
    private String getSystemDefaultProvince() {
		String defaultProvince = null;
		Properties prop = new Properties();
		try {
			prop.load(MviewRefreshService.class
					.getResourceAsStream("/application.properties"));
			if (prop.containsKey("system.defaultprovince")) {
				defaultProvince = prop.getProperty("system.defaultprovince");
				return defaultProvince;
			}
			throw new RuntimeException("未获取到系统默认分区，请检查application.properties文件的system.defaultprovince属性.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}

	@Override
	public void mviewRefreshDisDept() {
		/*String userID = "";
		String agencyID = this.getSystemDefaultProvince();
		String finYear = "";
		commonDao.excute("select GLOBAL_MULTYear_CZ.Secu_f_GLOBAL_SetPARM('"
				+ userID
				+ "','"
				+ agencyID
				+ "','"
				+ finYear
				+ "','" + agencyID + finYear + "') from dual");*/

        this.setSystemDefaultInfo();
        
		String sql = "BEGIN DBMS_MVIEW.REFRESH('CODE_M_DISTRICT','C'); DBMS_MVIEW.REFRESH('CODE_M_DEPARTMENT','C'); END; ";
		commonDao.excute(sql);
	}
}

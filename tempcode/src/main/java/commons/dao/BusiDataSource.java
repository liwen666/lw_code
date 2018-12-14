
package commons.dao;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.tjhq.commons.install.service.impl.InstallContext;
import com.tjhq.commons.install.service.impl.InstallContextHolder;
import com.tjhq.commons.servlet.VariableContext;

public class BusiDataSource implements DataSource {

    Logger logger = Logger.getLogger(BusiDataSource.class);

    DataSource dataSource;
    String defaultProvince = null;
    String defaultYear = null;
    String appID = null;

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return dataSource.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        dataSource.setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        dataSource.setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return dataSource.getLoginTimeout();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return dataSource.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return dataSource.isWrapperFor(iface);
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = getDataSource().getConnection();

        // System.out.println("----------------------------startSqlSessionParamSet-------------------------------------------");
        UserDTO user = SecureUtil.getCurrentUser();
        // 安装升级用
        InstallContext installContext = InstallContextHolder.getInstallContext();
        if (installContext != null) {
            if (installContext.getProvinceCode() == null || installContext.getProvinceCode().equals("")) {
                return conn;
            }
            String userID = "";
            String agencyID = installContext.getProvinceCode();
            String finYear = installContext.getYear();
            String sql = "select GLOBAL_MULTYear_CZ.Secu_f_GLOBAL_SetPARM('" + userID + "','" + agencyID + "','"
                    + finYear + "','" + agencyID + finYear + "') from dual";

            logger.debug("installContext:" + sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ps.close();
            return conn;
        }
        // // 同步用
        // Synch2PartitionInfo synch2PartitionInfo = Synch2PartitionSwitch
        // .getSynch2PartitionInfo();
        //
        // if (synch2PartitionInfo != null) {
        // // 其实只有接收导入方才会走这里切分区，导出时候走不到这里，因为user导出时是非空的
        // // 导入时，通过远程回调的方式，user是空的，所以导入时要的分区就是目标分区的省份码
        //			
        // //2016.2.19 此处切换分区似乎一点用处都没有，导出时根本不会走这里切分区，导出时一定会有登录用户，按照用户分区执行
        // //导入的情况是，每一次先去查询bgt_t_timeplan表，读取目标分区信息，之后才会在导入代码中设置Synch2PartitionInfo信息，
        // //进行导入；问题出在定时任务查询bgt_t_timeplan表，此时他就会走这里获取一条连接，但实际上这个时候，Synch2PartitionInfo信息
        // //还未进行设置！所以每一次定时任务会直接把分区切到getSystemProvince()，getSystemYear()也就是00分区，而bgt_t_timeplan表又不是
        // //分区表，所以能取到数据，同样的再进行插入业务数据到临时表时，province和year是通过SQL语句生成器直接把值写成了bgt_t_timeplan表中的目标分区，所以都是正确的
        //			
        // //正确的做法应是修改同步的部分存储过程（涉及从视图取数据的），由JAVA程序传参目标分区信息，在存储过程中进行设置分区；或者直接在java代码里通过mapper直接切分区，不再通过公用
        // //这里切分区，因为切了也没用
        // String agencyID = synch2PartitionInfo.getProvince();
        // String finYear = synch2PartitionInfo.getYear() + "";
        // String sql = "select GLOBAL_MULTYear_CZ.Secu_f_GLOBAL_SetPARM('','"
        // + agencyID + "','" + finYear + "','') from dual";
        // logger.debug("synch2PartitionInfo:" + sql);
        // PreparedStatement ps = conn.prepareStatement(sql);
        // ps.execute();
        // ps.close();
        // System.out.println("当前连接HASH值：" + conn.hashCode());
        // return conn;
        // }

        if (user != null) {
            String userID = "";
            String agencyID = getSystemProvince();
            String appID = getSystemAppID();
            
            logger.debug("Current APP is : " + appID);
            
            String finYear = VariableContext.getVariable("finYear");
            userID = user.getGuid();
            if ("0".equals(agencyID)) {
                // 如果是财政用户，取提升后的地区
                if (user.getUsertype() == 1) {
                    agencyID = user.getUpagencyCode();
                } else {
                    agencyID = SecureUtil.getUserSelectProvince();
                }

            }
            logger.debug("BusiDataSource user agency is : " + agencyID);
            // agencyID = new
            // StringBuffer("'").append(agencyID).append("'").toString();
            // 如果是基础信息采集,取默认分区
            if ("BAS".equals(appID)) {
                agencyID = "PKG_CONSTANTS.GETDEFAULTPROVINCE";
            }
            // 如果是项目库,取中间表专项ID
            if ("SPF".equals(appID)) {
                String docID = "";
                if (VariableContext.getVariable("docID") != null) {
                    docID = VariableContext.getVariable("docID");
                    logger.debug("Change docID:" + VariableContext.getVariable("docID"));
                }
//                if (docID != null && !docID.equals("")) {
//                    StringBuffer selectSql = new StringBuffer(
//                            "SELECT (SELECT CODE FROM CODE_T_DISTRICT_SPF WHERE GUID = DISTRICTID) AS PROVINCE ")
//                            .append(" FROM P#SPF_T_FBASEINFO S WHERE SPFID = ").append(
//                                    " (SELECT TASKID FROM SPF_T_OARELATION WHERE DOCID = '").append(docID).append(
//                                    "' AND TASKTYPE = '0' AND ROWNUM < 2)");
                    String spfProvinceCode = "";
//                    PreparedStatement ps = conn.prepareStatement(selectSql.toString());
//                    ResultSet rs = ps.executeQuery();
//                    if (rs.next()) {
//                        spfProvinceCode = rs.getString("PROVINCE");
//                    }
//                    rs.close();
//                    ps.close();
                    if (VariableContext.getVariable("regionCode") != null) {
                    	spfProvinceCode = VariableContext.getVariable("regionCode");
                        logger.debug("Change regionCode:" + VariableContext.getVariable("regionCode"));
                    }
                    if (spfProvinceCode != null && !spfProvinceCode.equals("")) {
                        agencyID = spfProvinceCode;
                        logger.debug("Change agencyID:" + VariableContext.getVariable("agencyID"));
                    }
//                }
                // 如果区划为空,取系统默认分区
                if (agencyID == null || "".equals(agencyID)) {
                    StringBuffer selectSql = new StringBuffer(
                            "SELECT PKG_CONSTANTS.GETDEFAULTPROVINCE AS PROVINCE FROM DUAL");
                    PreparedStatement ps = conn.prepareStatement(selectSql.toString());
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        agencyID = rs.getString("PROVINCE");
                    }
                    rs.close();
                    ps.close();
                }
            }
			// 如果是绩效,取中间表专项ID或者项目ID
			if ("KPI".equals(appID)) {
			    String docID = "";
                if (VariableContext.getVariable("docID") != null) {
                    docID = VariableContext.getVariable("docID");
                    logger.debug(" APP : KPI Change docID:" + docID);
                }
                String regionCode = "";
                if (VariableContext.getVariable("regionCode") != null) {
                    regionCode = VariableContext.getVariable("regionCode");
                    if (StringUtils.isNotEmpty(regionCode)) {
                        agencyID = regionCode;
                    }
                    logger.debug(" APP : KPI Change regionCode:" + VariableContext.getVariable("regionCode"));
                }
                // 如果区划为空,取系统默认分区
                if (agencyID == null || "".equals(agencyID)) {
                    StringBuffer selectSql = new StringBuffer(
                            "SELECT PKG_CONSTANTS.GETDEFAULTPROVINCE AS PROVINCE FROM DUAL");
                    PreparedStatement ps = conn.prepareStatement(selectSql.toString());
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        agencyID = rs.getString("PROVINCE");
                    }
                    rs.close();
                    ps.close();
                }
            }

            if (null == finYear || "".equals(finYear.trim())) {
                String finYearSql = "SELECT FINYEAR FROM DICT_T_BUDGETYEAR WHERE PROVINCE = {0}";
                if ("PKG_CONSTANTS.GETDEFAULTPROVINCE".equals(agencyID)) {
                    finYearSql = MessageFormat.format(finYearSql, MessageFormat.format("(SELECT {0} FROM DUAL)", agencyID));
                } else {
                    finYearSql = MessageFormat.format(finYearSql, "'"+agencyID+"'") ;
                }
                PreparedStatement ps = conn.prepareStatement(finYearSql);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    finYear = rs.getString("FINYEAR");
                }
                rs.close();
                ps.close();
            }

            if (null == finYear || "".equals(finYear.trim())) {
                logger.error(MessageFormat.format("当前地区没有默认预算年度 : province={0}, 请检查PUB_T_PARTITION_DIVID表设置信息。",
                        agencyID));
            }

            StringBuffer sql = new StringBuffer("select GLOBAL_MULTYear_CZ.Secu_f_GLOBAL_SetPARM('").append(userID)
                    .append("','").append(agencyID).append("','").append(finYear).append("','").append(agencyID)
                    .append(finYear).append("') ");
            // 如果是基础信息采集,取默认分区
            if ("BAS".equals(appID)) {
                sql = new StringBuffer("select GLOBAL_MULTYear_CZ.Secu_f_GLOBAL_SetPARM('").append(userID).append(
                        "',").append(agencyID).append(",'").append(finYear).append("',").append(agencyID).append(
                        "||'").append(finYear).append("') ");
            }
            if (VariableContext.getVariable("taskID") != null) {
                sql.append(",GLOBAL_MULTYEAR_CZ.F_SET_TASKID('").append(VariableContext.getVariable("taskID"))
                        .append("') ");
                logger.debug("Change taskID:" + VariableContext.getVariable("taskID"));
            }
            sql.append(" FROM DUAL ");
            logger.debug("Change user province:" + sql);
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.execute();
            ps.close();

        } else {
            String userID = "";
            String agencyID = getSystemProvince();
            String finYear = getSystemYear();
            String sql = "select GLOBAL_MULTYear_CZ.Secu_f_GLOBAL_SetPARM('" + userID + "','" + agencyID + "','"
                    + finYear + "','" + agencyID + finYear + "') from dual";
            logger.debug("Change default province:" + sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ps.close();
        }
        // System.out.println("----------------------------endSqlSessionParamSet-------------------------------------------");
        return conn;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getDataSource().getConnection(username, password);
    }

    private String getSystemProvince() {
        if (defaultProvince != null) {
            return defaultProvince;
        }
        Properties prop = new Properties();
        try {
            prop.load(BusiDataSource.class.getResourceAsStream("/application.properties"));
            if (prop.containsKey("system.province")) {
                defaultProvince = prop.getProperty("system.province");
                return defaultProvince;
            }
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getSystemYear() {
        if (defaultYear != null) {
            return defaultYear;
        }
        Properties prop = new Properties();
        try {
            prop.load(BusiDataSource.class.getResourceAsStream("/application.properties"));
            if (prop.containsKey("system.year")) {
                defaultYear = prop.getProperty("system.year");
                return defaultYear;
            }
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getSystemAppID() {
    	if (VariableContext.getVariable("appID") != null && !("").equals(VariableContext.getVariable("appID")) ){
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

    public DataSource getDataSource() {

        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}

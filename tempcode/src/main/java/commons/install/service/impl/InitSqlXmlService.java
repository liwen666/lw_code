package commons.install.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.jdom.CDATA;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.inputcomponent.utils.ToolUtil;
import com.tjhq.commons.install.dao.IInstallDataBaseDao;
import com.tjhq.commons.install.po.InstallLogObject;
import com.tjhq.commons.install.po.InstallSqlObject;
import com.tjhq.commons.install.service.IInitSqlXmlService;

@Service
@Transactional(readOnly = true)
public class InitSqlXmlService implements IInitSqlXmlService {

	@Resource
	private IInstallDataBaseDao installDataBaseDao;

	public static final String ROOT_ELEMENT = "INSTALL_SQL";

	public static final String SQL_ELEMENT = "SQL";
	public static final String OBJECTNAME_ELEMENT = "OBJECT_NAME";
	public static final String SQLCONTENT_ELEMENT = "SQL_CONTENT";
	public static final String SQLCOMMENT_ELEMENT = "SQL_COMMENT";

	public static final String OBJTYPE_TYPE = "TYPE";
	public static final String OBJTYPE_SEQUENCE = "SEQUENCE";
	public static final String OBJTYPE_TABLE = "TABLE";
	public static final String OBJTYPE_PACKAGE = "PACKAGE";
	public static final String OBJTYPE_FUNCTION = "FUNCTION";
	public static final String OBJTYPE_PROCEDURE = "PROCEDURE";
	public static final String OBJTYPE_VIEW = "VIEW";
	public static final String OBJTYPE_PACKAGEBODY = "PACKAGE BODY";
	public static final String OBJTYPE_TYPEBODY = "TYPE BODY";
	public static final String OBJTYPE_JAVASOURCE = "JAVA SOURCE";
	public static final String OBJTYPE_TRIGGER = "TRIGGER";
	public static final String OBJTYPE_MV = "MV";
	public static final String OBJTYPE_DATA = "DATA";

	public static final String COLUMN_OBJNAME = "OBJ_NAME";
	public static final String COLUMN_SQLCOMMENT = "OBJ_COMMENT";
	public static final String COLUMN_SQLTEXT = "SQL_TEXT";

	public static final String GLOBAL_PACKAGE_NAME = "GLOBAL_MULTYEAR_CZ";

	public static final String CONSTANTS_PACKAGE_NAME = "PKG_CONSTANTS";

	public static final String VAR_DEFAULT_YEAR = "#DEFAULT_YEAR#";

	public static final String VAR_DEFAULT_PROVINCE = "#DEFAULT_PROVINCE#";

	public static final String VAR_SYN_FASP_PARAM = "#SYN_FASP_PARAM#";

	public static final String INIT_SQLFILENAME = "installInitSQL.xml";

	public static final String WORKFLOW_SQLFILENAME = "installWorkFlowSQL.xml";

	public static final String SYS_STRUCTSQLFILENAME = "installSysStructSQL.xml";

	public static final String BUSI_STRUCTSQLFILENAME = "installBusiStructSQL.xml";

	public static final String BUSI_DATASQLFILENAME = "installBusiDataSQL.xml";

	public static final String[] INIT_OBJECTS = new String[] { OBJTYPE_PACKAGE,
			OBJTYPE_PACKAGEBODY, OBJTYPE_TABLE, OBJTYPE_VIEW, OBJTYPE_MV };

	public static final String[] WORKFLOW_OBJECTS = new String[] {
			OBJTYPE_TABLE, OBJTYPE_DATA };

	public static final String[] EXPORT_OBJECTS = new String[] { OBJTYPE_TYPE,
			OBJTYPE_SEQUENCE, OBJTYPE_TABLE, OBJTYPE_PACKAGE, OBJTYPE_FUNCTION,
			OBJTYPE_PROCEDURE, OBJTYPE_VIEW, OBJTYPE_PACKAGEBODY,
			OBJTYPE_TYPEBODY, OBJTYPE_JAVASOURCE, OBJTYPE_TRIGGER };

	public void generateInstallSqlFile() throws Exception {
		// 系统表结构脚本
		// generateInstallStructSqlFile("1");
		// 业务表结构脚本
		generateInstallStructSqlFile("0");
	}

	private void generateInstallStructSqlFile(String isSysObj) throws Exception {
		String exportSqlFileName = SYS_STRUCTSQLFILENAME;
		if ("0".equals(isSysObj)) {
			exportSqlFileName = BUSI_STRUCTSQLFILENAME;
		}
		Element root = new Element(ROOT_ELEMENT);
		Document document = new Document(root);
		for (int i = 0; i < EXPORT_OBJECTS.length; i++) {
			String objectType = EXPORT_OBJECTS[i];
			Map<String, String> parameterMap = new HashMap<String, String>();
			parameterMap.put("objType", objectType);
			parameterMap.put("isSysObj", isSysObj);
			List<InstallSqlObject> tableSqlList = installDataBaseDao
					.getInstallSqlListByObjType(parameterMap);
			if (tableSqlList != null && tableSqlList.size() > 0) {
				if (objectType.indexOf(" ") != -1) {
					objectType = objectType.replaceAll(" ", "_");
				}
				Element tableElement = new Element(objectType);
				root.addContent(tableElement);
				for (InstallSqlObject installSqlObject : tableSqlList) {
					Element sqlElement = new Element("SQL");
					tableElement.addContent(sqlElement);
					Element objNameElement = new Element(OBJECTNAME_ELEMENT);
					objNameElement.addContent(installSqlObject.getObjName());
					Element sqlCommentElement = new Element(SQLCOMMENT_ELEMENT);
					sqlCommentElement.addContent(new CDATA(installSqlObject
							.getObjComment()));
					Element sqlContentElement = new Element(SQLCONTENT_ELEMENT);
					sqlContentElement.addContent(new CDATA(installSqlObject
							.getSqlText()));
					sqlElement.addContent(objNameElement);
					sqlElement.addContent(sqlCommentElement);
					sqlElement.addContent(sqlContentElement);
				}
			}
		}
		XMLOutputter xmlOut = new XMLOutputter();
		FileOutputStream outFile = new FileOutputStream(exportSqlFileName);
		try {

			Format f = Format.getPrettyFormat();
			f.setEncoding("UTF-8");
			xmlOut.setFormat(f);
			xmlOut.output(document, outFile);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			outFile.flush();
			outFile.close();
		}
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void createObjects(String isSysObj, String objectType,
			String defaultYear, String defaultProvince, String dbLinkName)
			throws Exception {
		String createSqlFileName = SYS_STRUCTSQLFILENAME;
		if ("0".equals(isSysObj)) {
			createSqlFileName = BUSI_STRUCTSQLFILENAME;
		}
		InputStream sqlFile = getInstallFileInputStream(createSqlFileName);
		try {
			if (objectType.indexOf(" ") != -1) {
				objectType = objectType.replaceAll(" ", "_");
			}
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(sqlFile);
			Element root = document.getRootElement();
			createObjects(root, objectType, defaultYear, defaultProvince,
					dbLinkName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlFile.close();
		}
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void installWorkFlow(String defaultYear, String defaultProvince)
			throws Exception {
		InputStream sqlFile = getInstallFileInputStream(WORKFLOW_SQLFILENAME);
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(sqlFile);
			Element root = document.getRootElement();
			for (int i = 0; i < WORKFLOW_OBJECTS.length; i++) {
				createObjects(root, WORKFLOW_OBJECTS[i], defaultYear,
						defaultProvince, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlFile.close();
		}
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void initDataBase(String defaultYear, String defaultProvince,
			String dbLinkName) throws Exception {
		InputStream sqlFile = getInstallFileInputStream(INIT_SQLFILENAME);
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(sqlFile);
			Element root = document.getRootElement();
			String objectType = "";
			for (int i = 0; i < INIT_OBJECTS.length; i++) {
				objectType = INIT_OBJECTS[i];
				if (objectType.indexOf(" ") != -1) {
					objectType = objectType.replaceAll(" ", "_");
				}
				createObjects(root, objectType, defaultYear, defaultProvince,
						dbLinkName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlFile.close();
		}
	}

	private void createObjects(Element root, String objectType,
			String defaultYear, String defaultProvince, String dbLinkName) {
		Element objectElement = root.getChild(objectType);
		if (objectElement != null) {
			List<Element> sqlElementList = objectElement
					.getChildren(SQL_ELEMENT);
			if (sqlElementList != null && sqlElementList.size() > 0) {
				for (Element sqlElement : sqlElementList) {
					Element sqlContentElement = sqlElement
							.getChild(SQLCONTENT_ELEMENT);
					InstallLogObject installLogObj = new InstallLogObject();
					String logID = ToolUtil.getUUID();
					installLogObj.setLogID(logID);
					installLogObj.setObjectType(objectType);
					installLogObj.setObjectName(sqlElement.getChild(
							OBJECTNAME_ELEMENT).getText());
					installLogObj.setObjectComment(sqlElement.getChild(
							SQLCOMMENT_ELEMENT).getText());
					installLogObj.setIsSuccess("1");
					String sqlContent = sqlContentElement.getText();

					if (sqlContent != null && !sqlContent.trim().equals("")) {
						// 如果是全局包，需要替换默认年度和区划
						if (OBJTYPE_PACKAGE.equals(installLogObj
								.getObjectType())
								&& GLOBAL_PACKAGE_NAME.equals(installLogObj
										.getObjectName())) {
							if (defaultYear != null
									&& !defaultYear.trim().equals("")) {
								// 默认年度
								sqlContent = sqlContent.replaceFirst(
										VAR_DEFAULT_YEAR, defaultYear);
							}
							if (defaultProvince != null
									&& !defaultProvince.trim().equals("")) {
								// 默认区划
								sqlContent = sqlContent.replaceFirst(
										VAR_DEFAULT_PROVINCE, defaultProvince);
							}
						}
						// 如果是全局常量，需要替换默认区划
						if (OBJTYPE_PACKAGE.equals(installLogObj
								.getObjectType())
								&& CONSTANTS_PACKAGE_NAME.equals(installLogObj
										.getObjectName())) {
							if (defaultProvince != null
									&& !defaultProvince.trim().equals("")) {
								// 默认区划
								sqlContent = sqlContent.replaceFirst(
										VAR_DEFAULT_PROVINCE, defaultProvince);
							}
						}
						// 如果是全局包体，需要根据同义词或者用户名增加平台库同步线程方法
						if (OBJTYPE_PACKAGEBODY.replaceFirst(" ", "_").equals(
								installLogObj.getObjectType())
								&& GLOBAL_PACKAGE_NAME.replaceFirst(" ", "_")
										.equals(installLogObj.getObjectName())) {
							String addSynFaspSql = "";
							if (dbLinkName != null
									&& !dbLinkName.trim().equals("")) {
								// 判断是否dbLink
								int isDbLink = installDataBaseDao
										.isExistsDBLink(dbLinkName);

								// dbLink
								if (isDbLink == 1) {
									addSynFaspSql = "return global_multyear_cz.Secu_f_GLOBAL_SetPARM@"
											+ dbLinkName
											+ "(v_userID,v_pmDivID,v_pmYear);";
								}
								// 用户名
								else {
									addSynFaspSql = "return "
											+ dbLinkName
											+ ".global_multyear_cz.Secu_f_GLOBAL_SetPARM(v_userID,v_pmDivID,v_pmYear);";
								}

							}
							// 同库
							else {
								addSynFaspSql = " return '1';";
							}
							// 同步平台线程方法替换
							sqlContent = sqlContent.replaceFirst(
									VAR_SYN_FASP_PARAM, addSynFaspSql);
						}
						try {
							installLogObj.setSqlText(sqlContent);
							installLogObj.setIsSuccess("1");
							installDataBaseDao.insertInstallLog(installLogObj);
							installDataBaseDao.executeInstallSql(logID);
							// 安装完成的清除SQL脚本
							installDataBaseDao.clearInstallLogSQL(logID);
						} catch (Exception e) {
							installLogObj.setIsSuccess("0");
							installLogObj.setErroInfo(e.getMessage());
							installDataBaseDao.updateInstallLog(installLogObj);
						}
					} else {
						installLogObj.setIsSuccess("0");
						installLogObj.setErroInfo("无脚本内容");
						installDataBaseDao.insertInstallLog(installLogObj);
					}
				}
			}
		}
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void compileInvalidObjects() {
		installDataBaseDao.compileInvalidObjects();
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void recreateInvalidObjects(int recreateTime) {
		for (int i = 0; i < recreateTime; i++) {
			recreateInvalidObjects();
		}
	}

	private void recreateInvalidObjects() {
		List<InstallLogObject> erroInstallLogList = installDataBaseDao
				.getErroInstallLogList();
		if (erroInstallLogList != null && erroInstallLogList.size() > 0) {
			for (InstallLogObject installLogObj : erroInstallLogList) {
				try {
					installDataBaseDao.executeInstallSql(installLogObj
							.getLogID());
					installLogObj.setIsSuccess("1");
					installLogObj.setErroInfo("");
					installDataBaseDao.updateInstallLog(installLogObj);
					// 安装完成的清除SQL脚本
					installDataBaseDao.clearInstallLogSQL(installLogObj
							.getLogID());
				} catch (Exception e) {
					installLogObj.setIsSuccess("0");
					installLogObj.setErroInfo(e.getMessage());
					installDataBaseDao.updateInstallLog(installLogObj);
				}
			}
			installDataBaseDao.compileInvalidObjects();
		}
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void initBusiData(String defaultYear, String defaultProvince)
			throws Exception {
		InputStream sqlFile = getInstallFileInputStream(BUSI_DATASQLFILENAME);
		try {
			// 创建默认分区
			installDataBaseDao.initDefaultPartition(defaultYear,
					defaultProvince);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("初始化默认分区失败");
		}
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(sqlFile);
			Element root = document.getRootElement();
			createObjects(root, "DATA", defaultYear, defaultProvince, null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlFile.close();
		}
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void initBusiData4Fasp(String defaultYear, String defaultProvince)
			throws Exception {
		InputStream sqlFile = getInstallFileInputStream(BUSI_DATASQLFILENAME);
		try {
			// 创建默认分区
			installDataBaseDao.initDefaultPartition(defaultYear,
					defaultProvince);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("初始化默认分区失败");
		}
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(sqlFile);
			Element root = document.getRootElement();
			createObjects(root, "DATA", defaultYear, defaultProvince, null);
			Element objectElement = root.getChild("DATA");
			if (objectElement != null) {
				List<Element> sqlElementList = objectElement
						.getChildren(SQL_ELEMENT);
				if (sqlElementList != null && sqlElementList.size() > 0) {
					for (Element sqlElement : sqlElementList) {
						Element sqlContentElement = sqlElement
								.getChild(SQLCONTENT_ELEMENT);
						InstallLogObject installLogObj = new InstallLogObject();
						String logID = ToolUtil.getUUID();
						installLogObj.setLogID(logID);
						installLogObj.setObjectType("DATA");
						installLogObj.setObjectName(sqlElement.getChild(
								OBJECTNAME_ELEMENT).getText());
						// 如果是需要写平台表的数据，交给平台包执行
						if (installLogObj.getObjectName().equals("MENU_DATA")
								|| installLogObj.getObjectName().equals(
										"SECUFILTER_DATA")) {
							continue;
						}
						installLogObj.setObjectComment(sqlElement.getChild(
								SQLCOMMENT_ELEMENT).getText());
						installLogObj.setIsSuccess("1");
						String sqlContent = sqlContentElement.getText();
						if (sqlContent != null && !sqlContent.trim().equals("")) {
							try {
								installLogObj.setSqlText(sqlContent);
								installLogObj.setIsSuccess("1");
								installDataBaseDao
										.insertInstallLog(installLogObj);
								installDataBaseDao.executeInstallSql(logID);
								// 安装完成的清除SQL脚本
								installDataBaseDao.clearInstallLogSQL(logID);
							} catch (Exception e) {
								installLogObj.setIsSuccess("0");
								installLogObj.setErroInfo(e.getMessage());
								installDataBaseDao
										.updateInstallLog(installLogObj);
							}
						} else {
							installLogObj.setIsSuccess("0");
							installLogObj.setErroInfo("无脚本内容");
							installDataBaseDao.insertInstallLog(installLogObj);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlFile.close();
		}
	}

	private InputStream getInstallFileInputStream(String fileName) {
		return InitSqlXmlService.class.getClassLoader().getResourceAsStream(
				"com/tjhq/commons/install/sql/" + fileName);
	}

	/**
	 * 判断对象是否已经在数据库存在
	 * 
	 * @param objectName
	 *            对象名称
	 * @return 对象是否已经在数据库存在
	 */
	public boolean isExistsDBObject(String objectName) {
		int objCount = installDataBaseDao.isExistsDBObject(objectName);
		if (objCount > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断业务数据已经在数据库安装
	 * 
	 * @return 业务数据已经在数据库安装
	 */
	public boolean isExistsBusiData() {
		int objCount = installDataBaseDao.isExistsBusiData();
		if (objCount > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 得到安装默认地区
	 * @return 安装默认地区
	 */
	public String getInstallDefaultProvince() {
		String defaultProvince = installDataBaseDao.getInstallDefaultProvince();
		// 如果长度是6,说明是县级或者市本级，直接返回
		if (defaultProvince.length() == 6) {
			return defaultProvince;
		}
		// 如果不是00结尾,说明不是本级，需要补0
		if (!defaultProvince.endsWith("00")) {
			return defaultProvince + "00";
		}
		return defaultProvince;
	}

	/**
	 * 得到安装默认年份
	 * @return 安装默认年份
	 */
	public String getInstallDefaultYear() {
		return installDataBaseDao.getInstallDefaultYear();
	}

	/**
	 * 得到安装平台用户名
	 * @return 安装平台用户名
	 */
	public String getInstallFaspDBUser() {
		return installDataBaseDao.getInstallFaspDBUser();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String defaultProvince = "87";
		// 如果长度是6,说明是县级或者市本级，直接返回
		if (defaultProvince.length() == 6) {
			System.out.println(defaultProvince);
			return;
		}
		// 如果不是00结尾,说明不是本级，需要补0
		if (!defaultProvince.endsWith("00")) {
			System.out.println(defaultProvince + "00");
			return;
		}
		System.out.println(defaultProvince);
		// Element root = new Element(ROOT_ELEMENT);
		// Document document = new Document(root);
		// Element structElement = new Element(OBJTYPE_TABLE);
		// root.addContent(structElement);
		// Element sqlBodyElement = new Element("SQL");
		// structElement.addContent(sqlBodyElement);
		// Element sqlNameElement = new Element("SQL_NAME");
		// sqlNameElement.addContent("测试SQL");
		// Element sqlContentElement = new Element("SQL_CONTENT");
		// sqlContentElement
		// .addContent(new CDATA(
		// "select * from dict_t_factor where tableid = 'C3062F17E49C4A2BA93A94BB8458C320' and name like '%单位类别%' "));
		// sqlBodyElement.addContent(sqlNameElement);
		// sqlBodyElement.addContent(sqlContentElement);
		//
		// XMLOutputter xmlOut = new XMLOutputter();
		// try {
		// Format f = Format.getPrettyFormat();
		// f.setEncoding("UTF-8");// default=UTF-8
		// xmlOut.setFormat(f);
		// xmlOut.output(document, new FileOutputStream("D:/MyTestFile.xml"));
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

}

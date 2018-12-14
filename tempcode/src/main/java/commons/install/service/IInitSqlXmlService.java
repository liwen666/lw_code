package commons.install.service;

public interface IInitSqlXmlService {
	public void generateInstallSqlFile() throws Exception;

	public void initDataBase(String defaultYear, String defaultProvince,
                             String dbLinkName) throws Exception;

	public void installWorkFlow(String defaultYear, String defaultProvince)
			throws Exception;

	public void createObjects(String isSysObj, String objectType,
                              String defaultYear, String defaultProvince, String dbLinkName)
			throws Exception;

	public void compileInvalidObjects() throws Exception;

	public void recreateInvalidObjects(int recreateTime);

	public void initBusiData(String defaultYear, String defaultProvince)
			throws Exception;

	public void initBusiData4Fasp(String defaultYear, String defaultProvince)
			throws Exception;
	
	/**
	 * 判断对象是否已经在数据库存在
	 * 
	 * @param objectName  对象名称
	 * @return 对象是否已经在数据库存在
	 */
	public boolean isExistsDBObject(String objectName);
	
	/**
	 * 判断业务数据已经在数据库安装
	 * 
	 * @return 业务数据已经在数据库安装
	 */
	public boolean isExistsBusiData();
	
	/**
	 * 得到安装默认地区
	 * @return 安装默认地区
	 */
	public String getInstallDefaultProvince();

	/**
	 * 得到安装默认年份
	 * @return 安装默认年份
	 */
	public String getInstallDefaultYear();

	/**
	 * 得到安装平台用户名
	 * @return 安装平台用户名
	 */
	public String getInstallFaspDBUser();
}

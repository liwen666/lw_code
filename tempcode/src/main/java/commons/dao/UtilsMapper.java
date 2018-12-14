package commons.dao;

import com.tjhq.commons.Mybatis.SuperMapper;

/**
 * 通用数据库工具类
 * @author YAN
 * @CreateDate 2014-3-3
 * @version 1.0
 */
public interface UtilsMapper extends SuperMapper {

	/**
	 * 得到数据库的唯一ID(目前版本为oracle)
	 * @return 数据库的唯一ID
	 */
	public String getDBUniqueID();
	
	/**
	 * 通过单位ID获得districtID
	 * @param agencyId
	 * @return
	 */
	public String getDistrictByAgencyId(String agencyId);
	
	/**
	 * 通过单位ID返回单位所处的级别
	* @Title: getAgencyLevelNoByAgencyId 
	* @Description: 
	* @param  agencyId
	* @return String    
	* @throws
	 */
	public String getAgencyLevelNoByAgencyId(String agencyId);
	
	/**
	 * 通过单位ID获取该单位是否为叶子节点的字符串
	* @param @param agencyId
	* @return String    返回类型 
	 */
	public String getIsLeafByAgencyId(String agencyId);
	
	/**
	 * 通过用户的Division获取其所处的部门名称
	 */
	public String getDeptNameByDivisionId(String divisionId);
	
	/**
	 * 通过appid和Objectid获取tablename
	 */
	public String getTableNameByAppIdAndObjId(String appId, String objectId);
	
	/**
	 * 设置数据库全局taskID
	 * @param taskID
	 */
	public void setTaskParam(String taskID);
}

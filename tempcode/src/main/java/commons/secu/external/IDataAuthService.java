package commons.secu.external;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.secu.po.ColumnLimitPO;
import com.tjhq.commons.secu.po.SecuTColPO;
import com.tjhq.commons.secu.po.SecuTTablePO;


/**
 * 数据权限接口定义
 * @version 1.0
 * @CreateDate 2014/1/8
 * @author zushuxin
 *
 */
public interface IDataAuthService {
	
	/**
	 * 通过系统ID,表类型获得不同子系统的物理表名
	 * @param appId
	 * @param TableType
	 * @return String 数据库物理表名
	 */
	
	public String getTableDatabaseNameBy(String appId, String TableType);
	
	
    /**
     * 获得表权限通过用户id
     * @param tableID --表ID
     * @param userId --用户ID
     * @param appId --子系统ID
     * @param cascade --级联查询此表的行权限和列权限
     * @return SecuTTable --表权限实体类
     */
	public SecuTTablePO getTableAuthByUser(String tableID, String userId, String userType, String appId, boolean cascade);
	
	
	 /**
     * 获得表权限通过用户id
     * @param tableID --表ID
     * @param userId --用户ID
     * @param appId --子系统ID
     * @param cascade --级联查询此表的行权限和列权限
     * @return SecuTTable --表权限实体类
     */
	public SecuTTablePO getTableAuthByUser(String tableID, String userId, String userType, String appId, String targetDivId);
	
	
	/**
	 * 获得表行限制的SQL条件通过用户id
	 * @param tableID --表ID
	 * @param userId --用户ID
	 * @param appId --子系统ID
	 * @return String --获取所有行数据及行权限，单元格权限的SQL语句 例:select rowsecu,cellsecu,a.* from ...
	 */
	public String getTableRowAuthByUser(String tableID, String userId, String appId, String rightflag);
	 
	/**
	 * 获得表行限制的SQL条件通过用户id
	 * @param tableID --表ID
	 * @param userId --用户ID
	 * @param appId --子系统ID
	 * @return String --获取所有行数据及行权限，单元格权限的SQL语句 例:select rowsecu,cellsecu,a.* from ...
	 */
	public String getTableRowAuthByUser(String tableID, String userId, String appId);
	
	/**
	 * 获得表列限制集合通过用户id
	 * @param tableID --表ID
	 * @param userId --用户ID
	 * @return  Map<String,<SecuTCol>> --列权限实体类集成 String:colID,SecuTCol为列权限实体类。
	 */	public Map<String,SecuTColPO> getTableColsAuthByUser(String tableID, String userId);
	

	/**
	 * 获得套表下的所有只读可写的表集合（通过用户角色表权限过滤后的表集合）
	 * @param userId 用户ID
	 * @param suitId 套表ID
	 * @param appId 系统ID
	 * @return List<DictTSuitPO> 套表实体类
	 */
	
	public List<DictTModelPO> getTableListViaSecu(String userId, String suitId, String appId);
	
	/**
	 * 通过用户ID获得用户关联的单位
	 * @param userId 
	 * @param appId
	 * @return List<Map<String,String>>
	 */
	public List<Map<String,String>> getAgencyListByUserId(String userId, String appId);


	
	/**
	 * 通过tableID,获得单元格中的影响列的集合
	 * @param tableId 表ID
	 * @return String 数据库物理列名称
	 */
	
	public Set<String> getAffectColumnListForTableCellLimit(String tableId);
	
	
	/**
	 * 通过tableID,获得单元格中的被影响列的集合
	 * @param tableId 表ID
	 * @return String 数据库物理列名称 
	 */
	
	public List<String> getBeAffectedColumnListForTableCellLimit(String tableId);
	
	
	/**
	 * 获得本行数据的单元格权限
	 * @param tableId 业务表ID
	 * @param jsonRowDataStr 某一行的JSON格式的字符串 例如 ： {name:'xiaoli',sex:'mane',age:'19',address:'...',phone:'111'}
	 * @return String jsonRowDataStr字符串的单元格权限，例如： 10101010, 1为可写，0为只读。
	 */
	
	public String getRowCellSecuBy(String tableId, String jsonRowDataStr);
	
	/**
	 * 是否控制表权限
	 * @param tableID
	 * @param taskID
	 * @return
	 */
	public String isContrTableSecu(String tableID, String taskID);
	
	/**
	 * 获得数据权限和工作流程对表进行的权限控制结果
	 * @param appId 系统ID
	 * @param businessKey 业务流程实例的业务ID
	 * @param userId 用户ID
	 * @param userType 用户类别
	 * @param tableId 业务表ID
	 * @param cascade 是否级联查询改表的列权限
	 * @return SecuTTablePO 表权限实体类
	 */
	public SecuTTablePO getTableAuthForWorkflowByUser(String appId, String businessKey, String userId, String userType, String tableId, boolean cascade);

	/**
	 * 通过用户ID获得用户关联的单位sql
	 * @param userId 
	 * @param appId
	 * @return List<Map<String,String>>
	 */
	public String getAgencySqlByUserId(String userId, String appId);
	
	
	/**
	 * 通过tableID获取列限制信息
	 * @param tableID 
	 * @return ColumnLimitPO
	 */
	public List<ColumnLimitPO> getColumnLimitList(String userID, String tableID) throws ServiceException ;
	
}

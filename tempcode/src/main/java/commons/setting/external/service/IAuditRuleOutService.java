package commons.setting.external.service;

import java.util.List;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.setting.external.po.AuditRuleBaseInfoPO;


/**
 *@desc:数据审核对外接口
 *@author： wxn
 *@date:2014-10-28上午9:14:32
 */
public interface IAuditRuleOutService {
   /**
    * 通过单位Id 和审核规则id 执行审核
    * @param checkId 审核规则id
    * @return
    */
	public String getAuditResult(String checkId)throws Exception;
	/**
	 * 通过单位Id 和审核规则id 执行审核
	 * @param checkId 审核规则Id
	 * @param condition过滤条件 格式 and colname='44' and cloname2='55' 
	 * @return
	 */
	public String getAuditResult(String checkId, String conditions)throws Exception;
	/**
	 * 
	 * @param checkId 审核规则Id
	 * @param condition过滤条件 格式 @AGENCYID@= @WHERE@= AND 00101='1236'
	 * @param deltaData 是否增量审核标志 0 否 1是
	 * @return
	 */
	public String getAuditResult(String checkId, String conditions, String deltaData)throws Exception;
	/**
	 * 查询审核规则
	 * @param tableIDs 审核对象的id
	 * @param appID　系统appID
	 * @return
	 * @throws Exception
	 */
	public List<AuditRuleBaseInfoPO>  getAuditRuleList(List<String> tableIDList, String appID)throws ServiceException;
	/**
	 *  提供数据审核的SQL
	 * @param tableIDs List
	 * @param appID
	 * @return
	 * @throws Exception
	 */
	public String  getAuditRuleListSQL(List<String> tableIDs, String appID)throws Exception;
	/**
	 * 查询dbversion的sql
	 * @param tableIDs
	 * @param appID
	 * @return
	 * @throws ServiceException
	 */
	public String  getAuditRuleList4DbversionSql(List<String> tableIDList, String appID)throws ServiceException;
	/**
	 * 视图和表的关系是否可以删除
	 * @param tableID 表ID 
	 * @return true:可以删除
	 *          false:被引用 不可以删除
	 * @throws ServiceException
	 */
	public boolean canDelViewTOMaterialRaltion(String tableID)throws ServiceException;
}


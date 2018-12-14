package commons.setting.dataaudit.auditrule.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.po.TreePO;

/**
* @ClassName: IAuditRuleDefService
* @Description: 审核公式定义新的服务对象
* @author xiehongtao
* @date 2017-6-13 下午4:08:19
*
 */
public interface IAuditRuleDefService {
	
	/**
	 * 根据 审核ID 获取审核数据
	 * @param checkid
	 * @return map
	 */
	public Map<String, Object> getAuditData(String checkid);
	
	/**
	 * @throws Exception 
	* @Title: saveAuditData
	* @Description: 更新审核公式
	* @param @param formids
	* @param @return
	* @return Object
	* @throws
	 */
	public ResultPO saveAuditData(String formids) ;
	
	/**
	* @Title: checkAuditData
	* @Description: 检查审核公式
	* @param @param formids
	* @param @return
	* @param @throws Exception
	* @return Object
	* @throws
	 */
	public ResultPO checkAuditData(String formids) ;
	
	/**
	* @Title: parseAuditData
	* @Description: 解析审核定义到sql
	* @param @param formids
	* @param @return
	* @param @throws Exception
	* @return String
	* @throws
	 */
	public ResultPO parseAuditData(String formids) ;
	
	/**
	 * 查询财政级次
	 * @param userAgencyID
	 * @return
	 * @throws ServiceException
	 */
	public Integer findBudgetLevel(String userAgencyID) ;
	
	/**
	 * 得到审核表头定义
	 * @return
	 */
	public Table getAuditRuleTableDefine();
	
	/**
	 * 查询数据审核规则
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String,Object>> getDataAuditRuleList(Map<String, Object> param) throws ServiceException;
	/**
	 * 查询数据审核规则总数
	 * @return
	 * @throws ServiceException
	 */
	public int getDataAuditRuleList4count(Map<String, Object> param) throws ServiceException;
	
	/**
	 * 查看有几个规则被业务所引用
	 * @param checkIds
	 * @return
	 */
	public int findRelationBusiness(String checkIds);
	
	/**
	 * 批量保存审核规则
	 * @param table
	 * @return 
	 * @throws Exception
	 */
	public void batchDelAuditRule(Table table) throws Exception ;
	
	/**
	 * 查询审核验证数据
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getAuditRuleCheckList(Map<String, String> map);
	
	/**
	 * 得到系统函数定义
	 * @return
	 */
	public Table getSysfnTabDefine();
}

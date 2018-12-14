package commons.setting.dataaudit.rule.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.po.TreePO;

/**
 * 
 * @desc:审核验证规则  service
 * @author wxn
 * @date:2014-10-11下午2:36:36
 */
public interface IAuditRuleService{
	/**
	 * 查询审核分类数据
	 * @param appId
	 * @return
	 */
	public List<Map> getAuditCategoryTree(String appId);
    /**
     * 审核规则定义数据
     * @param map
     * @return
     */
	public List getAuditRuleDef(Map<String, String> map);
	/**
	 * 得到审核表头定义
	 * @return
	 */
	public Table getAuditRuleTableDefine();
	/**
	 * 得到审核类型表的列定义
	 * @return
	 */
	public Table getCheckTypeTableDefine();
    /**
     * 
     * @param grid
     * @return
     * @throws Exception
     */
	public Map<String,String> saveCheckSortData(Grid grid) throws Exception;
	/**
	 * 删除分类
	 * @param grid
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> delCheckSort(Grid grid) throws Exception;
	/**
	 * 根据id字符串
	 * @param map  checkSortIds='1','2'
	 * @return
	 * @throws Exception
	 */
	public void delCheckType(Map<String, String> map) throws Exception;
	/**
	 * 得到审核分类在审核规则中引用的数量
	 * @param paraMap checkSortIds='1','2'
	 * @return
	 */
	public int getCheckDefTypeNum(Map<String, String> paraMap);
	/**
	 * 验证父亲是否被引用
	 * @param superId
	 * @return
	 */
	public int checkRelationBySuperId(String superId);
	/**
	 *得到新分类
	 * @param appId
	 * @return
	 */
	public List<TreePO> getNewAuditCategoryTree(String appId);
	/**
	 * 公式验证
	 * @param map 
	 */
	public void check(Map map);
	
	/**
	 *公式验证的表达式验证，数值型
	 * @param map
	 */
	public void checkNum(Map map);
	/**
	 * 数据审核 INSERT
	 * @param formMap
	 * @throws ServiceException 
	 */
	public void saveAuditData(Map formMap) throws ServiceException;
	/**
	 * 审核定义 UPDATE
	 * @param formMap
	 */
	public void modifyAuditData(Map formMap);
	/**
	 * 组装 CHECKSQL
	 * @param map
	 * @param checkType 表间 1，表内 0
	 * @return
	 */
	public Map<String, Object> makeSql(Map map, String checkType);
	/**
	 * 根据 审核ID 获取审核数据
	 * @param checkid
	 * @return map
	 */
	public Map getAuditData(String checkid);
	/**
	 * 根据id 查找分类
	 * @param typeId
	 * @return
	 */
	public String getAuditCategoryNameById(String typeId);
	/**
	 * 查询审核验证数据
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getAuditRuleCheckList(Map map);
	/**
	 * 得到系统函数定义
	 * @return
	 */
	public Table getSysfnTabDefine();
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
	public void batchDelAuditRule(Table table)throws Exception;
	/**
	 * 查询审核规则基本信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getAuditRuleBaseInfo(Map<String, Object> param)throws Exception;
	//验证 审核类别的序号唯一性
	public int checkLvUnique(Map<String, Object> m)throws Exception;

	public String checkView(Map<String, Object> formMap);
	/**
	 * 检测审核名称
	 * @param m
	 * @return 
	 */
	public int checkAuditSortNameUnique(Map<String, Object> m);
	/**
	 * 查询财政级次
	 * @param userAgencyID
	 * @return
	 * @throws ServiceException
	 */
	public Integer findBudgetLevel(String userAgencyID)throws ServiceException;
	/**
	 * 查询数据审核规则
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String,Object>> getDataAuditRuleList(Map<String, Object> param)throws ServiceException;
	/**
	 * 查询数据审核规则总数
	 * @return
	 * @throws ServiceException
	 */
	public int getDataAuditRuleList4count(Map<String, Object> param)throws ServiceException;
}


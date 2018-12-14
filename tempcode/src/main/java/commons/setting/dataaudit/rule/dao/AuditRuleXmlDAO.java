package commons.setting.dataaudit.rule.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;

/**
 *@desc:数据审核dao
 *@author： wxn
 *@date:2016-2-29下午2:17:42
 */
public interface AuditRuleXmlDAO extends SuperMapper {
	/**
	 * 查询数据审核数据
	 * @param paraMap
	 * @return
	 */
	public List<Map<String,String>> findAuditData(Map<String, Object> paraMap);
    /**
     * 查询当前时间
     * @return
     */
	public String findSysDate();
	/**
	 * 保存审核规则
	 * @param formMap
	 */
	public void insertAuditData(Map<String, Object> paraMap);
	/**
	 * 查询审核规则是否存在
	 * @param paraMap
	 * @return
	 */
	public int findCountByCheckID(Map<String, Object> paraMap);
	/**
	 * 查询审核分类是否存在
	 * @param paraMap
	 * @return
	 */
	public int findCountByCheckSortID(Map<String, Object> paraMap);
	/**
	 * 查询表id是否存在
	 * @param tableID
	 * @return
	 */
	public int findCountTableID(String tableID);
	/**
	 * 查询审核分类数据
	 * @param paraMap
	 * @return
	 */
	public List<Map<String, String>> findAuditSortDataByExp(
            Map<String, Object> paraMap);
	/**
	 * 查询审核分类数据 从是上往下差
	 * @param paraMap
	 * @return
	 */
	public List<Map<String, String>> findAuditSortDataFromRoot(
            Map<String, Object> paraMap);
	/**
	 * 保存审核分类
	 * @param srcEle
	 */
	public void saveAuditSort(Map<String, Object> srcEle);
	/**
	 * 更新level
	 * @param srcEle
	 */
	public void updateLevelHasSuperID(Map<String, Object> srcEle);
	/**
	 * 更新level
	 * @param srcEle
	 */
	public void updateLevelNullSuperID(Map<String, Object> srcEle);
	/**
	 * 更新审核规则
	 * @param paraMap
	 */
	public void updateAuditData(Map<String, Object> paraMap);
	/**
	 * 清楚已经删除的数据
	 */
	public void clearAuditData();
	/**
	 * 
	 * @param paraMap
	 * @return
	 */
	public List<Map<String, String>> findViewToTable(Map<String, Object> paraMap);
	/**
	 * 
	 * @param paraMap
	 * @return
	 */
	public List<Map<String, String>> findInputObjToAuditObj(
            Map<String, Object> paraMap);
	/**
	 * 查询录入对象对应审核对象
	 * @param inputObjID
	 * @return
	 */
	public List<Map<String, String>> findByInputObj(String inputObjID);
	/**
	 * 插入录入对象和审核对象的关系
	 * @param inputObjToAuditObjData
	 */
	public void insertInputObjToAuditObj(
            Map<String, Object> inputObjToAuditObjData);
	/**
	 * 查询视图对应物理表数据
	 * @param tableID
	 * @return
	 */
	public List<Map<String, String>> findViewToTableByViewID(String viewID);
	/**
	 * 插入视图对应物理表
	 * @param viewToTableData
	 */
	public void insertViewToTable(Map<String, Object> viewToTableData);
	/**
	 * 插叙财政级次
	 * @param paraMap
	 * @return
	 */
	public Integer findCreateBgtLevelByCheckID(Map<String, Object> paraMap);

	

}


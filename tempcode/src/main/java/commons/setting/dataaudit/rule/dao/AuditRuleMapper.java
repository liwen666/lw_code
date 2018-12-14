 package commons.setting.dataaudit.rule.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.inputcomponent.po.TreePO;
import com.tjhq.commons.setting.dataaudit.rule.po.TreeNodePO;

/**
 *@desc:审核规则mapper
 *@author： wxn
 *@date:2014-10-11下午2:43:58
 */
public interface AuditRuleMapper extends SuperMapper {
    /**
     * 查询审核分类数据
     * @param paraMap
     * @return
     */
	List<Map> getAuditCategoryTree(Map paraMap);
	/**
	 * 新树的数据结构
	 * @param paraMap
	 * @return
	 */
	List<TreePO> getNewAuditCategoryTree(Map paraMap);
    /**
     * 查询审核规则定义
     * @param paraMap
     * @return
     */
	List<Map<String, String>> getAuditRuleDef(Map<String, String> paraMap);
	/**
	 * 更新节点的endFlag的值
	 * @param paraMap
	 */
	void updateEndFlag(Map<String, String> paraMap);
	/**
	 * 删除审核类型
	 * @param paraMap
	 */
	void delCheckType(Map<String, String> paraMap);
	/**
	 * 得到查询定义的数量
	 * @param paraMap
	 * @return
	 */
	int getCheckDefTypeNum(Map<String, String> paraMap);
	/**
	 * 验证父亲是否被引用
	 * @param String superId
	 * @return
	 */
	int checkRelationBySuperId(String superId);

	/**
	 * 验证 数据审核 分组|条件|表达式
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> check(Map map);
	public List<Map<String,Object>> checkByWhere(Map map);
	public void checkNum(Map map);

	/**
	 * 保存 数据审核 CHECK_T_DEF
	 * @param formMap
	 */
	public void insertAuditData(Map formMap);
	public void modifyAuditData(Map map);
	/**
	 * 根据id查询 审核数据
	 * @param checkid
	 * @return
	 */
	public Map getAuditData(String checkid);
	/**
	 * 根据id查实际的分类名称
	 * @param typeId
	 * @return
	 */
	public String getAuditCategoryNameById(String typeId);
	/**
	 * 得到审核数据定义
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getAuditRuleCheckList(Map map);
	/**
	 * 查看有几个规则被业务所引用
	 * @param checkIds
	 * @return
	 */
	public int findRelationBusiness(String checkIds);
	/**
	 * 查询审核规则基本信息
	 * @param param
	 * @return
	 */
	public List<Map<String,Object>> getAuditRuleBaseInfo(Map<String, Object> param);
	/**
	 * 
	 * @param m
	 * @return
	 */
	public int checkLvUnique(Map<String, Object> m);
	/**
	 * 查询物理表数量
	 * @param l_tableID
	 * @return
	 */
	public int findMaterialTalbleCount(String viewID);
	/**
	 * 查询dbversion 的数量
	 * @param dbtablename
	 * @return
	 */
	public int findDbversionColCount(String dbtableName);
	/**
	 * 检查审核分类名称是否唯一，同一层级下不能有重名
	 * @param m
	 */
	public int findAuditSortNameCount(Map<String, Object> m);
	/**
	 * 查找superID
	 * @param m
	 * @return
	 */
	public String findAuditSortSuperID(Map<String, Object> m);
	/**
	 * 
	 * @param userAgencyID
	 * @return
	 */
	public Integer findDistLevel(String userAgencyID);
	/**
	 * 查询数据规则
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> getDataAuditRuleList(Map<String, Object> param);
	/**
	 * 查询总数
	 * @param param
	 * @return
	 */
	public int getDataAuditRuleList4count(Map<String, Object> param);
	
}


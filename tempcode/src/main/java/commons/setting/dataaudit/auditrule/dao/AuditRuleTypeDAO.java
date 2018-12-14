package commons.setting.dataaudit.auditrule.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.inputcomponent.po.TreePO;

/**
 * 
* @ClassName: AuditRuleTypeDAO
* @Description: 审核分类数据库操作类
* @author xiehongtao
* @date 2017-6-27 下午1:26:03
*
 */
public interface AuditRuleTypeDAO extends SuperMapper {
	
    /**
     * 查询审核分类数据 已经废弃
     * @param paraMap
     * @return
     */
	List<Map> getAuditCategoryTree(Map paraMap);
	
	/**
	 * 查询审核分类数据树的数据结构
	 * @param paraMap
	 * @return
	 */
	List<TreePO> getNewAuditCategoryTree(Map paraMap);
	
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
	 * 根据id查实际的分类名称
	 * @param typeId
	 * @return
	 */
	public String getAuditCategoryNameById(String typeId);
	
	/**
	 *查询 LVLID 是否重复
	 * @param m
	 * @return
	 */
	public int checkLvUnique(Map<String, Object> m);
	
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
	
}

 package commons.setting.dataaudit.auditrule.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.inputcomponent.po.TreePO;

/**
* @ClassName: AuditRuleDefDAO
* @Description: 审核规则定义相关Mapper类
* @author xiehongtao
* @date 2017-6-15 下午1:14:33
 */
public interface AuditRuleDefDAO extends SuperMapper {

    /**
     * 查询审核规则定义
     * @param paraMap
     * @return
     */
	List<Map<String, String>> getAuditRuleDef(Map<String, String> paraMap);

	/**
	 * 验证 数据审核 分组|条件|表达式
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> check(Map map);
	
	/**
	* @Title: checkByWhere
	* @Description: 检查where条件是否正确
	* @param @param map
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	 */
	public List<Map<String,Object>> checkByWhere(Map map);
	
	/**
	* @Title: checkNum
	* @Description: 检查数值
	* @param @param map
	* @return void
	* @throws
	 */
	public void checkNum(Map map);

	/**
	 * 保存 数据审核 CHECK_T_DEF
	 * @param formMap
	 */
	public void insertAuditData(Map formMap);
	
	/** 
	* @Title: modifyAuditData
	* @Description: 修改审核规则
	* @param @param map
	* @return void
	* @throws
	 */
	public void modifyAuditData(Map map);
	/**
	 * 根据id查询 审核数据
	 * @param checkid
	 * @return
	 */
	public Map getAuditData(String checkid);

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
	

	/** 
	* @Title: getAuditDataAdv
	* @Description: 根据id查询 审核数据 高级版 将表名称和审核分组名称都提取出来了 避免循环
	* @param @param checkid
	* @param @return
	* @return Map
	* @throws
	 */
	public Map getAuditDataAdv(String checkid);
	
}


package commons.setting.external.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.setting.external.po.AuditRuleBaseInfoPO;
import com.tjhq.commons.setting.external.po.AuditRulePO;

/**
 *@desc:审核规则mapper
 *@author： wxn
 *@date:2014-10-28上午9:28:54
 */
public interface AuditRuleOutMapper extends SuperMapper {
	public void getAuditResult(AuditRulePO map);
    /**
     * 查询数据审核规则
     * @param tableIDs
     * @param appID
     * @return
     */
	public List<AuditRuleBaseInfoPO> queryAuditRuleList(@Param(value = "sql") String sql);
	/**
	 * 查询表再规则中引用的数量
	 * @param paraMap
	 * @return
	 */
	public int findAuditRuleCountInUsed(Map<String, String> paraMap);
	/**
	 * 查询dbversion 的数量
	 * @param dbtablename
	 * @return
	 */
	public int findDbversionColCount(String dbtableName);

}


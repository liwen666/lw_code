package commons.dict.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dict.external.po.DictTFactorPO;

/**
 * 列
 * 
 * @author xujingsi
 * 
 */
public interface DictTFactorSelfMapper extends SuperMapper {

	/**
	 * 添加
	 * 
	 * @param dictTFactor
	 */
	public void insertDictTFactor(DictTFactorPO dictTFactor);

	/**
	 * 修改
	 * 
	 * @param dictTFactor
	 */
	public void updateDictTFactor(DictTFactorPO dictTFactor);

	/**
	 * 删除
	 * 
	 * @param String
	 *            columnid
	 */
	public void deleteDictTFactor(String columnid);

	/**
	 * 删除表下所有列
	 * 
	 * @param tableID
	 *            表ID
	 */
	public void deleteAllDictTFactorByTableID(String tableID);

	/**
	 * 获取制定表中列 的最大ORDERID
	 * 
	 * @param tableid
	 * @return
	 */
	public Map<String, Object> getMAXColumnOrderid(Map<String, Object> map);

	/**
	 * 获取最大ORDERID
	 * 
	 * @param param
	 * @return 最大ORDERID
	 */
	public int getMAXOrderidBySuperID(Map<String, Object> param);

	public void copyFactorDefineToYear(Map param);

	public Integer copyFactorDefineToProvince(Map param);

	public List<Map<String, String>> checkFactorIsCopy(Map param);

	public void copyOneFactorDefineToYear(Map param);

	public void updateFactorDefinitionForYear(Map param);

	/**
	 * 该字段是否被下发
	 */
	public int findOtherRepeatNameCount(Map<String, String> map);
	
	/**
	 * 重新计算levelNo
	 * 
	 */
	public void updateDictTFactorLevelNo(String tableID);
	
	/**
	 * 得到创建备份表需要的列
	 * @param tableID 表ID
	 * @return 创建备份表需要的列
	 */
	public List<DictTFactorPO> getBakTableColumnList(@Param("tableID") String tableID);
}

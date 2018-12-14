package commons.setting.external.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;

/**
* 类名称：IFormulaOuterService  
* 类描述：公式定义接口
* 创建人：shishj
* 创建时间：Feb 27, 2014 2:08:01 AM
* @version 1.0
 */
public interface FormulaOuterMapper extends SuperMapper{
	
	/**
	 * 表内列公式
	 * @param expMap
	 * @return
	 */
	public void oneTableFormula(Map<String, String> formula);
	/**
	 * 表间公式
	 * @param appID TODO
	 * @param tableId
	 * @param agencyId
	 * @return
	 */
	public void betweenTableFormula(String appID, @Param("tableId") String tableId, @Param("agencyId") String agencyId);
	/**
	 * 取数列公式
	 * @param tableId
	 * @param agencyId
	 */
	public void calculateFomula_A8(@Param("tableId") String tableId, @Param("agencyId") String agencyId);
	
	/**
	 * 取数复杂公式
	 * @param tableId
	 * @param agencyId
	 */
	public void calculateFomula_A0(@Param("tableId") String tableId, @Param("agencyId") String agencyId);

	
	public List<Map<String, Object>> getForComCol_RefColumn(String tableId);
	
	/**
	 * 根据 表|公式类型 判断是否存在公式
	 * @param map
	 * @return
	 */
	public int existsFormulaCount(Map<String, Object> map);
	
	/**
	 * 判断当前表是否存在公式
	 * @param tableID
	 * @return
	 */
	public int isDefineFormula(String tableID);
	
	public void calculateCellFormula(Map<String, String> params);
	
	void deleteFormulaByTableID(Map<String, String> map);
}

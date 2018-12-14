package commons.setting.external.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.exception.ServiceException;

/**
 * 
* 类名称：IFormulaOuterService  
* 类描述：公式定义接口
* 创建人：shishj
* 创建时间：Feb 27, 2014 2:08:01 AM
* @version 1.0
 */
// 0表内列公式/1表内行公式[单元格]/8表间公式/A8取数列公式/A0取数复杂公式
public interface IFormulaOuterService {
	/**
	 * 表内列公式
	 * @param data
	 * @param tableId
	 * @return Map<String,Object>
	 * @throws Exception
	 */
    public Map<String,Object> oneTableFormula(String data, String tableId) throws Exception;
	/**
	 * 表间公式
	 * @param appID TODO
	 * @param tableId
	 * @param agencyId
	 * @return boolean
	 */
    public boolean betweenTableFormula(String appID, String tableId, String agencyId);
	/**
	 * 返回公式列 | 公式影响列
	 * @param tableId
	 * @return Map<String,List<String>>
	 */
    public Map<String,List<String>> getForComCol_RefColumn(String tableId);
	/**
	 * 获取 单元格公式 格式 
	 * @param tableID
	 * @param dealType
	 * @return List<Map<String,Object>>
	 */
    public List<Map<String,Object>> getCellFormulaFormat(String tableID);
	
	/**
	 * 根据 表|公式类型 判断是否存在公式 
	 * include <取数列公式 取数复杂公式 来源条件定义>
	 * @param tableID
	 * @return int
	 */
    public int existsFormulaCount(String tableID);
	
	/**
	 * 判断表格是否存在公式
	 * 包括：0表内列公式/1表内行公式[单元格]/8表间列/9工资统发/A0复杂取数/A8列取数[定义同8公式]
	 * @param tableID
	 * @return
	 */
    public boolean isDefineFormula(String tableID);
	
	/**
	 * 取数列公式
	 * @param tableId
	 * @param agencyId
	 */
    public boolean calculateFomula_A8(@Param("tableId") String tableId, @Param("agencyId") String agencyId);
	
	/**
	 * 取数复杂公式
	 * @param tableId
	 * @param agencyId
	 * @throws Exception 
	 */
    public boolean calculateFomula_A0(@Param("tableId") String tableId, @Param("agencyId") String agencyId) throws Exception;
	
	/**
	 * 计划单元格公式
	 * @param tableID
	 * @param formulaCell
	 * @param refFormulaCell
	 * @param paramMap TODO
	 * @return
	 */
    public List<String> calculateCellFormula(String tableID, String formulaCell, String refFormulaCell, Map<String, Object> paramMap);
    public List<Map<String, String>> selectFormulaColumn(@Param("tableID") String tableID, @Param("formulaType") String formulaType);
	 /**根据表ID与公式类型删除公式
	 * @param tableID
	 * @param formulaType
	 * @throws Exception
	 * @throws
	 */
    public void deleteFormulaByTableID(String tableID, String formulaType) throws ServiceException;
     /**.外部调用刷新公式
     * @param tableID
     * @param formulaType
     * @param tableType
     * @throws ServiceException
     * @throws
     */
    void refreshFormula(String tableID, String formulaType, String tableType) throws ServiceException;
}

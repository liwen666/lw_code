package commons.setting.formula.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.setting.formula.po.FormulaTFormulaDefPO;
import com.tjhq.commons.setting.input.po.TreeNode;

public interface IFormulaService {
	/**.
	 * 根据不同的定义类型 ，创建不同的表头
	 * @param tableID 表ID
	 * @param defineID 公式类型
	 * @return Table
	 * @throws ServiceException 业务异常
	 * @throws
	 */
	 Table getDefineHead(String tableID, String defineID) throws ServiceException;
	
	/**.
	 * 保存 公式定义
	 * @param formula 公式PO
	 * @param operator 操作  修改、新增、校验
	 * @return String
	 * @throws ServiceException 业务异常
	 */
	 String saveFormulaData(FormulaTFormulaDefPO formula, String operator) throws ServiceException;
    /**.校验与解析公式
     * @param formulaDefChi 公式中文内容
     * @param formulaType 公式类型
     * @param tableID 表ID
     * @param dealType 表类型
     * @return Map<String, String>
     * @throws ServiceException 业务异常
     * @throws
     */
     Map<String, String> covertAndVerify(String formulaDefChi, String formulaType, String tableID, String dealType) throws ServiceException;

	/**
	 * 插入 公式数据 （暂时不用）
	 * @param formula
	 * @return
	 */
//	public String insertFormulaData(FormulaTFormulaDefPO formula);
	
	/**
	 * 更新 公式数据 （暂时不用）
	 * @param formula
	 * @return
	 */
//	public String updateFormulaData(FormulaTFormulaDefPO formula);

	/**
	 * 删除 公式数据
	 * @param lists
	 * @return
	 */
	public Boolean deleteFormulaData(List<Map<String, Object>> lists) throws Exception;
	
	/**
	 * 根据不同的定义类型 ，查询不同的表数据
	 * @param tableID 表ID
	 * @param formulaID 公式ID
	 * @param defineID 公式类型
	 * @return
	 */
	public List<FormulaTFormulaDefPO> selectFormulaData(String tableID, String formulaID, String defineID);
	
	 /**.
	  * 获取公式表内容
	 * @param grid Grid
	 * @return Object
	 * @throws ServiceException 业务异常
	 * @throws
	 */
	Object getData(Grid grid) throws ServiceException;
	/**
	 * 获取 公式 最大序号
	 * @param tableID
	 * @param formulaType
	 * @return
	 */
	public Integer formulaOrderID(@Param("tableID") String tableID, @Param("formulaType") String formulaType);
	
	/**
	 * 查询 表内行公式 单元格公式
	 * @param tableID
	 * @param forComcol 公式列
	 * @param forWhere FDCode编码
	 * @return
	 */
	public FormulaTFormulaDefPO selectCellFormula(String tableID, String forComcol, String forWhere);
	
	//public List<Map<String, String>> selectFormulaColumn(@Param("tableID") String tableID,@Param("formulaType") String formulaType);
	/**
	 * 调用存储过程 返回ORACLE 错误信息
	 * @param sql
	 * @throws Exception
	 */
	public void callErrorMessage(String sql) throws Exception;
	
	/**
	 * 根据CSID 查询 代码表数据
	 * @param dbTableName
	 * @return
	 */
	public List<TreeNode> getModelCodeDataByCsid(String dbTableName) throws Exception;
	/**
     * 获取 业务系统
     * @return List
     */
    public List<Map<String, Object>> getAppList();
    /**
     * .获取在公式表中存在的数据
     * @param tableID
     * @param defineID
     * @return
     * @throws
     */
    List<Map<String, Object>> getExitData(String tableID, String defineID);

}

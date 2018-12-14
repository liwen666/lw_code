package commons.setting.formula.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.setting.formula.po.FormulaTFormulaDefPO;

public interface FormulaDAO extends SuperMapper{
	/**
	 * 根据不同的定义类型 ，删除不同的表数据
	 * @param map(tableID 表ID \ formulaID 公式ID \defineID 公式类型)
	 * @return
	 */
	public List<FormulaTFormulaDefPO> selectFormulaData(Map<String, Object> map);
	//改成list<map>
	public List<Map<String, Object>> selectFormulaGridData(Map<String, Object> map);
	/**
	 * 插入 公式数据
	 * @param formula
	 * @return
	 */
	public Integer insertFormulaData(FormulaTFormulaDefPO formula);
	/**
	 * 更新 公式数据
	 * @param formula
	 * @return
	 */
	public Integer updateFormulaData(FormulaTFormulaDefPO formula);
	
	/**
	 * 删除 公式数据
	 * @param lists
	 * @return
	 */
	public Integer deleteFormulaData(List<Map<String, Object>> lists);
	
	/**
	 * 校验 公式数据
	 * @param sql语句
	 * @return
	 */
	public Integer verifyFormulaDef(@Param("sql") String sql);
	
	/**
	 * 校验是否是数字类型
	 */
	public String verifyFormula(@Param("sql") String sql);
	/**
	 * 返回数据库错误信息
	 * @param map
	 * @throws Exception
	 */
	public void callErrorMessage(Map map) throws Exception;
	
	/**
	 * 根据公式列 判断是否存在公式
	 * @param map <tableID,forComcol,formulaID>
	 * @return
	 */
	public Integer formulaExist(FormulaTFormulaDefPO formula);
	/**
	 * 刷新公式
	 * @param formulaDefChi 中文公式
	 * @param formulaID 公式ID
	 * @return
	 */
	public Integer refreshFormula(@Param("formulaDefChi") String formulaDefChi, @Param("formulaID") String formulaID);
	/**
	 * 获取 公式 最大序号
	 * @param tableID
	 * @param formulaType
	 * @return
	 */
	public Integer formulaOrderID(@Param("tableID") String tableID, @Param("formulaType") String formulaType);
	/**
	 * 查询 表内行公式 单元格公式
	 * @param map
	 * @return
	 */
	public FormulaTFormulaDefPO selectCellFormula(Map<String, Object> map);
	/**
	 * 查询 表内行公式 单元格公式列
	 * @param tableID
	 * @param formulaType
	 * @return
	 */
	public List<Map<String, String>> selectFormulaColumn(@Param("tableID") String tableID, @Param("formulaType") String formulaType);
	/**
	 * 执行存储过程--表间公式0
	 */
	public String callPDropVoidTriggerForParam(Map<String, String> map) throws Exception;
	/**
	 * 执行存储过程--表间公式1
	 */
	public String callPCreateTrigger8ForParam(Map<String, String> map) throws Exception;
	
	public Map<String, String> getColNameByColID(Map<String, String> mm);
	/**
     * 获取 业务系统
     * @return
     */
    public List<Map<String, Object>> getAppList();
    /**
     * .判断表间公式needupdate字段长度
     */
    public void get_Needupdate_Length(Map<String, String> map);
}

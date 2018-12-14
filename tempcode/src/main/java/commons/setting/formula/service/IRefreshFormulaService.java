package commons.setting.formula.service;


public interface IRefreshFormulaService {

	/**
	 * 通过 存储的物理公式 转化为 中文公式
	 * @param tableID 表
	 * @param formulaID 公式ID
	 * @param defineID 公式类型
	 * @param dealType 表处理类型
	 * @return
	 */
	public String updateFormulaDefChi(String tableID, String formulaID, String defineID, String dealType) throws Exception;

}

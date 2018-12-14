package commons.setting.formula.po;

import java.io.Serializable;

//公式定义主表
public class FormulaTFormulaDefPO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String tableID; //对应表TABLEID
	private int orderID;
	
	private String formulaID; //公式ID
	private String formulaName; //公式名称
	private String formulaType; //公式类型：0表内列公式/1表内行公式[单元格]/8表间列/9工资统发/A0复杂取数[使用forwhere进行标志]/A8列取数[定义同8公式]
	private String formulaLvl;	//公式级次
	private String formulaDef; //公式内容，Oracle表达式显示 CLOB
	private String isPublic; //是否公共公式
	private String formulaDefChi; //公式内容，中文显示表达式 CLOB
	private String forComcol; //计算列
	private String forWhere; //计算列行条件
	private String forParmCol; //需要通过参数匹配
	
	private String forComcolName; //计算列 中文名

	private String formulaDefEng; //公式内容，带『』
	private String dealType; //处理类型
	private String isEdit;//公式列是否可编辑

	//用于保存 公式
	private String tableName;
	
	public String getTableID() {
		return tableID;
	}
	public void setTableID(String tableID) {
		this.tableID = tableID;
	}
	public String getFormulaID() {
		return formulaID;
	}
	public void setFormulaID(String formulaID) {
		this.formulaID = formulaID;
	}
	public String getFormulaName() {
		return formulaName;
	}
	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}
	public String getFormulaType() {
		return formulaType;
	}
	public void setFormulaType(String formulaType) {
		this.formulaType = formulaType;
	}
	public String getFormulaLvl() {
		return formulaLvl;
	}
	public void setFormulaLvl(String formulaLvl) {
		this.formulaLvl = formulaLvl;
	}
	public String getFormulaDef() {
		return formulaDef;
	}
	public void setFormulaDef(String formulaDef) {
		this.formulaDef = formulaDef;
	}
	public String getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}
	public String getFormulaDefChi() {
		return formulaDefChi;
	}
	public void setFormulaDefChi(String formulaDefChi) {
		this.formulaDefChi = formulaDefChi;
	}
	public String getForComcol() {
		return forComcol;
	}
	public void setForComcol(String forComcol) {
		this.forComcol = forComcol;
	}
	public String getForWhere() {
		return forWhere;
	}
	public void setForWhere(String forWhere) {
		this.forWhere = forWhere;
	}
	public String getForParmCol() {
		return forParmCol;
	}
	public void setForParmCol(String forParmCol) {
		this.forParmCol = forParmCol;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getFormulaDefEng() {
		return formulaDefEng;
	}
	public void setFormulaDefEng(String formulaDefEng) {
		this.formulaDefEng = formulaDefEng;
	}
	public String getForComcolName() {
		return forComcolName;
	}
	public void setForComcolName(String forComcolName) {
		this.forComcolName = forComcolName;
	}
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public String getIsEdit() {
		return isEdit;
	}
	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}


}

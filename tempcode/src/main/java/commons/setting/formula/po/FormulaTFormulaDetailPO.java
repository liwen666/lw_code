package commons.setting.formula.po;

import java.io.Serializable;

public class FormulaTFormulaDetailPO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String tableID; //表ID
	private String formulaID; //公式ID
	private int lineID; //子公式序号
	private String formulaType; //公式类型
	private String forComcol; //计算列内容
	private String forWhere; //计算列所在行
	private String forWhereCol; //计算列所在行[中文显示]
	
	private String refTableID; //对应所在表
	private String refColID; //对应列
	private String refWhere; //对应Where条件
	private String refWhereCol; //对应Where条件包含的列
	private String refComcont; //对应的列计算表达式
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
	public int getLineID() {
		return lineID;
	}
	public void setLineID(int lineID) {
		this.lineID = lineID;
	}
	public String getFormulaType() {
		return formulaType;
	}
	public void setFormulaType(String formulaType) {
		this.formulaType = formulaType;
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
	public String getForWhereCol() {
		return forWhereCol;
	}
	public void setForWhereCol(String forWhereCol) {
		this.forWhereCol = forWhereCol;
	}
	public String getRefTableID() {
		return refTableID;
	}
	public void setRefTableID(String refTableID) {
		this.refTableID = refTableID;
	}
	public String getRefColID() {
		return refColID;
	}
	public void setRefColID(String refColID) {
		this.refColID = refColID;
	}
	public String getRefWhere() {
		return refWhere;
	}
	public void setRefWhere(String refWhere) {
		this.refWhere = refWhere;
	}
	public String getRefWhereCol() {
		return refWhereCol;
	}
	public void setRefWhereCol(String refWhereCol) {
		this.refWhereCol = refWhereCol;
	}
	public String getRefComcont() {
		return refComcont;
	}
	public void setRefComcont(String refComcont) {
		this.refComcont = refComcont;
	}


}

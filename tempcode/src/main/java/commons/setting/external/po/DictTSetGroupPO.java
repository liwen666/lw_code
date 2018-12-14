package commons.setting.external.po;

import java.io.Serializable;


public class DictTSetGroupPO implements Serializable{
	
	private static final long serialVersionUID = 3647233284813657927L;

	private String tableID; //表ID	
	private int orderID; //分组序号
	private String columnID; //分组列ID
	//------
	private String name; //中文名称
	private String dbColumnName; //列名
	
	private String idShowCol; //显示编码位置
	private String nameShowCol;//显示名称位置
	private String isasc; //排序方式
	
	private String check;

	private String stage; //阶段
	private String SNAME_idShowCol; 
	private String SNAME_nameShowCol;
	private String SNAME_isasc; 
	
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getTableID() {
		return tableID;
	}
	public void setTableID(String tableID) {
		this.tableID = tableID;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getColumnID() {
		return columnID;
	}
	public void setColumnID(String columnID) {
		this.columnID = columnID;
	}
	public String getIdShowCol() {
		return idShowCol;
	}
	public void setIdShowCol(String idShowCol) {
		this.idShowCol = idShowCol;
	}
	public String getNameShowCol() {
		return nameShowCol;
	}
	public void setNameShowCol(String nameShowCol) {
		this.nameShowCol = nameShowCol;
	}
	public String getIsasc() {
		return isasc;
	}
	public void setIsasc(String isasc) {
		this.isasc = isasc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDbColumnName() {
		return dbColumnName;
	}
	public void setDbColumnName(String dbColumnName) {
		this.dbColumnName = dbColumnName;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public String getSNAME_idShowCol() {
		return SNAME_idShowCol;
	}
	public void setSNAME_idShowCol(String sNAME_idShowCol) {
		SNAME_idShowCol = sNAME_idShowCol;
	}
	public String getSNAME_nameShowCol() {
		return SNAME_nameShowCol;
	}
	public void setSNAME_nameShowCol(String sNAME_nameShowCol) {
		SNAME_nameShowCol = sNAME_nameShowCol;
	}
	public String getSNAME_isasc() {
		return SNAME_isasc;
	}
	public void setSNAME_isasc(String sNAME_isasc) {
		SNAME_isasc = sNAME_isasc;
	}
	


}

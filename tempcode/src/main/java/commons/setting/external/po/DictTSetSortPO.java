package commons.setting.external.po;

import java.io.Serializable;


public class DictTSetSortPO implements Serializable{
	private static final long serialVersionUID = 3647233284813657927L;
	
	private String tableID;
	private int orderID;
	private String columnID;
	private String ascFlag; //1升序，0降序
	//------
	private String name; //列名称
	private String dbColumnName; //中文列名
	
	private String isReserve; //是否保留
	private String isDefault; //是否默认
	
	private String stage; //阶段
	private String SNAME_ascFlag;

	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
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
	public String getAscFlag() {
		return ascFlag;
	}
	public void setAscFlag(String ascFlag) {
		this.ascFlag = ascFlag;
	}
	public String getIsReserve() {
		return isReserve;
	}
	public void setIsReserve(String isReserve) {
		this.isReserve = isReserve;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getSNAME_ascFlag() {
		return SNAME_ascFlag;
	}
	public void setSNAME_ascFlag(String sNAME_ascFlag) {
		SNAME_ascFlag = sNAME_ascFlag;
	}
	

}

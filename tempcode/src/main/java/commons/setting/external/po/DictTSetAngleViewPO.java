package commons.setting.external.po;

import java.io.Serializable;


public class DictTSetAngleViewPO implements Serializable{
	private static final long serialVersionUID = 3647233284813657927L;
	
	private String tableID;
	private int orderID;
	private String columnID;	
	private String name; //中文列名
	private String dbColumnName; //列名	
	private String isDefault; //是否默认	
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
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	
}

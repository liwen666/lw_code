package commons.setting.input.po;

import java.io.Serializable;

public class DictTSetBaseNumSub implements Serializable{
	private static final long serialVersionUID = 3647233284813657927L;

	private String guID; // 主键
	private String columnID; // DICT_T_SETBASENUMTAB 外键
	private int orderID; //序号
	private String colValue; //基本信息值
	private String SNAME_colValue;

	public String getGuID() {
		return guID;
	}
	public void setGuID(String guID) {
		this.guID = guID;
	}
	public String getColumnID() {
		return columnID;
	}
	public void setColumnID(String columnID) {
		this.columnID = columnID;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getColValue() {
		return colValue;
	}
	public void setColValue(String colValue) {
		this.colValue = colValue;
	}
	public String getSNAME_colValue() {
		return SNAME_colValue;
	}
	public void setSNAME_colValue(String value) {
		SNAME_colValue = value;
	}
}

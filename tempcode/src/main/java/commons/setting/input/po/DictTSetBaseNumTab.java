package commons.setting.input.po;

import java.io.Serializable;
//基本数字表设置 DICT_T_SETBASENUMTAB
import java.util.List;

public class DictTSetBaseNumTab implements Serializable{
	private static final long serialVersionUID = 3647233284813657927L;
	private String guID;//主键
	
	private String tableID; //表ID
	
	private String columnID; //列ID
	
	private String columnName; //列中文名
	
	private String csid;
	private String SNAME_columnID;
	
	//查询列设置
	private List<DictTSetBaseNumSub> baseNumSub;
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getGuID() {
		return guID;
	}
	public void setGuID(String guID) {
		this.guID = guID;
	}
	public String getTableID() {
		return tableID;
	}
	public void setTableID(String tableID) {
		this.tableID = tableID;
	}
	public String getColumnID() {
		return columnID;
	}
	public void setColumnID(String columnID) {
		this.columnID = columnID;
	}
	public List<DictTSetBaseNumSub> getBaseNumSub() {
		return baseNumSub;
	}
	public void setBaseNumSub(List<DictTSetBaseNumSub> baseNumSub) {
		this.baseNumSub = baseNumSub;
	}
	public String getCsid() {
		return csid;
	}
	public void setCsid(String csid) {
		this.csid = csid;
	}
	public String getSNAME_columnID() {
		return SNAME_columnID;
	}
	public void setSNAME_columnID(String sname_columnid) {
		SNAME_columnID = sname_columnid;
	}
			

}

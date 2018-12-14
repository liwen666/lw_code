package commons.setting.input.po;

import java.io.Serializable;

public class ConditionSetPo implements Serializable{
	private static final long serialVersionUID = 3647233284813657927L;
	
	private String guid;
	private String appID;
	private String condition;
	private String tableID;
	private String tableType;
	
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getTableID() {
		return tableID;
	}
	public void setTableID(String tableID) {
		this.tableID = tableID;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

}

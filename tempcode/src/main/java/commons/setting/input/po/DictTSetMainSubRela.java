package commons.setting.input.po;

import java.io.Serializable;

//采集录入表关系设置
public class DictTSetMainSubRela implements Serializable{
	private static final long serialVersionUID = 3647233284813657927L;
	
	private String mainSubID; // 与DictTSetMainSubTab 外键
	
	private String mainTabID; // 主表ID
	private String subTabID; // 子表ID
	
	private String mainFkID; // 主表 外键 ID
	private String fkID; // 子表 外键 ID
	
	public String getMainSubID() {
		return mainSubID;
	}
	public void setMainSubID(String mainSubID) {
		this.mainSubID = mainSubID;
	}
	public String getMainTabID() {
		return mainTabID;
	}
	public void setMainTabID(String mainTabID) {
		this.mainTabID = mainTabID;
	}
	public String getSubTabID() {
		return subTabID;
	}
	public void setSubTabID(String subTabID) {
		this.subTabID = subTabID;
	}
	public String getMainFkID() {
		return mainFkID;
	}
	public void setMainFkID(String mainFkID) {
		this.mainFkID = mainFkID;
	}
	public String getFkID() {
		return fkID;
	}
	public void setFkID(String fkID) {
		this.fkID = fkID;
	}
	



}

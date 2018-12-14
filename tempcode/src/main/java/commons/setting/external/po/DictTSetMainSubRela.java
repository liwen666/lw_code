package commons.setting.external.po;

import java.io.Serializable;

//采集录入表关系设置
public class DictTSetMainSubRela implements Serializable {
	private static final long serialVersionUID = 3647233284813657927L;

	private String mainSubID; // 与DictTSetMainSubTab 外键

	private String mainTabID; // 主表ID
	private String subTabID; // 子表ID

	private String mainFkID; // 主表 外键 ID
	private String fkID; // 子表 外键 ID

	private String mainTabName;
	private String subTabName;
	private String mainFkName;
	private String fkName;

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

	public String getMainTabName() {
		return mainTabName;
	}

	public void setMainTabName(String mainTabName) {
		this.mainTabName = mainTabName;
	}

	public String getSubTabName() {
		return subTabName;
	}

	public void setSubTabName(String subTabName) {
		this.subTabName = subTabName;
	}

	public String getMainFkName() {
		return mainFkName;
	}

	public void setMainFkName(String mainFkName) {
		this.mainFkName = mainFkName;
	}

	public String getFkName() {
		return fkName;
	}

	public void setFkName(String fkName) {
		this.fkName = fkName;
	}

}

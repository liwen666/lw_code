package commons.install.po;

import java.io.Serializable;

public class InstallSqlObject implements Serializable {
	String busiName;
	String objName;
	String objType;
	int execOrder;
	String parentObj;
	String sqlText;
	String objComment;

	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public String getObjType() {
		return objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public int getExecOrder() {
		return execOrder;
	}

	public void setExecOrder(int execOrder) {
		this.execOrder = execOrder;
	}

	public String getParentObj() {
		return parentObj;
	}

	public void setParentObj(String parentObj) {
		this.parentObj = parentObj;
	}

	public String getSqlText() {
		return sqlText;
	}

	public void setSqlText(String sqlText) {
		this.sqlText = sqlText;
	}

	public String getObjComment() {
		return objComment;
	}

	public void setObjComment(String objComment) {
		this.objComment = objComment;
	}

}

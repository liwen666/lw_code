package commons.execscript.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class DbTablePO implements  Serializable{
	private static final long serialVersionUID = 6081413094634083074L;
	private String logid;
	private String typeId;
	private String keyId;
	private String typeName;
	private String keyName;
	private String content;
	private Timestamp timeFlag;
	private String userlogid;
	private String resultflag;
	private String batchcode;
	private String remark;
	private String appID;

	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getBatchcode() {
		return batchcode;
	}
	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTimeFlag() {
		return timeFlag;
	}
	public void setTimeFlag(Timestamp timeFlag) {
		this.timeFlag = timeFlag;
	}
	public String getUserlogid() {
		return userlogid;
	}
	public void setUserlogid(String userlogid) {
		this.userlogid = userlogid;
	}
	public String getResultflag() {
		return resultflag;
	}
	public void setResultflag(String resultflag) {
		this.resultflag = resultflag;
	}
}

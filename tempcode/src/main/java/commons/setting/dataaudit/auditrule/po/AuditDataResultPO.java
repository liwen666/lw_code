package commons.setting.dataaudit.auditrule.po;

import java.io.Serializable;

/**
 *@desc:审核结果PO
 *@author： wxn
 *@date:2015-11-19上午11:44:17
 */
public class AuditDataResultPO implements Serializable{
	private static final long serialVersionUID = 1L;
	//guid
	private String  guid;
	//单位ID
	private String agencyID;
	private String taskID;
	private String ckDate;
	private String ckFlag;
	private String  busiCheckID;
	private String  userGuid;
	private String  userUpagencyID;
	private String   userDistrictID;
	private String ckResult;
	//审核错误的ID
	private String ckErrorID;
	public String getCkResult() {
		return ckResult;
	}
	public void setCkResult(String ckResult) {
		this.ckResult = ckResult;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getAgencyID() {
		return agencyID;
	}
	public void setAgencyID(String agencyID) {
		this.agencyID = agencyID;
	}
	public String getTaskID() {
		return taskID;
	}
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
	public String getCkDate() {
		return ckDate;
	}
	public void setCkDate(String ckDate) {
		this.ckDate = ckDate;
	}
	public String getCkFlag() {
		return ckFlag;
	}
	public void setCkFlag(String ckFlag) {
		this.ckFlag = ckFlag;
	}
	public String getBusiCheckID() {
		return busiCheckID;
	}
	public void setBusiCheckID(String busiCheckID) {
		this.busiCheckID = busiCheckID;
	}
	public String getUserGuid() {
		return userGuid;
	}
	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}
	public String getUserUpagencyID() {
		return userUpagencyID;
	}
	public void setUserUpagencyID(String userUpagencyID) {
		this.userUpagencyID = userUpagencyID;
	}
	public String getUserDistrictID() {
		return userDistrictID;
	}
	public void setUserDistrictID(String userDistrictID) {
		this.userDistrictID = userDistrictID;
	}
	public AuditDataResultPO() {
		super();
	}
	
	/**
	 * @return the ckErrorID
	 */
	public String getCkErrorID() {
		return ckErrorID;
	}
	/**
	 * @param ckErrorID the ckErrorID to set
	 */
	public void setCkErrorID(String ckErrorID) {
		this.ckErrorID = ckErrorID;
	}
	public AuditDataResultPO(String guid, String agencyID, String taskID,
			String ckDate, String ckFlag, String busiCheckID, String userGuid,
			String userUpagencyID, String userDistrictID) {
		super();
		this.guid = guid;
		this.agencyID = agencyID;
		this.taskID = taskID;
		this.ckDate = ckDate;
		this.ckFlag = ckFlag;
		this.busiCheckID = busiCheckID;
		this.userGuid = userGuid;
		this.userUpagencyID = userUpagencyID;
		this.userDistrictID = userDistrictID;
	}
	
	
}


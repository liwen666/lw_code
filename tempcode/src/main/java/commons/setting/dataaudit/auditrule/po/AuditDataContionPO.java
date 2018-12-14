package commons.setting.dataaudit.auditrule.po;

import java.io.Serializable;

/**
 *@desc:审核条件
 *@author： wxn
 *@date:2016-8-1下午1:47:01
 */
public class AuditDataContionPO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String   guid;
	 private String   checkResultID;
	 private String   deid;
	 private String   deidVal;
	 private String   conTotalNum;
	 /**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @param guid the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}
	/**
	 * @return the checkResultID
	 */
	public String getCheckResultID() {
		return checkResultID;
	}
	/**
	 * @param checkResultID the checkResultID to set
	 */
	public void setCheckResultID(String checkResultID) {
		this.checkResultID = checkResultID;
	}
	/**
	 * @return the deid
	 */
	public String getDeid() {
		return deid;
	}
	/**
	 * @param deid the deid to set
	 */
	public void setDeid(String deid) {
		this.deid = deid;
	}
	/**
	 * @return the deidVal
	 */
	public String getDeidVal() {
		return deidVal;
	}
	/**
	 * @param deidVal the deidVal to set
	 */
	public void setDeidVal(String deidVal) {
		this.deidVal = deidVal;
	}
	/**
	 * @return the conTotalNum
	 */
	public String getConTotalNum() {
		return conTotalNum;
	}
	/**
	 * @param conTotalNum the conTotalNum to set
	 */
	public void setConTotalNum(String conTotalNum) {
		this.conTotalNum = conTotalNum;
	}
    public static AuditDataContionPO newObj(String checkResultID,String deid,String deidVal,String conTotalNum){
    	AuditDataContionPO po =new AuditDataContionPO();
    	po.setCheckResultID(checkResultID);
    	po.setDeid(deid);
    	po.setDeidVal(deidVal);
    	po.setConTotalNum(conTotalNum);
    	return po;
    }
}


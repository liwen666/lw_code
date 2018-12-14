package commons.secu.po;

import java.io.Serializable;

/**
 * @version 1.0
 * @author zushuxin
 * 全局锁实体类
 */
public class SecuTLockPO implements Serializable{
	

	private static final long serialVersionUID = -5714715843964391862L;
	
	//系统ID
	private String appId;
	//锁类型：1部门锁、2科目锁、3报表锁、4全局锁、5套表分部门、6套表分司局（针对控制数表）
	private String typeId;
	//对象ID，如部门ID或科目ID等
	private String objectId;
	private String objectName;
	private String objectcode;
	private String toId;
	private String lockFlag;
	
	
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	public String getLockFlag() {
		return lockFlag;
	}
	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getObjectcode() {
		return objectcode;
	}
	public void setObjectcode(String objectcode) {
		this.objectcode = objectcode;
	}
	@Override
	public String toString() {
		return "SecuTLockPO [appId=" + appId + ", typeId=" + typeId
				+ ", objectId=" + objectId + ", objectName=" + objectName
				+ ", objectcode=" + objectcode + "]";
	}
	
	
}

package commons.setting.external.po;
/**
 *@desc:TODO
 *@author： wxn
 *@date:2015-11-19上午11:26:40
 */
public class AuditRuleBaseInfoPO {
	/**
	 * appID
	 */
	private String appID;
	//审核规则id
	private String checkID;
	//时间戳
	private String bversion;
	//审核类型
	private String checkType;
	//审核分类
	private String checkSortID;
	//审核名称
	private String defName;
	//左表Id
	private String lmodelID;
	//右表ID
	private String rmodelID;
	private String dbversion;
	private String ldesc;
	private String rdesc;
	public String getLdesc() {
		return ldesc;
	}
	public void setLdesc(String ldesc) {
		this.ldesc = ldesc;
	}
	public String getRdesc() {
		return rdesc;
	}
	public void setRdesc(String rdesc) {
		this.rdesc = rdesc;
	}
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getCheckID() {
		return checkID;
	}
	public void setCheckID(String checkID) {
		this.checkID = checkID;
	}
	public String getBversion() {
		return bversion;
	}
	public void setBversion(String bversion) {
		this.bversion = bversion;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getCheckSortID() {
		return checkSortID;
	}
	public void setCheckSortID(String checkSortID) {
		this.checkSortID = checkSortID;
	}
	public String getDefName() {
		return defName;
	}
	public void setDefName(String defName) {
		this.defName = defName;
	}
	public String getLmodelID() {
		return lmodelID;
	}
	public void setLmodelID(String lmodelID) {
		this.lmodelID = lmodelID;
	}
	public String getRmodelID() {
		return rmodelID;
	}
	public void setRmodelID(String rmodelID) {
		this.rmodelID = rmodelID;
	}
	public String getDbversion() {
		return dbversion;
	}
	public void setDbversion(String dbversion) {
		this.dbversion = dbversion;
	}


}


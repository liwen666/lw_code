package commons.setting.dataaudit.rule.po;

import com.tjhq.commons.inputcomponent.po.TreePO;

/**
 *@desc:TODO
 *@author： wxn
 *@date:2014-10-17下午5:57:15
 */
public class TreeNodePO  extends TreePO{
    /** 处理类型 */
    private String dealType;
    /** 表类型 */
    private String tableType; //1、物理表 2、视图、3、可更新视图
    
    private String appID;
    private String csid;//引用表的id
    
    public String getCsid() {
		return csid;
	}

	public void setCsid(String csid) {
		this.csid = csid;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getAuditTab() {
		return auditTab;
	}

	public void setAuditTab(String auditTab) {
		this.auditTab = auditTab;
	}

	private String auditTab; //1、表--来源于DICT_T_MODEL表 2、视图--来源于CHECK_T_REGVIEW
}


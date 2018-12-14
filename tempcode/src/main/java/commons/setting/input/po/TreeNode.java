package commons.setting.input.po;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 树节点数据
 * 
 * @ClassName TreeNode
 * @Description 树节点数据
 */
public class TreeNode implements Serializable {

    private static final long serialVersionUID = -697743058726107859L;

    /** ID */
    private String id;
    /** 父ID */
    @JsonProperty
    private String pId;
    /** 名称 */
    private String code;
    private String name;
    /** 排序 */
    private int orderId;
    /** 当前节点层级 */
    private int levelNo;
    /** 是否叶子节点 */
    private String isLeaf;
    /** 数据 */
    private Map<String, Object> data;
    /** 孩子节点 */
    private Collection<TreeNode> child;
    /** 处理类型 */
    private String dealType;
    /** 表类型 */
    private String tableType; //1、物理表 2、视图、3、可更新视图
    /**true为选中*/
    private String checked;
    private boolean open;
    
    private String isParent;
    
    private String columnId; //列ID
    private String dbColumnName; //列物理名
    private String columnName; //列中文名

    private String csid; //引用Id
    private String isRef; //是否引用列：0：不是，1：是；
    public String getIsRef() {
		return isRef;
	}
	public void setIsRef(String isRef) {
		this.isRef = isRef;
	}
	private String dealName; //处理类型中文名
    
    private String appID;
    
    private String auditTab; //1、表--来源于DICT_T_MODEL表 2、视图--来源于CHECK_T_REGVIEW
    
    private String dbTableName;
    
	public String getDbTableName() {
		return dbTableName;
	}
	public void setDbTableName(String dbTableName) {
		this.dbTableName = dbTableName;
	}
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JsonIgnore
	public String getPId() {
		return pId;
	}
	@JsonIgnore
	public void setPId(String id) {
		pId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public Collection<TreeNode> getChild() {
		return child;
	}
	public void setChild(Collection<TreeNode> child) {
		this.child = child;
	}
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public String getDbColumnName() {
		return dbColumnName;
	}
	public void setDbColumnName(String dbColumnName) {
		this.dbColumnName = dbColumnName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getCsid() {
		return csid;
	}
	public void setCsid(String csid) {
		this.csid = csid;
	}
	public String getDealName() {
		return dealName;
	}
	public void setDealName(String dealName) {
		this.dealName = dealName;
	}
	public String getAuditTab() {
		return auditTab;
	}
	public void setAuditTab(String auditTab) {
		this.auditTab = auditTab;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}

	// Added by ZK use for save relatree
    private String relaID;
    public String getRelaID() { return relaID; }
    public void setRelaID(String relaID) { this.relaID = relaID; }   
}

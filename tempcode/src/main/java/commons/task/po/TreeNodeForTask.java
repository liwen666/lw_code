package commons.task.po;
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
public class TreeNodeForTask implements Serializable {

    private static final long serialVersionUID = -697743058726107859L;

    /** ID */
    private String id;
    /** 父ID */
    private String pId;
    /** 名称 */
    private String name;
    //DIVTYPE,ISLEAF,LEVELNO
    private String divType;
    private String isLeaf;
    private String levelNo;
    private String code;
    private String open;
    public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getDivType() {
		return divType;
	}
	public void setDivType(String divType) {
		this.divType = divType;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(String levelNo) {
		this.levelNo = levelNo;
	}
	/** 排序 */
//    private int orderId;
//    /** 当前节点层级 */
//    private int levelNo;
//    /** 是否叶子节点 */
//    private String isLeaf;
//    /** 数据 */
//    private Map<String, Object> data;
//    /** 孩子节点 */
//    private Collection<TreeNode> child;
//    /** 处理类型 */
//    private String dealType;
//    
//    private boolean open;
//    
//    private String isParent;

    private String nocheck;
    
	public String getNocheck() {
		return nocheck;
	}
	public void setNocheck(String nocheck) {
		this.nocheck = nocheck;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public int getOrderId() {
//		return orderId;
//	}
//	public void setOrderId(int orderId) {
//		this.orderId = orderId;
//	}
//	public int getLevelNo() {
//		return levelNo;
//	}
//	public void setLevelNo(int levelNo) {
//		this.levelNo = levelNo;
//	}
//	public String getIsLeaf() {
//		return isLeaf;
//	}
//	public void setIsLeaf(String isLeaf) {
//		this.isLeaf = isLeaf;
//	}
//	public Map<String, Object> getData() {
//		return data;
//	}
//	public void setData(Map<String, Object> data) {
//		this.data = data;
//	}
//	public Collection<TreeNode> getChild() {
//		return child;
//	}
//	public void setChild(Collection<TreeNode> child) {
//		this.child = child;
//	}
//	public String getDealType() {
//		return dealType;
//	}
//	public void setDealType(String dealType) {
//		this.dealType = dealType;
//	}
//	public boolean isOpen() {
//		return open;
//	}
//	public void setOpen(boolean open) {
//		this.open = open;
//	}
//	public String getIsParent() {
//		return isParent;
//	}
//	public void setIsParent(String isParent) {
//		this.isParent = isParent;
//	}
   
}

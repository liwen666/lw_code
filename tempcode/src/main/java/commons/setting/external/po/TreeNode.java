package commons.setting.external.po;
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
    
    private boolean open;
    
    private boolean isParent;
    
    
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
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
 
   
}

package commons.secu.po;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * @version 1.0
 * @author zushuxin
 * 用户角色数据权限 （表）实体类
 */
public class SecuTTablePO implements Serializable{
	
	private static final long serialVersionUID = -7471125914550756542L;
	//业务表ID
	private String tableId;
	//业务表名称
	private String tableName;
	//tableId类型，是表还是套表
	private String isSuit;
	//用户或者角色ID
	private String manId;
	//关联类型
	private String manType;
	//表增功能
	private String extAdd;
	//表删功能
	private String extDel;
	//表修改功能
	private String extUpdate;
	//表权限: 3可写、2只读、1不可见
	private String baseSecu;
	//行不可见权限实体类
	private String secuTRow;
	//行只读权限
	private String SecuTRowReadOnly;
	
	public String getSecuTRowReadOnly() {
		return SecuTRowReadOnly; 
	}
	public void setSecuTRowReadOnly(String secuTRowReadOnly) {
		SecuTRowReadOnly = secuTRowReadOnly;
	}
	//列权限实体类集合
	private String suitId; 
	
	public String getSuitId() {
		return suitId;
	}
	public void setSuitId(String suitId) {
		this.suitId = suitId;
	}
	private Map<String,SecuTColPO> secuTCols;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getManId() {
		return manId;
	}
	public void setManId(String manId) {
		this.manId = manId;
	}
	public String getManType() {
		return manType;
	}
	public void setManType(String manType) {
		this.manType = manType;
	}
	public String getExtAdd() {
		return extAdd;
	}
	public void setExtAdd(String extAdd) {
		this.extAdd = extAdd;
	}
	public String getExtDel() {
		return extDel;
	}
	public void setExtDel(String extDel) {
		this.extDel = extDel;
	}
	public String getExtUpdate() {
		return extUpdate;
	}
	public void setExtUpdate(String extUpdate) {
		this.extUpdate = extUpdate;
	}
	
	public String getBaseSecu() {
		return baseSecu;
	}
	public void setBaseSecu(String baseSecu) {
		this.baseSecu = baseSecu;
	}
	

	public String getIsSuit() {
		return isSuit;
	}
	public void setIsSuit(String isSuit) {
		this.isSuit = isSuit;
	}
	public String getSecuTRow() {
		return secuTRow;
	}
	public void setSecuTRow(String secuTRow) {
		this.secuTRow = secuTRow;
	}

	public Map<String, SecuTColPO> getSecuTCols() {
		return secuTCols;
	}
	public void setSecuTCols(Map<String, SecuTColPO> secuTCols) {
		this.secuTCols = secuTCols;
	}
	@Override
	public String toString() {
		return "SecuTTable [tableId=" + tableId + ", manId=" + manId
				+ ", manType=" + manType + ", extAdd=" + extAdd + ", extDel="
				+ extDel + ", extUpdate=" + extUpdate + ", baseSecu="
				+ baseSecu + "]";
	}

}

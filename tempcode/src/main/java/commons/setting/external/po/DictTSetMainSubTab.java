package commons.setting.external.po;

import java.io.Serializable;
import java.util.List;

//采集录入表关系设置
public class DictTSetMainSubTab implements Serializable{
	private static final long serialVersionUID = 3647233284813657927L;
	
	private String guID;
    private String collTypeID;
    private String tableID;
    private int orderID;
    private String isMainTable;
    
    //DICT_T_MODEL
    private String tableName;
    private String dbTableName;

    private String remark;
    //DICT_T_DEALTYPE
    private String dealName;
    private String dealType;
    
    private List<DictTSetMainSubRela> mainSubRela;
    
	public String getCollTypeID() {
		return collTypeID;
	}
	public void setCollTypeID(String collTypeID) {
		this.collTypeID = collTypeID;
	}
	public String getTableID() {
		return tableID;
	}
	public void setTableID(String tableID) {
		this.tableID = tableID;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getIsMainTable() {
		return isMainTable;
	}
	public void setIsMainTable(String isMainTable) {
		this.isMainTable = isMainTable;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDealName() {
		return dealName;
	}
	public void setDealName(String dealName) {
		this.dealName = dealName;
	}
	public List<DictTSetMainSubRela> getMainSubRela() {
		return mainSubRela;
	}
	public void setMainSubRela(List<DictTSetMainSubRela> mainSubRela) {
		this.mainSubRela = mainSubRela;
	}
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public String getDbTableName() {
		return dbTableName;
	}
	public void setDbTableName(String dbTableName) {
		this.dbTableName = dbTableName;
	}
	public String getGuID() {
		return guID;
	}
	public void setGuID(String guID) {
		this.guID = guID;
	}

}

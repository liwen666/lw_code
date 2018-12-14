package commons.secu.po;

import java.io.Serializable;

/**
 * @version 1.0
 * @author zushuxin
 * 用户角色数据权限 （列）实体类
 */
public class SecuTColPO implements Serializable{
	

	private static final long serialVersionUID = -5714715843964391862L;
	
	//业务表ID
	private String tableId;
	//业务表名称
	private String tableName;
	//列ID
	private String colId;
	//列名称
	private String columnName;
	//用户或角色ID
	private String manId;
	//关联类型(用户或者角色)
	private String manType;
	//列权限: 1不可见、2只读、3可写
	private String baseSecu;
	
	private String superid;
	
    private String isleaf;
  
    private Integer levelno;
	
	public String getSuperid() {
		return superid;
	}


	public void setSuperid(String superid) {
		this.superid = superid;
	}


	public String getIsleaf() {
		return isleaf;
	}


	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}


	public Integer getLevelno() {
		return levelno;
	}


	public void setLevelno(Integer levelno) {
		this.levelno = levelno;
	}


	public String getTableName() {
		return tableName;
	}


	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public String getColumnName() {
		return columnName;
	}


	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}


	public String getTableId() {
		return tableId;
	}


	public void setTableId(String tableId) {
		this.tableId = tableId;
	}


	public String getColId() {
		return colId;
	}


	public void setColId(String colId) {
		this.colId = colId;
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


	public String getBaseSecu() {
		return baseSecu;
	}


	public void setBaseSecu(String baseSecu) {
		this.baseSecu = baseSecu;
	}



	@Override
	public String toString() {
		return "RowAuth [tableId=" + tableId + ", colId=" + colId + ", manId="
				+ manId + ", manType=" + manType + ", baseSecu=" + baseSecu
				+ "]";
	}

}

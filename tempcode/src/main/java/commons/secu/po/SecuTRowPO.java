package commons.secu.po;

import java.io.Serializable;

/**
 * @version 1.0
 * @author zushuxin
 * 用户角色数据权限 （行）实体类
 */
public class SecuTRowPO implements Serializable{
	
	private static final long serialVersionUID = -2840049528345552906L;
	//业务表ID
	private String tableId;
	//行权限定义的Where条件
	private String sqlWhere;
	//用户或者角色ID
	private String manId;
	//关联类型
	private String manType;
	//行权限: 1不可见、2只读、3可写
	private String baseSecu;
	//行权限定义的Where条件的中文显示
	private String showWhere;
	

	public String getTableId() {
		return tableId;
	}


	public void setTableId(String tableId) {
		this.tableId = tableId;
	}


	public String getSqlWhere() {
		return sqlWhere;
	}


	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
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


	public String getShowWhere() {
		return showWhere;
	}


	public void setShowWhere(String showWhere) {
		this.showWhere = showWhere;
	}

	@Override
	public String toString() {
		return "SecuTCol [tableId=" + tableId + ", sqlWhere=" + sqlWhere
				+ ", manId=" + manId + ", manType=" + manType + ", baseSecu="
				+ baseSecu + ", showWhere=" + showWhere + "]";
	}


}

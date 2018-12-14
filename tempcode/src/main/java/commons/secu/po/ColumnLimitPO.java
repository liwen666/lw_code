package commons.secu.po;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @version 1.0
 * 限制 （列）实体类
 */
public class ColumnLimitPO implements Serializable{
	

	private static final long serialVersionUID = -5714715843964391862L;
	
	private String appid;//系统ID
	
	private String guid;//表secu_t_columnlimit主键
	
	
	
	private String roleid;//角色ID
	
	private String tableid;//表ID
	
	private String columnid;//限制列ID
	
	private String dbColumnName;//限制列字段名
	
	private String describe;//说明
	
	//条件列信息
	private String detailGuid;//表secu_t_columnlimit_detail主键
	
	private String columnidLimit;//条件列ID
	
	private String condition;//SQL中使用的表达式
	
	private String conditionCn;//回显的表达式
	
	private String operator;//运算符
	
	private ArrayList<ColumnLimitDetailPO> conditionList;//多个条件列
	
	public String getDbColumnName() {
		return dbColumnName;
	}

	public void setDbColumnName(String dbColumnName) {
		this.dbColumnName = dbColumnName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getDetailGuid() {
		return detailGuid;
	}

	public void setDetailGuid(String detailGuid) {
		this.detailGuid = detailGuid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getColumnidLimit() {
		return columnidLimit;
	}

	public void setColumnidLimit(String columnidLimit) {
		this.columnidLimit = columnidLimit;
	}

	public String getConditionCn() {
		return conditionCn;
	}

	public void setConditionCn(String conditionCn) {
		this.conditionCn = conditionCn;
	}

	

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}


	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getTableid() {
		return tableid;
	}

	public void setTableid(String tableid) {
		this.tableid = tableid;
	}

	public String getColumnid() {
		return columnid;
	}

	public void setColumnid(String columnid) {
		this.columnid = columnid;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public ArrayList<ColumnLimitDetailPO> getConditionList() {
		return conditionList;
	}

	public void setConditionList(ArrayList<ColumnLimitDetailPO> conditionList) {
		this.conditionList = conditionList;
	}

}

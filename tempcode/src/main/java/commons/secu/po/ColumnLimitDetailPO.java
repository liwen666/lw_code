package commons.secu.po;

import java.io.Serializable;

/**
 * @version 1.0
 * 限制 （列）实体类
 */
public class ColumnLimitDetailPO implements Serializable{
	

	private static final long serialVersionUID = -5714715843964391862L;
	
	private String guid;//表secu_t_columnlimit_detail主键
	
	private String columnidLimit;//条件列ID
	
	private String dbColumnName;//条件列字段名
	
	private String operator;//运算符
	
	private String condition;//SQL中使用的表达式
	
	private String conditionCn;//回显的表达式

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
	
	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getColumnidLimit() {
		return columnidLimit;
	}

	public void setColumnidLimit(String columnidLimit) {
		this.columnidLimit = columnidLimit;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getConditionCn() {
		return conditionCn;
	}

	public void setConditionCn(String conditionCn) {
		this.conditionCn = conditionCn;
	}
	
	
}

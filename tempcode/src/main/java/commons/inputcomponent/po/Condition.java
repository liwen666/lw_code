package commons.inputcomponent.po;

import java.io.Serializable;

/**
 * 查询条件
 * Author:CAOK
 * 2014-9-4 下午03:46:48 
 * Version 1.0
 */
public class Condition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 查询列ID
	 */
	private String columnID;
	/**
	 * 查询列数据库名称
	 */
	private String columnDbName;
	/**
	 * 查询列数据类型
	 */
	private String dataType;
	/**
	 * 查询操作符
	 */
	private String operator;
	/**
	 * 查询值(具体类型根据实际情况转换)
	 * 多个值以“,”分隔
	 */
	private String queryValue;
	
	/**
	 * 自定义条件表达式
	 */
	private String expression;

	public String getColumnID() {
		return columnID;
	}

	public void setColumnID(String columnID) {
		this.columnID = columnID;
	}

	public String getColumnDbName() {
		return columnDbName;
	}

	public void setColumnDbName(String columnDbName) {
		this.columnDbName = columnDbName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getQueryValue() {
		return queryValue == null ? null : queryValue.trim();
	}

	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}

	public String getExpression() {
		return expression == null ? null : expression.trim();
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	

}

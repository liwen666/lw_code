package commons.inputcomponent.po;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.tjhq.commons.inputcomponent.utils.TableUtil;

public class Grid extends Table implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean canCheck;
	// 0：多选；默认 1：单选
	private boolean checkType;

	// 默认0，不控制。
	private int width;

	// 默认0，不控制。
	private int height;

	// 表头最大层级
	private int maxColumnLevel = 1;

	// 自定义条件
	private String condition;
	private List<Condition> queryPanelParamList;

	private PageInfo pageInfo = new PageInfo();

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	/**
	 * @return condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * @param condition
	 *            要设置的 condition
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	public List<Condition> getQueryPanelParamList() {
		return queryPanelParamList;
	}

	public void setQueryPanelParamList(List<Condition> queryPanelParamList) {
		this.queryPanelParamList = queryPanelParamList;
	}

	public boolean isCanCheck() {
		return canCheck;
	}

	public void setCanCheck(boolean canCheck) {
		this.canCheck = canCheck;
	}

	public boolean isCheckType() {
		return checkType;
	}

	public void setCheckType(boolean checkType) {
		this.checkType = checkType;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getMaxColumnLevel() {
		return maxColumnLevel;
	}

	public void setMaxColumnLevel(int maxColumnLevel) {
		this.maxColumnLevel = maxColumnLevel;
	}

	/**
	 * 获取查询条件SQL
	 * 
	 * @return
	 */
	public String convertConditionSQL() {
		StringBuffer whereBuffer = new StringBuffer(" ");
		if (condition != null && condition.length() > 0) {
			whereBuffer.append(condition).append(" AND ");
		}

		setQueryCondition(whereBuffer);

		if (whereBuffer.length() > 4) {
			whereBuffer = new StringBuffer(whereBuffer.toString().substring(0, whereBuffer.toString().length() - 4));
			return whereBuffer.toString();
		}
		return null;
	}

	private StringBuffer setQueryCondition(StringBuffer whereBuffer) {
		if (queryPanelParamList == null || queryPanelParamList.size() == 0)
			return whereBuffer;
		// 补充条件信息
		setConditionDataType();
		Map<String, Column> columnMapForDbName = TableUtil.getColumnMap("DbName", this);
		for (Condition condition : queryPanelParamList) {
			if (condition.getExpression() != null && condition.getExpression().length() > 0) {
				whereBuffer.append(condition.getExpression()).append(" AND ");
				continue;
			}
			if (condition.getQueryValue() == null || condition.getQueryValue().trim().length() == 0)
				continue;
			// 去掉查询值空格
			condition.setQueryValue(condition.getQueryValue().trim());
			if ("N".equals(condition.getDataType())) {
				if (condition.getOperator().toUpperCase().equals("IN")) {
					whereBuffer.append(condition.getColumnDbName()).append(" IN (").append(condition.getQueryValue())
							.append(") AND ");
				} else {
					whereBuffer.append(condition.getColumnDbName()).append(condition.getOperator())
							.append(condition.getQueryValue()).append(" AND ");
				}
			} else {// 目前Date类型也作为字符处理
				String operator = condition.getOperator().trim().toUpperCase();
				condition.setQueryValue(condition.getQueryValue().replaceAll("'", "''"));
				if (operator.equals("IN") || operator.equals("NOT IN")) {
					String[] values = condition.getQueryValue().split(",");
					String value = "";
					for (String str : values) {
						value += "'" + str + "',";
					}
					whereBuffer.append(condition.getColumnDbName()).append(" ").append(operator).append(" (")
							.append(value.substring(0, value.length() - 1)).append(") AND ");
				} else if (operator.equals("LIKE") || operator.equals("NOT LIKE")) {
					// 如果查询字段为引用
					if (columnMapForDbName.get(condition.getColumnDbName()).getRefTableDBName() != null
							&& columnMapForDbName.get(condition.getColumnDbName()).getRefTableDBName().length() > 0) {
						whereBuffer.append(condition.getColumnDbName()).append(" IN (SELECT GUID FROM ")
								.append(columnMapForDbName.get(condition.getColumnDbName()).getRefTableDBName())
								.append(" WHERE NAME ").append(operator).append(" '%").append(condition.getQueryValue())
								.append("%') AND ");

					} else {
						whereBuffer.append(condition.getColumnDbName()).append(" ").append(operator).append(" '%")
								.append(condition.getQueryValue()).append("%' AND ");
					}

				} else if (operator.contains("INSTR")) {// add by xl
					String[] values = condition.getQueryValue().split(",");
					whereBuffer.append("(");
					for (int i = 0; i < values.length; i++) {
						if (i > 0) {
							whereBuffer.append(operator.equals("INSTR_AND") ? " AND " : " OR ");
						}
						whereBuffer.append(" instr(");
						whereBuffer.append(condition.getColumnDbName());
						whereBuffer.append(",'");
						whereBuffer.append(values[i]);
						whereBuffer.append("')>0");
					}
					whereBuffer.append(") AND ");
				} else if (operator.contains("<>")) {
					whereBuffer.append(" ( ").append(condition.getColumnDbName()).append(condition.getOperator()).append(" '")
							.append(condition.getQueryValue()).append("' OR ").append(condition.getColumnDbName())
							.append(" IS NULL ").append(" ) AND ");
				} else {
					whereBuffer.append(condition.getColumnDbName()).append(condition.getOperator()).append(" '")
							.append(condition.getQueryValue()).append("' AND ");
				}
			}
		}

		return whereBuffer;

	}

	/**
	 * 补充Condition列信息
	 */
	private void setConditionDataType() {
		Map<String, Column> columnMapForID = TableUtil.getColumnMap("ID", this);
		Map<String, Column> columnMapForDbName = TableUtil.getColumnMap("DbName", this);
		for (Condition condition : queryPanelParamList) {
			Column column = null;
			if (condition.getColumnID() != null && condition.getColumnID().trim().length() > 0)
				column = columnMapForID.get(condition.getColumnID());
			if (column == null && condition.getColumnDbName() != null && condition.getColumnDbName().trim().length() > 0)
				column = columnMapForDbName.get(condition.getColumnDbName().toUpperCase());
			if (column != null) {
				condition.setColumnDbName(column.getColumnDBName());
				condition.setDataType(column.getDataType());
			}
		}
	}

}

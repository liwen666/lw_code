package commons.setting.input.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.tjhq.commons.inputcomponent.exception.NullableException;
import com.tjhq.commons.inputcomponent.po.Column;

public class MapField extends Column {

	// 属性名称
	private String fieldName;

	// 属性值
	private Object fieldValue;

	// sql字段
	private String fieldSqlName;

	// sql值，对应字段
	private String fieldSqlValue;

	// 操作符 默认 "="
	private String operator = " = ";

	public MapField() {
	}

	/**
	 * 获得sql字段，字段名或子查询，仅查询时使用。
	 * 
	 * @return
	 */
	public String getFieldSqlName() {
		// 引用表
		if (getRefTableID() != null && getShowType() != null
				&& getRefTableDBName() != null
				&& getShowType().equalsIgnoreCase("4")) {
			fieldSqlName = "" + getFieldName() + ",(select name from "
					+ getRefTableDBName() + " where guid = " + getFieldName()
					+ " ) as sname_" + getFieldName() + "";
			// 附件表
		} else if (getRefTableID() != null && getShowType() != null 
				&& getRefTableDBName() != null
				&& getShowType().equalsIgnoreCase("C")) {
			fieldSqlName = "" + getFieldName()
					+ ",(select attachname from pub_t_attach where attachid = "
					+ getFieldName() + " ) as sname_" + getFieldName() + "";
		} else {
			fieldSqlName = getFieldName();
		}
		return fieldSqlName;
	}

	/**
	 * 类型转换处理，获得Sql中使用的field Value
	 * 
	 * @return
	 */
	public String getFieldSqlValue() {

		// 如果是逻辑主键，并且前台传''空字符串时
		if (this.isKey() && this.getFieldValue() != null
				&& this.getFieldValue().toString().trim().equalsIgnoreCase(""))
			this.setFieldValue(null);

		// 不允许为空或为逻辑主键时，当字段值为空或没有没有默认值抛出异常
		if ((!this.isNullable() || this.isKey())
				&& this.getFieldValue() == null
				&& StringUtils.trimToNull(this.getDefaultValue()) == null)
			throw new NullableException("不可以插入空值，columnId = "
					+ this.getColumnID() + "\tcolumnName = "
					+ this.getColumnName());

		return convertSqlValue(this.getDataType(), fieldValue);
	}

	/**
	 * 根据数据类型获得转义后的sql值
	 * 
	 * @param dataType
	 *            1整形、 2小数、3字符、4标题、6引用、7大文本、8日期
	 * @param fieldValue
	 * @return
	 */
	private String convertSqlValue(String dateType, Object fieldValue) {

		// 字段值允许为空时返回Null或默认值
		if (fieldValue == null
				&& StringUtils.trimToNull(this.getDefaultValue()) == null)
			return "null";
		if (fieldValue == null
				&& StringUtils.trimToNull(this.getDefaultValue()) != null)
			return this.getDefaultValue();

		String fieldValueString = fieldValue.toString();
		Integer dType = NumberUtils.toInt(dateType);
		switch (dType) {
		case 1: { // 整形 默认 0
			if (fieldValueString.equalsIgnoreCase(""))
				fieldSqlValue = "0";
			else
				fieldSqlValue = fieldValueString;
			break;
		}
		case 2: { // 小数 默认 0
			if (fieldValueString.equalsIgnoreCase(""))
				fieldSqlValue = "0";
			else
				fieldSqlValue = fieldValueString;
			break;
		}
		case 3: { // 字符转换 默认 ''
			if (fieldValueString.equalsIgnoreCase(""))
				fieldSqlValue = "''";
			else
				fieldSqlValue = "'" + fieldValueString + "'";
			break;
		}
		case 6: { // 引用类型 默认 null
			if (fieldValueString.equalsIgnoreCase(""))
				fieldSqlValue = "null";
			else
				fieldSqlValue = "'" + fieldValueString + "'";
			break;
		}
		case 7: { // 大文本（clob）默认 null
			if (fieldValueString.equalsIgnoreCase(""))
				fieldSqlValue = "null";
			else
				fieldSqlValue = "'" + fieldValueString + "'";
			break;
		}
		case 8: { // 日期转换 默认 null
			if (fieldValueString.equalsIgnoreCase(""))
				fieldSqlValue = "null";
			else
				fieldSqlValue = "to_date('" + fieldValueString
						+ "','yyyy-mm-dd')";
			break;
		}
		default: {
			fieldSqlValue = fieldValueString;
			break;
		}
		}
		return fieldSqlValue;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

	public void setFieldSqlName(String fieldSqlName) {
		this.fieldSqlName = fieldSqlName;
	}

	public void setFieldSqlValue(String fieldSqlValue) {
		this.fieldSqlValue = fieldSqlValue;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
}

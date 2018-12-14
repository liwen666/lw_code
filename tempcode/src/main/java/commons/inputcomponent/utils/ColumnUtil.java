package commons.inputcomponent.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.inputcomponent.po.Column;

public class ColumnUtil {
	
	private static byte bytes[] = {(byte) 0xC2,(byte) 0xA0};
	/**
	 * 根据列类型转sql
	 * @param column
	 * @param fieldValue
	 * @return
	 */
	public static String getSqlValue(Column column, Object fieldValue) {
		String fieldSqlValue = "";
		// 字段值允许为空时返回Null或默认值
		if (fieldValue == null
				&& StringUtils.trimToNull(column.getDefaultValue()) != null)
			fieldValue = column.getDefaultValue();
		if (fieldValue == null)
			fieldValue = "";
		String fieldValueString = fieldValue.toString();
		if ("N".equals(column.getDataType())) {
			fieldSqlValue = "".equals(fieldValueString) ? "0"
					: fieldValueString;
		} else if ("S".equals(column.getDataType())) {
			fieldValueString = replaceChar(fieldValueString).trim();
			fieldSqlValue = "".equals(fieldValueString) ? "''" : "'"
					+ fieldValueString + "'";
		} else if ("D".equals(column.getDataType())) {
			fieldSqlValue = "".equals(fieldValueString) ? "" : "to_date('"
					+ fieldValueString + "','yyyy-mm-dd')";
		} else {
			fieldValueString = replaceChar(fieldValueString).trim();
			fieldSqlValue = fieldValueString;
		}
		return fieldSqlValue;
	}
	
	public static String replaceChar(String value) {
	    String utfSpace = "";
        try {
            utfSpace = new String(bytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
	    value = value.replaceAll(utfSpace, " ");
	    value = value.replaceAll("'", "‘");
	    value = value.replaceAll("\"", "“");
	    
	    return value;
	}
	
	/**
	 *  根据列属性转sql
	 * @param column
	 * @return
	 */
    public static String getSqlName(Column column) {
        String fieldSqlName = "";
        if (column.getShowType() != null && column.getShowType().equalsIgnoreCase("fileuploadfield")) { // 附件表
            fieldSqlName = column.getColumnDBName() + ",(SELECT ATTACHNAME FROM PUB_T_ATTACH WHERE ATTACHID = "
                    + column.getColumnDBName() + " ) AS SNAME_" + column.getColumnDBName();
            column.setRefTableDBName("CODE_T_ATTACH");
        }
        else if (column.getRefTableDBName() != null) { // 引用表
            if (null != column.getSysExtPro() && column.getSysExtPro().length() > 1
                    && column.getSysExtPro().charAt(1) == '1') {
                fieldSqlName = column.getColumnDBName() + "," + column.getColumnDBName() + " AS SNAME_"
                        + column.getColumnDBName();
            } else {
                String newDbName = " (SELECT GUID, NAME FROM "+ column.getRefTableDBName() +" ) ";
                fieldSqlName = column.getColumnDBName() + ",(SELECT NAME FROM " + newDbName
                        + " WHERE GUID = " + column.getColumnDBName() + " AND ROWNUM < 2 ) AS SNAME_"
                        + column.getColumnDBName();
            }
        }else if(column.getShowType()!=null && column.getRefTableDBName()!=null && column.getShowType().equalsIgnoreCase("multipleCombo")){//add by xl
            fieldSqlName = column.getColumnDBName() + ",(SELECT LISTAGG(NAME,',') WITHIN GROUP(ORDER BY GUID) FROM " + column.getRefTableDBName() + " WHERE " + column.getColumnDBName() + " like '%'||GUID||'%') AS SNAME_" + column.getColumnDBName();
        } else {
            fieldSqlName = column.getColumnDBName();
        }
        return fieldSqlName;
    }
	
	/**
	 * 拼装查询默认值的SQL
	 * @param factorPo
	 * @return
	 * @throws Exception
	 */
	public static String getDefaultValueSql(DictTFactorPO factorPo) {
		StringBuffer defaultValueSql = new StringBuffer();
		return getDefaultValueSql(factorPo, defaultValueSql);

	}
	
	private static String getDefaultValueSql(DictTFactorPO factorPo, StringBuffer defaultValueSql) {
		// 如果没有默认值
		if (StringUtils.isNotEmpty(factorPo.getDefaultvalue())) {
			
			if (defaultValueSql.toString().length() > 0) {
				defaultValueSql.append(" UNION ");
			} 

			defaultValueSql.append("SELECT   '").append(
					factorPo.getDbcolumnname()).append("'  AS COLUMNNAME, ");
			defaultValueSql.append(" ").append(factorPo.getDefaultvalue())
					.append(" || '' AS DEFAULTVALUE ,");

			if (StringUtils.isEmpty(factorPo.getCsid())) {

				defaultValueSql.append(" ").append(factorPo.getDefaultvalue())
						.append(" || ''  AS DEFAULTSHOWVALUE ");

			} else {// 如果是引用
				defaultValueSql.append(" (SELECT NAME FROM ").append(
						factorPo.getCsdbtablename()).append(" WHERE GUID=")
						.append(factorPo.getDefaultvalue()).append(
								" AND ROWNUM < 2 ) AS DEFAULTSHOWVALUE ");
			}

			defaultValueSql.append(" FROM DUAL   ");

		}

		if (factorPo.getDictTFactorList() != null
				&& factorPo.getDictTFactorList().size() > 0) {
			for (DictTFactorPO po : factorPo.getDictTFactorList()) {
				getDefaultValueSql(po, defaultValueSql);
			}
		}

		return defaultValueSql.toString();
	}
}

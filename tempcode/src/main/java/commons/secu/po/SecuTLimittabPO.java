package commons.secu.po;

import java.io.Serializable;

/**
 * @version 1.0
 * @author zushuxin
 * 数据限制（行）实体类
 */
public class SecuTLimittabPO implements Serializable{
	

	private static final long serialVersionUID = -5714715843964391862L;
	
	//业务表ID
	private String tableId;
	//限制ID
	private String limitId;
	//限制条件
	private String limitCon;
	//列权限: 1不可见、2只读、3可写
	private String baseSecu;
	//限制名称
	private String columnName;
	
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

	public String getBaseSecu() {
		return baseSecu;
	}


	public void setBaseSecu(String baseSecu) {
		this.baseSecu = baseSecu;
	}


	public String getLimitId() {
		return limitId;
	}


	public void setLimitId(String limitId) {
		this.limitId = limitId;
	}


	public String getLimitCon() {
		return limitCon;
	}


	public void setLimitCon(String limitCon) {
		this.limitCon = limitCon;
	}


	@Override
	public String toString() {
		return "SecuTLimittab [tableId=" + tableId + ", limitId=" + limitId
				+ ", limitCon=" + limitCon + ", baseSecu=" + baseSecu + "]";
	}
	

}

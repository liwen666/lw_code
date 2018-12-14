package commons.secu.po;

import java.io.Serializable;

/**
 * @version 1.0
 * @author zushuxin
 * 数据限制 （列）实体类
 */
public class SecuTLimitcolPO implements Serializable{
	

	private static final long serialVersionUID = -5714715843964391862L;
	
	//业务表ID
	private String tableId;
	//列ID
	private String columnId;
	//单元格限制条件
	private String limitCon;
	
	private String limitConFunc;
	
	private String showLimit;
	
	public String getLimitConFunc() {
		return limitConFunc;
	}


	public void setLimitConFunc(String limitConFunc) {
		this.limitConFunc = limitConFunc;
	}


	public String getShowLimit() {
		return showLimit;
	}


	public void setShowLimit(String showLimit) {
		this.showLimit = showLimit;
	}


	public String getColumnId() {
		return columnId;
	}


	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}


	public String getLimitCon() {
		return limitCon;
	}


	public void setLimitCon(String limitCon) {
		this.limitCon = limitCon;
	}


	public String getTableId() {
		return tableId;
	}


	public void setTableId(String tableId) {
		this.tableId = tableId;
	}


	@Override
	public String toString() {
		return "SecuTLimitcol [tableId=" + tableId + ", columnId=" + columnId
				+ ", limitCon=" + limitCon + "]";
	}


	

}

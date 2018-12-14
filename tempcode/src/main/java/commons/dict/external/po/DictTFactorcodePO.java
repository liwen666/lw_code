package commons.dict.external.po;

import java.io.Serializable;

public class DictTFactorcodePO implements Serializable {
	private String tableid;

	private String deid;

	private String columnid;

	private String name;

	private Integer orderid;

	private String dbcolumnname;

	private Integer datatype;

	private Integer datalength;

	private Integer scale;

	private String defaultvalue;

	private String csid;

	private String isvisible;

	private String isreserve;

	// private Integer dbversion;

	private String bgtlvl;

	private boolean open;
	private boolean nocheck;

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isNocheck() {
		return nocheck;
	}

	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}

	public String getTableid() {
		return tableid;
	}

	public void setTableid(String tableid) {
		this.tableid = tableid;
	}

	public String getDeid() {
		return deid;
	}

	public void setDeid(String deid) {
		this.deid = deid;
	}

	public String getColumnid() {
		return columnid;
	}

	public void setColumnid(String columnid) {
		this.columnid = columnid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public String getDbcolumnname() {
		return dbcolumnname;
	}

	public void setDbcolumnname(String dbcolumnname) {
		this.dbcolumnname = dbcolumnname;
	}

	public Integer getDatatype() {
		return datatype;
	}

	public void setDatatype(Integer datatype) {
		this.datatype = datatype;
	}

	public Integer getDatalength() {
		return datalength;
	}

	public void setDatalength(Integer datalength) {
		this.datalength = datalength;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	public String getCsid() {
		return csid;
	}

	public void setCsid(String csid) {
		this.csid = csid;
	}

	public String getIsvisible() {
		return isvisible;
	}

	public void setIsvisible(String isvisible) {
		this.isvisible = isvisible;
	}

	public String getIsreserve() {
		return isreserve;
	}

	public void setIsreserve(String isreserve) {
		this.isreserve = isreserve;
	}

	public String getBgtlvl() {
		return bgtlvl;
	}

	public void setBgtlvl(String bgtlvl) {
		this.bgtlvl = bgtlvl;
	}
}

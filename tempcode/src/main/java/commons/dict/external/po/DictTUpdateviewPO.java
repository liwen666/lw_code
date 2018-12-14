package commons.dict.external.po;

import java.io.Serializable;

public class DictTUpdateviewPO implements Serializable {
	private String tableid;

	private String columnid;

	private String tocolumnid;
	private String SNAME_columnid="";

	private String SNAME_tocolumnid="";

	// private Integer version;

	public String getSNAME_columnid() {
		return SNAME_columnid;
	}

	public void setSNAME_columnid(String sNAME_columnid) {
		SNAME_columnid = sNAME_columnid;
	}

	public String getSNAME_tocolumnid() {
		return SNAME_tocolumnid;
	}

	public void setSNAME_tocolumnid(String sNAME_tocolumnid) {
		SNAME_tocolumnid = sNAME_tocolumnid;
	}

	private String bgtlvl;

	private String guid;

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

	public String getTocolumnid() {
		return tocolumnid;
	}

	public void setTocolumnid(String tocolumnid) {
		this.tocolumnid = tocolumnid;
	}

	public String getBgtlvl() {
		return bgtlvl;
	}

	public void setBgtlvl(String bgtlvl) {
		this.bgtlvl = bgtlvl;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

}

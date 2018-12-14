package commons.excel.po;

import java.io.Serializable;

public class ExcelFilePO
  implements Serializable
{
	 private  String appid;
	 private  String  dbtablename;
	 private  String name;
	 private  String status;
	 private  String suitid;
	 private  String tableid;
	 private  String originalxlsxname;
	 private  Object originalxlsxtpl;
	 private  Object nullxlsxtpl;
	 private  Object datapartxlsx;
	 private  Object stylepartxlsx;
	 //编辑时间 20151102 start
	 private String relationjsondata;
	 private String datachangestatus;
	 
	 public String getRelationjsondata() {
		return relationjsondata;
	}
	public void setRelationjsondata(String relationjsondata) {
		this.relationjsondata = relationjsondata;
	}
	 public String getDatachangestatus() {
		return datachangestatus;
	}
	public void setDatachangestatus(String datachangestatus) {
		this.datachangestatus = datachangestatus;
	}
	//编辑时间 20151102 end
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getTabletype() {
		return tabletype;
	}
	public void setTabletype(String tabletype) {
		this.tabletype = tabletype;
	}
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	private  String orderid;
	 private  String tabletype;
	 private  String isshow;
	 private  String remark;
	 public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getDbtablename() {
		return dbtablename;
	}
	public void setDbtablename(String dbtablename) {
		this.dbtablename = dbtablename;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSuitid() {
		return suitid;
	}
	public void setSuitid(String suitid) {
		this.suitid = suitid;
	}
	public String getTableid() {
		return tableid;
	}
	public void setTableid(String tableid) {
		this.tableid = tableid;
	}
	public String getOriginalxlsxname() {
		return originalxlsxname;
	}
	public void setOriginalxlsxname(String originalxlsxname) {
		this.originalxlsxname = originalxlsxname;
	}
	public Object getOriginalxlsxtpl() {
		return originalxlsxtpl;
	}
	public void setOriginalxlsxtpl(Object originalxlsxtpl) {
		this.originalxlsxtpl = originalxlsxtpl;
	}
	public Object getNullxlsxtpl() {
		return nullxlsxtpl;
	}
	public void setNullxlsxtpl(Object nullxlsxtpl) {
		this.nullxlsxtpl = nullxlsxtpl;
	}
	public Object getDatapartxlsx() {
		return datapartxlsx;
	}
	public void setDatapartxlsx(Object datapartxlsx) {
		this.datapartxlsx = datapartxlsx;
	}
	public Object getStylepartxlsx() {
		return stylepartxlsx;
	}
	public void setStylepartxlsx(Object stylepartxlsx) {
		this.stylepartxlsx = stylepartxlsx;
	}

	 
}
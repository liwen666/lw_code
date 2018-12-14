package commons.dict.external.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DictTModelcodePO implements Serializable{
	private String appid;

	private String tableid;

	private String faspcsid;

	private String name;

	private String dbtablename;

	private Integer orderid;

	private String isrepbase;

	private String islvl;

	private String sqlcon;

	private String dynamicwhere;

	private String isorgid;

	private String isfasp;

	private String tabletype;
	
    private  String supperid;

    public List<DictTModelcodePO>  children = new ArrayList<DictTModelcodePO>();
    
    
	public String getIsfasp() {
		return isfasp;
	}

	public void setIsfasp(String isfasp) {
		this.isfasp = isfasp;
	}

	private List<DictTFactorcodePO> dictTFactorcodeList = new ArrayList<DictTFactorcodePO>();
	private boolean open;
	private boolean nocheck;

	public String getAppid() {
		return appid;

	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getTableid() {
		return tableid;
	}

	public void setTableid(String tableid) {
		this.tableid = tableid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDbtablename() {
		return dbtablename;
	}

	public void setDbtablename(String dbtablename) {
		this.dbtablename = dbtablename;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public String getIsrepbase() {
		return isrepbase;
	}

	public void setIsrepbase(String isrepbase) {
		this.isrepbase = isrepbase;
	}

	public String getIslvl() {
		return islvl;
	}

	public void setIslvl(String islvl) {
		this.islvl = islvl;
	}

	public String getSqlcon() {
		return sqlcon;
	}

	public void setSqlcon(String sqlcon) {
		this.sqlcon = sqlcon;
	}

	public String getDynamicwhere() {
		return dynamicwhere;
	}

	public void setDynamicwhere(String dynamicwhere) {
		this.dynamicwhere = dynamicwhere;
	}

	public String getIsorgid() {
		return isorgid;
	}

	public void setIsorgid(String isorgid) {
		this.isorgid = isorgid;
	}

	public List<DictTFactorcodePO> getDictTFactorcodeList() {
		return dictTFactorcodeList;
	}

	public void setDictTFactorcodeList(
			List<DictTFactorcodePO> dictTFactorcodeList) {
		this.dictTFactorcodeList = dictTFactorcodeList;
	}

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

	public String getFaspcsid() {
		return faspcsid;
	}

	public void setFaspcsid(String faspcsid) {
		this.faspcsid = faspcsid;
	}

	public String getTabletype() {
		return tabletype;
	}

	public void setTabletype(String tabletype) {
		this.tabletype = tabletype;
	}

	public List<DictTModelcodePO> getChildren() {
		return children;
	}

	public void setChildren(List<DictTModelcodePO> children) {
		this.children = children;
	}

	public String getSupperid() {
		return supperid;
	}

	public void setSupperid(String supperid) {
		this.supperid = supperid;
	}


}

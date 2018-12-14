package commons.dict.external.po;
/**
 *  表默认列管理
 * @author xujingsi
 *
 */
public class DictTDefaultcolPO  implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 处理类型ID
	 */
    private String dealid;
    /**
	 * 序号
	 */
    private Integer orderid;
    /**
	 * 中文名称
	 */
    private String name;
    /**
	 * 物理列名
	 */
    private String dbcolumnname;
    /**
	 * 数据类型
	 */
    private Integer datatype;
    /**
	 * 长度
	 */
    private Integer datalength;
    /**
	 * 小数位数
	 */
    private Integer scale;
    /**
	 * 引用ID
	 */
    private String csid;
    //数据元
    private String deid;
    /**
	 * 是否物理主键
	 */
    private String isprimary;
    /**
     * 是否逻辑主键
     */
    private String islogickey;
    /**
	 * 是否保留
	 */
    private String isreserve;
    
    private String guid;
    
    private String defaultvalue;
    
    private String SNAME_isreserve;
    
    private String SNAME_islogickey;
    
    private String SNAME_isprimary;
    
    private String SNAME_csid;
    
    private String SNAME_deid;


    public String getDealid() {
        return dealid;
    }

    public void setDealid(String dealid) {
        this.dealid = dealid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCsid() {
        return csid;
    }

    public void setCsid(String csid) {
        this.csid = csid;
    }

    public String getIsprimary() {
        return isprimary;
    }

    public void setIsprimary(String isprimary) {
        this.isprimary = isprimary;
    }

    public String getIsreserve() {
        return isreserve;
    }

    public void setIsreserve(String isreserve) {
        this.isreserve = isreserve;
    }

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	public String getIslogickey() {
		return islogickey;
	}

	public void setIslogickey(String islogickey) {
		this.islogickey = islogickey;
	}

	public String getSNAME_isreserve() {
		return SNAME_isreserve;
	}

	public void setSNAME_isreserve(String sNAME_isreserve) {
		SNAME_isreserve = sNAME_isreserve;
	}

	public String getSNAME_islogickey() {
		return SNAME_islogickey;
	}

	public void setSNAME_islogickey(String sNAME_islogickey) {
		SNAME_islogickey = sNAME_islogickey;
	}

	public String getSNAME_isprimary() {
		return SNAME_isprimary;
	}

	public void setSNAME_isprimary(String sNAME_isprimary) {
		SNAME_isprimary = sNAME_isprimary;
	}

	public String getSNAME_csid() {
		return SNAME_csid;
	}

	public void setSNAME_csid(String sNAME_csid) {
		SNAME_csid = sNAME_csid;
	}

	public String getDeid() {
		return deid;
	}

	public void setDeid(String deid) {
		this.deid = deid;
	}

	public String getSNAME_deid() {
		return SNAME_deid;
	}

	public void setSNAME_deid(String sNAME_deid) {
		SNAME_deid = sNAME_deid;
	}


	// Add isvisible & isupdate at 2016-02-25 by zk begin
    /**
	 * 是否可见
	 */
    private String isvisible;
    public String getIsvisible() { return isvisible; }
    public void setIsvisible(String isvisible) { this.isvisible = isvisible; }
    private String SNAME_isvisible;
    public String getSNAME_isvisible() { return SNAME_isvisible; }
    public void setSNAME_isvisible(String SNAME_isvisible) { this.SNAME_isvisible = SNAME_isvisible; }
    /**
	 * 是否可更新
	 */
    private String isupdate;
    public String getIsupdate() { return isupdate; }
    public void setIsupdate(String isupdate) { this.isupdate = isupdate; }
    private String SNAME_isupdate;
    public String getSNAME_isupdate() { return SNAME_isupdate; }
    public void setSNAME_isupdate(String SNAME_isupdate) { this.SNAME_isupdate = SNAME_isupdate; }
	// Add isvisible & isupdate at 2016-02-25 by zk end
}

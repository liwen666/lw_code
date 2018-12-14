package commons.dict.external.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 套表实体
 * @author xujingsi
 *
 */
public class DictTSuitPO implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String year;

    private String admdivcode;
    /**
     * 应用ID
     */
    private String appid;
    /**
     * 套表ID
     */
    private String suitid;
    /**
     * 套表名称
     */
    private String suitname;
    /**
     * 1表示业务套表、2表示权限套表
     */
    private String suittype;
    /**
     * 上级ID
     */
    private String superid;
    /**
     * 末级标志
     */
    private String isleaf;
    /**
     * 备注
     */
    private String remark;
    /**
     * 级次码（1,2,3,4,5..）
     */
    private Integer levelno;
    
    
    private Integer orderid;
    
    
    private boolean open;
    /**
     * 子集
     */
   
	private List<DictTSuitPO> dictTSuitList = new ArrayList<DictTSuitPO>();
	/**
     * ()表
     */
	private List<DictTModelPO> dictTModelList = new ArrayList<DictTModelPO>();

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAdmdivcode() {
        return admdivcode;
    }

    public void setAdmdivcode(String admdivcode) {
        this.admdivcode = admdivcode;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSuitid() {
        return suitid;
    }

    public void setSuitid(String suitid) {
        this.suitid = suitid;
    }

    public String getSuitname() {
        return suitname;
    }

    public void setSuitname(String suitname) {
        this.suitname = suitname;
    }

    public String getSuittype() {
        return suittype;
    }

    public void setSuittype(String suittype) {
        this.suittype = suittype;
    }

    public String getSuperid() {
        return superid;
    }

    public void setSuperid(String superid) {
        this.superid = superid;
    }

    public String getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(String isleaf) {
        this.isleaf = isleaf;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getLevelno() {
        return levelno;
    }

    public void setLevelno(Integer levelno) {
        this.levelno = levelno;
    }

	public List<DictTSuitPO> getDictTSuitList() {
		return dictTSuitList;
	}

	public void setDictTSuitList(List<DictTSuitPO> dictTSuitList) {
		this.dictTSuitList = dictTSuitList;
	}

	public List<DictTModelPO> getDictTModelList() {
		return dictTModelList;
	}

	public void setDictTModelList(List<DictTModelPO> dictTModelList) {
		this.dictTModelList = dictTModelList;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public boolean isOpen() {
		return true;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
    
    
}

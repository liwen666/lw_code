package commons.dict.external.po;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理类型表
 * @author xujingsi
 *
 */
public class DictTDealtypePO  implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 应用ID
	 */
    private String appid;
    /**
	 * 处理类型ID
	 */
    private String dealid;
    /**
	 * 处理类型名称
	 */
    private String dealname;
    /**
	 * 序号
	 */
    private Integer orderid;
    
    private  String supperid;
    
    private String needconfig;
    
    private Boolean nocheck;
    
    
    public List<DictTDealtypePO>  children = new ArrayList<DictTDealtypePO>();

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getDealid() {
        return dealid;
    }

    public void setDealid(String dealid) {
        this.dealid = dealid;
    }

    public String getDealname() {
        return dealname;
    }

    public void setDealname(String dealname) {
        this.dealname = dealname;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

	public String getSupperid() {
		return supperid;
	}

	public void setSupperid(String supperid) {
		this.supperid = supperid;
	}

	public List<DictTDealtypePO> getChildren() {
		return children;
	}

	public void setChildren(List<DictTDealtypePO> children) {
		this.children = children;
	}

	public Boolean getNocheck() {
		return nocheck;
	}

	public void setNocheck(Boolean nocheck) {
		this.nocheck = nocheck;
	}

	public String getNeedconfig() {
		return needconfig;
	}

	public void setNeedconfig(String needconfig) {
		this.needconfig = needconfig;
	}

	
    
    
}

package commons.inputcomponent.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * ZTree Response 必填项：Id,pid,name.
 */
public class TreePO implements java.io.Serializable{

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String name;
	private String realname;
	@JsonProperty  
	private String pId;
	private String code;
	private String isleaf;
	private String isitself;
	private boolean isParent = true;
	private boolean nocheck = false;
	private boolean open = false;
	private String checked;
	
	
	
    /**
     * @return checked
     */
    public String getChecked() {
        return checked;
    }
    /**
     * @param checked 要设置的 checked
     */
    public void setChecked(String checked) {
        this.checked = checked;
    }
    /**
     * @return open
     */
    public boolean isOpen() {
        return open;
    }
    /**
     * @param open 要设置的 open
     */
    public void setOpen(boolean open) {
        this.open = open;
    }
    /**
     * @return nocheck
     */
    public boolean isNocheck() {
        return nocheck;
    }
    /**
     * @param nocheck 要设置的 nocheck
     */
    public void setNocheck(boolean nocheck) {
            this.nocheck = nocheck;
    }
    /**
     * @return pId
     */
    public String getpId() {
        return pId;
    }
    /**
     * @param pId 要设置的 pId
     */
    public void setpId(String pId) {
        this.pId = pId;
    }
    public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	@JsonIgnore  
	public String getPId() {
		return pId;
	}
	@JsonIgnore  
	public void setPId(String pId) {
		this.pId = pId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}
	public String getIsitself() {
		return isitself;
	}
	public void setIsitself(String isitself) {
		this.isitself = isitself;
	}
}

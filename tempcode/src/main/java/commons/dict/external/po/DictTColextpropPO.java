package commons.dict.external.po;
/**
 * 列扩展属性
 * @author xujingsi
 *
 */
public class DictTColextpropPO  implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 系统ID
	 */
    private String appid;
    /**
     * 扩展ID
     */
    private String extid;
    /**
     * 扩展名称
     */
    private String extname;
    /**
     * 父 id 
     */
    private String superid;
    /**
     * 是否是末端
     */
    private String isleaf;
    /**
     * 备注
     */
    private String remark;
    /**
     * 序号（对应位标志符号）
     */
    private Integer orderid;
    
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getExtid() {
        return extid;
    }

    public void setExtid(String extid) {
        this.extid = extid;
    }

    public String getExtname() {
        return extname;
    }

    public void setExtname(String extname) {
        this.extname = extname;
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

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }
}

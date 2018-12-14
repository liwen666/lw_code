package commons.setting.external.po;

import java.io.Serializable;

public class DictTSetHrefParmPO implements Serializable{
	private static final long serialVersionUID = 3647233284813657927L;
	private String hrefParmID; // 关联 DictTSetAnaly
	private String orderID;
	private String parmName; //参数名称
	private String parmCon; //参数设置
	
	private int version;
	private String bgtlvl;
	
	private DictTSetAnalyPO analy;
	
	public DictTSetAnalyPO getAnaly() {
		return analy;
	}
	public void setAnaly(DictTSetAnalyPO analy) {
		this.analy = analy;
	}
	public String getHrefParmID() {
		return hrefParmID;
	}
	public void setHrefParmID(String hrefParmID) {
		this.hrefParmID = hrefParmID;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getParmName() {
		return parmName;
	}
	public void setParmName(String parmName) {
		this.parmName = parmName;
	}
	public String getParmCon() {
		return parmCon;
	}
	public void setParmCon(String parmCon) {
		this.parmCon = parmCon;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getBgtlvl() {
		return bgtlvl;
	}
	public void setBgtlvl(String bgtlvl) {
		this.bgtlvl = bgtlvl;
	}	
}

package commons.mongodb.po;


public class MongoDBPO {
	private String guID;
	private String appID;
	
	//IP
	private String ipAddress;
	//数据库
	private String dBase;
	
	
	//端口号
	private Integer port;
	private Integer isUse;
	public MongoDBPO() {
		super();
	}
	public String getGuID() {
		return guID;
	}
	public void setGuID(String guID) {
		this.guID = guID;
	}
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getdBase() {
		return dBase;
	}
	public void setdBase(String dBase) {
		this.dBase = dBase;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	
}
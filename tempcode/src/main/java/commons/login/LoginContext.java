package commons.login;

public class LoginContext {
	private String dbName;

	private String clientIP;
	private String tid;
	private boolean hasLockRes;

	private String curLoginUserid;
	private String userName;
	private String agencyID;
	private String finYear;
		
	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}
	
	public String getAgencyID() {
		return agencyID;
	}

	public void setAgencyID(String agencyID) {
		this.agencyID = agencyID;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public boolean isHasLockRes() {
		return hasLockRes;
	}

	public void setHasLockRes(boolean hasLockRes) {
		this.hasLockRes = hasLockRes;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCurLoginUserid() {
		return curLoginUserid;
	}

	public void setCurLoginUserid(String curLoginUserid) {
		this.curLoginUserid = curLoginUserid;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	
	
}
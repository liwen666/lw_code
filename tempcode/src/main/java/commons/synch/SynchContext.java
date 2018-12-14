package commons.synch;

import gov.mof.fasp2.ca.user.dto.UserDTO;

/**
 * 同步上下文
 * 
 * @author bizaiyi May 5, 2015 4:58:36 PM SynchContext.java
 */
public class SynchContext {
	private UserDTO SynchUser;
	private String provinceCode;
	private String year;
	private boolean isReport;//当前是否为上报，false为下发,待删除
	public boolean isReport() {
		return isReport;
	}

	public void setReport(boolean isReport) {
		this.isReport = isReport;
	}

	

	public UserDTO getSynchUser() {
		return SynchUser;
	}

	public void setSynchUser(UserDTO synchUser) {
		SynchUser = synchUser;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
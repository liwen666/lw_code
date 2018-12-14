package commons.install.service.impl;

import gov.mof.fasp2.ca.user.dto.UserDTO;

public class InstallContext {
	private UserDTO InstallUser;
	private String provinceCode;
	private String year;

	public UserDTO getInstallUser() {
		return InstallUser;
	}

	public void setInstallUser(UserDTO installUser) {
		InstallUser = installUser;
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

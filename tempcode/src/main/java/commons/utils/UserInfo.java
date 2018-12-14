package commons.utils;

import java.io.Serializable;


public class UserInfo implements Serializable {
    
    private static final long serialVersionUID = 7591612087873448227L;
    private String code;
    private String name;
    private Integer userType;
    private Integer issys;
    private String agency;
    private String division;
    private String guid;
    private String upAgencyID;
    private String upAgencyCode;
    private boolean isAdmin;
    private String organid;
    private String admdiv;
    private String province;
    private String userSelectProvince;
    private Integer userSelectYear;
    
    
    public UserInfo(String code, String name, Integer userType, Integer issys, String agency, String division, String guid,
            String upAgencyID, String upAgencyCode, boolean isAdmin, String organid, String admdiv, String province, String userSelectProvince, Integer userSelectYear) {
        this.code = code;
        this.name = name;
        this.userType = userType;
        this.issys = issys;
        this.agency = agency;
        this.division = division;
        this.guid = guid;
        this.upAgencyID = upAgencyID;
        this.upAgencyCode = upAgencyCode;
        this.isAdmin = isAdmin;
        this.organid = organid;
        this.admdiv = admdiv;
        this.province = province;
        this.userSelectProvince = userSelectProvince;
        this.userSelectYear = userSelectYear;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the userType
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * @return the issys
     */
    public Integer getIssys() {
        return issys;
    }

    /**
     * @return the agency
     */
    public String getAgency() {
        return agency;
    }

    /**
     * @return the division
     */
    public String getDivision() {
        return division;
    }

    /**
     * @return the guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @return the upAgencyID
     */
    public String getUpAgencyID() {
        return upAgencyID;
    }

    /**
     * @return the upAgencyCode
     */
    public String getUpAgencyCode() {
        return upAgencyCode;
    }

    /**
     * @return the isAdmin
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * @return the organid
     */
    public String getOrganid() {
        return organid;
    }

    /**
     * @return the admdiv
     */
    public String getAdmdiv() {
        return admdiv;
    }

    /**
     * @return the userSelectProvince
     */
    public String getUserSelectProvince() {
        return userSelectProvince;
    }

    /**
     * @return the userSelectYear
     */
    public Integer getUserSelectYear() {
        return userSelectYear;
    }

	public String getProvince() {
		return province;
	}

    
    
}

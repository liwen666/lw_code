package commons.setting.dataaudit.rule.po;

import java.io.Serializable;
import java.util.Map;

/**
 *@desc:审核错误的PO
 *@author： wxn
 *@date:2015-11-19上午11:44:38
 */
public class CheckErrorPO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String   guid;
	 private String   rightValue;
	 private String   leftValue;
	 private String   deviation;
	 private String   checkData;
	 private String   checkResultID;
	 private String   isleafAgency;
	 private String   dbversion;
	 private String   ckResult;
	 private String   ldbTableName;
	 private String   rdbTAbleName;
	 
	/**
	 * @return the ldbTableName
	 */
	public String getLdbTableName() {
		return ldbTableName;
	}
	/**
	 * @param ldbTableName the ldbTableName to set
	 */
	public void setLdbTableName(String ldbTableName) {
		this.ldbTableName = ldbTableName;
	}
	/**
	 * @return the rdbTAbleName
	 */
	public String getRdbTAbleName() {
		return rdbTAbleName;
	}
	/**
	 * @param rdbTAbleName the rdbTAbleName to set
	 */
	public void setRdbTAbleName(String rdbTAbleName) {
		this.rdbTAbleName = rdbTAbleName;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getRightValue() {
		return rightValue;
	}
	public void setRightValue(String rightValue) {
		this.rightValue = rightValue;
	}
	public String getLeftValue() {
		return leftValue;
	}
	public void setLeftValue(String leftValue) {
		this.leftValue = leftValue;
	}
	public String getDeviation() {
		return deviation;
	}
	public void setDeviation(String deviation) {
		this.deviation = deviation;
	}
	public String getCheckData() {
		return checkData;
	}
	public void setCheckData(String checkData) {
		this.checkData = checkData;
	}
	public String getCheckResultID() {
		return checkResultID;
	}
	public void setCheckResultID(String checkResultID) {
		this.checkResultID = checkResultID;
	}
	public String getIsleafAgency() {
		return isleafAgency;
	}
	public void setIsleafAgency(String isleafAgency) {
		this.isleafAgency = isleafAgency;
	}
	public String getDbversion() {
		return dbversion;
	}
	public void setDbversion(String dbversion) {
		this.dbversion = dbversion;
	}
	public String getCkResult() {
		return ckResult;
	}
	public void setCkResult(String ckResult) {
		this.ckResult = ckResult;
	}
	public void copyDataFromRowData(Map<String, String> rowData) {
		this.setLeftValue(rowData.get("LEFTVALUE"));
		this.setRightValue(rowData.get("RIGHTVALUE"));
		this.setDeviation(rowData.get("DEVIATION"));
		this.setCkResult(rowData.get("ERRORINFO"));
		this.setCheckData(rowData.get("CHECKDATA"));
		String dbTableName=rowData.get("TABLEDBNAME");
		if(null!=dbTableName && !"".equals(dbTableName)){
		String[] dbTableNameArray=dbTableName.split(",");
		this.setLdbTableName(dbTableNameArray[0]);
		
		if(dbTableNameArray.length==2){
			
			this.setRdbTAbleName(dbTableNameArray[1]);
		}
		
		}
		

	}

}


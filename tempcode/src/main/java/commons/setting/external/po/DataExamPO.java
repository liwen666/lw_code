package commons.setting.external.po;

import java.util.List;
import java.util.Map;

public class DataExamPO {
	private String agencyId;
	private String reportId;
	private List<DataExamPO> someList;//返回值的集合
	private String checkId;//审核Id
	private String orderId;//序号
	private String checkName;//审核名称
	private String showText;//显示的错误信息
	private String leftVal;//左值
	private String rightVal;//右 值
	private String isLocate;//是否定位
	private String locateColumn;//定位的列 32位码 id
	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	public String getLeftVal() {
		return leftVal;
	}
	public void setLeftVal(String leftVal) {
		this.leftVal = leftVal;
	}
	public String getRightVal() {
		return rightVal;
	}
	public void setRightVal(String rightVal) {
		this.rightVal = rightVal;
	}
	public String getIsLocate() {
		return isLocate;
	}
	public void setIsLocate(String isLocate) {
		this.isLocate = isLocate;
	}
	public String getLocateColumn() {
		return locateColumn;
	}
	public void setLocateColumn(String locateColumn) {
		this.locateColumn = locateColumn;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public List<DataExamPO> getSomeList() {
		return someList;
	}
	public void setSomeList(List<DataExamPO> someList) {
		this.someList = someList;
	}
	public String getCheckName() {
		return checkName;
	}
	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	public String getShowText() {
		return showText;
	}
	public void setShowText(String showText) {
		this.showText = showText;
	}
	

}

package commons.setting.external.po;

import java.util.List;
import java.util.Map;

/**
 *@desc:审核结构po 存放审核规则执行后的反馈结果
 *@author： wxn
 *@date:2014-10-28上午9:19:55
 */
public class AuditResultPO {
 //审核id
 private String checkID;
  //审核分组列的值
 private List checkData;

//左值
 private String leftValue;
 //右值
 private String rightValue;
 //误差
 private String  deviationValue;
//审核错误信息
private String errorMessage;
private String dbname;
public String getDbname() {
	return dbname;
}
public void setDbname(String dbname) {
	this.dbname = dbname;
}
public List getCheckData() {
	return checkData;
}
public void setCheckData(List checkData) {
	this.checkData = checkData;
}
 public String getCheckID() {
	return checkID;
}
public void setCheckID(String checkID) {
	this.checkID = checkID;
}

public String getLeftValue() {
	return leftValue;
}
public void setLeftValue(String leftValue) {
	this.leftValue = leftValue;
}
public String getRightValue() {
	return rightValue;
}
public void setRightValue(String rightValue) {
	this.rightValue = rightValue;
}
public String getDeviationValue() {
	return deviationValue;
}
public void setDeviationValue(String deviationValue) {
	this.deviationValue = deviationValue;
}
public String getErrorMessage() {
	return errorMessage;
}
public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
}
}


package commons.task.po;
/**
 * 日志
 * @author zushuxin
 *
 */
public class TaskLogPO {
 //日志ID
 private String logId;
 //记录日期
 private String logDate;
 //任务ID
 private String taskId;
 //操作员所在行政区划
 private String districtId;
 //操作员ID
 private String operator;
 //任务老状态
 private String oldStatus;
 //任务新状态
 private String newStatus;
 //日志描述
 private String logDesc;
public String getLogId() {
	return logId;
}
public void setLogId(String logId) {
	this.logId = logId;
}
public String getLogDate() {
	return logDate;
}
public void setLogDate(String logDate) {
	this.logDate = logDate;
}
public String getTaskId() {
	return taskId;
}
public void setTaskId(String taskId) {
	this.taskId = taskId;
}
public String getDistrictId() {
	return districtId;
}
public void setDistrictId(String districtId) {
	this.districtId = districtId;
}
public String getOperator() {
	return operator;
}
public void setOperator(String operator) {
	this.operator = operator;
}
public String getOldStatus() {
	return oldStatus;
}
public void setOldStatus(String oldStatus) {
	this.oldStatus = oldStatus;
}
public String getNewStatus() {
	return newStatus;
}
public void setNewStatus(String newStatus) {
	this.newStatus = newStatus;
}
public String getLogDesc() {
	return logDesc;
}
public void setLogDesc(String logDesc) {
	this.logDesc = logDesc;
}
 
 
}

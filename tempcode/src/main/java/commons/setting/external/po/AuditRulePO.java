package commons.setting.external.po;

import java.util.List;
import java.util.Map;

/**
 *@desc:TODO
 *@author： wxn
 *@date:2014-10-28上午9:19:55
 */
public class AuditRulePO {
	//单位Id
private String agencyId;
//审核规则Id
private String checkId;
//过滤条件
private String conditions;
//增量审核
private String deltaData;
//返回数据
private String resultData;
private List auditResultList;
private List<Map<String,Object>> auditResultMap;
public String getAgencyId() {
	return agencyId;
}
public void setAgencyId(String agencyId) {
	this.agencyId = agencyId;
}
public String getCheckId() {
	return checkId;
}
public List<Map<String, Object>> getAuditResultMap() {
	return auditResultMap;
}
public void setAuditResultMap(List<Map<String, Object>> auditResultMap) {
	this.auditResultMap = auditResultMap;
}
public void setCheckId(String checkId) {
	this.checkId = checkId;
}
public String getConditions() {
	return conditions;
}
public void setConditions(String conditions) {
	this.conditions = conditions;
}
public List getAuditResultList() {
	return auditResultList;
}
public void setAuditResultList(List auditResultList) {
	this.auditResultList = auditResultList;
}
public String getDeltaData() {
	return deltaData;
}
public void setDeltaData(String deltaData) {
	this.deltaData = deltaData;
}
public String getResultData() {
	return resultData;
}
public void setResultData(String resultData) {
	this.resultData = resultData;
}
}


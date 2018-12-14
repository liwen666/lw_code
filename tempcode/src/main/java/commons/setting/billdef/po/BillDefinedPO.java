package commons.setting.billdef.po;

import java.io.Serializable;

public class BillDefinedPO implements Serializable {

	/**
	 * 记账定义PO
	 */
	private static final long serialVersionUID = 1L;
	private String appId;
    private String approvedCols; //目标表核定要素
    private String billCol; //目标表记账字段ID
    private String billColName; //目标表记账字段名称
    private String billDefName; //记账定义名称
    private String checkSql; //完整sql
    private String guid;
    private String offsetCol; //冲抵字段ID
    private String offsetColName; //冲抵字段名称
    private String srcDbTabId; //来源表对应物理表tableId
    private String srcDbName; //来源表对应物理表名称
    private String srcTabId; //原始来源表tableId
    private String srcName; //来源表名称
    private String srcWhere; //来源表where条件
    private String tgtName; //目标表名称
    private String tgtTabId; //目标表tableId
    private String procName; //调用存储过程
    private String genSrcTabId; //生成来源表tableId
    private String whereDesc;//where 条件描述
    private String sumCol;//目标表金额字段
    private String sumColName;//目标表金额字段名称
    private String isIncr;//是否增量记账
    private String includeDel;//来源表包含删除数据
    
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getApprovedCols() {
		return approvedCols;
	}
	public void setApprovedCols(String approvedCols) {
		this.approvedCols = approvedCols;
	}
	public String getBillCol() {
		return billCol;
	}
	public void setBillCol(String billCol) {
		this.billCol = billCol;
	}
	public String getBillColName() {
		return billColName;
	}
	public void setBillColName(String billColName) {
		this.billColName = billColName;
	}
	public String getBillDefName() {
		return billDefName;
	}
	public void setBillDefName(String billDefName) {
		this.billDefName = billDefName;
	}
	public String getCheckSql() {
		return checkSql;
	}
	public void setCheckSql(String checkSql) {
		this.checkSql = checkSql;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getOffsetCol() {
		return offsetCol;
	}
	public void setOffsetCol(String offsetCol) {
		this.offsetCol = offsetCol;
	}
	public String getOffsetColName() {
		return offsetColName;
	}
	public void setOffsetColName(String offsetColName) {
		this.offsetColName = offsetColName;
	}
	public String getSrcDbTabId() {
		return srcDbTabId;
	}
	public void setSrcDbTabId(String srcDbTabId) {
		this.srcDbTabId = srcDbTabId;
	}
	public String getSrcDbName() {
		return srcDbName;
	}
	public void setSrcDbName(String srcDbName) {
		this.srcDbName = srcDbName;
	}
	public String getSrcTabId() {
		return srcTabId;
	}
	public void setSrcTabId(String srcTabId) {
		this.srcTabId = srcTabId;
	}
	public String getSrcName() {
		return srcName;
	}
	public void setSrcName(String srcName) {
		this.srcName = srcName;
	}
	public String getSrcWhere() {
		return srcWhere;
	}
	public void setSrcWhere(String srcWhere) {
		this.srcWhere = srcWhere;
	}
	public String getTgtName() {
		return tgtName;
	}
	public void setTgtName(String tgtName) {
		this.tgtName = tgtName;
	}
	public String getTgtTabId() {
		return tgtTabId;
	}
	public void setTgtTabId(String tgtTabId) {
		this.tgtTabId = tgtTabId;
	}
	public String getProcName() {
		return procName;
	}
	public void setProcName(String procName) {
		this.procName = procName;
	}
	public String getGenSrcTabId() {
		return genSrcTabId;
	}
	public void setGenSrcTabId(String genSrcTabId) {
		this.genSrcTabId = genSrcTabId;
	}
	public String getWhereDesc() {
		return whereDesc;
	}
	public void setWhereDesc(String whereDesc) {
		this.whereDesc = whereDesc;
	}
	public String getSumCol() {
		return sumCol;
	}
	public void setSumCol(String sumCol) {
		this.sumCol = sumCol;
	}
	public String getSumColName() {
		return sumColName;
	}
	public void setSumColName(String sumColName) {
		this.sumColName = sumColName;
	}
	public String getIsIncr() {
		return isIncr;
	}
	public void setIsIncr(String isIncr) {
		this.isIncr = isIncr;
	}
	public String getIncludeDel() {
		return includeDel;
	}
	public void setIncludeDel(String includeDel) {
		this.includeDel = includeDel;
	}
    
}

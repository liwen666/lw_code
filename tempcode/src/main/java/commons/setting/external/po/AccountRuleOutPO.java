package commons.setting.external.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountRuleOutPO  implements Serializable{

	/**
	 * 记账定义PO
	 */
	private static final long serialVersionUID = 1L;
	//guid
	private String billDefId;
	//来源表Id
	private String srcTableId;
	//原始来源表对应物理表名称
	private String srcDBTableName;
	//来源表物理名称
	private String srcTableName;
	//来源表where条件
	private String srcWhere;
	//目标表Id
	private String tgtTableId;
	//目标表物理名称
	private String tgtDBTableName;
	//目标表冲抵字段
	private String offsetColName;
	//目标表记账字段
	private String billColName;
	//目标表金额字段
	private String sumColName;
	//是否增量记账
	private String isIncr;
	//来源表业务主键字段名称
	private String busiKey;
	//来源表包含删除数据
	private String includeDel;
	//目标表列公式
	private List<AccountRuleRelaPO> billRelas = new ArrayList<AccountRuleRelaPO>();
	
	public String getBillDefId() {
		return billDefId;
	}
	public void setBillDefId(String billDefId) {
		this.billDefId = billDefId;
	}
	public String getSrcTableId() {
		return srcTableId;
	}
	public void setSrcTableId(String srcTableId) {
		this.srcTableId = srcTableId;
	}
	public String getSrcTableName() {
		return srcTableName;
	}
	public void setSrcTableName(String srcTableName) {
		this.srcTableName = srcTableName;
	}
	public String getSrcDBTableName() {
		return srcDBTableName;
	}
	public void setSrcDBTableName(String srcDBTableName) {
		this.srcDBTableName = srcDBTableName;
	}
	public String getSrcWhere() {
		return srcWhere;
	}
	public void setSrcWhere(String srcWhere) {
		this.srcWhere = srcWhere;
	}
	public String getTgtTableId() {
		return tgtTableId;
	}
	public void setTgtTableId(String tgtTableId) {
		this.tgtTableId = tgtTableId;
	}
	public String getTgtDBTableName() {
		return tgtDBTableName;
	}
	public void setTgtDBTableName(String tgtDBTableName) {
		this.tgtDBTableName = tgtDBTableName;
	}
	public String getOffsetColName() {
		return offsetColName;
	}
	public void setOffsetColName(String offsetColName) {
		this.offsetColName = offsetColName;
	}
	public String getBillColName() {
		return billColName;
	}
	public void setBillColName(String billColName) {
		this.billColName = billColName;
	}
	public String getSumColName() {
		return sumColName;
	}
	public void setSumColName(String sumColName) {
		this.sumColName = sumColName;
	}
	public List<AccountRuleRelaPO> getBillRelas() {
		return billRelas;
	}
	public void setBillRelas(List<AccountRuleRelaPO> billRelas) {
		this.billRelas = billRelas;
	}
	public String getIsIncr() {
		return isIncr;
	}
	public void setIsIncr(String isIncr) {
		this.isIncr = isIncr;
	}
	public String getBusiKey() {
		return busiKey;
	}
	public void setBusiKey(String busiKey) {
		this.busiKey = busiKey;
	}
	public String getIncludeDel() {
		return includeDel;
	}
	public void setIncludeDel(String includeDel) {
		this.includeDel = includeDel;
	}
	

}

package commons.setting.external.po;

import java.io.Serializable;
//查询条件设置
public class DictTSetQuerydDetPO implements Serializable{
	private static final long serialVersionUID = 3647233284813657927L;
	
	private String guID; //主键ID
	private String recID; //主记录ID
	private String ctrlID; //如果是分组控件，则新产生ID;如果是选择表结构的列，则为COLUMNID
    private String ctrlName; //如果是分组控件，则新产生ID;如果是选择表结构的列，默认为空，列名可改。
    private String dbColumnName; //列名
    private int orderID; //序号
	private	int colspan; //所占列数
	private String isShow; //是否显示
	private String isRef; //属性列是否引用列
	private String csid;//引用列tableid
	public String getCsid() {
		return csid;
	}
	public void setCsid(String csid) {
		this.csid = csid;
	}
	public String getIsRef() {
		return isRef;
	}
	public void setIsRef(String isRef) {
		this.isRef = isRef;
	}
	private String operator; //操作符号[这里为引用]
	private String defvalue; //	默认值

	//
	private DictTSetQuerydPO queryd;
	private String SNAME_operator;
	private String  SNAME_ctrlID;
	private String SNAME_defvalue;

    public String getDbColumnName() {
        return dbColumnName;
    }
    public void setDbColumnName(String dbColumnName) {
        this.dbColumnName = dbColumnName;
    }
	public String getSNAME_defvalue() {
		return SNAME_defvalue;
	}
	public void setSNAME_defvalue(String sNAME_defvalue) {
		SNAME_defvalue = sNAME_defvalue;
	}
	public String getGuID() {
		return guID;
	}
	public void setGuID(String guID) {
		this.guID = guID;
	}
	public String getRecID() {
		return recID;
	}
	public void setRecID(String recID) {
		this.recID = recID;
	}
	public String getCtrlID() {
		return ctrlID;
	}
	public void setCtrlID(String ctrlID) {
		this.ctrlID = ctrlID;
	}
	public String getCtrlName() {
		return ctrlName;
	}
	public void setCtrlName(String ctrlName) {
		this.ctrlName = ctrlName;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getColspan() {
		return colspan;
	}
	public void setColspan(int colspan) {
		this.colspan = colspan;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getDefvalue() {
		return defvalue;
	}
	public void setDefvalue(String defvalue) {
		this.defvalue = defvalue;
	}
	public DictTSetQuerydPO getQueryd() {
		return queryd;
	}
	public void setQueryd(DictTSetQuerydPO queryd) {
		this.queryd = queryd;
	}
	public String getSNAME_operator() {
		return SNAME_operator;
	}
	public void setSNAME_operator(String sNAME_operator) {
		SNAME_operator = sNAME_operator;
	}
	public String getSNAME_ctrlID() {
		return SNAME_ctrlID;
	}
	public void setSNAME_ctrlID(String sNAME_ctrlID) {
		SNAME_ctrlID = sNAME_ctrlID;
	}
	
	

}

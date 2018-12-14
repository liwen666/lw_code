package commons.dict.external.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 表 实体
 * 
 * @author xujingsi
 * 
 */
public class DictTModelPO implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String year;

	private String admdivcode;
	/**
	 * 表ID
	 */
	private String tableid;
	/**
	 * 中文名称
	 */
	private String name;
	/**
	 * 序号
	 */
	private Integer orderid;
	/**
	 * 数据库物理名称
	 */
	private String dbtablename;
	/**
	 * 表类型：1物理表、2（不更新）视图表、3（可更新）视图表
	 */
	private String tabletype;
	/**
	 * 是否显示，0 不显示，1 显示
	 */
	private String isshow;
	/**
	 * 应用ID
	 */
	private String appid;
	/**
	 * 版本号
	 */
	// private Integer version;
	/**
	 * 注释
	 */
	private String remark;
	/**
	 * 套表ID
	 */
	private String suitid;
	/**
	 * 套表名字
	 */
	private String suitName;
	/**
	 * 处理类型，参见Dict_t_DealType表
	 */
	private String dealtype;

	private String dealName;
	/**
	 * 是否保留，保留表不能删除
	 */
	private String isreserved;
	/**
	 * 录入级次：1财政、2部门、3单位
	 */
	private String inputlvl;
	/**
	 * 是否增量表
	 */
	private String isadd;
	/**
	 * 表显示内容(如计量单位：万元)
	 */
	private String shorttitle;
	/**
	 * 扩展属性，参见Dict_t_TABEXTPROP
	 */
	private String extprop;
	/**
	 * 1中央、2省、3市、4县
	 */
	private String bgtlvl;
	/**
	 * 过滤条件(可以带参数)
	 */
	private String secusql;
	/**
	 * 初始化SQL
	 */
	private String initsql;
	/**
	 * 初始化SQL执行时间
	 */
	private String initsqltime;
	/**
	 * 是否汇总表标志[中央]
	 */
	private String issumtab;
	/**
	 * 针对多表关联标志
	 */
	private String isman;
	/**
	 * 多表关联时，指定其中一张表为更新表
	 */
	private String mainuptab;
	/**
	 * 多表关联时，多个表的tableCode，以逗号分隔
	 */
	private String relatab;
	/**
	 * 多表关联时，where条件
	 */
	private String tabswhere;

	/**
	 * 是否分区建表
	 */
	private String ispartition;

	/**
	 * 是否 备份
	 */
	private String isbak;
	/**
	 * 是否任务表
	 */
	private String istask;

	/**
	 * 列
	 */
	private String columnid;

	/**
	 * 原始excel模版文件
	 */
	private String originalxlsxname;

	/**
	 * 是否全地区访问
	 */
	private String isalldistrict;
	
	/**
	 * 是否全年度访问
	 */
	private String isallyear;
	
	/**
	 * 初始化SQL按钮名称
	 */
	private String initsqlbtnname;

	/**
	 * 是否支持excel导入
	 */
	private String isexcelimp;
	
	/**
	 * 是否只读
	 */
	
	private String isReadOnly = "0";
	/**
	 * 保存是否刷新公式
	 */
	private String isSaveFormula = "0";
	
	
	
	// 表说明文件
	private String descfile;
	public String getDescfile() {
		return descfile;
	}
	public void setDescfile(String descfile) {
		this.descfile = descfile;
	}
	private String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	// 表说明文件

	private List<DictTFactorPO> dictTFactorList = new ArrayList<DictTFactorPO>();

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getAdmdivcode() {
		return admdivcode;
	}

	public void setAdmdivcode(String admdivcode) {
		this.admdivcode = admdivcode;
	}

	public String getTableid() {
		return tableid;
	}

	public void setTableid(String tableid) {
		this.tableid = tableid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public String getDbtablename() {
		return dbtablename;
	}

	public void setDbtablename(String dbtablename) {
		this.dbtablename = dbtablename;
	}

	public String getTabletype() {
		return tabletype;
	}

	public void setTabletype(String tabletype) {
		this.tabletype = tabletype;
	}

	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSuitid() {
		return suitid;
	}

	public void setSuitid(String suitid) {
		this.suitid = suitid;
	}

	public String getDealtype() {
		return dealtype;
	}

	public void setDealtype(String dealtype) {
		this.dealtype = dealtype;
	}

	public String getIsreserved() {
		return isreserved;
	}

	public void setIsreserved(String isreserved) {
		this.isreserved = isreserved;
	}

	public String getInputlvl() {
		return inputlvl;
	}

	public void setInputlvl(String inputlvl) {
		this.inputlvl = inputlvl;
	}

	public String getIsadd() {
		return isadd;
	}

	public void setIsadd(String isadd) {
		this.isadd = isadd;
	}

	public String getShorttitle() {
		return shorttitle;
	}

	public void setShorttitle(String shorttitle) {
		this.shorttitle = shorttitle;
	}

	public String getExtprop() {
		return extprop;
	}

	public void setExtprop(String extprop) {
		this.extprop = extprop;
	}

	public String getBgtlvl() {
		return bgtlvl;
	}

	public void setBgtlvl(String bgtlvl) {
		this.bgtlvl = bgtlvl;
	}

	public String getSecusql() {
		return secusql;
	}

	public void setSecusql(String secusql) {
		this.secusql = secusql;
	}

	public String getIssumtab() {
		return issumtab;
	}

	public void setIssumtab(String issumtab) {
		this.issumtab = issumtab;
	}

	public String getIsman() {
		return isman;
	}

	public void setIsman(String isman) {
		this.isman = isman;
	}

	public String getMainuptab() {
		return mainuptab;
	}

	public void setMainuptab(String mainuptab) {
		this.mainuptab = mainuptab;
	}

	public String getRelatab() {
		return relatab;
	}

	public void setRelatab(String relatab) {
		this.relatab = relatab;
	}

	public String getTabswhere() {
		return tabswhere;
	}

	public void setTabswhere(String tabswhere) {
		this.tabswhere = tabswhere;
	}

	public List<DictTFactorPO> getDictTFactorList() {
		return dictTFactorList;
	}

	public void setDictTFactorList(List<DictTFactorPO> dictTFactorList) {
		this.dictTFactorList = dictTFactorList;
	}

	public String getSuitName() {
		return suitName;
	}

	public void setSuitName(String suitName) {
		this.suitName = suitName;
	}

	public String getIspartition() {
		return ispartition;
	}

	public void setIspartition(String ispartition) {
		this.ispartition = ispartition;
	}

	public String getDealName() {
		return dealName;
	}

	public void setDealName(String dealName) {
		this.dealName = dealName;
	}

	public String getColumnid() {
		return columnid;
	}

	public void setColumnid(String columnid) {
		this.columnid = columnid;
	}

	public String getIsbak() {
		return isbak;
	}

	public void setIsbak(String isbak) {
		this.isbak = isbak;
	}

	public String getInitsql() {
		return initsql;
	}

	public void setInitsql(String initsql) {
		this.initsql = initsql;
	}

	public String getInitsqltime() {
		return initsqltime;
	}

	public void setInitsqltime(String initsqltime) {
		this.initsqltime = initsqltime;
	}

	public String getIstask() {
		return istask;
	}

	public void setIstask(String istask) {
		this.istask = istask;
	}

	public String getOriginalxlsxname() {
		return originalxlsxname;
	}

	public void setOriginalxlsxname(String originalxlsxname) {
		this.originalxlsxname = originalxlsxname;
	}

	public String getIsalldistrict() {
		return isalldistrict;
	}

	public void setIsalldistrict(String isalldistrict) {
		this.isalldistrict = isalldistrict;
	}

	public String getIsallyear() {
		return isallyear;
	}

	public void setIsallyear(String isallyear) {
		this.isallyear = isallyear;
	}

	public String getInitsqlbtnname() {
		return initsqlbtnname;
	}
	public void setInitsqlbtnname(String initsqlbtnname) {
		this.initsqlbtnname = initsqlbtnname;
	}
	
	
	
	public String getIsexcelimp() {
		return isexcelimp;
	}
	public void setIsexcelimp(String isexcelimp) {
		this.isexcelimp = isexcelimp;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
    /**
     * @return isReadOnly
     */
    public String getIsReadOnly() {
        return isReadOnly;
    }
    /**
     * @param isReadOnly 要设置的 isReadOnly
     */
    public void setIsReadOnly(String isReadOnly) {
        this.isReadOnly = isReadOnly;
    }
    
	public String getIsSaveFormula() {
		return isSaveFormula;
	}
	
	public void setIsSaveFormula(String isSaveFormula) {
		this.isSaveFormula = isSaveFormula;
	}

}

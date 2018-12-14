package commons.setting.dataaudit.rule.po;

import java.io.Serializable;

/**
 *@desc:列PO 现在用于导入导出
 *@author： wxn
 *@date:2016-2-29下午3:32:12
 */
public class ColPO implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1569508982985259158L;
	private String colDbName;
	private String name;
	private int datatype;//1 int 2 浮点数 3 引用,4 字符 5 clob
	private String csTabName;
	private boolean needCDATA;
	private boolean isVirtual=false;//是否为虚列
	/**
	 * @return the isVirtual
	 */
	public boolean isVirtual() {
		return isVirtual;
	}
	/**
	 * @param isVirtual the isVirtual to set
	 */
	public void setVirtual(boolean isVirtual) {
		this.isVirtual = isVirtual;
	}
	/**
	 * @return the needCDATA
	 */
	public boolean isNeedCDATA() {
		return needCDATA;
	}
	/**
	 * @param needCDATA the needCDATA to set
	 */
	public void setNeedCDATA(boolean needCDATA) {
		this.needCDATA = needCDATA;
	}
	public ColPO(String name, String colDbName, int datatype,boolean needCDATA) {
		this.setName(name);
		this.setColDbName(colDbName);
		this.setDatatype(datatype);
		this.setNeedCDATA(needCDATA);
		
	}
	public ColPO(String name, String colDbName, int datatype,boolean needCDATA,boolean isVirtual) {
		this.setName(name);
		this.setColDbName(colDbName);
		this.setDatatype(datatype);
		this.setNeedCDATA(needCDATA);
		this.setVirtual(isVirtual);
		
	}
	public ColPO(String name, String colDbName, int datatype,String csTabName,boolean needCDATA) {
		this.setName(name);
		this.setColDbName(colDbName);
		this.setDatatype(datatype);
		this.setCsTabName(csTabName);
		this.setNeedCDATA(needCDATA);
	}
	public ColPO(String name, String colDbName, int datatype,String csTabName,boolean needCDATA,boolean isVirtual) {
		this.setName(name);
		this.setColDbName(colDbName);
		this.setDatatype(datatype);
		this.setCsTabName(csTabName);
		this.setNeedCDATA(needCDATA);
		this.setVirtual(isVirtual);
	}

	/**
	 * @return the colDbName
	 */
	public String getColDbName() {
		return colDbName;
	}

	/**
	 * @param colDbName the colDbName to set
	 */
	public void setColDbName(String colDbName) {
		this.colDbName = colDbName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the datatype
	 */
	public int getDatatype() {
		return datatype;
	}
	/**
	 * @param datatype the datatype to set
	 */
	public void setDatatype(int datatype) {
		this.datatype = datatype;
	}
	/**
	 * @return the csTabName
	 */
	public String getCsTabName() {
		return csTabName;
	}
	/**
	 * @param csTabName the csTabName to set
	 */
	public void setCsTabName(String csTabName) {
		this.csTabName = csTabName;
	}

}


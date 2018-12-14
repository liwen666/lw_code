package commons.setting.input.po;

import java.io.Serializable;

/*
---固定行列表默认列
FDCode：  varchar2(100) 浮动表的显示列（规定四位一级）
LEVELNO:  Number(1)    级次码（1,2,3,4,5..） 
ISLEAF:   char(1)      末级编码
IsQZX:    char(1)        是否其中项
IsDJ:     char(1)        是否倒挤行
OrigCode: varchar2(32)   原始编码，如从代码表选择而来，则此列为代码表原始32位唯一编码
------
FDCode_1：  varchar2(100) 浮动表的显示列（规定四位一级）
LEVELNO_1:  Number(1)    级次码（1,2,3,4,5..） 
ISLEAF_1:   char(1)      末级编码
IsQZX_1:    char(1)        是否其中项
IsDJ_1:     char(1)        是否倒挤行
OrigCode_1: varchar2(32)   原始编码，如从代码表选择而来，则此列为代码表原始32位唯一编码
------
FDCode_2：  varchar2(100) 浮动表的显示列（规定四位一级）
LEVELNO_2:  Number(1)    级次码（1,2,3,4,5..） 
ISLEAF_2:   char(1)      末级编码
IsQZX_2:    char(1)        是否其中项
IsDJ_2:     char(1)        是否倒挤行
OrigCode_2: varchar2(32)   原始编码，如从代码表选择而来，则此列为代码表原始32位唯一编码
------
--IsInsert: char(1)      是否可以插入下级
OrderID:  Number(9)    序号
CellSecu: varchar2(1000) 单元是否可录，如：010111100000...
*/
public class DictTSetFixPO implements Serializable{
	private static final long serialVersionUID = 3647233284813657927L;
	
	private String guID;
	private String tableID;
	private String typeID; //类型，1表示fdcode_1,2表示fdcode_2,3表示fdcode_3
	private String fdCodeToCols; //当前fdcode对应的columnID，以逗号分隔
	
	private String lvlNanmeCol; //层次名称列
	private String layerIndent; //层次缩进位数，如:2,3,2
	private String refID; //引用代码表
	private String refWhere; //引用代码表的过滤条件
	
	private String lvlNanmeColName;
	
	private String fdCodeToColsName;

	private String otherToCols;
	private String colOrder;

	public String getTableID() {
		return tableID;
	}
	public void setTableID(String tableID) {
		this.tableID = tableID;
	}
	public String getTypeID() {
		return typeID;
	}
	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}
	public String getFdCodeToCols() {
		return fdCodeToCols;
	}
	public void setFdCodeToCols(String fdCodeToCols) {
		this.fdCodeToCols = fdCodeToCols;
	}
	public String getLvlNanmeCol() {
		return lvlNanmeCol;
	}
	public void setLvlNanmeCol(String lvlNanmeCol) {
		this.lvlNanmeCol = lvlNanmeCol;
	}
	public String getLayerIndent() {
		return layerIndent;
	}
	public void setLayerIndent(String layerIndent) {
		this.layerIndent = layerIndent;
	}
	public String getRefID() {
		return refID;
	}
	public void setRefID(String refID) {
		this.refID = refID;
	}
	public String getRefWhere() {
		return refWhere;
	}
	public void setRefWhere(String refWhere) {
		this.refWhere = refWhere;
	}
	public String getGuID() {
		return guID;
	}
	public void setGuID(String guID) {
		this.guID = guID;
	}
	public String getLvlNanmeColName() {
		return lvlNanmeColName;
	}
	public void setLvlNanmeColName(String lvlNanmeColName) {
		this.lvlNanmeColName = lvlNanmeColName;
	}
	public String getFdCodeToColsName() {
		return fdCodeToColsName;
	}
	public void setFdCodeToColsName(String fdCodeToColsName) {
		this.fdCodeToColsName = fdCodeToColsName;
	}
	public String getOtherToCols() {
		return otherToCols;
	}
	public void setOtherToCols(String otherToCols) {
		this.otherToCols = otherToCols;
	}
	public String getColOrder() {
		return colOrder;
	}
	public void setColOrder(String colOrder) {
		this.colOrder = colOrder;
	}
}

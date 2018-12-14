package commons.setting.external.po;

import java.io.Serializable;

/*
---浮动表默认列
FDCode：  varchar2(100) 浮动表的显示列（规定四位一级）
LEVELNO:  Number(1)    级次码（1,2,3,4,5..） 
ISLEAF:   char(1)      末级编码
OrderID:  Number(9)    序号
IsInsert: char(1)      是否可以插入下级
CellSecu: varchar2(1000) 单元是否可录，如：010111100000...
IsQZX:    char(1)        是否其中项
IsDJ:     char(1)        是否倒挤行
OrigCode: varchar2(32)   原始编码，如从代码表选择而来，则此列为代码表原始32位唯一编码
*/
public class DictTSetFddefPO implements Serializable{
	private static final long serialVersionUID = 3647233284813657927L;

	private String tableID;
	private String isFix; //是否固定浮动表
	private String lvlNanmeCol; //层次名称列
	private String layerIndent; //层次缩进位数，如:2,3,2
	private String refID; //引用代码表
	private String refWhere; //引用代码表的过滤条件
	private String colOrder; //单元格 排序列

	public String getTableID() {
		return tableID;
	}
	public void setTableID(String tableID) {
		this.tableID = tableID;
	}
	public String getIsFix() {
		return isFix;
	}
	public void setIsFix(String isFix) {
		this.isFix = isFix;
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
	public String getColOrder() {
		return colOrder;
	}
	public void setColOrder(String colOrder) {
		this.colOrder = colOrder;
	}
}

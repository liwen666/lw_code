package commons.inputcomponent.grid.fixgrid.po;

import com.tjhq.commons.inputcomponent.po.Column;

public class FixColumn extends Column {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//控制列
	private int isCtrl; 
	
	private String ownerColumn; 
	
	private int  isDj;
	
	private int  isQzx;
	
	private String  ownerType="1";

	public int getIsCtrl() {
		return isCtrl;
	}

	public void setIsCtrl(int isCtrl) {
		this.isCtrl = isCtrl;
	}

	public String getOwnerColumn() {
		return ownerColumn;
	}

	public void setOwnerColumn(String ownerColumn) {
		this.ownerColumn = ownerColumn;
	}

	public int getIsDj() {
		return isDj;
	}

	public void setIsDj(int isDj) {
		this.isDj = isDj;
	}

	public int getIsQzx() {
		return isQzx;
	}

	public void setIsQzx(int isQzx) {
		this.isQzx = isQzx;
	}

	public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

}

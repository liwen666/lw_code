package commons.inputcomponent.po;

import java.io.Serializable;

public class FormItem extends Column implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rowSpan;
	private int colSpan;	
	//是否大文本
	private boolean isTextArea = false;
	public int getRowSpan() {
		return rowSpan;
	}
	public void setRowSpan(int rowSpan) {
		this.rowSpan = rowSpan;
	}
	public int getColSpan() {
		return colSpan;
	}
	public void setColSpan(int colSpan) {
		this.colSpan = colSpan;
	}
	public boolean isTextArea() {
		return isTextArea;
	}
	public void setTextArea(boolean isTextArea) {
		this.isTextArea = isTextArea;
	}
	

}

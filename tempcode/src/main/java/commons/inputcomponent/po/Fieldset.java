package commons.inputcomponent.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Fieldset implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private boolean visiable = true;

	private int rowSpan;
	
	private int colSpan;
	
	private int leftcols;
	
	private int toprows;

	private int topgroups;
	
	private String border;//公组框有无边框和标题；0：无，1：有。
	
	private int orderId;
	
	private String showType = "fieldset";

	private List<FormItem> formItemList = new ArrayList<FormItem>();

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public int getTopgroups() {
		return topgroups;
	}

	public void setTopgroups(int topgroups) {
		this.topgroups = topgroups;
	}

	public int getLeftcols() {
		return leftcols;
	}

	public void setLeftcols(int leftcols) {
		this.leftcols = leftcols;
	}

	public int getToprows() {
		return toprows;
	}

	public void setToprows(int toprows) {
		this.toprows = toprows;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVisiable() {
		return visiable;
	}

	public void setVisiable(boolean visiable) {
		this.visiable = visiable;
	}

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

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public List<FormItem> getFormItemList() {
		return formItemList;
	}

	public void setFormItemList(List<FormItem> formItemList) {
		this.formItemList = formItemList;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	
	
}

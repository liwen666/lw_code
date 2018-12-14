package commons.inputcomponent.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tjhq.commons.inputcomponent.constants.XType;


public class Form extends Table implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Form() {
		super();
		super.setxType(XType.FORM);
	}

	private int columnSize = 1;
	
	private int labelWidth;
	
	//其中包括 Fieldset 和  FormItem
	private List<Object> formItemList = new ArrayList<Object>();
	
	// 默认0，不控制。
	private int width;

	// 默认0，不控制。
	private int height;
	
	//自定义条件
	private String condition;
	private int absolutePosition; //页面是否为绝对定位
	private String lableTextAlign;//lable左右对齐

	@JsonIgnore
	@Override
	public List<Column> getColumnList() {
		return super.getColumnList();
	}

	public int getAbsolutePosition() {
		return absolutePosition;
	}

	public void setAbsolutePosition(int absolutePosition) {
		this.absolutePosition = absolutePosition;
	}

	public String getLableTextAlign() {
		return lableTextAlign;
	}

	public void setLableTextAlign(String lableTextAlign) {
		this.lableTextAlign = lableTextAlign;
	}

	public int getLabelWidth() {
		return labelWidth;
	}

	public void setLabelWidth(int labelWidth) {
		this.labelWidth = labelWidth;
	}

	public List<Object> getFormItemList() {
		return formItemList;
	}

	public void setFormItemList(List<Object> formItemList) {
		this.formItemList = formItemList;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}


	
}

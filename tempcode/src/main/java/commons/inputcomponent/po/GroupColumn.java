package commons.inputcomponent.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GroupColumn extends Column implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//是否生成展开、折叠动作，默认为false数据全部显示，如果设置为true，则需要指定数据折叠状态字段、初始显示层次等属性
	private boolean isFolded = false;
	
	//大于0的整数，默认值为0.当isFolded为true时，该属性生效并用于控制表格初始状态时显示到哪一级数据
	private int initlevel = 0;
	
	public List<Column> getGroupColumn() {
		return groupColumn;
	}

	public void setGroupColumn(List<Column> groupColumn) {
		this.groupColumn = groupColumn;
	}

	//合并列
	private List<Column> groupColumn = new ArrayList<Column>();

	
	public int getInitlevel() {
		return initlevel;
	}

	public void setInitlevel(int initlevel) {
		this.initlevel = initlevel;
	}

	public boolean isFolded() {
		return isFolded;
	}

	public void setFolded(boolean isFolded) {
		this.isFolded = isFolded;
	}
}

package commons.inputcomponent.grid.propertygrid.po;

import com.tjhq.commons.inputcomponent.constants.XType;
import com.tjhq.commons.inputcomponent.po.Grid;

public class PropertyGrid extends Grid{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PropertyGrid() {
		super();
		super.setxType(XType.BASENUM_GRID);
	}
	
	//基本数值表列名字段
	private String bNColumnField;

	public String getbNColumnField() {
		return bNColumnField;
	}

	public void setbNColumnField(String bNColumnField) {
		this.bNColumnField = bNColumnField;
	}
	

}

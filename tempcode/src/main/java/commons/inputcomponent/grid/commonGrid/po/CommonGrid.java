package commons.inputcomponent.grid.commonGrid.po;

import java.io.Serializable;
import java.util.List;

import com.tjhq.commons.inputcomponent.constants.XType;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.setting.external.po.DictTSetGroupPO;
import com.tjhq.commons.setting.external.po.DictTSetSortPO;

public class CommonGrid extends Grid implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CommonGrid() {
		super();
		super.setxType(XType.COMMMON_GRID);
	}
	
	
	private List<DictTSetGroupPO> groupColumnsList;
	
	private List<DictTSetSortPO> sortColumnsList;

	public List<DictTSetGroupPO> getGroupColumnsList() {
		return groupColumnsList;
	}

	public void setGroupColumnsList(List<DictTSetGroupPO> groupColumnsList) {
		this.groupColumnsList = groupColumnsList;
	}

	public List<DictTSetSortPO> getSortColumnsList() {
		return sortColumnsList;
	}

	public void setSortColumnsList(List<DictTSetSortPO> sortColumnsList) {
		this.sortColumnsList = sortColumnsList;
	}
	
	
}

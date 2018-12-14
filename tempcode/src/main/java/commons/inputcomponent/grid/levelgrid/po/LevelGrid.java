package commons.inputcomponent.grid.levelgrid.po;

import java.io.Serializable;

import com.tjhq.commons.inputcomponent.constants.XType;
import com.tjhq.commons.inputcomponent.po.Grid;

public class LevelGrid extends Grid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5810006750382858247L;
	
	public LevelGrid() {
		super.setxType(XType.LEVEL_GRID);
	}

}

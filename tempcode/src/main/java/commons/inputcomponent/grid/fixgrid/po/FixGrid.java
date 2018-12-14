package commons.inputcomponent.grid.fixgrid.po;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tjhq.commons.inputcomponent.constants.XType;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;

public class FixGrid extends CommonGrid implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6447368215491906380L;
	
	public FixGrid() {
		super();
		super.setxType(XType.FIX_GRID); 
	}
	
	//固定行列表分组列
	private String levelNameCol;
	
	//单元格公式
	private Map<String, List<List<String>>> cellFormulaMap = new HashMap<String, List<List<String>>>();

	public String getLevelNameCol() {
		return levelNameCol; 
	}

	public void setLevelNameCol(String levelNameCol) {
		this.levelNameCol = levelNameCol;
	}

	public Map<String, List<List<String>>> getCellFormulaMap() {
		return cellFormulaMap;
	}

	public void setCellFormulaMap(Map<String, List<List<String>>> cellFormulaMap) {
		this.cellFormulaMap = cellFormulaMap;
	}
}

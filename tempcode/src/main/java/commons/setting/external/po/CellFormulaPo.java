package commons.setting.external.po;

import java.util.HashMap;
import java.util.Map;

public class CellFormulaPo {

	public Map<String, Object> resultCell;
	
	public Map<String, Object> row;
	
	public Map<String, Object> col;

	//公式单元格
	public Map<String, Object> getResultCell() {
		Map<String, Object> resultCell = new HashMap<String, Object>();
		resultCell.put("row", this.getRow());
		resultCell.put("col", this.getCol());

		return resultCell;
	}
	
	public Map<String, Object> getRow() {
		return row;
	}
	//定位 行对象
	public void setRow(String key ,String value) {
		Map<String, Object> row = new HashMap<String, Object>();
		row.put(key, value);	
		this.row = row;
	}

	public Map<String, Object> getCol() {
		return col;
	}
	//定位 列对象
	public void setCol(String key,String value) {
		Map<String, Object> col = new HashMap<String, Object>();
		col.put(key, value);
		this.col = col;
	}

	public void setResultCell(Map<String, Object> resultCell) {
		this.resultCell = resultCell;
	}


	
}
	
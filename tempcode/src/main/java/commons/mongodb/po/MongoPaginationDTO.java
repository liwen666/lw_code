package commons.mongodb.po;

import java.util.List;


public class MongoPaginationDTO {

	private int currPage;
	private int rows;
	private List<MongoLogDTO> result;
	private int totalRows;
	public MongoPaginationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MongoPaginationDTO(int currPage, int rows, List<MongoLogDTO> result,
			int totalRows) {
		super();
		this.currPage = currPage;
		this.rows = rows;
		this.result = result;
		this.totalRows = totalRows;
	}

	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

	public List<MongoLogDTO> getResult() {
		return result;
	}

	public void setResult(List<MongoLogDTO> list) {
		this.result = list;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	
	
	
}

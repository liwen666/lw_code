package commons.setting.external.po;

import java.io.Serializable;
import java.util.List;
//查询条件设置
public class DictTSetQuerydPO implements Serializable{
	private static final long serialVersionUID = 3647233284813657927L;

	private String tableID; //表ID
	private String recID; //记录ID
	private int showCols; //显示列数
	private int titleWidth; //标题列宽度
	private String isShowTitle; //是否显示标题列
	
	private String stage;

	//查询列设置
	private List<DictTSetQuerydDetPO> querydDet;

	public String getTableID() {
		return tableID;
	}
	public void setTableID(String tableID) {
		this.tableID = tableID;
	}
	public String getRecID() {
		return recID;
	}
	public void setRecID(String recID) {
		this.recID = recID;
	}
	public int getShowCols() {
		return showCols;
	}
	public void setShowCols(int showCols) {
		this.showCols = showCols;
	}
	public int getTitleWidth() {
		return titleWidth;
	}
	public void setTitleWidth(int titleWidth) {
		this.titleWidth = titleWidth;
	}
	public String getIsShowTitle() {
		return isShowTitle;
	}
	public void setIsShowTitle(String isShowTitle) {
		this.isShowTitle = isShowTitle;
	}
	public List<DictTSetQuerydDetPO> getQuerydDet() {
		return querydDet;
	}
	public void setQuerydDet(List<DictTSetQuerydDetPO> querydDet) {
		this.querydDet = querydDet;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}

}

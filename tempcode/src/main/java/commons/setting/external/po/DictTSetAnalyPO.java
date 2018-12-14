package commons.setting.external.po;

import java.io.Serializable;
import java.util.List;
//查询（分析）引用定义
public class DictTSetAnalyPO implements Serializable{
	private static final long serialVersionUID = 3647233284813657927L;

	private String tableID; //表ID
	private String hrefName;
	private String hrefLoc; //查询分析.do
	private String hrefParmID; //参数,参见dict_t_SetHREFParm
	private String pictureID; //图片标志ID
	private String pictureUrl; //图片路径

	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	private String stage;
	
	//查询参数设置表
	private List<DictTSetHrefParmPO> hrefParm;

	public String getTableID() {
		return tableID;
	}
	public void setTableID(String tableID) {
		this.tableID = tableID;
	}
	public String getHrefName() {
		return hrefName;
	}
	public void setHrefName(String hrefName) {
		this.hrefName = hrefName;
	}
	public String getHrefLoc() {
		return hrefLoc;
	}
	public void setHrefLoc(String hrefLoc) {
		this.hrefLoc = hrefLoc;
	}
	public String getHrefParmID() {
		return hrefParmID;
	}
	public void setHrefParmID(String hrefParmID) {
		this.hrefParmID = hrefParmID;
	}
	public String getPictureID() {
		return pictureID;
	}
	public void setPictureID(String pictureID) {
		this.pictureID = pictureID;
	}
	public List<DictTSetHrefParmPO> getHrefParm() {
		return hrefParm;
	}
	public void setHrefParm(List<DictTSetHrefParmPO> hrefParm) {
		this.hrefParm = hrefParm;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}

}

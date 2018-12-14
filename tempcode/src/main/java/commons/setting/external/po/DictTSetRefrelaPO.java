package commons.setting.external.po;

import java.io.Serializable;
import java.util.List;
// 引用列关系定义
public class DictTSetRefrelaPO implements Serializable{
	private static final long serialVersionUID = 3647233284813657927L;
	private String relaID; //关系ID、
	private String relaName;//关系名称
	private String relaDbTab;//物理表名
	private String tableID; //表ID
	private String condColumnID; //列ID
	private String columnID; //对应列ID
	private String tableType;//表类型
    private String stage;
	private String SNAME_columnID;
	private String SNAME_condColumnID; 

	//关联列 与 被关联列
	private List<DictTSetRefrelaDataPO> refrelaData ;

    
    public String getTableType() {
        return tableType;
    }
    public void setTableType(String tableType) {
        this.tableType = tableType;
    }
	public String getRelaID() {
		return relaID;
	}
	public void setRelaID(String relaID) {
		this.relaID = relaID;
	}
	public String getTableID() {
		return tableID;
	}
	public void setTableID(String tableID) {
		this.tableID = tableID;
	}
	

	public String getCondColumnID() {
		return condColumnID;
	}
	public void setCondColumnID(String condColumnID) {
		this.condColumnID = condColumnID;
	}
	
	public String getColumnID() {
		return columnID;
	}
	public void setColumnID(String columnID) {
		this.columnID = columnID;
	}
	public List<DictTSetRefrelaDataPO> getRefrelaData() {
		return refrelaData;
	}
	public void setRefrelaData(List<DictTSetRefrelaDataPO> refrelaData) {
		this.refrelaData = refrelaData;
	}
	public String getRelaName() {
		return relaName;
	}
	public void setRelaName(String relaName) {
		this.relaName = relaName;
	}
	public String getRelaDbTab() {
		return relaDbTab;
	}
	public void setRelaDbTab(String relaDbTab) {
		this.relaDbTab = relaDbTab;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getSNAME_columnID() {
		return SNAME_columnID;
	}
	public void setSNAME_columnID(String sNAME_columnID) {
		SNAME_columnID = sNAME_columnID;
	}
	public String getSNAME_condColumnID() {
		return SNAME_condColumnID;
	}
	public void setSNAME_condColumnID(String sNAME_condColumnID) {
		SNAME_condColumnID = sNAME_condColumnID;
	}
	

}

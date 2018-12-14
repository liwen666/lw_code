package commons.setting.external.po;

import java.io.Serializable;

import com.tjhq.commons.setting.external.po.DictTSetRefrelaPO;

public class DictTSetRefrelaDataPO implements Serializable{
	private static final long serialVersionUID = 3647233284813657927L;

	private String relaID; //关系ID
	private String condDataID; //列ID对应引用数据ID
	private String dataID; //对应列ID对应引用数据ID


	private String code;
	private String name;
	private DictTSetRefrelaPO refrela;


	public DictTSetRefrelaPO getRefrela() {
		return refrela;
	}
	public void setRefrela(DictTSetRefrelaPO refrela) {
		this.refrela = refrela;
	}
	public String getRelaID() {
		return relaID;
	}
	public void setRelaID(String relaID) {
		this.relaID = relaID;
	}

	public String getCondDataID() {
		return condDataID;
	}
	public void setCondDataID(String condDataID) {
		this.condDataID = condDataID;
	}
	public String getDataID() {
		return dataID;
	}
	public void setDataID(String dataID) {
		this.dataID = dataID;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}

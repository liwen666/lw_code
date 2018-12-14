package commons.setting.external.po;

import java.io.Serializable;

public class RECPO  implements Serializable{

	private static final long serialVersionUID = 3666761178752783487L;
	
	private String OBJECTID; //对象ID.[表：用‘*’表示/其他：SPF_T_PROJTAB的ID]
	private String TABLEID; //表ID
	private String RECID; //记录ID
	private String SHOWCOLS; //显示列数
	private String TITLEWIDTH; //标题列宽度
	private String ABSOLUTEPOSITION; //页面是否为绝对定位
	private String LABLETEXTALIGN;//lable对齐方式；0：右，1：左。
	private String ORDERID; //显示次序
	private String REMARK; //注释

	public String getOBJECTID() {
		return OBJECTID;
	}
	public void setOBJECTID( String oBJECTID ) {
		OBJECTID = oBJECTID;
	}
	public String getTABLEID() {
		return TABLEID;
	}
	public void setTABLEID( String tABLEID ) {
		TABLEID = tABLEID;
	}
	public String getRECID() {
		return RECID;
	}
	public void setRECID( String rECID ) {
		RECID = rECID;
	}
	public String getLABLETEXTALIGN() {
		return LABLETEXTALIGN;
	}
	public void setLABLETEXTALIGN(String lABLETEXTALIGN) {
		LABLETEXTALIGN = lABLETEXTALIGN;
	}
	public String getSHOWCOLS() {
		return SHOWCOLS;
	}
	public void setSHOWCOLS( String sHOWCOLS ) {
		SHOWCOLS = sHOWCOLS;
	}
	public String getTITLEWIDTH() {
		return TITLEWIDTH;
	}
	public void setTITLEWIDTH( String tITLEWIDTH ) {
		TITLEWIDTH = tITLEWIDTH;
	}
	public String getORDERID() {
		return ORDERID;
	}
	public void setORDERID( String oRDERID ) {
		ORDERID = oRDERID;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK( String rEMARK ) {
		REMARK = rEMARK;
	}
	public String getABSOLUTEPOSITION() {
		return ABSOLUTEPOSITION;
	}
	public void setABSOLUTEPOSITION(String aBSOLUTEPOSITION) {
		ABSOLUTEPOSITION = aBSOLUTEPOSITION;
	}
}

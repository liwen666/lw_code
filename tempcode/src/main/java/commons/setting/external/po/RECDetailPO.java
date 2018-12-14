package commons.setting.external.po;

import java.io.Serializable;
import java.util.List;

public class RECDetailPO implements Serializable{
	private static final long serialVersionUID = -4708592887586599222L;
	private String recid; //主记录ID
	private String ctrlid; //如果是分组控件，则新产生ID;如果是选择表结构的列，则为COLUMNID
	private String ctrlname; //如果是分组控件，则新产生ID;如果是选择表结构的列，默认为空，列名可改。
	private String superid; //父级id
	private String isleaf; //是否末级
	private int levelno; //级次
	private int orderid; //序号
	private String istext; //是否大文本
	private int colspan; //所占列数
	private int rowspan; //所占行数
	private int LEFTCOLS; //公组框距离左边框列数
	private int TOPROWS; //公组框距离上边框行数
	private int TOPGROUPS; //公组框距离上边框的公组框个数
	private String BORDER;//公组框有无边框和标题
	private String isgroupctrl; //是否分组控件。（仅针对isLEAF=0的）
	private String isshow; //是否显示
	private List<RECDetailPO> listDetail;
	
	public String getBORDER() {
		return BORDER;
	}
	public void setBORDER(String bORDER) {
		BORDER = bORDER;
	}
	public int getTOPGROUPS() {
		return TOPGROUPS;
	}
	public void setTOPGROUPS(int tOPGROUPS) {
		TOPGROUPS = tOPGROUPS;
	}
	public int getLEFTCOLS() {
		return LEFTCOLS;
	}
	public void setLEFTCOLS(int lEFTCOLS) {
		LEFTCOLS = lEFTCOLS;
	}
	public int getTOPROWS() {
		return TOPROWS;
	}
	public void setTOPROWS(int tOPROWS) {
		TOPROWS = tOPROWS;
	}
	public List<RECDetailPO> getListDetail() {
	
		return listDetail;
	}

	
	public void setListDetail( List<RECDetailPO> listDetail ) {
	
		this.listDetail = listDetail;
	}

	public String getRecid() {
	
		return recid;
	}
	
	public void setRecid( String recid ) {
	
		this.recid = recid;
	}
	
	public String getCtrlid() {
	
		return ctrlid;
	}
	
	public void setCtrlid( String ctrlid ) {
	
		this.ctrlid = ctrlid;
	}
	
	public String getCtrlname() {
	
		return ctrlname;
	}
	
	public void setCtrlname( String ctrlname ) {
	
		this.ctrlname = ctrlname;
	}
	
	public String getSuperid() {
	
		return superid;
	}
	
	public void setSuperid( String superid ) {
	
		this.superid = superid;
	}
	
	public String getIsleaf() {
	
		return isleaf;
	}
	
	public void setIsleaf( String isleaf ) {
	
		this.isleaf = isleaf;
	}
	
	public int getLevelno() {
	
		return levelno;
	}
	
	public void setLevelno( int levelno ) {
	
		this.levelno = levelno;
	}
	
	public int getOrderid() {
	
		return orderid;
	}
	
	public void setOrderid( int orderid ) {
	
		this.orderid = orderid;
	}
	
	public String getIstext() {
	
		return istext;
	}
	
	public void setIstext( String istext ) {
	
		this.istext = istext;
	}
	
	public int getColspan() {
	
		return colspan;
	}
	
	public void setColspan( int colspan ) {
	
		this.colspan = colspan;
	}
	
	public int getRowspan() {
	
		return rowspan;
	}
	
	public void setRowspan( int rowspan ) {
	
		this.rowspan = rowspan;
	}
	
	public String getIsgroupctrl() {
	
		return isgroupctrl;
	}
	
	public void setIsgroupctrl( String isgroupctrl ) {
	
		this.isgroupctrl = isgroupctrl;
	}
	
	public String getIsshow() {
	
		return isshow;
	}
	
	public void setIsshow( String isshow ) {
	
		this.isshow = isshow;
	}
	
	
}

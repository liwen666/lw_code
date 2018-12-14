package commons.setting.external.po;

import java.io.Serializable;

/**
 * 录入表实体
 * @ClassName: ProjTabEntity
 * @Description: 录入表
 * @author liangdb
 * @date 2014-1-10 下午06:09:46
 */
public class ProjTabPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String objectid; //对象ID.[项目类别ID/专项资金ID]
	private String typeflag; //0:针对专项资金、1:针对专项资金下级执行项目
	private String id; //ID
	private int orderid; //序号
	private String tableid; //表ID
	private String isreserve; //
	private String isreadonly;
	private String ismaintable;
	private String processid;
	private String isopened;



	public String getObjectid() {

		return objectid;
	}


	public void setObjectid( String objectid ) {

		this.objectid = objectid;
	}


	public String getTypeflag() {

		return typeflag;
	}


	public void setTypeflag( String typeflag ) {

		this.typeflag = typeflag;
	}


	public String getId() {

		return id;
	}


	public void setId( String id ) {

		this.id = id;
	}


	public int getOrderid() {

		return orderid;
	}


	public void setOrderid( int orderid ) {

		this.orderid = orderid;
	}


	public String getTableid() {

		return tableid;
	}


	public void setTableid( String tableid ) {

		this.tableid = tableid;
	}


	public String getIsreserve() {

		return isreserve;
	}


	public void setIsreserve( String isreserve ) {

		this.isreserve = isreserve;
	}


	public void setIsreadonly(String isreadonly) {
		this.isreadonly = isreadonly;
	}


	public String getIsreadonly() {
		return isreadonly;
	}


	public void setIsmaintable(String ismaintable) {
		this.ismaintable = ismaintable;
	}


	public String getIsmaintable() {
		return ismaintable;
	}


	public void setProcessid(String processid) {
		this.processid = processid;
	}


	public String getProcessid() {
		return processid;
	}


	public void setIsopened(String isopened) {
		this.isopened = isopened;
	}


	public String getIsopened() {
		return isopened;
	}


	

}

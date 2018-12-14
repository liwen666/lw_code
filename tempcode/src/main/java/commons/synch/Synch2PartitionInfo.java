package commons.synch;

public class Synch2PartitionInfo {
	/**
	 * 省份分区信息
	 */
	private String province;
	/**
	 * 年度分区信息
	 */
	private String year;
	
	
	public Synch2PartitionInfo(String province,String year){
		this.province = province;
		this.year = year;
	}
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	
}

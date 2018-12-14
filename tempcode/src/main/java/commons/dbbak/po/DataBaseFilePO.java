package commons.dbbak.po;

public class DataBaseFilePO {
	
	/**
	 * 文件名称
	 */
	private String file_Name;
	/**
	 * 文件内容
	 */
	private byte[] file_dmp;
	
	public String getFile_Name() {
		return file_Name;
	}
	public void setFile_Name(String fileName) {
		file_Name = fileName;
	}
	public byte[] getFile_dmp() {
		return file_dmp;
	}
	public void setFile_dmp(byte[] fileDmp) {
		file_dmp = fileDmp;
	}
	
	
}

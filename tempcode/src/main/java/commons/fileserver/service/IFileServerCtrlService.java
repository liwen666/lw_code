package commons.fileserver.service;

import com.tjhq.commons.fileserver.po.AttachmentPO;

public interface IFileServerCtrlService {

	public String insertAttachment(AttachmentPO attachmentPO);
	
	public AttachmentPO getAttachmentByID(String attachmentID);
	
	public void getTestTableData();

}

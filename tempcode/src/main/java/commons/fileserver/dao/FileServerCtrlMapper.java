package commons.fileserver.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.fileserver.po.AttachmentPO;

/**
 * 文件服务器操作
 * @author YAN
 * @CreateDate 2014-3-3
 * @version 1.0
 */
public interface FileServerCtrlMapper extends SuperMapper{

	public void insertAttachment(AttachmentPO attachmentPO);
	
	public AttachmentPO getAttachmentByID(@Param("attachmentID") String attachmentID);
	
	public List<Map> getTestTableData1();
	
	public List<Map> getTestTableData2();
}

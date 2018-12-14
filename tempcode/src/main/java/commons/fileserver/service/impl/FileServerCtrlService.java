package commons.fileserver.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.longtu.businessframework.common.fudsclient.service.IFUDSService;
import com.longtu.framework.util.ServiceFactory;
import com.tjhq.commons.dao.UtilsMapper;
import com.tjhq.commons.fileserver.dao.FileServerCtrlMapper;
import com.tjhq.commons.fileserver.po.AttachmentPO;
import com.tjhq.commons.fileserver.service.IFileServerCtrlService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
public class FileServerCtrlService implements IFileServerCtrlService {
	@Resource
	FileServerCtrlMapper fileServerCtrlMapper;
	@Resource
	UtilsMapper utilsMapper;

	@Transactional(readOnly=false)
	public String insertAttachment(AttachmentPO attachmentPO) {
//		String attachmentID = utilsMapper.getDBUniqueID();
//		attachmentPO.setAttachmentID(attachmentID);
		fileServerCtrlMapper.insertAttachment(attachmentPO);
		return attachmentPO.getAttachmentID();
	}

	public AttachmentPO getAttachmentByID(String attachmentID) {
		return fileServerCtrlMapper.getAttachmentByID(attachmentID);
	}
	
	public void getTestTableData(){
		System.out.println("----------CACHE_T_CODE--------------");
		List<Map> resultList = fileServerCtrlMapper.getTestTableData1();
		System.out.println("----------CACHE_T_QREPORT--------------");
		resultList = fileServerCtrlMapper.getTestTableData2();
	}

}

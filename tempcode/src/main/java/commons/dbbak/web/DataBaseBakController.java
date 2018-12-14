package commons.dbbak.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.dbbak.po.BackupInfoPO;
import com.tjhq.commons.dbbak.po.DataBaseFilePO;
import com.tjhq.commons.dbbak.service.IDataBaseBakService;

@Controller
@RequestMapping(value = "/commons/database")
public class DataBaseBakController {
	
	@Resource
	private IDataBaseBakService dataBaseBakService;

	public IDataBaseBakService getDataBaseBakService() {
		return dataBaseBakService;
	}

	public void setDataBaseBakService(IDataBaseBakService dataBaseBakService) {
		this.dataBaseBakService = dataBaseBakService;
	}
	
	@RequestMapping(value = "")
	public String forwardBackupMain() {
		return "commons/dbbak/backupMain";
	}
	
	@RequestMapping(value = "/detail")
	public String forwardBackupDetail() {
		return "commons/dbbak/backupDetail";
	}
	
	@RequestMapping(value = "/runBackup")
	public void runBackup() {
		getDataBaseBakService().backUpDataBase();
	}
	
	@RequestMapping(value = "/getBackupList")
	@ResponseBody
	public List<BackupInfoPO> getDataBaseBakInfoList() {
		return getDataBaseBakService().getDataBaseBakInfoList();
	}
	
	@RequestMapping(value = "/getBakFileList")
	@ResponseBody
	public List<Map<String, Object>> getDataBaseBakList(String orderID) {
		return getDataBaseBakService().getDataBaseBakList(orderID);
	}
	
	@RequestMapping(value = "/deleteBackup")
	@ResponseBody
	public String deleteBackup(String orderID) {
		getDataBaseBakService().deleteBackup(orderID);
		return "1";
	}
	
	@RequestMapping(value = "/getDmpFile")
	public void downloadDbFile(HttpServletRequest request,
			HttpServletResponse response, String orderID, String fileID) {
		ServletOutputStream outStream = null;
		try {
			DataBaseFilePO filePO = getDataBaseBakService().getDataBaseBakFile(orderID, fileID);
			outStream = response.getOutputStream();
			// 设置下载头
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ filePO.getFile_Name() + "\"");
			
			outStream.write(filePO.getFile_dmp());
			
			outStream.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outStream != null) {
				try {
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}

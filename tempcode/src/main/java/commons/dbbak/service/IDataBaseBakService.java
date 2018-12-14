package commons.dbbak.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.dbbak.po.BackupInfoPO;
import com.tjhq.commons.dbbak.po.DataBaseFilePO;

public interface IDataBaseBakService {
	
	/**
	 * 备份数据库
	 */
	public void backUpDataBase();
	
	/**
	 * 获取备份执行情况
	 * @return
	 */
	public List<BackupInfoPO> getDataBaseBakInfoList();
	
	/**
	 * 获取数据库列表
	 * @return
	 */
	public List<Map<String, Object>> getDataBaseBakList(String orderID);
	
	/**
	 * 获取数据文件
	 * @param orderID
	 * @param fileID
	 * @return
	 */
	public DataBaseFilePO getDataBaseBakFile(String orderID, String fileID);
	
	/**
	 * 删除备份
	 * @param orderID
	 * @return
	 */
	public String deleteBackup(String orderID);
}

package commons.dbbak.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dbbak.po.BackupInfoPO;
import com.tjhq.commons.dbbak.po.DataBaseFilePO;

public interface IDataBaseBakMapper extends SuperMapper {
	
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
	 * 获取备份数据库列表
	 * @return
	 */
	public List<Map<String, Object>> getDataBaseBakList(@Param("orderID") String orderID);
	
	/**
	 * 获取数据文件
	 * @param orderID
	 * @param fileID
	 * @return
	 */
	public DataBaseFilePO getDataBaseBakFile(@Param("orderID") String orderID, @Param("fileID") String fileID);
	
	/**
	 * 删除备份
	 * @param orderID
	 * @return
	 */
	public String deleteBackup(@Param("orderID") String orderID) throws Exception;
}

package commons.install.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.install.po.InstallLogObject;
import com.tjhq.commons.install.po.InstallSqlObject;

/**
 * 数据库安装工具类
 * 
 * @author YAN
 * @CreateDate 2014-3-3
 * @version 1.0
 */
public interface IInstallDataBaseDao extends SuperMapper {

	public List<InstallSqlObject> getInstallSqlListByObjType(
            Map<String, String> parameterMap);

	public void executeInstallSql(@Param("sqlString") String sqlString);

	public void insertInstallLog(InstallLogObject installLogObj);

	public void updateInstallLog(InstallLogObject installLogObj);

	public void clearInstallLogSQL(@Param("logID") String logID);

	public List<InstallLogObject> getErroInstallLogList();

	public void compileInvalidObjects();

	public List<Map<String, Object>> getInstallData(Map<String, Object> map);

	public Integer hasModel(@Param("tableName") String tableName);
	
	public List<Map<String, Object>> getDefaultProvince();

	public void initDefaultPartition(@Param("defaultYear") String defaultYear,
                                     @Param("defaultProvince") String defaultProvince);

	public int isExistsDBLink(@Param("dbLink") String dbLink);
	
	public int isExistsDBObject(@Param("objectName") String objectName);
	
	public int isExistsBusiData();
	
	public String getInstallDefaultProvince();
	
	public String getInstallDefaultYear();
	
	public String getInstallFaspDBUser();
	
	
}

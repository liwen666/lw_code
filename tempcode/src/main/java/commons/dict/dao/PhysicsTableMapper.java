package commons.dict.dao;

import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;

public interface PhysicsTableMapper extends SuperMapper{

	/**
	 *  <!-- 获取数据库状态位-->
	 */
	public void getGlobalIsmultdb(Map<String, String> map)throws Exception;
	/**
	 * 执行sql 
	 */
	public String callExecDllLong(String dbSql) throws Exception;
	
	
	/**
	 *   <!-- 查看数据库是否存在      0 不存在    1 存在 -->
	 */
	public void getIfExistsInDB(Map<String, Object> map)throws Exception;
	
	/**
	 *  <!-- 获取数据库UUID-->
	 * @param map
	 * @throws Exception
	 */
	public void getDBForUUID(Map<String, String> map) throws Exception;
}

package commons.inputcomponent.utils.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;


public interface IUtilDAO  extends SuperMapper{

	/**
	 * 通用查询接口，指定sql语句查询。
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryForList(@Param("sql") String sql) throws Exception;
	
	/**
	 * 执行Sql语句，无返回值
	 * @param sql
	 * @throws Exception
	 */
	public void execute(@Param("sql") String sql) throws Exception;
	
	/**
	 * 查询个返回值
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Object queryObject(@Param("sql") String sql)throws Exception;
	
	/**.查询返回String
	 * @param sql
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public String queryStringValue(@Param("sql") String sql)throws Exception;

}

package commons.sql.dao;

import java.sql.Connection;
import java.util.Map;

public interface ICommonDao {
	public void excute(String sqlString) ;
	public Map<String,Object> queryForMap(String sqlString) ;
	public Connection  getCurrentConnection();
}

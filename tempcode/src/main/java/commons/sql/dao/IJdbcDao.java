package commons.sql.dao;

import java.sql.Connection;

public interface IJdbcDao {
	public void excute(String sqlString) ;
	public Connection  getCurrentConnection();
}

package commons.sql.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.tjhq.commons.sql.dao.ICommonDao;

public class CommonDao extends JdbcDaoSupport implements ICommonDao  {	

	@Override
	public Connection getCurrentConnection() {
		return getConnection();
	}
	@Override
	public Map<String,Object> queryForMap(String sqlString) {
		return getJdbcTemplate().queryForMap(sqlString);
	}
	@Override
	public void excute(String sqlString) {
		getJdbcTemplate().execute(sqlString);
	}
	

}

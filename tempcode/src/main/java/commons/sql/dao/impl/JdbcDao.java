package commons.sql.dao.impl;

import java.sql.Connection;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.tjhq.commons.sql.dao.IJdbcDao;

public class JdbcDao extends JdbcDaoSupport implements IJdbcDao  {	

	@Override
	public Connection getCurrentConnection() {
		return getConnection();
	}

	@Override
	public void excute(String sqlString) {
		getJdbcTemplate().execute(sqlString);
	}
}

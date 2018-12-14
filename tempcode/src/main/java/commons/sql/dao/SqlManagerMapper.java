package commons.sql.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.sql.po.SqlManagerPO;

public interface SqlManagerMapper extends SuperMapper {

	List<SqlManagerPO> getGridData(Map<String, Object> extProperties);

	String getGridDataCount(Map<String, Object> extProperties);

	void insertSqlstatement(Map<String, String> row);

	void updateSqlstatement(Map<String, String> row);

	void deleteSqlstatement(@Param("sqlid") String sqlid);

	SqlManagerPO getSqlstatementById(@Param("sqlid") String sqlid);
	
	void insertSqlLog(Map<String, String> map);
	
	void execSql(@Param("sql") String sql);
}

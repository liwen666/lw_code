package commons.sql.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;

public interface SysFuncDefMapper extends SuperMapper {

	List<HashMap<String, String>> getGridData(Map<String, Object> extProperties);

	String getGridDataCount(Map<String, Object> extProperties);

	HashMap<String, String> getDefInfoByID(@Param("guid") String guid);

	void insertData(HashMap<String, String> params);

	void deleteData(@Param("guid") String guid);

	void updateData(HashMap<String, String> params);
	
	void execSql(@Param("sql") String sql);
	
}

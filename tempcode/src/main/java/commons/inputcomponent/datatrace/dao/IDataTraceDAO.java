package commons.inputcomponent.datatrace.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;

public interface IDataTraceDAO extends SuperMapper {
	
	public List<Map<String, Object>> queryData(@Param("sql") String sql) throws Exception;
    
	public String getTaskNameByID(String taskID);


}

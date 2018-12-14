package commons.task.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;

public interface CdtTTaskRelaMapper extends SuperMapper {
	public void addTaskTargetRela(Map param);
	public List<String> getAllAgencyIdByTaskId(@Param("taskId") String taskId);
	public List<Map<String, String>> getTaskTargetRelaInstead(Map param);
	public List<Map<String, String>> getTaskTargetRelaNotInstead(Map param);
}

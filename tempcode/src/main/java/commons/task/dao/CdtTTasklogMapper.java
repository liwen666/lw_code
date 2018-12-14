package commons.task.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.task.po.TaskLogPO;

public interface CdtTTasklogMapper extends SuperMapper {
	public void recordLog(TaskLogPO tasklog);
	public List<TaskLogPO> getTaskLogs(String taskId);
	void deleteLatestTaskLog(@Param("taskId") String taskId);
}

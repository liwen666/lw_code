package commons.task.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.task.po.TaskPO;

public interface CdtTTaskinfoMapper extends SuperMapper {
	public void createTask(TaskPO taskpo);

	public void deleteTask(String taskId);
	
	public TaskPO getTaskInfo(Map<String, String> param);

	public void updateTaskStatus(Map<String, String> param);

	public List<Map<String,String>> getTaskInfos(Map<String, String> param);

	public void createRootTask(TaskPO taskPo);
	
	public List<Map<String,String>> getTaskModelRela(Map param);
	
	void updateTaskInfo(TaskPO taskPO) throws Exception;
}

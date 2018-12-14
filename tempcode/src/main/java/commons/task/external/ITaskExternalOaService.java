package commons.task.external;

import java.util.List;

import com.tjhq.commons.task.po.TaskPO;

public interface ITaskExternalOaService {
	
	public List<TaskPO>  getTaskInfosByUserAppId(String userId, String appId) throws Exception;
}

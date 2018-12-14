package commons.task.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.task.po.TaskLogPO;
import com.tjhq.commons.task.po.TaskPO;

public interface ITaskSelfService {
	
	public TaskPO createRootTask(TaskPO taskPo) throws Exception;

	public void deleteTask(String taskId) throws Exception;

	public TaskPO getTaskInfo(String taskId) throws Exception;

	public TaskPO decomposeTask(TaskPO taskPO, List<String> target) throws Exception;

	public void updateTaskStatus(String taskId, String taskStatus, String description) throws Exception;

	public List<Map<String,String>> getTaskInfos(String querySql) throws Exception;

	public List<Map<String,String>> getRelaModelByTask(String taskId) throws Exception;

	public List<Map<String,String>> getRelaAgencyByTask(String taskId);

	public boolean isInsteadTask(String taskId);

	void updateTaskInfo(TaskPO taskPO) throws Exception;

	public String getRelaAgencySQLStrByTask(String taskId);
	
	List<String> getAllAgencyIdByTaskId(String taskId);
	
	List<TaskLogPO> getTaskLogs(String taskId);
	
	void deleteLatestTaskLog(String taskId);

	public TaskPO createRootTask(String taskName, String taskCode,
                                 String taskTypeId, String receiveId, String userId,
                                 String districtId, String taskEndDate, String taskDesc, String taskCycle, String appId) throws Exception;

	/**
	 * 当前用户在指定系统中的所有任务
	 * @param userId
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> getTaskInfos(String userId, String appId) throws Exception;
	
	/**
	 * 根据任务ID查询关联的目标集合
	 * @param userId
	 * @param taskId
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public String getRelaAgencySQLStrByTask(String userId, String taskId, String appId) throws Exception;

	/**
     * 创建任务 自动生成taskID
     * @param taskPO
     * @param upagencyid
     * @param guid
     * @param districtId
     * @return
     * @throws Exception
     */
    public TaskPO createTask(TaskPO taskPO, String upagencyid, String guid, String districtId)throws Exception;
    
    /**
     *  创建任务 给定taskID
     * @param taskPO
     * @param upagencyid
     * @param guid
     * @param districtId
     * @param taskId
     * @return
     * @throws Exception
     */
    public TaskPO createTask(TaskPO taskPO, String upagencyid, String guid, String districtId, String taskId)throws Exception;
}

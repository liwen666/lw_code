package commons.task.service.impl;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dao.UtilsMapper;
import com.tjhq.commons.task.dao.CdtTTaskRelaMapper;
import com.tjhq.commons.task.dao.CdtTTaskinfoMapper;
import com.tjhq.commons.task.dao.CdtTTasklogMapper;
import com.tjhq.commons.task.po.TaskLogPO;
import com.tjhq.commons.task.po.TaskPO;
import com.tjhq.commons.task.service.ITaskSelfService;
import com.tjhq.commons.utils.IdGenerator;
@Service
@Transactional(readOnly=true)
public class TaskSelfService implements ITaskSelfService {
	
	private static final String TASK_ROOT_ID="#";
	@Resource
	private CdtTTaskinfoMapper cdtTTaskinfoMapper;
	@Resource
	private CdtTTasklogMapper cdtTTaskLogMapper;
	@Resource
	private CdtTTaskRelaMapper cdtTTaskRelaMapper;
	@Resource
	private UtilsMapper utilsMapper;
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public TaskPO createRootTask(TaskPO taskPo) throws Exception {
		this.cdtTTaskinfoMapper.createRootTask(taskPo);
		Map<String,String> param = new HashMap<String,String>();
		param.put("taskId",taskPo.getTaskId());
		param.put("targetId", taskPo.getReceiveId());
		this.cdtTTaskRelaMapper.addTaskTargetRela(param);
		recordLog(taskPo.getTaskId(),taskPo.getCreator(),taskPo.getTaskStatus(),taskPo.getTaskStatus(),taskPo.getDistrictId(),taskPo.getTaskCode());
		return taskPo;
	}
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void recordLog(String taskId, String operator, String oldStatus,
			String newStatus, String districtId,String description) {
		TaskLogPO taskLog = new TaskLogPO();
		taskLog.setTaskId(taskId);
		taskLog.setOldStatus(oldStatus);
		taskLog.setNewStatus(newStatus);
		taskLog.setDistrictId(districtId);
		taskLog.setOperator(operator);
		if(description != null &&"".equals(description)){
			taskLog.setLogDesc(description);
		}else{
			taskLog.setLogDesc("无信息");
		}
		this.cdtTTaskLogMapper.recordLog(taskLog);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void deleteTask(String taskId) throws Exception {
		this.cdtTTaskinfoMapper.deleteTask(taskId);
	}

	@Override
	public TaskPO getTaskInfo(String taskId) throws Exception{
		Map param = new HashMap();
		param.put("taskId", taskId);
		return this.cdtTTaskinfoMapper.getTaskInfo(param);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public TaskPO decomposeTask(TaskPO taskPO,List<String> targetIds) throws Exception {
		UserDTO userDto = SecureUtil.getCurrentUser();
		this.cdtTTaskinfoMapper.createTask(taskPO);
		Map<String,String> param = new HashMap<String,String>();
		param.put("taskId",taskPO.getTaskId());
		for(String targetId: targetIds){
			param.put("targetId", targetId);
			this.cdtTTaskRelaMapper.addTaskTargetRela(param);
		}
		recordLog(taskPO.getTaskId(),userDto.getGuid(),taskPO.getTaskStatus(),taskPO.getTaskStatus(),userDto.getProvince(),taskPO.getTaskCode());
		return taskPO;
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void updateTaskStatus(String taskId,String taskStatus,String description) throws Exception {
		Map<String,String> param = new HashMap<String,String>();
		param.put("taskId", taskId);
		TaskPO taskPO = this.cdtTTaskinfoMapper.getTaskInfo(param);
		UserDTO userDto = SecureUtil.getCurrentUser();
		param = new HashMap<String,String>();
		param.put("taskId", taskId);
		param.put("taskStatus", taskStatus);
		cdtTTaskinfoMapper.updateTaskStatus(param);
		recordLog(taskPO.getTaskId(),userDto.getGuid(),taskPO.getTaskStatus(),taskStatus,userDto.getProvince(),description);
	}

	@Override
	public List<Map<String,String>> getTaskInfos(String querySql) throws Exception {
		Map<String,String> param = new HashMap<String,String>();
		param.put("querySql", querySql);
		return cdtTTaskinfoMapper.getTaskInfos(param);
	}
	@Override
	public List<Map<String,String>> getRelaModelByTask(String taskId) throws Exception {
		Map param = new HashMap();
		param.put("taskId", taskId);
		return cdtTTaskinfoMapper.getTaskModelRela(param);
	}
	@Override
	public List<Map<String,String>> getRelaAgencyByTask(String taskId) {
		// TODO Auto-generated method stub
		Map param = new HashMap();
		TaskPO taskPO = null;
		try {
			taskPO  = this.getTaskInfo(taskId);
			if("1".equals(taskPO.getIsInstead())){
				String agencyId = SecureUtil.getCurrentUser().getUpagencyid();
				String levelno = utilsMapper.getAgencyLevelNoByAgencyId(agencyId);
				param.put("taskId", taskId);
				param.put("levelNo", levelno);
				return cdtTTaskRelaMapper.getTaskTargetRelaInstead(param);
			}else{
				param.put("agencyId", taskPO.getReceiveId());
				return cdtTTaskRelaMapper.getTaskTargetRelaNotInstead(param);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ArrayList<Map<String,String>>();
		}
		
	}
	@Override
	public boolean isInsteadTask(String taskId) {
		String querySql = "select taskId from cdt_t_taskrela where targetId = (select receiveId from Cdt_t_Taskinfo where taskId='"+taskId+"') and taskId='"+taskId+"'";
		Map<String,String> param = new HashMap<String,String>();
		param.put("querySql", querySql);
		List<Map<String,String>> taskInfos = cdtTTaskinfoMapper.getTaskInfos(param);
		if(taskInfos == null ||taskInfos.size() ==0){
			return true;
		}
		return false;
	}
	@Override
	public void updateTaskInfo(TaskPO taskPO) throws Exception{
		// TODO Auto-generated method stub
		cdtTTaskinfoMapper.updateTaskInfo(taskPO);
	}
	@Override
	public String getRelaAgencySQLStrByTask(String taskId) {
		TaskPO taskPO = null;
		String sql="";
		try {
			taskPO  = this.getTaskInfo(taskId);
			if("1".equals(taskPO.getIsInstead())){
				String agencyId = SecureUtil.getCurrentUser().getUpagencyid();
				String levelno = utilsMapper.getAgencyLevelNoByAgencyId(agencyId);
				sql = "select distinct *"+
		  " from code_t_agency t5"+
		 " start with t5.GUID in (select guid"+
		                       " from code_t_agency"+
		                        " where guid in"+
		                              " (select distinct targetId"+
		                                "  from cdt_t_taskrela t1"+
		                                 " where t1.taskid in"+
		                                 "   (select taskId" +
		                                 "      from cdt_t_taskInfo t" +
		                                 "     where t.taskLvlID like" +
		                                 "         (select t.taskLvlID || '%'" +
		                                 "         from cdt_t_taskInfo t" +
		                                 "         where t.taskId ='"+taskId+"'" +
		                                 "             )))" +
		                                 "  and isleaf = '1')" +
		                                 " connect by t5.levelno >= "+levelno +
		                                 "   and prior t5.SUPERGUID = t5.GUID";
				
			}else{
				sql = " select * from code_t_agency_bgt t5";
			}
			return sql;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "";
		}
		
	}
	@Override
	public List<String> getAllAgencyIdByTaskId(String taskId) {
		return cdtTTaskRelaMapper.getAllAgencyIdByTaskId(taskId);
	}
	@Override
	public List<TaskLogPO> getTaskLogs(String taskId) {
		
		return cdtTTaskLogMapper.getTaskLogs(taskId);
	}
	@Override
	public void deleteLatestTaskLog(String taskId) {
		cdtTTaskLogMapper.deleteLatestTaskLog(taskId);
	}
	@Override
	public TaskPO createRootTask(String taskName, String taskCode,
			String taskTypeId, String receiveId, String userId,
			String districtId, String taskEndDate, String taskDesc,String taskCycle,String appId) throws Exception {
		TaskPO taskPO = new TaskPO();
		String taskId = IdGenerator.get32UUID();
		taskPO.setTaskId(taskId);
		taskPO.setTaskName(taskName);
		taskPO.setTaskCode(taskCode);
		taskPO.setTaskCycle(taskCycle);
		taskPO.setTaskDesc(taskDesc);
		taskPO.setTaskTypeId(taskTypeId);
		taskPO.setReceiveId(receiveId);
		taskPO.setCreator(userId);
		taskPO.setEndDate(taskEndDate);
		taskPO.setDistrictId(districtId);
		taskPO.setRootTaskId(taskId);
		//taskPO.setSuperTaskId("#");
		//update by yxm 20160426 superID='0'
		taskPO.setSuperTaskId("0");
		taskPO.setTaskStatus("0");
		taskPO.setLevelNo("1");
		taskPO.setAppId(appId);
		taskPO = createRootTask(taskPO);
		return taskPO;
	}
	
	@Override
	public List<Map<String, String>> getTaskInfos(String userId, String appId)
			throws Exception {
		String agencyId = SecureUtil.getCurrentUser().getUpagencyid();
		String querySql = " select t.taskId from cdt_t_taskInfo t where t.appId='"+appId+"' and t.receiveId= '"+agencyId+"' ";
		return getTaskInfos(querySql);
	}
	@Override
	public String getRelaAgencySQLStrByTask(String userId, String taskId,
			String appId) throws Exception {
		return getRelaAgencySQLStrByTask(taskId);
	}
    @Override
    public TaskPO createTask(TaskPO paramTaskPO, String upagencyid, String guid, String districtId) throws Exception{

        String taskId = IdGenerator.get32UUID().toUpperCase();
        paramTaskPO.setTaskId(taskId);     
        paramTaskPO.setReceiveId(upagencyid);
        paramTaskPO.setCreator(guid);
        paramTaskPO.setDistrictId(districtId);
        paramTaskPO.setRootTaskId(taskId);
        paramTaskPO.setSuperTaskId("0");
        paramTaskPO.setTaskStatus("0");
        paramTaskPO.setLevelNo("1");
        return createRootTask(paramTaskPO);
    
    }
    @Override
    public TaskPO createTask(TaskPO paramTaskPO, String upagencyid, String guid, String districtId,String taskId) throws Exception{

        paramTaskPO.setTaskId(taskId);     
        paramTaskPO.setReceiveId(upagencyid);
        paramTaskPO.setCreator(guid);
        paramTaskPO.setDistrictId(districtId);
        paramTaskPO.setRootTaskId(taskId);
        paramTaskPO.setSuperTaskId("0");
        paramTaskPO.setTaskStatus("0");
        paramTaskPO.setLevelNo("1");
        return createRootTask(paramTaskPO);
    
    }

}

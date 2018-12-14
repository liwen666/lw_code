package commons.task.po;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 任务管理PO对象
 * @author zushuxin
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class TaskPO {
	//任务唯一32位ID
	private String taskId;
	//任务名称
	private String taskName;
	//任务编号
	private String taskCode;
	//上级任务
	private String superTaskId;
	//下级任务
	private List<TaskPO> subTaskPo;
	//任务分类ID
	private String taskTypeId;
	//主任务ID
	private String rootTaskId;
	//任务行政区划
	private String districtId;
	//任务执行单位
	private String receiveId;
	//任务层级码
	private String taskLvlId;
	//任务级次
	private String levelNo;
	//任务采集周期
	private String taskCycle;
	//数据采集开始日期
	private String dataStartDate;
	//数据采集结束日期
	private String dataEndDate;
	//任务创建人
	private String creator;
	//任务创建时间
	private String createDate;
	//任务描述
	private String taskDesc;
	//任务截止日期
	private String endDate;
	//任务状态
	private String taskStatus;
	//任务总结内容
	private String remark;
	//任务对应采集数据单位
	private List<Map<String,String>> targetIds;
	//任务对应附件
	private List<Object> attachements;
	//任务日志
	private List<Object> taskLogs;
	
	private String appId;
	
	private String SNAME_taskStatus;
	
	private String isInstead;
	
	private String isLeaf;
	
	private String finYear;//add by yxm 20160511
	
	private String taskSource;//add by yxm 20160519
	private String sname_taskSource;//add by yxm 20160519
	
	private String isShow;//是否在数据查看界面显示 add by yxm 20160629
	
	private String transformType;//任务类型数据转换方式 add by yxm 20170320
	
	
	/**
     * @return the transformType
     */
    public String getTransformType() {
        return transformType;
    }
    /**
     * @param transformType the transformType to set
     */
    public void setTransformType(String transformType) {
        this.transformType = transformType;
    }
    public String getIsShow() {
        return isShow;
    }
    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }
    public String getTaskSource() {
        return taskSource;
    }
    public void setTaskSource(String taskSource) {
        this.taskSource = taskSource;
    }
    public String getSname_taskSource() {
        return sname_taskSource;
    }
    public void setSname_taskSource(String snameTaskSource) {
        sname_taskSource = snameTaskSource;
    }
    public String getFinYear() {
        return finYear;
    }
    public void setFinYear(String finYear) {
        this.finYear = finYear;
    }
    public String getIsInstead() {
		return isInstead;
	}
	public void setIsInstead(String isInstead) {
		this.isInstead = isInstead;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getSNAME_taskStatus() {
		return SNAME_taskStatus;
	}
	public void setSNAME_taskStatus(String sNAME_taskStatus) {
		SNAME_taskStatus = sNAME_taskStatus;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getSuperTaskId() {
		return superTaskId;
	}
	public void setSuperTaskId(String superTaskId) {
		this.superTaskId = superTaskId;
	}
	public String getRootTaskId() {
		return rootTaskId;
	}
	public void setRootTaskId(String rootTaskId) {
		this.rootTaskId = rootTaskId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	public String getTaskTypeId() {
		return taskTypeId;
	}
	public void setTaskTypeId(String taskTypeId) {
		this.taskTypeId = taskTypeId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}
	public String getTaskLvlId() {
		return taskLvlId;
	}
	public void setTaskLvlId(String taskLvlId) {
		this.taskLvlId = taskLvlId;
	}
	public String getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(String levelNo) {
		this.levelNo = levelNo;
	}
	public String getTaskCycle() {
		return taskCycle;
	}
	public void setTaskCycle(String taskCycle) {
		this.taskCycle = taskCycle;
	}
	public String getDataStartDate() {
		return dataStartDate;
	}
	public void setDataStartDate(String dataStartDate) {
		this.dataStartDate = dataStartDate;
	}
	public String getDataEndDate() {
		return dataEndDate;
	}
	public void setDataEndDate(String dataEndDate) {
		this.dataEndDate = dataEndDate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<Map<String, String>> getTargetIds() {
		return targetIds;
	}
	public void setTargetIds(List<Map<String, String>> targetIds) {
		this.targetIds = targetIds;
	}
	public List<Object> getAttachements() {
		return attachements;
	}
	public void setAttachements(List<Object> attachements) {
		this.attachements = attachements;
	}
	public List<Object> getTaskLogs() {
		return taskLogs;
	}
	public void setTaskLogs(List<Object> taskLogs) {
		this.taskLogs = taskLogs;
	}
	
	
}

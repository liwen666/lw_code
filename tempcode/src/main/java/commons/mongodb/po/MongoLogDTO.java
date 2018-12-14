package commons.mongodb.po;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class MongoLogDTO implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//进行了什么操作（删除、添加、修改。。。）
	private String function;
	//操作的表ID 
	private String tableID;
	//每条数据的列英文名称 和 对应值
	private Map<String, Object> map;
	//该字段 用于模糊查询    不用set值
	private String condation;
	//系统名称  可不用处理
	private String appID;
	//任务ID 预算必填 其他系统可空
	private String taskID;
	//任务名称  通过任务ID获得 可不填
	private String taskName;
	//日志来源  如果不填则默认为方法名称
	private String opration;
	//用户名称 不用做处理
	private String userName;
    //记录日志时间  不用处理
	private String dataTime;
	
	private String text4;
	private String text5;
	private String text6;
	private String text7;
	private String text8;
	private String text9;
	private String text10;
	public MongoLogDTO() {
		super();
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getTableID() {
		return tableID;
	}
	public void setTableID(String tableID) {
		this.tableID = tableID;
	}

	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public String getCondation() {
		return condation;
	}
	public void setCondation(String condation) {
		this.condation = condation;
	}
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getTaskID() {
		return taskID;
	}
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getOpration() {
		return opration;
	}
	public void setOpration(String opration) {
		this.opration = opration;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDataTime() {
		return dataTime;
	}
	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
	
	public String getText4() {
		return text4;
	}
	public void setText4(String text4) {
		this.text4 = text4;
	}
	public String getText5() {
		return text5;
	}
	public void setText5(String text5) {
		this.text5 = text5;
	}
	public String getText6() {
		return text6;
	}
	public void setText6(String text6) {
		this.text6 = text6;
	}
	public String getText7() {
		return text7;
	}
	public void setText7(String text7) {
		this.text7 = text7;
	}
	public String getText8() {
		return text8;
	}
	public void setText8(String text8) {
		this.text8 = text8;
	}
	public String getText9() {
		return text9;
	}
	public void setText9(String text9) {
		this.text9 = text9;
	}
	public String getText10() {
		return text10;
	}
	public void setText10(String text10) {
		this.text10 = text10;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
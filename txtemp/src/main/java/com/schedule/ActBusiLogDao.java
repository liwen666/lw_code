package com.schedule;

import java.util.List;
import java.util.Map;

import com.hq.bpmn.common.Mybatis.SuperMapper;
import com.hq.bpmn.processinstance.bean.BpmnTicket;
import com.hq.bpmn.templatedef.bean.BpmnCode;
import com.hq.bpmn.templatedef.bean.BpmnTemplateDef;

/**
 * 流程模版定义数据访问层接口。
 * 
 *
 */
public interface ActBusiLogDao extends SuperMapper{
	
	/**
	 * 将日志数据插入表中
	 * @return
	 */
	public int addLogId(com.client.ActBusiLogDomain dom);
	public int batchAddLogId(List<com.client.ActBusiLogDomain> logList);
	
}
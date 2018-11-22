package com;



import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BpmnService {


	/**
	 * 查询工作流全部节点流转路径历史信息(查询所有节点的路径信息，包括执行撤销动作的节点信息)
	 *
	 * @param bpmnType
	 * 			流程类型
	 * @param ticketId
	 *          工单号
	 * @param year
	 *          年份
	 *@param currentYear
	 *          是否是本年度
	 * @return
	 * 			工作流所有节点流转路径{@link BpmnPath}历史信息列表
	 */
	public Map<List<String>,String> queryAllBpmnHistoryPath(String bpmnType,
                                                                 String ticketId, String year, boolean currentYear);
}
package com.exception.service;

import util.ResultPO;

import com.exception.ServiceException;

public interface IEfmisWebService {

	/**
	 * 公文驱动调用业务驱动时用的接口
	 * @Title: getCirculateType
	 * @Description: 公文驱动调用业务驱动时用的接口
	 * @param docId 根公文ID
	 * @param currentDocId 当前公文ID
	 * @param busiTypeId 业务类型ID
	 * @param currentNodeId 当前节点ID
	 * @param processDefId 流程定义 ID（流程版本号）
	 * @param currentUserCode 当前用户身份号码
	 * @param lstTragetDocParams 目标参数信息（
	 * 					targetDocId 目标公文ID, 
	 * 					targetNodeId 目标节点ID, 
	 * 					targetNodeName 目标节点名称, 
	 * 					targetUserCode 目标用户身编码（公文驱动即将流转下一步的人））
	 * @param parentNodeId 上一级节点ID
	 * @param strExtendParam 扩展参数 //{用户tokenid}
	 * @return String
	 */
	public ResultPO getCirculateType(String docId, String currentDocId, String busiTypeId, String currentNodeId, String processDefId,
			String currentUserCode, String strTragetDocParams, String parentNodeId, String strExtendParam) throws ServiceException;

	/**
	 * 公文驱动调用业务驱动的删除业务接口
	 * @Title: delEfmisData
	 * @Description: 公文驱动调用业务驱动的删除业务接口
	 * @param docId 根公文ID
	 * @param currentDocId 当前公文ID
	 * @param currentNodeId 当前节点ID
	 * @return String
	 */
	public ResultPO delEfmisData(String docId, String currentDocId, String currentNodeId) throws ServiceException;
	
	/**
	 * 公文撤回、办结等操作调用方法
	 * @param doc	根公文id
	 * @param targetDocIds	目标公文ids,逗号分隔
	 * @param operationType	操作类型（子流程撤销SUBCANCEL，子流程和主流程办结HANDEND，）
	 * @param strExtendParam	扩展参数 {用户tokenid}	
	 * @return
	 */
	public ResultPO busiOperation(String docId, String targetDocIds, String operationType, String strExtendParam)
			throws ServiceException;
	
}

package com.exception.service;

import java.util.List;
import java.util.Map;

public interface IDocBatchRelationService {

	public Map<String, Object> findDocBatchRelationById(String datakey);

	public Map<String, Object> findDocBatchRelationByDocNodeId(String docId, String currentDocId, String nodeId);

	public List<Map<String, Object>> findDocBatchRelations(Map<String, Object> params);
	
	public int addAllDocBatchRelation(List<Map<String, Object>> lstParams);
	
	public int addDocBatchRelation(Map<String, Object> params);
	
	public int addRelationParam(Map<String, Object> params);
	
	public int updateDocBatchRelation(Map<String, Object> params);
	
	public int delDocBatchRelation(Map<String, Object> params);

	/**
	 * 通过根公文ID查询批次公文关系表信息
	 * @Title: findOneRelateByDocId
	 * @Description: 通过根公文ID查询批次公文关系表信息
	 * @param docId 根公文ID
	 * @return Map<String,Object>
	 */
	public Map<String, Object> findOneRelateByDocId(String docId);

	
}

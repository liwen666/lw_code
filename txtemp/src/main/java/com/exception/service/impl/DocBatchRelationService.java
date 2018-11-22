package com.exception.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exception.dao.IDocBatchRelationDao;
import com.exception.service.IDocBatchRelationService;


@Service
@Transactional(readOnly = true)
public class DocBatchRelationService implements IDocBatchRelationService {

	@Resource
	private IDocBatchRelationDao docBatchRelationDao;

	@Override
	public Map<String, Object> findDocBatchRelationById(String datakey) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("datakey", datakey);
		return docBatchRelationDao.queryDocBatchRelationById(params);
	}

	@Override
	public Map<String, Object> findDocBatchRelationByDocNodeId(String docId, String currentDocId, String nodeId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("docId", docId);
		params.put("currentDocId", currentDocId);
		params.put("nodeId", nodeId);
		// params.put("state", SceneData.STATE_ACTIVE);
		List<Map<String, Object>> lstRelations = docBatchRelationDao.queryDocBatchRelations(params);
		if (null != lstRelations && lstRelations.size() >= 1) {			
			return lstRelations.get(0);
		}
		return new HashMap<String, Object>();
	}

	@Override
	public List<Map<String, Object>> findDocBatchRelations(Map<String, Object> params) {
		return docBatchRelationDao.queryDocBatchRelations(params);
	}

	@Override
	public int addAllDocBatchRelation(List<Map<String, Object>> lstParams) {
		return docBatchRelationDao.insertAllDocBatchRelation(lstParams);
	}
	
	@Override
	public int addDocBatchRelation(Map<String, Object> params) {
		return docBatchRelationDao.insertDocBatchRelation(params);
	}
	
	@Override
	public int addRelationParam(Map<String, Object> params) {
		return docBatchRelationDao.insertRelationParam(params);
	}

	@Override
	public int updateDocBatchRelation(Map<String, Object> params) {
		return docBatchRelationDao.updateDocBatchRelation(params);
	}

	@Override
	public int delDocBatchRelation(Map<String, Object> params) {
		return docBatchRelationDao.deleteDocBatchRelation(params);
	}

	@Override
	public Map<String, Object> findOneRelateByDocId(String docId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("docId", docId);
		return docBatchRelationDao.queryDocBatchRelationsOne(params);
	}

}

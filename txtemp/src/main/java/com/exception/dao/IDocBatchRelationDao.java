package com.exception.dao;

import java.util.List;
import java.util.Map;

import common.mybatis.SuperMapper;


public interface IDocBatchRelationDao extends SuperMapper {

	/**
	 * 通过主键datakey查询公文批次关系信息
	 * @Title: queryDocBatchRelationById
	 * @Description: 通过主键datakey查询公文批次关系信息
	 * @param params datakey
	 * @return Map<String,Object>
	 */
	public Map<String, Object> queryDocBatchRelationById(Map<String, Object> params);
	
	/**
	 * 通过部分参数查询公文批次关系信息
	 * @Title: queryDocBatchRelations
	 * @Description: 通过部分参数查询公文批次关系信息
	 * @param params docId等，当参数为空时，查询全部信息
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryDocBatchRelations(Map<String, Object> params);
	
	/**
	 * 批量插入数据
	 * @Title: insertAllDocBatchRelation
	 * @Description: 批量插入数据
	 * @param lstParams
	 * @return int
	 */
	public int insertAllDocBatchRelation(List<Map<String, Object>> lstParams);
	
	/**
	 * 插入公文批次关系信息
	 * @Title: insertDocBatchRelation
	 * @Description: 插入公文批次关系信息
	 * @param params 视图的所有字段
	 * @return int 返回插入条数
	 */
	public int insertDocBatchRelation(Map<String, Object> params);
	
	/**
	 * 保存传递的关系参数
	 * @Title: insertRelationParam
	 * @Description: 保存传递的关系参数
	 * @param params
	 * @return int
	 */
	public int insertRelationParam(Map<String, Object> params);
	
	/**
	 * 更新公文批次关系信息
	 * @Title: updateDocBatchRelation
	 * @Description: 更新公文批次关系信息
	 * @param params createTime和datakey必传
	 * @return int 返回更新条数
	 */
	public int updateDocBatchRelation(Map<String, Object> params);
	
	/**
	 * 删除公文批次关系
	 * @Title: deleteDocBatchRelation
	 * @Description: 删除公文批次关系
	 * @param params datakey, 可单个，可多个，已','隔开
	 * @return int 返回删除条数
	 */
	public int deleteDocBatchRelation(Map<String, Object> params);
	/**
	 * 通过部分参数查询公文批次关系信息 添加当前公文id和活动状态
	 * @Title: queryDocBatchRelations
	 * @Description: 通过部分参数查询公文批次关系信息
	 * @param params docId等，当参数为空时，查询全部信息
	 * @return List<Map<String,Object>>
	 */
	public Map<String, Object> queryDocBatchRelationsOne(Map<String, Object> queryCondition);
	
}

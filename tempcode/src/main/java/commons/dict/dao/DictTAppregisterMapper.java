package commons.dict.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dict.external.po.DictTAppregisterPO;


public interface DictTAppregisterMapper extends SuperMapper{

	

	/**
	 * 添加
	 * @param DictTAppregisterPO
	 */
	public void insertDictTAppregister(DictTAppregisterPO dictTAppregister);
	
	/**
	 * 修改
	 * @param DictTAppregisterPO
	 */
	public void updateDictTAppregister(DictTAppregisterPO dictTAppregister);
	
	/**
	 * 删除
	 * @param String dbid
	 */
	public void deleteDictTAppregister(String dbid);
	
	
	/**
	 * ***********************************************************************************
	 */
	
	
	
	/**
	 * 获取 by dbid 
	 * @param String dbid
	 */
	public DictTAppregisterPO getDictTAppregister(String dbid);
	
	/**
	 * 获取 by appid 
	 * @param String appid
	 */
	public DictTAppregisterPO getDictTAppregisterByAppid(String appid);
	
	/**
	 * 获取全部
	 * @return List<DictTAppregister>
	 */
	public List<DictTAppregisterPO> getAllDictTAppregister();
	
	/**
	 * 查询 by 条件   需要模糊查询like的 （加'*_like'）
	 * @param Map<String, Object> map
	 * return List<DictTAppregister>
	 */
	public List<DictTAppregisterPO> findDictTAppregister(Map<String, Object> map);
	/**
	 * 查询 by 条件   需要模糊查询like的 （加'*_like'）
	 * @param Map<String, Object> map
	 * return count
	 */
	public Long findDictTAppregisterCount(Map<String, Object> map);
	/**
	 * 获取全部年份
	 * @param Map<String, Object> map
	 */
	public List<Map<String, String>> getAllFinyear();
}

package commons.dict.external.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dict.external.po.DictTModelPO;
/**
 * 表
 * @author xujingsi
 *
 */
public interface DictTModelMapper extends SuperMapper{

	
	/**
	 * 获取单个表Byid
	 * @param 
	 */
	public DictTModelPO getDictTModel(String id);
	
	/**
	 * 获取全部表
	 * @param 
	 */
	public List<DictTModelPO> getAllDictTModel();
	
	/**
	 * 查询 by 条件   需要模糊查询like的 （加'_like'）
	 * @param Map<String, Object> map
	 */
	public List<DictTModelPO> findDictTModel(Map<String, Object> map);
	
	/**
	 * 查询总记录数  by 条件    查询 by 条件   需要模糊查询like的 （加'_like'）
	 * @param Map<String, Object> map
	 */
	public Long findDictTModelCount(Map<String, Object> map);
	
	/**
	 * 获取单个 by dbtablename
	 * @param dbtablename
	 * @return
	 */
	public DictTModelPO getDictTModelByName(String dbtablename);
	public List<Map<String, Object>> getTableByDealtype(Map<String, Object> map);

	/**
	 * 获取是否存在XX关系
	 * @param tableID
	 * @return
	 */
	int existRexistRela(@Param(value = "tableID") String tableID);
}

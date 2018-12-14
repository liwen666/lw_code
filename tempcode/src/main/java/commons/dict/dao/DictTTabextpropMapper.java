package commons.dict.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dict.external.po.DictTTabextpropPO;
/**
 * 表扩展属性管理
 * @author xujingsi
 *
 */
public interface DictTTabextpropMapper extends SuperMapper{

	
	/**
	 * 添加
	 * @param DictTTabextpropPO
	 */
	public void insertDictTTabextprop(DictTTabextpropPO dictTTabextprop);
	
	/**
	 * 修改
	 * @param DictTTabextpropPO
	 */
	public void updateDictTTabextprop(DictTTabextpropPO dictTTabextprop);
	
	/**
	 * 删除
	 * @param String extid
	 */
	public void deleteDictTTabextprop(String extid);
	
	
	/**
	 * ***********************************************************************************
	 */
	
	
	
	/**
	 * 获取 by extid 
	 * @param String extid
	 */
	public DictTTabextpropPO getDictTTabextprop(String extid);
	
	/**
	 * 获取全部
	 * @return List<DictTTabextprop>
	 */
	public List<DictTTabextpropPO> getAllDictTTabextprop();
	
	/**
	 * 查询 by 条件   需要模糊查询like的 （加'*_like'）
	 * @param Map<String, Object> map
	 * return List<DictTTabextprop>
	 */
	public List<DictTTabextpropPO> findDictTTabextprop(Map<String, Object> map);
	/**
	 * 查询 by 条件   需要模糊查询like的 （加'*_like'）
	 * @param Map<String, Object> map
	 * return count
	 */
	public Long findDictTTabextpropCount(Map<String, Object> map);
}

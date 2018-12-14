package commons.dict.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dict.external.po.DictTColextpropPO;
/**
 * 列拓展属性
 * @author xujingsi
 *
 */
public interface DictTColextpropMapper extends SuperMapper{

	
	/**
	 * 添加
	 * @param DictTColextpropPO
	 */
	public void insertDictTColextprop(DictTColextpropPO dictTColextprop);
	
	/**
	 * 修改
	 * @param DictTColextpropPO
	 */
	public void updateDictTColextprop(DictTColextpropPO dictTColextprop);
	
	/**
	 * 删除
	 * @param String extid
	 */
	public void deleteDictTColextprop(String extid);
	
	
	/**
	 * ***********************************************************************************
	 */
	
	
	
	/**
	 * 获取 by extid 
	 * @param String extid
	 */
	public DictTColextpropPO getDictTColextprop(String extid);
	
	/**
	 * 获取全部
	 * @return List<DictTColextprop>
	 */
	public List<DictTColextpropPO> getAllDictTColextprop();
	
	/**
	 * 查询 by 条件   需要模糊查询like的 （加'*_like'）
	 * @param Map<String, Object> map
	 * return List<DictTColextprop>
	 */
	public List<DictTColextpropPO> findDictTColextprop(Map<String, Object> map);
	/**
	 * 查询 by 条件   需要模糊查询like的 （加'*_like'）
	 * @param Map<String, Object> map
	 * return count
	 */
	public Long findDictTColextpropCount(Map<String, Object> map);
}

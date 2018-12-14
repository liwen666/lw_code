package commons.dict.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dict.external.po.DictTDefaultcolPO;
/**
 * 表默认列管理
 * @author xujingsi
 *
 */
public interface DictTDefaultcolMapper extends SuperMapper{

	
	/**
	 * 添加
	 * @param DictTDefaultcolPO
	 */
	public void insertDictTDefaultcol(DictTDefaultcolPO dictTDefaultcol);
	
	/**
	 * 修改
	 * @param DictTDefaultcolPO
	 */
	public void updateDictTDefaultcol(DictTDefaultcolPO dictTDefaultcol);
	
	/**
	 * 删除
	 * @param String dealid
	 */
	public void deleteDictTDefaultcol(DictTDefaultcolPO dictTDefaultcol);
	
	
	/**
	 * ***********************************************************************************
	 */
	
	
	
	/**
	 * 获取 by dealid 
	 * @param String dealid
	 */
	public List<DictTDefaultcolPO> getDictTDefaultcol(String dealid);
	
	/**
	 * 获取全部
	 * @return List<DictTDefaultcol>
	 */
	public List<DictTDefaultcolPO> getAllDictTDefaultcol();
	
	/**
	 * 查询 by 条件   需要模糊查询like的 （加'*_like'）
	 * @param Map<String, Object> map
	 * return List<DictTDefaultcol>
	 */
	public List<DictTDefaultcolPO> findDictTDefaultcol(Map<String, Object> map);
	
	
	/**
	 * 查询 by 条件   需要模糊查询like的 （加'*_like'）
	 * 
	 * return List<DictTDefaultcol>
	 */
	public List<DictTDefaultcolPO> findDictTDefaultfactortcodecol();
	
	
	/**
	 * 查询 by 条件   需要模糊查询like的 （加'*_like'）,设置界面回显用
	 * @param Map<String, Object> map
	 * return List<Map>
	 */
	public List<DictTDefaultcolPO> findDictTDefaultcol4Show(Map<String, Object> map);
	/**
	 * 查询 by 条件   需要模糊查询like的 （加'*_like'）
	 * @param Map<String, Object> map
	 * return count
	 */
	public Long findDictTDefaultcolCount(Map<String, Object> map);
	
	public List<String> findRepeateDictTDefaultcol(String dealid);

	/**
	 * 获取已经设置的缺省列的数量
	 * @param dealID
	 * @return
	 */
	String getDefaultcolCountByDeal(@Param(value = "dealID") String dealID);
	
	/**
	 * 查询 by 条件   需要模糊查询like的 （加'*_like'）,设置界面回显用
	 * @param Map<String, Object> map
	 * return List<Map>
	 */
	public List<DictTDefaultcolPO> findDictTDefaultcolbyDealIDandTableId(Map<String, Object> map);
}

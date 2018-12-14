package commons.dict.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dict.external.po.DictTDealtypePO;
/**
 * 处理类型
 * @author xujingsi
 *
 */
public interface DictTDealtypeMapper extends SuperMapper{

	
	/**
	 * 添加
	 * @param DictTDealtypePO
	 */
	public void insertDictTDealtype(DictTDealtypePO dictTDealtype);
	
	/**
	 * 修改
	 * @param DictTDealtypePO
	 */
	public void updateDictTDealtype(DictTDealtypePO dictTDealtype);
	
	/**
	 * 删除
	 * @param String dealid
	 */
	public void deleteDictTDealtype(String dealid);
	
	
	/**
	 * ***********************************************************************************
	 */
	
	
	
	/**
	 * 获取 by dealid 
	 * @param Map param
	 */
	public DictTDealtypePO getDictTDealtype(Map<String, String> param);
	
	/**
	 * 获取全部
	 * @return List<DictTDealtype>
	 */
	public List<DictTDealtypePO> getAllDictTDealtype();
	
	/**
	 * 获取全部 层次
	 * @return List<DictTDealtype>
	 */
	public List<DictTDealtypePO> getAllDictTDealtypeLevel();
	//select dealid, lpad(' ',2*length(dealid)-4,' ')||dealname as dealname,appid,orderid  from dict_t_dealtype  order by orderid
	
	/**
	 * 查询 by 条件   需要模糊查询like的 （加'*_like'）
	 * @param Map<String, Object> map
	 * return List<DictTDealtype>
	 */
	public List<DictTDealtypePO> findDictTDealtype(Map<String, Object> map);
	/**
	 * 查询 by 条件   需要模糊查询like的 （加'*_like'）
	 * @param Map<String, Object> map
	 * return count
	 */
	public Long findDictTDealtypeCount(Map<String, Object> map);
	
	/**
	 * group by appid and appid<>'*'
	 * @return
	 */
	public List<DictTDealtypePO> getDictTDealtypeForGroupBy();
}

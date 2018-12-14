package commons.dict.external.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dict.external.po.DictTFactorcodePO;
/**
 * 列代码表 code
 * @author xujingsi
 *
 */
public interface DictTFactorcodeMapper extends SuperMapper{

	/**
	 * 获取单个列
	 * @param 
	 */
	public DictTFactorcodePO getDictTFactorcode(String id);
	
	/**
	 * 获取全部列
	 * @param 
	 */
	public List<DictTFactorcodePO> getAllDictTFactorcode();
	
	/**
	 * 查询 by 条件   like  查询 by 条件   需要模糊查询like的 （加'_like'）
	 * @param Map<String, Object> map
	 */
	public List<DictTFactorcodePO> findDictTFactorcode(Map<String, Object> map);
	
	/**
	 * 查询总记录数  by 条件    查询 by 条件   需要模糊查询like的 （加'_like'）
	 * @param Map<String, Object> map
	 */
	public Long findDictTFactorcodeCount(Map<String, Object> map);
	
	
	/**
	 * 获取单个列 by dbcolumnname
	 * @param 
	 */
	public DictTFactorcodePO getDictTFactorcodeByDBName(String dbcolumnname);
	
	

	/**
	 * 添加
	 * @param DictTFactorcodePO
	 */
	public void insertDictTFactorcode(DictTFactorcodePO dictTFactorcodePO);
	
	/**
	 * 修改
	 * @param DictTFactorcodePO
	 */
	public void updateDictTFactorcode(DictTFactorcodePO dictTFactorcodePO);
	
	/**
	 * 删除
	 * @param dictTFactorcodePO.columnid
	 */
	public void deleteDictTFactorcode(String columnid);
	/**
	 * 下发业务表的同时，下发引用表，已经存在该地区的的引用表不进行二次下发
	 * @return 
	 */
	public  Integer copyFactorCodeDefineToProvince(Map<String, Object> map);

	/**
	 *  获取当前代码表是否已注册到平台 
	 * @param tableID
	 * @return
	 */
	int getCodeTableRegisted(String tableID);

	/**
	 *  获取当前代码表是否被引用 
	 * @param tableID
	 * @return
	 */
	int getFactorByCsid(String tableID);
}

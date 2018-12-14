package commons.dict.external.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dict.external.po.DictTFactorPO;

/**
 * 列
 * 
 * @author xujingsi
 * 
 */
public interface DictTFactorMapper extends SuperMapper {

	/**
	 * 获取单个列
	 * 
	 * @param
	 */
	public DictTFactorPO getDictTFactor(String id);

	/**
	 * 获取全部列
	 * 
	 * @param
	 */
	public List<DictTFactorPO> getAllDictTFactor();

	/**
	 * 查询 by 条件 like 查询 by 条件 需要模糊查询like的 （加'_like'）
	 * 
	 * @param Map
	 *            <String, Object> map
	 */
	public List<DictTFactorPO> findDictTFactor(Map<String, Object> map);

	/**
	 * 根据superID找到下级
	 * 
	 * @param map
	 * @return
	 */
	public List<DictTFactorPO> findDictTFactorBySuperID(Map<String, Object> map);

	/**
	 * 查询tableid对应的所有列，排除该地区对应的列名
	 * 
	 * @param map
	 * @return
	 */
	public List<DictTFactorPO> findPDictTFactor(Map<String, Object> map);
	/**
	 * 查询P#Factor
	 * 
	 * @param map
	 * @return
	 */
	public List<DictTFactorPO> findNewPDictTFactor(Map<String, Object> map);

	/**
	 * 查询总记录数 by 条件 查询 by 条件 需要模糊查询like的 （加'_like'）
	 * 
	 * @param Map
	 *            <String, Object> map
	 */
	public int findDictTFactorCount(Map<String, Object> map);

	/**
	 * 获取单个列 by dbcolumnname
	 * 
	 * @param dbcolumnname
	 * @return
	 */
	public DictTFactorPO getDictTFactorByDBName(String dbcolumnname);

	/**
	 * 获取列<获取某个标题中的 多个列>
	 * 
	 * @param columnid
	 *            列ID
	 * @return DictTFactor
	 */
	public List<DictTFactorPO> getDictTFactorByColumnid(Map<String, Object> map);

	/**
	 * group by frmtabid tablids
	 * 
	 * @param tableid
	 * @return
	 */
	public List<String> getTableidsByGroupByFrmtabid(String tableid);

	public List<DictTFactorPO> getDictTFactorsPk(String tableid);

	public List<DictTFactorPO> findDictTFactorVisilbeRef(Map<String, Object> map);

	public List<DictTFactorPO> getAllFactorListTreeByAppID(String appID);
	
	public List<DictTFactorPO> getAllFactorListTree();

	public List<DictTFactorPO> getLeafFactorListByTableID(String tableID);

	/**
	 * 获取各种数据类型默认的长度设置
	 * @return
	 */
	List<Map<String, String>> getAllDefaultDataLength();

	/**
	 * 获取当前系统模式（是否可进行数据长度及删除）
	 * @return
	 */
	String getEditMode();
	
	/**
	 * 获取各种数据类型默认的长度设置
	 * @return
	 */
	List<Map<String, String>> getTableBakAllColumns(String table_bak_name);
}

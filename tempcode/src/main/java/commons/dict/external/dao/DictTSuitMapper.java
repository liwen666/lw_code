package commons.dict.external.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dict.external.po.DictTSuitPO;
import com.tjhq.commons.setting.external.po.TreeNode;
/**
 * 套表
 * @author xujingsi
 *
 */
public interface DictTSuitMapper extends SuperMapper{

	
	
	/**
	 * 获取单个
	 * @param 
	 */
	public DictTSuitPO getDictTSuit(String id);
	
	public List<DictTSuitPO> getDictTSuitByAppID(String appID);
	
	/**
	 * 获取全部
	 * @param 
	 */
	public List<DictTSuitPO> getAllDictTSuit();
	
	/**
	 * 查询 by 条件    查询 by 条件   需要模糊查询like的 （加'_like'）
	 * @param Map<String, Object> map
	 */
	public List<DictTSuitPO> findDictTSuit(Map<String, Object> map);
	
	/**
	 * 查询总记录数  by 条件   查询 by 条件   需要模糊查询like的 （加'_like'）
	 * @param Map<String, Object> map
	 */
	public Long findDictTSuitCount(Map<String, Object> map);
	/**
	 * 下发业务表的同时，下发套表信息，已经存在该地区的套表，不进行二次下发
	 */
	public Integer copySuitDefineToProvince(Map<String, Object> param);

	public List<TreeNode> findDictTSuitsByAppId(Map<String, Object> dtsMap);

	public List<TreeNode> getAllDictTSuitWithModelTree(
            Map<String, Object> dtsMap);
	
	public List<TreeNode> getAllDictTSuitWithModelTree4Formula(
            Map<String, Object> dtsMap);
	
	
}

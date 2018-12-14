package commons.dict.external.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.inputcomponent.po.TreePO;
/**
 * 表code
 * @author xujingsi
 *
 */
public interface DictTModelcodeMapper extends SuperMapper{

	/**
	 * 获取单个表Byid
	 * @param 
	 */
	public DictTModelcodePO getDictTModelcode(String id);
	
	/**
	 * 获取全部表
	 * @param 
	 */
	public List<DictTModelcodePO> getAllDictTModelcode();
	
	/**
	 * 查询 by 条件   需要模糊查询like的 （加'_like'）
	 * @param Map<String, Object> map
	 */
	public List<DictTModelcodePO> findDictTModelcode(Map<String, Object> map);
	
	/**
	 * 查询业务系统下登记过的所有代码表
	 * @param appID 业务系统ID
	 * @return 业务系统下登记过的所有代码表
	 */
	public List<DictTModelcodePO> getDictTModelcodeListByAppID(String appID);
	
	/**
	 * 查询总记录数  by 条件    查询 by 条件   需要模糊查询like的 （加'_like'）
	 * @param Map<String, Object> map
	 */
	public Long findDictTModelcodeCount(Map<String, Object> map);
	
	/**
	 * 获取单个 by dbtablename
	 * @param dbtablename
	 * @return
	 */
	public DictTModelcodePO getDictTModelcodeByDBName(String dbtablename);

	/**
	 * 添加
	 * @param DictTModelcodePO
	 */
	public void insertDictTModelcode(DictTModelcodePO dictTModelcodePO);

	/**
	 * 修改
	 * @param DictTModelcodePO
	 */
	public void updateDictTModelcode(DictTModelcodePO dictTModelcodePO);

	/**
	 * 删除
	 * @param DictTModelcodePO.tableid
	 */
	public void deleteDictTModelcode(String tableid);
	/**
	 * 下发modelcode表代码,已经存在的不进行重复下发
	 * @return 
	 */
	public  Integer  copyModelCodeDefineToProvince(Map<String, Object> map);

	public DictTModelcodePO findDictTModelName(Map<String, Object> map);

	/**
	 * 获取当前where条件是否有效
	 * @param params
	 */
	int valiWhere(Map<String, String> params);
	
	/**
	 * @Description: (给用户授权)
	 * @param  设定文件
	 * @return  返回类型
	 */
	public void grantTableToUser(Map<String, Object> map);
	
	List<TreePO> getAngleCodeTabList(@Param("appID") String appID);

}

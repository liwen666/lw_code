package commons.dict.external.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.dict.external.po.DictTFactorPO;

/**
 * 列接口
 * 
 * @author xujingsi
 * 
 */
public interface IDictTFactorService {

	/**
	 * 获取列
	 * 
	 * @param columnid
	 *            列ID
	 * @return DictTFactor
	 */
	public DictTFactorPO getDictTFactorByColumnId(String columnid);

	/**
	 * 获取列<获取某个标题中的 多个列>
	 * 
	 * @param childTilte是否包含子标题
	 * @param columnid
	 *            列ID
	 * @return DictTFactor
	 */
	public List<DictTFactorPO> getDictTFactorListByColumnId(String columnid,
                                                            String tableid, boolean childTilte);

	/**
	 * 根据superID得到下级列的列表
	 * 
	 * @param superID
	 *            父ID
	 * @param tableID
	 *            表ID
	 * @return 下级列的列表
	 */
	public List<DictTFactorPO> getDictTFactorListBySuperID(String superID,
                                                           String tableID);

	/**
	 * 获取列
	 * 
	 * @param dbcolumnname
	 *            列名称
	 * @return
	 */
	public DictTFactorPO getDictTFactorByDBColumnName(String dbcolumnname,
                                                      String tableid);

	/**
	 * 获取某个表下的所有列
	 * 
	 * @param tableid
	 *            表ID
	 * @return List<DictTFactor>
	 */
	public List<DictTFactorPO> getDictTFactorsByTableId(String tableid);

	/**
	 * 获取某个表下的所有列 (树状结构)
	 * 
	 * @param tableid
	 *            表ID
	 * @return List<DictTFactor>
	 */
	public List<DictTFactorPO> getDictTFactorsByTableIdForTree(String tableid);

	/**
	 * 获取列
	 * 
	 * @param name
	 *            列中文名称
	 * @return
	 */
	public List<DictTFactorPO> getDictTFactorByName(String tableid, String name);

	/**
	 * 获取列
	 * 
	 * @param tableid
	 * @param isleaf
	 *            (0 false 1 true)
	 * @return
	 */
	public List<DictTFactorPO> getDictTFactorByTableidAndType(String tableid,
                                                              String isleaf);

	/**
	 * 获取列
	 * 
	 * @param tableid
	 * @param isvisible
	 *            (0 false 1 true)
	 * @return
	 */
	public List<DictTFactorPO> getDictTFactorByTableidAndIsvisible(
            String tableid, String isvisible);

	/**
	 * group by frmtabid tablids
	 * 
	 * @param tableid
	 * @return
	 */
	public List<String> getTableidsByGroupByFrmtabid(String tableid);

	/**
	 * 获取某个表下的所有列(带用户ID)
	 * 
	 * @param tableid
	 *            表ID
	 * @param userId
	 *            用户ID
	 * @return List<DictTFactor>
	 */
	public List<DictTFactorPO> getDictTFactorsByTableIdAndUser(String tableid,
                                                               String userId);

	/**
	 * 获取某个表下的所有列 (树状结构)(带用户ID)
	 * 
	 * @param tableid
	 *            表ID
	 * @param userId
	 *            用户ID
	 * @return List<DictTFactor>
	 */
	public List<DictTFactorPO> getDictTFactorsByTableIdAndUserForTree(
            String tableid, String userId);

	/**
	 * 根据表ID 获取列主键对象；
	 * 
	 * @param tableid
	 * @return List<DictTFactorPO>
	 */
	public List<DictTFactorPO> getDictTFactorsPk(String tableid);

	/**
	 * 获取某个表下的所有列 (以树状结构显示)(带用户ID)
	 * 
	 * @param tableid
	 *            表ID
	 * @param userId
	 *            用户ID 例如：--A(a1,a2) B(B1,B2,B3,B4) C(C1,C2,C3)
	 * @return List<DictTFactor>
	 */
	public List<DictTFactorPO> getDictTFactorsByTableIdForTreeAndChild(
            String tableId, String columnId);

	/**
	 * 获取某个表下不可以为空的列
	 * 
	 * @param tableID
	 * @return
	 */
	public List getNotNullDictTFactorsByTableId(String tableID);

	public List getVisibleRefTreeByTableId(String tableID);

	public List<DictTFactorPO> getAllFactorListTreeByAppID(String appID);
	
	public List<DictTFactorPO> getLeafFactorListByTableID(String tableID);

	/**
	 * 获取各种数据类型默认的长度设置
	 * @return
	 */
	List<Map<String, String>> getAllDefaultDataLength();

	/**
	 * 获取当前系统运行模式（是否可进行数据长度及删除）
	 * @return
	 */
	Object getEditMode();
}

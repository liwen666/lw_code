package commons.dict.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTUpdateviewPO;

/**
 * 视图关系映射
 * 
 * @author xujingsi
 * 
 */
public interface DictTUpdateviewMapper extends SuperMapper {

	/**
	 * 删除 DictTUpdateviewPO
	 * 
	 * @param guid
	 */
	public void deleteDictTUpdateview(String guid);

	/**
	 * 删除 DictTUpdateviewPO
	 * 
	 * @param tableID
	 */
	public void deleteDictTUpdateviewByTableID(String tableID);

	/**
	 * 修改 DictTUpdateviewPO
	 * 
	 * @param dictTUpdateview
	 */
	public void updateDictTUpdateview(DictTUpdateviewPO dictTUpdateview);

	/**
	 * 添加 DictTUpdateviewPO
	 * 
	 * @param dictTUpdateview
	 */
	public void insertDictTUpdateview(DictTUpdateviewPO dictTUpdateview);

	/**
	 * 根据lineid 获取单个DictTUpdateviewPO
	 * 
	 * @param guid
	 * @return
	 */
	public DictTUpdateviewPO getDictTUpdateview(String guid);

	/**
	 * 全部
	 * 
	 * @return List<DictTUpdateviewPO>
	 */
	public List<DictTUpdateviewPO> getAllDictTUpdateview();

	/**
	 * 条件查询
	 * 
	 * @param map
	 *            <字段,值>
	 * @return List<DictTUpdateviewPO>
	 */
	public List<DictTUpdateviewPO> findDictTUpdateview(Map<String, Object> map);

	
	/**
	 * 根据来源表ID取到业务对象来源表列
	 * @param 参数Map
	 * @return 来源表列集合
	 */
	public List<DictTFactorPO> getDictTFactorByFromTableID(Map<String, String> map);

}

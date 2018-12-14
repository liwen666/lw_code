package commons.dict.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
/**
 * 表对应关系设置
 * @author bizaiyi
 * Apr 20, 2015 9:45:54 AM
 * DictTModelRelaMapper.java
 */
public interface DictTModelRelaMapper extends SuperMapper{
	
	/**
	 * 添加
	 * params ：List<Map<tableId,subTableId>>
	 */
	public void insertDictTModelRela(List<Map<String, String>> params);
	
	/**
	 * 删除
	 * params ：List<Map<tableId,subTableId>>
	 */
	public void deleteDictTModelRela(List<Map<String, String>> params);
	
	/**
	 * 根据主表id，全部删除
	 */
	public void deleteDictTModelRelaByTableId(String tableId);

	/**
	 * 查
	 * param : tableId 主表id
	 * return : List<Map<主表id,子表id>>
	 */
	public List<Map<String,String>> selectDictTModelRelaList(String tableId);
	/**
	 * 查
	 * param : tableId 主表id
	 * return : List<子表id>
	 */
	public List<String> selectDictTModelRelaSubSet(String tableId);
}

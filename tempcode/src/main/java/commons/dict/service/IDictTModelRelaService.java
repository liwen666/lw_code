package commons.dict.service;

import java.util.List;
import java.util.Map;

public interface IDictTModelRelaService {
	/**
	 * 保存表关联关系
	 * @param paramMap
	 */
	public void insertDictTModelRela(List<Map<String, String>> params);

	/**
	 * 查
	 * param : tableId 主表id
	 * return : List<子表id>
	 */
	public List<String> selectDictTModelRelaSubSet(String tableId);

    
    /**
     * 根据主表id，全部删除
     */
    public void deleteDictTModelRelaByTableId(String tableId);
}

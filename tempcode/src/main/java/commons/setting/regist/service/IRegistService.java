package commons.setting.regist.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;

public interface IRegistService {
	List<Map<String, Object>> getDataList(Map<String, String> params,
                                          String queryDBName, String queryTableName) throws Exception;

	/**
	 * 保存数据
	 * @param commonGrid
	 */
	void saveData(CommonGrid commonGrid);
}

package commons.setting.regist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;

public interface IRegistMapper extends SuperMapper{
	List<Map<String, Object>> getDataList(@Param("params") Map<String, String> params,
                                          @Param("queryDBName") String queryDBName, @Param("queryTableName") String queryTableName);

	/**
	 * 新增数据
	 * @param map
	 */
	void addData(Map<String, Object> map);

	/**
	 * 更新数据
	 * @param map
	 */
	void updateData(Map<String, Object> map);

	/**
	 * 删除数据
	 * @param map
	 */
	void delData(Map<String, Object> map);
}


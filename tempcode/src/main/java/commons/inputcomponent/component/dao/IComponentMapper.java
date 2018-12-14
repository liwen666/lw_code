package commons.inputcomponent.component.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;

public interface IComponentMapper extends SuperMapper {
	/**
	 * 是否有重复数据
	 * 
	 * @Title: isHasRepeateData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param map
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return Integer 返回类型
	 * @throws
	 */
	public Integer isHasRepeateData(Map<String, Object> map) throws Exception;

	/**
	 * 根据主键取数据
	 * 
	 * @Title: getDataByPk
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param map
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List 返回类型
	 * @throws
	 */
	public List<Map<String, Object>> getDataByPk(Map<String, Object> map) throws Exception;

	/**
	 * 取超链的参数
	 * 
	 * @Title: queryForParmList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param paramId
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List 返回类型
	 * @throws
	 */
	public List<Map<String, String>> getHrefParmList(String paramId) throws Exception;

	/**
	 * 是否有重复数据整体验证
	 * 
	 * @Title: isHasRepeateData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param map
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return Integer 返回类型
	 * @throws
	 */
	public int isHasRepeateDataAll(Map<String, Object> map) throws Exception;

	/**
	 * 更新动态表数据
	 * 
	 * @param mapBuisnessObject
	 * @throws Exception
	 */
	public void insert(Map<String, Object> map) throws Exception;

	void batchInsert(@Param("dataList") List<Map<String, Object>> dataList);

	/**
	 * 更新动态表数据
	 * 
	 * @param mapBuisnessObject
	 * @throws Exception
	 */
	public void update(Map<String, Object> map) throws Exception;

	void batchUpdate(@Param("dataList") List<Map<String, Object>> dataList);

	/**
	 * 删除动态表数据
	 * 
	 * @param mapBuisnessObject
	 * @throws Exception
	 */
	public void delete(Map<String, Object> map) throws Exception;

	void batchDelete(@Param("dataList") List<Map<String, Object>> dataList);

	/**
	 * 根据物理主键获取数据
	 * 
	 * @param pkMap
	 * @return
	 */
	public Map<String, Object> getDataLogicKeyByPk(Map<String, Object> pkMap);
}

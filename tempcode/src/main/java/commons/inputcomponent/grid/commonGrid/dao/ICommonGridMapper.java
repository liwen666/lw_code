package commons.inputcomponent.grid.commonGrid.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;

public interface ICommonGridMapper extends SuperMapper {
	
	
	public List<Map<String, Object>> getGroupGridData(Map<String, Object> map)
			throws Exception;

	/***
	 * 取分页数据
	 * 
	 * @Title: getGroupGridDataCount
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param map
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List 返回类型
	 * @throws
	 */
	public int getGroupGridDataCount(Map<String, Object> map) throws Exception;
}

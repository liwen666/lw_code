package commons.setting.input.dao;

import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;


public interface BaseNumMapper extends SuperMapper{
	
	/**
	 * 新增、修改 基本数字表设置
	 * @param lists
	 * @return
	 */
	public Integer insertBaseNumTab(Map<String, Object> map);
	public Integer updateBaseNumTab(Map<String, Object> map);
	
	public Integer deleteBaseNumSubByColumnID(String columnID);
}

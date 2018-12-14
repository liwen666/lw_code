package commons.inputcomponent.form.baseform.dao;

import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;

public interface IBaseFormMapper extends SuperMapper {
	
	/**
	 * 查询Form数据
	 * @param parameterMap
	 * @return
	 */
	public Map<String, Object> getFormData(Map<String, String> parameterMap);
}

package commons.setting.input.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.setting.input.po.DictTSetFddefPO;

/**
* 类名称：IBaseNumService  
* 类描述：基本数字表设置
* 创建人：shishj
* 创建时间：Mar 20, 2014 9:33:08 AM
* @version
 */
public interface IBaseNumService {
	
	/**
	 * 新增 更新 查询条件定义
	 * @param map
	 * @return
	 */
	public String saveBaseNumTab(Map<String, Object> map);

}
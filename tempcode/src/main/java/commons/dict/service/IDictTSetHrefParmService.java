package commons.dict.service;

import java.util.List;
import java.util.Map;


import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.setting.input.po.DictTSetHrefParmPO;


/**
 * 超链接 参数  服务 
 * @author xujingsi
 *
 */
public interface IDictTSetHrefParmService {
	
	/**
	 * 查询 超链接参数
	 * @param hrefParmID
	 * @return
	 */
	public List<DictTSetHrefParmPO> findDictTSetHrefParmByHrefParmID(String hrefParmID) throws Exception;
	/**&
	 * 增加 超链接参数
	 * @param lists
	 * @return
	 */
	public void insertDictTSetHrefParmPO(List<Map<String, Object>> lists) throws Exception;
	/**
	 * 修改 超链接参数
	 * @param lists
	 * @return
	 */
	public void updateDictTSetHrefParmPO(List<Map<String, Object>> lists) throws Exception;
	/**
	 * 删除  超链接参数 by hrefID
	 * @param lists
	 * @return
	 */
	public void deleteDictTSetHrefParmPO(List<Map<String, Object>> lists) throws Exception;
	
	
	/**
	 * 删除  超链接参数 by hrefparmID
	 * @param lists
	 * @return
	 */
	public void deleteDictTSetHrefParmPObyParm(List<Map<String, Object>> lists) throws Exception;
	
	
	public Grid getDictTSetHrefParmHead(String tablename);
}







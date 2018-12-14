package commons.dict.dao;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dict.external.po.DictTSuitPO;
/**
 * 套表
 * @author xujingsi
 *
 */
public interface DictTSuitSelfMapper extends SuperMapper{

	
	/**
	 * 添加
	 * @param dictTSuit
	 */
	public void insertDictTSuit(DictTSuitPO dictTSuit);
	
	/**
	 * 修改
	 * @param dictTSuit
	 */
	public void updateDictTSuit(DictTSuitPO dictTSuit);
	
	/**
	 * 删除
	 * @param String suitid
	 */
	public void deleteDictTSuit(String suitid);
	
}

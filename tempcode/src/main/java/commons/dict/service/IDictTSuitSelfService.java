package commons.dict.service;

import com.tjhq.commons.dict.external.po.DictTSuitPO;
import com.tjhq.commons.inputcomponent.po.Grid;



/**
 * 套表接口
 * @author xujingsi
 *
 */
public interface IDictTSuitSelfService {


	
	/**
	 * 添加
	 * @param dictTSuit
	 */
	public void insertDictTSuit(DictTSuitPO dictTSuit) throws Exception ;
	
	/**
	 * 修改
	 * @param dictTSuit
	 */
	public void updateDictTSuit(DictTSuitPO dictTSuit) throws Exception ;
	
	/**
	 * 删除
	 * @param String suitid
	 */
	public void deleteDictTSuit(String suitid) throws Exception ;
	
	
	/**
	 * 获取DictTSuit grid 表头 
	 * @param tableid
	 * @return
	 */
	public Grid getDictTSuitHead(String tableid);
	
}

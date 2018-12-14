package commons.dict.service;


import java.util.List;
import java.util.Map;

import com.tjhq.commons.dict.external.po.DictTDefaultcolPO;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;



/**
 * 表默认列管理（缺省列）接口
 * @author xujingsi
 *
 */
public interface IDictTDefaultcolService {


	/**
	 * 添加
	 * @param DictTDefaultcolPO
	 */
	public void insertDictTDefaultcol(DictTDefaultcolPO dictTDefaultcol) throws Exception;
	
	/**
	 * 修改
	 * @param DictTDefaultcolPO
	 */
	public void updateDictTDefaultcol(DictTDefaultcolPO dictTDefaultcol) throws Exception;
	
	/**
	 * 删除
	 * @param String dealid
	 */
	public void deleteDictTDefaultcol(DictTDefaultcolPO dictTDefaultcol) throws Exception;
	
	
	/**
	 * ***********************************************************************************
	 */
	
	
	
	/**
	 * 获取 by dealid 
	 * @param String dealid
	 */
	public List<DictTDefaultcolPO> getDictTDefaultcols(String dealid) throws Exception;
	
	public List<DictTDefaultcolPO> getDictTDefaultcols4Show(String dealid) throws Exception;
	
	/**
	 * 获取全部
	 * @return List<DictTDefaultcol>
	 */
	public List<DictTDefaultcolPO> getAllDictTDefaultcol() throws Exception;
	
	
	/**
	 * 获取DictTDefaultcol grid 表头 
	 * @param tableid
	 * @param appid 系统id
	 * @return
	 */
	public Grid getDictTDefaultcolHead(String tableid, String appid);
	
	public List<Column> getDictTDefaultcolColumnlist();
	
	public void saveDictTDefaultcolData(Table table) throws Exception;

	/**
	 * 获取已经设置的缺省列的数量
	 * @param dealID
	 * @return
	 */
	public String getDefaultcolCountByDeal(String dealID, String tableId) throws Exception;
	
}

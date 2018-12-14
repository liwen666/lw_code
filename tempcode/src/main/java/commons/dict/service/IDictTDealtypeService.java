package commons.dict.service;


import java.util.List;

import com.tjhq.commons.dict.external.po.DictTDealtypePO;


/**
 * 处理类型表接口
 * @author xujingsi
 *
 */

public interface IDictTDealtypeService {

	/**
	 * 添加
	 * @param DictTDealtypePO
	 */
	public void insertDictTDealtype(DictTDealtypePO dictTDealtype) throws Exception;
	
	/**
	 * 修改
	 * @param DictTDealtypePO
	 */
	public void updateDictTDealtype(DictTDealtypePO dictTDealtype) throws Exception;
	
	/**
	 * 删除
	 * @param String dealid
	 */
	public void deleteDictTDealtype(String dealid) throws Exception;
	
	
	/**
	 * ***********************************************************************************
	 */
	
	
	
	/**
	 * 
	 * @param dealID
	 * @param appID
	 * @return
	 * @throws Exception
	 */
	public DictTDealtypePO getDictTDealtype(String dealID, String appID)
			throws Exception;
	
	/**
	 * 获取全部
	 * @return List<DictTDealtype>
	 */
	public List<DictTDealtypePO> getAllDictTDealtype() throws Exception;
	

	/**
	 * DictTDealtypePO  simple data 有自定义数据  父节点
	 * @param appid
	 * @return
	 */
	public List<DictTDealtypePO> findDictTDealtypeListForZTree() throws Exception;
}

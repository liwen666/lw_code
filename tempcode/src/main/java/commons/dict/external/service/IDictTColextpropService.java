package commons.dict.external.service;


import java.util.List;

import com.tjhq.commons.dict.external.po.DictTColextpropPO;


/**
 * 列拓展属性接口
 * @author xujingsi
 *
 */
public interface IDictTColextpropService {

	/**
	 * 获取 by extid 
	 * @param String extid
	 */
	public DictTColextpropPO getDictTColextprop(String extid);
	
	/**
	 * 获取全部
	 * @return List<DictTColextprop>
	 */
	public List<DictTColextpropPO> getAllDictTColextprop();
	
	/**
	 * 获取全部 by appid
	 * @param appid
	 * @return
	 */
	public List<DictTColextpropPO> getAllDictTColextpropByAppid(String appid);
	
	
   
}

package commons.dict.external.service;


import java.util.List;

import com.tjhq.commons.dict.external.po.DictTTabextpropPO;
import com.tjhq.commons.inputcomponent.po.Grid;


/**
 * 表拓展属性接口
 * @author xujingsi
 *
 */
public interface IDictTTabextpropService {

	/**
	 * 获取 by extid 
	 * @param String extid
	 */
	public DictTTabextpropPO getDictTTabextprop(String extid);
	
	/**
	 * 获取全部
	 * @return List<DictTTabextprop>
	 */
	public List<DictTTabextpropPO> getAllDictTTabextprop();
	
	/**
	 * 获取全部 by appid
	 * @param appid
	 * @return
	 */
	public List<DictTTabextpropPO> getAllDictTTabextpropByAppid(String appid);
	
	
	/**
	 * 获取DictTTabextprop grid 表头 
	 * @param tableid
	 * @return
	 */
	public Grid getDictTTabextpropHead(String tableid);
}

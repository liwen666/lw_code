package commons.setting.external.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.setting.external.po.ProjTabPO;
import com.tjhq.commons.setting.external.po.RECDetailPO;
import com.tjhq.commons.setting.external.po.RECPO;

/**
 * 项目库-设置接口
 * 说明：项目报表设置、项目编码设置、绩效指标设置、附件设置等接口
 * @author liangdb
 * @CreateDate 2014-2-21
 * @version 1.0
 */
public interface IExpProjectSetInfoService {
	/**
	 * 录入表列表显示
	 * @Title: getProjTabList
	 * @Description: 根据对象ID和对象类型查询录入表列表
	 * @date: 2014-1-9
	 * @param  map(objectid,typeflag,spfid)
	 * @return List<ProjTabEntity>
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	public List<ProjTabPO> getProjTabList(Map<String, String> map);

	/**
	 * 单页面整体设置
	 * @Title: getSetRECList
	 * @Description: 单页面整体设置 map(objectid,tableid) 
	 * @param  map(objectid,tableid)
	 * @date: 2014-1-9
	 * @return List<SetREC>
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	public List<RECPO> getSetRECList(Map<String, String> map);


	/**
	 * 单页面明细设置
	 * @Title: getSetRECDetailList
	 * @Description: 单页面明细设置
	 * @param recid 单页面整体设置主键
	 * @date: 2014-1-9
	 * @return List<SetRECDetail>
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	public Map<String, List<RECDetailPO>> getSetRECDetailList(String recid);
	
	
}

package commons.setting.external.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.setting.external.po.ProjTabPO;
import com.tjhq.commons.setting.external.po.RECDetailPO;
import com.tjhq.commons.setting.external.po.RECPO;

public interface ExpProjectSetInfoMapper extends SuperMapper{


	/**
	 * 单页面整体设置
	 * @Title: getSetRECList
	 * @Description: 单页面整体设置
	 * @date: 2014-1-9
	 * @return List<SetREC>
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	public List<RECPO> getSetRECList(Map<String, String> map);
	/**
	 * 录入表列表显示
	 * @Title: getProjTabList
	 * @Description: 根据对象ID和对象类型查询录入表列表
	 * @date: 2014-1-9
	 * @return List<ProjTabEntity>
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	public List<ProjTabPO> getProjTabList(Map<String, String> map);
	/**
	 * 获取专项资金本身设置的报表数
	 * @param map
	 * @return
	 */
	public int getTabCount(Map<String, String> map);
	/**
	 * 单页面明细设置
	 * @Title: getSetRECDetailList
	 * @Description: 单页面明细设置
	 * @param superid
	 *            单页面整体设置主键
	 * @date: 2014-1-9
	 * @return List<SetRECDetail>
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 * @Select("select * from dict_t_setrecdetail t where t.superid =#{superid}")
	 */
	public List<RECDetailPO> getSetRECDetailList(Map<String, String> map);
	
	/**
	 * 获取项目类别编码
	 * @param objectid
	 * @return
	 */
	public String getProjTypeId(String objectid);
}

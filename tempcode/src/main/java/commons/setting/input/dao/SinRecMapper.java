package commons.setting.input.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.setting.external.po.RECDetailPO;
import com.tjhq.commons.setting.external.po.RECPO;


public interface SinRecMapper extends SuperMapper{
	/**
	 * 获取报表明细主表信息
	 * @param map
	 * @return
	 */
	public List<RECPO> getSetRECList(String tableid);
	/**
	 * 单记录form明细设置
	 */
	public List<RECDetailPO> getSinRECDetailList(Map<String, String> map);

	/**
	 * 表数据查询
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getTableData(@Param("tableID") String tableID);
	/**
	 * 保存主表
	 * @param map
	 */
	public void insertSetREC(Map<String, Object> map);
	/**
	 * 更新明细表设置
	 * @param map
	 */
	public void updateSetREC(Map<String, Object> map);
	/**
	 * 保存明细表设置
	 * @param map
	 */
	public void insertSetRECDetail(Map<String, Object> map);

	/**
	 * 删除明细表设置
	 */
	public void deleteSetRECDetail(Map<String, Object> map);
	/**
	 * 更新明细表设置
	 */
	public void updateSetRECDetail(Map<String, Object> map);
}

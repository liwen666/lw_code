package commons.setting.input.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.setting.external.po.RECDetailPO;
import com.tjhq.commons.setting.external.po.RECPO;
/**
* 类描述：单记录表设置
 */
public interface ISinRecService {
	/**
	 * 单记录明细表查询
	 * @param tableID
	 * @return
	 */
	public List<Map<String, Object>> selectSingleRecord(String tableID);
	/**
	 * 获取报表明细主表信息
	 * @param map
	 * @return
	 */
	public List<RECPO> getSetRECList(String tableId);
	/**
	 * 明细表设置查询
	 * @param recId
	 * @return
	 */
	public Map<String, List<RECDetailPO>> getSetRECDetailList(String recId);
	/**
	 * 更新报表设置表
	 */
	public void updateSetREC(Map<String, Object> map) throws Exception;
    /**
     * 保存单记录表设置信息
     */
    public String saveSingleRecord(Map<String, Object> map) throws Exception;
	/**
	 * 保存主表
	 * @param map
	 */
	public void saveSetREC(Map<String, Object> map) throws Exception;
	/**
	 * 保存单页面明细设置
	 */
	public void saveSetRECDetail(Map<String, Object> map) throws Exception;

	/**
	 * 删除单页面明细设置
	 */
	public void deleteSetRECDetail(Map<String, Object> map) throws Exception;

	/**
	 * 更新单页面明细设置
	 */
	public void updateSetRECDetail(Map<String, Object> map) throws Exception;
}
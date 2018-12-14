package commons.setting.input.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.setting.input.po.DictTSetFixPO;

public interface FixedMapper extends SuperMapper{

	/**
	 * 插入 整表设置
	 * @param fixed
	 * @return
	 */
	public Integer insertFixedWhole(DictTSetFixPO fixed);
	
	/**
	 * 更新 整表设置
	 * @param fixed
	 * @return
	 */
	public Integer updateFixedWhole(DictTSetFixPO fixed);
	/**
	 * 查询 系统下 单位
	 * @param appID
	 * @return
	 */
	public String selectAgencyTableName(@Param("appID") String appID);
	
	public List<Map<String,Object>> selectAgencyTableData(@Param("dbTableName") String dbTableName);

	public void updateSynStatus(@Param("dbTableName") String dbTableName);
	/**
	 * 初始化固定行列表   插入数据
	 * @param list
	 */
	public void setFixData(Map<String, Object> params);
	/**
	 * 根据tableID查询出代码表的COLUMNID
	 */
	public List<String> getLvlNameColByTableID(String tableID);
	/**
	 * 根据colI查询出代码表的表名
	 * @param colID
	 * @return
	 */
	public Map<String, String> getCodeNameBycolID(String colID);
	/**.160523 清除固定行列表 浮动表的垃圾数据
	 * @param tableID 表ID
	 * @throws
	 */
	void clearFloatErrData(String tableID);
	/**.获取最长fdcode列      1  2   3
	 * @param map
	 * @return 1,2,3
	 * @throws
	 */
	String getMaxFdCol(Map<String, Object> map);
}

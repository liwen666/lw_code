package commons.setting.input.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;

public interface GeneralMapper extends SuperMapper{
	
	/**
	 * 插入 更新 删除 视角设置
	 * @param lists
	 * @return
	 */
	public Integer insertAngleView(List<Map<String, Object>> lists);

	public Integer updateAngleView(List<Map<String, Object>> lists);

	public Integer deleteAngleView(List<Map<String, Object>> lists);

	/**
	 * 插入 更新 删除 分组设置
	 * @param lists
	 * @return
	 */
	public Integer insertGroup(List<Map<String, Object>> lists);

	public Integer updateGroup(List<Map<String, Object>> lists);

	public Integer deleteGroup(List<Map<String, Object>> lists);
	/**
	 * 插入 更新 删除 排序设置
	 * @param lists
	 * @return
	 */ 
	public Integer insertSort(List<Map<String, Object>> lists);
	
	public Integer updateSort(List<Map<String, Object>> lists);
	
	public Integer deleteSort(List<Map<String, Object>> lists);
	
	/**
	 * 插入 更新 删除 引用列关系定义
	 * @param lists
	 * @return
	 */ 
	public Integer insertRefrela(List<Map<String, Object>> lists);
	
	public Integer updateRefrela(List<Map<String, Object>> lists);
	
	public Integer deleteRefrela(List<Map<String, Object>> lists);

	/**
	 * 引用列关系定义 （插入 删除 关联物理表）
	 * @param lists
	 * @param relaDbTab 物理表名称
	 * @return
	 */
	public Integer insertRefrelaDetail(@Param("list") List<Map<String, Object>> lists, @Param("relaDbTab") String relaDbTab);
	public Integer deleteRefrelaDetail(@Param("list") List<Map<String, Object>> lists, @Param("relaDbTab") String relaDbTab);
	
	
	/**
	 * 插入 更新 删除 查询（分析）引用定义
	 * @param lists
	 * @return
	 */  
	public Integer insertAnaly(List<Map<String, Object>> lists);
	
	public Integer updateAnaly(List<Map<String, Object>> lists);
	
	public Integer deleteAnaly(List<Map<String, Object>> lists);
	//级联删除
	public Integer deleteAnalyCascHref(List<Map<String, Object>> lists);
	
	/**
	 * 插入 更新 删除 查询 引用 参数设置
	 * @param lists
	 * @return
	 */  
	public Integer insertAnalyDetail(List<Map<String, Object>> lists);
	
	public Integer updateAnalyDetail(List<Map<String, Object>> lists);
	
	public Integer deleteAnalyDetail(List<Map<String, Object>> lists);
	
	/**
	 * 查询条件设置 主表
	 * @param lists
	 * @return
	 */
	public Integer insertQueryd(Map<String, Object> map);
	public Integer updateQueryd(Map<String, Object> map);
	
	/**
	 * 查询条件设置 子表
	 * @param lists
	 * @return
	 */
	public Integer insertQuerydDet(List<Map<String, Object>> lists);
	
	public Integer updateQuerydDet(List<Map<String, Object>> lists);
	
	public Integer deleteQuerydDet(List<Map<String, Object>> lists);
	
	/**
	 * 基本数字表设置 子表
	 * @param lists
	 * @return
	 */
	public Integer insertBaseNumSub(List<Map<String, Object>> lists);
	
	public Integer updateBaseNumSub(List<Map<String, Object>> lists);
	
	public Integer deleteBaseNumSub(List<Map<String, Object>> lists);
	/**
	 * 根据表名 查询代码表数据
	 * @param dbTableName
	 * @return
	 */
	public List<Map<String,Object>> queryCodeTable(@Param("dbTableName") String dbTableName);
	/**
	 * 查询表中是否有重复关联关系名称的记录
	 */
	public int findRepeatNameCount(Map<String, String> map);

	public int findOtherSameRelName(Map<String, Object> map);

	public void deleteRefrelaData(@Param("relaDbTabName") String relaDbTabName, @Param("relaId") String relaId);

	/**
	 * 删除某列对应的所有关系列（TRee）
	 * @author ZK
	 * @date 16-11-30
	 * @param relaDbTab
	 * @param condDataID
	 */
	void deleteAllRefrelaDetail(@Param("relaDbTab") String relaDbTab, @Param("condDataID") String condDataID);
	
	/**
	 * 删除某列对应的所有关系列（TRee）
	 * @author ZK
	 * @date 16-11-30
	 * @param params
	 */
	public void addRefrelaDetail(Map<String, Object> params);
}

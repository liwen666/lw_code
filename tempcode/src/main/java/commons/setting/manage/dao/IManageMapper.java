package commons.setting.manage.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.setting.input.po.TreeNode;

public interface IManageMapper  extends SuperMapper{
	public List<Map<String, Object>> getPartitionDataList();
	public List<Map<String, Object>> getInitPartitionDataList(String year);
	public List<TreeNode> getDistinctTree();
	public void savaPartition(Map<String, Object> map);
	/**
     * 根据appid keyid typeid 查询公共信息表
     * @param params
     * @return
     */
    public List<Map<String, String>> queryDictTPublic(Map<String, String> params);
    
	/**
	 * 新增分区
	 * SYS_P_ADDPARTITION_ALL
	 */
	public void addPartitionAll();
	/**
	 * 初始化分区（把源分区的数据刷到目标分区）
	 * @param map
	 */
	public void initPartition(Map<String, Object> map);
    /**
     * 查询所有分区年度
     */
    public List<TreeNode> getSysYears();
	/**
	 * 刷AGENCY物化视图
	 */
	public void mviewRefresh();
	/**
	 * 刷CODE_M_DISTRICT,CODE_M_DEPARTMENT物化视图
	 */
	public void mviewRefreshDisDept();
	
}

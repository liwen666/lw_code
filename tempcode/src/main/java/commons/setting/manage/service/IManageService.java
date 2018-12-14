package commons.setting.manage.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.setting.input.po.TreeNode;

public interface IManageService {
    /**
     * 根据appid keyid typeid 查询公共信息表
     * @param params
     * @return
     */
    public List<Map<String, String>> queryDictTPublic(Map<String, String> params);
	/**
	 * 获取表定义
	 * @param curTab 标签页分类
	 * @return
	 */
	Table setTableDefine(String curTab) throws Exception;
	/**
	 * 获取分区表数据
	 * @return
	 */
	List<Map<String, Object>> getPartitionDataList() throws Exception;
	/**
	 * 获取地区树
	 * @return
	 */
	List<TreeNode> getDistinctTree() throws Exception;
	/**
	 * 保存分区信息到分区表，并刷分区
	 * @param map
	 */
	void savaPartition(Map<String, Object> map) throws Exception;
	/**
	 * 获取初始化分区表数据
	 * @param gridFlag（左表或右表）
	 * @param province 默认分区
	 * @return
	 */
	List<Map<String, Object>> getInitPartitionDataList(String gridFlag, String province, String year) throws Exception;
	/**
	 * 初始化分区（把源分区当前年的数据刷到目标分区当前年）
	 * @param map
	 */
	void initPartition(Map<String, Object> map) throws Exception;
	/**
	 * 查询所有分区年度
	 */
    List<TreeNode> getSysYears();
}

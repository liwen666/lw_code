package commons.secu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.secu.po.ColumnLimitPO;
import com.tjhq.commons.setting.input.po.TreeNode;
/**
 * 列限制配置
 */
public interface ColumnLimitMapper  extends SuperMapper {

	/**
     * 查询套表
     */
    public List<Map<String, String>> querySuitModel(Map<String, String> map);
    
    /**
     * 查询树
     */
    public List<Map<String, String>> queryFactorColumnLimitDetail(Map<String, String> map);
    
    /**
     * 查询树
     */
    public List<Map<String, String>> queryFactorTree(Map<String, String> map);
    
    /**
	 * 查询
	 */
	public List<Map<String, Object>> queryData1(Map<String, Object> map);
	
	/**
	 * 查询
	 */
	public List<Map<String, Object>> queryData2(Map<String, Object> map);
	
	/**
     * 删除
     */
    public int deleteColumnLimit_byColumnid(List<Map<String, Object>> lists);
    
    /**
     * 修改
     */
    public int updateColumnLimit(ColumnLimitPO columnLimitPO);
    
    /**
     * 新增
     */
    public int insertColumnLimit(ColumnLimitPO columnLimitPO);
    
    /**
     * 删除
     */
    public int deleteColumnLimitDetail(ColumnLimitPO columnLimitPO);
    
    /**
     * 查询
     */
    public int queryColumnLimit_Check(ColumnLimitPO columnLimitPO);
    
    /**
     * 新增
     */
    public int insertColumnLimitDetail(ColumnLimitPO columnLimitPO);
    
    /**
     * 删除
     */
    public int deleteColumnLimitDetail_byColumnid(List<Map<String, Object>> lists);
    
    /**
     * 删除
     */
    public int deleteColumnLimitDetail_byGuid(List<Map<String, Object>> lists);
    
    /**
     * 修改
     */
    public int updateColumnLimitDetail(List<Map<String, Object>> lists);
    
    /**
     * 新增
     */
    public int insertColumnLimitDetail_list(List<Map<String, Object>> lists);
    
    /**
	 * 根据 表ID 获取 列限制信息
	 * @return
	 */
	public List<ColumnLimitPO> queryColumnLimitList(@Param("roleid") String roleid, @Param("tableid") String tableid);
	
	/**
	 * 查询角色
	 * @return
	 */
	public List<Map<String, String>> queryRolesByUserid(@Param("userID") String userID);
	
	/**
     * 查询
     */
    public int querySql_Check(@Param("sql") String sql);
    
    public List<TreeNode> getRefrelaDbTableTree(Map<String, Object> map);//获取代码表数据
	
}

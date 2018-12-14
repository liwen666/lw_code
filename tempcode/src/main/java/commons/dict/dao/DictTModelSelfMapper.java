package commons.dict.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.setting.input.po.TreeNode;
/**
 * 表
 * @author xujingsi
 *
 */
public interface DictTModelSelfMapper extends SuperMapper{

	
	/**
	 * 添加
	 * @param dictTModel
	 */
	public void insertDictTModel(DictTModelPO dictTModel);
	
	/**
	 * 修改
	 * @param dictTModel
	 */
	public void updateDictTModel(DictTModelPO dictTModel);
	
	/**
	 * 删除
	 * @param String tableid
	 */
	public void deleteDictTModel(String tableid);
	/**
	 * 判断表是否被复制过
	 * @param param
	 */
	public List<Map<String,String>> checkTableIsCopy(Map param);

	public void copyModelDefineToYear(Map param);
	public Integer copyModelDefineToProvince(Map param);
	/**
	 * 根据tableid 获得该表名字
	 * @param map
	 */
	public List<DictTModelPO> getDictTModelDBName(Map<String, String> map);
	
	/**
	 * 复制创建业务表
	 * @param sourceTableID   源表ID
	 * @param newTableName    新表中文名 
	 * @param newTableDBName  新表物理表名
	 * @param dealType        处理类型   
	 * @param suitID          套表ID
	 */
	public void replicateTable(@Param("sourceTableID") String sourceTableID,
                               @Param("newTableName") String newTableName,
                               @Param("newTableDBName") String newTableDBName,
                               @Param("dealType") String dealType, @Param("suitID") String suitID);

	/**
	 * 更新复制创建表是否任务表字段
	 * @param isTask
	 */
	void updateIsTaskByDBtableName(@Param("isTask") String isTask,
                                   @Param("dbTableName") String dbTableName);
	
	List<TreeNode> getSubAreas(@Param("agencyID") String agencyID);
}

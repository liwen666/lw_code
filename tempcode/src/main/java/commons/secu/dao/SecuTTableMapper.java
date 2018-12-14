package commons.secu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.secu.po.SecuTTablePO;
import com.tjhq.commons.secu.po.TreeNode;

public interface SecuTTableMapper  extends SuperMapper{
	public List<SecuTTablePO> getSecuTtableLimitList(Map<String, Object> params);
	public List<SecuTTablePO> getSecuTtableLimitListViaSuitIDs(Map<String, Object> params);
	public List<Map<String, String>> getTableInputLimitListBySuitId(String objId);
	public List<Map<String, String>> getTableInputLimitListByTableId(
            String objId);
	public List<TreeNode> getAcctItemTreeData(Map param);
	public List<Map<String, String>> getTableInputUnlimitListBySuitId(
            String objId);
	public List<Map<String, String>> getTableInputLimitedListBySuitId(
            String objId);
	public List<SecuTTablePO> getTableSecuList(Map param);
	public void updateSecuTTableData(Map<String, String> editData);
	public void insertSecuTTableData(Map<String, String> addData);
	public List<DictTModelPO> getVisiableTableList(Map param);
	public List<Map<String, String>> getSuitIsHiddenBySuitId(Map param);
	public List<DictTModelPO> getTableListWithNoSuitSecu(Map param);
	public List<DictTModelPO> getTableListWithSuitSecu(Map param);
	public List<Map<String, String>> getTableDatabaseNameBy(Map param);
	public void deleteTableSecuLimit(Map param);
	public void verifyTableRowLimit(Map<String, String> param);
	public void verifyTableCellLimit(Map<String, String> param);
	public String getTableSecuWithDataBaseCache(
            Map<String, String> param);
	public String isContrTableSecu(String taskID);
	
	//--Add new method of 2016-08-31
	SecuTTablePO getTableSecu(@Param("userID") String userID, @Param("tableID") String tableID);
}

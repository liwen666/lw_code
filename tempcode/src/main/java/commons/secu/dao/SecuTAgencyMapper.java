package commons.secu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.secu.po.TreeNode;
import com.tjhq.exp.task.po.TreeNodeForTask;

public interface SecuTAgencyMapper  extends SuperMapper{

	List<Map<String, String>> getAgencyListByUserId(String userId);

	List<TreeNode> getSelectedRoleToAgency(Map param);

	List<TreeNode> getUnSelectRoleToAgency(Map param);

	void addSelectedAgencyToRole(Map selectAgency);

	void removeSelectedAgencyToRole(Map param);

	List<Map<String,String>> getRoleIdListByUserId(String userId);

	List<Map<String, String>> getAgencyListByRoleId(Map param);
	
	List<Map<String, Object>> getUserInfoByUserId(Map param);
	List<Map<String, Object>> getUserInfoByUserIdForBIM(Map param);
	List<Map<String, String>> getAgencyListByUserIdForTaskAssignForDW(Map param);
	List<Map<String, String>> getAgencyListByUserIdForTaskAssignForDWAtBIM(Map param);
	List<Map<String, Object>> getAgencyListByUserIdForTaskAssignForCZ(Map param);
	List<Map<String,Object>> getAgencyListByUserIdForTaskAssignForCZAtBIM(Map param);
	List<TreeNodeForTask> getAgencyListByUserIdForDW(Map param);
	List<TreeNodeForTask> getAgencyListByUserIdForDWAtBIM(Map param);
	List<TreeNodeForTask> getAgencyListByUserIdForCZ(Map param);
	List<Map<String, Object>> getSubAgencyListToAssign(Map param);

	List<Map<String, String>> getRoleListByOrgId(Map param);

	List<Map<String, String>> getAgencyListByRoleIdForDW(Map param);

	List<TreeNodeForTask> getCanCheckAgencyListByUserIdForDW(Map param);
	List<TreeNodeForTask> getCanCheckAgencyListByUserIdForDWAtBIM(Map param);
	List<TreeNodeForTask> getCanCheckAgencyListByUserIdForCZ(Map param);

	List<TreeNodeForTask> getAgencyListByUserIdForCZAtBIM(Map param);

	List<TreeNodeForTask> getAgencyListForCZAtBIM(Map param);

	List<TreeNodeForTask> getAgencyListForDWAtBIM(Map param);

	List<Map<String, String>> getUserList(Map param);
	
	public void updateSql(@Param("sql") String sql);

}

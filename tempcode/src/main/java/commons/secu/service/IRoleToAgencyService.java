package commons.secu.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.secu.po.SecuTColPO;
import com.tjhq.commons.secu.po.SecuTRowPO;
import com.tjhq.commons.secu.po.SecuTTablePO;
import com.tjhq.commons.secu.po.TreeNode;

public interface IRoleToAgencyService {

	public Grid getTableListHead();

	public Grid getTableColSecuListHead();

	public Grid getTableRowSecuListHead();

	public Map<String, List<TreeNode>> getRoleToAgencyList(String roleId, String appId, String manType);

	public void addSelectedAgencyToRole(List<Map> selectAgencyList, String roleId, String appId, String mantype) throws Exception;

	public void removeSelectedAgencyToRole(List<Map> unselectAgencyList,
                                           String roleId, String appId, String manType) throws Exception;

	public List<SecuTTablePO> getTableSecuList(String roleId, String appId);

	public List<SecuTColPO> getColLimitListByTableId(String tableId, String roleId);

	public List<SecuTRowPO> getRowLimitListByTableId(String tableId,
                                                     String roleId);

	public Grid getTableColListHead();

	public void saveTableRowLimit(Map<String, String> param) throws Exception;

	public void updateTableRowLimit(Map<String, String> param) throws Exception;

	public void cancelTableRowLimitLimit(Map<String, String> param) throws Exception;

	public void saveTableSecuLimit(
            List<Map<String, String>> editTableColLimitList,
            List<Map<String, String>> addTableSecuLimitList,
            List<Map<String, String>> editTableSecuLimitList, String roleId) throws Exception ;

	public void deleteTableSecuLimit(String roleId, String appId,
                                     String tableId, String isSuit) throws Exception;

	public void verifyTableRowLimit(Map<String, String> param);

	public List<Map<String, String>> getRoleTreeData(String appId, String userId, String roleType);

	public Object transferTableRowLimit(String tableId, String showWhere);

	public Table setTableSecuDefine();

	public Table setColSecuDefine();

	public Table setRowSecuSecuDefine();

	public Table setColTableDefine();

	public List<Map<String, String>> getUserTreeData();
	/**
	 * 复制权限
	 * @param appID
	 * @param fromRoleID
	 * @param toRoleIDs
	 * @throws ServiceException
	 */
	public void copy(String appID, String fromRoleID, String toRoleIDs) throws ServiceException;
}

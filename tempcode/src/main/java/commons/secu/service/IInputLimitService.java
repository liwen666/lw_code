package commons.secu.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.secu.po.SecuTLimitcolPO;
import com.tjhq.commons.secu.po.SecuTLimittabPO;
import com.tjhq.commons.secu.po.TreeNode;

public interface IInputLimitService {

	public Grid getInputLimitTableListHead();

	public List<Map<String,String>> getTableInputLimitListBySuitId(String objId);

	public List<Map<String,String>> getTableInputLimitListByTableId(
            String objId);

	public Grid getInputLimitItemListHead();

	public List<SecuTLimittabPO> getInputLimitItemList(String tableId);

	public List<TreeNode> getAcctItemTreeData(String tableId);

	public void deleteAcctItemTreeData(Map param) throws Exception;

	public void saveAcctItemTreeData(List editDatasList, List addDatasList) throws Exception;

	public List<Map<String,String>> getTableInputUnlimitListBySuitId(
            String objId);

	public List<Map<String,String>> getTableInputLimitedListBySuitId(
            String objId);

	public void cancelInputLimit(String tableId) throws Exception;

	public SecuTLimitcolPO queryInputColLimit(String tableId, String columnId);

	public void saveColLimitData(String tableId, String columnId,
                                 String showWhere, String sqlWhere, String existFlag) throws Exception;

	public void clearLimitWindow(String tableId, String columnId) throws Exception;

	public Table getTableColListHead();

	public void verifyTableCellLimit(Map<String, String> param);

	public Table getInputLimitGrid();

	public Table getInputLimitItemGrid(); 


}

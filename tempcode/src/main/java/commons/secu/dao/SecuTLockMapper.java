package commons.secu.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.secu.po.SecuTLockPO;

public interface SecuTLockMapper extends SuperMapper{

	public List<SecuTLockPO> gettableLockedListByParams(Map<String, String> params);
	
	public List<SecuTLockPO> getAllDeptLockList(String appId);

	public List<SecuTLockPO> getLockedDeptLockList(String appId);

	public List<SecuTLockPO> getUnlockDeptLockList(String appId);

	public void addLockForDept(Map lockData);

	public void dropLockForDept(Map param);

	public List<SecuTLockPO> getAllAgencyLockList(Map param);

	public List<SecuTLockPO> getLockedAgencyLockList(Map param);

	public List<SecuTLockPO> getUnlockAgencyLockList(Map param);

	public void addLockForAgency(Map lockData);

	public void dropLockForAgency(Map param);

	public List<SecuTLockPO> getAllAcctItemLockList(Map param);

	public List<SecuTLockPO> getLockedAcctItemLockList(Map param);

	public List<SecuTLockPO> getUnlockAcctItemLockList(Map param);

	public void addLockForAcctItem(Map lockData);

	public void dropLockForAcctItem(Map param);

	public List<SecuTLockPO> getAllAppLockList();

	public List<SecuTLockPO> getLockedAppLockList();

	public List<SecuTLockPO> getUnlockAppLockList();

	public void addLockForApp(Map lockData);

	public void dropLockForApp(Map param);

	public List<SecuTLockPO> queryTableLockListByTableId(Map<String, String> param);

	public List<SecuTLockPO> queryTableLockListBySuitId(Map<String, String> param);

	public List<SecuTLockPO> queryTableLockedListBySuitId(
            Map<String, String> param);

	public List<SecuTLockPO> queryTableUnLockListBySuitId(
            Map<String, String> param);

	public void addLockForTable(Map lockData);

	public void dropLockForTable(Map param);

	public List<SecuTLockPO>  getAgencySuitLockList(Map<String, String> param);

	public List<SecuTLockPO>  getAgencySuitUnLockList(Map<String, String> param);

	public List<SecuTLockPO>  getAgencySuitLockedList(Map<String, String> param);

	public void dropLockForAgencySuit(Map param);

	public void addLockForAgencySuit(Map lockData);
	
	public List<SecuTLockPO>  getDeptSuitLockList(Map<String, String> param);

	public List<SecuTLockPO>  getDeptSuitUnLockList(Map<String, String> param);

	public List<SecuTLockPO>  getDeptSuitLockedList(Map<String, String> param);

	public void dropLockForDeptSuit(Map param);

	public void addLockForDeptSuit(Map lockData);

	public List<SecuTLockPO> getAcctItemLockList(String appId);

	
	
}

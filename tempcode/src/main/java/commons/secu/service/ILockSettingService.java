package commons.secu.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.secu.po.SecuTLockPO;


/**
 * 锁设置接口定义
 * @version 1.0
 * @CreateDate 2014/1/8
 * @author zushuxin
 *
 */
public interface ILockSettingService {
    /**
     * 通过业务系统ID 和查询条件获得司局锁列表
     * @param tableID --表ID
     * @param userId --用户ID
     * @param cascade --级联查询此表的行权限和列权限
     * @return SecuTTable --表权限实体类
     */
	public List<SecuTLockPO> getDeptLockList(String appId, String queryParam);

	public Table getDeptLockListHead();

	public void addLockForDept(List<Map> lockDatas, String appId) throws Exception;

	public void dropLockForDept(List<Map> unlockDatas, String appId) throws Exception;

	public Table getAgencyLockListHead();

	public List<SecuTLockPO> getAgencyLockList(String appId, String queryParam);

	public void addLockForAgency(List<Map> lockDatas, String appId) throws Exception;

	public void dropLockForAgency(List<Map> unlockDatas, String appId) throws Exception;

	public Table getAcctItemLockListHead();

	public List<SecuTLockPO> getAcctItemLockList(String appId, String queryParam);

	public void addLockForAcctItem(List<Map> lockDatas, String appId) throws Exception;

	public void dropLockForAcctItem(List<Map> unlockDatas, String appId) throws Exception;

	public Table getAppLockListHead();

	public List<SecuTLockPO> getAppLockListHead(String queryParam);

	public void addLockForApp(List<Map> lockDatas) throws Exception;

	public void dropLockForApp(List<Map> unlockDatas) throws Exception;

	public Table getTableLockListHead();

	public List<SecuTLockPO> getTableLockList(Map<String, String> param, String isSuit, String queryParam);

	public void addLockForTable(List<Map> lockDatas, String appId) throws Exception;

	public void dropLockForTable(List<Map> unlockDatas) throws Exception;

	public Table getAgencySuitLockListHead();

	public List<SecuTLockPO> getAgencySuitLockList(Map<String, String> param,
                                                   String queryParam, String appId);

	public void addLockForAgencySuit(List<Map> lockDatas, String appId) throws Exception;

	public void dropLockForAgencySuit(List<Map> unlockDatas, String appId,
                                      String suitId) throws Exception;
	public Table getDeptSuitLockListHead();

	public List<SecuTLockPO> getDeptSuitLockList(Map<String, String> param,
                                                 String queryParam);

	public void addLockForDeptSuit(List<Map> lockDatas, String appId) throws Exception;

	public void dropLockForDeptSuit(List<Map> unlockDatas, String appId,
                                    String suitId) throws Exception;
	
}

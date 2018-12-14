package commons.setting.external.service;

import java.util.List;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.setting.external.po.AccountRuleOutPO;

public interface IAccountRuleOutService {
	/**
	 * 查询记账定义PO
	 * @param appId
	 * @return
	 * @throws ServiceException
	 */
	public List<AccountRuleOutPO> getBillDefinedData(String appId) throws ServiceException;
	/**
	 *  查询记账定义PO by 记账定义ID
	 * @param appId
	 * @param billDefId
	 * @return
	 * @throws ServiceException
	 */
	public AccountRuleOutPO getAccountRuleByRuleID(String appId, String billDefId) throws ServiceException;
	/**
	 * 查询记账定义PO by 记账定义类型
	 * @param appId
	 * @param typeId
	 * @return
	 * @throws ServiceException
	 */
	public List<AccountRuleOutPO> getBillDefDataByTypeId(String appId, String typeId) throws ServiceException;

	
}

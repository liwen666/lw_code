package commons.setting.external.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.setting.external.dao.AccountRuleOutMapper;
import com.tjhq.commons.setting.external.po.AccountRuleOutPO;
import com.tjhq.commons.setting.external.service.IAccountRuleOutService;

@Service
@Transactional(readOnly=true)
public class AccountRuleOutService implements IAccountRuleOutService {
	@Resource
	private AccountRuleOutMapper accountRuleOutMapper;
	
	@Override
	public List<AccountRuleOutPO> getBillDefinedData(String appId)
			throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appId", appId);
		List<AccountRuleOutPO> data = null;
		try {
			data = accountRuleOutMapper.getBillDefinedData(params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.BDF_00001, "记账定义规则错误！", false);
		}
		return data;
	}

	@Override
	public AccountRuleOutPO getAccountRuleByRuleID(String appId,
			String billDefId) throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appId", appId);
		params.put("billDefId", billDefId);
		List<AccountRuleOutPO> data = null;
		try {
		    data = accountRuleOutMapper.getBillDefinedData(params);
		    if (data != null && data.size() > 0) {
		        return data.get(0);
		    }
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.BDF_00001, "记账定义规则错误！", false);
		}
		return null;
	}

	@Override
	public List<AccountRuleOutPO> getBillDefDataByTypeId(String appId,
			String typeId) throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appId", appId);
		params.put("typeId", typeId);
		List<AccountRuleOutPO> data = null;
		try {
			data = accountRuleOutMapper.getBillDefinedData(params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.BDF_00001, "记账定义规则错误！", false);
		}
		return data;
	}

}

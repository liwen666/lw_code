package commons.setting.external.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.setting.external.po.AccountRuleOutPO;

public interface AccountRuleOutMapper  extends SuperMapper {
	/**
	 * 获取记账定义数据
	 * @param params
	 * @return
	 */
	public List<AccountRuleOutPO> getBillDefinedData(Map<String, Object> params);

}

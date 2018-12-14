package commons.setting.billdef.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.inputcomponent.po.TreePO;

public interface IBillDefTypeMapper extends SuperMapper {
	/**
	 * 获取记账类型数据
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getBillTypeList(Map<String, Object> params);
	/**
	 * 获取记账定义数据
	 * @param appId
	 * @return
	 */
	public List<TreePO> getBillDefList(String appId);
	/**
	 * 修改记账类型设置
	 * @param po
	 */
	public void updateData(Map<String, Object> po);
	/**
	 * 新增记账类型设置
	 * @param po
	 */
	public void insertData(Map<String, Object> po);

}

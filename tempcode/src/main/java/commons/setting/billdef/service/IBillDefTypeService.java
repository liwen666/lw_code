package commons.setting.billdef.service;

import java.util.List;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.TreePO;

public interface IBillDefTypeService {
	/**
	 * 获取记账定义类型表定义
	 * @return
	 * @throws ServiceException
	 */
	public Object getTableDefined(String appId) throws ServiceException;
	/**
	 * 获取记账定义类型数据
	 * @return
	 * @throws ServiceException
	 */
	public Object getTableData(Grid data) throws ServiceException;
	/**
	 * 保存
	 * @param data
	 * @throws ServiceException
	 */
	public void saveData(Grid data) throws ServiceException;
	/**
	 * 获取记账定义数据
	 * @param appId
	 * @return
	 * @throws ServiceException
	 */
	public List<TreePO> getBillDefList(String appId) throws ServiceException;

}

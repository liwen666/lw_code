package commons.secu.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.commonGrid.service.ICommonGridService;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.secu.po.ColumnLimitPO;
import com.tjhq.commons.setting.input.po.TreeNode;


public interface IColumnLimitService extends ICommonGridService {

	/**
	 * 查询 套表和表
	 */
	public List<Map<String, String>> querySuitModel(String appID) throws ServiceException;
	
	/**
	 * 查询 列
	 */
	public List<Map<String, String>> queryFactorColumnLimitDetail(String roleid, String tableid, String columnid) throws ServiceException;
	
	/**
	 * 查询 列
	 */
	public List<Map<String, String>> queryFactorTree(String tableid) throws ServiceException;

	/**
	 * 获取表头
	 * @return
	 * @throws ServiceException
	 */
    public Table getDefine1() throws ServiceException;
    
    /**
	 * 获取数据
	 * @return
	 * @throws ServiceException
	 */
    public Object getData1(Table table) throws ServiceException;
    
	/**
	 * 获取表头
	 * @return
	 * @throws ServiceException
	 */
    public Table getDefine2() throws ServiceException;
    /**
	 * 获取数据
	 * @return
	 * @throws ServiceException
	 */
    public Object getData2(Table table) throws ServiceException;
    
    /**
     * 保存数据
     * @param table
     * @return
     * @throws ServiceException
     */
    public String saveTableData(String operate, ColumnLimitPO columnLimitPO) throws ServiceException;
	
	/**
	 * 删除
	 * @param jsonStr
	 * @return
	 * @throws ServiceException
	 */
	public String deleteColumnLimit(String jsonStr) throws ServiceException;
	
	public List<TreeNode> getModelCodeDataByCsid(String csid, String selectId) throws ServiceException;
    
	public String deleteColumnLimitDetail(ColumnLimitPO columnLimitPO) throws ServiceException;
}

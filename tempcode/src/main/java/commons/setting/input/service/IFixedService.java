package commons.setting.input.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.fixgrid.service.IFixGridService;
import com.tjhq.commons.setting.input.po.DictTSetFixPO;


public interface IFixedService extends IFixGridService{
	
	/**
	 * 保存 固定行列表 整表设置
	 * @param fddef
	 * @param operator
	 * @param dealType
	 * @return
	 * @throws Exception 
	 */
	public String saveFixedWhole(DictTSetFixPO fixed, String operator, String dealType) throws Exception;
	/**
	 * 根据表ID，获取固定行列表 设置信息
	 * @param tableID
	 * @return
	 */
	public Map<String,Object> getLvlNanmeCol_2(String tableID);

	public String createIsLeafSql(List<Map<String, Object>> updateIsLeaf, String dbTableName) ;
	
	/**
	 * 固定行列表  更新单元格权限
	 * 参数：
	 * floatData   数据库中数据
	 * isLeaf      处理后的是否末级
	 * fdCodeToColsMap 编码对应列
	 * fdCode_len  编码个数
	 * colOrder    factor表中的所有显示列
	 * dbTableName 物理表名
	 * tableID	   表ID
	 */
	public void updateCellSecu(List<Map<String, Object>> floatData, Map<String, Object> isLeaf,
                               Map<String, Object> fdCodeToColsMap, int fdCode_len, String colOrder, String dbTableName, String tableID);

	/**
	 * 发布浮动表和固定行列表
	 * UPDATE tableName_CFG SET SYNSTATUS = '1' WHERE SYNSTATUS = '0'
	 * @param tableID
	 */
	public void publishData(String tableID) throws ServiceException;
	/**
	 * 根据表ID，where条件查询数据  初始化固定行列表
	 * @throws Exception 
	 */
	public void initFixData(String tableID) throws ServiceException;
}
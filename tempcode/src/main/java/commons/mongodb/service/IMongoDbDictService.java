package commons.mongodb.service;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.po.Table;

public interface IMongoDbDictService {
	/**
	 *  表头
	 * @return
	 * @throws ServiceException
	 */
	public Table getDefineMongoDbDict() throws ServiceException;
	/**
	 * 表数据
	 * @return
	 * @throws ServiceException
	 */
	public Object getDataMongoDbDict(Table table) throws ServiceException;
	
	/**
	 * 保存
	 */
	public void saveData(CommonGrid commonGrid);
}

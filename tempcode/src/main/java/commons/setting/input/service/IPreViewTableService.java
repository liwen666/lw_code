package commons.setting.input.service;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.basegrid.service.IBaseGridService;

public interface IPreViewTableService extends IBaseGridService{
	public Object getDefine(String tableID, String dealType, String typeID, String processID) throws ServiceException;
	public Object getData(String grid, String dealType) throws ServiceException;
}

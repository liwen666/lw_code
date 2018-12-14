package commons.inputcomponent.grid.settinggrid.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.inputcomponent.component.service.IComponentService;
import com.tjhq.commons.inputcomponent.po.PageInfo;

public interface ISettingGridService extends IComponentService {

	public void transferGridData(List<Map<String, Object>> resultList,
                                 PageInfo pageInfo) throws Exception;
}

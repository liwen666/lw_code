package commons.inputcomponent.grid.propertygrid.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.basegrid.service.impl.BaseGridService;
import com.tjhq.commons.inputcomponent.grid.propertygrid.dao.IPropertyGridMapper;
import com.tjhq.commons.inputcomponent.grid.propertygrid.po.PropertyGrid;
import com.tjhq.commons.inputcomponent.grid.propertygrid.service.IPropertyGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.setting.external.po.DictTSetBaseNumTab;
import com.tjhq.commons.setting.external.service.IEntryOuterService;

/**
 * @author Administrator
 * @version 1.0
 * @created 11-����-2014 15:09:29
 */
@Service("propertyGridService")
@Transactional(readOnly=true)
public class PropertyGridService extends BaseGridService implements IPropertyGridService {
	@Resource
	private IEntryOuterService entryOuterService;
	@Resource
	private IPropertyGridMapper propertyGridMapper;
	public PropertyGridService(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Table initStructure(Table table, String userID) throws ServiceException {
		PropertyGrid grid =(PropertyGrid)super.initStructure(table, userID);
		DictTSetBaseNumTab dictTSetBaseNumTab=entryOuterService.getDataBaseTabList(grid.getTableID());//去基本数字表设置表头列
		if(dictTSetBaseNumTab ==null || dictTSetBaseNumTab.getColumnName()==null){			
			throw new ServiceException(ExceptionCode.ERR_00000, "没有设置基本数字表显示列!", false);
		}
		String titleColumn = dictTSetBaseNumTab.getColumnName();
		titleColumn=titleColumn.toUpperCase();
		grid.setbNColumnField(titleColumn);
		List<Column> allColumns = grid.getColumnList();
		Map<String,Column> allColumnsMap = new HashMap<String,Column>();
		for(Column c:allColumns){
			allColumnsMap.put(c.getColumnID(),c);
		}
		List<Object> queryResults = null;
        try {
            queryResults = propertyGridMapper.getColumnForTree(grid.getTableID());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERR_00000, "加载基本数字表列信息出错!", false);
        }
		List<Column> newAllColumns = new ArrayList<Column>();
		for(Object o : queryResults){
			Map<String, String> row = (Map<String, String>)o;
			if(row.get("COLUMNID")!=null && allColumnsMap.get(row.get("COLUMNID"))!=null)
				newAllColumns.add(allColumnsMap.get(row.get("COLUMNID")));
		}
		grid.setColumnList(newAllColumns);
		return grid;
	}
	

}
package commons.inputcomponent.grid.treegroupgrid.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.basegrid.service.impl.BaseGridService;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.setting.external.po.DictTSetGroupPO;

/**
 * @author Administrator
 * @version 1.0
 * @created 11-����-2014 15:10:32
 */
@Service("treeGroupGridService")
@Transactional(readOnly=true)
public class TreeGroupGridService extends BaseGridService {

	public TreeGroupGridService(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	/**
	 * 获得浮动表定义
	 * @param businessObjId
	 * @return
	 * @throws Exception
	 */
	public Grid getGroupDefine(String businessObjId,String nullvalue) throws Exception{
//		List<DictTSetGroupPO> dictTSetGroupPOs = entryOuterService.getDataGroupList(businessObjId);
//		return getGroupDefine(businessObjId,nullvalue,dictTSetGroupPOs);
		return null;
	}
	
	/**
	 * 根据指定和并列 获得浮动表定义
	 * @param businessObjId
	 * @param dictTSetGroupPOs
	 * @return
	 * @throws Exception
	 */
	public Grid getGroupDefine(String businessObjId,String nullvalue,List<DictTSetGroupPO> dictTSetGroupPOs) throws Exception{
//		Grid grid = getDefine(businessObjId);
//		grid.setxType("floatgrid");
//		if(dictTSetGroupPOs == null || dictTSetGroupPOs.size()==0) return grid;
//		grid.getHead().setColumns(addGroupColumn(nullvalue,dictTSetGroupPOs,grid.getHead().getColumns()));
		return null;
	}

	@Override
	public Object getData(Table table) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}




	
}
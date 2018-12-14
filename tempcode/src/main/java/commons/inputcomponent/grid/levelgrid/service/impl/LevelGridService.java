package commons.inputcomponent.grid.levelgrid.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.constants.XType;
import com.tjhq.commons.inputcomponent.exception.BusinessObjNotDefineException;
import com.tjhq.commons.inputcomponent.exception.GridTypeConvertException;
import com.tjhq.commons.inputcomponent.exception.InputBaseException;
import com.tjhq.commons.inputcomponent.grid.basegrid.service.impl.BaseGridService;
import com.tjhq.commons.inputcomponent.grid.levelgrid.po.LevelGrid;
import com.tjhq.commons.inputcomponent.grid.levelgrid.service.ILevelGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Table;

/**
 * 层次表 以树形结构展现数据，可以在树的任意节点添加上级或下级
 * 定义层次表时需要有以下固定列
 * LEVELNO 层次号
 * ISLEAF  是否末级
 * ORDERID 数据排序
 * SUPERID 父级ID
 * DATAKEY 主键
 * 
 * Author:CAOK
 * 2014-9-9 下午02:01:19 
 * Version 1.0
 */
@Service("levelGridService")
@Transactional(readOnly = true)
public class LevelGridService extends BaseGridService implements
		ILevelGridService {

	@Override
	public Table initStructure(Table table, String userID) throws ServiceException {
		if (!checkTable(table)) return null;
		//添加层次表操作列
		addColumnToTable(table);
		return super.initStructure(table, userID);
	}
	
	/**
	 * 检查表格信息
	 * @param table
	 * @return
	 */
	protected boolean checkTable(Table table) {
		if (table == null) {
			throw new InputBaseException("表格对象为空");
		}
		if (!XType.LEVEL_GRID.equals(table.getxType())) {
			throw new GridTypeConvertException("表类型设置有误，应该为"
					+ XType.LEVEL_GRID + "，实际设置为" + table.getxType());
		}
		if (!(table instanceof LevelGrid)) {
			throw new GridTypeConvertException("不能把类型"
					+ table.getClass().getName() + "转换为"
					+ LevelGrid.class.getName());
		}
		if (table.getTableID() == null || "".equals(table.getTableID().trim())) {
			throw new BusinessObjNotDefineException("表格ID不能为空");
		}
		return true;
	}
	
	/**
	 * 添加固定列到表格中
	 * @param table
	 */
	protected void addColumnToTable(Table table) {
		Column column = new Column();
		column.setColumnName("操作");
		column.setColumnDBName("operator");
		
		table.getColumnList().add(column);
	}
	
	@Override
	public Object getData(Table table) throws ServiceException {
		super.setTableColumns(table);
		return super.getData(table);
	}
}

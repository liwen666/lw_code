package commons.install.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.grid.commonGrid.service.impl.CommonGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.install.dao.IInstallDataBaseDao;
import com.tjhq.commons.install.service.IInstallDataBaseService;
@Service
public class InstallDataBaseService extends CommonGridService implements IInstallDataBaseService{
	@Resource
	private IInstallDataBaseDao installDataBaseDao;
	@Override
	public Table getDefineTabHead() {
		//TODO:
		Table grid = new Table();
		grid.setTableID("");
		grid.setTableDBName("");
		grid.setTableName("");
		setColumns(grid);
		return grid;
	}
	// * 列定义
	public void setColumns(Table data) {

		List<Column> list = new ArrayList<Column>();
		
		Column objectNameCol = new Column();
		objectNameCol.setColumnID("objectName");
		objectNameCol.setColumnName("对象名称");
		objectNameCol.setAlias("对象名称");
		objectNameCol.setLeaf(true);
		objectNameCol.setColumnDBName("OBJECTNAME");
		objectNameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectNameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		objectNameCol.setDataLength(10);
		objectNameCol.setWidth(300);
		objectNameCol.setReadOnly(true);
		objectNameCol.setKey(true);
		objectNameCol.setVisible(true);
		objectNameCol.setOrderID(1);

		Column objectTypeCol = new Column();
		objectTypeCol.setColumnID("objectType");
		objectTypeCol.setColumnName("对象类型");
		objectTypeCol.setAlias("对象类型");
		objectTypeCol.setLeaf(true);
		objectTypeCol.setColumnDBName("OBJECTTYPE");
		objectTypeCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectTypeCol.setShowType(ShowType.SHOW_TYPE_HTML);
		objectTypeCol.setDataLength(20);
		objectTypeCol.setWidth(200);
		objectTypeCol.setReadOnly(true);
		objectTypeCol.setVisible(true);
		objectTypeCol.setOrderID(2);
		
		Column isSuccessCol = new Column(); 
		isSuccessCol.setColumnID("isSuccess");
		isSuccessCol.setColumnName("是否成功");
		isSuccessCol.setAlias("是否成功");
		isSuccessCol.setLeaf(true);
		isSuccessCol.setColumnDBName("ISSUCCESS");
		isSuccessCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		isSuccessCol.setShowType(ShowType.SHOW_TYPE_HTML);
		isSuccessCol.setDataLength(60);
		isSuccessCol.setWidth(100);
		isSuccessCol.setReadOnly(true);
		isSuccessCol.setVisible(true);
		isSuccessCol.setOrderID(3);
		
		Column sqlTextCol = new Column();
		sqlTextCol.setColumnID("sqlText");
		sqlTextCol.setColumnName("SQL");
		sqlTextCol.setAlias("SQL");
		sqlTextCol.setLeaf(true);
		sqlTextCol.setColumnDBName("SQLTEXT");
		sqlTextCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		sqlTextCol.setShowType(ShowType.SHOW_TYPE_TEXT_AREA);
		sqlTextCol.setDataLength(60);
		sqlTextCol.setWidth(200);
		sqlTextCol.setReadOnly(false);
		sqlTextCol.setVisible(true);
		sqlTextCol.setOrderID(4);

		Column erroInfoCol = new Column();
		erroInfoCol.setColumnID("erroInfo");
		erroInfoCol.setColumnName("错误日志");
		erroInfoCol.setAlias("错误日志");
		erroInfoCol.setLeaf(true);
		erroInfoCol.setColumnDBName("ERROINFO");
		erroInfoCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		erroInfoCol.setShowType(ShowType.SHOW_TYPE_TEXT_AREA);
		erroInfoCol.setDataLength(60);
		erroInfoCol.setWidth(200);
		erroInfoCol.setReadOnly(false);
		erroInfoCol.setVisible(true);
		erroInfoCol.setOrderID(5);

		Column objectCommentCol = new Column();
		objectCommentCol.setColumnID("objectComment");
		objectCommentCol.setColumnName("备注");
		objectCommentCol.setAlias("备注");
		objectCommentCol.setLeaf(true);
		objectCommentCol.setColumnDBName("OBJECTCOMMENT");
		objectCommentCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		objectCommentCol.setShowType(ShowType.SHOW_TYPE_HTML);
		objectCommentCol.setDataLength(10);
		objectCommentCol.setWidth(300);
		objectCommentCol.setKey(true);
		objectCommentCol.setLogicKey(true);
		objectCommentCol.setVisible(false);
		objectCommentCol.setOrderID(6);

		list.add(objectNameCol);
		list.add(objectTypeCol);
		list.add(isSuccessCol);
		list.add(sqlTextCol);
		list.add(erroInfoCol);
		list.add(objectCommentCol);

		data.setColumnList(list);
	}

	@Override
	public Object getInstalledData(Grid grid) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = grid.getExtProperties();
		//多条件查询
		String objectType = (String) paramMap.get("objectType");
		String objectName = (String) paramMap.get("objectName");
		String isSuccess = (String) paramMap.get("isSuccess");
		Map<String, Object> map = new HashMap<String, Object>();
		if (objectType != null && !"".equals(objectType)) {
			objectType = objectType.trim();
			map.put("objectType_like", "%" + objectType + "%");
		}
		if (objectName != null && !"".equals(objectName)) {
			objectName = objectName.trim();
			map.put("objectName_like", "%" + objectName + "%");
		}
		if (isSuccess != null && !"".equals(isSuccess)) {
			isSuccess = isSuccess.trim();
			map.put("isSuccess_like",  isSuccess);
		}
		// 取得数据
		PageInfo pageInfo = new PageInfo();
		try {
			super.setGridData(installDataBaseDao.getInstallData(map),
					pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageInfo;
	}
	//判断是否存在model表  
	@Override
	public Integer hasModel(String tableName) {
		return installDataBaseDao.hasModel(tableName);
	}
	@Override
	public List<Map<String, Object>> getDefaultProvince() {
		
		return installDataBaseDao.getDefaultProvince();
	}
}

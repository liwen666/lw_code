package commons.mongodb.service.impl;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.mongodb.dao.IMongoDbDictDAO;
import com.tjhq.commons.mongodb.po.MongoDBPO;
import com.tjhq.commons.mongodb.service.IMongoDbDictService;
import com.tjhq.commons.utils.SystemProperty;
@Service
@Transactional(readOnly = true)
public class MongoDbDictService implements IMongoDbDictService{
	@Resource
	private ISettingGridService settingGridService;
	@Resource
	private IMongoDbDictDAO mongoDbDictMapper;
	
	private static Column newColumn(String columnID, String columnName,
			String columnDBName, boolean isKey, int orderID) {
		Column col = new Column();
		col.setColumnID(columnID);
		col.setColumnName(columnName);
		col.setAlias(columnName);
		col.setColumnDBName(columnDBName);
		col.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		col.setShowType(ShowType.SHOW_TYPE_HTML);
		col.setDataLength(32);
		col.setKey(isKey);
		col.setOrderID(orderID);
		col.setNullable(isKey ? false : true);
		col.setVisible(isKey ? false : true);
		return col;
	}
	private static Column newColumn2(String columnID, String columnName,
			String columnDBName, boolean isKey, int orderID) {
		Column col = new Column();
		col.setColumnID(columnID);
		col.setColumnName(columnName);
		col.setAlias("是否启用");
		col.setColumnDBName(columnDBName);
		col.setDataType(JSTypeUtils.getJSDateType(DataType.INT));
		col.setShowType(ShowType.SHOW_TYPE_RADIO);
		col.setDataLength(32);
		col.setKey(isKey);
		col.setOrderID(orderID);
		col.setNullable(isKey ? false : true);
		col.setVisible(isKey ? false : true);
		return col;
	}
	
	@Override
	public Table getDefineMongoDbDict() throws ServiceException {
		try {
			Table table = new Table();
			table.setTableID("1");
			table.setAppID("1");
			table.setTableDBName("1");
			table.setTableName("1");
			List<Column> list = new ArrayList<Column>();
			int orderId = 0;
			
			Column column1 = newColumn("guID", "guID", "guID", true, orderId++);
			column1.setReadOnly(false);
			list.add(column1);
			
			Column column2 = newColumn("appID", "系统名称", "appID", false, orderId++);
			column2.setReadOnly(false);
			list.add(column2);
			
			Column column3 = newColumn("ipAddress", "ip地址", "ipAddress", false, orderId++);
			column3.setReadOnly(false);
			list.add(column3);
			
			Column column4 = newColumn("port", "端口", "port", false, orderId++);
			column4.setReadOnly(false);
			list.add(column4);
			
			Column column5 = newColumn("dBase", "数据库", "dBase", false, orderId++);
			column5.setReadOnly(false);
			list.add(column5);
			
			Column column6 = newColumn2("isUse", "是否启用", "isUse", false, orderId++);
			column6.setReadOnly(true);
			list.add(column6);
			table.setColumnList(list);
			UserDTO user = SecureUtil.getCurrentUser();
			table = settingGridService.initStructure(table, user.getGuid());
			return table;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, "查询表出错异常",
					false);
		}
	}

	@Override
	public Object getDataMongoDbDict(Table table) throws ServiceException {

		try {
			CommonGrid commonGrid = (CommonGrid) table;
			List<MongoDBPO> resultlist = mongoDbDictMapper.getAllMongoDbDict();
			List<Object> datalist = new ArrayList<Object>();
			if (resultlist != null && resultlist.size() > 0) {
				for (MongoDBPO data : resultlist) {
					datalist.add(new Object[] {data.getGuID(),data.getAppID(),data.getIpAddress(),data.getdBase(),data.getPort(),data.getIsUse()});
				}
			}
			PageInfo pageInfo = commonGrid.getPageInfo();
			pageInfo.setDataList(datalist);
			pageInfo.setTotal(datalist.size());
			List<String> columsList = new ArrayList<String>();
			columsList.add("guID");
			columsList.add("appID");
			columsList.add("ipAddress");
			columsList.add("dBase");
			columsList.add("port");
			columsList.add("isUse");
			pageInfo.setColumns(columsList);

			return pageInfo;

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, "查询表出错异常",
					false);
		}
	
	}

	@Override
	public void saveData(CommonGrid commonGrid) {
		// 插入数据
		String appID=SystemProperty.getSystemAppID();
		for (Map<String, Object> map : commonGrid.getInsertValues()) {
			map.put("appID", appID);
			Object changeDictistrict = map.get("CHANGEDISTRICT");
			String utfSpace = "";
			if (changeDictistrict != null)
				changeDictistrict.toString().replaceAll(utfSpace, " ").trim()
						.toUpperCase();
			map.put("CHANGEDISTRICT", changeDictistrict);
			mongoDbDictMapper.addMongoDbDict(map);
		}

		// 刪除数据
		System.out.println(commonGrid.getDeleteValues());
		for (Map<String, Object> map : commonGrid.getDeleteValues()) {
			map.put("appID", appID);
			mongoDbDictMapper.delMongoDbDict(map);
		}
		//修改数据
		System.out.println(commonGrid.getUpdateValues());
		for (Map<String, Object> map : commonGrid.getUpdateValues()) {
			mongoDbDictMapper.updateMongoDbDict(map);
		}

	
	}

}

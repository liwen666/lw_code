package commons.setting.regist.web;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.regist.service.IRegistService;

@Controller
@RequestMapping(value = "/commons/setting/regist")
public class RegistController {

	@Resource
	private ISettingGridService settingGridService;

	@Resource
	private IRegistService registService;

	@RequestMapping(value = "regist")
	public String page(Model model) throws Exception {
		return "/commons/setting/regist/regist";
	}

	/**
	 * 获取表数据
	 * 
	 * @param grid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getData")
	@ResponseBody
	public Object getData(String grid) throws Exception {
		Grid data = (Grid) (new ObjectMapper()).readValue(grid, Grid.class);
		Map<String, String> params = new HashMap<String, String>();
		String queryDBName = (String) data.getExtProperties().get("QueryDBName");
		if(queryDBName != null){
		queryDBName = queryDBName.toUpperCase();
		}
		String queryTableName = (String) data.getExtProperties().get("QueryTableName");
		List<Map<String, Object>> dataList = registService.getDataList(params,queryDBName,queryTableName);

		settingGridService.transferGridData(dataList, data.getPageInfo());
		return data.getPageInfo();
	}

	// 保存数据
	@RequestMapping(value = "saveData")
	@ResponseBody
	public Object saveData(String grid) throws Exception {
		CommonGrid commonGrid = null;
		ResultPO resultPO = null;

		try {
			commonGrid = (CommonGrid) (new ObjectMapper()).readJson(grid, CommonGrid.class);
			registService.saveData(commonGrid);
			resultPO = new ResultPO("数据保存成功!");
		} catch (ServiceException e) {
			e.printStackTrace();
			resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
		}

		return resultPO;
	}

	/**
	 * 获取表格列定义
	 * 
	 * @explain
	 * @return Object
	 * @throws Exception
	 */
	@RequestMapping(value = "getDefine")
	@ResponseBody
	public Object getDefine() throws Exception {
		try {
			Table grid = this.setTableDefine();
			UserDTO user = SecureUtil.getCurrentUser();
			Table table = settingGridService
					.initStructure(grid, user.getGuid());
			return table;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 表数据
	private Table setTableDefine() {
		Table table = new Table();

		table.setAppID("xxxAppID");
		table.setTableName("xxTableName");
		table.setTableID("xxxTableID");
		table.setTableDBName("xxxTableDBName");

		List<Column> columnList = new ArrayList<Column>();

		// guid
		Column guidCol = new Column();
		guidCol.setColumnID("GUID");
		guidCol.setColumnName("GUID");
		guidCol.setColumnDBName("GUID");
		guidCol.setAlias("主键");
		guidCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		guidCol.setShowType(ShowType.SHOW_TYPE_HTML);
		guidCol.setDataLength(30);
		guidCol.setKey(true);
		guidCol.setOrderID(1);
		guidCol.setReadOnly(false);
		guidCol.setVisible(false);
		guidCol.setWidth(100);
		columnList.add(guidCol);

		// 表英文名称
		Column dbtablenameCol = new Column();
		dbtablenameCol.setColumnID("DBTABLENAME");
		dbtablenameCol.setColumnName("DBTABLENAME");
		dbtablenameCol.setColumnDBName("DBTABLENAME");
		dbtablenameCol.setAlias("表英文名称");
		dbtablenameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		dbtablenameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		dbtablenameCol.setDataLength(30);
		dbtablenameCol.setKey(false);
		dbtablenameCol.setOrderID(1);
		dbtablenameCol.setReadOnly(false);
		dbtablenameCol.setVisible(true);
		dbtablenameCol.setWidth(180);
		dbtablenameCol.setNullable(false);
		columnList.add(dbtablenameCol);

		// 表中文名称
		Column nameCol = new Column();
		nameCol.setColumnID("NAME");
		nameCol.setColumnName("NAME");
		nameCol.setColumnDBName("NAME");
		nameCol.setAlias("表中文名称");
		nameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		nameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		nameCol.setDataLength(100);
		nameCol.setKey(false);
		nameCol.setOrderID(2);
		nameCol.setReadOnly(false);
		nameCol.setVisible(true);
		nameCol.setWidth(180);
		columnList.add(nameCol);

		

		// 业务系统
		Column appidCol = new Column();
		appidCol.setColumnID("APPID");
		appidCol.setColumnName("APPID");
		appidCol.setColumnDBName("APPID");
		appidCol.setAlias("业务系统");
		appidCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		appidCol.setShowType(ShowType.SHOW_TYPE_HTML);
		appidCol.setDataLength(1000);
		appidCol.setKey(false);
		appidCol.setOrderID(5);
		appidCol.setReadOnly(false);
		appidCol.setVisible(false);
		appidCol.setWidth(150);
		columnList.add(appidCol);

		// 年份分区
		Column yearpartCol = new Column();
		yearpartCol.setColumnID("YEARPART");
		yearpartCol.setColumnName("YEARPART");
		yearpartCol.setColumnDBName("YEARPART");
		yearpartCol.setAlias("年份分区");
		yearpartCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		yearpartCol.setShowType(ShowType.SHOW_TYPE_HTML);
		yearpartCol.setDataLength(2);
		yearpartCol.setKey(false);
		yearpartCol.setOrderID(6);
		yearpartCol.setReadOnly(true);
		yearpartCol.setVisible(true);
		yearpartCol.setWidth(80);
		columnList.add(yearpartCol);
		
		// 地区分区
		Column provincepartCol = new Column();
		provincepartCol.setColumnID("PROVINCEPART");
		provincepartCol.setColumnName("PROVINCEPART");
		provincepartCol.setColumnDBName("PROVINCEPART");
		provincepartCol.setAlias("地区分区");
		provincepartCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		provincepartCol.setShowType(ShowType.SHOW_TYPE_HTML);
		provincepartCol.setDataLength(2);
		provincepartCol.setKey(false);
		provincepartCol.setOrderID(7);
		provincepartCol.setReadOnly(true);
		provincepartCol.setVisible(true);
		provincepartCol.setWidth(80);
		columnList.add(provincepartCol);
		
		// 任务分区
		Column taskpartCol = new Column();
		taskpartCol.setColumnID("TASKPART");
		taskpartCol.setColumnName("TASKPART");
		taskpartCol.setColumnDBName("TASKPART");
		taskpartCol.setAlias("任务分区");
		taskpartCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		taskpartCol.setShowType(ShowType.SHOW_TYPE_HTML);
		taskpartCol.setDataLength(2);
		taskpartCol.setKey(false);
		taskpartCol.setOrderID(8);
		taskpartCol.setReadOnly(true);
		taskpartCol.setVisible(true);
		taskpartCol.setWidth(80);
		columnList.add(taskpartCol);
		
		// 过滤条件
		Column tabswhereCol = new Column();
		tabswhereCol.setColumnID("TABSWHERE");
		tabswhereCol.setColumnName("TABSWHERE");
		tabswhereCol.setColumnDBName("TABSWHERE");
		tabswhereCol.setAlias("过滤条件");
		tabswhereCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		tabswhereCol.setShowType(ShowType.SHOW_TYPE_TEXT_AREA);
		tabswhereCol.setDataLength(4000);
		tabswhereCol.setKey(false);
		tabswhereCol.setOrderID(9);
		tabswhereCol.setReadOnly(false);
		tabswhereCol.setVisible(true);
		tabswhereCol.setWidth(150);
		columnList.add(tabswhereCol);
		
		
		// 是否初始化数据
		Column initializeCol = new Column();
		initializeCol.setColumnID("INITIALIZE");
		initializeCol.setColumnName("INITIALIZE");
		initializeCol.setColumnDBName("INITIALIZE");
		initializeCol.setAlias("初始化数据");
		initializeCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		initializeCol.setShowType(ShowType.SHOW_TYPE_HTML);
		initializeCol.setDataLength(2);
		initializeCol.setKey(false);
		initializeCol.setOrderID(10);
		initializeCol.setReadOnly(true);
		initializeCol.setVisible(true);
		initializeCol.setWidth(80);
		columnList.add(initializeCol);
		
		// 是否平台导入
		Column isleadCol = new Column();
		isleadCol.setColumnID("ISLEAD");
		isleadCol.setColumnName("ISLEAD");
		isleadCol.setColumnDBName("ISLEAD");
		isleadCol.setAlias("是否平台导入");
		isleadCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		isleadCol.setShowType(ShowType.SHOW_TYPE_HTML);
		isleadCol.setDataLength(2);
		isleadCol.setKey(false);
		isleadCol.setOrderID(10);
		isleadCol.setReadOnly(true);
		isleadCol.setVisible(true);
		isleadCol.setWidth(80);
		columnList.add(isleadCol);
		
		//地区字段名
		Column tabchangedistrictCol = new Column();
		tabchangedistrictCol.setColumnID("CHANGEDISTRICT");
		tabchangedistrictCol.setColumnName("CHANGEDISTRICT");
		tabchangedistrictCol.setColumnDBName("CHANGEDISTRICT");
		tabchangedistrictCol.setAlias("地区字段名");
		tabchangedistrictCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		tabchangedistrictCol.setShowType(ShowType.SHOW_TYPE_HTML);
		tabchangedistrictCol.setDataLength(32);
		tabchangedistrictCol.setKey(false);
		tabchangedistrictCol.setOrderID(9);
		tabchangedistrictCol.setReadOnly(false);
		tabchangedistrictCol.setVisible(true);
		tabchangedistrictCol.setWidth(100);
		columnList.add(tabchangedistrictCol);
		
		// 备注
		Column remarkCol = new Column();
		remarkCol.setColumnID("REMARK");
		remarkCol.setColumnName("REMARK");
		remarkCol.setColumnDBName("REMARK");
		remarkCol.setAlias("备注");
		remarkCol.setDataType(JSTypeUtils.getJSDateType(DataType.BIGTEXT));
		remarkCol.setShowType(ShowType.SHOW_TYPE_TEXT_AREA);
		remarkCol.setDataLength(4000);
		remarkCol.setKey(false);
		remarkCol.setOrderID(11);
		remarkCol.setReadOnly(false);
		remarkCol.setVisible(true);
		remarkCol.setWidth(150);
		columnList.add(remarkCol);

		table.setColumnList(columnList);
		return table;
	}
}

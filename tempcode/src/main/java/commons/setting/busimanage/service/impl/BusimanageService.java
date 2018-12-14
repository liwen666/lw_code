package commons.setting.busimanage.service.impl;

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
import com.tjhq.commons.inputcomponent.grid.basegrid.service.impl.BaseGridService;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.busimanage.dao.BusimanageMapper;
import com.tjhq.commons.setting.busimanage.service.IBusimanageService;
@Service
@Transactional(readOnly = true)
public class BusimanageService  extends BaseGridService  implements IBusimanageService {
	@Resource
	BusimanageMapper busimanagerMaper;
	
	@Override
	public PageInfo getDataList(CommonGrid commonGrid) throws Exception {
		PageInfo pageInfo = commonGrid.getPageInfo();
		Map<String, Object> paramMap = getQueryParamter(commonGrid);
		String QueryTitle = (String) commonGrid.getExtProperties() .get("QueryTitle");
		
		paramMap.put("QueryTitle", QueryTitle);
		
        List<Map<String, Object>> dataList = busimanagerMaper.getDataList(paramMap);
		setGridData(dataList, pageInfo);
        if (commonGrid.isPagination()) { // 如果表格有分页，需要取得数据总条数
            int total = busimanagerMaper.getDataListCount(paramMap);
            pageInfo.setTotal(total);
        }
		return pageInfo;
		
	}
	
	@Override
	public List<Map<String, Object>> getDataList() throws Exception{
			Map<String, Object> paramMap = null ;
		   List<Map<String, Object>> dataList = busimanagerMaper.getDataList(paramMap);
		   return dataList ;
		   
		
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void saveData(Map<String, Object> map) {
		
		busimanagerMaper.saveData(map);
	
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Object saveData(String grid) {
		CommonGrid commonGrid = null;
		ResultPO resultPO = null;
		try {

			commonGrid = (CommonGrid) (new ObjectMapper()).readJson(grid,
					CommonGrid.class);
			// 插入数据

			// 检查 数据是否有效
			for (Map<String, Object> map : commonGrid.getInsertValues()) {
				String appid = (String) map.get("APPID");
				String name = (String) map.get("NAME");
				String orderid = (String) map.get("ORDERID");
				String url = (String) map.get("URL");
				if ("".equals(appid) || appid == null) {
					throw new ServiceException(ExceptionCode.INP_00201,
							"所属业务模块" + "不可为空！", false);

				} else if ("".equals(name) || name == null) {
					throw new ServiceException(ExceptionCode.INP_00201, "业务名称"
							+ "不可为空！", false);

				} else if ("".equals(orderid) || orderid == null) {

					throw new ServiceException(ExceptionCode.INP_00201, "排序ID"
							+ "不可为空！", false);

				} else if ("".equals(url) || url == null) {
					throw new ServiceException(ExceptionCode.INP_00201, "URL"
							+ "不可为空！", false);

				}  else {
					try {
						Integer.parseInt(orderid);
					} catch (Exception e) {
						throw new ServiceException(ExceptionCode.INP_00201,
								"排序ID" + "必须为数字！", false);
					}

				}
			}
			// 插入数据
			for (Map<String, Object> map : commonGrid.getInsertValues()) {

				this.saveData(map);
			}
			// 更新数据
			for (Map<String, Object> map : commonGrid.getUpdateValues()) {
				String appid = (String) map.get("APPID");
				String name = (String) map.get("NAME");
				String orderid = (String) map.get("ORDERID");
				String url = (String) map.get("URL");
				if (appid != null) {
					if ("".equals(appid))
						throw new ServiceException(ExceptionCode.INP_00201,
								"所属业务模块" + "不可为空！", false);

				}
				if (name != null) {
					if ("".equals(name))
						throw new ServiceException(ExceptionCode.INP_00201,
								"业务名称" + "不可为空！", false);

				}
				if (orderid != null) {
					if ("".equals(orderid)) {
						throw new ServiceException(ExceptionCode.INP_00201,
								"排序ID" + "不可为空！", false);
					} else {
						try {
							Integer.parseInt(orderid);
						} catch (Exception e) {
							throw new ServiceException(ExceptionCode.INP_00201,
									"排序ID" + "必须为数字！", false);
						}
					}
				}
				if (url != null) {
					if ("".equals(url))
						throw new ServiceException(ExceptionCode.INP_00201,
								"URL" + "不可为空！", false);

				}
				
			}
			// 更新数据
			for (Map<String, Object> map : commonGrid.getUpdateValues()) {
				this.updateData(map);
			}

			// 刪除数据
			for (Map<String, Object> map : commonGrid.getDeleteValues()) {
				this.delData(map);
			}

			resultPO = new ResultPO("数据保存成功!");
		} catch (Exception e) {
			// e.printStackTrace();
			resultPO = new ResultPO(e.getMessage());
		}

		return resultPO;
	}


	// 表数据
	@Override
	public Table setTableDefine() {
		Table table = new Table();

		table.setAppID("xxxAppID");
		table.setTableName("xxTableName");
		table.setTableID("xxxTableID");
		table.setTableDBName("xxxTableDBName");

		List<Column> columnList = new ArrayList<Column>();

		Column datakeyCol = new Column();
		datakeyCol.setColumnID("DATAKEY");
		datakeyCol.setColumnName("DATAKEY");
		datakeyCol.setColumnDBName("DATAKEY");
		datakeyCol.setAlias("主键");
		datakeyCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		datakeyCol.setShowType(ShowType.SHOW_TYPE_HTML);
		datakeyCol.setDataLength(32);
		datakeyCol.setKey(true);
		// datakeyCol.setOrderID(1);
		datakeyCol.setReadOnly(false);
		datakeyCol.setVisible(false);
		datakeyCol.setNullable(false);
		datakeyCol.setWidth(100);
		columnList.add(datakeyCol);

		// 姓名
		Column nameCol = new Column();
		nameCol.setColumnID("NAME");
		nameCol.setColumnName("NAME");
		nameCol.setColumnDBName("NAME");
		nameCol.setAlias("业务名称");
		nameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		nameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		nameCol.setDataLength(32);
		nameCol.setKey(false);
		// nameCol.setOrderID(9);
		nameCol.setReadOnly(false);
		nameCol.setNullable(false);
		nameCol.setVisible(true);
		nameCol.setWidth(150);
		columnList.add(nameCol);

		// URL
		Column urlCol = new Column();
		urlCol.setColumnID("URL");
		urlCol.setColumnName("URL");
		urlCol.setColumnDBName("URL");
		urlCol.setAlias("URL");
		urlCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		urlCol.setShowType(ShowType.SHOW_TYPE_HTML);
		urlCol.setDataLength(100);
		urlCol.setKey(false);
		urlCol.setNullable(false);
		// nameCol.setOrderID(9);
		urlCol.setReadOnly(false);
		urlCol.setVisible(true);
		urlCol.setWidth(150);
		columnList.add(urlCol);

		// 排序
		Column orderidCol = new Column();
		orderidCol.setColumnID("ORDERID");
		orderidCol.setColumnName("ORDERID");
		orderidCol.setColumnDBName("ORDERID");
		orderidCol.setAlias("排序ID");
		orderidCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		orderidCol.setShowType(ShowType.SHOW_TYPE_HTML);
		orderidCol.setDataLength(32);
		orderidCol.setKey(false);
		// nameCol.setOrderID(9);
		orderidCol.setReadOnly(false);
		orderidCol.setNullable(false);
		orderidCol.setVisible(true);
		orderidCol.setWidth(150);
		columnList.add(orderidCol);

		// 地区字段名
		Column typeCol = new Column();
		typeCol.setColumnID("APPID");
		typeCol.setColumnName("APPID");
		typeCol.setColumnDBName("APPID");
		typeCol.setAlias("所属业务模块");
		typeCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		typeCol.setShowType(ShowType.SHOW_TYPE_HTML);
		typeCol.setDataLength(32);
		typeCol.setKey(false);
		typeCol.setNullable(false);
		// typeCol.setOrderID(9);
		typeCol.setReadOnly(false);
		typeCol.setVisible(true);
		typeCol.setWidth(120);
		columnList.add(typeCol);

		// 是否是ajax请求。
		Column isAjaxCol = new Column();
		isAjaxCol.setColumnID("IS_AJAX");
		isAjaxCol.setColumnName("IS_AJAX");
		isAjaxCol.setColumnDBName("IS_AJAX");
		isAjaxCol.setAlias("是否ajax请求");
		isAjaxCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		isAjaxCol.setShowType(ShowType.SHOW_TYPE_HTML);
		isAjaxCol.setDataLength(32);
		isAjaxCol.setKey(true);
		isAjaxCol.setNullable(false);
		// typeCol.setOrderID(9);
		isAjaxCol.setReadOnly(false);
		isAjaxCol.setVisible(true);
		isAjaxCol.setWidth(120);
		columnList.add(isAjaxCol);

		table.setColumnList(columnList);
		return table;

	}
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void updateData(Map<String, Object> map) {
		busimanagerMaper.updateData(map);
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delData(Map<String, Object> map) {
		busimanagerMaper.delData(map);
	}

}

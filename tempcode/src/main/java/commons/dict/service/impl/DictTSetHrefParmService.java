package commons.dict.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dict.service.IDictTSetHrefParmService;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.setting.input.dao.EntryMapper;
import com.tjhq.commons.setting.input.dao.GeneralMapper;
import com.tjhq.commons.setting.input.po.DictTSetHrefParmPO;
import com.tjhq.commons.setting.input.web.ConverTables;

@Service
@Transactional
public class DictTSetHrefParmService implements IDictTSetHrefParmService {

	@Resource
	private GeneralMapper generalMapper;

	@Resource
	private EntryMapper entryMapper;

	@Override
	public List<DictTSetHrefParmPO> findDictTSetHrefParmByHrefParmID(
			String hrefParmID) throws Exception {

		List<DictTSetHrefParmPO> listhref = null;
		try {
			if (hrefParmID != null && !"".equals(hrefParmID)) {
				listhref = this.entryMapper.selectHrefParm(hrefParmID);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return listhref;
	}

	@Override
	public void insertDictTSetHrefParmPO(List<Map<String, Object>> lists) throws Exception{

		if (lists != null && lists.size() > 0) {
			try {
				this.generalMapper.insertAnalyDetail(lists);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
	}

	@Override
	public void updateDictTSetHrefParmPO(List<Map<String, Object>> lists) throws Exception{

		if (lists != null && lists.size() > 0) {
			try {
				this.generalMapper.updateAnalyDetail(lists);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
	}

	@Override
	public void deleteDictTSetHrefParmPO(List<Map<String, Object>> lists) throws Exception{

		if (lists != null && lists.size() > 0) {
			try {
				this.generalMapper.deleteAnalyDetail(lists);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
	}

	@Override
	public Grid getDictTSetHrefParmHead(String tablename) {

		// 创建Grid
		Grid grid = new Grid();
		// 设置tableID
		grid.setTableID(tablename);
		// Grid类型
		grid.setTableDBName(tablename);
		grid.setTableName(tablename);
		grid.setAppID("");
		int orderId = 0;
		// 创建列
		// Column orderID = new Column();
		// orderID.setColumnID("orderID");
		// orderID.setColumnDBName("orderID");
		// orderID.setColumnName("序号");
		// orderID.setAlias("序号");
		// orderID.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		// orderID.setShowType(ShowType.SHOW_TYPE_HTML);
		// orderID.setOrderID(++orderId);
		// orderID.setKey(true);
		// orderID.setVisible(true);

		Column parmName = new Column();
		parmName.setColumnID("parmName");
		parmName.setColumnDBName("parmName");
		parmName.setColumnName("参数名称");
		parmName.setAlias("参数名称");
		parmName.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		parmName.setShowType(ShowType.SHOW_TYPE_HTML);
		parmName.setOrderID(++orderId);
		parmName.setDataLength(256);
		parmName.setKey(true);
		parmName.setVisible(true);

		Column parmCon = new Column();
		parmCon.setColumnID("parmCon");
		parmCon.setColumnDBName("parmCon");
		parmCon.setColumnName("参数表达式");
		parmCon.setAlias("参数表达式");
		parmCon.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		parmCon.setShowType(ShowType.SHOW_TYPE_HTML);
		parmCon.setOrderID(++orderId);
		parmCon.setDataLength(1024);
		parmCon.setKey(true);
		parmCon.setVisible(true);

		Column hrefParmID = new Column();
		hrefParmID.setColumnID("hrefParmID");
		hrefParmID.setColumnDBName("hrefParmID");
		hrefParmID.setColumnName("参数ID");
		hrefParmID.setAlias("参数ID");
		hrefParmID.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		hrefParmID.setShowType(ShowType.SHOW_TYPE_HTML);
		hrefParmID.setOrderID(++orderId);
		hrefParmID.setDataLength(32);
		hrefParmID.setKey(true);
		hrefParmID.setVisible(false);

		Column hrefID = new Column();
		hrefID.setColumnID("hrefID");
		hrefID.setColumnDBName("hrefID");
		hrefID.setColumnName("主键ID");
		hrefID.setAlias("主键ID");
		hrefID.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		hrefID.setShowType(ShowType.SHOW_TYPE_HTML);
		hrefID.setOrderID(++orderId);
		hrefID.setDataLength(32);
		hrefID.setKey(true);
		hrefID.setVisible(false);

		// 设置表头
		List<Column> columns = new ArrayList<Column>();
		// columns.add(orderID);
		columns.add(parmName);
		columns.add(parmCon);
		columns.add(hrefParmID);
		columns.add(hrefID);
		grid.setColumnList(columns);
		return grid;
	}

	@Override
	public void deleteDictTSetHrefParmPObyParm(List<Map<String, Object>> lists) throws Exception {
		if (lists != null && lists.size() > 0) {
			try {
				this.generalMapper.deleteAnalyCascHref(lists);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
	}

}

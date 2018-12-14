package commons.dict.external.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.dict.constants.DictCacheKey;
import com.tjhq.commons.dict.external.dao.DictTFactorMapper;
import com.tjhq.commons.dict.external.dao.DictTFactorcodeMapper;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTFactorcodePO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.service.IDictTFactorcodeService;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.dict.util.DictDBConstants;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;

/**
 * 列代码表接口实现
 * 
 * @author xujingsi
 * 
 */
@Service
@Transactional
public class DictTFactorcodeService implements IDictTFactorcodeService {

	@Resource
	private DictTFactorcodeMapper dictTFactorcodeMapper;

	@Resource
	private DictTFactorMapper dictTFactorMapper;

	@Resource
	private IDataCacheService dataCacheService;

	@Resource
	private IDictTModelcodeService dictTModelcodeService;

	@Override
	public DictTFactorcodePO getDictTFactorcodePOByColumnId(String columnid)
			throws Exception {

		DictTFactorcodePO dtf = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictFactorCode.ID.getCacheKey(), columnid,
				"getDictTFactorcodePOByColumnId" };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			dtf = (DictTFactorcodePO) dataCacheService.get(keys);
			return dtf;
		}
		try {
			if (columnid != null && !"".equals(columnid)) {
				dtf = dictTFactorcodeMapper.getDictTFactorcode(columnid);
				// 加入到缓存
				dataCacheService.put(keys, dtf);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return dtf;
	}

	@Override
	public DictTFactorcodePO getDictTFactorcodePObyDBColumnName(String tableID,
			String dbcolumnname) throws Exception {

		DictTFactorcodePO dtf = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictFactorCode.DBNAME.getCacheKey(), dbcolumnname,
				"getDictTFactorcodePObyDBColumnName", tableID };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			dtf = (DictTFactorcodePO) dataCacheService.get(keys);
			return dtf;
		}
		try {
			if (dbcolumnname != null && !"".equals(dbcolumnname)) {
				dtf = dictTFactorcodeMapper
						.getDictTFactorcodeByDBName(dbcolumnname);
				// 加入到缓存
				dataCacheService.put(keys, dtf);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return dtf;
	}

	@Override
	public List<DictTFactorcodePO> getDictTFactorcodePOsByTableId(String tableid)
			throws Exception {

		List<DictTFactorcodePO> factors = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTableCode.ID.getCacheKey(), tableid,
				"getDictTFactorcodePOsByTableId" };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			factors = (List<DictTFactorcodePO>) dataCacheService.get(keys);
			return factors;
		}
		try {
			if (tableid != null && !"".equals(tableid)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tableid", tableid);
				factors = dictTFactorcodeMapper.findDictTFactorcode(map);
				// 加入到缓存
				dataCacheService.put(keys, factors);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return factors;
	}

	@Override
	public void insertDictTFactorcode(DictTFactorcodePO dictTFactorcodePO)
			throws Exception {

		try {
			this.dictTFactorcodeMapper.insertDictTFactorcode(dictTFactorcodePO);
			// 清除表缓存
			String[] tableKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTableCode.ID.getCacheKey(),
					dictTFactorcodePO.getTableid() };
			dataCacheService.put(tableKeys, null);
			// 清除app列缓存
			String[] columnAppKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictFactorCode.APP.getCacheKey() };
			dataCacheService.put(columnAppKeys, null);
			// 清除app表缓存
			String[] tableAppKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTableCode.APP.getCacheKey() };
			dataCacheService.put(tableAppKeys, null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void updateDictTFactorcode(DictTFactorcodePO dictTFactorcodePO)
			throws Exception {

		try {
			String columnid = dictTFactorcodePO.getColumnid();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bandrefdwcol", columnid);
			List<DictTFactorPO> dtfList = this.dictTFactorMapper
					.findDictTFactor(map);
			if (dtfList == null || dtfList.size() < 1) {
				this.dictTFactorcodeMapper
						.updateDictTFactorcode(dictTFactorcodePO);
				// 清除列ID缓存
				String[] columnIDKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictFactorCode.ID.getCacheKey(),
						dictTFactorcodePO.getColumnid() };
				dataCacheService.put(columnIDKeys, null);
				// 清除列物理列名缓存
				String[] columnDBNameKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictFactorCode.DBNAME.getCacheKey(),
						dictTFactorcodePO.getDbcolumnname() };
				dataCacheService.put(columnDBNameKeys, null);
				// 清除表ID缓存
				String[] tableIDKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictTableCode.ID.getCacheKey(),
						dictTFactorcodePO.getTableid() };
				dataCacheService.put(tableIDKeys, null);
				DictTModelcodePO dtmCode = dictTModelcodeService
						.getDictTModelcodePOByID(dictTFactorcodePO.getTableid());
				// 清除表物理名缓存
				String[] tableDBNameKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictTableCode.DBNAME.getCacheKey(),
						dtmCode.getDbtablename() };
				dataCacheService.put(tableDBNameKeys, null);
				String appID = dtmCode.getAppid();
				// 清除app列缓存
				String[] columnAppKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictFactorCode.APP.getCacheKey(), appID };
				dataCacheService.put(columnAppKeys, null);
				// 清除app表缓存
				String[] tableAppKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictTableCode.APP.getCacheKey(), appID };
				dataCacheService.put(tableAppKeys, null);
			} else {
				throw new Exception("该列已经被引用，禁止修改.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void deleteDictTFactorcode(String columnid) throws Exception {

		try {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("bandrefdwcol", columnid);
//			List<DictTFactorcodePO> dtfList = this.dictTFactorcodeMapper
//					.findDictTFactorcode(map);
//			if (dtfList == null || dtfList.size() < 1) {
			if(columnid!=null && columnid.length()>0){
				DictTFactorcodePO dictTFactorcodePO = getDictTFactorcodePOByColumnId(columnid);
				this.dictTFactorcodeMapper.deleteDictTFactorcode(columnid);
				// 清除列ID缓存
				String[] columnIDKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictFactorCode.ID.getCacheKey(),
						dictTFactorcodePO.getColumnid() };
				dataCacheService.put(columnIDKeys, null);
				// 清除列物理列名缓存
				String[] columnDBNameKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictFactorCode.DBNAME.getCacheKey(),
						dictTFactorcodePO.getDbcolumnname() };
				dataCacheService.put(columnDBNameKeys, null);
				// 清除表缓存
				String[] tableKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictTableCode.ID.getCacheKey(),
						dictTFactorcodePO.getTableid() };
				dataCacheService.put(tableKeys, null);
				DictTModelcodePO dtmCode = dictTModelcodeService
						.getDictTModelcodePOByID(dictTFactorcodePO.getTableid());
				String appID = dtmCode.getAppid();
				// 清除app列缓存
				String[] columnAppKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictFactorCode.APP.getCacheKey(), appID };
				dataCacheService.put(columnAppKeys, null);
				// 清除app表缓存
				String[] tableAppKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictTableCode.APP.getCacheKey(), appID };
				dataCacheService.put(tableAppKeys, null);
				
			}
//			} else {
//				throw new Exception("该列已经被引用，禁止删除.");
//			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Grid getDictTFactorCodeHead(String tableid_) {
		// 创建Grid
		Grid grid = new Grid();
		// 设置tableID
		grid.setTableID(tableid_);
		grid.setTableDBName("mytableName");
		grid.setTableName("");
		grid.setAppID("");
		int orderId = 0;
		List<Column> columnlist = new ArrayList<Column>();
		Column col1 = setColumn("name", "name", "列中文名称", "列中文名称",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, true);
		Column col2 = setColumn("dbcolumnname", "dbcolumnname", "列英文名称", "列英文名称",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, true);
		Column col3 = setColumn("datalength", "datalength", "列物理长度", "列物理长度",
				JSTypeUtils.getJSDateType(DataType.INT),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, true);
		Column col4 = setColumn("datatype", "datatype", "列类型", "列类型",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, true);
		Column col5 = setColumn("defaultvalue", "defaultvalue", "默认值", "默认值",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, true);
		Column col6 = setColumn("scale", "scale", "小数位数", "小数位数",
				JSTypeUtils.getJSDateType(DataType.INT),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, true);
		Column col7 = setColumn("isreserve", "isreserve", "是否保留", "是否保留",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, true);
		Column col8 = setColumn("isvisible", "isvisible", "是否可见", "是否可见",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, true);
		Column col9 = setColumn("tableid", "tableid", "tableid", "tableid",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, false, false);
		columnlist.add(col1);
		columnlist.add(col2);
		columnlist.add(col3);
		columnlist.add(col4);
		columnlist.add(col5);
		columnlist.add(col6);
		columnlist.add(col7);
		columnlist.add(col8);
		columnlist.add(col9);
		grid.setColumnList(columnlist);
		return grid;

	}

	public Column setColumn(String columnID, String columnDBName,
			String columnName, String alias, String dataType, String showType,
			int orderID, boolean visible, boolean readOnly) {
		Column col = new Column();
		col.setColumnID(columnID);
		col.setColumnDBName(columnDBName);
		col.setColumnName(columnName);
		col.setAlias(alias);
		col.setDataType(dataType);
		col.setShowType(showType);
		col.setOrderID(orderID);
		col.setVisible(visible);
		col.setReadOnly(readOnly);
		return col;
	}

	@Override
	public List<DictTFactorcodePO> getDictTFactorcodePOsByTableIdName(
			String tableid, String name,String columnid) throws Exception {

		List<DictTFactorcodePO> factors = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTableCode.ID.getCacheKey(), tableid,
				"getDictTFactorcodePOsByTableIdName", name,columnid };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			factors = (List<DictTFactorcodePO>) dataCacheService.get(keys);
			return factors;
		}
		try {
			if (tableid != null && !"".equals(tableid)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tableid", tableid);
				map.put("name", name);
				map.put("columnid", columnid);
				factors = dictTFactorcodeMapper.findDictTFactorcode(map);
				// 加入到缓存
				dataCacheService.put(keys, factors);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return factors;
	}

	@Override
	public boolean getCodeTableExist(String tableID) {
		return dictTFactorcodeMapper.getFactorByCsid(tableID) > 0 ? true : false;
	}

	public boolean getCodeTableRegisted(String tableID) {
		return dictTFactorcodeMapper.getCodeTableRegisted(tableID) > 0 ? true : false;
	}
}

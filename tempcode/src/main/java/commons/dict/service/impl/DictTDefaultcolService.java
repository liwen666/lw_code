package commons.dict.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.dict.constants.DictCacheKey;
import com.tjhq.commons.dict.dao.DictTDefaultcolMapper;
import com.tjhq.commons.dict.external.po.DictTDefaultcolPO;
import com.tjhq.commons.dict.service.IDictTDefaultcolService;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;

/**
 * 表默认列管理（缺省列）接口
 * 
 * @author xujingsi
 * 
 */
@Service
@Transactional
public class DictTDefaultcolService implements IDictTDefaultcolService {

	@Resource
	private DictTDefaultcolMapper dictTDefaultcolMapper;
	@Resource
	private IDataCacheService dataCacheService;

	/**
	 * 添加
	 * 
	 * @param DictTDefaultcolPO
	 */
	public void insertDictTDefaultcol(DictTDefaultcolPO dictTDefaultcol) throws Exception{
		try {
			this.dictTDefaultcolMapper.insertDictTDefaultcol(dictTDefaultcol);

			// 清除缓存 BY ZK
			// 清除表处理类型
			String[] dealIDKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTDefaultcol.getDealid() };
			dataCacheService.put(dealIDKeys, null);
			// 清除引用列
			String[] csidKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTDefaultcol.getCsid() };
			dataCacheService.put(csidKeys, null);
			// 清除物理长度
			String[] dtLengthKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					String.valueOf(dictTDefaultcol.getDatalength()) };
			dataCacheService.put(dtLengthKeys, null);
			// 清除精度
			String[] scaleKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					String.valueOf(dictTDefaultcol.getScale()) };
			dataCacheService.put(scaleKeys, null);
			// 清除数据类型
			String[] dtTypeKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					String.valueOf(dictTDefaultcol.getDatatype()) };
			dataCacheService.put(dtTypeKeys, null);
			// 清除物理名称
			String[] dbCmnNameKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTDefaultcol.getDbcolumnname() };
			dataCacheService.put(dbCmnNameKeys, null);
			// 清除默认值
			String[] dftValKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTDefaultcol.getDefaultvalue() };
			dataCacheService.put(dftValKeys, null);
			// 清除数据元
			String[] deIDKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTDefaultcol.getDeid() };
			dataCacheService.put(deIDKeys, null);
			// 清除中文名
			String[] nameKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTDefaultcol.getName() };
			dataCacheService.put(nameKeys, null);
			
			//清除表默认列
            String[] idKeys = { DictCacheKey.CACHE_KEY_DICT,
                    DictCacheKey.DictTable.ID.getCacheKey()};
            dataCacheService.put(idKeys, null);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 修改
	 * 
	 * @param DictTDefaultcolPO
	 */
	public void updateDictTDefaultcol(DictTDefaultcolPO dictTDefaultcol) throws Exception{
		try {
			this.dictTDefaultcolMapper.updateDictTDefaultcol(dictTDefaultcol);

			// 清除缓存 BY ZK
			// 清除表处理类型
			String[] dealIDKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTDefaultcol.getDealid() };
			dataCacheService.put(dealIDKeys, null);
			// 清除引用列
			String[] csidKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTDefaultcol.getCsid() };
			dataCacheService.put(csidKeys, null);
			// 清除物理长度
			String[] dtLengthKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					String.valueOf(dictTDefaultcol.getDatalength()) };
			dataCacheService.put(dtLengthKeys, null);
			// 清除精度
			String[] scaleKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					String.valueOf(dictTDefaultcol.getScale()) };
			dataCacheService.put(scaleKeys, null);
			// 清除数据类型
			String[] dtTypeKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					String.valueOf(dictTDefaultcol.getDatatype()) };
			dataCacheService.put(dtTypeKeys, null);
			// 清除物理名称
			String[] dbCmnNameKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTDefaultcol.getDbcolumnname() };
			dataCacheService.put(dbCmnNameKeys, null);
			// 清除默认值
			String[] dftValKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTDefaultcol.getDefaultvalue() };
			dataCacheService.put(dftValKeys, null);
			// 清除数据元
			String[] deIDKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTDefaultcol.getDeid() };
			dataCacheService.put(deIDKeys, null);
			// 清除中文名
			String[] nameKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTDefaultcol.getName() };
			dataCacheService.put(nameKeys, null);
			
			//清除表默认列
			String[] idKeys = { DictCacheKey.CACHE_KEY_DICT,
	                DictCacheKey.DictTable.ID.getCacheKey()};
			dataCacheService.put(idKeys, null);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * 删除
	 * 
	 * @param String
	 *            dealid
	 */
	public void deleteDictTDefaultcol(DictTDefaultcolPO dictTDefaultcol) throws Exception{
		try {
			this.dictTDefaultcolMapper.deleteDictTDefaultcol(dictTDefaultcol);

			// 清除缓存 BY ZK
			// 清除表处理类型
			String[] dealIDKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTDefaultcol.getDealid() };
			dataCacheService.put(dealIDKeys, null);
			// 清除引用列
			String[] csidKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTDefaultcol.getCsid() };
			dataCacheService.put(csidKeys, null);
			// 清除物理长度
			String[] dtLengthKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					String.valueOf(dictTDefaultcol.getDatalength()) };
			dataCacheService.put(dtLengthKeys, null);
			// 清除精度
			String[] scaleKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					String.valueOf(dictTDefaultcol.getScale()) };
			dataCacheService.put(scaleKeys, null);
			// 清除数据类型
			String[] dtTypeKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					String.valueOf(dictTDefaultcol.getDatatype()) };
			dataCacheService.put(dtTypeKeys, null);
			// 清除物理名称
			String[] dbCmnNameKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTDefaultcol.getDbcolumnname() };
			dataCacheService.put(dbCmnNameKeys, null);
			// 清除默认值
			String[] dftValKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTDefaultcol.getDefaultvalue() };
			dataCacheService.put(dftValKeys, null);
			// 清除数据元
			String[] deIDKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTDefaultcol.getDeid() };
			dataCacheService.put(deIDKeys, null);
			// 清除中文名
			String[] nameKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTDefaultcol.getName() };
			dataCacheService.put(nameKeys, null);
			
			//清除表默认列
            String[] idKeys = { DictCacheKey.CACHE_KEY_DICT,
                    DictCacheKey.DictTable.ID.getCacheKey()};
            dataCacheService.put(idKeys, null);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * *************************************************************************
	 * **********
	 */

	/**
	 * 获取 by dealid
	 * 
	 * @param String
	 *            dealid
	 */
	public List<DictTDefaultcolPO> getDictTDefaultcols(String dealid) throws Exception {
		List<DictTDefaultcolPO> dtds = null;
		try {
			if (dealid != null && !"".equals(dealid)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dealid", dealid);
				dtds = this.dictTDefaultcolMapper.findDictTDefaultcol(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return dtds;
	}

	/**
	 * 获取 by dealid
	 * 
	 * @param String
	 *            dealid
	 */
	public List<DictTDefaultcolPO> getDictTDefaultcols4Show(String dealid) throws Exception{
		List<DictTDefaultcolPO> dtds = null;
		try {
			if (dealid != null && !"".equals(dealid)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dealid", dealid);
				map.put("YES_CODE", "是");
				map.put("NO_CODE", "否");
				dtds = this.dictTDefaultcolMapper.findDictTDefaultcol4Show(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return dtds;
	}

	/**
	 * 获取全部
	 * 
	 * @return List<DictTDefaultcol>
	 */
	public List<DictTDefaultcolPO> getAllDictTDefaultcol() throws Exception{
		List<DictTDefaultcolPO> dtds = null;
		try {
			dtds = this.dictTDefaultcolMapper.getAllDictTDefaultcol();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return dtds;
	}

	@Override
	public Grid getDictTDefaultcolHead(String tableid, String appid) {
		// 创建Grid
		Grid grid = new Grid();
		// 设置tableID
		grid.setTableID(tableid);
		grid.setTableDBName("mytableName");
		grid.setTableName("");
		grid.setAppID(appid);

		List<Column> columnlist = getDictTDefaultcolColumnlist();

		grid.setColumnList(columnlist);

		return grid;
	}

	public List<Column> getDictTDefaultcolColumnlist() {

		int orderId = 0;
		// 创建列
		Column col0 = setColumn("guid", "guid", "主键", "主键",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, false, false);
		col0.setKey(true);
		Column col1 = setColumn("name", "name", "中文名称", "中文名称",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, false);

		Column col2 = setColumn("dbcolumnname", "dbcolumnname", "物理列名", "物理列名",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, false);

		Column col3 = setColumn("datatype", "datatype", "数据类型", "数据类型",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, false);

		Column col4 = setColumn("datalength", "datalength", "长度", "长度",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, false);

		Column col5 = setColumn("scale", "scale", "小数位数", "小数位数",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, false);

		Column col6 = setColumn("defaultvalue", "defaultvalue", "默认值", "默认值",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, false);

		Column col7 = setColumn("isprimary", "isprimary", "是否物理主键", "是否物理主键",
				JSTypeUtils.getJSDateType(DataType.REF),
				ShowType.SHOW_TYPE_LIST, ++orderId, true, false);

		Column col8 = setColumn("islogickey", "islogickey", "是否逻辑主键", "是否逻辑主键",
				JSTypeUtils.getJSDateType(DataType.REF),
				ShowType.SHOW_TYPE_LIST, ++orderId, true, false);

		Column col9 = setColumn("isreserve", "isreserve", "是否保留", "是否保留",
				JSTypeUtils.getJSDateType(DataType.REF),
				ShowType.SHOW_TYPE_LIST, ++orderId, true, false);

		Column col10 = setColumn("csid", "csid", "引用表", "引用表",
				JSTypeUtils.getJSDateType(DataType.REF),
				ShowType.SHOW_TYPE_LIST, ++orderId, true, false);
		
		Column col11 = setColumn("deid", "deid", "数据元", "数据元",
				JSTypeUtils.getJSDateType(DataType.REF),
				ShowType.SHOW_TYPE_LIST, ++orderId, true, false);

		Column col12 = setColumn("isvisible", "isvisible", "是否可见", "是否可见",
				JSTypeUtils.getJSDateType(DataType.REF),
				ShowType.SHOW_TYPE_LIST, ++orderId, true, false);

		Column col13 = setColumn("isupdate", "isupdate", "是否可更新", "是否可更新",
				JSTypeUtils.getJSDateType(DataType.REF),
				ShowType.SHOW_TYPE_LIST, ++orderId, true, false);
		List<Column> columnlist = new ArrayList<Column>();
		columnlist.add(col0);
		columnlist.add(col1);
		columnlist.add(col2);
		columnlist.add(col3);
		columnlist.add(col4);
		columnlist.add(col5);
		columnlist.add(col6);
		columnlist.add(col7);
		columnlist.add(col8);
		columnlist.add(col9);
		columnlist.add(col10);
		columnlist.add(col11);
		columnlist.add(col12);
		columnlist.add(col13);
		return columnlist;
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

	public void saveDictTDefaultcolData(Table table) throws Exception {
		Map<String, Object> tableExtProperties = table.getExtProperties();
		String dealid = (String) tableExtProperties.get("dealid");
		// 获取插入数据
		List<Map<String, Object>> insertList = table.getInsertValues();
		// 获取更新数据
		List<Map<String, Object>> updateList = table.getUpdateValues();
		// 获取删除数据
		List<Map<String, Object>> deleteList = table.getDeleteValues();


		if (insertList != null && insertList.size() > 0) {// 插入数据
			for (Map<String, Object> map : insertList) {
				if (map.get("dbcolumnname") == null
						|| ((String) map.get("dbcolumnname")).trim().equals("")) {
					throw new Exception("物理列名不能为空。");
				}
				String dbcolumnname = ((String) map.get("dbcolumnname")).trim()
						.toUpperCase();
				if (map.get("datatype") == null
						|| ((String) map.get("datatype")).trim().equals("")) {
					throw new Exception("数据类型不能为空。");
				}
//				if (map.get("deid") == null
//						|| ((String) map.get("deid")).trim().equals("")) {
//					throw new Exception("数据元不能为空。");
//				}
				String datatype = ((String) map.get("datatype")).trim();
				if ("YEAR".equals(dbcolumnname)
						|| "PROVINCE".equals(dbcolumnname)
						|| "STATUS".equals(dbcolumnname)
						|| "DBVERSION".equals(dbcolumnname)) {
					throw new Exception(
							"不允许出现'YEAR','PROVINCE','STATUS','DBVERSION' 等字段。");
				}
				DictTDefaultcolPO dtd = new DictTDefaultcolPO();
				dtd.setDealid(dealid);
				dtd.setName(map.get("name") == null
						|| "".equals(map.get("name")) ? "" : map.get("name")
						.toString());
				String orderid = map.get("_sortid") == null
						|| "".equals(map.get("_sortid")) ? "" : map.get(
						"_sortid").toString();
				if (orderid != null && !"".equals(orderid)) {
					dtd.setOrderid(Integer.parseInt(orderid) + 1);
				}
				dtd.setCsid(map.get("csid") == null
						|| "".equals(map.get("csid")) ? "" : map.get("csid")
						.toString());
				String datalength = map.get("datalength") == null
						|| "".equals(map.get("datalength")) ? "" : map.get(
						"datalength").toString();
				if (datalength != null && !"".equals(datalength)) {
					dtd.setDatalength(Integer.parseInt(datalength));
				}
				dtd.setDatatype(Integer.parseInt(datatype));
				dtd.setDefaultvalue(map.get("defaultvalue") == null
						|| "".equals(map.get("defaultvalue")) ? "" : map.get(
						"defaultvalue").toString());
				dtd.setDbcolumnname(dbcolumnname);
				dtd.setIsprimary(map.get("isprimary") == null
						|| "".equals(map.get("isprimary")) ? "" : map.get(
						"isprimary").toString());
				dtd.setIsreserve(map.get("isreserve") == null
						|| "".equals(map.get("isreserve")) ? "" : map.get(
						"isreserve").toString());
				String scale = map.get("scale") == null
						|| "".equals(map.get("scale")) ? "0" : map.get("scale")
						.toString();
				if (DataType.INT.equals(datatype)
						|| DataType.NUMBER.equals(datatype)) {
					if (scale != null && !"".equals(scale)) {
						dtd.setScale(Integer.parseInt(scale));
					}
				}
				dtd.setDeid((String)map.get("deid"));
				dtd.setIsvisible(
						map.get("isvisible") == null || "".equals(map.get("isvisible"))
						? "" : map.get("isvisible").toString());
				dtd.setIsupdate(
					map.get("isupdate") == null || "".equals(map.get("isupdate"))
					? "" : map.get("isupdate").toString());
				insertDictTDefaultcol(dtd);
			}
		}

		if (updateList != null && updateList.size() > 0) { // 更新数据
			for (Map<String, Object> map : updateList) {
				DictTDefaultcolPO dtd = new DictTDefaultcolPO();
//				if (map.get("deid") == null
//						|| ((String) map.get("deid")).trim().equals("")) {
//					throw new Exception("数据元不能为空。");
//				}
				String dbcolumnname = map.get("dbcolumnname") == null
						|| "".equals(map.get("dbcolumnname")) ? "" : map.get(
						"dbcolumnname").toString();
				String datatype = map.get("datatype") == null
						|| "".equals(map.get("datatype")) ? "" : map.get(
						"datatype").toString();
				dbcolumnname = dbcolumnname.toUpperCase();
				dtd.setGuid(map.get("guid") == null
						|| "".equals(map.get("guid")) ? "" : map.get("guid")
						.toString());
				dtd.setDealid(map.get("dealid") == null
						|| "".equals(map.get("dealid")) ? "" : map
						.get("dealid").toString());
				dtd.setName(map.get("name") == null
						|| "".equals(map.get("name")) ? "" : map.get("name")
						.toString());
				String orderid = map.get("orderid") == null
						|| "".equals(map.get("orderid")) ? "" : map.get(
						"orderid").toString();
				if (orderid != null && !"".equals(orderid)) {
					dtd.setOrderid(Integer.parseInt(orderid));
				}
				dtd.setCsid(map.get("csid") == null
						|| "".equals(map.get("csid")) ? "" : map.get("csid")
						.toString());
				String datalength = map.get("datalength") == null
						|| "".equals(map.get("datalength")) ? "" : map.get(
						"datalength").toString();
				if (datalength != null && !"".equals(datalength)) {
					dtd.setDatalength(Integer.parseInt(datalength));
				}

				if (datatype != null && !"".equals(datatype)) {
					dtd.setDatatype(Integer.parseInt(datatype));
				}
				dtd.setDbcolumnname(dbcolumnname);
				dtd.setDefaultvalue(map.get("defaultvalue") == null
						|| "".equals(map.get("defaultvalue")) ? "" : map.get(
						"defaultvalue").toString());
				dtd.setIsprimary(map.get("isprimary") == null
						|| "".equals(map.get("isprimary")) ? "" : map.get(
						"isprimary").toString());
				dtd.setIslogickey(map.get("islogickey") == null
						|| "".equals(map.get("islogickey")) ? "" : map.get(
						"islogickey").toString());
				dtd.setIsreserve(map.get("isreserve") == null
						|| "".equals(map.get("isreserve")) ? "" : map.get(
						"isreserve").toString());
				String scale = map.get("scale") == null
						|| "".equals(map.get("scale")) ? "0" : map.get("scale")
						.toString();
				if (DataType.INT.equals(datatype)
						|| DataType.NUMBER.equals(datatype)) {
					if (scale != null && !"".equals(scale)) {
						dtd.setScale(Integer.parseInt(scale));
					}
				}
				dtd.setDeid((String)map.get("deid"));
				dtd.setIsvisible(map.get("isvisible") == null
						|| "".equals(map.get("isvisible")) ? "" : map.get(
						"isvisible").toString());
				dtd.setIsupdate(map.get("isupdate") == null
						|| "".equals(map.get("isupdate")) ? "" : map.get(
						"isupdate").toString());
				updateDictTDefaultcol(dtd);
			}
		}
		if (deleteList != null && deleteList.size() > 0) { // 删除数据
			for (Map<String, Object> map : deleteList) {
				DictTDefaultcolPO dtd = new DictTDefaultcolPO();
				if (map.get("guid") == null
						|| ((String) map.get("guid")).trim().equals("")) {
					continue;
				}
				dtd.setGuid(map.get("guid") == null
						|| "".equals(map.get("guid")) ? "" : map.get("guid")
						.toString());
				deleteDictTDefaultcol(dtd);
			}
		}
		
		//判断物理列名重复
		List<String> repeateColumnList = dictTDefaultcolMapper.findRepeateDictTDefaultcol(dealid);
		if (repeateColumnList != null && repeateColumnList.size() > 0){
			StringBuffer repeateInfo = new StringBuffer("以下物理列名重复：");
			for (String repeateColumn : repeateColumnList) {
				repeateInfo.append(repeateColumn).append("  ");
			}
			throw new Exception(repeateInfo.append(".").toString());
		}
	}

	@Override
	public String getDefaultcolCountByDeal(String dealID,String tableId)  throws Exception{
		Map map = new HashMap();
		map.put("dealID", dealID);
		map.put("tableId", tableId);
		List<DictTDefaultcolPO> defaultcolPOlist = (List<DictTDefaultcolPO>) 
				dictTDefaultcolMapper.findDictTDefaultcolbyDealIDandTableId(map);
		String result = "o"; 
		if(defaultcolPOlist!=null&&defaultcolPOlist.size()>0){
			for(DictTDefaultcolPO dictTDefaultcolPO:defaultcolPOlist){
				String dbcolumnname = dictTDefaultcolPO.getDbcolumnname();
				if(result =="o"){
					result = dbcolumnname;
				}else{
					result = result + "," + dbcolumnname;
				}
			}
			result = "默认列沒有包含"+result+",操作失败!";
		}
		return result;
	}
}

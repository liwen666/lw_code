package commons.dict.external.service.impl;

import gov.mof.fasp2.dic.dataset.dto.DataSetDTO;
import gov.mof.fasp2.dic.dataset.service.IDataSetService;
import gov.mof.fasp2.dic.table.dto.DicColumnDTO;
import gov.mof.fasp2.dic.table.dto.DicTableDTO;
import gov.mof.fasp2.dic.table.service.IDicTableQueryService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longtu.framework.util.ServiceFactory;
import com.tjhq.commons.Constants;
import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.dict.constants.DictCacheKey;
import com.tjhq.commons.dict.dao.DictTAppregisterMapper;
import com.tjhq.commons.dict.dao.DictTDefaultcolMapper;
import com.tjhq.commons.dict.external.dao.DictTFactorMapper;
import com.tjhq.commons.dict.external.dao.DictTModelcodeMapper;
import com.tjhq.commons.dict.external.po.DictTAppregisterPO;
import com.tjhq.commons.dict.external.po.DictTDefaultcolPO;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTFactorcodePO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.dict.service.IDictDBExecuteService;
import com.tjhq.commons.dict.util.DictChangeDtoForSYNC;
import com.tjhq.commons.dict.util.DictDBConstants;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.TreePO;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.install.dao.IInstallDataBaseDao;
import com.tjhq.commons.setting.external.po.TreeNode;

/**
 * 表接口实现
 * 
 * @author xujingsi
 * 
 */
@Service
@Transactional
public class DictTModelcodeService implements IDictTModelcodeService {
	@Resource
	private DictTDefaultcolMapper dictTDefaultcolMapper;
	@Resource
	private DictTFactorMapper dictTFactorMapper;
	@Resource
	private DictTAppregisterMapper dictTAppregisterMapper;
	@Resource
	private DictTFactorcodeService dictTFactorcodeService;
	@Resource
	private DictTModelcodeMapper dictTModelcodeMapper;
	@Resource
	private IDictDBExecuteService dictDBExecuteService;
	@Resource
	private IDataCacheService dataCacheService;
	@Resource
	private IInstallDataBaseDao installDataBaseDao;
	/**
	 * 添加 1物理表
	 */
	@Override
	public void insertDictTModelcodeForPhysics(DictTModelcodePO dtm) throws Exception {
		String tableid = dictDBExecuteService.getUUID();// id
		dtm.setTableid(tableid);
		try {
			// 取到缺省列
			List<DictTDefaultcolPO> defaultColList = 
					dictTDefaultcolMapper.findDictTDefaultfactortcodecol();
			if (defaultColList != null && defaultColList.size() > 0) {
				String tableName = dtm.getDbtablename().toUpperCase();
				// 1物理表
				String status = dictDBExecuteService.getGlobalIsmultdb();
				String prefix = DictDBConstants.PREFIX_TABLE;
				if (status.equals("0")) {
					prefix = "";
				}
				try {
					Map<String, String> sqlmap = this.dictDBExecuteService.createPhysicsTablecodeSql(defaultColList,
									tableName, dtm.getName(), status,"1", dtm.getDynamicwhere());
					if (sqlmap != null && sqlmap.size() > 0) {
						// 记录到DictTModelcode表
						try {
							dtm.setDbtablename(tableName);
							List<DictTFactorcodePO> dtfList = new ArrayList<DictTFactorcodePO>();
							// 记录到DictTFactorcode表
							int i = 1;
							for (DictTDefaultcolPO defaultCol : defaultColList) {
								String dbcolumnname = defaultCol.getDbcolumnname().toUpperCase().trim();
								if (!"YEAR".equals(dbcolumnname)&& !"PROVINCE".equals(dbcolumnname)) {
									DictTFactorcodePO dtf = new DictTFactorcodePO();
									dtf.setColumnid(this.dictDBExecuteService.getUUID());
									dtf.setTableid(dtm.getTableid());
									dtf.setDatatype(Integer.parseInt(DataType.STRING));
									// 如果是数字型
									if (1 == defaultCol.getDatatype()) {
										if (defaultCol.getScale() != null&& defaultCol.getScale() != 0) {
											dtf.setDatatype(Integer.parseInt(DataType.NUMBER));
										} else {
											dtf.setDatatype(Integer.parseInt(DataType.INT));
										}
									}
									dtf.setDatalength(defaultCol.getDatalength());
									dtf.setDbcolumnname(defaultCol.getDbcolumnname());
									dtf.setDefaultvalue(defaultCol.getDefaultvalue());
									dtf.setName(defaultCol.getName().trim());
									dtf.setIsreserve(defaultCol.getIsreserve());
									dtf.setIsvisible(defaultCol.getIsvisible());
									dtf.setScale(defaultCol.getScale());
									dtf.setDeid(defaultCol.getDeid());
									dtf.setOrderid(i);
									dtfList.add(dtf);
									// 创建表列不单独同步到平台，和表一起同步
									this.dictTFactorcodeService.insertDictTFactorcode(dtf);
									i++;
								}
							}
							dtm.setDictTFactorcodeList(dtfList);
						} catch (Exception e) {
							e.printStackTrace();
							throw new Exception(e.getMessage());
						}
						String pKsql = sqlmap.get(DictDBConstants.TYPE_PRIMARY);
						String indexSql = sqlmap.get(DictDBConstants.TYPE_INDEX);
						String add_comments = sqlmap.get(DictDBConstants.TYPE_TABLE_COMMENTS);
						if ("0".equals(status)) {
							// 单创建物理表
							String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE + "'" + prefix + tableName + "'";
							Integer isTableExist = this.dictDBExecuteService.getIfExistsInDB(ifExistsTableSql);// 同名物理表
							//视图是否存在
							String ifExistsViewSql_ = DictDBConstants.IF_EXISTS_TYPE_VIEW+ "'" + tableName + "'";
							Integer isViewExist = dictDBExecuteService.getIfExistsInDB(ifExistsViewSql_);// 同名视图
							if (isTableExist == 0 &&  isViewExist == 0) {
								this.insertDictTModelcode(dtm);
								try {
									String createTableSql = sqlmap
											.get(DictDBConstants.TYPE_TABLE);
									this.dictDBExecuteService
									.ExecDllLongForParam(createTableSql);
									// +主键
									if (pKsql != null && !"".equals(pKsql)) {
										this.dictDBExecuteService.ExecDllLongForParam(pKsql);
									}
									// +索引
									if (indexSql != null&& !"".equals(indexSql)) {
										this.dictDBExecuteService.ExecDllLongForParam(indexSql);
									}
									// +add_comments
									if (add_comments != null&& !"".equals(add_comments)) {
										this.dictDBExecuteService.ExecDllLongForParam(add_comments);
									}
								} catch (Exception e) {
									e.printStackTrace();
									throw new Exception(e.getMessage());
								}
							} else {
								throw new SQLException(tableName+ "物理表已经存在.", "CREATETABLE");
							}
						} else if (("1".equals(status) || "2".equals(status))) {
							// 创建物理表 +分区
							String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE+ "'" + prefix + tableName + "'";
							String createTableSql = sqlmap.get(DictDBConstants.TYPE_PARTITION);
							Integer isTableExist = dictDBExecuteService.getIfExistsInDB(ifExistsTableSql);// 同名物理表
							//视图是否存在
							String ifExistsViewSql_ = DictDBConstants.IF_EXISTS_TYPE_VIEW+ "'" + tableName + "'";
							Integer isViewExist = dictDBExecuteService.getIfExistsInDB(ifExistsViewSql_);// 同名视图
							if (isTableExist == 0 && isViewExist == 0) {
								try {
									this.insertDictTModelcode(dtm); 
									this.dictDBExecuteService.ExecDllLongForParam(createTableSql);
									
									// +视图
									String ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW + "'" + tableName + "'";
									String createViewSql = sqlmap.get(DictDBConstants.TYPE_VIEW);
									if (isViewExist == 0) {
										this.dictDBExecuteService.ExecDllLongForParam(createViewSql);
									} else {
										throw new Exception("" + tableName+ " :视图已经存在.");
									}
									// +主键
									if (pKsql != null && !"".equals(pKsql)) {
										this.dictDBExecuteService.ExecDllLongForParam(pKsql);
									}
									// +索引
									if (indexSql != null&& !"".equals(indexSql)) {
										this.dictDBExecuteService.ExecDllLongForParam(indexSql);
									}
									// +add_comments
									if (add_comments != null
											&& !"".equals(add_comments)) {
										this.dictDBExecuteService.ExecDllLongForParam(add_comments);
									}
								} catch (Exception e) {
									e.printStackTrace();
									throw new Exception(e.getMessage());
								}
							} else {
								throw new SQLException(prefix + tableName+ " :物理表（分区）已经存在.", "CREATETABLE");
							}
						}
					}
				} catch (Exception e) {
					if (e instanceof SQLException && ((SQLException) e).getSQLState().equals("CREATETABLE")) {
						throw e;
					} else {
						String ifExistsViewSql_ = DictDBConstants.IF_EXISTS_TYPE_VIEW+ "'" + tableName + "'";
						Integer isViewExist = dictDBExecuteService.getIfExistsInDB(ifExistsViewSql_);// 同名视图
						if (isViewExist == 1) {
							dictDBExecuteService.ExecDllLongForParam("DROP VIEW "+ tableName);
						}
						throw e;
					}
				}
			} else {
				throw new Exception("未找到缺省列信息");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage().replace("\"", "\'"));
		}
	}
	
	
	@Override
	public DictTModelcodePO getDictTModelcodePOByID(String tableid) {
		DictTModelcodePO dtm = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTableCode.ID.getCacheKey(), tableid,
				"getDictTModelcodePOByID" };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			dtm = (DictTModelcodePO) dataCacheService.get(keys);
			return dtm;
		}
		if (tableid != null && !"".equals(tableid)) {
			dtm = dictTModelcodeMapper.getDictTModelcode(tableid);
			// 加入到缓存
			dataCacheService.put(keys, dtm);
		}
		return dtm;
	}

	@Override
	public DictTModelcodePO getDictTModelcodePOByDBtableName(String dbtablename) {
		DictTModelcodePO dtm = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTableCode.DBNAME.getCacheKey(), dbtablename,
				"getDictTModelcodePOByDBtableName" };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			dtm = (DictTModelcodePO) dataCacheService.get(keys);
			return dtm;
		}
		if (dbtablename != null && !"".equals(dbtablename)) {
			dtm = dictTModelcodeMapper.getDictTModelcodeByDBName(dbtablename);
			// 加入到缓存
			dataCacheService.put(keys, dtm);
		}
		return dtm;
	}

	@Override
	public List<DictTModelcodePO> findDictTModelcodeByArgs(Map<String,Object> paramMap) {

		List<DictTModelcodePO> list = null;
//		// 缓存key
//		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
//				DictCacheKey.DictTableCode.APP.getCacheKey(), appid,
//				"findDictTModelcodeByArgs" };
//		// 如果缓存中存在
//		if (dataCacheService.get(keys) != null) {
//			list = (List<DictTModelcodePO>) dataCacheService.get(keys);
//			return list;
//		}
//		Map<String, Object> map = new HashMap<String, Object>();
//		if (appid != null && !"".equals(appid)) {
//			map.put("appid", appid);
//		}
		list = this.dictTModelcodeMapper.findDictTModelcode(paramMap);
//		// 加入到缓存
//		dataCacheService.put(keys, list);
		return list;
	}

	/**
	 * 取到未登记的平台代码表列表
	 * 
	 * @param appID
	 *            业务系统ID
	 * @return 未登记的平台代码表列表
	 * @throws Exception
	 */
	public List<DictTModelcodePO> getDictModelCodeElementCodeList(String appID)
			throws Exception {
		IDataSetService dataSetService = (IDataSetService) ServiceFactory
				.getBean("fasp2.dic.dataset.service");
		IDicTableQueryService dicQueryService = (IDicTableQueryService) ServiceFactory
				.getBean("fasp2.dictable.queryService");
		List<DictTModelcodePO> dicTableDTOList = new ArrayList<DictTModelcodePO>();
		Map<String, String> registeredModelCodeMap = new HashMap<String, String>();
		List<DictTModelcodePO> registeredModelCodeList = dictTModelcodeMapper
				.getDictTModelcodeListByAppID(appID);
		for (DictTModelcodePO dtc : registeredModelCodeList) {
			registeredModelCodeMap.put(dtc.getDbtablename().toUpperCase().replaceFirst("_V_", "_T_"), "");
		}
		List<DataSetDTO> dataset = dataSetService.getAllDataSet();
		for (DataSetDTO dsd : dataset) {
			if (dsd.getTablecode() == null
					|| dsd.getTablecode().trim().length() == 0) {
				continue;
			}
			DicTableDTO dt = dicQueryService.getDicTableWithNoException(dsd.getTablecode());
			// 已经注册过的不再显示
			if (dt != null
					&& !registeredModelCodeMap.containsKey(dt.getTablecode()
							.toUpperCase())) {
				DictTModelcodePO dtmc = DictChangeDtoForSYNC.changeDicTableDTO(
						dsd, dt);
				dicTableDTOList.add(dtmc);
			}
		}
		return dicTableDTOList;
	}

	/**
	 * 登记平台代码表信息到业务系统
	 * 
	 * @param appID
	 *            业务系统ID
	 * @param selectedTableCodeList
	 *            选择的平台代码表
	 * @throws Exception
	 */
	public void saveElementCode2DictModelCode(String appID,
			List<String> selectedTableCodeList) throws Exception {
		if (appID == null || appID.trim().equals("")) {
			throw new Exception("业务系统ID不能为空");
		}
		if (selectedTableCodeList == null || selectedTableCodeList.size() == 0) {
			throw new Exception("没有选择可以登记的代码表");
		}
		IDataSetService dataSetService = (IDataSetService) ServiceFactory
				.getBean("fasp2.dic.dataset.service");
		IDicTableQueryService dicQueryService = (IDicTableQueryService) ServiceFactory
				.getBean("fasp2.dictable.queryService");
		for (String tableCode : selectedTableCodeList) {
			DicTableDTO dt = dicQueryService.getDicTableWithNoException(tableCode);
			if (dt == null) {
				throw new Exception("平台系统未找到" + tableCode + "表");
			}
			List<DataSetDTO> datasetList = dataSetService
					.getDataSetsByWhereSql(" TABLECODE = '" + tableCode + "'");
			if (datasetList == null || datasetList.size() == 0) {
				throw new Exception("平台系统" + tableCode + "表未找到业务要素定义");
			}
			try {
				DataSetDTO dsd = datasetList.get(0);
				DictTModelcodePO dtmc = DictChangeDtoForSYNC.changeDicTableDTO(
						dsd, dt);
				String tableID = dictDBExecuteService.getUUID();
				dtmc.setAppid(appID);
				dtmc.setTableid(tableID);
				dtmc.setIsfasp("1");
				insertDictTModelcode(dtmc);
				for (DicColumnDTO dc : dt.getListCol()) {
					DictTFactorcodePO dtfc = DictChangeDtoForSYNC
							.changeDicColumnDTO(dc);
					dtfc.setTableid(tableID);
					dtfc.setColumnid(dictDBExecuteService.getUUID());
					dictTFactorcodeService.insertDictTFactorcode(dtfc);
				}
				// 清除app列缓存
				String[] columnAppKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictFactorCode.APP.getCacheKey(), appID };
				dataCacheService.put(columnAppKeys, null);
				// 清除app表缓存
				String[] tableAppKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictTableCode.APP.getCacheKey(), appID };
				dataCacheService.put(tableAppKeys, null);
				// 通过平台dblink是否存在，判断是否是分库，如果是分库，自动创建同义词
				String ifExistFaspDBLinkSql = new StringBuffer(
						"SELECT COUNT(1) FROM USER_DB_LINKS WHERE DB_LINK='")
						.append(DictDBConstants.FASP_DBLINKNAME).append("'")
						.toString();
//				Integer isFaspDBLinkExist = dictDBExecuteService
//						.getIfExistsInDB(ifExistFaspDBLinkSql);
//				if (isFaspDBLinkExist == 1) {
//					String createSynonymSql = new StringBuffer(
//							"create or replace synonym ")
//							.append(dtmc.getDbtablename()).append(" for ")
//							.append(dtmc.getDbtablename()).append("@")
//							.append(DictDBConstants.FASP_DBLINKNAME).toString();
//					dictDBExecuteService.ExecDllLongForParam(createSynonymSql);
//				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("登记平台代码表到业务系统出错");
			}
		}
	}

	
	/**
	 * 去平台查询一个视图的所有列
	 * @param selectedTableCode
	 * @throws Exception
	 */
	@Override
	public List<DictTFactorcodePO> getElementCode2DictFactorCode(
			String selectedTableCode) throws Exception {
		List<DictTFactorcodePO> listFactorcode = new ArrayList<DictTFactorcodePO>();

		selectedTableCode = selectedTableCode.replaceFirst("_V_", "_T_");
		IDataSetService dataSetService = (IDataSetService) ServiceFactory
				.getBean("fasp2.dic.dataset.service");
		IDicTableQueryService dicQueryService = (IDicTableQueryService) ServiceFactory
				.getBean("fasp2.dictable.queryService");
		DicTableDTO dt = dicQueryService.getDicTableWithNoException(selectedTableCode);
		if (dt == null) {
			throw new Exception("平台系统未找到" + selectedTableCode + "表");
		}
		try {
			/**
			 * 不需要插入DictTModelcode
			 */
//			String tableID = dictDBExecuteService.getUUID();
//			dtmc.setTableid(tableID);
//			dtmc.setIsfasp("1");
//			insertDictTModelcode(dtmc);
			for (DicColumnDTO dc : dt.getListCol()) {
				DictTFactorcodePO dtfc = DictChangeDtoForSYNC
						.changeDicColumnDTO(dc);
				listFactorcode.add(dtfc);
				/**
				 * 不需要插入TFactorcode
				 */
//				dtfc.setTableid(tableID);
//				dtfc.setColumnid(dictDBExecuteService.getUUID());
//				dictTFactorcodeService.insertDictTFactorcode(dtfc);
			}
			// 清除app列缓存
			String[] columnAppKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictFactorCode.APP.getCacheKey() };
			dataCacheService.put(columnAppKeys, null);
			// 清除app表缓存
			String[] tableAppKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTableCode.APP.getCacheKey()};
			dataCacheService.put(tableAppKeys, null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("登记平台代码表到业务系统出错");
		}
		return listFactorcode;
	}
	
	
	@Override
	public Grid getDictTModelCodeHead(String tableid_) {
		// 创建Grid
		Grid grid = new Grid();
		// 设置tableID
		grid.setTableID(tableid_);
		grid.setTableDBName("mytableName");
		grid.setTableName("w221ss");
		grid.setAppID("q");
		int orderId = 0;
		List<Column> columnlist = new ArrayList<Column>();
		Column col0 = setColumn("tableid", "tableid", "主键", "主键",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, false, false);
		col0.setKey(true);
		Column col1 = setColumn("name", "name", "中文名称", "中文名称",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, true);
		Column col2 = setColumn("dbtablename", "dbtablename", "物理名称", "物理名称",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, true);
		Column col3 = setColumn("sqlcon", "sqlcon", "sql内容", "sql内容",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, true);
		Column col4 = setColumn("dynamicwhere", "dynamicwhere", "where条件",
				"where条件", JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, true);
		Column col5 = setColumn("isrepbase", "isrepbase", "保留", "保留",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, true);
		Column col6 = setColumn("islvl", "islvl", "层次编码", "层次编码",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, true);
		Column col7 = setColumn("isorgid", "isorgid", "组织机构", "组织机构",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, true);
		Column col8 = setColumn("isfasp", "isfasp", "是否平台", "是否平台",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, true);
		Column col9 = setColumn("tabletype", "tabletype", "表类型", "表类型",
				JSTypeUtils.getJSDateType(DataType.STRING),
				ShowType.SHOW_TYPE_HTML, ++orderId, true, true);
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
	public void insertDictTModelcode(DictTModelcodePO dictTModelcodePO)
			throws Exception {

		try {
			this.dictTModelcodeMapper.insertDictTModelcode(dictTModelcodePO);
			String appID = dictTModelcodePO.getAppid();
			// 清除app列缓存
			String[] columnAppKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictFactorCode.APP.getCacheKey(), appID };
			dataCacheService.put(columnAppKeys, null);
			// 清除所有列ID缓存
			String[] columnIDKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictFactorCode.ID.getCacheKey() };
			dataCacheService.put(columnIDKeys, null);
			// 清除所有列物理列名缓存
			String[] columnDBNameKeys = {
					DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictFactorCode.DBNAME
							.getCacheKey() };
			dataCacheService.put(columnDBNameKeys, null);
			// 清除所有表ID缓存
			String[] tableIDKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTableCode.ID.getCacheKey() };
			dataCacheService.put(tableIDKeys, null);
			// 清除表物理名缓存
			String[] tableDBNameKeys = {
					DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTableCode.DBNAME.getCacheKey(),
					dictTModelcodePO.getDbtablename() };
			dataCacheService.put(tableDBNameKeys, null);
			// 清除app表缓存
			String[] tableAppKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTableCode.APP.getCacheKey(),
					appID };
			// 清除所有业务表类缓存
			String[] allDICTKeys = { DictCacheKey.CACHE_KEY_DICT };
			dataCacheService.put(allDICTKeys, null);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void updateDictTModelcode(DictTModelcodePO dictTModelcodePO)
			throws Exception {

		String tableid = dictTModelcodePO.getTableid();
		if (tableid != null && !"".equals(tableid)) {
			// Map<String, Object> whereMap = new HashMap<String, Object>();
			// whereMap.put("csid", tableid);
			// List<DictTDefaultcolPO> dtdcList = this.dictTDefaultcolMapper
			// .findDictTDefaultcol(whereMap);
			// if (dtdcList == null || dtdcList.size() < 1) {// 默认列 是否有引用
			// List<DictTFactorPO> dtdList = this.dictTFactorMapper
			// .findDictTFactor(whereMap);
			// if (dtdList == null || dtdList.size() < 1) {// 列 是否有引用
			try {
				String dbname = dictTModelcodePO.getDbtablename().toUpperCase()
						.trim();
				List<Map<String, Object>> list = this.dictDBExecuteService
						.getColumnByViewName(dbname);
				boolean hasStatus = false, hasCode = false;
				for (Map<String, Object> col : list) {
					String dbColumnName = col.get("COLUMN_NAME").toString()
							.toUpperCase();
					if (dbColumnName.equals("STATUS"))
						hasStatus = true;
					if (dbColumnName.equals("CODE"))
						hasCode = true;
				}

				// 平台代码表登记默认where条件
				if ("1".equals(dictTModelcodePO.getIsfasp())) {
					String dynamicWhere = dictTModelcodePO.getDynamicwhere();
					if (dynamicWhere == null || ("").equals(dynamicWhere)) {
						if (hasStatus)
							dynamicWhere = " status=1 ";
						if (hasCode)
							dynamicWhere += " order by code ";
					} else {
						if (!dynamicWhere.toLowerCase().contains("status=1")
								&& hasStatus)
							dynamicWhere = " status=1 AND " + dynamicWhere;
						if (!dynamicWhere.toLowerCase().toLowerCase()
								.contains("order")
								&& hasCode)
							dynamicWhere += " order by code ";
						else {
							if (!dynamicWhere.toLowerCase().contains("code")
									&& hasCode)
								dynamicWhere += ", code";
						}
					}
					dictTModelcodePO.setDynamicwhere(dynamicWhere);
				}

				this.dictTModelcodeMapper
						.updateDictTModelcode(dictTModelcodePO);
				// 清除所有列ID缓存
				String[] columnIDKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictFactorCode.ID.getCacheKey() };
				dataCacheService.put(columnIDKeys, null);
				// 清除所有列物理列名缓存
				String[] columnDBNameKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictFactorCode.DBNAME.getCacheKey() };
				dataCacheService.put(columnDBNameKeys, null);
				// 清除表ID缓存
				String[] tableIDKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictTableCode.ID.getCacheKey(),
						dictTModelcodePO.getTableid() };
				dataCacheService.put(tableIDKeys, null);
				// 清除表物理名缓存
				String[] tableDBNameKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictTableCode.DBNAME.getCacheKey(),
						dictTModelcodePO.getDbtablename() };
				dataCacheService.put(tableDBNameKeys, null);
				String appID = dictTModelcodePO.getAppid();
				// 清除app列缓存
				String[] columnAppKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictFactorCode.APP.getCacheKey(), appID };
				dataCacheService.put(columnAppKeys, null);
				// 清除app表缓存
				String[] tableAppKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictTableCode.APP.getCacheKey(), appID };
				dataCacheService.put(tableAppKeys, null);
				// 清除所有业务表类缓存
				String[] allDICTKeys = { DictCacheKey.CACHE_KEY_DICT };
				dataCacheService.put(allDICTKeys, null);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			// } else {
			// throw new Exception("'" + tableid + "'列中已经被引用,禁止修改.");
			// }
			// } else {
			// throw new Exception("'" + tableid + "'中默认列已经被引用,禁止修改.");
			// }
			// } else {
			// throw new Exception("'" + tableid + "'默认列中已经被引用,禁止删除.");
			// }
		} else {
			throw new Exception("tableid 为null或'' ,找不到对象.");
		}
	}

	@Override
	public void deleteDictTModelcode(String tableid) throws Exception {

		if (tableid != null && !"".equals(tableid)) {
			Map<String, Object> whereMap = new HashMap<String, Object>();
			whereMap.put("csid", tableid);
			List<DictTDefaultcolPO> dtdcList = this.dictTDefaultcolMapper
					.findDictTDefaultcol(whereMap);
			if (dtdcList == null || dtdcList.size() < 1) {// 默认列 是否有引用
				List<DictTFactorPO> dtdList = this.dictTFactorMapper
						.findDictTFactor(whereMap);
				if (dtdList == null || dtdList.size() < 1) {// 列 是否有引用
					try {
						List<DictTFactorcodePO> dtfcList = this.dictTFactorcodeService
								.getDictTFactorcodePOsByTableId(tableid);
						for (DictTFactorcodePO dtfc : dtfcList) {
							dictTFactorcodeService.deleteDictTFactorcode(dtfc
									.getColumnid());
						}
						DictTModelcodePO dtmCode = getDictTModelcodePOByID(tableid);
						this.dictTModelcodeMapper.deleteDictTModelcode(tableid);
						// 清除所有列ID缓存
						String[] columnIDKeys = { DictCacheKey.CACHE_KEY_DICT,
								DictCacheKey.DictFactorCode.ID.getCacheKey() };
						dataCacheService.put(columnIDKeys, null);
						// 清除所有列物理列名缓存
						String[] columnDBNameKeys = {
								DictCacheKey.CACHE_KEY_DICT,
								DictCacheKey.DictFactorCode.DBNAME
										.getCacheKey() };
						dataCacheService.put(columnDBNameKeys, null);
						// 清除所有表ID缓存
						String[] tableIDKeys = { DictCacheKey.CACHE_KEY_DICT,
								DictCacheKey.DictTableCode.ID.getCacheKey() };
						dataCacheService.put(tableIDKeys, null);
						// 清除表物理名缓存
						String[] tableDBNameKeys = {
								DictCacheKey.CACHE_KEY_DICT,
								DictCacheKey.DictTableCode.DBNAME.getCacheKey(),
								dtmCode.getDbtablename() };
						dataCacheService.put(tableDBNameKeys, null);
						String appID = dtmCode.getAppid();
						// 清除app列缓存
						String[] columnAppKeys = { DictCacheKey.CACHE_KEY_DICT,
								DictCacheKey.DictFactorCode.APP.getCacheKey(),
								appID };
						dataCacheService.put(columnAppKeys, null);
						// 清除app表缓存
						String[] tableAppKeys = { DictCacheKey.CACHE_KEY_DICT,
								DictCacheKey.DictTableCode.APP.getCacheKey(),
								appID };
						dataCacheService.put(tableAppKeys, null);
						// 清除所有业务表类缓存
						String[] allDICTKeys = { DictCacheKey.CACHE_KEY_DICT };
						dataCacheService.put(allDICTKeys, null);
					} catch (Exception e) {
						throw new Exception(e.getMessage());
					}
				} else {
					throw new Exception("'" + tableid + "'列中已经被引用,禁止删除.");
				}
			} else {
				throw new Exception("'" + tableid + "'默认列中已经被引用,禁止删除.");
			}
		} else {
			throw new Exception("tableid 为null或'' ,找不到对象.");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertDictTModelForCustomcode(DictTModelcodePO dictTModelcodePO)
			throws Exception {

		String dbname = dictTModelcodePO.getDbtablename().toUpperCase().trim();
		Map<String, String> alwaysColumnmap = new HashMap<String, String>();
		alwaysColumnmap.put("GUID", "GUID");
		alwaysColumnmap.put("CODE", "CODE");
		alwaysColumnmap.put("NAME", "NAME");
		alwaysColumnmap.put("SUPERGUID", "SUPERGUID");
		alwaysColumnmap.put("ISLEAF", "ISLEAF");
		alwaysColumnmap.put("LEVELNO", "LEVELNO");
		// 列记录到DictTFactorcode表
		try {
			Map<String, String> columnmap = new HashMap<String, String>();
			List<Map<String, Object>> list = this.dictDBExecuteService
					.getColumnByViewName(dbname);
			List<DictTFactorcodePO> dtfcList = new ArrayList<DictTFactorcodePO>();

			boolean hasStatus = false, hasCode = false;
			for (Map<String, Object> col : list) {
				String dbColumnName = col.get("COLUMN_NAME").toString().toUpperCase();
				if(dbColumnName.equals("STATUS"))	hasStatus = true;
				if(dbColumnName.equals("CODE"))	hasCode = true;

				DictTFactorcodePO dtf = new DictTFactorcodePO();
				// 数字型
				String dataType = (String) col.get("DATA_TYPE");
				String dataLength = col.get("DATA_LENGTH") == null ? "0" : col
						.get("DATA_LENGTH").toString();
				String newDataType = DataType.STRING;
				if ("NUMBER".equals(dataType)) {
					// 如果是没有精度，是整型
					String dataScale = col.get("DATA_SCALE") == null ? "0"
							: col.get("DATA_SCALE").toString();
					if (dataScale == null || dataScale.equals("0")) {
						newDataType = DataType.INT;
						dtf.setScale(0);
					} else {
						newDataType = DataType.NUMBER;
						dtf.setScale(Integer.parseInt(dataScale));
					}
					dataLength = col.get("DATA_PRECISION") == null ? "0" : col
							.get("DATA_PRECISION").toString();
				}
				// 字符型
				else if ("VARCHAR2".equals(dataType) || "CHAR".equals(dataType)) {
					newDataType = DataType.STRING;
				}
				if (dataLength == null || dataLength.equals("")) {
					dataLength = "0";
				}
				dtf.setDatatype(Integer.parseInt(newDataType));
				dtf.setDatalength(Integer.parseInt(dataLength));
				dtf.setDbcolumnname(dbColumnName.toUpperCase());
				dtf.setName(dbColumnName.toUpperCase());
				dtf.setDefaultvalue(col.get("DATA_DEFAULT") == null ? null
						: col.get("DATA_DEFAULT").toString());
				if (dtf.getDefaultvalue() == null) {
					dtf.setDefaultvalue("");
				}
				dtf.setIsreserve("0");
				dtf.setIsvisible("0");
				// -------------------------------------
				dtfcList.add(dtf);
				columnmap.put(dbColumnName, dbColumnName);
			}
			boolean flag = true;
			for (Entry<String, String> m : alwaysColumnmap.entrySet()) {
				if (columnmap.get(m.getKey()) == null) {
					flag = false;
					break;
				}
			}
			// 保存
			if (flag) {
				try {
					String tableid = this.dictDBExecuteService.getUUID();
					dictTModelcodePO.setTableid(tableid);
					// 平台用代码表ID
					dictTModelcodePO.setFaspcsid(tableid);

					// 平台代码表登记默认where条件
					if("1".equals(dictTModelcodePO.getIsfasp())) {
						String dynamicWhere = dictTModelcodePO.getDynamicwhere();
						if(dynamicWhere == null || ("").equals(dynamicWhere)) {
							if(hasStatus) dynamicWhere = " status=1 ";
							if(hasCode) dynamicWhere += " order by code ";
						} else {
							if(!dynamicWhere.toLowerCase().contains("status=1") && hasStatus)
								dynamicWhere = " status=1 AND " + dynamicWhere;
							if(!dynamicWhere.toLowerCase().contains("order") && hasCode)
								dynamicWhere += " order by code ";
							else {
								if(!dynamicWhere.toLowerCase().contains("code") && hasCode)
									dynamicWhere += ", code";
							}
						}
						dictTModelcodePO.setDynamicwhere(dynamicWhere);
					}

					this.insertDictTModelcode(dictTModelcodePO);
					for (DictTFactorcodePO dtfc : dtfcList) {
						dtfc.setTableid(tableid);
						dtfc.setColumnid(this.dictDBExecuteService.getUUID());
						this.dictTFactorcodeService.insertDictTFactorcode(dtfc);
					}
					String appID = dictTModelcodePO.getAppid();
					// 清除app列缓存
					String[] columnAppKeys = { DictCacheKey.CACHE_KEY_DICT,
							DictCacheKey.DictFactorCode.APP.getCacheKey(),
							appID };
					dataCacheService.put(columnAppKeys, null);
					// 清除app表缓存
					String[] tableAppKeys = { DictCacheKey.CACHE_KEY_DICT,
							DictCacheKey.DictTableCode.APP.getCacheKey(), appID };
					dataCacheService.put(tableAppKeys, null);
					// 清除所有业务表类缓存
					String[] allDICTKeys = { DictCacheKey.CACHE_KEY_DICT };
					dataCacheService.put(allDICTKeys, null);
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception(e.getMessage());
				}
			} else {
				throw new Exception(
						"视图中必须包含 'GUID','CODE','NAME','SUPERGUID','ISLEAF','LEVELNO' 等字段.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<DictTModelcodePO> getDictTModelcodePOByName(String name) {

		List<DictTModelcodePO> list = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTableCode.NAME.getCacheKey(), name,
				"getDictTModelcodePOByName" };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			list = (List<DictTModelcodePO>) dataCacheService.get(keys);
			return list;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (name != null && !"".equals(name)) {
			map.put("name", name);
		}
		list = this.dictTModelcodeMapper.findDictTModelcode(map);
		// 加入到缓存
		dataCacheService.put(keys, list);
		return list;
	}

	@Override
	public DictTModelcodePO findDictTModelName(String appId, String currentValue) {
		DictTModelcodePO dictTModelcodePO = null;
		Map<String, Object> map = new HashMap<String, Object>();
		if (appId != null && !"".equals(appId)) {
			map.put("appId", appId);
		}
		if (currentValue != null && !"".equals(currentValue)) {
			map.put("tableId", currentValue);
		}
		dictTModelcodePO = this.dictTModelcodeMapper.findDictTModelName(map);
		return dictTModelcodePO;
	}

	@Override
	public void toFasp(List<DictTModelcodePO> dtoList) throws Exception {
//		IDataSetSourceService dataSetSourceService = (IDataSetSourceService) 
//			ServiceFactory.getBean("fasp2.dic.dataset.source.service");
//		for(DictTModelcodePO dto: dtoList) {
//			DataSetSourceDTO dsDto = new DataSetSourceDTO();
//			dsDto.setApp(dto.getAppid().toLowerCase());
//			dsDto.setCode(dto.getDbtablename());
//			dsDto.setName(dto.getName());
//			dsDto.setGuid(dto.getTableid());
//			dsDto.setElementcode(dto.getDbtablename().replaceAll("_", ""));
//			dsDto.setDatasource(dto.getDbtablename());
//			dsDto.setSourcetype("3");
//			dsDto.setTypeguid("200100");
//			dsDto.setShowstruct("1");
		IDataSetService dataSetSourceService = (IDataSetService) ServiceFactory.getBean("fasp2.dic.dataset.service");
		for(DictTModelcodePO dto: dtoList) {
			DataSetDTO dsDto = new DataSetDTO();
			dsDto.setApp(dto.getAppid().toLowerCase());
			dsDto.setCode(dto.getDbtablename());
			dsDto.setName(dto.getName());
			dsDto.setGuid(dto.getTableid());
			dsDto.setElementcode(dto.getDbtablename().replaceAll("_", ""));
			dsDto.setTablecode(dto.getDbtablename());
		
			try {
				//授权
				String user_name = getInstallFaspDBUser();
				Map map = new HashMap();
				map.put("table_name", dto.getDbtablename());
				map.put("user_name", user_name);
				dictTModelcodeMapper.grantTableToUser(map);
				dataSetSourceService.addDataSetType3(dsDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 得到安装平台用户名
	 * @return 安装平台用户名
	 */
	public String getInstallFaspDBUser() {
		return installDataBaseDao.getInstallFaspDBUser();
	}
	@Override
	public String valiWhere(String dbtablename, String dynamicwhere) {
		String msg = "";
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("dbtablename", dbtablename + " " + Constants.TABLE_NAME_ALIAS);
			params.put("dynamicwhere", dynamicwhere);
			dictTModelcodeMapper.valiWhere(params);
		} catch(Exception e) {
			msg = e.getCause().getMessage();
		}
		return msg;
	}


	@Override
	public List<TreeNode> findDictTModelcodeListForZTree()
			throws Exception {
		List<TreeNode> totalTree = new ArrayList();
		try {
			List<DictTAppregisterPO> listDTDs = dictTAppregisterMapper.getAllDictTAppregister();
			for (DictTAppregisterPO dtd : listDTDs) {
				TreeNode tree = new TreeNode();
				tree.setId(dtd.getAppid());
				tree.setName(dtd.getAppname());
				tree.setParent(false);
				tree.setPId("0");
				tree.setOpen(false);
				tree.setIsLeaf("1");
				List<TreePO> app = dictTModelcodeMapper
						.getAngleCodeTabList(dtd.getAppid());
				if (app != null&&app.size()>0) {
					for(TreePO  treePO : app){
						TreeNode treeNew = new TreeNode();
						treeNew.setId(treePO.getId());
						treeNew.setName(treePO.getName());
						treeNew.setPId(dtd.getAppid());
						treeNew.setIsLeaf("0");
//						treeNew.setDealType(treePO.getDbtablename());
						totalTree.add(treeNew);
					}
				}
				totalTree.add(tree);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return totalTree;
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

		List<Column> columnlist = getDictTDefaultcolColumnlist(tableid);

		grid.setColumnList(columnlist);

		return grid;
	}
	public List<Column> getDictTDefaultcolColumnlist(String tableid) {
		List<Column> columnlist = new ArrayList<Column>();
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

	/**
	 * 获取 by dealid
	 * 
	 * @param String
	 *            dealid
	 */
	public List<DictTModelcodePO> getDictTDefaultcols4Show(String tablename) throws Exception{
		List<DictTModelcodePO> dtds = null;
		try {
			if (tablename != null && !"".equals(tablename)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tablename", tablename);
				map.put("YES_CODE", "是");
				map.put("NO_CODE", "否");
//				dtds = this.dictTDefaultcolMapper.findDictTDefaultcol4Show(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return dtds;
	}
}

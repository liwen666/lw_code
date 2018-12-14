package commons.dict.service.impl;

import gov.mof.fasp2.dic.table.dto.DicColumnDTO;
import gov.mof.fasp2.dic.table.service.IDicTableQueryService;
import gov.mof.fasp2.dic.table.service.IDicTableService;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longtu.framework.cache.service.ICacheService;
import com.longtu.framework.util.ServiceFactory;
import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.dict.constants.DictCacheKey;
import com.tjhq.commons.dict.dao.DictTFactorSelfMapper;
import com.tjhq.commons.dict.dao.DictTModelSelfMapper;
import com.tjhq.commons.dict.dao.DictTUpdateviewMapper;
import com.tjhq.commons.dict.external.dao.DictTFactorMapper;
import com.tjhq.commons.dict.external.dao.DictTModelMapper;
import com.tjhq.commons.dict.external.po.ConsistencyPO;
import com.tjhq.commons.dict.external.po.DictTDealtypePO;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.po.DictTUpdateviewPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.dict.service.IDictDBExecuteService;
import com.tjhq.commons.dict.service.IDictTDealtypeService;
import com.tjhq.commons.dict.service.IDictTFactorSelfService;
import com.tjhq.commons.dict.service.IDictTModelSelfService;
import com.tjhq.commons.dict.service.IDictTSetHrefParmService;
import com.tjhq.commons.dict.util.DictDBConstants;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.setting.input.dao.EntryMapper;
import com.tjhq.commons.setting.input.web.ConverTables;

/**
 * 列接口实现
 * 
 * @author xujingsi
 * 
 */
@Service
@Transactional
public class DictTFactorSelfService implements IDictTFactorSelfService {

	@Resource
	private DictTFactorSelfMapper dictTFactorSelfMapper;

	@Resource
	private IDictDBExecuteService dictDBExecuteService;

	@Resource
	private DictTModelSelfMapper dictTModelSelfMapper;

	@Resource
	private DictTFactorMapper dictTFactorMapper;

	@Resource
	private DictTModelMapper dictTModelMapper;

	@Resource
	private IDictTModelcodeService dictTModelcodeService;

	@Resource
	private IDictTFactorService dictTFactorService;

	@Resource
	private EntryMapper entryMapper;

	@Resource
	private DictTUpdateviewMapper dictTUpdateviewMapper;

	@Resource
	private IDictTSetHrefParmService dictTSetHrefParmService;

	@Resource
	private IDataCacheService dataCacheService;

	@Resource
	private IDictTModelService dictTModelService;

	@Resource
	private IDictTDealtypeService dictTDealtypeService;

	@Resource
	private IDictTModelSelfService dictTModelSelfService;

	/**
	 * 添加列 for 1 物理表
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertDictTFactorForPhysics(DictTFactorPO dictTFactor,
			DictTModelPO dictTModel, List<Map<String, Object>> listInsert)
			throws Exception {
		if (dictTFactor == null) {
			return;
		}
		String ispartition = dictTModel.getIspartition();
		String status = this.dictDBExecuteService.getGlobalIsmultdb();
		String prefix = DictDBConstants.PREFIX_TABLE;
		if (status.equals("0")) {
			prefix = "";
		}
		Boolean isTask = false, isAllDistrict = false, isAllYear = false;
		if ("1".equals(dictTModel.getIstask())) {
			isTask = true;
		}
		if ("1".equals(dictTModel.getIsalldistrict())) {
			isAllDistrict = true;
		}
		if ("1".equals(dictTModel.getIsallyear())) {
			isAllYear = true;
		}
		boolean isConfig = false;
		// 得到处理类型
		String dealTypeID = dictTModel.getDealtype();
		// 通过处理类型得到是否需要配置视图
		DictTDealtypePO dictTDealtype = dictTDealtypeService.getDictTDealtype(
				dealTypeID, dictTModel.getAppid());
		if (dictTDealtype != null) {
			if ("1".equals(dictTDealtype.getNeedconfig())) {
				isConfig = true;
			}
		}
		try {
			String tableName = dictTModel.getDbtablename().toUpperCase();
			String columnDBName = dictTFactor.getDbcolumnname().toUpperCase();
			dictTFactor.setDbcolumnname(columnDBName);
			// 物理表 roll back
			String ifExistsColumnSql = DictDBConstants.IF_EXISTS_TYPE_COLUMN
					+ "'" + prefix + tableName + "' AND  COLUMN_NAME ='"
					+ dictTFactor.getDbcolumnname() + "'";
			Integer isColumnExist = dictDBExecuteService
					.getIfExistsInDB(ifExistsColumnSql);
			if (isColumnExist == 0) {
				if (!"YEAR".equals(dictTFactor.getDbcolumnname().trim())
						&& !"PROVINCE".equals(dictTFactor.getDbcolumnname()
								.trim())
						&& !"STATUS".equals(dictTFactor.getDbcolumnname()
								.trim())
						&& !"DBVERSION".equals(dictTFactor.getDbcolumnname()
								.trim())
						&& !"BAKUSERID".equals(dictTFactor.getDbcolumnname()
								.trim())
						&& !"BAKVERSION".equals(dictTFactor.getDbcolumnname()
								.trim())
						&& !"BAKTYPE".equals(dictTFactor.getDbcolumnname()
								.toUpperCase().trim())) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("tableid", dictTModel.getTableid());
					map.put("isleaf", "1");
					List<DictTFactorPO> factorList = this.dictTFactorMapper
							.findDictTFactor(map);
					List<DictTFactorPO> factorList0 = this.dictTFactorMapper
							.findPDictTFactor(map);
					Set<String> set = new HashSet<String>();
					if (ConverTables.isNotNullList(factorList0)) {
						for (DictTFactorPO dictTFactorPO : factorList0) {
							int setsize = set.size();
							String dbcol = dictTFactorPO.getDbcolumnname();
							set.add(dbcol);
							if (set.size() > setsize)
								factorList.add(dictTFactorPO);
						}
					}
					Map<String, List<DictTFactorPO>> viewColumMap = new HashMap<String, List<DictTFactorPO>>();
					// 原有主键
					for (DictTFactorPO dtf : factorList) {
						// //原有视图字段
						if (dtf.getDatatype() != 4) {
							List<DictTFactorPO> listFactor = viewColumMap
									.get(prefix + tableName);
							if (listFactor == null) {
								listFactor = new ArrayList<DictTFactorPO>();
								listFactor.add(dtf);
								viewColumMap
										.put(prefix + tableName, listFactor);
							} else {
								listFactor.add(dtf);
								viewColumMap
										.put(prefix + tableName, listFactor);
							}
							/*
							 * if("1".equals(dtf.getIskey())){
							 * listIndex.add(dtf.getDbcolumnname()); }
							 */
						}
					}
					List<DictTFactorPO> listFactor = viewColumMap.get(prefix
							+ tableName);
					if (listFactor == null) {
						listFactor = new ArrayList<DictTFactorPO>();
						listFactor.add(dictTFactor);
						viewColumMap.put(prefix + tableName, listFactor);
					} else {
						listFactor.add(dictTFactor);
						viewColumMap.put(prefix + tableName, listFactor);
					}
					/*
					 * if("1".equals(dictTFactor.getIskey())){
					 * listIndex.add(dictTFactor.getDbcolumnname()); }
					 */

					// append alter table sql
					String alterSql = null;
					if (!"1".equals(dictTFactor.getIsbandcol())) {
						StringBuilder sb = new StringBuilder();
						sb.append(DictDBConstants.ALTER_TABLE + " ");
						sb.append(prefix + tableName + " ");
						sb.append(DictDBConstants.ADD + " ");
						sb.append(dictTFactor.getDbcolumnname().toUpperCase()
								+ " ");
						Integer type = dictTFactor.getDatatype();
						if (type == 3) {
							if (dictTFactor.getDatalength() == 1) {
								sb.append("CHAR ");
							} else {
								sb.append(DictDBConstants.dataType.get(type)
										+ " ");
							}
						} else if (type == 7) {
							sb.append(" VARCHAR2 ");
						} else {
							sb.append(DictDBConstants.dataType.get(type) + " ");
						}
						if (type != 8 && type != 10) {
							sb.append(" (");
							if (type == 7) {
								sb.append("4000");
							} else {
								sb.append(dictTFactor.getDatalength());
							}
							if (type == 2) {
								if (dictTFactor.getScale() != null
										&& !"".equals(dictTFactor.getScale()))
									sb.append("," + dictTFactor.getScale());
							}
							sb.append(" ) ");
							// 虚列
							if ("1".equals(dictTFactor.getIsvirtual())) {
								sb.append(DictDBConstants.GENERATED + " ");
								sb.append(DictDBConstants.ALWAYS + " ");
								sb.append(DictDBConstants.AS + " (");
								sb.append(dictTFactor.getVircontext() + " )");
								sb.append(DictDBConstants.VIRTUAL + " ");
							} else {
								if (dictTFactor.getDefaultvalue() != null
										&& !"".equals(dictTFactor
												.getDefaultvalue())) {
									// 默认值
									if (type == 1 || type == 2) {
										sb.append(DictDBConstants.DEFAULT + " "
												+ dictTFactor.getDefaultvalue()
												+ " ");
									} else if (type == 3 || type == 4
											|| type == 6 || type == 9) {
										sb.append(DictDBConstants.DEFAULT + " "
												+ dictTFactor.getDefaultvalue()
												+ " ");
									}
								}
							}/*
							 * if("1".equals(dictTFactor.getIskey())||!"1".
							 * equals(dictTFactor.getNullable())){
							 * sb.append(DictDBConstants.NOT_NULL); }
							 */
						}
						alterSql = sb.toString();
					}

					try {

						/**
						 * -----------------------超链接参数--------------------
						 * --------
						 */
						if ("1".equals(dictTFactor.getIshref())) {
							dictTFactor
									.setHrefparmid(dictTFactor.getColumnid());
							String msg = "";
							if (listInsert != null && listInsert.size() > 0) { // 插入数据
								for (Map<String, Object> mapinsert : listInsert) {
									String parmName = mapinsert.get("parmName")
											+ "";
									String parmCon = mapinsert.get("parmCon")
											+ "";
									String orderid = mapinsert.get("_sortid") == null
											|| "".equals(mapinsert
													.get("_sortid")) ? ""
											: mapinsert.get("_sortid")
													.toString();
									if (orderid != null && !"".equals(orderid)) {
										mapinsert.put("orderID",
												Integer.parseInt(orderid) + 1
														+ "");
									}
									if ((parmName != null
											&& !"".equals(parmName.trim()) && !"null"
												.equals(parmName))
											&& (parmCon != null
													&& !"".equals(parmCon
															.trim()) && !"null"
														.equals(parmCon))) {
										mapinsert.put("hrefParmID",
												dictTFactor.getColumnid());
										List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
										lists.add(mapinsert);
										this.dictTSetHrefParmService
												.insertDictTSetHrefParmPO(lists);
									} else {
										msg = msg
												+ "第"
												+ (Integer.parseInt(orderid) + 1)
												+ "行,参数名称,参数表达式设置不能为空值";
										break;
									}
								}
								if (!"".equals(msg)) {
									throw new Exception("超链接参数设置：" + msg);
								}
							}

						}
						// 插入到factor
						this.insertDictTFactor(tableName, dictTFactor, true);
					} catch (Exception e1) {
						e1.printStackTrace();
						throw new Exception(e1.getMessage());
					}

					try {

						/**
						 * 单一物理表
						 */
						if (!("1".equals(ispartition))) {
							// 单物理表
							String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
									+ "'" + prefix + tableName + "'";
							Integer isTableExist = dictDBExecuteService
									.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
							if (isTableExist == 1) {
								try {
									if (alterSql != null) {// 新列 不是绑定列 也不是虚列
										// 更新 物理表
										this.dictDBExecuteService
												.ExecDllLongForParam(alterSql);
									}
									// TODO:原代码中注释掉视图执行,先恢复执行,原因待测
									// 更新视图
									String updateViewSql = dictDBExecuteService.createView(
											ispartition, status, tableName, viewColumMap,
											dictTModel.getSecusql(), isTask, false,
											isAllDistrict, isAllYear);
									this.dictDBExecuteService
											.ExecDllLongForParam(updateViewSql);
									// 重构视图触发器
									dictDBExecuteService
											.createFormulaTrigger(dictTModel
													.getTableid());
									// 如果有配置视图，同步更新
									if (isConfig) {
										updateViewSql = dictDBExecuteService.createView(
												ispartition, status, tableName, viewColumMap,
												dictTModel.getSecusql(),isTask, isConfig,
												isAllDistrict, isAllYear);
										this.dictDBExecuteService
												.ExecDllLongForParam(updateViewSql);
									}
								} catch (Exception e) {
									// 物理表 roll back
									ifExistsColumnSql = DictDBConstants.IF_EXISTS_TYPE_COLUMN
											+ "'"
											+ prefix
											+ tableName
											+ "' AND  COLUMN_NAME ='"
											+ dictTFactor.getDbcolumnname()
													.toUpperCase() + "'";
									isColumnExist = dictDBExecuteService
											.getIfExistsInDB(ifExistsColumnSql);
									if (isColumnExist == 1) {
										// ;alter table prefix+AACSWLB2 drop
										// column asdasd
										dictDBExecuteService
												.ExecDllLongForParam(DictDBConstants.ALTER_TABLE
														+ " "
														+ prefix
														+ tableName
														+ " DROP  COLUMN "
														+ dictTFactor
																.getDbcolumnname()
																.toUpperCase());
									}
									e.printStackTrace();
									throw new Exception(e.getMessage());
								}
							} else {
								throw new Exception("未找到表：" + "'" + prefix
										+ tableName + "' ");
							}

						} else {
							/**
							 * 区划
							 */
							// 更新 物理表
							// alter table prefix+AABB add bbbbbb number(3,7)
							// default 0 not null;
							String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
									+ "'" + prefix + tableName + "'";
							Integer isTableExist = dictDBExecuteService
									.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
							if (isTableExist == 1) {
								try {
									if (alterSql != null) {// 新列 不是绑定列 也不是虚列
										// 更新 物理表
										this.dictDBExecuteService
												.ExecDllLongForParam(alterSql);
									}
									// 更新视图
									String updateViewSql = dictDBExecuteService.createView(
											ispartition, status, tableName, viewColumMap,
											dictTModel.getSecusql(), isTask, false,
											isAllDistrict, isAllYear);
									this.dictDBExecuteService
											.ExecDllLongForParam(updateViewSql);
									// 如果有配置视图，同步更新
									if (isConfig) {
										updateViewSql = dictDBExecuteService.createView(
												ispartition, status, tableName, viewColumMap,
												dictTModel.getSecusql(), isTask, isConfig,
												isAllDistrict, isAllYear);
										this.dictDBExecuteService
												.ExecDllLongForParam(updateViewSql);
									}
								} catch (Exception e) {
									// 物理表 roll back
									ifExistsColumnSql = DictDBConstants.IF_EXISTS_TYPE_COLUMN
											+ "'"
											+ prefix
											+ tableName
											+ "' AND  COLUMN_NAME ='"
											+ dictTFactor.getDbcolumnname()
													.toUpperCase() + "'";
									isColumnExist = dictDBExecuteService
											.getIfExistsInDB(ifExistsColumnSql);
									if (isColumnExist == 1) {
										// ;alter table prefix+AACSWLB2 drop
										// column asdasd
										dictDBExecuteService
												.ExecDllLongForParam(DictDBConstants.ALTER_TABLE
														+ " "
														+ prefix
														+ tableName
														+ " DROP  COLUMN "
														+ dictTFactor
																.getDbcolumnname()
																.toUpperCase());
										this.deleteDictTFactor(tableName,
												dictTFactor, true);
									}

									e.printStackTrace();
									throw new Exception(e.getMessage());
								}
							} else {
								throw new Exception("未找到分区表：" + "'" + prefix
										+ tableName + "' ");
							}
						}

						/*
						 * //更新INDEX if("1".equals(dictTFactor.getIskey())){
						 * 
						 * if(listIndex.size()>0){ StringBuilder ifExistIndexSql
						 * = new StringBuilder();
						 * ifExistIndexSql.append(DictDBConstants
						 * .IF_EXISTS_TYPE_INDEX);
						 * ifExistIndexSql.append("'"+prefix+tableName+"' ");
						 * ifExistIndexSql.append(" AND U.INDEX_NAME = ");
						 * ifExistIndexSql.append("'IN_"+prefix+tableName+"'");
						 * Integer r_index =
						 * dictDBExecuteService.getIfExistsInDB
						 * (ifExistIndexSql.toString());// //删除原有index
						 * if(r_index==1){ //删除原来的主键 try {
						 * this.dictDBExecuteService
						 * .ExecDllLongForParam("DROP INDEX IN_"
						 * +prefix+tableName); } catch (Exception e) {
						 * e.printStackTrace(); throw new
						 * Exception(e.getMessage()); } }
						 * 
						 * //设定新 索引 //create unique index INDEX_AACSWLB1 on
						 * prefix+AACSWLB1 (ORDERID, PROVINCE) try { String
						 * indexSql =
						 * dictDBExecuteService.createIndex(tableName,
						 * listIndex);
						 * this.dictDBExecuteService.ExecDllLongForParam
						 * (indexSql); } catch (Exception e) { // Auto-generated
						 * catch block e.printStackTrace(); throw new
						 * Exception(e.getMessage()); } } }
						 */
						/*
						 * //插入到factor this.insertDictTFactor(dictTFactor,true);
						 */

						// ------------------- 同步 备份表
						// ----------------------------------------------
						String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
								+ "'" + prefix + tableName + "_BAK'";
						Integer isTableExist = dictDBExecuteService
								.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
						if (isTableExist == 1) {
							try {
								createDictTFactorForBak(dictTModel,
										dictTFactor, true);
							} catch (Exception e) {
								e.printStackTrace();
								throw new Exception(e.getMessage());
							}
						}
					} catch (Exception e) {
						// 执行出错,删除平台登记列
						deleteRegisterDicColumn(tableName, dictTFactor);
						throw new Exception(e.getMessage());
					}
					// ------------------- 同步 备份表
					// ----------------------------------------------
				} else {
					throw new Exception(
							"不允许出现'YEAR','PROVINCE','STATUS','DBVERSION','BAKVERSION','BAKUSERID','BAKTYPE' 等字段.");
				}
			} else {
				throw new Exception("出现重复字段[" + dictTFactor.getDbcolumnname()
						+ "].");
			}

			// 清除缓存
			// 清除表ID缓存
			String[] tableIDKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.ID.getCacheKey(),
					dictTModel.getTableid() };
			dataCacheService.put(tableIDKeys, null);
			// 清除表物理名缓存
			String[] tableDBNameKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.DBNAME.getCacheKey(),
					dictTModel.getDbtablename() };
			dataCacheService.put(tableDBNameKeys, null);
			// 清除表类型
			String[] tableTypeKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTModel.getTabletype() };
			dataCacheService.put(tableTypeKeys, null);
			// 清除表处理类型
			String[] tableDealTypeKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.DEALTYPE.getCacheKey(),
					dictTModel.getDealtype() };
			dataCacheService.put(tableDealTypeKeys, null);

			String suitID = dictTModel.getSuitid();
			// 清除套表缓存
			String[] tableSuitKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictSuit.ID.getCacheKey() };
			dataCacheService.put(tableSuitKeys, null);
			String appID = dictTModel.getAppid();
			// 清除app列缓存
			String[] columnAppKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictColumn.APP.getCacheKey(), appID };
			dataCacheService.put(columnAppKeys, null);
			// 清除app表缓存
			String[] tableAppKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.APP.getCacheKey(), appID };
			dataCacheService.put(tableAppKeys, null);
			// 清除app套表缓存
			String[] suitAppKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictSuit.APP.getCacheKey() };
			dataCacheService.put(suitAppKeys, null);
			// 清除所有业务表类缓存
			String[] allDICTKeys = { DictCacheKey.CACHE_KEY_DICT };
			dataCacheService.put(allDICTKeys, null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		//最后统一调用刷新表视图，避免大集中多分区造成的不一致
		dictDBExecuteService.recreateViews(dictTModel.getTableid());
		// 增加列注释
		dictTModelSelfService.addColumnComment4Table(dictTModel);
	}

	public void initNewFactor(DictTFactorPO dictTFactor, DictTModelPO dictTModel)
			throws Exception {
		String tableId = dictTFactor.getTableid();
		String columnId = dictDBExecuteService.getUUID();
		dictTFactor.setColumnid(columnId);
		if (!("1".equals(dictTFactor.getIsbandcol()))) {
			dictTFactor.setIsbandcol("0");
			dictTFactor.setBandcolumnid(null);
			dictTFactor.setBandrefdwcol(null);
		}
		if (!("1".equals(dictTFactor.getIsregex()))) {
			dictTFactor.setIsregex("0");
			dictTFactor.setRegexpr(null);
			dictTFactor.setRegexprinfo(null);
		}
		if (!("1".equals(dictTFactor.getIshref()))) {
			dictTFactor.setIshref("0");
			dictTFactor.setHrefloc("");
			dictTFactor.setHrefparmid("");
		}
		if (!("1".equals(dictTFactor.getIsvirtual()))) {
			dictTFactor.setIsvirtual("0");
			dictTFactor.setVircontext(null);
		}
		if (!("1".equals(dictTFactor.getNullable()))) {
			dictTFactor.setNullable("0");
		}
		if (dictTFactor.getScale() == null) {
			dictTFactor.setScale(0);
		}
		dictTFactor.setOpenwindowtype(dictTFactor.getOpenwindowtype() == null
				|| "".equals(dictTFactor.getOpenwindowtype()) ? "0"
				: dictTFactor.getOpenwindowtype());
		dictTFactor.setIskey(dictTFactor.getIskey() == null
				|| "".equals(dictTFactor.getIskey()) ? "0" : dictTFactor
				.getIskey());
		dictTFactor.setIsleaf(dictTFactor.getIsleaf() == null
				|| "".equals(dictTFactor.getIsleaf()) ? "1" : dictTFactor
				.getIsleaf());
		dictTFactor.setIsreserve(dictTFactor.getIsreserve() == null
				|| "".equals(dictTFactor.getIsreserve()) ? "0" : dictTFactor
				.getIsreserve());
		dictTFactor.setIssum(dictTFactor.getIssum() == null
				|| "".equals(dictTFactor.getIssum()) ? "0" : dictTFactor
				.getIssum());
		dictTFactor.setIsupdate(dictTFactor.getIsupdate() == null
				|| "".equals(dictTFactor.getIsupdate()) ? "0" : dictTFactor
				.getIsupdate());
		dictTFactor.setIsvisible(dictTFactor.getIsvisible() == null
				|| "".equals(dictTFactor.getIsvisible()) ? "0" : dictTFactor
				.getIsvisible());
		dictTFactor
				.setParentNodeCanCheck(dictTFactor.getParentNodeCanCheck() == null
						|| "".equals(dictTFactor.getParentNodeCanCheck()) ? "0"
						: dictTFactor.getParentNodeCanCheck());
		dictTFactor.setSuperid(dictTFactor.getSuperid() == null
				|| "".equals(dictTFactor.getSuperid()) ? "0" : dictTFactor
				.getSuperid());
		String extprop = dictTFactor.getExtprop();
		if (extprop == null || extprop.trim().equals("")) {
			extprop = "";
			for (int i = 0; i < DictDBConstants.DICT_DB_EXEPROP - 1; i++) {
				extprop = extprop + "0";
			}
			dictTFactor.setExtprop(extprop);
		}
		if (dictTFactor.getDefaultvalue() == null) {
			dictTFactor.setDefaultvalue("");
		}
		if (ConverTables.isNotNull(dictTFactor.getDefaultvalue())) {
			if (dictTFactor.getDatatype() == 3) {
				String regex = "[0-9]+?";
				Pattern pattern = Pattern.compile(regex);
				Matcher m = pattern.matcher(dictTFactor.getDefaultvalue());
				if (m.matches() == true) {
					dictTFactor.setDefaultvalue("'"
							+ dictTFactor.getDefaultvalue() + "'");
				}
			}
		}

		String tableName = dictTModel.getDbtablename().toUpperCase();
		String tabletype = dictTModel.getTabletype();
		// 物理表检查列一致性
		if (DictDBConstants.TABLE_TYPE_TABLE.equals(tabletype)) {
			/**
			 * 检查列一致性
			 * 
			 */
			List<ConsistencyPO> listConsistency = this.dictDBExecuteService
					.getConsistencyByArgs(tableName);
			if (listConsistency != null && listConsistency.size() > 0) {
				StringBuffer msg = new StringBuffer("");
				for (ConsistencyPO consis : listConsistency) {
					msg.append("表 [P#").append(consis.getTableName())
							.append("] 中缺失 [").append(consis.getFactorName())
							.append("] 列.  ");
				}
				throw new Exception(msg.toString());
			}
		}

		if (dictTFactor.getSuperid() == null
				|| "".equals(dictTFactor.getSuperid())
				|| "0".equals(dictTFactor.getSuperid())) {
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("tableid", tableId);
			parameterMap.put("levelno", 1);
			Integer max = getMAXColumnOrderid(parameterMap);
			dictTFactor.setOrderid(max + 1);
			dictTFactor.setLevelno(1);
		} else {
			DictTFactorPO supperDtf = this.dictTFactorService
					.getDictTFactorByColumnId(dictTFactor.getSuperid());
			dictTFactor.setLevelno(supperDtf.getLevelno() + 1);
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("tableid", tableId);
			parameterMap.put("superid", dictTFactor.getSuperid());
			int maxOrderID = this.dictTFactorSelfMapper
					.getMAXOrderidBySuperID(parameterMap);
			dictTFactor.setOrderid(maxOrderID + 1);
		}
		Map parameterMap = new HashMap();
		parameterMap.put("tableid", tableId);
		parameterMap.put("name", dictTFactor.getName());
		int count = dictTFactorMapper.findDictTFactorCount(parameterMap);
		if (count > 0) {
			throw new Exception("列中文名称出现重复");
		}
	}

	/*
	 * alter table prefix+AABB rename column T to Tttttt; alter table
	 * prefix+AABB modify Tttttt NUMBER(30,1) default 0.0 null; --
	 * Create/Recreate primary, unique and foreign key constraints alter table
	 * prefix+AABB drop constraint PK_prefix+AABB cascade; alter table
	 * prefix+AABB add constraint PK_prefix+AABB primary key (POP2, RRRR,
	 * TTTTTT)
	 * 
	 * -- Create/Recreate indexes create unique index INDEX_AACSWLB1 on
	 * prefix+AACSWLB1 (year, version);
	 * 
	 * -- Drop indexes drop index INDEX_AACSWLB11;
	 */
	/**
	 * 修改列 for 1 物理表
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateDictTFactorForPhysics(DictTFactorPO oldDictTFactor,
			DictTFactorPO newDictTFactor, DictTModelPO dictTModel)
			throws Exception {

		String tableName = dictTModel.getDbtablename().toUpperCase();
		// 检查 一致性
		List<ConsistencyPO> listConsistency = this.dictDBExecuteService
				.getConsistencyByArgs(tableName);
		if (listConsistency != null && listConsistency.size() > 0) {
			StringBuffer msg = new StringBuffer("");
			for (ConsistencyPO consis : listConsistency) {
				msg.append("表 [P#").append(consis.getTableName())
						.append("] 中缺失 [").append(consis.getFactorName())
						.append("] 列. ");
			}
			throw new Exception(msg.toString());
		}
		String ispartition = dictTModel.getIspartition();
		String status = this.dictDBExecuteService.getGlobalIsmultdb();
		String prefix = DictDBConstants.PREFIX_TABLE;
		if (status.equals("0")) {
			prefix = "";
		}
		Boolean isTask = false, isAllDistrict = false, isAllYear = false;
		if ("1".equals(dictTModel.getIstask())) {
			isTask = true;
		}
		if ("1".equals(dictTModel.getIsalldistrict())) {
			isAllDistrict = true;
		}
		if ("1".equals(dictTModel.getIsallyear())) {
			isAllYear = true;
		}
		boolean isConfig = false;
		// 得到处理类型
		String dealTypeID = dictTModel.getDealtype();
		// 通过处理类型得到是否需要配置视图
		DictTDealtypePO dictTDealtype = dictTDealtypeService.getDictTDealtype(
				dealTypeID, dictTModel.getAppid());
		if (dictTDealtype != null) {
			if ("1".equals(dictTDealtype.getNeedconfig())) {
				isConfig = true;
			}
		}
		if (tableName != null && !"".equals(tableName)) {
			newDictTFactor.setDbcolumnname(newDictTFactor.getDbcolumnname()
					.toUpperCase());
			String newColumnName = newDictTFactor.getDbcolumnname()
					.toUpperCase();
			if (!"YEAR".equals(newDictTFactor.getDbcolumnname().trim())
					&& !"PROVINCE".equals(newDictTFactor.getDbcolumnname()
							.trim())
					&& !"STATUS"
							.equals(newDictTFactor.getDbcolumnname().trim())
					&& !"DBVERSION".equals(newDictTFactor.getDbcolumnname()
							.trim())
					&& !"BAKUSERID".equals(newDictTFactor.getDbcolumnname()
							.trim())
					&& !"BAKVERSION".equals(newDictTFactor.getDbcolumnname()
							.trim())
					&& !"BAKTYPE".equals(newDictTFactor.getDbcolumnname()
							.toUpperCase().trim())) {
				// append alter table modify ******
				String sbsql = null;
				String renamesql = null;
				// if(!(("1".equals(oldDictTFactor.getIsbandcol())&&!"1".equals(newDictTFactor.getIsbandcol()))||(!"1".equals(oldDictTFactor.getIsbandcol())&&"1".equals(newDictTFactor.getIsbandcol())))){
				if ((oldDictTFactor.getIsbandcol().equals(newDictTFactor
						.getIsbandcol()))
						&& (oldDictTFactor.getIsvirtual().equals(newDictTFactor
								.getIsvirtual()))) {
					StringBuilder sb = new StringBuilder();
					sb.append(DictDBConstants.ALTER_TABLE + " ");
					sb.append(prefix + tableName + " ");
					sb.append(DictDBConstants.MODIFY + " ");
					sb.append(newDictTFactor.getDbcolumnname() + " ");

					Integer type = newDictTFactor.getDatatype();
					if (type != oldDictTFactor.getDatatype()
							|| oldDictTFactor.getDatalength() != newDictTFactor
									.getDatalength()
							|| newDictTFactor.getScale() != oldDictTFactor
									.getScale()) {
						if (type == 3) {
							if (newDictTFactor.getDatalength() == 1) {
								sb.append("CHAR ");
							} else {
								sb.append(DictDBConstants.dataType.get(type)
										+ " ");
							}
						} else if (type == 7) {
							sb.append(" VARCHAR2 ");
						} else {
							sb.append(DictDBConstants.dataType.get(type) + " ");
						}

						if (type != 8 && type != 10) {
							sb.append(" (");
							if (type == 7) {
								sb.append("4000");
							} else {
								sb.append(newDictTFactor.getDatalength());
							}

							if (type == 2) {
								if (newDictTFactor.getScale() != null
										&& !"".equals(newDictTFactor.getScale()))
									sb.append("," + newDictTFactor.getScale());
							}
							sb.append(" ) ");

						}
					}
					if ("1".equals(newDictTFactor.getIsvirtual())) {
						// plevel varchar2(3) generated always as() virtual
						sb.append(DictDBConstants.GENERATED + " ");
						sb.append(DictDBConstants.ALWAYS + " ");
						sb.append(DictDBConstants.AS + " (");
						sb.append(newDictTFactor.getVircontext() + " )");
						sb.append(DictDBConstants.VIRTUAL + " ");

					} else {
						if (newDictTFactor.getDefaultvalue() != null
								&& !"".equals(newDictTFactor.getDefaultvalue())) {
							// 默认值
							if (type == 1 || type == 2) {
								sb.append(DictDBConstants.DEFAULT + " "
										+ newDictTFactor.getDefaultvalue()
										+ " ");
							} else if (type == 3 || type == 4 || type == 6
									|| type == 9) {
								sb.append(DictDBConstants.DEFAULT + " "
										+ newDictTFactor.getDefaultvalue()
										+ " ");
							}
						} else {
							sb.append(DictDBConstants.DEFAULT + " NULL ");
						}
					}
					/*
					 * if(!"1".equals(oldDictTFactor.getNullable())){//原 not
					 * null if(!"1".equals(newDictTFactor.getNullable())){
					 * if("1".equals(newDictTFactor.getIskey())){
					 * //sb.append(DictDBConstants.NOT_NULL); }else{
					 * //sb.append(DictDBConstants.NOT_NULL); }
					 * 
					 * }else if("1".equals(newDictTFactor.getNullable())){
					 * if("1".equals(newDictTFactor.getIskey())){
					 * //sb.append(DictDBConstants.NOT_NULL); }else{
					 * sb.append(" NULL "); } } }else{//原 null
					 * if(!"1".equals(newDictTFactor.getNullable())){
					 * if("1".equals(newDictTFactor.getIskey())){
					 * sb.append(DictDBConstants.NOT_NULL); }else{
					 * sb.append(DictDBConstants.NOT_NULL); }
					 * 
					 * }else if("1".equals(newDictTFactor.getNullable())){
					 * if("1".equals(newDictTFactor.getIskey())){
					 * sb.append(DictDBConstants.NOT_NULL); }else{
					 * //sb.append(" NULL "); } } }
					 */
					sbsql = sb.toString();

					StringBuilder rename = new StringBuilder();
					// alter table prefix+AABB rename column T to Tttttt;
					rename.append(DictDBConstants.ALTER_TABLE + " ");
					rename.append(prefix + tableName + " ");
					rename.append(DictDBConstants.RENAME + " ");
					rename.append(DictDBConstants.COLUMN + " ");
					rename.append(oldDictTFactor.getDbcolumnname() + " ");
					rename.append(DictDBConstants.TO + " ");
					rename.append(newDictTFactor.getDbcolumnname());
					renamesql = rename.toString();
				} else {
					throw new Exception("列修改时不能转换绑定列,或虚列.");
				}
				// 主键 and 视图字段
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tableid", dictTModel.getTableid());
				map.put("isleaf", "1");
				/*
				 * List<String> listIndex = new ArrayList<String>();
				 * if("1".equals(status)){ listIndex.add("YEAR"); // 索引 }else
				 * if("2".equals(status)){ listIndex.add("PROVINCE"); // 索引
				 * listIndex.add("YEAR"); // 索引 }
				 */
				List<DictTFactorPO> factorList = this.dictTFactorMapper
						.findDictTFactor(map);
				// StringBuilder viewColum = new StringBuilder();
				Map<String, List<DictTFactorPO>> viewColumMap = new HashMap<String, List<DictTFactorPO>>();
				// 原有主键
				for (DictTFactorPO dtf : factorList) {
					if (!(dtf.getColumnid()
							.equals(newDictTFactor.getColumnid()))
							&& dtf.getDatatype() != 4) {// 过滤掉 将要修改的
						// 原有视图字段
						List<DictTFactorPO> listFactor = viewColumMap
								.get(prefix + tableName);
						if (listFactor == null) {
							listFactor = new ArrayList<DictTFactorPO>();
							listFactor.add(dtf);
							viewColumMap.put(prefix + tableName, listFactor);
						} else {
							listFactor.add(dtf);
							viewColumMap.put(prefix + tableName, listFactor);
						}
						/*
						 * if("1".equals(dtf.getIskey())){//原有主键
						 * listIndex.add(dtf.getDbcolumnname()); }
						 */
					}
				}
				// viewColum.append(newDictTFactor.getDbcolumnname() +",");
				List<DictTFactorPO> listFactor = viewColumMap.get(prefix
						+ tableName);
				if (listFactor == null) {
					listFactor = new ArrayList<DictTFactorPO>();
					listFactor.add(newDictTFactor);
					viewColumMap.put(prefix + tableName, listFactor);
				} else {
					listFactor.add(newDictTFactor);
					viewColumMap.put(prefix + tableName, listFactor);
				}
				/*
				 * if("1".equals(newDictTFactor.getIskey())){
				 * listIndex.add(newColumnName); }
				 */



				/**
				 * 单一物理表
				 */
				if (!("1".equals(ispartition))) {
					// 单物理表
					String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
							+ "'" + prefix + tableName + "'";
					Integer r_table = dictDBExecuteService
							.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
					if (r_table == 1) {
						try {
							if (!"1".equals(newDictTFactor.getIsbandcol())) {
								if (!newColumnName.equals(oldDictTFactor
										.getDbcolumnname())) { // 与原字段名 不同
									this.dictDBExecuteService
											.ExecDllLongForParam(renamesql);
								}
								this.dictDBExecuteService
										.ExecDllLongForParam(sbsql);

							}
							// 更新视图
							// String updateViewSql =
							// dictDBExecuteService.createView(ispartition,status,
							// tableName, viewColumMap,dictTModel.getSecusql());
							// this.dictDBExecuteService.ExecDllLongForParam(updateViewSql);
						} catch (Exception e) {
							e.printStackTrace();
							throw new Exception(e.getMessage());
						}
					} else {
						throw new Exception("未找到表：" + "'" + prefix + tableName
								+ "' ");
					}

				} else {
					/**
					 * 区划
					 */
					String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
							+ "'" + prefix + tableName + "'";
					Integer r_table = dictDBExecuteService
							.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
					if (r_table == 1) {
						try {
							if (!"1".equals(newDictTFactor.getIsbandcol())) {
								if (!newColumnName.equals(oldDictTFactor
										.getDbcolumnname())) { // 与原字段名 不同
									this.dictDBExecuteService
											.ExecDllLongForParam(renamesql);
								}
								// 更新 物理表
								this.dictDBExecuteService
										.ExecDllLongForParam(sbsql);
							}
							// 更新视图
							String updateViewSql = dictDBExecuteService.createView(
									ispartition, status, tableName, viewColumMap,
									dictTModel.getSecusql(), isTask, false,
									isAllDistrict, isAllYear);
							this.dictDBExecuteService
									.ExecDllLongForParam(updateViewSql);
							// 重构视图触发器
							dictDBExecuteService
									.createFormulaTrigger(dictTModel
											.getTableid());
							// 如果有配置视图，同步更新
							if (isConfig) {
								updateViewSql = dictDBExecuteService.createView(
										ispartition, status, tableName, viewColumMap,
										dictTModel.getSecusql(), isTask, isConfig,
										isAllDistrict, isAllYear);
								this.dictDBExecuteService
										.ExecDllLongForParam(updateViewSql);
							}
						} catch (Exception e) {
							e.printStackTrace();
							throw new Exception(e.getMessage());
						}
					} else {
						throw new Exception("未找到分区表：" + "'" + prefix
								+ tableName + "' ");
					}
				}

				/*
				 * //更新主键 PK
				 * if(("1".equals(newDictTFactor.getIskey())||"1".equals
				 * (oldDictTFactor
				 * .getIskey()))&&!oldDictTFactor.getDbcolumnname(
				 * ).equals(newDictTFactor.getDbcolumnname())){
				 * 
				 * if(listIndex.size()>0){ StringBuilder ifExistIndexSql = new
				 * StringBuilder();
				 * ifExistIndexSql.append(DictDBConstants.IF_EXISTS_TYPE_INDEX);
				 * ifExistIndexSql.append("'"+prefix+tableName+"' ");
				 * ifExistIndexSql.append(" AND U.INDEX_NAME = ");
				 * ifExistIndexSql.append("'IN_"+prefix+tableName+"'"); Integer
				 * r_index =
				 * dictDBExecuteService.getIfExistsInDB(ifExistIndexSql
				 * .toString());// //删除原有index if(r_index==1){ //删除原来的主键 try {
				 * this
				 * .dictDBExecuteService.ExecDllLongForParam("DROP INDEX IN_"
				 * +prefix+tableName); } catch (Exception e) { // Auto-generated
				 * catch block e.printStackTrace(); throw new
				 * Exception(e.getMessage()); } }
				 * 
				 * //设定新 索引 try { String indexSql =
				 * dictDBExecuteService.createIndex(tableName, listIndex);
				 * this.dictDBExecuteService.ExecDllLongForParam(indexSql); }
				 * catch (Exception e) { // Auto-generated catch block
				 * e.printStackTrace(); throw new Exception(e.getMessage()); } }
				 * }
				 */

				// --------------------同步
				// 备份表-------------------------------------
				String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
						+ "'" + prefix + tableName + "_BAK'";
				Integer isTableExist = dictDBExecuteService
						.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
				if (isTableExist == 1) {
					try {
						updateDictTFactorForBak(oldDictTFactor, newDictTFactor,
								dictTModel);
					} catch (Exception e) {
						e.printStackTrace();
						throw new Exception(e.getMessage());
					}
				}
				// --------------------同步
				// 备份表-------------------------------------

				/*
				 * //update到factor this.updateDictTFactor(newDictTFactor);
				 */
				try {
					DictTFactorPO dtfpnew = (DictTFactorPO) newDictTFactor
							.clone();
					this.updateDictTFactor(dtfpnew, true);
				} catch (Exception e1) {
					e1.printStackTrace();
					throw new Exception(e1.getMessage());
				}
			} else {
				throw new Exception(
						"不允许出现'YEAR','PROVINCE','STATUS','DBVERSION','BAKVERSION','BAKUSERID','BAKTYPE' 等字段.");
			}
		}
		//最后统一调用刷新表视图，避免大集中多分区造成的不一致
		dictDBExecuteService.recreateViews(dictTModel.getTableid());
		// 增加列注释
		dictTModelSelfService.addColumnComment4Table(dictTModel);
	}

	/**
	 * 删除 列 for 1 物理表
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteDictTFactorForPhysics(DictTFactorPO dictTFactor,
			DictTModelPO dictTModel) throws Exception {

		try {

			String tableName = dictTModel.getDbtablename().toUpperCase();
			// 检查 一致性
			List<ConsistencyPO> listConsistency = this.dictDBExecuteService
					.getConsistencyByArgs(tableName);
			if (listConsistency != null && listConsistency.size() > 0) {
				StringBuffer msg = new StringBuffer("");
				for (ConsistencyPO consis : listConsistency) {
					msg.append("表 [P#").append(consis.getTableName())
							.append("] 中缺失 [").append(consis.getFactorName())
							.append("] 列. ");
				}
				throw new Exception(msg.toString());
			}
			String ispartition = dictTModel.getIspartition();
			String status = this.dictDBExecuteService.getGlobalIsmultdb();
			String prefix = DictDBConstants.PREFIX_TABLE;
			if (status.equals("0")) {
				prefix = "";
			}
			Boolean isTask = false, isAllDistrict = false, isAllYear = false;
			if ("1".equals(dictTModel.getIstask())) {
				isTask = true;
			}
			if ("1".equals(dictTModel.getIsalldistrict())) {
				isAllDistrict = true;
			}
			if ("1".equals(dictTModel.getIsallyear())) {
				isAllYear = true;
			}
			boolean isConfig = false;
			// 得到处理类型
			String dealTypeID = dictTModel.getDealtype();
			// 通过处理类型得到是否需要配置视图
			DictTDealtypePO dictTDealtype = dictTDealtypeService
					.getDictTDealtype(dealTypeID, dictTModel.getAppid());
			if (dictTDealtype != null) {
				if ("1".equals(dictTDealtype.getNeedconfig())) {
					isConfig = true;
				}
			}
			// alter table prefix+AABB drop column UUU;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableid", dictTModel.getTableid());
			map.put("isleaf", "1");
			// List<String> listIndex = new ArrayList<String>();
			List<DictTFactorPO> factorList = this.dictTFactorMapper
					.findDictTFactor(map);
			List<DictTFactorPO> factorList0 = this.dictTFactorMapper
					.findPDictTFactor(map);
			Set<String> set = new HashSet<String>();
			if (ConverTables.isNotNullList(factorList0)) {
				for (DictTFactorPO dictTFactorPO : factorList0) {
					int setsize = set.size();
					String dbcol = dictTFactorPO.getDbcolumnname();
					set.add(dbcol);
					if (set.size() > setsize)
						factorList.add(dictTFactorPO);
				}
			}
			// StringBuilder viewColum = new StringBuilder();
			Map<String, List<DictTFactorPO>> viewColumMap = new HashMap<String, List<DictTFactorPO>>();
			// 原有主键
			Map<String, String> mapkey = new HashMap<String, String>();
			for (DictTFactorPO dtf : factorList) {
				if ("1".equals(dtf.getIsbandcol())) {
					mapkey.put(dtf.getBandcolumnid(), dtf.getBandcolumnid());
				}
				if (!dtf.getColumnid().equals(dictTFactor.getColumnid())
						&& dtf.getDatatype() != 4) {// 去除 dictTFactor 列
					// 视图 字段
					List<DictTFactorPO> listFactor = viewColumMap.get(prefix
							+ tableName);
					if (listFactor == null) {
						listFactor = new ArrayList<DictTFactorPO>();
						listFactor.add(dtf);
						viewColumMap.put(prefix + tableName, listFactor);
					} else {
						listFactor.add(dtf);
						viewColumMap.put(prefix + tableName, listFactor);
					}
					/*
					 * if("1".equals(dtf.getIskey())){
					 * listIndex.add(dtf.getDbcolumnname());//主键 字段 }
					 */
				}
			}
			if (mapkey.get(dictTFactor.getColumnid()) != null) {
				throw new Exception("该列已经被绑定，不能删除！");
			}
			String sbsql = null;
			String ifExistsColumnSql = DictDBConstants.IF_EXISTS_TYPE_COLUMN
					+ "'" + prefix + tableName + "' AND  COLUMN_NAME ='"
					+ dictTFactor.getDbcolumnname().toUpperCase() + "'";
			Integer isColumnExist = dictDBExecuteService
					.getIfExistsInDB(ifExistsColumnSql);
			if (!"1".equals(dictTFactor.getIsbandcol()) && isColumnExist > 0) {
				StringBuilder sb = new StringBuilder();
				sb.append(DictDBConstants.ALTER_TABLE + " ");
				sb.append(prefix + tableName + " ");
				sb.append(DictDBConstants.DROP + " ");
				sb.append(DictDBConstants.COLUMN + " ");
				sb.append(dictTFactor.getDbcolumnname().toUpperCase());
				sbsql = sb.toString();
			}
			Map<String, String> columnidMap = new HashMap<String, String>();
			columnidMap.put("condColumnID", dictTFactor.getColumnid());
			int columnIdCount = this.entryMapper
					.getDataRefrelaCount(columnidMap);
			if (columnIdCount > 0)
				throw new Exception("引用列关系定义中该列已经被引用，不能删除！");
			try {
				this.deleteDictTFactor(tableName, dictTFactor, true);
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new Exception(e1.getMessage());
			}
			// alter table prefix+AABB drop column UUU;
			if (!("1".equals(ispartition))) {
				String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
						+ "'" + prefix + tableName + "'";
				Integer r_table = dictDBExecuteService
						.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
				if (r_table == 1) {
					try {
						if (sbsql != null) {
							// 更新 物理表
							this.dictDBExecuteService
									.ExecDllLongForParam(sbsql);
						}
						// 更新视图
						// String updateViewSql =
						// dictDBExecuteService.createView(ispartition,status,
						// tableName, viewColumMap,dictTModel.getSecusql());
						// this.dictDBExecuteService.ExecDllLongForParam(updateViewSql);
					} catch (Exception e) {
						e.printStackTrace();
						throw new Exception(e.getMessage());
					}
				} else {
					throw new Exception("未找到表：" + "'" + prefix + tableName
							+ "' ");
				}
			} else {
				String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
						+ "'" + prefix + tableName + "'";
				Integer r_table = dictDBExecuteService
						.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
				if (r_table == 1) {
					try {
						Map<String, String> mapp = new HashMap<String, String>();
						mapp.put("tableid", dictTFactor.getTableid());
						mapp.put("dbcolumnname", dictTFactor.getDbcolumnname());
						int count = this.dictTFactorSelfMapper
								.findOtherRepeatNameCount(mapp);
						if (sbsql != null && count < 1) {
							// 更新 物理表
							this.dictDBExecuteService
									.ExecDllLongForParam(sbsql);
						}
						if (count < 1) {
							// 更新视图
							String updateViewSql = dictDBExecuteService.createView(
									ispartition, status, tableName, viewColumMap,
									dictTModel.getSecusql(), isTask, false,
									isAllDistrict, isAllYear);
							this.dictDBExecuteService
									.ExecDllLongForParam(updateViewSql);
							// 重构视图触发器
							dictDBExecuteService
									.createFormulaTrigger(dictTModel
											.getTableid());
							// 如果有配置视图，同步更新
							if (isConfig) {
								updateViewSql = dictDBExecuteService.createView(
										ispartition, status, tableName, viewColumMap,
										dictTModel.getSecusql(), isTask, isConfig,
										isAllDistrict, isAllYear);
								this.dictDBExecuteService
										.ExecDllLongForParam(updateViewSql);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw new Exception(e.getMessage());
					}
				} else {
					throw new Exception("未找到分区表：" + "'" + prefix + tableName
							+ "' ");
				}
			}
			/*
			 * //更新主键 PK if("1".equals(dictTFactor.getIskey())){
			 * if("1".equals(status)){ listIndex.add("YEAR"); // 索引 }else
			 * if("2".equals(status)){ listIndex.add("PROVINCE"); // 索引
			 * listIndex.add("YEAR"); // 索引 } if(listIndex.size()>0){
			 * StringBuilder ifExistIndexSql = new StringBuilder();
			 * ifExistIndexSql.append(DictDBConstants.IF_EXISTS_TYPE_INDEX);
			 * ifExistIndexSql.append("'"+prefix+tableName+"' ");
			 * ifExistIndexSql.append(" AND U.INDEX_NAME = ");
			 * ifExistIndexSql.append("'IN_"+prefix+tableName+"'"); Integer
			 * r_index =
			 * dictDBExecuteService.getIfExistsInDB(ifExistIndexSql.toString
			 * ());// //删除原有index if(r_index==1){ try {
			 * this.dictDBExecuteService
			 * .ExecDllLongForParam("DROP INDEX IN_"+prefix+tableName); } catch
			 * (Exception e) { // Auto-generated catch block
			 * e.printStackTrace(); throw new Exception(e.getMessage()); } }
			 * //设定新 索引 //create unique index INDEX_AACSWLB1 on prefix+AACSWLB1
			 * (ORDERID, PROVINCE) try { String indexSql =
			 * dictDBExecuteService.createIndex(tableName, listIndex);
			 * this.dictDBExecuteService.ExecDllLongForParam(indexSql); } catch
			 * (Exception e) { // Auto-generated catch block
			 * e.printStackTrace(); throw new Exception(e.getMessage()); } } }
			 */

			/* this.deleteDictTFactor(dictTFactor,true); */

			// ------------------------同步备份表-------------------------------------------------
			String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
					+ "'" + prefix + tableName + "_BAK'";
			Integer r_table = dictDBExecuteService
					.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
			if (r_table == 1) {
				try {
					deleteDictTFactorForBak(dictTFactor, dictTModel);
				} catch (Exception e1) {
					e1.printStackTrace();
					throw new Exception(e1.getMessage());
				}

			}
			//最后统一调用刷新表视图，避免大集中多分区造成的不一致
			dictDBExecuteService.recreateViews(dictTModel.getTableid());
			// ------------------------同步备份表-------------------------------------------------
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	public Grid getDictTFactorHead(String tableid) {

		// 创建Grid
		Grid grid = new Grid();
		// 设置tableID
		grid.setTableID(tableid);
		// Grid类型
		grid.setTableDBName("mytableName");
		grid.setTableName("");
		grid.setAppID("");
		int orderId = 0;
		// 创建列
		Column deid = new Column();
		deid.setColumnID("deid");
		deid.setColumnDBName("deid");
		deid.setColumnName("数据元ID");
		deid.setAlias("数据元ID");
		deid.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		deid.setShowType(ShowType.SHOW_TYPE_HTML);
		deid.setOrderID(++orderId);
		deid.setVisible(true);

		Column name = new Column();
		name.setColumnID("name");
		name.setColumnDBName("name");
		name.setColumnName("中文名称");
		name.setAlias("中文名称");
		name.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		name.setShowType(ShowType.SHOW_TYPE_HTML);
		name.setOrderID(++orderId);
		name.setVisible(true);

		Column dbcolumnname = new Column();
		dbcolumnname.setColumnID("dbcolumnname");
		dbcolumnname.setColumnDBName("dbcolumnname");
		dbcolumnname.setColumnName("物理名称");
		dbcolumnname.setAlias("物理名称");
		dbcolumnname.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		dbcolumnname.setShowType(ShowType.SHOW_TYPE_HTML);
		dbcolumnname.setOrderID(++orderId);
		dbcolumnname.setVisible(true);

		Column datatype = new Column();
		datatype.setColumnID("datatype");
		datatype.setColumnDBName("datatype");
		datatype.setColumnName("数据类型");
		datatype.setAlias("数据类型");
		datatype.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		datatype.setShowType(ShowType.SHOW_TYPE_HTML);
		datatype.setOrderID(++orderId);
		datatype.setVisible(true);

		Column showformat = new Column();
		showformat.setColumnID("showformat");
		showformat.setColumnDBName("showformat");
		showformat.setColumnName("显示方式");
		showformat.setAlias("显示方式");
		showformat.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		showformat.setShowType(ShowType.SHOW_TYPE_HTML);
		showformat.setOrderID(++orderId);
		showformat.setVisible(true);

		Column showwidth = new Column();
		showwidth.setColumnID("showwidth");
		showwidth.setColumnDBName("showwidth");
		showwidth.setColumnName("显示宽度");
		showwidth.setAlias("显示宽度");
		showwidth.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		showwidth.setShowType(ShowType.SHOW_TYPE_HTML);
		showwidth.setOrderID(++orderId);
		showwidth.setVisible(true);

		Column csid = new Column();
		csid.setColumnID("csid");
		csid.setColumnDBName("csid");
		csid.setColumnName("引用表");
		csid.setAlias("引用表");
		csid.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		csid.setShowType(ShowType.SHOW_TYPE_HTML);
		csid.setOrderID(++orderId);
		csid.setVisible(false);

		Column csdbtablename = new Column();
		csdbtablename.setColumnID("csdbtablename");
		csdbtablename.setColumnDBName("csdbtablename");
		csdbtablename.setColumnName("引用表");
		csdbtablename.setAlias("引用表");
		csdbtablename.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		csdbtablename.setShowType(ShowType.SHOW_TYPE_HTML);
		csdbtablename.setOrderID(++orderId);
		csdbtablename.setVisible(true);

		Column columnid = new Column();
		columnid.setColumnID("columnid");
		columnid.setColumnDBName("columnid");
		columnid.setColumnName("套表id");
		columnid.setAlias("套表id");
		columnid.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		columnid.setShowType(ShowType.SHOW_TYPE_HTML);
		columnid.setOrderID(++orderId);
		columnid.setKey(true);
		columnid.setVisible(false);

		Column isKey = new Column();
		isKey.setColumnID("iskey");
		isKey.setColumnDBName("iskey");
		isKey.setColumnName("是否主键");
		isKey.setAlias("是否主键");
		isKey.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		isKey.setShowType(ShowType.SHOW_TYPE_HTML);
		isKey.setOrderID(++orderId);
		isKey.setVisible(true);

		Column isVisible = new Column();
		isVisible.setColumnID("isvisible");
		isVisible.setColumnDBName("isvisible");
		isVisible.setColumnName("是否可见");
		isVisible.setAlias("是否可见");
		isVisible.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		isVisible.setShowType(ShowType.SHOW_TYPE_HTML);
		isVisible.setOrderID(++orderId);
		isVisible.setVisible(true);

		List<Column> columnlist = new ArrayList<Column>();
		columnlist.add(deid);
		columnlist.add(name);
		columnlist.add(dbcolumnname);
		columnlist.add(datatype);
		columnlist.add(showformat);
		columnlist.add(showwidth);
		columnlist.add(csid);
		columnlist.add(csdbtablename);
		columnlist.add(isKey);
		columnlist.add(isVisible);
		grid.setColumnList(columnlist);

		return grid;
	}

	/**
	 * 添加标题
	 */
	@Override
	public void insertDictTFactorForTitle(DictTFactorPO dictTFactor)
			throws Exception {

		if (dictTFactor != null) {
			try {
				this.insertDictTFactor(null, dictTFactor, false);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("添加标题列出错");
			}
		}
	}

	/**
	 * 删除标题
	 */
	@Override
	public void deleteDictTFactorForTitle(String clumnid) throws Exception {

		try {
			if (clumnid != null && !"".equals(clumnid)) {
				DictTFactorPO dictTFactor = this.dictTFactorService
						.getDictTFactorByColumnId(clumnid);
				// 视图的需要同步删除平台登记
				if (("1").equals(dictTFactor.getIsleaf())
						&& dictTFactor.getDbcolumnname() != null
						&& !("").equals(dictTFactor.getDbcolumnname())) {
					DictTModelPO dictTModel = dictTModelService
							.getDictTModelByID(dictTFactor.getTableid(), false);
					String tableName = dictTModel.getDbtablename()
							.toUpperCase();
					this.deleteDictTFactor(tableName, dictTFactor, true);
				} else {
					this.deleteDictTFactor(null, dictTFactor, false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("删除标题列出错");
		}
	}

	@Override
	public Grid getColumnDataHead(String tableid) {

		// 创建Grid
		Grid grid = new Grid();
		// 设置tableID
		grid.setTableID(tableid);
		// Grid类型
		grid.setTableDBName("mytableName");
		grid.setTableName("");
		grid.setAppID("");
		int orderId = 0;
		// 创建列
		Column name = new Column();
		name.setColumnID("name");
		name.setColumnDBName("name");
		name.setColumnName("中文名称");
		name.setAlias("中文名称");
		name.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		name.setShowType(ShowType.SHOW_TYPE_HTML);
		name.setOrderID(++orderId);
		name.setDataLength(70);
		name.setVisible(true);

		Column dbcolumnname = new Column();
		dbcolumnname.setColumnID("dbcolumnname");
		dbcolumnname.setColumnDBName("dbcolumnname");
		dbcolumnname.setColumnName("物理名称");
		dbcolumnname.setAlias("物理名称");
		dbcolumnname.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		dbcolumnname.setShowType(ShowType.SHOW_TYPE_HTML);
		dbcolumnname.setOrderID(++orderId);
		dbcolumnname.setDataLength(70);
		dbcolumnname.setVisible(true);

		Column changename = new Column();
		changename.setColumnID("changename");
		changename.setColumnDBName("changename");
		changename.setColumnName("物理别名");
		changename.setAlias("物理别名");
		changename.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		changename.setShowType(ShowType.SHOW_TYPE_HTML);
		changename.setOrderID(++orderId);
		changename.setDataLength(70);
		changename.setVisible(false);

		Column isbandcol = new Column();
		isbandcol.setColumnID("isbandcol");
		isbandcol.setColumnDBName("isbandcol");
		isbandcol.setColumnName("是否绑定列");
		isbandcol.setAlias("是否绑定列");
		isbandcol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		isbandcol.setShowType(ShowType.SHOW_TYPE_HTML);
		isbandcol.setOrderID(++orderId);
		isbandcol.setDataLength(70);
		isbandcol.setVisible(false);

		Column bandcolumnid = new Column();
		bandcolumnid.setColumnID("bandcolumnid");
		bandcolumnid.setColumnDBName("bandcolumnid");
		bandcolumnid.setColumnName("本表引用id");
		bandcolumnid.setAlias("本表引用id");
		bandcolumnid.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		bandcolumnid.setShowType(ShowType.SHOW_TYPE_HTML);
		bandcolumnid.setOrderID(++orderId);
		bandcolumnid.setDataLength(70);
		bandcolumnid.setVisible(false);

		List<Column> columnList = new ArrayList<Column>();

		columnList.add(name);
		columnList.add(dbcolumnname);
		columnList.add(changename);
		columnList.add(isbandcol);
		columnList.add(bandcolumnid);
		grid.setColumnList(columnList);
		return grid;
	}

	public Grid getSettingColumnDataHead(String tableid,
			Map<String, List<DictTFactorPO>> map) {

		// 创建Grid
		// 创建Grid
		Grid grid = new Grid();
		// 设置tableID
		grid.setTableID(tableid);
		// Grid类型
		grid.setTableDBName("mytableName");
		grid.setTableName("");
		grid.setAppID("");
		int orderId = 0;

		List columnidList = new ArrayList();
		Map<String, String> mapFactor = new HashMap<String, String>();
		List<String> tables = new ArrayList<String>();
		for (Entry<String, List<DictTFactorPO>> m : map.entrySet()) {
			String table_id = m.getKey();
			List<DictTFactorPO> dtfs = m.getValue();
			DictTModelPO dtm = this.dictTModelMapper.getDictTModel(table_id);
			if (dtm != null) {
				tables.add(table_id);
				DictTFactorPO d = new DictTFactorPO();
				d.setColumnid(dtm.getTableid());
				d.setTableid("");
				d.setName(dtm.getDbtablename());
				d.setIsleaf("0");
				d.setDbcolumnname(dtm.getName());
				d.setChangename("");
				columnidList.add(d);
			}
			for (DictTFactorPO dtf : dtfs) {
				mapFactor.put(dtf.getColumnid(), dtf.getColumnid());
				columnidList.add(dtf);
			}
		}
		List tocolumnidList = new ArrayList();
		for (String tid : tables) {
			DictTModelPO dtm = this.dictTModelMapper.getDictTModel(tid);
			if (dtm != null) {
				DictTFactorPO d = new DictTFactorPO();
				d.setColumnid(dtm.getTableid());
				d.setTableid("");
				d.setName(dtm.getDbtablename());
				d.setIsleaf("0");
				d.setDbcolumnname(dtm.getName());
				d.setChangename("");
				boolean flag = false;
				List<DictTFactorPO> factors = this.dictTFactorService
						.getDictTFactorByTableidAndType(dtm.getTableid(), "1");
				for (DictTFactorPO dtf : factors) {
					if (mapFactor.get(dtf.getColumnid()) == null) {
						tocolumnidList.add(dtf);
						flag = true;
					}
				}
				if (flag) {
					tocolumnidList.add(d);
				}
			}
		}

		// 创建列
		Column columnid = new Column();
		columnid.setColumnID("columnid");
		columnid.setColumnDBName("columnid");
		columnid.setColumnName("视图列");
		columnid.setAlias("视图列");
		columnid.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		columnid.setShowType(ShowType.SHOW_TYPE_HTML);
		columnid.setOrderID(++orderId);
		columnid.setDataLength(70);
		columnid.setKey(true);
		columnid.setVisible(true);

		Column tocolumnid = new Column();
		tocolumnid.setColumnID("tocolumnid");
		tocolumnid.setColumnDBName("tocolumnid");
		tocolumnid.setColumnName("对应表列");
		tocolumnid.setAlias("对应表列");
		tocolumnid.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		tocolumnid.setShowType(ShowType.SHOW_TYPE_HTML);
		tocolumnid.setOrderID(++orderId);
		tocolumnid.setDataLength(70);
		tocolumnid.setVisible(true);

		// RefColumn columnid = new RefColumn("视图列","columnid");
		// columnid.setColumnId("columnid");
		// columnid.setEditable(true);
		// columnid.setIdField("columnid");
		// columnid.setSuperIdField("tableid");
		// columnid.setRefShowFormat("#dbcolumnname");
		// columnid.setDataList(columnidList);
		// columnid.setLeaf(true);
		//
		// Column columnName = new Column("视图列名称","SNAME_columnid");
		// columnName.setVisible(false);

		// RefColumn tocolumnid = new RefColumn("对应表列","tocolumnid");
		// tocolumnid.setEditable(true);
		// tocolumnid.setColumnId("tocolumnid");
		// tocolumnid.setIdField("columnid");
		// tocolumnid.setSuperIdField("tableid");
		// tocolumnid.setRefShowFormat("#dbcolumnname");
		// tocolumnid.setDataList(tocolumnidList);
		// tocolumnid.setLeaf(true);
		// Column tocolumnName = new Column("对应表列名称","SNAME_tocolumnid");
		// tocolumnName.setVisible(false);
		// Column nocheckTableid = new
		// Column("nocheckTableid","nocheckTableid");
		// nocheckTableid.setEditable(false);
		// nocheckTableid.setVisible(false);

		List<Column> columnList = new ArrayList<Column>();
		columnList.add(columnid);
		// columnList.add(columnName);
		columnList.add(tocolumnid);
		// columnList.add(tocolumnName);
		// columnList.add(nocheckTableid);
		grid.setColumnList(columnList);
		return grid;
	}

	/**
     *     
     */
	@Override
	public List<DictTFactorPO> getSettingColumnChangeData(String tableid,
			Map<String, List<DictTFactorPO>> map) {

		Map<String, String> mapFactor = new HashMap<String, String>();
		List<String> tables = new ArrayList<String>();
		for (Entry<String, List<DictTFactorPO>> m : map.entrySet()) {
			String table_id = m.getKey();
			List<DictTFactorPO> dtfs = m.getValue();
			DictTModelPO dtm = this.dictTModelMapper.getDictTModel(table_id);
			if (dtm != null) {
				tables.add(table_id);
			}
			for (DictTFactorPO dtf : dtfs) {
				mapFactor.put(dtf.getColumnid(), dtf.getColumnid());

			}
		}
		List<DictTFactorPO> tableColumnList = new ArrayList<DictTFactorPO>();
		for (String tid : tables) {
			DictTModelPO dtm = this.dictTModelMapper.getDictTModel(tid);
			if (dtm != null) {
				DictTFactorPO d = new DictTFactorPO();
				d.setColumnid(dtm.getTableid());
				d.setTableid("");
				d.setName(dtm.getName());
				d.setIsleaf("0");
				d.setDbcolumnname(dtm.getDbtablename());
				d.setChangename("");
				boolean flag = false;
				List<DictTFactorPO> factors = this.dictTFactorService
						.getDictTFactorByTableidAndType(dtm.getTableid(), "1");
				for (DictTFactorPO dtf : factors) {
					if (mapFactor.get(dtf.getColumnid()) == null) {
						tableColumnList.add(dtf);
						flag = true;
					}
				}
				if (flag) {
					tableColumnList.add(d);
				}
			}
		}
		/*
		 * Column col1 = setColumn("tableColumn", "tableColumn", "对应表列", "对应表列",
		 * JSTypeUtils.getJSDateType(DataType.STRING), ShowType.SHOW_TYPE_HTML,
		 * 1, true,false);
		 */
		return tableColumnList;
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

	/**
	 * 修改列 for 3 可更新试图
	 */
	@Override
	public void updateDictTFactorForUpdateView(DictTFactorPO oldDictTFactor,
			DictTFactorPO newDictTFactor, DictTModelPO dictTModel)
			throws Exception {
		// TODO:多表预留
		this.updateDictTFactor(newDictTFactor, true);

	}

	/**
	 * 从新 创建 视图 触发器 for 3 可更新视图
	 * 
	 * @param newDBTableName
	 * @param dictTModel
	 * @param sourceTableMap
	 * @param settingList
	 * @throws Exception
	 */
	public void reCreateDictModleForUpdateView(DictTModelPO dictTModel,
			Map<String, List<DictTFactorPO>> sourceTableMap,
			List<Map<String, String>> settingList) throws Exception {

		String viewName = dictTModel.getDbtablename().toUpperCase();
		String status = this.dictDBExecuteService.getGlobalIsmultdb();
		Map<String, String> sqlmap;
		try {
			sqlmap = dictDBExecuteService.createViewTriggerForUpdataView(
					viewName, dictTModel, sourceTableMap, settingList,
					dictTModel.getSecusql(), status);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new Exception(e1.getMessage());
		}
		String viewSql = sqlmap.get(DictDBConstants.VIEWFORUPDATEVIEW);
		String triggerSql = sqlmap.get(DictDBConstants.TRIGGERFORUPDATEVIEW);
		try {
			/*
			 * String ifExistsViewSql_ = DictDBConstants.IF_EXISTS_TYPE_VIEW +
			 * "'"+viewName+"'"; Integer r_view_ =
			 * dictDBExecuteService.getIfExistsInDB(ifExistsViewSql_);//同名视图
			 * if(r_view_==1){
			 * this.dictDBExecuteService.ExecDllLongForParam("DROP VIEW "+
			 * viewName); }
			 */
			this.dictDBExecuteService.ExecDllLongForParam(viewSql);
			/*
			 * String ifExistsTriggerSql =
			 * DictDBConstants.IF_EXISTS_TYPE_TRIGGER +
			 * "'TR_"+prefix+viewName+"'"; Integer r_trigger =
			 * dictDBExecuteService.getIfExistsInDB(ifExistsTriggerSql);//同名触发器
			 * if(r_trigger==1){
			 * this.dictDBExecuteService.ExecDllLongForParam("DROP TRIGGER "
			 * +"TR_"+prefix+viewName); }
			 */
			if (!sourceTableMap.isEmpty() && sourceTableMap.size() > 1) {
				this.dictDBExecuteService.ExecDllLongForParam(triggerSql);
			}
			// 重构视图触发器
			dictDBExecuteService.createFormulaTrigger(dictTModel.getTableid());
			//最后统一调用刷新表视图，避免大集中多分区造成的不一致
			dictDBExecuteService.recreateViews(dictTModel.getTableid());
		} catch (Exception e) { // roll back
			e.printStackTrace();
			/*
			 * String ifExistsViewSql_ = DictDBConstants.IF_EXISTS_TYPE_VIEW +
			 * "'"+viewName+"'"; Integer r_view_ =
			 * dictDBExecuteService.getIfExistsInDB(ifExistsViewSql_);//同名视图
			 * if(r_view_==1){
			 * this.dictDBExecuteService.ExecDllLongForParam("DROP VIEW "+
			 * viewName); } String ifExistsTriggerSql =
			 * DictDBConstants.IF_EXISTS_TYPE_TRIGGER +
			 * "'TR_"+prefix+viewName+"'"; Integer r_trigger =
			 * dictDBExecuteService.getIfExistsInDB(ifExistsTriggerSql);//同名触发器
			 * if(r_trigger==1){
			 * this.dictDBExecuteService.ExecDllLongForParam("DROP TRIGGER "
			 * +"TR_"+prefix+viewName); }
			 */
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 删除列 for 3 可更新试图
	 */
	@Override
	public void deleteDictTFactorForUpdateView(DictTFactorPO dictTFactor,
			DictTModelPO dictTModel) throws Exception {

		Map<String, List<DictTFactorPO>> sourceTableMap = new HashMap<String, List<DictTFactorPO>>();
		List<Map<String, String>> settingList = new ArrayList<Map<String, String>>();
		String tableid = dictTModel.getTableid();
		String tableDBName = dictTModel.getDbtablename().toUpperCase();
		List<DictTFactorPO> listFactor = dictTFactorService
				.getLeafFactorListByTableID(tableid);
		Map<String, String> mapkey = new HashMap<String, String>();

		for (DictTFactorPO dtf : listFactor) {
			String frmTableID = dtf.getFrmtabid();
			if (dtf.getColumnid().equals(dictTFactor.getColumnid())) {
				continue;
			}
			if (sourceTableMap.containsKey(frmTableID)) {
				List<DictTFactorPO> dtfs = sourceTableMap.get(frmTableID);
				dtfs.add(dtf);
			} else {
				List<DictTFactorPO> dtfs = new ArrayList<DictTFactorPO>();
				dtfs.add(dtf);
				sourceTableMap.put(frmTableID, dtfs);
			}
			if ("1".equals(dtf.getIsbandcol())) {
				mapkey.put(dtf.getBandcolumnid(), dtf.getBandcolumnid());
			}
		}
		if (mapkey.get(dictTFactor.getColumnid()) != null) {
			throw new Exception("该列已经被绑定，不能删除！");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("", tableid);
		List<DictTUpdateviewPO> listDictTUpdateview = dictTUpdateviewMapper
				.findDictTUpdateview(map);
		for (DictTUpdateviewPO dtu : listDictTUpdateview) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("columnid", dtu.getColumnid());
			m.put("tocolumnid", dtu.getTocolumnid());
			settingList.add(m);
		}
		this.deleteDictTFactor(tableDBName, dictTFactor, true);
		try {
			reCreateDictModleForUpdateView(dictTModel, sourceTableMap,
					settingList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Integer getMAXColumnOrderid(Map<String, Object> map) {

		Integer max = 0;
		if (map != null) {
			Map<String, Object> m = this.dictTFactorSelfMapper
					.getMAXColumnOrderid(map);
			if (m.get("ORDERID") != null && !"".equals(m.get("ORDERID"))) {
				max = Integer.parseInt(m.get("ORDERID") + "");
			}
		}
		return max;
	}

	/**
	 * 数据库 添加 列
	 * 
	 */
	@Override
	public void insertDictTFactor(String tableDBName,
			DictTFactorPO dictTFactor, boolean isAsync) throws Exception {
		if (dictTFactor != null) {
			dictTFactorSelfMapper.insertDictTFactor(dictTFactor);
			String tableID = dictTFactor.getTableid();
			DictTModelPO dictTModel = dictTModelService.getDictTModelByID(
					tableID, false);
			if (dictTModel != null) {
				// 清除缓存
				// 清除表ID缓存
				String[] tableIDKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictTable.ID.getCacheKey(),
						dictTModel.getTableid() };
				dataCacheService.put(tableIDKeys, null);
				// 清除表物理名缓存
				String[] tableDBNameKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictTable.DBNAME.getCacheKey(),
						dictTModel.getDbtablename() };
				dataCacheService.put(tableDBNameKeys, null);
				// 清除表类型
				String[] tableTypeKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictTable.TYPE.getCacheKey(),
						dictTModel.getTabletype() };
				dataCacheService.put(tableTypeKeys, null);
				// 清除表处理类型
				String[] tableDealTypeKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictTable.DEALTYPE.getCacheKey(),
						dictTModel.getDealtype() };
				dataCacheService.put(tableDealTypeKeys, null);

				String suitID = dictTModel.getSuitid();
				// 清除套表缓存
				String[] tableSuitKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictSuit.ID.getCacheKey() };
				dataCacheService.put(tableSuitKeys, null);
				String appID = dictTModel.getAppid();
				// 清除app列缓存
				String[] columnAppKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictColumn.APP.getCacheKey(), appID };
				dataCacheService.put(columnAppKeys, null);
				// 清除app表缓存
				String[] tableAppKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictTable.APP.getCacheKey(), appID };
				dataCacheService.put(tableAppKeys, null);
				// 清除app套表缓存
				String[] suitAppKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictSuit.APP.getCacheKey() };
				dataCacheService.put(suitAppKeys, null);
				// 清除所有业务表类缓存
				String[] allDICTKeys = { DictCacheKey.CACHE_KEY_DICT };
				dataCacheService.put(allDICTKeys, null);
			}
			if (isAsync && 4 != dictTFactor.getDatatype()) {
				try {
					// 注册到平台
					registerDicColumn(tableDBName, dictTFactor);
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("同步增加平台注册列信息出错");
				}
			}
		}
	}

	/**
	 * 数据库 修改 列
	 * 
	 */
	@Override
	public void updateDictTFactor(DictTFactorPO dictTFactor, boolean isAsync)
			throws Exception {
		try {
			if (dictTFactor != null) {
				dictTFactorSelfMapper.updateDictTFactor(dictTFactor);
				String tableID = dictTFactor.getTableid();
				DictTModelPO dictTModel = dictTModelService.getDictTModelByID(
						tableID, false);
				// 清除缓存
				// 清除列ID缓存
				String[] columnIDKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictColumn.ID.getCacheKey(),
						dictTFactor.getColumnid() };
				dataCacheService.put(columnIDKeys, null);
				if (4 != dictTFactor.getDatatype()) {
					// 清除列物理列名缓存
					String[] columnDBNameKeys = { DictCacheKey.CACHE_KEY_DICT,
							DictCacheKey.DictColumn.DBNAME.getCacheKey(),
							dictTFactor.getDbcolumnname() };
					dataCacheService.put(columnDBNameKeys, null);
				}
				// 清除表ID缓存
				String[] tableIDKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictTable.ID.getCacheKey(),
						dictTModel.getTableid() };
				dataCacheService.put(tableIDKeys, null);
				// 清除表物理名缓存
				String[] tableDBNameKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictTable.DBNAME.getCacheKey(),
						dictTModel.getDbtablename() };
				dataCacheService.put(tableDBNameKeys, null);
				// 清除表类型
				String[] tableTypeKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictTable.TYPE.getCacheKey(),
						dictTModel.getTabletype() };
				dataCacheService.put(tableTypeKeys, null);
				// 清除表处理类型
				String[] tableDealTypeKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictTable.DEALTYPE.getCacheKey(),
						dictTModel.getDealtype() };
				dataCacheService.put(tableDealTypeKeys, null);

				String suitID = dictTModel.getSuitid();
				// 清除套表缓存
				String[] tableSuitKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictSuit.ID.getCacheKey() };
				dataCacheService.put(tableSuitKeys, null);
				String appID = dictTModel.getAppid();
				// 清除app列缓存
				String[] columnAppKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictColumn.APP.getCacheKey(), appID };
				dataCacheService.put(columnAppKeys, null);
				// 清除app表缓存
				String[] tableAppKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictTable.APP.getCacheKey(), appID };
				dataCacheService.put(tableAppKeys, null);
				// 清除app套表缓存
				String[] suitAppKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictSuit.APP.getCacheKey() };
				dataCacheService.put(suitAppKeys, null);
				// 清除所有业务表类缓存
				String[] allDICTKeys = { DictCacheKey.CACHE_KEY_DICT };
				dataCacheService.put(allDICTKeys, null);
				dictTFactor = this.dictTFactorService
						.getDictTFactorByColumnId(dictTFactor.getColumnid());
				if (isAsync && 4 != dictTFactor.getDatatype()) {
					try {
						// 同步更新平台信息
						this.updateRegisterDicColumn(dictTModel, dictTFactor);
					} catch (Exception e) {
						e.printStackTrace();
						throw new Exception("同步修改平台注册列信息出错");
					}
				}
				// 增加列注释
				dictTModelSelfService.addColumnComment4Table(dictTModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 数据库 删除 列
	 * 
	 */
	@Override
	public void deleteDictTFactor(String tableDBName,
			DictTFactorPO dictTFactor, boolean isAsync) throws Exception {
		if (dictTFactor != null) {
			String columnid = dictTFactor.getColumnid();
			String tableID = dictTFactor.getTableid();
			DictTModelPO dictTModel = dictTModelService.getDictTModelByID(
					tableID, false);
			dictTFactorSelfMapper.deleteDictTFactor(columnid);
			// 清除缓存
			// 清除列ID缓存
			String[] columnIDKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictColumn.ID.getCacheKey(),
					dictTFactor.getColumnid() };
			dataCacheService.put(columnIDKeys, null);
			if (4 != dictTFactor.getDatatype()) {
				// 清除列物理列名缓存
				String[] columnDBNameKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictColumn.DBNAME.getCacheKey(),
						dictTFactor.getDbcolumnname() };
				dataCacheService.put(columnDBNameKeys, null);
			}
			// 清除表ID缓存
			String[] tableIDKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.ID.getCacheKey(),
					dictTModel.getTableid() };
			dataCacheService.put(tableIDKeys, null);
			// 清除表物理名缓存
			String[] tableDBNameKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.DBNAME.getCacheKey(),
					dictTModel.getDbtablename() };
			dataCacheService.put(tableDBNameKeys, null);
			// 清除表类型
			String[] tableTypeKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					dictTModel.getTabletype() };
			dataCacheService.put(tableTypeKeys, null);
			// 清除表处理类型
			String[] tableDealTypeKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.DEALTYPE.getCacheKey(),
					dictTModel.getDealtype() };
			dataCacheService.put(tableDealTypeKeys, null);

			String suitID = dictTModel.getSuitid();
			// 清除套表缓存
			String[] tableSuitKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictSuit.ID.getCacheKey() };
			dataCacheService.put(tableSuitKeys, null);
			String appID = dictTModel.getAppid();
			// 清除app列缓存
			String[] columnAppKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictColumn.APP.getCacheKey(), appID };
			dataCacheService.put(columnAppKeys, null);
			// 清除app表缓存
			String[] tableAppKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.APP.getCacheKey(), appID };
			dataCacheService.put(tableAppKeys, null);
			// 清除app套表缓存
			String[] suitAppKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictSuit.APP.getCacheKey() };
			dataCacheService.put(suitAppKeys, null);
			// 清除所有业务表类缓存
			String[] allDICTKeys = { DictCacheKey.CACHE_KEY_DICT };
			dataCacheService.put(allDICTKeys, null);
			// 平台接口
			if (isAsync) {
				try {
					this.deleteRegisterDicColumn(tableDBName, dictTFactor);
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("同步删除平台注册列信息出错");
				}
			}
		}
	}

	/**
	 * 增加列 同步 备份表
	 */
	public void createDictTFactorForBak(DictTModelPO dictTModel,
			DictTFactorPO dictTFactor, boolean needInsertColsIntoTable)
			throws Exception {
		String tableName = dictTModel.getDbtablename().toUpperCase() + "_BAK";
		String ispartition = dictTModel.getIspartition();
		String status = this.dictDBExecuteService.getGlobalIsmultdb();
		String prefix = DictDBConstants.PREFIX_TABLE;
		if (status.equals("0")) {
			prefix = "";
		}
		Boolean isTask = false, isAllDistrict = false, isAllYear = false;
		if ("1".equals(dictTModel.getIstask())) {
			isTask = true;
		}
		if ("1".equals(dictTModel.getIsalldistrict())) {
			isAllDistrict = true;
		}
		if ("1".equals(dictTModel.getIsallyear())) {
			isAllYear = true;
		}
		String ifExistsColumnSql = "'";
		Integer isColumnExist = 0;
		if (!"YEAR".equals(dictTFactor.getDbcolumnname().trim())
				&& !"PROVINCE".equals(dictTFactor.getDbcolumnname().trim())
				&& !"STATUS".equals(dictTFactor.getDbcolumnname().trim())
				&& !"DBVERSION".equals(dictTFactor.getDbcolumnname().trim())
				&& !"BAKUSERID".equals(dictTFactor.getDbcolumnname().trim())
				&& !"BAKVERSION".equals(dictTFactor.getDbcolumnname().trim())
				&& !"BAKTYPE".equals(dictTFactor.getDbcolumnname().trim())) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableid", dictTModel.getTableid());
			map.put("isleaf", "1");
			/*
			 * List<String> listIndex = new ArrayList<String>();
			 * if("1".equals(status)){ listIndex.add("YEAR"); // 索引
			 * listIndex.add("STATUS"); }else if("2".equals(status)){
			 * listIndex.add("PROVINCE"); // 索引 listIndex.add("YEAR"); // 索引
			 * listIndex.add("STATUS"); }
			 */
			// listIndex.add("BAKVERSION");//索引*/
			List<DictTFactorPO> factorList = this.dictTFactorMapper
					.findDictTFactor(map);
			Map<String, List<DictTFactorPO>> viewColumMap = new HashMap<String, List<DictTFactorPO>>();
			
			List list_bak = dictTFactorMapper.getTableBakAllColumns(prefix+ tableName);
			//增加对bak表的判断，如果viewColumMap不包含(备份操作传过来的多个列，remove掉)
			//创建一个Map存bak表的所有列
			Map map_bak = new HashMap();
			if(list_bak!=null && list_bak.size()>0){
				for(int i = 0 ; i < list_bak.size() ; i ++){
					Map map1 = (Map)list_bak.get(i);
					String colums= map1.get("COLUMN_NAME").toString();
					map_bak.put(colums, "1");
				}
			}

			// 原有字段
			boolean flag = true;
			for (DictTFactorPO dtf : factorList) {
				String dbName = dtf.getDbcolumnname();
				//原有视图字段
				if(dbName !=null){
					if(map_bak.get(dbName)==null 
							&& !"1".equals(dtf.getIsbandcol())){//如果是绑定列，不在备份的p#表生成列
						String alterSql = getInstSql(dtf,prefix + tableName);
						this.dictDBExecuteService
						.ExecDllLongForParam(alterSql);
						List<DictTFactorPO> listFactors = viewColumMap.get(prefix
								+ tableName);
						listFactors.add(dtf);
						viewColumMap.put(prefix + tableName, listFactors);

						continue;
					}
				}
				// //原有视图字段
				if (dtf.getDatatype() != 4) {
					List<DictTFactorPO> listFactors = viewColumMap.get(prefix
							+ tableName);
					if (listFactors == null) {
						listFactors = new ArrayList<DictTFactorPO>();
					}
					listFactors.add(dtf);
					viewColumMap.put(prefix + tableName, listFactors);
				}
			}
			List<DictTFactorPO> listFactor = viewColumMap.get(prefix
					+ tableName);
			if (listFactor == null) {
				listFactor = new ArrayList<DictTFactorPO>();
			}
			DictTFactorPO dv = new DictTFactorPO();
			dv.setDbcolumnname("BAKVERSION");
			DictTFactorPO du = new DictTFactorPO();
			du.setDbcolumnname("BAKUSERID");
			DictTFactorPO dt = new DictTFactorPO();
			dt.setDbcolumnname("BAKTYPE");
			listFactor.add(dv);
			listFactor.add(du);
			listFactor.add(dt);
			viewColumMap.put(prefix + tableName, listFactor);
			// 更新视图
			String updateViewSql = dictDBExecuteService.createView(
					ispartition, status, tableName, viewColumMap,
					dictTModel.getSecusql(), isTask, false,
					isAllDistrict, isAllYear);
			this.dictDBExecuteService
			.ExecDllLongForParam(updateViewSql);
			String alterSql = null;
			if (!"1".equals(dictTFactor.getIsbandcol())) {
				StringBuilder sb = new StringBuilder();
				sb.append(DictDBConstants.ALTER_TABLE + " ");
				sb.append(prefix + tableName + " ");
				sb.append(DictDBConstants.ADD + " ");
				sb.append(dictTFactor.getDbcolumnname().toUpperCase() + " ");
				Integer type = dictTFactor.getDatatype();
				if (type == 3) {
					if (dictTFactor.getDatalength() == 1) {
						sb.append("CHAR ");
					} else {
						sb.append(DictDBConstants.dataType.get(type) + " ");
					}
				} else if (type == 7) {
					sb.append(" VARCHAR2 ");
				} else {
					sb.append(DictDBConstants.dataType.get(type) + " ");
				}
				if (type != 8 && type != 10) {
					sb.append(" (");
					if (type == 7) {
						sb.append("4000");
					} else {
						sb.append(dictTFactor.getDatalength());
					}
					if (type == 2) {
						if (dictTFactor.getScale() != null
								&& !"".equals(dictTFactor.getScale()))
							sb.append("," + dictTFactor.getScale());
					}
					sb.append(" ) ");
					// 虚列
					if ("1".equals(dictTFactor.getIsvirtual())) {
						// plevel varchar2(3) generated always as() virtual
						sb.append(DictDBConstants.GENERATED + " ");
						sb.append(DictDBConstants.ALWAYS + " ");
						sb.append(DictDBConstants.AS + " (");
						sb.append(dictTFactor.getVircontext() + " )");
						sb.append(DictDBConstants.VIRTUAL + " ");
					} else {
						if (dictTFactor.getDefaultvalue() != null
								&& !"".equals(dictTFactor.getDefaultvalue())) {
							// 默认值
							if (type == 1 || type == 2) {
								sb.append(DictDBConstants.DEFAULT + " "
										+ dictTFactor.getDefaultvalue() + " ");
							} else if (type == 3 || type == 4 || type == 6
									|| type == 9) {
								sb.append(DictDBConstants.DEFAULT + " "
										+ dictTFactor.getDefaultvalue() + " ");
							}
						}
					}
					/*
					 * if("1".equals(dictTFactor.getIskey())||!"1".equals(
					 * dictTFactor.getNullable())){
					 * sb.append(DictDBConstants.NOT_NULL); }
					 */
				}
				alterSql = sb.toString();
			}
			/**
			 * 单一物理表
			 */
			if (!("1".equals(ispartition))) {
				// 单物理表
				String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
						+ "'" + prefix + tableName + "'";
				Integer isTableExist = dictDBExecuteService
						.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
				if (isTableExist == 1) {
					try {
						if (alterSql != null && needInsertColsIntoTable) {// 新列
							ifExistsColumnSql = DictDBConstants.IF_EXISTS_TYPE_COLUMN + "'"
									+ prefix + tableName + "' AND  COLUMN_NAME ='"
									+ dictTFactor.getDbcolumnname() + "'";
							isColumnExist = dictDBExecuteService
									.getIfExistsInDB(ifExistsColumnSql);
							// 不是绑定列
							// 也不是虚列
							// 更新 物理表
							if (isColumnExist == 0) {
								this.dictDBExecuteService
										.ExecDllLongForParam(alterSql);
							}
						}
						// 更新视图
						// String updateViewSql =
						// dictDBExecuteService.createView(ispartition,status,
						// tableName, viewColumMap,dictTModel.getSecusql());
						// this.dictDBExecuteService.ExecDllLongForParam(updateViewSql);
					} catch (Exception e) {
						// 物理表 roll back
						String ifhave_ = DictDBConstants.IF_EXISTS_TYPE_COLUMN
								+ "'" + prefix + tableName
								+ "' AND  COLUMN_NAME ='"
								+ dictTFactor.getDbcolumnname().toUpperCase()
								+ "'";
						Integer r_have_ = dictDBExecuteService
								.getIfExistsInDB(ifhave_);
						if (r_have_ == 1) {
							// ;alter table prefix+AACSWLB2 drop column asdasd
							dictDBExecuteService
									.ExecDllLongForParam(DictDBConstants.ALTER_TABLE
											+ " "
											+ prefix
											+ tableName
											+ " DROP  COLUMN "
											+ dictTFactor.getDbcolumnname()
													.toUpperCase());
						}
						e.printStackTrace();
						throw new Exception(e.getMessage());
					}
				} else {
					throw new Exception("未找到表：" + "'" + prefix + tableName
							+ "' ");
				}

			} else {
				/**
				 * 区划
				 */
				// 更新 物理表
				// alter table prefix+AABB add bbbbbb number(3,7) default 0 not
				// null;
				String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
						+ "'" + prefix + tableName + "'";
				Integer isTableExist = dictDBExecuteService
						.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
				if (isTableExist == 1) {
					try {
						ifExistsColumnSql = DictDBConstants.IF_EXISTS_TYPE_COLUMN + "'"
								+ prefix + tableName + "' AND  COLUMN_NAME ='"
								+ dictTFactor.getDbcolumnname() + "'";
						isColumnExist = dictDBExecuteService
								.getIfExistsInDB(ifExistsColumnSql);
						if (alterSql != null && needInsertColsIntoTable
								&& isColumnExist == 0) {// 新列 不是绑定列 也不是虚列
							// 更新 物理表
							this.dictDBExecuteService
									.ExecDllLongForParam(alterSql);
							// 更新视图
							updateViewSql = dictDBExecuteService.createView(
									ispartition, status, tableName, viewColumMap,
									dictTModel.getSecusql(), isTask, false,
									isAllDistrict, isAllYear);
							this.dictDBExecuteService
							.ExecDllLongForParam(updateViewSql);
						}
					} catch (Exception e) {
						// 物理表 roll back
						String ifhave_ = DictDBConstants.IF_EXISTS_TYPE_COLUMN
								+ "'" + prefix + tableName
								+ "' AND  COLUMN_NAME ='"
								+ dictTFactor.getDbcolumnname().toUpperCase()
								+ "'";
						Integer r_have_ = dictDBExecuteService
								.getIfExistsInDB(ifhave_);
						if (r_have_ == 1) {
							String execSql = DictDBConstants.ALTER_TABLE
									+ " "+ prefix+ tableName+ " DROP  COLUMN "
									+ dictTFactor.getDbcolumnname().toUpperCase();
							dictDBExecuteService
									.ExecDllLongForParam(execSql);
						this.deleteDictTFactor(tableName, dictTFactor,false);
						}
						e.printStackTrace();
						throw new Exception(e.getMessage());
					}
				} else {
					throw new Exception("未找到分区表：" + "'" + prefix + tableName+ "' ");
				}
			}
		} else {
			throw new Exception(
					"不允许出现'YEAR','PROVINCE','STATUS','DBVERSION','BAKVERSION','BAKUSERID','BAKTYPE' 等字段.");
		}
	}

	public String getInstSql(DictTFactorPO  dictTFactor , String table ){
		StringBuilder sb = new StringBuilder();
		sb.append(DictDBConstants.ALTER_TABLE + " ");
		sb.append(table + " ");
		sb.append(DictDBConstants.ADD + " ");
		sb.append(dictTFactor.getDbcolumnname().toUpperCase() + " ");
		Integer type = dictTFactor.getDatatype();
		if (type == 3) {
			if (dictTFactor.getDatalength() == 1) {
				sb.append("CHAR ");
			} else {
				sb.append(DictDBConstants.dataType.get(type) + " ");
			}
		} else if (type == 7) {
			sb.append(" VARCHAR2 ");
		} else {
			sb.append(DictDBConstants.dataType.get(type) + " ");
		}
		if (type != 8 && type != 10) {
			sb.append(" (");
			if (type == 7) {
				sb.append("4000");
			} else {
				sb.append(dictTFactor.getDatalength());
			}
			if (type == 2) {
				if (dictTFactor.getScale() != null
						&& !"".equals(dictTFactor.getScale()))
					sb.append("," + dictTFactor.getScale());
			}
			sb.append(" ) ");
			// 虚列
			if ("1".equals(dictTFactor.getIsvirtual())) {
				// plevel varchar2(3) generated always as() virtual
				sb.append(DictDBConstants.GENERATED + " ");
				sb.append(DictDBConstants.ALWAYS + " ");
				sb.append(DictDBConstants.AS + " (");
				sb.append(dictTFactor.getVircontext() + " )");
				sb.append(DictDBConstants.VIRTUAL + " ");
			} else {
				if (dictTFactor.getDefaultvalue() != null
						&& !"".equals(dictTFactor.getDefaultvalue())) {
					// 默认值
					if (type == 1 || type == 2) {
						sb.append(DictDBConstants.DEFAULT + " "
								+ dictTFactor.getDefaultvalue() + " ");
					} else if (type == 3 || type == 4 || type == 6
							|| type == 9) {
						sb.append(DictDBConstants.DEFAULT + " "
								+ dictTFactor.getDefaultvalue() + " ");
					}
				}
			}
			/*
			 * if("1".equals(dictTFactor.getIskey())||!"1".equals(
			 * dictTFactor.getNullable())){
			 * sb.append(DictDBConstants.NOT_NULL); }
			 */
		}
		return sb.toString();
	}
	
	/**
	 * 修改列 同步 备份表
	 */
	public void updateDictTFactorForBak(DictTFactorPO oldDictTFactor,
			DictTFactorPO newDictTFactor, DictTModelPO dictTModel)
			throws Exception {
		String tableName = dictTModel.getDbtablename().toUpperCase() + "_BAK";
		String ispartition = dictTModel.getIspartition();
		String status = this.dictDBExecuteService.getGlobalIsmultdb();
		String prefix = DictDBConstants.PREFIX_TABLE;
		if (status.equals("0")) {
			prefix = "";
		}
		Boolean isTask = false, isAllDistrict = false, isAllYear = false;
		if ("1".equals(dictTModel.getIstask())) {
			isTask = true;
		}
		if ("1".equals(dictTModel.getIsalldistrict())) {
			isAllDistrict = true;
		}
		if ("1".equals(dictTModel.getIsallyear())) {
			isAllYear = true;
		}
		if (tableName != null && !"".equals(tableName)) {
			newDictTFactor.setDbcolumnname(newDictTFactor.getDbcolumnname()
					.toUpperCase());
			String newColumnName = newDictTFactor.getDbcolumnname()
					.toUpperCase();
			if (!"YEAR".equals(newDictTFactor.getDbcolumnname().trim())
					&& !"PROVINCE".equals(newDictTFactor.getDbcolumnname()
							.trim())
					&& !"STATUS"
							.equals(newDictTFactor.getDbcolumnname().trim())
					&& !"DBVERSION".equals(newDictTFactor.getDbcolumnname()
							.trim())
					&& !"BAKUSERID".equals(newDictTFactor.getDbcolumnname()
							.trim())
					&& !"BAKVERSION".equals(newDictTFactor.getDbcolumnname()
							.trim())
					&& !"BAKTYPE".equals(newDictTFactor.getDbcolumnname()
							.toUpperCase().trim())) {
				// append alter table modify ******
				String sbsql = null;
				String renamesql = null;
				// if(!(("1".equals(oldDictTFactor.getIsbandcol())&&!"1".equals(newDictTFactor.getIsbandcol()))||(!"1".equals(oldDictTFactor.getIsbandcol())&&"1".equals(newDictTFactor.getIsbandcol())))){
				if ((oldDictTFactor.getIsbandcol().equals(newDictTFactor
						.getIsbandcol()))
						&& (oldDictTFactor.getIsvirtual().equals(newDictTFactor
								.getIsvirtual()))) {
					StringBuilder sb = new StringBuilder();
					sb.append(DictDBConstants.ALTER_TABLE + " ");
					sb.append(prefix + tableName + " ");
					sb.append(DictDBConstants.MODIFY + " ");
					sb.append(newDictTFactor.getDbcolumnname() + " ");

					Integer type = newDictTFactor.getDatatype();
					if (type != oldDictTFactor.getDatatype()
							|| oldDictTFactor.getDatalength() != newDictTFactor
									.getDatalength()
							|| newDictTFactor.getScale() != oldDictTFactor
									.getScale()) {
						if (type == 3) {
							if (newDictTFactor.getDatalength() == 1) {
								sb.append("CHAR ");
							} else {
								sb.append(DictDBConstants.dataType.get(type)
										+ " ");
							}
						} else if (type == 7) {
							sb.append(" VARCHAR2 ");
						} else {
							sb.append(DictDBConstants.dataType.get(type) + " ");
						}

						if (type != 8 && type != 10) {
							sb.append(" (");
							if (type == 7) {
								sb.append("4000");
							} else {
								sb.append(newDictTFactor.getDatalength());
							}

							if (type == 2) {
								if (newDictTFactor.getScale() != null
										&& !"".equals(newDictTFactor.getScale()))
									sb.append("," + newDictTFactor.getScale());
							}
							sb.append(" ) ");
							if ("1".equals(newDictTFactor.getIsvirtual())) {
								// plevel varchar2(3) generated always as()
								// virtual
								sb.append(DictDBConstants.GENERATED + " ");
								sb.append(DictDBConstants.ALWAYS + " ");
								sb.append(DictDBConstants.AS + " (");
								sb.append(newDictTFactor.getVircontext() + " )");
								sb.append(DictDBConstants.VIRTUAL + " ");

							}
						}
					}
					if ("1".equals(newDictTFactor.getIsvirtual())) {

					} else {
						if (newDictTFactor.getDefaultvalue() != null
								&& !"".equals(newDictTFactor.getDefaultvalue())) {
							// 默认值
							if (type == 1 || type == 2) {
								sb.append(DictDBConstants.DEFAULT + " "
										+ newDictTFactor.getDefaultvalue()
										+ " ");
							} else if (type == 3 || type == 4 || type == 6
									|| type == 9) {
								sb.append(DictDBConstants.DEFAULT + " "
										+ newDictTFactor.getDefaultvalue()
										+ " ");
							}
						} else {
							sb.append(DictDBConstants.DEFAULT + " NULL ");
						}
					}
					/*
					 * if(!"1".equals(oldDictTFactor.getNullable())){//原 not
					 * null if(!"1".equals(newDictTFactor.getNullable())){
					 * if("1".equals(newDictTFactor.getIskey())){
					 * //sb.append(DictDBConstants.NOT_NULL); }else{
					 * //sb.append(DictDBConstants.NOT_NULL); }
					 * 
					 * }else if("1".equals(newDictTFactor.getNullable())){
					 * if("1".equals(newDictTFactor.getIskey())){
					 * //sb.append(DictDBConstants.NOT_NULL); }else{
					 * sb.append(" NULL "); } } }else{//原 null
					 * if(!"1".equals(newDictTFactor.getNullable())){
					 * if("1".equals(newDictTFactor.getIskey())){
					 * sb.append(DictDBConstants.NOT_NULL); }else{
					 * sb.append(DictDBConstants.NOT_NULL); }
					 * 
					 * }else if("1".equals(newDictTFactor.getNullable())){
					 * if("1".equals(newDictTFactor.getIskey())){
					 * sb.append(DictDBConstants.NOT_NULL); }else{
					 * //sb.append(" NULL "); } } }
					 */
					sbsql = sb.toString();

					StringBuilder rename = new StringBuilder();
					// alter table prefix+AABB rename column T to Tttttt;
					rename.append(DictDBConstants.ALTER_TABLE + " ");
					rename.append(prefix + tableName + " ");
					rename.append(DictDBConstants.RENAME + " ");
					rename.append(DictDBConstants.COLUMN + " ");
					rename.append(oldDictTFactor.getDbcolumnname() + " ");
					rename.append(DictDBConstants.TO + " ");
					rename.append(newDictTFactor.getDbcolumnname());
					renamesql = rename.toString();
				} else {
					throw new Exception("列修改时不能转换绑定列,或虚列.");
				}
				// 主键 and 视图字段
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tableid", dictTModel.getTableid());
				map.put("isleaf", "1");
				/*
				 * List<String> listIndex = new ArrayList<String>();
				 * if("1".equals(status)){ listIndex.add("YEAR"); // 索引 }else
				 * if("2".equals(status)){ listIndex.add("PROVINCE"); // 索引
				 * listIndex.add("YEAR"); // 索引 }
				 * listIndex.add("BAKVERSION");//索引
				 */

				List<DictTFactorPO> factorList = this.dictTFactorMapper
						.findDictTFactor(map);
				// StringBuilder viewColum = new StringBuilder();
				Map<String, List<DictTFactorPO>> viewColumMap = new HashMap<String, List<DictTFactorPO>>();
				// 原有主键
				for (DictTFactorPO dtf : factorList) {
					if (/*
						 * !(dtf.getColumnid()
						 * .equals(newDictTFactor.getColumnid())) &&
						 */dtf.getDatatype() != 4) {// 过滤掉 将要修改的
						// 原有视图字段
						List<DictTFactorPO> listFactor = viewColumMap
								.get(prefix + tableName);
						if (listFactor == null) {
							listFactor = new ArrayList<DictTFactorPO>();
							listFactor.add(dtf);
							viewColumMap.put(prefix + tableName, listFactor);
						} else {
							listFactor.add(dtf);
							viewColumMap.put(prefix + tableName, listFactor);
						}
						/*
						 * if("1".equals(dtf.getIskey())){//原有主键
						 * listIndex.add(dtf.getDbcolumnname()); }
						 */
					}
				}
				List<DictTFactorPO> listFactor = viewColumMap.get(prefix
						+ tableName);
				if (listFactor == null) {
					listFactor = new ArrayList<DictTFactorPO>();
					DictTFactorPO dv = new DictTFactorPO();
					dv.setDbcolumnname("BAKVERSION");
					DictTFactorPO du = new DictTFactorPO();
					du.setDbcolumnname("BAKUSERID");
					DictTFactorPO dt = new DictTFactorPO();
					dt.setDbcolumnname("BAKTYPE");
					listFactor.add(dv);
					listFactor.add(du);
					listFactor.add(dt);
					viewColumMap.put(prefix + tableName, listFactor);
				} else {
					DictTFactorPO dv = new DictTFactorPO();
					dv.setDbcolumnname("BAKVERSION");
					DictTFactorPO du = new DictTFactorPO();
					du.setDbcolumnname("BAKUSERID");
					DictTFactorPO dt = new DictTFactorPO();
					dt.setDbcolumnname("BAKTYPE");
					listFactor.add(dv);
					listFactor.add(du);
					listFactor.add(dt);
					viewColumMap.put(prefix + tableName, listFactor);
				}
				/*
				 * if("1".equals(newDictTFactor.getIskey())){
				 * listIndex.add(newColumnName); }
				 */
				/**
				 * 单一物理表
				 */
				if (!("1".equals(ispartition))) {
					// 单物理表
					String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
							+ "'" + prefix + tableName + "'";
					Integer r_table = dictDBExecuteService
							.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
					if (r_table == 1) {
						try {
							if (!"1".equals(newDictTFactor.getIsbandcol())) {
								if (!newColumnName.equals(oldDictTFactor
										.getDbcolumnname())) { // 与原字段名 不同
									this.dictDBExecuteService
											.ExecDllLongForParam(renamesql);
								}
								this.dictDBExecuteService
										.ExecDllLongForParam(sbsql);

							}
							// 更新视图
							// String updateViewSql =
							// dictDBExecuteService.createView(ispartition,status,
							// tableName, viewColumMap,dictTModel.getSecusql());
							// this.dictDBExecuteService.ExecDllLongForParam(updateViewSql);
						} catch (Exception e) {
							e.printStackTrace();
							throw new Exception(e.getMessage());
						}
					} else {
						throw new Exception("未找到表：" + "'" + prefix + tableName
								+ "' ");
					}

				} else {
					/**
					 * 区划
					 */
					String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
							+ "'" + prefix + tableName + "'";
					Integer r_table = dictDBExecuteService
							.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
					if (r_table == 1) {
						try {
							if (!"1".equals(newDictTFactor.getIsbandcol())) {
								if (!newColumnName.equals(oldDictTFactor
										.getDbcolumnname())) { // 与原字段名 不同
									this.dictDBExecuteService
											.ExecDllLongForParam(renamesql);
								}
								// 更新 物理表
								this.dictDBExecuteService
										.ExecDllLongForParam(sbsql);
							}
							// 更新视图
							String updateViewSql = dictDBExecuteService.createView(
									ispartition, status, tableName, viewColumMap,
									dictTModel.getSecusql(), isTask, false,
									isAllDistrict, isAllYear);
							this.dictDBExecuteService
									.ExecDllLongForParam(updateViewSql);
							//最后统一调用刷新表视图，避免大集中多分区造成的不一致
							dictDBExecuteService.recreateViews(dictTModel.getTableid());
						} catch (Exception e) {
							e.printStackTrace();
							throw new Exception(e.getMessage());
						}
					} else {
						throw new Exception("未找到分区表：" + "'" + prefix
								+ tableName + "' ");
					}
				}

				/*
				 * //更新主键 PK
				 * if(("1".equals(newDictTFactor.getIskey())||"1".equals
				 * (oldDictTFactor
				 * .getIskey()))&&!oldDictTFactor.getDbcolumnname(
				 * ).equals(newDictTFactor.getDbcolumnname())){
				 * 
				 * if(listIndex.size()>0){ StringBuilder ifExistIndexSql = new
				 * StringBuilder();
				 * ifExistIndexSql.append(DictDBConstants.IF_EXISTS_TYPE_INDEX);
				 * ifExistIndexSql.append("'"+prefix+tableName+"' ");
				 * ifExistIndexSql.append(" AND U.INDEX_NAME = ");
				 * ifExistIndexSql.append("'IN_"+prefix+tableName+"'"); Integer
				 * r_index =
				 * dictDBExecuteService.getIfExistsInDB(ifExistIndexSql
				 * .toString());// //删除原有index if(r_index==1){ //删除原来的主键 try {
				 * this
				 * .dictDBExecuteService.ExecDllLongForParam("DROP INDEX IN_"
				 * +prefix+tableName); } catch (Exception e) { // Auto-generated
				 * catch block e.printStackTrace(); throw new
				 * Exception(e.getMessage()); } }
				 * 
				 * //设定新 索引 try { String indexSql =
				 * dictDBExecuteService.createIndex(tableName, listIndex);
				 * this.dictDBExecuteService.ExecDllLongForParam(indexSql); }
				 * catch (Exception e) { // e.printStackTrace(); throw new
				 * Exception(e.getMessage()); } } }
				 */
			} else {
				throw new Exception(
						"不允许出现'YEAR','PROVINCE','STATUS','DBVERSION','BAKVERSION','BAKUSERID','BAKTYPE' 等字段.");
			}
		}
	}

	/**
	 * 删除列 同步 备份表
	 */
	public void deleteDictTFactorForBak(DictTFactorPO dictTFactor,
			DictTModelPO dictTModel) throws Exception {
		String tableName = dictTModel.getDbtablename().toUpperCase() + "_BAK";
		String ispartition = dictTModel.getIspartition();
		String status = this.dictDBExecuteService.getGlobalIsmultdb();
		String prefix = DictDBConstants.PREFIX_TABLE;
		if (status.equals("0")) {
			prefix = "";
		}
		Boolean isTask = false, isAllDistrict = false, isAllYear = false;
		if ("1".equals(dictTModel.getIstask())) {
			isTask = true;
		}
		if ("1".equals(dictTModel.getIsalldistrict())) {
			isAllDistrict = true;
		}
		if ("1".equals(dictTModel.getIsallyear())) {
			isAllYear = true;
		}
		// alter table prefix+AABB drop column UUU;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableid", dictTModel.getTableid());
		map.put("isleaf", "1");
		/*
		 * List<String> listIndex = new ArrayList<String>();
		 * if("1".equals(status)){ listIndex.add("YEAR"); // 索引 }else
		 * if("2".equals(status)){ listIndex.add("PROVINCE"); // 索引
		 * listIndex.add("YEAR"); // 索引 } listIndex.add("BAKVERSION");//索引
		 */List<DictTFactorPO> factorList = this.dictTFactorMapper
				.findDictTFactor(map);
		// StringBuilder viewColum = new StringBuilder();
		Map<String, List<DictTFactorPO>> viewColumMap = new HashMap<String, List<DictTFactorPO>>();
		// 原有主键
		for (DictTFactorPO dtf : factorList) {
			if (!dtf.getColumnid().equals(dictTFactor.getColumnid())
					&& dtf.getDatatype() != 4) {// 去除 dictTFactor 列
				// 视图 字段
				List<DictTFactorPO> listFactor = viewColumMap.get(prefix
						+ tableName);
				if (listFactor == null) {
					listFactor = new ArrayList<DictTFactorPO>();
					listFactor.add(dtf);
					viewColumMap.put(prefix + tableName, listFactor);
				} else {
					listFactor.add(dtf);
					viewColumMap.put(prefix + tableName, listFactor);
				}
				/*
				 * if("1".equals(dtf.getIskey())){
				 * listIndex.add(dtf.getDbcolumnname());//主键 字段 }
				 */
			}
		}

		List<DictTFactorPO> listFactor = viewColumMap.get(prefix + tableName);
		if (listFactor == null) {
			listFactor = new ArrayList<DictTFactorPO>();
			DictTFactorPO dv = new DictTFactorPO();
			dv.setDbcolumnname("BAKVERSION");
			DictTFactorPO du = new DictTFactorPO();
			du.setDbcolumnname("BAKUSERID");
			DictTFactorPO dt = new DictTFactorPO();
			dt.setDbcolumnname("BAKTYPE");
			listFactor.add(dv);
			listFactor.add(du);
			listFactor.add(dt);
			viewColumMap.put(prefix + tableName, listFactor);
		} else {
			DictTFactorPO dv = new DictTFactorPO();
			dv.setDbcolumnname("BAKVERSION");
			DictTFactorPO du = new DictTFactorPO();
			du.setDbcolumnname("BAKUSERID");
			DictTFactorPO dt = new DictTFactorPO();
			dt.setDbcolumnname("BAKTYPE");
			listFactor.add(dv);
			listFactor.add(du);
			listFactor.add(dt);
			viewColumMap.put(prefix + tableName, listFactor);
		}
		String sbsql = null;
		String ifhave = DictDBConstants.IF_EXISTS_TYPE_COLUMN + "'" + prefix
				+ tableName + "' AND  COLUMN_NAME ='"
				+ dictTFactor.getDbcolumnname().toUpperCase() + "'";
		Integer r_have = dictDBExecuteService.getIfExistsInDB(ifhave);
		if (!"1".equals(dictTFactor.getIsbandcol()) && r_have > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(DictDBConstants.ALTER_TABLE + " ");
			sb.append(prefix + tableName + " ");
			sb.append(DictDBConstants.DROP + " ");
			sb.append(DictDBConstants.COLUMN + " ");
			sb.append(dictTFactor.getDbcolumnname().toUpperCase());
			sbsql = sb.toString();
		}
		// alter table prefix+AABB drop column UUU;
		if (!("1".equals(ispartition))) {
			String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
					+ "'" + prefix + tableName + "'";
			Integer r_table = dictDBExecuteService
					.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
			if (r_table == 1) {
				try {
					if (sbsql != null) {
						// 更新 物理表
						this.dictDBExecuteService.ExecDllLongForParam(sbsql);
					}
					// 更新视图
					// String updateViewSql =
					// dictDBExecuteService.createView(ispartition,status,
					// tableName, viewColumMap,dictTModel.getSecusql());
					// this.dictDBExecuteService.ExecDllLongForParam(updateViewSql);
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception(e.getMessage());
				}
			} else {
				throw new Exception("未找到表：" + "'" + prefix + tableName + "' ");
			}
		} else {
			String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
					+ "'" + prefix + tableName + "'";
			Integer r_table = dictDBExecuteService
					.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
			if (r_table == 1) {
				try {
					if (sbsql != null) {
						// 更新 物理表
						this.dictDBExecuteService.ExecDllLongForParam(sbsql);
					}
					// 更新视图
					String updateViewSql = dictDBExecuteService.createView(
							ispartition, status, tableName, viewColumMap,
							dictTModel.getSecusql(), isTask, false,
							isAllDistrict, isAllYear);
					this.dictDBExecuteService
							.ExecDllLongForParam(updateViewSql);
					//最后统一调用刷新表视图，避免大集中多分区造成的不一致
					dictDBExecuteService.recreateViews(dictTModel.getTableid());
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception(e.getMessage());
				}
			} else {
				throw new Exception("未找到分区表：" + "'" + prefix + tableName + "' ");
			}
		}
		// 更新主键 PK
		/*
		 * if("1".equals(dictTFactor.getIskey())){
		 * 
		 * if(listIndex.size()>0){ StringBuilder ifExistIndexSql = new
		 * StringBuilder();
		 * ifExistIndexSql.append(DictDBConstants.IF_EXISTS_TYPE_INDEX);
		 * ifExistIndexSql.append("'"+prefix+tableName+"' ");
		 * ifExistIndexSql.append(" AND U.INDEX_NAME = ");
		 * ifExistIndexSql.append("'IN_"+prefix+tableName+"'"); Integer r_index
		 * = dictDBExecuteService.getIfExistsInDB(ifExistIndexSql.toString());//
		 * //删除原有index if(r_index==1){ try {
		 * this.dictDBExecuteService.ExecDllLongForParam
		 * ("DROP INDEX IN_"+prefix+tableName); } catch (Exception e) { //
		 * Auto-generated catch block e.printStackTrace(); throw new
		 * Exception(e.getMessage()); } } //设定新 索引 //create unique index
		 * INDEX_AACSWLB1 on prefix+AACSWLB1 (ORDERID, PROVINCE) try { String
		 * indexSql = dictDBExecuteService.createIndex(tableName, listIndex);
		 * this.dictDBExecuteService.ExecDllLongForParam(indexSql); } catch
		 * (Exception e) { // e.printStackTrace(); throw new
		 * Exception(e.getMessage()); } } }
		 */

	}

	@Override
	public List<DictTFactorPO> getSettingFinddata(List<Map<String, String>> list) {
		Map<String, List<DictTFactorPO>> talMap = new HashMap<String, List<DictTFactorPO>>();
		for (Map<String, String> map : list) {
			String tableid = map.get("tableid");
			DictTFactorPO dtf = new DictTFactorPO();
			dtf.setColumnid(map.get("columnid"));
			dtf.setName(map.get("name"));
			dtf.setDbcolumnname(map.get("dbcolumnname"));
			dtf.setChangename(map.get("changename"));
			dtf.setTableid(tableid);
			List<DictTFactorPO> dtfs = talMap.get(tableid);
			if (dtfs == null) {
				dtfs = new ArrayList<DictTFactorPO>();
				dtfs.add(dtf);
				talMap.put(tableid, dtfs);
			} else {
				dtfs.add(dtf);
				talMap.put(tableid, dtfs);
			}
		}

		List<DictTFactorPO> columnidList = new ArrayList<DictTFactorPO>();
		Map<String, String> mapFactor = new HashMap<String, String>();
		List<String> tables = new ArrayList<String>();
		for (Entry<String, List<DictTFactorPO>> m : talMap.entrySet()) {
			String table_id = m.getKey();
			List<DictTFactorPO> dtfs = m.getValue();
			DictTModelPO dtm = this.dictTModelMapper.getDictTModel(table_id);
			if (dtm != null) {
				tables.add(table_id);
				DictTFactorPO d = new DictTFactorPO();
				d.setColumnid(dtm.getTableid());
				d.setTableid("");
				d.setDbcolumnname(dtm.getDbtablename());
				d.setIsleaf("0");
				d.setName(dtm.getName());
				d.setChangename("");
				columnidList.add(d);
			}
			for (DictTFactorPO dtf : dtfs) {
				mapFactor.put(dtf.getColumnid(), dtf.getColumnid());
				columnidList.add(dtf);
			}
		}
		// RefColumn column = new RefColumn("视图列","columnid");
		// column.setColumnId("columnid");
		// column.setEditable(true);
		// column.setIdField("columnid");
		// column.setNameField("dbcolumnname");
		// column.setCodeField("dbcolumnname");
		// column.setSuperIdField("tableid");
		// column.setRefShowFormat("#dbcolumnname");
		// column.setDataList(columnidList);
		// column.setLeaf(true);
		// return column;
		return columnidList;
	}

	@Override
	public String copyColumnDefinitionToFactor(String finyear,
			List<Map<String, String>> factorData) {
		StringBuffer message = new StringBuffer("");
		String tableId = "";
		String factorId = "";
		try {

			tableId = factorData.get(0).get("tableid");
			factorId = factorData.get(0).get("columnid");
			if (!this.checkTableIsCopy(tableId, finyear)) {
				message.append(factorData.get(0).get("name")
						+ " : 所属的业务表没有进行复制，请先进行业务表复制操作！");
				return message.toString();
			}

			if (!this.checkFactorIsCopy(factorId, finyear)) {
				copyColumnDefinitionToYear(factorId, finyear);
			}
			message.append(factorData.get(0).get("name") + " : 同步成功").append(
					"\n ");
		} catch (Exception e) {
			message = new StringBuffer("");
			message.append("同步失败 ：" + e.getMessage());
		}
		return message.toString();
	}

	private boolean checkTableIsCopy(String tableId, String finyear) {
		Map param = new HashMap();
		param.put("tableId", tableId);
		param.put("finyear", finyear);
		List<Map<String, String>> ModelPOs = dictTModelSelfMapper
				.checkTableIsCopy(param);
		if (ModelPOs == null || ModelPOs.size() == 0) {
			return false;
		}
		return true;
	}

	private boolean checkFactorIsCopy(String factorId, String finyear) {
		Map param = new HashMap();
		param.put("factorId", factorId);
		param.put("finyear", finyear);
		List<Map<String, String>> factorPOs = dictTFactorSelfMapper
				.checkFactorIsCopy(param);
		if (factorPOs == null || factorPOs.size() == 0) {
			return false;
		}
		param.put("finyear", SecureUtil.getUserSelectYear());
		Map<String, String> factor = dictTFactorSelfMapper.checkFactorIsCopy(
				param).get(0);
		factor.put("FINYEAR", finyear);
		dictTFactorSelfMapper.updateFactorDefinitionForYear(factor);
		return true;
	}

	private void copyColumnDefinitionToYear(String factorId, String finyear) {
		Map param = new HashMap();
		param.put("factorId", factorId);
		param.put("finyear", finyear);
		dictTFactorSelfMapper.copyOneFactorDefineToYear(param);
	}

	/**
	 * 更新 DictTFactorPO 缓存
	 * 
	 * @param dtf
	 */
	public void updateDictTFactorCache(DictTFactorPO dictTFactor) {
		//
		// Set<String> set = iCacheService.getKeys();
		// if (set != null) {

		String[] keyStr = {
		// Constants.CACHE_KEY_FACTOR_GETDICTTFACTORBYDBCOLUMNNAME+"_"+dictTFactor.getTableid(),
		// Constants.CACHE_KEY_FACTOR_GETDICTTFACTORBYCOLUMNID+"_"+dictTFactor.getColumnid(),
		// Constants.CACHE_KEY_FACTOR_GETDICTTFACTORSBYTABLEID + "_"+
		// dictTFactor.getTableid(),
		// Constants.CACHE_KEY_FACTOR_GETDICTTFACTORSBYTABLEIDFORTREE+ "_" +
		// dictTFactor.getTableid(),
		// Constants.CACHE_KEY_FACTOR_GETDICTTFACTORLISTBYCOLUMNID+ "_" +
		// dictTFactor.getTableid(),
		// Constants.CACHE_KEY_FACTOR_GETDICTTFACTORBYNAME + "_"+
		// dictTFactor.getTableid(),
		// Constants.CACHE_KEY_FACTOR_GETDICTTFACTORBYTABLEIDANDTYPE+ "_" +
		// dictTFactor.getTableid(),
		// Constants.CACHE_KEY_FACTOR_GETDICTTFACTORBYTABLEIDANDISVISIBLE+
		// "_" + dictTFactor.getTableid()
		};
		// Set<String> keys = DictDBConstants.findCacheKeyOfIndex(set, keyStr);
		// for (String key : keys) {
		// iCacheService.remove(key);
		// }
		// }

	}

	/**
	 * 平台同步增加注册列
	 * 
	 * @param dtm
	 * @throws Exception
	 */
	private void registerDicColumn(String tableDBName,
			DictTFactorPO dictTFactorPO) throws Exception {
		// TODO:平台接口暂时屏蔽
		if (true) {
			return;
		}
		// 如果数据元ID为空，不同步
		if (dictTFactorPO.getDeid() == null
				|| dictTFactorPO.getDeid().equals("")) {
			return;
		}
		IDicTableService dicTableService = (IDicTableService) ServiceFactory
				.getBean("fasp2.dic.dictableService");
		// 同步到平台
		// 平台列对象
		DicColumnDTO dicColumn = new DicColumnDTO();
		dicColumn.setTablecode(tableDBName);
		dicColumn.setColumnid(dictTFactorPO.getColumnid());
		dicColumn.setColumncode(dictTFactorPO.getDbcolumnname());
		dicColumn.setDbcolumncode(dictTFactorPO.getDbcolumnname());
		dicColumn.setName(dictTFactorPO.getName());
		// 引用ID
		// 需要到代码表中找到对应的平台CSID
		if (dictTFactorPO.getCsid() != null
				&& !"".equals(dictTFactorPO.getCsid())) {
			DictTModelcodePO dtmCode = dictTModelcodeService
					.getDictTModelcodePOByID(dictTFactorPO.getCsid());
			if (dtmCode != null && dtmCode.getFaspcsid() != null
					&& !"".equals(dtmCode.getFaspcsid())) {
				dicColumn.setCsid(dtmCode.getFaspcsid());
			} else {
				dicColumn.setCsid("");
			}
		} else {
			dicColumn.setCsid("");
		}
		// 数据元ID
		dicColumn.setDeid(dictTFactorPO.getDeid());
		dicTableService.saveDicCol(tableDBName, dicColumn);
	}

	public void updateRegisterDicColumn(DictTModelPO dictTModel,
			DictTFactorPO dictTFactorPO) throws Exception {
		// TODO:平台接口暂时屏蔽
		if (true) {
			return;
		}
		IDicTableService dicTableService = (IDicTableService) ServiceFactory
				.getBean("fasp2.dic.dictableService");
		IDicTableQueryService dicTableQueryService = (IDicTableQueryService) ServiceFactory
				.getBean("fasp2.dictable.queryService");
		String tableDBName = dictTModel.getDbtablename().toUpperCase();
		String columnDBName = dictTFactorPO.getDbcolumnname().toUpperCase();
		boolean isRegisteredTable = true;
		if (dicTableQueryService.getDicTableWithNoException(tableDBName) == null) {
			isRegisteredTable = false;
		}
		boolean isRegisteredColumn = true;
		DicColumnDTO dicColumn = dicTableQueryService.getDicColumnDTO(
				tableDBName, columnDBName);
		if (dicColumn == null) {
			isRegisteredColumn = false;
			dicColumn = new DicColumnDTO();
		}
		// 如果数据元ID为空，不同步，如果平台存在列，删除
		if (dictTFactorPO.getDeid() == null
				|| dictTFactorPO.getDeid().equals("")) {
			// 如果存在平台列，删掉
			if (isRegisteredColumn) {
				List<String> columnCodeList = new ArrayList<String>();
				columnCodeList.add(dictTFactorPO.getDbcolumnname()
						.toUpperCase());
				dicTableService.deleteDicCol(tableDBName, columnCodeList);
			}
			return;
		}

		// 同步到平台
		// 平台列对象
		if (!isRegisteredColumn) {
			dicColumn.setTablecode(tableDBName);
			dicColumn.setColumnid(dictTFactorPO.getColumnid());
			dicColumn.setColumncode(dictTFactorPO.getDbcolumnname());
			dicColumn.setDbcolumncode(dictTFactorPO.getDbcolumnname());
			dicColumn.setName(dictTFactorPO.getName());
			// 引用ID
			// 需要到代码表中找到对应的平台CSID
			if (dictTFactorPO.getCsid() != null
					&& !"".equals(dictTFactorPO.getCsid())) {
				DictTModelcodePO dtmCode = dictTModelcodeService
						.getDictTModelcodePOByID(dictTFactorPO.getCsid());
				if (dtmCode != null && dtmCode.getFaspcsid() != null
						&& !"".equals(dtmCode.getFaspcsid())) {
					dicColumn.setCsid(dtmCode.getFaspcsid());
				} else {
					dicColumn.setCsid("");
				}
			} else {
				dicColumn.setCsid("");
			}
			// 数据元ID
			dicColumn.setDeid(dictTFactorPO.getDeid());
			// 默认值
			dicColumn.setDefaultvalue(dictTFactorPO.getDefaultvalue());
		}
		// // 如果不存在表信息，补登记表和列信息
		// if (!isRegisteredTable) {
		// List<DictTFactorPO> dtfList = new ArrayList<DictTFactorPO>();
		// dtfList.add(dictTFactorPO);
		// dictTModel.setDictTFactorList(dtfList);
		// dictTModelSelfService.registerDicTable(dictTModel);
		// return;
		// }
		// 如果不存在表信息，补登记表和列信息
		if (!isRegisteredTable) {
			List<DictTFactorPO> dtfList = this.dictTFactorService
					.getDictTFactorsByTableId(dictTModel.getTableid());
			for (DictTFactorPO dictTFactor : dtfList) {
				// 如果找到当前修改列，移除
				if (dictTFactorPO.getDbcolumnname().equals(
						dictTFactor.getDbcolumnname())) {
					dtfList.remove(dictTFactor);
					break;
				}
			}
			// 需要取出所有列信息
			dtfList.add(dictTFactorPO);
			dictTModel.setDictTFactorList(dtfList);
			dictTModelSelfService.registerDicTable(dictTModel);
			return;
		}
		// 只传可能变化的信息
		dicColumn.setName(dictTFactorPO.getName());
		// 引用ID
		// 需要到代码表中找到对应的平台CSID
		if (dictTFactorPO.getCsid() != null
				&& !"".equals(dictTFactorPO.getCsid())) {
			DictTModelcodePO dtmCode = dictTModelcodeService
					.getDictTModelcodePOByID(dictTFactorPO.getCsid());
			if (dtmCode != null && dtmCode.getFaspcsid() != null
					&& !"".equals(dtmCode.getFaspcsid())) {
				dicColumn.setCsid(dtmCode.getFaspcsid());
			} else {
				dicColumn.setCsid("");
			}
		} else {
			dicColumn.setCsid("");
		}
		// 数据元ID
		dicColumn.setDeid(dictTFactorPO.getDeid());
		// 默认值
		dicColumn.setDefaultvalue(dictTFactorPO.getDefaultvalue());
		if (isRegisteredColumn) {
			// 更新平台列信息
			dicTableService.updateDicCol(tableDBName, dicColumn);
		} else {
			dicTableService.saveDicCol(tableDBName, dicColumn);
		}

	}

	/**
	 * 平台同步删除注册列
	 * 
	 * @param tableDBName
	 * @param dictTFactorPO
	 * @throws Exception
	 */
	public void deleteRegisterDicColumn(String tableDBName,
			DictTFactorPO dictTFactorPO) throws Exception {
		// TODO:平台接口暂时屏蔽
		if (true) {
			return;
		}
		// 如果数据元ID为空，不同步
		if (dictTFactorPO.getDeid() == null
				|| dictTFactorPO.getDeid().equals("")) {
			return;
		}
		IDicTableService dicTableService = (IDicTableService) ServiceFactory
				.getBean("fasp2.dic.dictableService");
		IDicTableQueryService dicTableQueryService = (IDicTableQueryService) ServiceFactory
				.getBean("fasp2.dictable.queryService");
		boolean isRegisteredColumn = true;
		String columnDBName = dictTFactorPO.getDbcolumnname().toUpperCase();
		if (dicTableQueryService.getDicColumnDTO(tableDBName, columnDBName) == null) {
			isRegisteredColumn = false;
		}
		if (isRegisteredColumn) {
			List<String> columnCodeList = new ArrayList<String>();
			columnCodeList.add(dictTFactorPO.getDbcolumnname().toUpperCase());
			dicTableService.deleteDicCol(tableDBName, columnCodeList);
		}
	}

	/**
	 * 刷新factor的levelID
	 * 
	 * 
	 * @param tableID
	 */
	public void updateDictTFactorLevelNo(String tableID) {
		dictTFactorSelfMapper.updateDictTFactorLevelNo(tableID);
		// 删除所有业务表类缓存
		// 清除缓存
		// 清除所有业务表类缓存
		String[] allDICTKeys = { DictCacheKey.CACHE_KEY_DICT };
		dataCacheService.put(allDICTKeys, null);
	}

}

package commons.dict.service.impl;

import gov.mof.fasp2.dic.table.dto.DicColumnDTO;
import gov.mof.fasp2.dic.table.dto.DicTableDTO;
import gov.mof.fasp2.dic.table.service.IDicTableQueryService;
import gov.mof.fasp2.dic.table.service.IDicTableService;
import gov.mof.fasp2.dic.dataelement.dto.DataElementDTO;
import gov.mof.fasp2.dic.dataelement.dto.DataElementTypeDTO;
import gov.mof.fasp2.dic.dataelement.service.IDataElementService;
import gov.mof.fasp2.dic.dataelement.typeservice.IDataElementTypeService;
import gov.mof.fasp2.dic.enumclass.TableTypeEnum;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longtu.framework.util.ServiceFactory;
import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.dao.UtilsMapper;
import com.tjhq.commons.dict.constants.DictCacheKey;
import com.tjhq.commons.dict.dao.DictDBExecuteMapper;
import com.tjhq.commons.dict.dao.DictTDefaultcolMapper;
import com.tjhq.commons.dict.dao.DictTFactorSelfMapper;
import com.tjhq.commons.dict.dao.DictTModelSelfMapper;
import com.tjhq.commons.dict.dao.DictTUpdateviewMapper;
import com.tjhq.commons.dict.external.dao.DictTFactorMapper;
import com.tjhq.commons.dict.external.dao.DictTFactorcodeMapper;
import com.tjhq.commons.dict.external.dao.DictTModelMapper;
import com.tjhq.commons.dict.external.dao.DictTModelcodeMapper;
import com.tjhq.commons.dict.external.dao.DictTSuitMapper;
import com.tjhq.commons.dict.external.po.DictElementPO;
import com.tjhq.commons.dict.external.po.DictTDealtypePO;
import com.tjhq.commons.dict.external.po.DictTDefaultcolPO;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.po.DictTSuitPO;
import com.tjhq.commons.dict.external.po.DictTUpdateviewPO;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.dict.external.service.impl.DictTFactorService;
import com.tjhq.commons.dict.service.IDictDBExecuteService;
import com.tjhq.commons.dict.service.IDictTDealtypeService;
import com.tjhq.commons.dict.service.IDictTModelSelfService;
import com.tjhq.commons.dict.util.DictChangeDtoForSYNC;
import com.tjhq.commons.dict.util.DictDBConstants;

import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;

/**
 * 表接口实现
 * 
 * @author xujingsi
 * 
 */
@Service
public class DictTModelSelfService implements IDictTModelSelfService {

	@Resource
	private DictTModelSelfMapper dictTModelSelfMapper;

	@Resource
	private DictTFactorSelfMapper dictTFactorSelfMapper;
	@Resource
	private DictTModelcodeMapper dictTModelcodeMapper;
	@Resource
	private DictTFactorcodeMapper dictTFactorcodeMapper;

	@Resource
	private DictTFactorSelfService dictTFactorSelfService;

	@Resource
	private DictTFactorService dictTFactorService;

	@Resource
	private DictTSuitMapper dictTSuitMapper;

	@Resource
	private DictTModelMapper dictTModelMapper;

	@Resource
	private DictTFactorMapper dictTFactorMapper;

	@Resource
	private IDictDBExecuteService dictDBExecuteService;

	@Resource
	private DictTUpdateviewMapper dictTUpdateviewMapper;

	@Resource
	public UtilsMapper utilsMapper;
	@Resource
	private DictDBExecuteMapper dictDBExecuteMapper;
	@Resource
	private DictTDefaultcolMapper dictTDefaultcolMapper;
	@Resource
	private IDictTModelService dictTModelService;
	@Resource
	private IDictTModelcodeService dictTModelcodeService;

	@Resource
	private IDataCacheService dataCacheService;

	@Resource
	private IDictTDealtypeService dictTDealtypeService;

	/**
	 * 添加 1物理表
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insertDictTModelForPhysics(DictTModelPO dtm) throws Exception {
		if (dtm.getDealtype() != null && !"".equals(dtm.getDealtype())) {
			String tableid = dictDBExecuteService.getUUID();// id
			dtm.setTableid(tableid);
			dtm.setIsbak("0");
			try {
				// 取到缺省列
				Map<String, Object> parameterMap = new HashMap<String, Object>();
				parameterMap.put("dealid", dtm.getDealtype());

				List<DictTDefaultcolPO> defaultColList = dictTDefaultcolMapper
						.findDictTDefaultcol(parameterMap);
				if (defaultColList != null && defaultColList.size() > 0) {
					String tableName = dtm.getDbtablename().toUpperCase();
					// 1物理表
					String status = dictDBExecuteService.getGlobalIsmultdb();
					String ispartition = dtm.getIspartition();
					String prefix = DictDBConstants.PREFIX_TABLE;
					if (status.equals("0")) {
						prefix = "";
					}
					if (ispartition == null) {
						ispartition = "0";
					}
					Boolean isTask = false, isAllDistrict = false, isAllYear = false;
					if ("1".equals(dtm.getIstask())) {
						isTask = true;
					}
					;
					if ("1".equals(dtm.getIsalldistrict())) {
						isAllDistrict = true;
					}
					if ("1".equals(dtm.getIsallyear())) {
						isAllYear = true;
					}
					boolean isConfig = false;
					// 得到处理类型
					String dealTypeID = dtm.getDealtype();
					// 通过处理类型得到是否需要配置视图
					DictTDealtypePO dictTDealtype = dictTDealtypeService
							.getDictTDealtype(dealTypeID, dtm.getAppid());
					if (dictTDealtype != null) {
						if ("1".equals(dictTDealtype.getNeedconfig())) {
							isConfig = true;
						}
					}
					try {
						Map<String, String> sqlmap = this.dictDBExecuteService
								.createPhysicsTableSql(defaultColList,
										tableName, dtm.getName(), status,
										ispartition, dtm.getSecusql(), false,
										isTask, isConfig, isAllDistrict, isAllYear, null);

						if (sqlmap != null && sqlmap.size() > 0) {

							// 记录到DictTModel表
							try {
								dtm.setDbtablename(tableName);
								dtm.setIspartition(ispartition);
								// insert(dictTModel,dtdList);
								dtm.setIsadd(dtm.getIsadd() == null
										|| "".equals(dtm.getIsadd()) ? "0"
										: dtm.getIsadd());
								dtm.setIsman(dtm.getIsman() == null
										|| "".equals(dtm.getIsman()) ? "0"
										: dtm.getIsman());
								dtm.setIspartition(dtm.getIspartition() == null
										|| "".equals(dtm.getIspartition()) ? "0"
										: dtm.getIspartition());
								dtm.setIsreserved(dtm.getIsreserved() == null
										|| "".equals(dtm.getIsreserved()) ? "0"
										: dtm.getIsreserved());
								dtm.setIsshow(dtm.getIsshow() == null
										|| "".equals(dtm.getIsshow()) ? "1"
										: dtm.getIsshow());
								dtm.setIstask(dtm.getIstask() == null
										|| "".equals(dtm.getIstask()) ? "0"
										: dtm.getIstask());
								dtm.setIssumtab(dtm.getIssumtab() == null
										|| "".equals(dtm.getIssumtab()) ? "0"
										: dtm.getIssumtab());
								List<DictTFactorPO> dtfList = new ArrayList<DictTFactorPO>();
								// 记录到DictTFactor表
								int i = 1;
								for (DictTDefaultcolPO defaultCol : defaultColList) {
									if (!"YEAR".equals(defaultCol
											.getDbcolumnname().toUpperCase()
											.trim())
											&& !"PROVINCE".equals(defaultCol
													.getDbcolumnname()
													.toUpperCase().trim())) {
										DictTFactorPO dtf = new DictTFactorPO();
										dtf.setColumnid(this.dictDBExecuteService
												.getUUID());
										dtf.setTableid(dtm.getTableid());
										dtf.setDatatype(Integer
												.parseInt(DataType.STRING));
										// 如果是数字型
										if (1 == defaultCol.getDatatype()) {
											if (defaultCol.getScale() != null
													&& defaultCol.getScale() != 0) {
												dtf.setDatatype(Integer
														.parseInt(DataType.NUMBER));
											} else {
												dtf.setDatatype(Integer
														.parseInt(DataType.INT));
											}
										}
										dtf.setDatalength(defaultCol
												.getDatalength());
										dtf.setDbcolumnname(defaultCol
												.getDbcolumnname()
												.toUpperCase().trim());
										dtf.setDefaultvalue(defaultCol
												.getDefaultvalue() == null ? ""
												: defaultCol.getDefaultvalue()
														.trim());
										dtf.setName(defaultCol.getName().trim());
										dtf.setAlias(defaultCol.getName()
												.trim());
										// dtf.setColformat(dtd.getName().trim());
										dtf.setColtips(defaultCol.getName()
												.trim());
										if ("1".equals(defaultCol
												.getIslogickey())) {
											dtf.setIskey("0");
											dtf.setNullable("0");
										} else {
											dtf.setIskey("0");
											dtf.setNullable("1");
										}
										if (!("1".equals(dtf.getIshref()))) {
											dtf.setIshref("0");
											dtf.setHrefloc(null);
											dtf.setHrefparmid(null);
										}
										dtf.setIsbandcol(dtf.getIsbandcol() == null
												|| "".equals(dtf.getIsbandcol()) ? "0"
												: dtf.getIsbandcol());
										dtf.setIshref(dtf.getIshref() == null
												|| "".equals(dtf.getIshref()) ? "0"
												: dtf.getIshref());
										dtf.setIskey(dtf.getIskey() == null
												|| "".equals(dtf.getIskey()) ? "0"
												: dtf.getIskey());
										dtf.setIsleaf(dtf.getIsleaf() == null
												|| "".equals(dtf.getIsleaf()) ? "1"
												: dtf.getIsleaf());
										dtf.setIsregex(dtf.getIsregex() == null
												|| "".equals(dtf.getIsregex()) ? "0"
												: dtf.getIsregex());
										dtf.setIsreserve(dtf.getIsreserve() == null
												|| "".equals(dtf.getIsreserve()) ? "0"
												: dtf.getIsreserve());
										dtf.setIssum(dtf.getIssum() == null
												|| "".equals(dtf.getIssum()) ? "0"
												: dtf.getIssum());
										dtf.setIsupdate(dtf.getIsupdate() == null
												|| "".equals(dtf.getIsupdate()) ? "1"
												: dtf.getIsupdate());
										dtf.setIsvirtual(dtf.getIsvirtual() == null
												|| "".equals(dtf.getIsvirtual()) ? "0"
												: dtf.getIsvirtual());
										dtf.setIsvisible(dtf.getIsvisible() == null
												|| "".equals(dtf.getIsvisible()) ? "1"
												: dtf.getIsvisible());
										dtf.setNullable(dtf.getNullable() == null
												|| "".equals(dtf.getNullable()) ? "1"
												: dtf.getNullable());
										dtf.setParentNodeCanCheck(dtf
												.getParentNodeCanCheck() == null
												|| "".equals(dtf
														.getParentNodeCanCheck()) ? "0"
												: dtf.getParentNodeCanCheck());
										dtf.setSuperid(dtf.getSuperid() == null
												|| "".equals(dtf.getSuperid()) ? "0"
												: dtf.getSuperid());
										dtf.setScale(defaultCol.getScale());
										dtf.setIsreserve("1".equals(defaultCol
												.getIsreserve()) ? defaultCol
												.getIsreserve() : "0");
										// 数据元ID
										dtf.setDeid(defaultCol.getDeid());
										dtf.setIsleaf("1");
										dtf.setOrderid(i);
										dtf.setLevelno(1);
										dtfList.add(dtf);
										// 创建表列不单独同步到平台，和表一起同步
										this.dictTFactorSelfService
												.insertDictTFactor(tableName,
														dtf, false);
										i++;
									}
								}
								dtm.setDictTFactorList(dtfList);
							} catch (Exception e) {
								e.printStackTrace();
								throw new Exception(e.getMessage());
							}
							String pKsql = sqlmap
									.get(DictDBConstants.TYPE_PRIMARY);
							String indexSql = sqlmap
									.get(DictDBConstants.TYPE_INDEX);
							String add_comments = sqlmap
									.get(DictDBConstants.TYPE_TABLE_COMMENTS);
							if ("0".equals(status) || !"1".equals(ispartition)) {
								// 单创建物理表
								String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
										+ "'" + prefix + tableName + "'";

								Integer isTableExist = this.dictDBExecuteService
										.getIfExistsInDB(ifExistsTableSql);// 同名物理表
								//视图是否存在
								String ifExistsViewSql_ = DictDBConstants.IF_EXISTS_TYPE_VIEW+ "'" + tableName + "'";
								Integer isViewExist = dictDBExecuteService.getIfExistsInDB(ifExistsViewSql_);// 同名视图
								if (isTableExist == 0 && isViewExist == 0) {
									this.insertDictTModel(dtm, true);
									// String ifExistsViewSql =
									// DictDBConstants.IF_EXISTS_TYPE_VIEW +
									// "'"+tableName+"'";
									// Integer r_view =
									// this.dictDBExecuteService.getIfExistsInDB(ifExistsViewSql);//同名视图
									// if(r_view==0){
									try {
										String createTableSql = sqlmap
												.get(DictDBConstants.TYPE_TABLE);
										this.dictDBExecuteService
												.ExecDllLongForParam(createTableSql);
										// +视图
										// String createViewSql =
										// sqlmap.get(DictDBConstants.TYPE_VIEW);
										// this.dictDBExecuteService.ExecDllLongForParam(createViewSql);

										// +触发器
										String ifExistsTriggerSql = DictDBConstants.IF_EXISTS_TYPE_TRIGGER
												+ "'TR_"
												+ prefix
												+ tableName
												+ "'";
										String createTriggerSql = sqlmap
												.get(DictDBConstants.TYPE_TRIGGER);
										Integer isTriggerExist = this.dictDBExecuteService
												.getIfExistsInDB(ifExistsTriggerSql);// 同名触发器
										if (isTriggerExist == 0) {
											this.dictDBExecuteService
													.ExecDllLongForParam(createTriggerSql);
										} else {
											throw new Exception("TR_" + prefix
													+ tableName + " :触发器已经存在.");
										}
										// +主键
										if (pKsql != null && !"".equals(pKsql)) {
											this.dictDBExecuteService
													.ExecDllLongForParam(pKsql);
										}

										// +索引
										if (indexSql != null
												&& !"".equals(indexSql)) {
											this.dictDBExecuteService
													.ExecDllLongForParam(indexSql);
										}

										// +add_comments
										if (add_comments != null
												&& !"".equals(add_comments)) {
											this.dictDBExecuteService
													.ExecDllLongForParam(add_comments);
										}
										/*
										 * //索引 String indexSql =
										 * sqlmap.get(DictDBConstants
										 * .TYPE_INDEX);
										 * if(indexSql!=null&&!"".equals
										 * (indexSql)){
										 * this.dictDBExecuteService
										 * .ExecDllLongForParam(indexSql); }
										 */
									} catch (Exception e) {
										e.printStackTrace();
										// roll back

										/*
										 * String ifExistsViewSql_ =
										 * DictDBConstants.IF_EXISTS_TYPE_VIEW +
										 * "'"+tableName+"'"; Integer r_view_ =
										 * dictDBExecuteService
										 * .getIfExistsInDB(ifExistsViewSql_
										 * );//同名视图 if(r_view_==1){
										 * dictDBExecuteService
										 * .ExecDllLongForParam("DROP VIEW "+
										 * tableName); } String
										 * ifExistsTriggerSql =
										 * DictDBConstants.IF_EXISTS_TYPE_TRIGGER
										 * + "'TR_"+prefix++tableName+"'";
										 * Integer r_trigger =
										 * dictDBExecuteService
										 * .getIfExistsInDB(ifExistsTriggerSql
										 * );//同名触发器 if(r_trigger==1){
										 * dictDBExecuteService
										 * .ExecDllLongForParam
										 * ("DROP TRIGGER "+"TR_"
										 * +prefix+tableName); } String
										 * ifExistsTable_Sql =
										 * DictDBConstants.IF_EXISTS_TYPE_TABLE
										 * + "'"+prefix+tableName+"'"; Integer
										 * havetable_ =
										 * dictDBExecuteService.getIfExistsInDB
										 * (ifExistsTable_Sql);//同名表
										 * if(havetable_==1){
										 * dictDBExecuteService
										 * .ExecDllLongForParam
										 * ("DROP TABLE "+prefix
										 * +tableName+" PURGE "); }
										 * delete(dictTModel);
										 */
										throw new Exception(e.getMessage());
									}
									// }else{
									// throw new
									// Exception(tableName+"物理表（视图）已经存在.");
									// }
								} else {
									throw new SQLException(tableName
											+ "物理表已经存在.", "CREATETABLE");
									// throw new
									// Exception(tableName+"物理表已经存在.");
								}
							} else if ("1".equals(ispartition)
									&& ("1".equals(status) || "2"
											.equals(status))) {
								// 创建物理表 +分区
								String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
										+ "'" + prefix + tableName + "'";
								String createTableSql = sqlmap
										.get(DictDBConstants.TYPE_PARTITION);
								Integer isTableExist = dictDBExecuteService
										.getIfExistsInDB(ifExistsTableSql);// 同名物理表
								//视图是否存在
								String ifExistsViewSql_ = DictDBConstants.IF_EXISTS_TYPE_VIEW+ "'" + tableName + "'";
								Integer isViewExist = dictDBExecuteService.getIfExistsInDB(ifExistsViewSql_);// 同名视图
								if (isTableExist == 0 && isViewExist == 0) {
									try {
										this.insertDictTModel(dtm, true);
										this.dictDBExecuteService
												.ExecDllLongForParam(createTableSql);

										// +视图
										String ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW
												+ "'" + tableName + "'";
										String createViewSql = sqlmap
												.get(DictDBConstants.TYPE_VIEW);
										isViewExist = this.dictDBExecuteService
												.getIfExistsInDB(ifExistsViewSql);// 同名视图
										if (isViewExist == 0) {
											this.dictDBExecuteService
													.ExecDllLongForParam(createViewSql);
										} else {
											throw new Exception("" + tableName
													+ " :视图已经存在.");
										}
										// 如果有配置视图，创建
										if (isConfig) {
											// +配置视图
											ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW
													+ "'"
													+ tableName
													+ DictDBConstants.SUFFIX_CONFIG_VIEW
													+ "'";
											createViewSql = sqlmap
													.get(DictDBConstants.TYPE_CFG_VIEW);
											isViewExist = this.dictDBExecuteService
													.getIfExistsInDB(ifExistsViewSql);// 同名视图
											if (isViewExist == 0) {
												this.dictDBExecuteService
														.ExecDllLongForParam(createViewSql);
											} else {
												throw new Exception(
														""
																+ tableName
																+ DictDBConstants.SUFFIX_CONFIG_VIEW
																+ " :视图已经存在.");
											}
										}
										// +触发器
										String ifExistsTriggerSql = DictDBConstants.IF_EXISTS_TYPE_TRIGGER
												+ "'TR_"
												+ prefix
												+ tableName
												+ "'";
										String createTriggerSql = sqlmap
												.get(DictDBConstants.TYPE_TRIGGER);
										Integer r_trigger = this.dictDBExecuteService
												.getIfExistsInDB(ifExistsTriggerSql);// 同名触发器
										if (r_trigger == 0) {
											this.dictDBExecuteService
													.ExecDllLongForParam(createTriggerSql);
										} else {
											throw new Exception("TR_" + prefix
													+ tableName + " :触发器已经存在.");
										}
										// +主键
										if (pKsql != null && !"".equals(pKsql)) {
											this.dictDBExecuteService
													.ExecDllLongForParam(pKsql);
										}

										// +索引
										if (indexSql != null
												&& !"".equals(indexSql)) {
											this.dictDBExecuteService
													.ExecDllLongForParam(indexSql);
										}

										// +add_comments
										if (add_comments != null
												&& !"".equals(add_comments)) {
											this.dictDBExecuteService
													.ExecDllLongForParam(add_comments);
										}
									} catch (Exception e) {

										// roll back

										/*
										 * String ifExistsViewSql_ =
										 * DictDBConstants.IF_EXISTS_TYPE_VIEW +
										 * "'"+tableName+"'"; Integer r_view_ =
										 * dictDBExecuteService
										 * .getIfExistsInDB(ifExistsViewSql_
										 * );//同名视图 if(r_view_==1){
										 * dictDBExecuteService
										 * .ExecDllLongForParam("DROP VIEW "+
										 * tableName); } String
										 * ifExistsTriggerSql =
										 * DictDBConstants.IF_EXISTS_TYPE_TRIGGER
										 * + "'TR_"+prefix+tableName+"'";
										 * Integer r_trigger =
										 * dictDBExecuteService
										 * .getIfExistsInDB(ifExistsTriggerSql
										 * );//同名触发器 if(r_trigger==1){
										 * dictDBExecuteService
										 * .ExecDllLongForParam
										 * ("DROP TRIGGER "+"TR_"
										 * +prefix+tableName); } String
										 * ifExistsTable_Sql =
										 * DictDBConstants.IF_EXISTS_TYPE_TABLE
										 * + "'"+prefix+tableName+"'"; Integer
										 * havetable_ =
										 * dictDBExecuteService.getIfExistsInDB
										 * (ifExistsTable_Sql);//同名表
										 * if(havetable_==1){
										 * dictDBExecuteService
										 * .ExecDllLongForParam
										 * ("DROP TABLE "+""+
										 * prefix+tableName+" PURGE "); }
										 * delete(dictTModel);
										 */

										e.printStackTrace();
										throw new Exception(e.getMessage());
									}
								} else {
									throw new SQLException(prefix + tableName
											+ " :物理表（分区）已经存在.", "CREATETABLE");
									// throw new
									// Exception(prefix+tableName+" :物理表（分区）已经存在.");
								}
							}
							// 记录到DictTModel表
							/*
							 * try { dictTModel.setDbtablename(tableName);
							 * dictTModel.setIspartition(ispartition);
							 * insert(dictTModel,dtdList); } catch (Exception e)
							 * { e.printStackTrace(); }
							 */

						}
					} catch (Exception e) {
						if (e instanceof SQLException
								&& ((SQLException) e).getSQLState().equals(
										"CREATETABLE")) {
							throw e;

						} else {

							String ifExistsViewSql_ = DictDBConstants.IF_EXISTS_TYPE_VIEW
									+ "'" + tableName + "'";
							Integer isViewExist = dictDBExecuteService
									.getIfExistsInDB(ifExistsViewSql_);// 同名视图
							if (isViewExist == 1) {
								dictDBExecuteService
										.ExecDllLongForParam("DROP VIEW "
												+ tableName);
							}
							// 如果有配置视图,同步删除
							if (isConfig) {
								ifExistsViewSql_ = DictDBConstants.IF_EXISTS_TYPE_VIEW
										+ "'"
										+ tableName
										+ DictDBConstants.SUFFIX_CONFIG_VIEW
										+ "'";
								isViewExist = dictDBExecuteService
										.getIfExistsInDB(ifExistsViewSql_);// 同名视图
								if (isViewExist == 1) {
									dictDBExecuteService
											.ExecDllLongForParam("DROP VIEW "
													+ tableName
													+ DictDBConstants.SUFFIX_CONFIG_VIEW);
								}
							}
							String ifExistsTriggerSql = DictDBConstants.IF_EXISTS_TYPE_TRIGGER
									+ "'TR_" + prefix + tableName + "'";
							Integer isTriggerExist = dictDBExecuteService
									.getIfExistsInDB(ifExistsTriggerSql);// 同名触发器
							if (isTriggerExist == 1) {
								dictDBExecuteService
										.ExecDllLongForParam("DROP TRIGGER "
												+ "TR_" + prefix + tableName);
							}
							String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
									+ "'" + prefix + tableName + "'";
							Integer isTableExist = dictDBExecuteService
									.getIfExistsInDB(ifExistsTableSql);// 同名表
							if (isTableExist == 1) {
								dictDBExecuteService
										.ExecDllLongForParam("DROP TABLE "
												+ prefix + tableName
												+ " PURGE ");
							}
							throw e;
						}
					}
				} else {
					throw new Exception("未找到缺省列信息");
				}
			} catch (Exception e) {
				// 删除平台登记信息
				deleteRegisterDicTable(dtm.getDbtablename());
				e.printStackTrace();
				throw new Exception(e.getMessage().replace("\"", "\'"));
			}
		}
		//增加列注释
		addColumnComment4Table(dtm);
	}

	/**
	 * 平台同步注册表
	 * 
	 * @param dtm
	 * @throws Exception
	 */
	public void registerDicTable(DictTModelPO dictTModel) throws Exception {
		 //TODO:平台接口暂时屏蔽
		 if (true){
		   return;
		 }
		IDicTableService dicTableService = (IDicTableService) ServiceFactory
				.getBean("fasp2.dic.dictableService");
		// 同步到平台
		// 平台表对象
		DicTableDTO dicTable = new DicTableDTO();
		// 登记到平台的业务类型小写
		dicTable.setAppid(dictTModel.getAppid().toLowerCase());
		dicTable.setTablecode(dictTModel.getDbtablename());
		dicTable.setTabletype(TableTypeEnum.TABLETYPE_DVIEW);
		dicTable.setDbtabname(dictTModel.getDbtablename());
		dicTable.setName(dictTModel.getName());
		dicTable.setViewtablename(dictTModel.getDbtablename());
		dicTable.setRemark(dictTModel.getName());
		List<DictTFactorPO> factorList = dictTModel.getDictTFactorList();
		int deidColumnCount = 0;
		for (DictTFactorPO dictTFactorPO : factorList) {
			// 如果数据元ID为空，不同步
			if (dictTFactorPO.getDeid() == null
					|| dictTFactorPO.getDeid().equals("")) {
				continue;
			}
			DicColumnDTO dicColumn = new DicColumnDTO();
			dicColumn.setTablecode(dicTable.getTablecode());
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
			dicTable.addCol(dicColumn);
			deidColumnCount++;
		}
		// 如果找不到任何数据元的列, 不注册表
		if (deidColumnCount == 0) {
			return;
		}
		dicTableService.saveDicTab(dicTable);
	}

	/**
	 * 删除平台注册表
	 * 
	 * @param tableDbName
	 * @throws Exception
	 */
	public void deleteRegisterDicTable(String tableDBName) throws Exception {
		// // TODO:平台接口暂时屏蔽
		// if (true) {
		// return;
		// }
		IDicTableService dicTableService = (IDicTableService) ServiceFactory
				.getBean("fasp2.dic.dictableService");
		IDicTableQueryService dicTableQueryService = (IDicTableQueryService) ServiceFactory
				.getBean("fasp2.dictable.queryService");
		boolean isRegisteredTable = true;
		if (dicTableQueryService.getDicTableWithNoException(tableDBName) == null) {
			isRegisteredTable = false;
		}
		if (isRegisteredTable) {
			dicTableService.deleteDicTab(tableDBName);
		}
	}

	/*
	 * @Transactional(rollbackFor=Exception.class) public void
	 * insert(DictTModelPO dictTModel,List<DictTDefaultcolPO> dtdList){
	 * dictTModel
	 * .setIsadd(dictTModel.getIsadd()==null||"".equals(dictTModel.getIsadd
	 * ())?"0":dictTModel.getIsadd());
	 * dictTModel.setIsman(dictTModel.getIsman()==
	 * null||"".equals(dictTModel.getIsman())?"0":dictTModel.getIsman());
	 * dictTModel
	 * .setIspartition(dictTModel.getIspartition()==null||"".equals(dictTModel
	 * .getIspartition())?"0":dictTModel.getIspartition());
	 * dictTModel.setIsreserved
	 * (dictTModel.getIsreserved()==null||"".equals(dictTModel
	 * .getIsreserved())?"0":dictTModel.getIsreserved());
	 * dictTModel.setIsshow(dictTModel
	 * .getIsshow()==null||"".equals(dictTModel.getIsshow
	 * ())?"1":dictTModel.getIsshow());
	 * dictTModel.setIssumtab(dictTModel.getIssumtab
	 * ()==null||"".equals(dictTModel
	 * .getIssumtab())?"0":dictTModel.getIssumtab()); List<DictTFactorPO>
	 * dtfList = new ArrayList<DictTFactorPO>(); //记录到DictTFactor表
	 * 
	 * if(dictTModel.getTableid()!=null&&!"".equals(dictTModel.getTableid())){
	 * int i = 1; for(DictTDefaultcolPO dtd : dtdList){
	 * if(!"YEAR".equals(dtd.getDbcolumnname
	 * ().toUpperCase().trim())&&!"PROVINCE"
	 * .equals(dtd.getDbcolumnname().toUpperCase().trim())){ DictTFactorPO dtf =
	 * new DictTFactorPO();
	 * dtf.setColumnid(this.dictDBExecuteService.getUUID());
	 * dtf.setTableid(dictTModel.getTableid());
	 * dtf.setDatatype(dtd.getDatatype());
	 * dtf.setDatalength(dtd.getDatalength());
	 * dtf.setDbcolumnname(dtd.getDbcolumnname().toUpperCase().trim());
	 * dtf.setDefaultvalue
	 * (dtd.getDefaultvalue()==null?"":dtd.getDefaultvalue().trim());
	 * dtf.setName(dtd.getName().trim()); dtf.setAlias(dtd.getName().trim());
	 * //dtf.setColformat(dtd.getName().trim());
	 * dtf.setColtips(dtd.getName().trim()); if("1".equals(dtd.getIsprimary())){
	 * dtf.setIskey("0"); dtf.setNullable("0"); }else{ dtf.setIskey("0");
	 * dtf.setNullable("1"); }
	 * dtf.setIsbandcol(dtf.getIsbandcol()==null||"".equals
	 * (dtf.getIsbandcol())?"0":dtf.getIsbandcol());
	 * dtf.setIshref(dtf.getIshref(
	 * )==null||"".equals(dtf.getIshref())?"0":dtf.getIshref());
	 * dtf.setIskey(dtf
	 * .getIskey()==null||"".equals(dtf.getIskey())?"0":dtf.getIskey());
	 * dtf.setIsleaf
	 * (dtf.getIsleaf()==null||"".equals(dtf.getIsleaf())?"1":dtf.getIsleaf());
	 * dtf
	 * .setIsregex(dtf.getIsregex()==null||"".equals(dtf.getIsregex())?"0":dtf
	 * .getIsregex());
	 * dtf.setIsreserve(dtf.getIsreserve()==null||"".equals(dtf.getIsreserve
	 * ())?"0":dtf.getIsreserve());
	 * dtf.setIssum(dtf.getIssum()==null||"".equals(
	 * dtf.getIssum())?"0":dtf.getIssum());
	 * dtf.setIsupdate(dtf.getIsupdate()==null
	 * ||"".equals(dtf.getIsupdate())?"1":dtf.getIsupdate());
	 * dtf.setIsvirtual(dtf
	 * .getIsvirtual()==null||"".equals(dtf.getIsvirtual())?"0"
	 * :dtf.getIsvirtual());
	 * dtf.setIsvisible(dtf.getIsvisible()==null||"".equals
	 * (dtf.getIsvisible())?"1":dtf.getIsvisible());
	 * dtf.setNullable(dtf.getNullable
	 * ()==null||"".equals(dtf.getNullable())?"1":dtf.getNullable());
	 * dtf.setScale(dtd.getScale());
	 * dtf.setIsreserve("1".equals(dtd.getIsreserve())?dtd.getIsreserve():"0");
	 * dtf.setIsleaf("1"); dtf.setOrderid(i); dtf.setLevelno(1);
	 * dtfList.add(dtf);
	 * this.dictTFactorSelfService.insertDictTFactor(dtf,false); i++; } }
	 * dictTModel.setDictTFactorList(dtfList);
	 * this.insertDictTModel(dictTModel); } }
	 * 
	 * @Transactional(rollbackFor=Exception.class) public void
	 * delete(DictTModelPO dictTModel){ Map<String,Object> mm = new
	 * HashMap<String, Object>(); mm.put("tableid", dictTModel.getTableid());
	 * List<DictTFactorPO> dtdList_ =
	 * this.dictTFactorMapper.findDictTFactor(mm); for(DictTFactorPO dd
	 * :dtdList_){ this.dictTFactorSelfService.deleteDictTFactor(dd,false); } }
	 */

	/**
	 * 添加 2（不可更新）视图
	 * 
	 * @param dictTModel
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertDictTModelForNoUpdateView(DictTModelPO dictTModel)
			throws Exception {
		if (dictTModel.getDealtype() == null
				|| "".equals(dictTModel.getDealtype())) {
			throw new Exception("表处理类型不能为空");
		}
		String viewName = dictTModel.getDbtablename().toUpperCase();
		dictTModel.setDbtablename(viewName);

		String ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW + "'"
				+ viewName + "'";
		Integer isViewExist = dictDBExecuteService
				.getIfExistsInDB(ifExistsViewSql);// 同名视图
		if (isViewExist == 0) {
			throw new Exception("未找到 '" + viewName + "'视图.");
		}
		DictTModelPO registeredDictTModel = dictTModelMapper
				.getDictTModelByName(viewName);
		if (registeredDictTModel != null) {
			throw new Exception("已经登记过 '" + viewName + "'视图.不需要再次登记.");
		}
		// 取到缺省列
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("dealid", dictTModel.getDealtype());

		List<DictTDefaultcolPO> defaultColList = dictTDefaultcolMapper
				.findDictTDefaultcol(parameterMap);
		Map<String, DictTDefaultcolPO> defaultColMap = new HashMap<String, DictTDefaultcolPO>();
		// 记录下缺省列，判断视图是否包含全部缺省列，同时列数据类型应该一致
		for (DictTDefaultcolPO dictTDefaultcolPO : defaultColList) {
			defaultColMap.put(dictTDefaultcolPO.getDbcolumnname().trim()
					.toUpperCase(), dictTDefaultcolPO);
		}

		String tableid = dictDBExecuteService.getUUID();
		dictTModel.setTableid(tableid);
		List<Map<String, Object>> list = this.dictDBExecuteService
				.getColumnByViewName(viewName);
		List<DictTFactorPO> dtfList = new ArrayList<DictTFactorPO>();
		int i = 1;
		for (Map<String, Object> col : list) {
			String dbColumnName = col.get("COLUMN_NAME").toString()
					.toUpperCase();
			DictTFactorPO dtf = new DictTFactorPO();
			dtf.setColumnid(this.dictDBExecuteService.getUUID());
			dtf.setTableid(dictTModel.getTableid());
			// 处理类型转换
			// 数字型
			String dataType = (String) col.get("DATA_TYPE");
			String dataLength = col.get("DATA_LENGTH") == null ? "0" : col.get(
					"DATA_LENGTH").toString();
			String newDataType = DataType.STRING;
			if ("NUMBER".equals(dataType)) {
				// 如果是没有精度，是整型
				String dataScale = col.get("DATA_SCALE") == null ? "0" : col
						.get("DATA_SCALE").toString();
				if (dataScale == null || dataScale.equals("0")) {
					newDataType = DataType.INT;
					dtf.setScale(0);
				} else {
					newDataType = DataType.NUMBER;
					dtf.setScale(Integer.parseInt(dataScale));
				}
				dataLength = col.get("DATA_PRECISION") == null ? "0" : col.get(
						"DATA_PRECISION").toString();
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
			dtf.setIskey("0");
			String nullable = "0";
			if ("Y".equals(col.get("NULLABLE"))) {
				nullable = "1";
			}
			dtf.setNullable(nullable);
			dtf.setIsreserve("0");
			dtf.setIsleaf("1");
			dtf.setSuperid("0");
			dtf.setDefaultvalue("");
			dtf.setOrderid(i);
			dtf.setLevelno(1);
			dtf.setIsbandcol("0");
			dtf.setIshref("0");
			dtf.setIskey("0");
			dtf.setIsregex("0");
			dtf.setIssum("0");
			dtf.setIsupdate("0");
			dtf.setIsvirtual("0");
			dtf.setIsvisible("1");
			dtf.setParentNodeCanCheck("0");
			// 如果是缺省列，进行一些缺省值设置
			if (defaultColMap.containsKey(dtf.getDbcolumnname())) {
				DictTDefaultcolPO defaultCol = defaultColMap.get(dtf
						.getDbcolumnname());
				dtf.setDatatype(defaultCol.getDatatype());
				dtf.setDatalength(defaultCol.getDatalength());
				dtf.setDefaultvalue(defaultCol.getDefaultvalue() == null ? ""
						: defaultCol.getDefaultvalue().trim());
				dtf.setName(defaultCol.getName().trim());
				dtf.setAlias(defaultCol.getName().trim());
				// dtf.setColformat(dtd.getName().trim());
				dtf.setColtips(defaultCol.getName().trim());
				if ("1".equals(defaultCol.getIslogickey())) {
					dtf.setIskey("0");
					dtf.setNullable("0");
				} else {
					dtf.setIskey("0");
					dtf.setNullable("1");
				}
				dtf.setScale(defaultCol.getScale());
				dtf.setIsreserve("1".equals(defaultCol.getIsreserve()) ? defaultCol
						.getIsreserve() : "0");
				// 数据元ID
				dtf.setDeid(defaultCol.getDeid());
				// 使用完成后移除
				defaultColMap.remove(dtf.getDbcolumnname());
			}

			// 创建表列不单独同步到平台，和表一起同步
			this.dictTFactorSelfService.insertDictTFactor(viewName, dtf, false);
			dtfList.add(dtf);
			i++;
		}
		// 如果缺省列还存在，不能创建视图
		if (defaultColMap != null && defaultColMap.size() > 0) {
			StringBuffer defectColumnInfo = new StringBuffer(
					"视图中缺少该表处理类型下的以下缺省列：");
			for (Iterator<DictTDefaultcolPO> iterator = defaultColMap.values()
					.iterator(); iterator.hasNext();) {
				DictTDefaultcolPO defaultCol = iterator.next();
				defectColumnInfo.append(defaultCol.getName()).append("[")
						.append(defaultCol.getDbcolumnname()).append("]  ");

			}
			defectColumnInfo.append("不能登记,请补全");
			throw new Exception(defectColumnInfo.toString());
		}
		dictTModel.setDictTFactorList(dtfList);
		dictTModel.setIsadd("0");
		dictTModel.setIsman("0");
		dictTModel.setIspartition("0");
		dictTModel.setIsreserved("0");
		dictTModel.setIsshow("1");
		dictTModel.setIssumtab("0");
		this.insertDictTModel(dictTModel, true);

	}

	private void setUpdateViewSourceColumnInfo(DictTModelPO dictTModel,
			List<Map<String, String>> sourceColumnList,
			Map<String, List<DictTFactorPO>> sourceTableMap,
			List<DictTFactorPO> selectedSourceColumnList) throws Exception {
		// 取到缺省列
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("dealid", dictTModel.getDealtype());
		List<DictTDefaultcolPO> defaultColList = dictTDefaultcolMapper
				.findDictTDefaultcol(parameterMap);
		Map<String, DictTDefaultcolPO> defaultColMap = new HashMap<String, DictTDefaultcolPO>();
		// 记录下缺省列，判断业务对象是否包含全部缺省列，同时列数据类型应该一致
		for (DictTDefaultcolPO dictTDefaultcolPO : defaultColList) {
			defaultColMap.put(dictTDefaultcolPO.getDbcolumnname().trim()
					.toUpperCase(), dictTDefaultcolPO);
		}

		for (Map<String, String> sourceColumnMap : sourceColumnList) {
			String sourceTableID = (String) sourceColumnMap.get("tableid");
			// 新表,登记
			if (!sourceTableMap.containsKey(sourceTableID)) {
				// 取得表定义中所有列
				List<DictTFactorPO> sourceFactorList = dictTFactorService
						.getDictTFactorsByTableId(sourceTableID);
				sourceTableMap.put(sourceTableID, sourceFactorList);

			}
			List<DictTFactorPO> sourceFactorList = (List<DictTFactorPO>) sourceTableMap
					.get(sourceTableID);
			// 补全当前列的信息
			for (DictTFactorPO sourceDictTFactor : sourceFactorList) {
				// 选中的列
				if (sourceDictTFactor.getColumnid().equals(
						(String) sourceColumnMap.get("columnid"))) {
					sourceDictTFactor.setChangename((String) sourceColumnMap
							.get("changename"));
					sourceDictTFactor.setColumnoldid((String) sourceColumnMap
							.get("columnid"));
					selectedSourceColumnList.add(sourceDictTFactor);
					// 如果和缺省列一致,移除缺省列
					if (defaultColMap.containsKey(sourceDictTFactor
							.getDbcolumnname().toUpperCase())) {
						// 移除
						defaultColMap.remove(sourceDictTFactor
								.getDbcolumnname().toUpperCase());
					}
				}
			}
		}
		// 如果缺省列还存在，不能创建视图
		if (defaultColMap != null && defaultColMap.size() > 0) {
			StringBuffer defectColumnInfo = new StringBuffer(
					"业务对象中缺少该表处理类型下的以下缺省列：");
			for (Iterator<DictTDefaultcolPO> iterator = defaultColMap.values()
					.iterator(); iterator.hasNext();) {
				DictTDefaultcolPO defaultCol = iterator.next();
				defectColumnInfo.append(defaultCol.getName()).append("[")
						.append(defaultCol.getDbcolumnname()).append("]  ");

			}
			defectColumnInfo.append("不能登记,请补全");
			throw new Exception(defectColumnInfo.toString());
		}

		// 过滤掉非选择的列
		for (Iterator<Entry<String, List<DictTFactorPO>>> iterator = sourceTableMap
				.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, List<DictTFactorPO>> entry = iterator.next();
			List<DictTFactorPO> sourceFactorList = entry.getValue();
			for (Iterator<DictTFactorPO> sourceIterator = sourceFactorList
					.iterator(); sourceIterator.hasNext();) {
				DictTFactorPO dictTFactorPO = sourceIterator.next();
				// 来源列ID为空的，为非选择的列，过滤掉
				if (dictTFactorPO.getColumnoldid() == null
						|| "".equals(dictTFactorPO.getColumnoldid())) {
					sourceIterator.remove();
				}
			}
		}
	}

	/**
	 * 添加 3 业务对象（可更新）视图
	 * 
	 * @param dictTModel
	 * @param sourceColumnList
	 * @param settingList
	 * @throws Exception
	 */
	public void insertDictTModelForUpdateView(DictTModelPO dictTModel,
			List<Map<String, String>> sourceColumnList,
			List<Map<String, String>> settingList) throws Exception {
		if (dictTModel.getDealtype() == null
				|| "".equals(dictTModel.getDealtype())) {
			throw new Exception("表处理类型不能为空");
		}
		String tableID = dictDBExecuteService.getUUID();// id
		dictTModel.setTableid(tableID);
		String tableName = dictTModel.getDbtablename().toUpperCase();
		if (tableName == null || "".equals(tableName.trim())) {
			throw new Exception("请填写物理名称");
		} else if (tableName.length() > 20) {
			throw new Exception("物理名称长度应小于20");
		}
		dictTModel.setDbtablename(tableName);
		String viewName = dictTModel.getDbtablename().toUpperCase();
		String ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW + "'"
				+ viewName + "'";
		Integer isViewExist = dictDBExecuteService
				.getIfExistsInDB(ifExistsViewSql);// 同名视图
		if (isViewExist == 1) {
			throw new Exception("已经登记过 '" + viewName + "'视图.不能再次登记.");
		}
		// 遍历源数据对象,找到来源表
		Map<String, List<DictTFactorPO>> sourceTableMap = new HashMap<String, List<DictTFactorPO>>();
		// 来源列
		List<DictTFactorPO> selectedSourceColumnList = new ArrayList<DictTFactorPO>();
		setUpdateViewSourceColumnInfo(dictTModel, sourceColumnList,
				sourceTableMap, selectedSourceColumnList);

		Map<String, String> settingMap = new HashMap<String, String>();
		String status = this.dictDBExecuteService.getGlobalIsmultdb();
		String prefix = DictDBConstants.PREFIX_TABLE;
		if (status.equals("0")) {
			prefix = "";
		}
		// 关系字段
		for (Map<String, String> m : settingList) {
			settingMap.put(m.get("columnid"), m.get("tocolumnid"));
		}

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
			this.dictDBExecuteService.ExecDllLongForParam(viewSql);
			// TODO:如果是单表的业务对象，先不创建视图触发器,多表才创建
			if (!sourceTableMap.isEmpty() && sourceTableMap.size() > 1) {
				this.dictDBExecuteService.ExecDllLongForParam(triggerSql);
			}
		} catch (Exception e) { // roll back

			e.printStackTrace();
			ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW + "'"
					+ viewName + "'";
			isViewExist = dictDBExecuteService.getIfExistsInDB(ifExistsViewSql);// 同名视图
			if (isViewExist == 1) {
				this.dictDBExecuteService.ExecDllLongForParam("DROP VIEW "
						+ viewName);
			}
			String ifExistsTriggerSql = DictDBConstants.IF_EXISTS_TYPE_TRIGGER
					+ "'TR_" + prefix + viewName + "'";
			Integer isTriggerExist = dictDBExecuteService
					.getIfExistsInDB(ifExistsTriggerSql);// 同名触发器
			if (isTriggerExist == 1) {
				this.dictDBExecuteService.ExecDllLongForParam("DROP TRIGGER "
						+ "TR_" + prefix + viewName);
			}
			throw new Exception(e.getMessage());
		}

		try {
			List<DictTFactorPO> dtfList = new ArrayList<DictTFactorPO>();

			// // 新旧列ID对照Map
			// Map<String, String> old2newColumnIDMap = new HashMap<String,
			// String>();

			// 补全修改列信息
			for (DictTFactorPO dictTFactorPO : selectedSourceColumnList) {
				// 如果有别名,列名用别名
				if (dictTFactorPO.getChangename() != null
						&& !"".equals(dictTFactorPO.getChangename().trim())) {
					dictTFactorPO.setDbcolumnname(dictTFactorPO.getChangename()
							.toUpperCase());
				}

				dictTFactorPO.setFrmtabid(dictTFactorPO.getTableid());
				dictTFactorPO.setFrmcolid(dictTFactorPO.getColumnid());

				dictTFactorPO.setTableid(tableID);
				// 列ID换为新唯一编码
				String columnid = dictDBExecuteService.getUUID();
				dictTFactorPO.setColumnid(columnid);

				dictTFactorPO.setSuperid("0");
				dictTFactorPO.setIsleaf("1");
				dictTFactorPO.setLevelno(1);
				// 超链清空
				dictTFactorPO.setIshref("0");
				// 主键清空
				dictTFactorPO.setIskey("0");
				// 是否保留清空
				dictTFactorPO.setIsreserve("0");
				// 如果是绑定列，清空是否绑定属性，同时设置为只读列
				if ("1".equals(dictTFactorPO.getIsbandcol())) {
					dictTFactorPO.setIsbandcol("0");
					dictTFactorPO.setBandcolumnid("");
				}
				dtfList.add(dictTFactorPO);
				// 创建表列不单独同步到平台，和表一起同步
				this.dictTFactorSelfService.insertDictTFactor(viewName,
						dictTFactorPO, false);

				// // 如果是绑定列，需要业务对象同时登记被绑定列，否则会出错
				// // 登记新旧列ID对照Map
				// old2newColumnIDMap.put(dictTFactorPO.getFrmcolid(),
				// dictTFactorPO.getColumnid());

			}

			// // 再次循环，补全绑定列，同时业务对象插入factor表
			// for (DictTFactorPO dictTFactorPO : selectedSourceColumnList) {
			// // 如果是绑定列，需要业务对象同时登记被绑定列，否则会出错
			// if ("1".equals(dictTFactorPO.getIsbandcol())) {
			// // 如果没有找到，说明该列登记出错
			// if (!old2newColumnIDMap.containsKey(dictTFactorPO
			// .getBandcolumnid())) {
			// StringBuffer erroColumnInfo = new StringBuffer(
			// "业务对象中绑定列：").append(dictTFactorPO.getName())
			// .append("[")
			// .append(dictTFactorPO.getDbcolumnname())
			// .append("]  没有找到对应列,请补全");
			// throw new Exception(erroColumnInfo.toString());
			// }
			// // 找到旧绑定列ID对应的新列ID
			// String newBandcolumnid = (String) old2newColumnIDMap
			// .get(dictTFactorPO.getBandcolumnid());
			// dictTFactorPO.setBandcolumnid(newBandcolumnid);
			//
			// }
			// dtfList.add(dictTFactorPO);
			// // 创建表列不单独同步到平台，和表一起同步
			// this.dictTFactorSelfService.insertDictTFactor(viewName,
			// dictTFactorPO, false);
			// }

			for (Map<String, String> m : settingList) {
				DictTUpdateviewPO dtu = new DictTUpdateviewPO();
				dtu.setColumnid(m.get("columnid"));
				dtu.setBgtlvl(dictTModel.getBgtlvl());
				dtu.setTocolumnid(m.get("tocolumnid"));
				dtu.setTableid(dictTModel.getTableid());
				dictTUpdateviewMapper.insertDictTUpdateview(dtu);
			}
			dictTModel.setIsadd(dictTModel.getIsadd() == null
					|| "".equals(dictTModel.getIsadd()) ? "0" : dictTModel
					.getIsadd());
			dictTModel.setIsman(dictTModel.getIsman() == null
					|| "".equals(dictTModel.getIsman()) ? "0" : dictTModel
					.getIsman());
			dictTModel.setIspartition(dictTModel.getIspartition() == null
					|| "".equals(dictTModel.getIspartition()) ? "0"
					: dictTModel.getIspartition());
			dictTModel.setIsreserved(dictTModel.getIsreserved() == null
					|| "".equals(dictTModel.getIsreserved()) ? "0" : dictTModel
					.getIsreserved());
			dictTModel.setIsshow(dictTModel.getIsshow() == null
					|| "".equals(dictTModel.getIsshow()) ? "1" : dictTModel
					.getIsshow());
			dictTModel.setIssumtab(dictTModel.getIssumtab() == null
					|| "".equals(dictTModel.getIssumtab()) ? "0" : dictTModel
					.getIssumtab());
			dictTModel.setDictTFactorList(dtfList);
			this.insertDictTModel(dictTModel, true);

		} catch (Exception e) {
			e.printStackTrace();
			// 删除 列 and 表
			dictTFactorSelfMapper.deleteAllDictTFactorByTableID(tableID);
			dictTUpdateviewMapper.deleteDictTUpdateviewByTableID(tableID);
			deleteDictTModel(tableID, true);

			ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW + "'"
					+ viewName + "'";
			isViewExist = dictDBExecuteService.getIfExistsInDB(ifExistsViewSql);// 同名视图
			if (isViewExist == 1) {
				this.dictDBExecuteService.ExecDllLongForParam("DROP VIEW "
						+ viewName);
			}
			String ifExistsTriggerSql = DictDBConstants.IF_EXISTS_TYPE_TRIGGER
					+ "'TR_" + prefix + viewName + "'";
			Integer isTriggerExist = dictDBExecuteService
					.getIfExistsInDB(ifExistsTriggerSql);// 同名触发器
			if (isTriggerExist == 1) {
				this.dictDBExecuteService.ExecDllLongForParam("DROP TRIGGER "
						+ "TR_" + prefix + viewName);
			}
			throw new Exception(e.getMessage());
		}

	}

	/**
	 * 删除 1物理表
	 * 
	 * @param String
	 *            tableid
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteDictTModelForPhysics(DictTModelPO dictTModel)
			throws Exception {

		String tableName = dictTModel.getDbtablename().toUpperCase();
		String ispartition = dictTModel.getIspartition();
		String prefix = DictDBConstants.PREFIX_TABLE;
		if (ispartition.equals("0")) {
			prefix = "";
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
			// 实际删除
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableid", dictTModel.getTableid());
			List<DictTFactorPO> listFactor = this.dictTFactorMapper
					.findDictTFactor(map);
			try {
				for (DictTFactorPO dtf : listFactor) {
					// 平台注册列不用循环删除，调用平台删表接口表一起删除
					this.dictTFactorSelfService.deleteDictTFactor(tableName,
							dtf, false);
				}
				this.deleteDictTModel(dictTModel.getTableid(), true);
			} catch (Exception e1) {

				e1.printStackTrace();
				throw new Exception(e1.getMessage());
			}

			if (!("1".equals(ispartition))) {
				// +视图
				// String ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW
				// + "'"+tableName+"'";
				// Integer r_view =
				// dictDBExecuteService.getIfExistsInDB(ifExistsViewSql);//同名视图
				// if(r_view==1){
				// try {
				// this.dictDBExecuteService.ExecDllLongForParam("DROP VIEW "+
				// tableName);
				// } catch (Exception e) {

				// e.printStackTrace();
				// throw new Exception(e.getMessage());
				// }
				// }else{
				// throw new Exception(prefix+tableName+" :视图已经存在.");
				// }

				// 单物理表
				String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
						+ "'" + prefix + tableName + "'";
				Integer isTableExist = dictDBExecuteService
						.getIfExistsInDB(ifExistsTableSql);
				if (isTableExist == 1) {
					try {
						this.dictDBExecuteService
								.ExecDllLongForParam("DROP TABLE " + prefix
										+ tableName + " PURGE ");
					} catch (Exception e) {

						e.printStackTrace();
						throw new Exception(e.getMessage());
					}
				} else {
					// throw new Exception(tableName+"物理表不存在.");
				}
			} else {
				/**
				 * 单物理表 分区 视图 触发器
				 */
				// 删除视图
				String ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW
						+ "'" + tableName + "'";
				Integer isViewExist = dictDBExecuteService
						.getIfExistsInDB(ifExistsViewSql);// 同名视图
				if (isViewExist == 1) {
					try {
						this.dictDBExecuteService
								.ExecDllLongForParam("DROP VIEW " + tableName);
					} catch (Exception e) {

						e.printStackTrace();
						throw new Exception(e.getMessage());
					}
				}
				// 删除配置表视图
				if (isConfig) {
					// 删除配置视图
					ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW + "'"
							+ tableName + DictDBConstants.SUFFIX_CONFIG_VIEW
							+ "'";
					isViewExist = dictDBExecuteService
							.getIfExistsInDB(ifExistsViewSql);// 同名视图
					if (isViewExist == 1) {
						try {
							this.dictDBExecuteService
									.ExecDllLongForParam("DROP VIEW "
											+ tableName
											+ DictDBConstants.SUFFIX_CONFIG_VIEW);
						} catch (Exception e) {

							e.printStackTrace();
							throw new Exception(e.getMessage());
						}
					}
				}
				// +触发器
				String ifExistsTriggerSql = DictDBConstants.IF_EXISTS_TYPE_TRIGGER
						+ "'TR_" + prefix + tableName + "'";
				Integer isTriggerExist = dictDBExecuteService
						.getIfExistsInDB(ifExistsTriggerSql);// 同名触发器
				if (isTriggerExist == 1) {
					try {
						this.dictDBExecuteService
								.ExecDllLongForParam("DROP TRIGGER " + "TR_"
										+ prefix + tableName);
					} catch (Exception e) {

						e.printStackTrace();
						throw new Exception(e.getMessage());
					}
				} else {
					// throw new Exception("TR_"+prefix+tableName+" :触发器已经存在.");
				}
				// 物理表
				String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
						+ "'" + prefix + tableName + "'";
				Integer isTableExist = dictDBExecuteService
						.getIfExistsInDB(ifExistsTableSql);
				if (isTableExist == 1) {
					try {
						this.dictDBExecuteService
								.ExecDllLongForParam("DROP TABLE " + prefix
										+ tableName + " PURGE ");
					} catch (Exception e) {

						e.printStackTrace();
						throw new Exception(e.getMessage());
					}
				} else {
					// throw new Exception(tableName+"物理表不存在.");
				}
			}
			/*
			 * //实际删除 Map<String,Object> map = new HashMap<String, Object>();
			 * map.put("tableid", dictTModel.getTableid()); List<DictTFactorPO>
			 * listFactor = this.dictTFactorMapper.findDictTFactor(map);
			 * for(DictTFactorPO dtf :listFactor){
			 * this.dictTFactorSelfService.deleteDictTFactor(dtf,false); }
			 * this.deleteDictTModel(dictTModel.getTableid());
			 */

			// ---------------------------------------------------------------------BAK-------------------------------------------------------
			try {
				String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
						+ "'" + prefix + tableName + "_BAK'";
				Integer isTableExist = dictDBExecuteService
						.getIfExistsInDB(ifExistsTableSql);
				if (isTableExist == 1) {
					deleteTableForBak(dictTModel);
				}
			} catch (Exception e) {
				throw new Exception("同步删除BAK表出错.");
			}
		}
	}

	/**
	 * 删除 2（不可更新）视图
	 * 
	 * @param String
	 *            tableid
	 */

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteDictTModelForNoUpdateView(DictTModelPO dictTModel)
			throws Exception {

		// 实际删除
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableid", dictTModel.getTableid());
		List<DictTFactorPO> listFactor = this.dictTFactorMapper
				.findDictTFactor(map);
		for (DictTFactorPO dtf : listFactor) {
			// 平台注册列不用循环删除，调用平台删表接口表一起删除
			this.dictTFactorSelfService.deleteDictTFactor(null, dtf, false);
		}
		this.deleteDictTModel(dictTModel.getTableid(), true);
	}

	/**
	 * 删除 3（可更新）视图
	 * 
	 * @param DictTModelPO
	 *            dictTModel
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteDictTModelForUpdateView(DictTModelPO dictTModel)
			throws Exception {

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableid", dictTModel.getTableid());
			List<DictTFactorPO> dtfList = this.dictTFactorMapper
					.findDictTFactor(map);
			for (DictTFactorPO dtf : dtfList) {
				// 平台注册列不用循环删除，调用平台删表接口表一起删除
				this.dictTFactorSelfService.deleteDictTFactor(null, dtf, false);
			}
			List<DictTUpdateviewPO> dtuList = this.dictTUpdateviewMapper
					.findDictTUpdateview(map);
			for (DictTUpdateviewPO dtu : dtuList) {
				this.dictTUpdateviewMapper.deleteDictTUpdateview(dtu.getGuid());
			}
			this.deleteDictTModel(dictTModel.getTableid(), true);
			// +视图
			String ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW + "'"
					+ dictTModel.getDbtablename().toUpperCase() + "'";
			Integer isViewExist = dictDBExecuteService
					.getIfExistsInDB(ifExistsViewSql);// 同名视图
			if (isViewExist == 1) {
				this.dictDBExecuteService.ExecDllLongForParam("DROP VIEW "
						+ dictTModel.getDbtablename().toUpperCase());
			}
		} catch (Exception e) {

			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertDictTModel(DictTModelPO dictTModel, boolean isAsync)
			throws Exception {
		if (dictTModel != null) {
			dictTModelSelfMapper.insertDictTModel(dictTModel);
			// 清除缓存
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
			if (isAsync) {
				try {
					// 同步到平台
					registerDicTable(dictTModel);
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("同步增加平台注册表信息出错");
				}
			}
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void updateDictTModel(DictTModelPO newDtm) throws Exception {
		try {
			DictTModelPO oldDtm = this.dictTModelService.getDictTModelByID(
					newDtm.getTableid(), false);
			newDtm.setDbtablename(oldDtm.getDbtablename().toUpperCase());
			if (newDtm.getDealtype() == null || "".equals(newDtm)) {
				newDtm.setDealtype(oldDtm.getDealtype());
			}
			String tableType = oldDtm.getTabletype();
			newDtm.setTabletype(tableType);
			String ispartition = oldDtm.getIspartition();
			String dbStatus = this.dictDBExecuteService.getGlobalIsmultdb();
			String prefix = DictDBConstants.PREFIX_TABLE;
			if (oldDtm.getSecusql() == null) {
				oldDtm.setSecusql("");
			}
			if (newDtm.getSecusql() == null) {
				newDtm.setSecusql("");
			}
			if (oldDtm.getIstask() == null || "".equals(oldDtm.getIstask())) {
				oldDtm.setIstask("0");
			}
			if (newDtm.getIstask() == null || "".equals(newDtm.getIstask())) {
				newDtm.setIstask("0");
			}
			if (dbStatus.equals("0")) {
				prefix = "";
			}
			boolean isConfig = false;
			// 得到处理类型
			String dealTypeID = newDtm.getDealtype();
			// 通过处理类型得到是否需要配置视图
			DictTDealtypePO dictTDealtype = dictTDealtypeService
					.getDictTDealtype(dealTypeID, oldDtm.getAppid());
			if (dictTDealtype != null) {
				if (DictDBConstants.TABLE_TYPE_TABLE.equals(tableType)
						&& "1".equals(dictTDealtype.getNeedconfig())) {
					isConfig = true;
				}
			}
			// 查找BAK表是否存在
			String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
					+ "'" + prefix
					+ oldDtm.getDbtablename().toUpperCase().trim() + "_BAK'";
			Integer isTableExist = this.dictDBExecuteService
					.getIfExistsInDB(ifExistsTableSql);
			boolean hasBak = false;
			if (DictDBConstants.TABLE_TYPE_TABLE.equals(tableType)
					&& isTableExist == 1) {
				hasBak = true;
			}
			List<String> sqlList = new ArrayList<String>();
			List<String> bakSqlList = new ArrayList<String>();

			// 物理表的变更SQL
			if (DictDBConstants.TABLE_TYPE_TABLE.equals(tableType)) {
				// 表中文名变更引起表注释变更
				if (!oldDtm.getName().trim().equals(newDtm.getName().trim())) {
					String add_comments = DictDBConstants.ADD_COMMENTS + "  "
							+ prefix
							+ oldDtm.getDbtablename().toUpperCase().trim()
							+ " " + DictDBConstants.IS + " '"
							+ newDtm.getName().trim() + "' ";
					sqlList.add(add_comments);
					// 如果存在bak表，同步更新
					if (hasBak) {
						String add_comments_forBak = DictDBConstants.ADD_COMMENTS
								+ "  "
								+ prefix
								+ oldDtm.getDbtablename().toUpperCase().trim()
								+ "_BAK"
								+ " "
								+ DictDBConstants.IS
								+ " '"
								+ newDtm.getName().trim() + "' ";
						bakSqlList.add(add_comments_forBak);
					}
				}
				// 物理表任务表属性变更
				if (!oldDtm.getIstask().equals(newDtm.getIstask())) {
					Boolean isTask = false, isAllDistrict = false, isAllYear = false;
					String tableName = newDtm.getDbtablename().toUpperCase();

					if ("1".equals(newDtm.getIstask())) {
						isTask = true;
					}
					;
					if ("1".equals(newDtm.getIsalldistrict())) {
						isAllDistrict = true;
					}
					if ("1".equals(newDtm.getIsallyear())) {
						isAllYear = true;
					}
					// 如果变为任务表
					if (isTask) {
						// 判断表TASKID列是否存在，不存在就增加该列
						String ifExistsColumnSql = DictDBConstants.IF_EXISTS_TYPE_COLUMN
								+ "'"
								+ prefix
								+ tableName
								+ "' AND  COLUMN_NAME ='TASKID'";
						Integer isColumnExist = dictDBExecuteService
								.getIfExistsInDB(ifExistsColumnSql);
						// 如果不存在，更新物理表
						if (isColumnExist == 0) {
							StringBuffer alterSql = new StringBuffer(
									DictDBConstants.ALTER_TABLE + " ");
							alterSql.append(" ");
							alterSql.append(prefix + tableName + " ");
							alterSql.append(DictDBConstants.ADD + " ");
							alterSql.append("  TASKID VARCHAR2(32) ");
							sqlList.add(alterSql.toString());
						}
						// 如果存在bak表,同步进行
						if (hasBak) {
							// 判断表TASKID列是否存在，不存在就增加该列
							ifExistsColumnSql = DictDBConstants.IF_EXISTS_TYPE_COLUMN
									+ "'"
									+ prefix
									+ tableName
									+ "_BAK' AND  COLUMN_NAME ='TASKID'";
							isColumnExist = dictDBExecuteService
									.getIfExistsInDB(ifExistsColumnSql);
							// 如果不存在，更新物理表
							if (isColumnExist == 0) {
								StringBuffer alterSql = new StringBuffer(
										DictDBConstants.ALTER_TABLE + " ");
								alterSql.append(" ");
								alterSql.append(prefix + tableName + "_BAK ");
								alterSql.append(DictDBConstants.ADD + " ");
								alterSql.append("  TASKID VARCHAR2(32) ");
								bakSqlList.add(alterSql.toString());
							}
						}
					}
					// 视图字段
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("tableid", oldDtm.getTableid());
					map.put("isleaf", "1");
					/*
					 * List<String> listIndex = new ArrayList<String>();
					 * if("1".equals(status)){ listIndex.add("YEAR"); // 索引
					 * }else if("2".equals(status)){ listIndex.add("PROVINCE");
					 * // 索引 listIndex.add("YEAR"); // 索引 }
					 */
					List<DictTFactorPO> factorList = this.dictTFactorMapper
							.findDictTFactor(map);
					// StringBuilder viewColum = new StringBuilder();
					Map<String, List<DictTFactorPO>> viewColumMap = new HashMap<String, List<DictTFactorPO>>();
					List<DictTFactorPO> listFactor = new ArrayList<DictTFactorPO>();
					viewColumMap.put(prefix + tableName, listFactor);
					for (DictTFactorPO dtf : factorList) {
						// 标题列不考虑
						if (dtf.getDatatype() != 4) {
							listFactor = viewColumMap.get(prefix + tableName);
							listFactor.add(dtf);
							viewColumMap.put(prefix + tableName, listFactor);
						}
					}
					// 过滤条件换成新的
					String secusql = newDtm.getSecusql();
					// 重构视图
					String viewSql = dictDBExecuteService.createView(
							ispartition, dbStatus, tableName, viewColumMap,
							secusql, isTask, false, isAllDistrict, isAllYear);
					sqlList.add(viewSql);

					// 如果需要有配置表，同步重构配置表视图
					if (isConfig) {
						String configViewSql = dictDBExecuteService.createView(
								ispartition, dbStatus, tableName, viewColumMap,
								secusql, isTask, isConfig, isAllDistrict, isAllYear);
						sqlList.add(configViewSql);
					}
					// 如果有BAK表,同步重构BAK表视图
					if (hasBak) {
						String bakViewSql = dictDBExecuteService.createView(
								ispartition, dbStatus, tableName + "_BAK",
								viewColumMap, secusql, isTask, isConfig,
								isAllDistrict, isAllYear);
						sqlList.add(bakViewSql);
					}
					// 重构触发器
					String triggerSql = dictDBExecuteService.createTrigger(
							dbStatus, tableName, isTask);
					sqlList.add(triggerSql);

				}
				// 字段变更或者过滤SQL变更也需要重构视图
				else if (!oldDtm.getIsalldistrict().equals(newDtm.getIsalldistrict())
						|| !oldDtm.getIsallyear().equals(newDtm.getIsallyear())		// 是否多年度多年度
						|| !newDtm.getSecusql().equals(oldDtm.getSecusql())) {
					Boolean isTask = false, isAllDistrict = false, isAllYear = false;
					String tableName = newDtm.getDbtablename().toUpperCase();
					if ("1".equals(newDtm.getIstask())) {
						isTask = true;
					}
					if ("1".equals(newDtm.getIsalldistrict())) {
						isAllDistrict = true;
					}
					if ("1".equals(newDtm.getIsallyear())) {
						isAllYear = true;
					}
					// 视图字段
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("tableid", oldDtm.getTableid());
					map.put("isleaf", "1");
					List<DictTFactorPO> factorList = this.dictTFactorMapper
							.findDictTFactor(map);
					Map<String, List<DictTFactorPO>> viewColumMap = new HashMap<String, List<DictTFactorPO>>();
					List<DictTFactorPO> listFactor = new ArrayList<DictTFactorPO>();
					viewColumMap.put(prefix + tableName, listFactor);
					for (DictTFactorPO dtf : factorList) {
						// 标题列不考虑
						if (dtf.getDatatype() != 4) {
							listFactor = viewColumMap.get(prefix + tableName);
							listFactor.add(dtf);
							viewColumMap.put(prefix + tableName, listFactor);
						}
					}
					// 过滤条件换成新的
					String secusql = newDtm.getSecusql();
					// 重构视图
					String viewSql = dictDBExecuteService.createView(
							ispartition, dbStatus, tableName, viewColumMap,
							secusql, isTask, false, isAllDistrict, isAllYear);
					sqlList.add(viewSql);
					// 如果需要有配置表，同步重构配置表视图
					if (isConfig) {
						String configViewSql = dictDBExecuteService.createView(
								ispartition, dbStatus, tableName, viewColumMap,
								secusql, isTask, isConfig, isAllDistrict, isAllYear);
						sqlList.add(configViewSql);
					}
					// 如果有BAK表,同步重构BAK表视图
					if (hasBak) {
						String bakViewSql = dictDBExecuteService.createView(
								ispartition, dbStatus, tableName + "_BAK",
								viewColumMap, secusql, isTask, isConfig,
								isAllDistrict, isAllYear);
						sqlList.add(bakViewSql);
					}
				}
			}
			// 业务对象的变更SQL
			if (DictDBConstants.TABLE_TYPE_TABLE.equals(tableType)) {

			}
			dictTModelSelfMapper.updateDictTModel(newDtm);
			// 清除缓存
			// 清除表ID缓存
			String[] tableIDKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.ID.getCacheKey(),
					oldDtm.getTableid() };
			dataCacheService.put(tableIDKeys, null);
			String[] viewKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.ID.getCacheKey(), oldDtm.getTableid(),
					"getDictTModelByID"};
			dataCacheService.put(viewKeys, null);
			// 清除表物理名缓存
			String[] tableDBNameKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.DBNAME.getCacheKey(),
					oldDtm.getDbtablename() };
			dataCacheService.put(tableDBNameKeys, null);
			// 清除表类型
			String[] tableTypeKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.TYPE.getCacheKey(),
					oldDtm.getTabletype() };
			dataCacheService.put(tableTypeKeys, null);
			// 清除表处理类型
			String[] tableDealTypeKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.DEALTYPE.getCacheKey(),
					oldDtm.getDealtype() };
			dataCacheService.put(tableDealTypeKeys, null);

			String suitID = oldDtm.getSuitid();
			// 清除套表缓存
			String[] tableSuitKeys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictSuit.ID.getCacheKey() };
			dataCacheService.put(tableSuitKeys, null);
			String appID = oldDtm.getAppid();
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

			// DictTModelPO olddtmforupdate = (DictTModelPO) newDtm.clone();
			// dictTModelSelfMapper.updateDictTModel(DictDBConstants
			// .changeDataForDictTModelPOUpdate(olddtmforupdate, oldDtm));

			// dll语句自动事务提交，先执行更新操作, 再执行dll语句，
			if (sqlList != null && sqlList.size() > 0) {
				for (String sqlString : sqlList) {
					dictDBExecuteService.ExecDllLongForParam(sqlString);
				}
				// 重构视图触发器
				dictDBExecuteService.createFormulaTrigger(newDtm.getTableid());

			}
			if (hasBak) {
				if (bakSqlList != null && bakSqlList.size() > 0) {
					for (String sqlString : bakSqlList) {
						dictDBExecuteService.ExecDllLongForParam(sqlString);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteDictTModel(String tableid, boolean isAsync)
			throws Exception {
		if (tableid != null && !"".equals(tableid)) {

			try {
				DictTModelPO dictTModel = this.dictTModelService
						.getDictTModelByID(tableid, false);
				dictTModelSelfMapper.deleteDictTModel(tableid);
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
				if (isAsync) {
					// 删除平台登记信息
					deleteRegisterDicTable(dictTModel.getDbtablename());
				}
			} catch (Exception e) {

				e.printStackTrace();
				throw new Exception("删除业务表出错");
			}
		}
	}

	public Grid getDictTModelHead(String tableid_) {

		// 创建Grid
		Grid grid = new Grid();
		// 设置tableID
		grid.setTableID(tableid_);
		// Grid类型
		grid.setTableDBName("");
		grid.setTableName("");
		grid.setAppID("");
		int orderId = 0;
		// 创建列
		Column suitName = new Column();
		suitName.setColumnID("suitName");
		suitName.setColumnDBName("suitName");
		suitName.setColumnName("所属套表");
		suitName.setAlias("所属套表");
		suitName.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		suitName.setShowType(ShowType.SHOW_TYPE_HTML);
		suitName.setOrderID(++orderId);
		suitName.setVisible(true);

		Column name = new Column();
		name.setColumnID("name");
		name.setColumnDBName("name");
		name.setColumnName("中文名称");
		name.setAlias("中文名称");
		name.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		name.setShowType(ShowType.SHOW_TYPE_HTML);
		name.setOrderID(++orderId);
		name.setVisible(true);

		Column dbtablename = new Column();
		dbtablename.setColumnID("dbtablename");
		dbtablename.setColumnDBName("dbtablename");
		dbtablename.setColumnName("物理名称");
		dbtablename.setAlias("物理名称");
		dbtablename.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		dbtablename.setShowType(ShowType.SHOW_TYPE_HTML);
		dbtablename.setOrderID(++orderId);
		dbtablename.setVisible(true);

		Column tabletype = new Column();
		tabletype.setColumnID("tabletype");
		tabletype.setColumnDBName("tabletype");
		tabletype.setColumnName("表类型");
		tabletype.setAlias("表类型");
		tabletype.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		tabletype.setShowType(ShowType.SHOW_TYPE_HTML);
		tabletype.setOrderID(++orderId);
		tabletype.setVisible(true);

		Column dealName = new Column();
		dealName.setColumnID("dealName");
		dealName.setColumnDBName("dealName");
		dealName.setColumnName("表处理类型");
		dealName.setAlias("表处理类型");
		dealName.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		dealName.setShowType(ShowType.SHOW_TYPE_HTML);
		dealName.setOrderID(++orderId);
		dealName.setVisible(true);

		Column isreadonly = new Column();
		isreadonly.setColumnID("isReadOnly");
		isreadonly.setColumnDBName("isReadOnly");
		isreadonly.setColumnName("是否只读");
		isreadonly.setAlias("是否只读");
		isreadonly.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		isreadonly.setShowType(ShowType.SHOW_TYPE_HTML);
		isreadonly.setOrderID(++orderId);
		isreadonly.setVisible(true);

		Column secusql = new Column();
		secusql.setColumnID("secusql");
		secusql.setColumnDBName("secusql");
		secusql.setColumnName("过滤条件");
		secusql.setAlias("过滤条件");
		secusql.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		secusql.setShowType(ShowType.SHOW_TYPE_HTML);
		secusql.setOrderID(++orderId);
		secusql.setVisible(true);

		Column isbak = new Column();
		isbak.setColumnID("isbak");
		isbak.setColumnDBName("isbak");
		isbak.setColumnName("备份");
		isbak.setAlias("备份");
		isbak.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		isbak.setShowType(ShowType.SHOW_TYPE_HTML);
		isbak.setOrderID(++orderId);
		isbak.setVisible(true);

		// 是否按照任务分区
		Column taskPartition = new Column();
		taskPartition.setColumnID("istask");
		taskPartition.setColumnDBName("istask");
		taskPartition.setColumnName("按任务分区");
		taskPartition.setAlias("按任务分区");
		taskPartition.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		taskPartition.setShowType(ShowType.SHOW_TYPE_HTML);
		taskPartition.setOrderID(++orderId);
		taskPartition.setVisible(true);
		// 是否全地区访问
		Column fullAreaAccess = new Column();
		fullAreaAccess.setColumnID("isalldistrict");
		fullAreaAccess.setColumnDBName("isalldistrict");
		fullAreaAccess.setColumnName("全地区访问");
		fullAreaAccess.setAlias("全地区访问");
		fullAreaAccess.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		fullAreaAccess.setShowType(ShowType.SHOW_TYPE_HTML);
		fullAreaAccess.setOrderID(++orderId);
		fullAreaAccess.setVisible(true);
		
		// 是否全年度访问
		Column isallyear = new Column();
		isallyear.setColumnID("isallyear");
		isallyear.setColumnDBName("isallyear");
		isallyear.setColumnName("全年度访问");
		isallyear.setAlias("全年度访问");
		isallyear.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		isallyear.setShowType(ShowType.SHOW_TYPE_HTML);
		isallyear.setOrderID(++orderId);
		isallyear.setVisible(true);
		
		
		
		Column tableid = new Column();
		tableid.setColumnID("tableid");
		tableid.setColumnDBName("tableid");
		tableid.setColumnName("表id");
		tableid.setAlias("表id");
		tableid.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		tableid.setShowType(ShowType.SHOW_TYPE_HTML);
		tableid.setOrderID(++orderId);
		tableid.setKey(true);
		tableid.setVisible(false);

		List<Column> columnlist = new ArrayList<Column>();
		columnlist.add(suitName);
		columnlist.add(name);
		columnlist.add(dbtablename);
		columnlist.add(tabletype);
		columnlist.add(dealName);
		columnlist.add(isreadonly);
		// columnlist.add(tabletype1);
		columnlist.add(secusql);
		columnlist.add(isbak);
		columnlist.add(taskPartition);
		columnlist.add(fullAreaAccess);
		columnlist.add(isallyear);
		columnlist.add(tableid);
		grid.setColumnList(columnlist);
		return grid;
	}

	/**
	 * 获取某个套表下(包括子套)中的所有 DictTModel
	 * 
	 * @param suitid
	 *            套表ID
	 * @param childColumn
	 *            是否包含表中的 列（默认不带）
	 * @return List<DictTModel>
	 * 
	 */
	public List<DictTModelPO> getDictTModelsBySuitId(String suitid,
			boolean childColumn) {

		List<DictTModelPO> dtmList = null;
		try {
			if (suitid != null && !"".equals(suitid)) {
				List<DictTSuitPO> dtsList = dictTSuitMapper.getAllDictTSuit();
				List<DictTSuitPO> list = new ArrayList<DictTSuitPO>();
				/**
				 * 查找出 套表suitid 下的所有子套 的 末端 ：：：list
				 */
				for (DictTSuitPO dts : dtsList) {
					if (dts.getSuitid().equals(suitid)) {
						list.add(dts);
						list = dictTSuitChildren(dts, dtsList, list);
						break;
					}
				}
				/**
				 * 所有子套 的 末端list 循环查找表
				 */
				if (list != null && list.size() > 0) {
					dtmList = new ArrayList<DictTModelPO>();
					Map<String, Object> dtmMap = new HashMap<String, Object>();
					Map<String, Object> dtfMap = new HashMap<String, Object>();
					for (DictTSuitPO dts : list) {
						dtmMap.put("suitid", dts.getSuitid());
						List<DictTModelPO> dtms = this.dictTModelMapper
								.findDictTModel(dtmMap);
						for (DictTModelPO dtm : dtms) {
							if (childColumn) {
								dtfMap.put("tableid", dtm.getTableid());
								dtm.setDictTFactorList(this.dictTFactorMapper
										.findDictTFactor(dtfMap));
							}
							dtmList.add(dtm);
						}
					}
				}

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return dtmList;
	}

	/**
	 * 获取某个套表下(包括子套)中的所有 DictTModel
	 * 
	 * @param suitid
	 *            套表id
	 * @param map
	 *            多条件
	 * @param childColumn
	 * @return
	 */
	public List<DictTModelPO> getDictTModelsBySuitIdmap(String suitid,
			Map<String, Object> map, boolean childColumn) {

		List<DictTModelPO> dtmList = null;
		try {
			if (suitid != null && !"".equals(suitid)) {
				Map<String, Object> map_ = new HashMap<String, Object>();
				List<DictTSuitPO> dtsList = dictTSuitMapper.findDictTSuit(map_);
				List<DictTSuitPO> list = new ArrayList<DictTSuitPO>();
				/**
				 * 查找出 套表suitid 下的所有子套 的 末端 ：：：list
				 */
				for (DictTSuitPO dts : dtsList) {
					if (dts.getSuitid().equals(suitid)) {
						list.add(dts);
						list = dictTSuitChildren(dts, dtsList, list);
						break;
					}
				}
				/**
				 * 所有子套 的 末端list 循环查找表
				 */
				if (list != null && list.size() > 0) {
					dtmList = new ArrayList<DictTModelPO>();
					Map<String, Object> dtmMap = new HashMap<String, Object>();
					Map<String, Object> dtfMap = new HashMap<String, Object>();
					for (DictTSuitPO dts : list) {
						map.put("suitid", dts.getSuitid());
						List<DictTModelPO> dtms = this.dictTModelMapper
								.findDictTModel(map);
						for (DictTModelPO dtm : dtms) {
							if (childColumn) {
								dtfMap.put("tableid", dtm.getTableid());
								dtm.setDictTFactorList(this.dictTFactorMapper
										.findDictTFactor(dtfMap));
							}
							dtmList.add(dtm);
						}
					}
				}

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return dtmList;
	}

	/**
	 * 结构排列
	 * 
	 * @param pc
	 * @param pcs
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<DictTSuitPO> dictTSuitChildren(DictTSuitPO p,
			List<DictTSuitPO> pcs, List<DictTSuitPO> list) {
		List<Integer> indexs = new ArrayList<Integer>();
		p.setDictTModelList(new ArrayList<DictTModelPO>());
		for (int i = 0; i < pcs.size(); i++) {
			if (p.getSuitid().equals(pcs.get(i).getSuperid())) {
				p.getDictTSuitList().add(pcs.get(i));
				if ("1".equals(pcs.get(i).getIsleaf())) {
					list.add(pcs.get(i));
				}
			}
		}
		if (p.getDictTSuitList().size() > 0) {
			for (DictTSuitPO p_ : p.getDictTSuitList())
				dictTSuitChildren(p_, pcs, list);
		}
		return list;
	}

	@Override
	public List<DictTModelPO> getDictTModels(Map<String, Object> map) {

		List<DictTModelPO> dtmList = null;
		try {
			dtmList = dictTModelMapper.findDictTModel(map);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return dtmList;
	}

	/**
	 * 更改物理表名称 for 物理表
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveDictModleForTableName(String newDBTableName,
			DictTModelPO dictTModel) throws Exception {

		String tableName = dictTModel.getDbtablename().toUpperCase();
		String ispartition = dictTModel.getIspartition();
		String status = this.dictDBExecuteService.getGlobalIsmultdb();
		String prefix = DictDBConstants.PREFIX_TABLE;
		if (ispartition.equals("0")) {
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
		if (newDBTableName != null && !"".equals(newDBTableName)) {

			DictTModelPO updatepo = (DictTModelPO) dictTModel.clone();
			updatepo.setDbtablename(newDBTableName);
			// update到factor
			try {
				this.updateDictTModel(updatepo);
			} catch (Exception e1) {

				e1.printStackTrace();
				throw new Exception(e1.getMessage());
			}

			// 主键 and 视图字段
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableid", dictTModel.getTableid());
			map.put("isleaf", "1");
			// List<String> listIndex = new ArrayList<String>();
			/*
			 * if("1".equals(status)){ listIndex.add("YEAR"); // 索引
			 * listIndex.add("STATUS"); //索引 }else if("2".equals(status)){
			 * listIndex.add("PROVINCE"); // 索引 listIndex.add("YEAR"); // 索引
			 * listIndex.add("STATUS"); //索引 }
			 */
			List<DictTFactorPO> factorList = this.dictTFactorMapper
					.findDictTFactor(map);
			Map<String, List<DictTFactorPO>> viewColumMap = new HashMap<String, List<DictTFactorPO>>();
			// 原有主键
			for (DictTFactorPO dtf : factorList) {
				// //原有视图字段
				if (dtf.getDatatype() != 4) {
					List<DictTFactorPO> listFactor = viewColumMap.get(prefix
							+ newDBTableName);
					if (listFactor == null) {
						listFactor = new ArrayList<DictTFactorPO>();
						listFactor.add(dtf);
						viewColumMap.put(prefix + newDBTableName, listFactor);
					} else {
						listFactor.add(dtf);
						viewColumMap.put(prefix + newDBTableName, listFactor);
					}
					/*
					 * if("1".equals(dtf.getIskey())){//原有主键
					 * listIndex.add(dtf.getDbcolumnname()); }
					 */

				}
			}
			StringBuilder rename = new StringBuilder();
			// alter table prefix+AABB rename column T to Tttttt;
			rename.append(DictDBConstants.ALTER_TABLE + " ");
			rename.append(prefix + tableName + " ");
			rename.append(DictDBConstants.RENAME + " ");
			rename.append(DictDBConstants.TO + " ");
			/**
			 * 单一物理表
			 */
			if (!("1".equals(ispartition))) {
				rename.append(prefix + newDBTableName);
				// 单物理表
				String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
						+ "'" + prefix + tableName + "'";
				Integer r_table = dictDBExecuteService
						.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
				if (r_table == 1) {
					try {
						this.dictDBExecuteService.ExecDllLongForParam(rename
								.toString());
						// +视图
						// String ifExistsViewSql =
						// DictDBConstants.IF_EXISTS_TYPE_VIEW +
						// "'"+tableName+"'";
						// Integer r_view =
						// dictDBExecuteService.getIfExistsInDB(ifExistsViewSql);//同名视图
						// if(r_view==1){
						// this.dictDBExecuteService.ExecDllLongForParam("DROP VIEW "+
						// tableName);
						// }
						// 更新视图
						// String createViewSql =
						// dictDBExecuteService.createView(ispartition,status,
						// newDBTableName,
						// viewColumMap,dictTModel.getSecusql());
						// this.dictDBExecuteService.ExecDllLongForParam(createViewSql);
					} catch (Exception e) {

						e.printStackTrace();
						throw new Exception(e.getMessage());
					}
				} else {
					throw new Exception("未找到原表：" + "'" + prefix + tableName
							+ "' ");
				}

			} else {
				/**
				 * 区划
				 */
				rename.append(prefix + newDBTableName);
				String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
						+ "'" + prefix + tableName + "'";
				Integer r_table = dictDBExecuteService
						.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
				if (r_table == 1) {
					try {
						this.dictDBExecuteService.ExecDllLongForParam(rename
								.toString());

						// +视图
						String ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW
								+ "'" + tableName + "'";
						Integer r_view = dictDBExecuteService
								.getIfExistsInDB(ifExistsViewSql);// 同名视图
						if (r_view == 1) {
							this.dictDBExecuteService
									.ExecDllLongForParam("DROP VIEW "
											+ tableName);
						}
						// 更新视图
						String createViewSql = dictDBExecuteService.createView(
								ispartition, status, newDBTableName,
								viewColumMap, dictTModel.getSecusql(), isTask,
								false, isAllDistrict, isAllYear);
						this.dictDBExecuteService
								.ExecDllLongForParam(createViewSql);
						// 如果需要有配置表，同步创建配置表
						if (isConfig) {
							String configViewSql = dictDBExecuteService
									.createView(ispartition, status,
											newDBTableName, viewColumMap,
											dictTModel.getSecusql(), isTask,
											isConfig, isAllDistrict, isAllYear);
							this.dictDBExecuteService
									.ExecDllLongForParam(configViewSql);
						}

						// +触发器
						String ifExistsTriggerSql = DictDBConstants.IF_EXISTS_TYPE_TRIGGER
								+ "'TR_" + prefix + tableName + "'";
						Integer r_trigger = dictDBExecuteService
								.getIfExistsInDB(ifExistsTriggerSql);// 同名触发器
						if (r_trigger == 1) {
							this.dictDBExecuteService
									.ExecDllLongForParam("DROP TRIGGER "
											+ "TR_" + prefix + tableName);
						}
						String createTriggerSql = dictDBExecuteService
								.createTrigger(status, newDBTableName, isTask);
						this.dictDBExecuteService
								.ExecDllLongForParam(createTriggerSql);

					} catch (Exception e) {

						e.printStackTrace();
						throw new Exception(e.getMessage());
					}
				} else {
					throw new Exception("未找到分区表：" + "'" + prefix + tableName
							+ "' ");
				}
			}

			StringBuilder ifExistPKSql = new StringBuilder();
			ifExistPKSql.append(DictDBConstants.IF_EXISTS_TYPE_PK);
			ifExistPKSql.append("'PK_" + prefix + tableName + "'");
			ifExistPKSql.append(" AND T.TABLE_NAME = ");
			ifExistPKSql.append("'" + prefix + tableName + "' ");

			Integer r_pk = dictDBExecuteService.getIfExistsInDB(ifExistPKSql
					.toString());//

			List<String> listPK = new ArrayList<String>();
			if (r_pk == 1) {

				// 查找原来PK
				List<Map<String, String>> list = this.dictDBExecuteService
						.getTableColumnPK(prefix + tableName);
				for (Map<String, String> col : list) {
					String dbColumnName = col.get("COLUMN_NAME").toString()
							.toUpperCase();
					listPK.add(dbColumnName);
				}

				// 删除原来的主键
				// alter table prefix+AABB drop constraint PK_prefix+AABB
				// cascade;
				StringBuilder dropPk = new StringBuilder();
				dropPk.append(DictDBConstants.ALTER_TABLE + " ");
				dropPk.append(prefix + tableName + " ");
				dropPk.append(DictDBConstants.DROP + " ");
				dropPk.append(DictDBConstants.CONSTRAINT + " ");
				dropPk.append("PK_" + prefix + tableName + " ");
				dropPk.append(DictDBConstants.CASCADE);
				try {
					this.dictDBExecuteService.ExecDllLongForParam(dropPk
							.toString());
				} catch (Exception e) {

					e.printStackTrace();
					throw new Exception(e.getMessage());
				}
			}
			// 设定新 pk
			if (listPK.size() > 0) {
				try {
					String pksql = this.dictDBExecuteService.createPK(status,
							newDBTableName, listPK);
					this.dictDBExecuteService.ExecDllLongForParam(pksql);
				} catch (Exception e) {

					e.printStackTrace();
					throw new Exception(e.getMessage());
				}
			}

			// 更新index
			/*
			 * if(listIndex.size()>0){ StringBuilder ifExistIndexSql = new
			 * StringBuilder();
			 * ifExistIndexSql.append(DictDBConstants.IF_EXISTS_TYPE_INDEX);
			 * ifExistIndexSql.append("'"+prefix+tableName+"' ");
			 * ifExistIndexSql.append(" AND U.INDEX_NAME = ");
			 * ifExistIndexSql.append("'IN_"+prefix+tableName+"'"); Integer
			 * r_index =
			 * dictDBExecuteService.getIfExistsInDB(ifExistIndexSql.toString
			 * ());// //删除原有index if(r_index==1){ //删除原来的主键 try {
			 * this.dictDBExecuteService
			 * .ExecDllLongForParam("DROP INDEX IN_"+prefix+tableName); } catch
			 * (Exception e) { e.printStackTrace(); throw new
			 * Exception(e.getMessage()); } }
			 * 
			 * //设定新 索引 //create unique index INDEX_AACSWLB1 on prefix+AACSWLB1
			 * (ORDERID, PROVINCE) try { String indexsql =
			 * this.dictDBExecuteService.createIndex(newDBTableName, listIndex);
			 * this.dictDBExecuteService.ExecDllLongForParam(indexsql); } catch
			 * (Exception e) { e.printStackTrace(); throw new
			 * Exception(e.getMessage()); } }
			 */
			/*
			 * dictTModel.setDbtablename(newDBTableName); //update到factor
			 * this.updateDictTModel(dictTModel);
			 */
			// ----------------------------------------BAK--------------------------------------------------
			try {
				String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
						+ "'" + prefix + tableName + "_BAK'";
				Integer r_table = this.dictDBExecuteService
						.getIfExistsInDB(ifExistsTableSql);// 同名物理表
				if (r_table == 1) {
					updateTableNameForbak(newDBTableName + "_BAK", dictTModel);
				}
				// ----------------------------------------BAK--------------------------------------------------
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

	}

	/**
	 * 修改 可更新试图
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateDictTModelForUpdateView(DictTModelPO dictTModel,
			List<Map<String, String>> sourceColumnList,
			List<Map<String, String>> settingList) throws Exception {
		String tableID = dictTModel.getTableid();
		String viewName = dictTModel.getDbtablename().toUpperCase();

		// 遍历源数据对象,找到来源表
		Map<String, List<DictTFactorPO>> sourceTableMap = new HashMap<String, List<DictTFactorPO>>();
		// 来源列
		List<DictTFactorPO> selectedSourceColumnList = new ArrayList<DictTFactorPO>();

		setUpdateViewSourceColumnInfo(dictTModel, sourceColumnList,
				sourceTableMap, selectedSourceColumnList);
		Map<String, String> settingMap = new HashMap<String, String>();
		String status = this.dictDBExecuteService.getGlobalIsmultdb();
		// 关系字段
		for (Map<String, String> m : settingList) {
			settingMap.put(m.get("columnid"), m.get("tocolumnid"));
		}

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
			// TODO:如果是单表的业务对象，先不创建视图触发器,多表才创建
			if (!sourceTableMap.isEmpty() && sourceTableMap.size() > 1) {
				this.dictDBExecuteService.ExecDllLongForParam(triggerSql);
			}
			// 重构视图触发器
			dictDBExecuteService.createFormulaTrigger(tableID);
		} catch (Exception e) { // roll back

			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

		try {
			this.updateDictTModel(dictTModel);
			// Map<String, Object> mapWhere = new HashMap<String, Object>();
			// Map<String, DictTFactorPO> mapRe = new HashMap<String,
			// DictTFactorPO>();
			// mapWhere.put("tableid", dictTModel.getTableid());
			// mapWhere.put("isleaf", "1");
			// List<DictTFactorPO> dtfList_ = this.dictTFactorMapper
			// .findDictTFactor(mapWhere);
			//
			// for (DictTFactorPO dtf : dtfList_) {
			// mapRe.put(dtf.getColumnid(), dtf);
			// }
			// Map<String, String> keymap = new HashMap<String, String>();
			// Map<String, String> valuemap = new HashMap<String, String>();
			// for (DictTFactorPO dtf_ : dtfsList) {
			//
			// String oldColumnid = dtf_.getColumnoldid();
			// if (oldColumnid != null && !"".equals(oldColumnid)) {// update
			// mapRe.remove(oldColumnid);
			// DictTFactorPO olddtf = this.dictTFactorMapper
			// .getDictTFactor(oldColumnid);
			// olddtf.setDbcolumnname(dtf_.getChangename() == null
			// || "".equals(dtf_.getChangename()) ? dtf_
			// .getDbcolumnname().toUpperCase() : dtf_
			// .getChangename().toUpperCase());
			// olddtf.setFrmtabid(dtf_.getTableid());
			// olddtf.setFrmcolid(dtf_.getColumnid());
			// olddtf.setTableid(dictTModel.getTableid());
			// // olddtf.setName(olddtf.getDbcolumnname());
			// if (olddtf.getDefaultvalue() == null) {
			// olddtf.setDefaultvalue("");
			// }
			// valuemap.put(dtf_.getColumnid(), oldColumnid);
			// if ("1".equals(dtf_.getIsbandcol())) {
			// keymap.put(dtf_.getColumnid(), dtf_.getBandcolumnid());
			// }
			// this.dictTFactorSelfService.updateDictTFactor(olddtf, true);
			// } else { // insert
			// String columnid = dictDBExecuteService.getUUID();// id
			//
			// if ("1".equals(dtf_.getIsbandcol())) {
			// keymap.put(dtf_.getColumnid(), dtf_.getBandcolumnid());
			// }
			// valuemap.put(dtf_.getColumnid(), columnid);
			//
			// dtf_.setDbcolumnname(dtf_.getChangename() == null
			// || "".equals(dtf_.getChangename()) ? dtf_
			// .getDbcolumnname().toUpperCase() : dtf_
			// .getChangename().toUpperCase());
			// dtf_.setFrmtabid(dtf_.getTableid());
			// dtf_.setFrmcolid(dtf_.getColumnid());
			// dtf_.setColumnid(columnid);
			// dtf_.setTableid(dictTModel.getTableid());
			// dtf_.setSuperid("0");
			// // dtf_.setName(dtf_.getDbcolumnname());
			// if (dtf_.getDefaultvalue() == null) {
			// dtf_.setDefaultvalue("");
			// }
			// dtf_.setIsbandcol(dtf_.getIsbandcol() == null
			// || "".equals(dtf_.getIsbandcol()) ? "0" : dtf_
			// .getIsbandcol());
			// dtf_.setIshref(dtf_.getIshref() == null
			// || "".equals(dtf_.getIshref()) ? "0" : dtf_
			// .getIshref());
			// dtf_.setIskey(dtf_.getIskey() == null
			// || "".equals(dtf_.getIskey()) ? "0" : dtf_
			// .getIskey());
			// dtf_.setIsleaf(dtf_.getIsleaf() == null
			// || "".equals(dtf_.getIsleaf()) ? "1" : dtf_
			// .getIsleaf());
			// dtf_.setIsregex(dtf_.getIsregex() == null
			// || "".equals(dtf_.getIsregex()) ? "0" : dtf_
			// .getIsregex());
			// dtf_.setIsreserve(dtf_.getIsreserve() == null
			// || "".equals(dtf_.getIsreserve()) ? "0" : dtf_
			// .getIsreserve());
			// dtf_.setIssum(dtf_.getIssum() == null
			// || "".equals(dtf_.getIssum()) ? "0" : dtf_
			// .getIssum());
			// dtf_.setIsupdate(dtf_.getIsupdate() == null
			// || "".equals(dtf_.getIsupdate()) ? "1" : dtf_
			// .getIsupdate());
			// dtf_.setIsvirtual(dtf_.getIsvirtual() == null
			// || "".equals(dtf_.getIsvirtual()) ? "0" : dtf_
			// .getIsvirtual());
			// dtf_.setIsvisible(dtf_.getIsvisible() == null
			// || "".equals(dtf_.getIsvisible()) ? "1" : dtf_
			// .getIsvisible());
			// dtf_.setNullable(dtf_.getNullable() == null
			// || "".equals(dtf_.getNullable()) ? "1" : dtf_
			// .getNullable());
			// this.dictTFactorSelfService.insertDictTFactor(viewName,
			// dtf_, true);
			// }
			//
			// }
			// for (DictTFactorPO dtf1 : dtfsList) {
			// if ("1".equals(dtf1.getIsbandcol())) {
			// String newbandeid = valuemap.get(keymap.get(dtf1
			// .getFrmcolid()));
			// dtf1.setBandcolumnid(newbandeid);
			// this.dictTFactorSelfService.updateDictTFactor(dtf1, true);
			// }
			// }
			// for (Entry<String, DictTFactorPO> mn : mapRe.entrySet()) {
			// this.dictTFactorSelfService.deleteDictTFactor(viewName,
			// mn.getValue(), true);
			// }
			//
			// List<DictTUpdateviewPO> listDictTUpdateview =
			// this.dictTUpdateviewMapper
			// .findDictTUpdateview(mapWhere);
			// for (DictTUpdateviewPO dtu : listDictTUpdateview) {
			// this.dictTUpdateviewMapper.deleteDictTUpdateview(dtu.getGuid());
			// }
			// Map<String,String> settingMap = new HashMap<String, String>();

			List<DictTFactorPO> oldDtfList = dictTFactorService
					.getLeafFactorListByTableID(tableID);
			Map<String, String> existedFactorMap = new HashMap<String, String>();

			// 补全修改列信息
			for (DictTFactorPO dictTFactorPO : selectedSourceColumnList) {
				// 循环比较，需要用到frmcolumnID和frmTableID比较
				boolean isExist = false;
				for (DictTFactorPO oldDtf : oldDtfList) {
					if (oldDtf.getFrmtabid().equals(dictTFactorPO.getTableid())
							&& oldDtf.getFrmcolid().equals(
									dictTFactorPO.getColumnid())) {
						existedFactorMap.put(oldDtf.getColumnid(), "");
						// 如果别名不同,列名用新别名
						// 如果别名不为空
						if (dictTFactorPO.getChangename() != null
								&& !"".equals(dictTFactorPO.getChangename()
										.trim())) {
							// 如果别名和旧列不一致
							if (!oldDtf.getDbcolumnname().equals(
									dictTFactorPO.getChangename())) {
								// 列名变更需要先删除平台登记旧列
								dictTFactorSelfService.deleteRegisterDicColumn(
										viewName, oldDtf);
								// 设置别名
								oldDtf.setDbcolumnname(dictTFactorPO
										.getChangename().toUpperCase());
								isExist = true;
								// 更新列
								dictTFactorSelfService.updateDictTFactor(
										oldDtf, true);
								break;
							}
						}
						// 如果别名为空
						else {
							// 如果旧列和新列列名不一致，说明旧列使用了别名
							// 如果别名和旧列不一致
							if (!oldDtf.getDbcolumnname().equals(
									dictTFactorPO.getDbcolumnname())) {
								// 列名变更需要先删除平台登记旧列
								dictTFactorSelfService.deleteRegisterDicColumn(
										viewName, oldDtf);
								// 设置别名
								oldDtf.setDbcolumnname(dictTFactorPO
										.getDbcolumnname().toUpperCase());
								isExist = true;
								// 更新列
								dictTFactorSelfService.updateDictTFactor(
										oldDtf, true);
								break;
							}
						}
						isExist = true;
						break;
					}
				}
				if (isExist) {
					continue;
				}

				// 如果有别名,列名用别名
				if (dictTFactorPO.getChangename() != null
						&& !"".equals(dictTFactorPO.getChangename().trim())) {
					dictTFactorPO.setDbcolumnname(dictTFactorPO.getChangename()
							.toUpperCase());
				}

				dictTFactorPO.setFrmtabid(dictTFactorPO.getTableid());
				dictTFactorPO.setFrmcolid(dictTFactorPO.getColumnid());

				dictTFactorPO.setTableid(tableID);
				// 列ID换为新唯一编码
				String columnid = dictDBExecuteService.getUUID();
				dictTFactorPO.setColumnid(columnid);

				dictTFactorPO.setSuperid("0");
				dictTFactorPO.setIsleaf("1");
				dictTFactorPO.setLevelno(1);
				// 超链清空
				dictTFactorPO.setIshref("0");
				// 主键清空
				dictTFactorPO.setIskey("0");
				// 是否保留清空
				dictTFactorPO.setIsreserve("0");
				// 如果是绑定列，清空是否绑定属性，同时设置为只读列
				if ("1".equals(dictTFactorPO.getIsbandcol())) {
					dictTFactorPO.setIsbandcol("0");
					dictTFactorPO.setBandcolumnid("");
				}
				this.dictTFactorSelfService.insertDictTFactor(viewName,
						dictTFactorPO, true);

			}

			for (DictTFactorPO oldDtf : oldDtfList) {
				// 新列中不存在的，删除
				if (!existedFactorMap.containsKey(oldDtf.getColumnid())) {
					dictTFactorSelfService.deleteDictTFactor(viewName, oldDtf,
							true);
				}
			}

			// for (Map<String, String> m : settingList) {
			// // settingMap.put(m.get("columnid"), m.get("tocolumnid"));
			// DictTUpdateviewPO dtu = new DictTUpdateviewPO();
			// dtu.setColumnid(m.get("columnid"));
			// // dtu.setLineid(lineid);
			// dtu.setBgtlvl(dictTModel.getBgtlvl());
			// dtu.setTocolumnid(m.get("tocolumnid"));
			// // dtu.setVersion(0);
			// dtu.setTableid(dictTModel.getTableid());
			// dictTUpdateviewMapper.insertDictTUpdateview(dtu);
			// }

			/*
			 * for(Entry<String, String> mm :settingMap.entrySet()){
			 * DictTUpdateviewPO dtu = new DictTUpdateviewPO();
			 * dtu.setColumnid(mm.getKey()); //dtu.setLineid(lineid);
			 * dtu.setBgtlvl(dictTModel.getBgtlvl());
			 * dtu.setTocolumnid(mm.getValue()); // dtu.setVersion(0);
			 * dtu.setTableid(dictTModel.getTableid());
			 * dictTUpdateviewMapper.insertDictTUpdateview(dtu); }
			 */
			// 如果有bak表，重新构建bak表视图
			if ("1".equals(dictTModel.getIsbak())) {
				createDictTModelForUpdateViewBak(dictTModel);
			}
			//最后统一调用刷新表视图，避免大集中多分区造成的不一致
			dictDBExecuteService.recreateViews(tableID);
			String[] keys = { DictCacheKey.CACHE_KEY_DICT,
					DictCacheKey.DictTable.ID.getCacheKey(), tableID,
					"getDictTModelByID"};
			dataCacheService.put(keys, null);
		} catch (Exception e) {

			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

	}

	/**
	 * 修改 可更新试图 物理表名
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveDictModleForTableNameForUpdateView(
			List<DictTFactorPO> dtfsList, String newDBTableName,
			DictTModelPO dictTModel, Map<String, List<DictTFactorPO>> map,
			List<Map<String, String>> settingList) throws Exception {

		String viewName = newDBTableName.toUpperCase();
		String OldviewName = dictTModel.getDbtablename().toUpperCase();
		String status = this.dictDBExecuteService.getGlobalIsmultdb();
		String prefix = DictDBConstants.PREFIX_TABLE;
		if (status.equals("0")) {
			prefix = "";
		}
		Map<String, String> sqlmap;
		try {
			sqlmap = dictDBExecuteService.createViewTriggerForUpdataView(
					viewName, dictTModel, map, settingList,
					dictTModel.getSecusql(), status);
		} catch (Exception e1) {

			e1.printStackTrace();
			throw new Exception(e1.getMessage());
		}
		String viewSql = sqlmap.get(DictDBConstants.VIEWFORUPDATEVIEW);
		String triggerSql = sqlmap.get(DictDBConstants.TRIGGERFORUPDATEVIEW);

		try {
			String ifExistsViewSql_ = DictDBConstants.IF_EXISTS_TYPE_VIEW + "'"
					+ OldviewName + "'";
			Integer r_view_ = dictDBExecuteService
					.getIfExistsInDB(ifExistsViewSql_);// 同名视图
			if (r_view_ == 1) {
				this.dictDBExecuteService.ExecDllLongForParam("DROP VIEW "
						+ OldviewName);
			}
			this.dictDBExecuteService.ExecDllLongForParam(viewSql);
			String ifExistsTriggerSql = DictDBConstants.IF_EXISTS_TYPE_TRIGGER
					+ "'TR_" + prefix + OldviewName + "'";
			Integer r_trigger = dictDBExecuteService
					.getIfExistsInDB(ifExistsTriggerSql);// 同名触发器
			if (r_trigger == 1) {
				this.dictDBExecuteService.ExecDllLongForParam("DROP TRIGGER "
						+ "TR_" + prefix + OldviewName);
			}
			this.dictDBExecuteService.ExecDllLongForParam(triggerSql);
		} catch (Exception e) {
			// roll back

			e.printStackTrace();
			String ifExistsViewSql_ = DictDBConstants.IF_EXISTS_TYPE_VIEW + "'"
					+ viewName + "'";
			Integer r_view_ = dictDBExecuteService
					.getIfExistsInDB(ifExistsViewSql_);// 同名视图
			if (r_view_ == 1) {
				this.dictDBExecuteService.ExecDllLongForParam("DROP VIEW "
						+ viewName);
			}
			String ifExistsTriggerSql = DictDBConstants.IF_EXISTS_TYPE_TRIGGER
					+ "'TR_" + prefix + viewName + "'";
			Integer r_trigger = dictDBExecuteService
					.getIfExistsInDB(ifExistsTriggerSql);// 同名触发器
			if (r_trigger == 1) {
				this.dictDBExecuteService.ExecDllLongForParam("DROP TRIGGER "
						+ "TR_" + prefix + viewName);
			}
			throw new Exception(e.getMessage());
		}

		try {
			dictTModel.setDbtablename(viewName);
			this.updateDictTModel(dictTModel);
		} catch (Exception e) {

			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 创建 备份表 _bak ;
	 */
	@Override
	public void createDictTModelForBak(DictTModelPO dictTModel,
			List<DictTDefaultcolPO> dtdList) throws Exception {

		String status = this.dictDBExecuteService.getGlobalIsmultdb();
		String tableName = dictTModel.getDbtablename().toUpperCase() + "_BAK";
		String ispartition = dictTModel.getIspartition();
		String prefix = DictDBConstants.PREFIX_TABLE;
		if (ispartition.equals("0")) {
			prefix = "";
		}
		if (ispartition == null) {
			ispartition = "0";
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableid", dictTModel.getTableid());
		map.put("isleaf", "1");
		List<DictTFactorPO> factorList = dictTFactorSelfMapper
				.getBakTableColumnList(dictTModel.getTableid());
		Map<String, String> sqlmap = this.dictDBExecuteService
		.createPhysicsTableSql(dtdList, tableName,
						dictTModel.getName(), status, ispartition,
						dictTModel.getSecusql(), true, isTask, false,
						isAllDistrict, isAllYear, factorList);
		if (sqlmap != null && sqlmap.size() > 0) {
			String pKsql = sqlmap.get(DictDBConstants.TYPE_PRIMARY);
			String add_comments = sqlmap
					.get(DictDBConstants.TYPE_TABLE_COMMENTS);
			if ("0".equals(status) || "0".equals(ispartition)) {
				// 单创建物理表
				String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
						+ "'" + prefix + tableName + "'";

				Integer isTableExist = this.dictDBExecuteService
						.getIfExistsInDB(ifExistsTableSql);// 同名物理表
				if (isTableExist == 0) {
					// String ifExistsViewSql =
					// DictDBConstants.IF_EXISTS_TYPE_VIEW + "'"+tableName+"'";
					// Integer r_view =
					// this.dictDBExecuteService.getIfExistsInDB(ifExistsViewSql);//同名视图
					// if(r_view==0){
					try {
						String createTableSql = sqlmap
								.get(DictDBConstants.TYPE_TABLE);
						this.dictDBExecuteService
								.ExecDllLongForParam(createTableSql);
						// +视图
						// String createViewSql =
						// sqlmap.get(DictDBConstants.TYPE_VIEW);
						// this.dictDBExecuteService.ExecDllLongForParam(createViewSql);

						// +触发器
						String ifExistsTriggerSql = DictDBConstants.IF_EXISTS_TYPE_TRIGGER
								+ "'TR_" + prefix + tableName + "'";
						String createTriggerSql = sqlmap
								.get(DictDBConstants.TYPE_TRIGGER);
						Integer isTriggerExist = this.dictDBExecuteService
								.getIfExistsInDB(ifExistsTriggerSql);// 同名触发器
						if (isTriggerExist == 0) {
							this.dictDBExecuteService
									.ExecDllLongForParam(createTriggerSql);
						} else {
							throw new Exception("TR_" + prefix + tableName
									+ " :触发器已经存在.");
						}
						// +主键
						if (pKsql != null && !"".equals(pKsql)) {
							this.dictDBExecuteService
									.ExecDllLongForParam(pKsql);
						}

						// +add_comments
						if (add_comments != null && !"".equals(add_comments)) {
							this.dictDBExecuteService
									.ExecDllLongForParam(add_comments);
						}
						/*
						 * //+索引 if(indexSql!=null&&!"".equals(indexSql)){
						 * this.dictDBExecuteService
						 * .ExecDllLongForParam(indexSql); }
						 */
					} catch (Exception e) {

						e.printStackTrace();
						// roll back

						// String ifExistsViewSql_ =
						// DictDBConstants.IF_EXISTS_TYPE_VIEW +
						// "'"+tableName+"'";
						// Integer r_view_ =
						// dictDBExecuteService.getIfExistsInDB(ifExistsViewSql_);//同名视图
						// if(r_view_==1){
						// dictDBExecuteService.ExecDllLongForParam("DROP VIEW "+
						// tableName);
						// }
						String ifExistsTriggerSql = DictDBConstants.IF_EXISTS_TYPE_TRIGGER
								+ "'TR_" + prefix + tableName + "'";
						Integer r_trigger = dictDBExecuteService
								.getIfExistsInDB(ifExistsTriggerSql);// 同名触发器
						if (r_trigger == 1) {
							dictDBExecuteService
									.ExecDllLongForParam("DROP TRIGGER "
											+ "TR_" + prefix + tableName);
						}
						String ifExistsTable_Sql = DictDBConstants.IF_EXISTS_TYPE_TABLE
								+ "'" + prefix + tableName + "'";
						Integer havetable_ = dictDBExecuteService
								.getIfExistsInDB(ifExistsTable_Sql);// 同名表
						if (havetable_ == 1) {
							dictDBExecuteService
									.ExecDllLongForParam("DROP TABLE " + prefix
											+ tableName + " PURGE ");
						}
						// delete(dictTModel);
						throw new Exception(e.getMessage());
					}
					// }else{
					// throw new Exception(tableName+"物理表（视图）已经存在.");
					// }
				} else {
					throw new Exception(tableName + "表已经存在.");
				}
			} else if ("1".equals(ispartition)
					&& ("1".equals(status) || "2".equals(status))) {
				// 创建物理表 +分区
				String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
						+ "'" + prefix + tableName + "'";
				String createTableSql = sqlmap
						.get(DictDBConstants.TYPE_PARTITION);
				Integer r_table = dictDBExecuteService
						.getIfExistsInDB(ifExistsTableSql);// 同名物理表
				if (r_table == 0) {
					try {
						this.dictDBExecuteService
								.ExecDllLongForParam(createTableSql);

						// +视图
						String ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW
								+ "'" + tableName + "'";
						String createViewSql = sqlmap
								.get(DictDBConstants.TYPE_VIEW);
						Integer isExistView = this.dictDBExecuteService
								.getIfExistsInDB(ifExistsViewSql);// 同名视图
						if (isExistView == 0) {
							this.dictDBExecuteService
									.ExecDllLongForParam(createViewSql);
						} else {
							throw new Exception("" + tableName + " :视图已经存在.");
						}
						// +触发器
						String ifExistsTriggerSql = DictDBConstants.IF_EXISTS_TYPE_TRIGGER
								+ "'TR_" + prefix + tableName + "'";
						String createTriggerSql = sqlmap
								.get(DictDBConstants.TYPE_TRIGGER);
						Integer isExistTrigger = this.dictDBExecuteService
								.getIfExistsInDB(ifExistsTriggerSql);// 同名触发器
						if (isExistTrigger == 0) {
							this.dictDBExecuteService
									.ExecDllLongForParam(createTriggerSql);
						} else {
							throw new Exception("TR_" + prefix + tableName
									+ " :触发器已经存在.");
						}
						// +主键
						if (pKsql != null && !"".equals(pKsql)) {
							this.dictDBExecuteService
									.ExecDllLongForParam(pKsql);
						}
						/*
						 * //+索引 if(indexSql!=null&&!"".equals(indexSql)){
						 * this.dictDBExecuteService
						 * .ExecDllLongForParam(indexSql); }
						 */
					} catch (Exception e) {
						// roll back
						String ifExistsViewSql_ = DictDBConstants.IF_EXISTS_TYPE_VIEW
								+ "'" + tableName + "'";
						Integer r_view_ = dictDBExecuteService
								.getIfExistsInDB(ifExistsViewSql_);// 同名视图
						if (r_view_ == 1) {
							dictDBExecuteService
									.ExecDllLongForParam("DROP VIEW "
											+ tableName);
						}
						String ifExistsTriggerSql = DictDBConstants.IF_EXISTS_TYPE_TRIGGER
								+ "'TR_" + prefix + tableName + "'";
						Integer r_trigger = dictDBExecuteService
								.getIfExistsInDB(ifExistsTriggerSql);// 同名触发器
						if (r_trigger == 1) {
							dictDBExecuteService
									.ExecDllLongForParam("DROP TRIGGER "
											+ "TR_" + prefix + tableName);
						}
						String ifExistsTable_Sql = DictDBConstants.IF_EXISTS_TYPE_TABLE
								+ "'" + prefix + tableName + "'";
						Integer havetable_ = dictDBExecuteService
								.getIfExistsInDB(ifExistsTable_Sql);// 同名表
						if (havetable_ == 1) {
							dictDBExecuteService
									.ExecDllLongForParam("DROP TABLE " + prefix
											+ tableName + " PURGE ");
						}

						e.printStackTrace();
						throw new Exception(e.getMessage());
					}
				} else {
					throw new Exception(prefix + tableName + " :物理表（分区）已经存在.");
				}
			}
			// 记录到DictTModel表
			dictTModel.setIsbak("1");
			this.updateDictTModel(dictTModel);
		}
	}

	/**
	 * 创建 业务对象的备份表 _bak ;
	 * 
	 * @param dictTModel
	 * @throws Exception
	 */
	public void createDictTModelForUpdateViewBak(DictTModelPO dictTModel)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableid", dictTModel.getTableid());
		map.put("isleaf", "1");
		List<DictTFactorPO> factorList = this.dictTFactorMapper
				.findDictTFactor(map);
		// 找到业务对象来源表，现在先只是支持单表
		DictTFactorPO firstDtf = factorList.get(0);
		String fromTableID = firstDtf.getFrmtabid();
		DictTModelPO fromDtm = this.dictTModelService.getDictTModelByID(
				fromTableID, false);
		// 如果来源表没有创建BAK表，报错
		if (!"1".equals(fromDtm.getIsbak())) {
			throw new Exception("业务对象来源表没有创建bak表");
		}
		String tableName = dictTModel.getDbtablename().toUpperCase() + "_BAK";
		String soureTableName = fromDtm.getDbtablename().toUpperCase() + "_BAK";
		Boolean isTask = false;
		if ("1".equals(dictTModel.getIstask())) {
			isTask = true;
		}
		Map<String, List<DictTFactorPO>> viewColumMap = new HashMap<String, List<DictTFactorPO>>();

		// 原有主键
		for (DictTFactorPO dtf : factorList) {
			// //原有视图字段
			List<DictTFactorPO> listFactor = viewColumMap.get(soureTableName);
			if (listFactor == null) {
				listFactor = new ArrayList<DictTFactorPO>();
				listFactor.add(dtf);
				viewColumMap.put(soureTableName, listFactor);
			} else {
				listFactor.add(dtf);
			}
		}
		List<DictTFactorPO> listFactor = viewColumMap.get(soureTableName);
		// 加入bak表特殊字段
		DictTFactorPO dv = new DictTFactorPO();
		dv.setDbcolumnname("BAKVERSION");
		DictTFactorPO du = new DictTFactorPO();
		du.setDbcolumnname("BAKUSERID");
		DictTFactorPO dt = new DictTFactorPO();
		dt.setDbcolumnname("BAKTYPE");
		listFactor.add(dv);
		listFactor.add(du);
		listFactor.add(dt);
		String createViewSql = this.dictDBExecuteService
				.createBakViewForUpdateView(tableName, viewColumMap,
						dictTModel.getSecusql(), isTask);
		if (createViewSql != null && !createViewSql.trim().equals("")) {
			try {
				// // +视图
				// String ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW
				// + "'" + tableName + "'";
				// Integer isViewExist = this.dictDBExecuteService
				// .getIfExistsInDB(ifExistsViewSql);// 同名视图
				// if (isViewExist == 0) {
				this.dictDBExecuteService.ExecDllLongForParam(createViewSql);
				// } else {
				// throw new Exception("" + tableName + " :视图已经存在.");
				// }
				//最后统一调用刷新表视图，避免大集中多分区造成的不一致
				dictDBExecuteService.recreateViews(dictTModel.getTableid());

			} catch (Exception e) {
				// roll back
				String ifExistsViewSql_ = DictDBConstants.IF_EXISTS_TYPE_VIEW
						+ "'" + tableName + "'";
				Integer isViewExist = dictDBExecuteService
						.getIfExistsInDB(ifExistsViewSql_);// 同名视图
				if (isViewExist == 1) {
					dictDBExecuteService.ExecDllLongForParam("DROP VIEW "
							+ tableName);
				}
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
			// 记录到DictTModel表
			dictTModel.setIsbak("1");
			this.updateDictTModel(dictTModel);
		}
	}

	/**
	 * 更改物理表名称 for 物理备份表
	 */
	public void updateTableNameForbak(String newDBTableName,
			DictTModelPO dictTModel) throws Exception {

		String tableName = dictTModel.getDbtablename().toUpperCase() + "_BAK";
		String ispartition = dictTModel.getIspartition();
		String status = this.dictDBExecuteService.getGlobalIsmultdb();
		String prefix = DictDBConstants.PREFIX_TABLE;
		if (ispartition.equals("0")) {
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
		if (newDBTableName != null && !"".equals(newDBTableName)) {
			// 主键 and 视图字段
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableid", dictTModel.getTableid());
			map.put("isleaf", "1");
			/*
			 * List<String> listIndex = new ArrayList<String>();
			 * listIndex.add("BAKVERSION");//索引 if("1".equals(status)){
			 * listIndex.add("YEAR"); // 索引 listIndex.add("STATUS"); //索引 }else
			 * if("2".equals(status)){ listIndex.add("PROVINCE"); // 索引
			 * listIndex.add("YEAR"); // 索引 listIndex.add("STATUS"); //索引 }
			 */
			List<DictTFactorPO> factorList = this.dictTFactorMapper
					.findDictTFactor(map);
			Map<String, List<DictTFactorPO>> viewColumMap = new HashMap<String, List<DictTFactorPO>>();
			// 原有主键
			for (DictTFactorPO dtf : factorList) {
				// //原有视图字段
				if (dtf.getDatatype() != 4) {
					List<DictTFactorPO> listFactor = viewColumMap.get(prefix
							+ newDBTableName);
					if (listFactor == null) {
						listFactor = new ArrayList<DictTFactorPO>();
						listFactor.add(dtf);
						viewColumMap.put(prefix + newDBTableName, listFactor);
					} else {
						listFactor.add(dtf);
						viewColumMap.put(prefix + newDBTableName, listFactor);
					}
					/*
					 * if("1".equals(dtf.getIskey())){//原有主键
					 * listIndex.add(dtf.getDbcolumnname()); }
					 */
					/*
					 * if("DATAKEY".equals(dtf.getDbcolumnname().toUpperCase().trim
					 * ())){ listIndex.add(dtf.getDbcolumnname());//索引 }
					 */
				}
			}
			StringBuilder rename = new StringBuilder();
			// alter table prefix+AABB rename column T to Tttttt;
			rename.append(DictDBConstants.ALTER_TABLE + " ");
			rename.append(prefix + tableName + " ");
			rename.append(DictDBConstants.RENAME + " ");
			rename.append(DictDBConstants.TO + " ");
			/**
			 * 单一物理表
			 */
			if (!("1".equals(ispartition))) {
				rename.append(prefix + newDBTableName);
				// 单物理表
				String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
						+ "'" + prefix + tableName + "'";
				Integer r_table = dictDBExecuteService
						.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
				if (r_table == 1) {
					try {
						this.dictDBExecuteService.ExecDllLongForParam(rename
								.toString());
						// +视图
						// String ifExistsViewSql =
						// DictDBConstants.IF_EXISTS_TYPE_VIEW +
						// "'"+tableName+"'";
						// Integer r_view =
						// dictDBExecuteService.getIfExistsInDB(ifExistsViewSql);//同名视图
						// if(r_view==1){
						// this.dictDBExecuteService.ExecDllLongForParam("DROP VIEW "+
						// tableName);
						// }
						// 更新视图
						// String createViewSql =
						// dictDBExecuteService.createView(ispartition,status,
						// newDBTableName,
						// viewColumMap,dictTModel.getSecusql());
						// this.dictDBExecuteService.ExecDllLongForParam(createViewSql);
					} catch (Exception e) {

						e.printStackTrace();
						throw new Exception(e.getMessage());
					}
				} else {
					throw new Exception("未找到原表：" + "'" + prefix + tableName
							+ "' ");
				}

			} else {
				/**
				 * 区划
				 */
				rename.append(prefix + newDBTableName);
				String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
						+ "'" + prefix + tableName + "'";
				Integer r_table = dictDBExecuteService
						.getIfExistsInDB(ifExistsTableSql);// 同名物理分区表
				if (r_table == 1) {
					try {
						this.dictDBExecuteService.ExecDllLongForParam(rename
								.toString());

						// +视图
						String ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW
								+ "'" + tableName + "'";
						Integer r_view = dictDBExecuteService
								.getIfExistsInDB(ifExistsViewSql);// 同名视图
						if (r_view == 1) {
							this.dictDBExecuteService
									.ExecDllLongForParam("DROP VIEW "
											+ tableName);
						}
						// 更新视图
						String createViewSql = dictDBExecuteService.createView(
								ispartition, status, newDBTableName,
								viewColumMap, dictTModel.getSecusql(), isTask,
								false, isAllDistrict, isAllYear);
						this.dictDBExecuteService
								.ExecDllLongForParam(createViewSql);
						// 如果需要有配置表，同步创建配置表
						if (isConfig) {
							String configViewSql = dictDBExecuteService
									.createView(ispartition, status,
											newDBTableName, viewColumMap,
											dictTModel.getSecusql(), isTask,
											isConfig, isAllDistrict, isAllYear);
							this.dictDBExecuteService
									.ExecDllLongForParam(configViewSql);
						}

						// +触发器
						String ifExistsTriggerSql = DictDBConstants.IF_EXISTS_TYPE_TRIGGER
								+ "'TR_" + prefix + tableName + "'";
						Integer r_trigger = dictDBExecuteService
								.getIfExistsInDB(ifExistsTriggerSql);// 同名触发器
						if (r_trigger == 1) {
							this.dictDBExecuteService
									.ExecDllLongForParam("DROP TRIGGER "
											+ "TR_" + prefix + tableName);
						}
						String createTriggerSql = dictDBExecuteService
								.createTrigger(status, newDBTableName, isTask);
						this.dictDBExecuteService
								.ExecDllLongForParam(createTriggerSql);
						//最后统一调用刷新表视图，避免大集中多分区造成的不一致
						dictDBExecuteService.recreateViews(dictTModel.getTableid());

					} catch (Exception e) {

						e.printStackTrace();
						throw new Exception(e.getMessage());
					}
				} else {
					throw new Exception("未找到分区表：" + "'" + prefix + tableName
							+ "' ");
				}
			}

			StringBuilder ifExistPKSql = new StringBuilder();
			ifExistPKSql.append(DictDBConstants.IF_EXISTS_TYPE_PK);
			ifExistPKSql.append("'PK_" + prefix + tableName + "'");
			ifExistPKSql.append(" AND T.TABLE_NAME = ");
			ifExistPKSql.append("'" + prefix + tableName + "' ");

			Integer r_pk = dictDBExecuteService.getIfExistsInDB(ifExistPKSql
					.toString());//

			List<String> listPK = new ArrayList<String>();
			if (r_pk == 1) {

				// 查找原来PK
				List<Map<String, String>> list = this.dictDBExecuteService
						.getTableColumnPK(prefix + tableName);
				for (Map<String, String> col : list) {
					String dbColumnName = col.get("COLUMN_NAME").toString()
							.toUpperCase();
					listPK.add(dbColumnName);
				}

				// 删除原来的主键
				// alter table prefix+AABB drop constraint PK_prefix+AABB
				// cascade;
				StringBuilder dropPk = new StringBuilder();
				dropPk.append(DictDBConstants.ALTER_TABLE + " ");
				dropPk.append(prefix + tableName + " ");
				dropPk.append(DictDBConstants.DROP + " ");
				dropPk.append(DictDBConstants.CONSTRAINT + " ");
				dropPk.append("PK_" + prefix + tableName + " ");
				dropPk.append(DictDBConstants.CASCADE);
				try {
					this.dictDBExecuteService.ExecDllLongForParam(dropPk
							.toString());
				} catch (Exception e) {

					e.printStackTrace();
					throw new Exception(e.getMessage());
				}
			}
			// 设定新 pk
			if (listPK.size() > 0) {

				try {
					String pksql = this.dictDBExecuteService.createPK(status,
							tableName, listPK);
					this.dictDBExecuteService.ExecDllLongForParam(pksql);
				} catch (Exception e) {

					e.printStackTrace();
					throw new Exception(e.getMessage());
				}
			}

			// 更新index
			/*
			 * if(listIndex.size()>0){ StringBuilder ifExistIndexSql = new
			 * StringBuilder();
			 * ifExistIndexSql.append(DictDBConstants.IF_EXISTS_TYPE_INDEX);
			 * ifExistIndexSql.append("'"+prefix+tableName+"' ");
			 * ifExistIndexSql.append(" AND U.INDEX_NAME = ");
			 * ifExistIndexSql.append("'IN_"+prefix+tableName+"'"); Integer
			 * r_index =
			 * dictDBExecuteService.getIfExistsInDB(ifExistIndexSql.toString
			 * ());// //删除原有index if(r_index==1){ //删除原来的主键 try {
			 * this.dictDBExecuteService
			 * .ExecDllLongForParam("DROP INDEX IN_"+prefix+tableName); } catch
			 * (Exception e) { e.printStackTrace(); throw new
			 * Exception(e.getMessage()); } }
			 * 
			 * //设定新 索引 //create unique index INDEX_AACSWLB1 on prefix+AACSWLB1
			 * (ORDERID, PROVINCE)
			 * 
			 * try { String indexsql =
			 * this.dictDBExecuteService.createIndex(tableName, listIndex);
			 * this.dictDBExecuteService.ExecDllLongForParam(indexsql); } catch
			 * (Exception e) { e.printStackTrace(); throw new
			 * Exception(e.getMessage()); } }
			 */
		}

	}

	/**
	 * 删除 物理表 备份表
	 * 
	 * @param String
	 *            tableid
	 */
	@Override
	public void deleteTableForBak(DictTModelPO dictTModel) throws Exception {

		String tableName = dictTModel.getDbtablename().toUpperCase() + "_BAK";
		String ispartition = dictTModel.getIspartition();
		String prefix = DictDBConstants.PREFIX_TABLE;
		if (ispartition.equals("0")) {
			prefix = "";
		}
		if (tableName != null && !"".equals(tableName)) {
			if (!("1".equals(ispartition))) {
				// +视图
				// String ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW
				// + "'"+tableName+"'";
				// Integer r_view =
				// dictDBExecuteService.getIfExistsInDB(ifExistsViewSql);//同名视图
				// if(r_view==1){
				// try {
				// this.dictDBExecuteService.ExecDllLongForParam("DROP VIEW "+
				// tableName);
				// } catch (Exception e) {

				// e.printStackTrace();
				// throw new Exception(e.getMessage());
				// }
				// }else{
				// throw new Exception(prefix+tableName+" :视图已经存在.");
				// }
				String tbtype = dictTModel.getTabletype();
				if(tbtype.equals("1")) {
					// 单物理表
					String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
						+ "'" + prefix + tableName + "'";
					Integer r_table = dictDBExecuteService
						.getIfExistsInDB(ifExistsTableSql);
					if (r_table == 1) {
						try {
							dictDBExecuteService.ExecDllLongForParam(
								"DROP TABLE " + prefix + tableName + " PURGE ");
						} catch (Exception e) {
							e.printStackTrace();
							throw new Exception(e.getMessage());
						}
					} else {
						// throw new Exception(tableName+"物理表不存在.");
					}
				} else if(tbtype.equals("3")) {
					// 业务对象
					String ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW
						+ "'" + tableName + "'";
					Integer r_view = dictDBExecuteService
						.getIfExistsInDB(ifExistsViewSql);
					if(r_view == 1) {
						try {
							dictDBExecuteService.ExecDllLongForParam(
								"DROP VIEW " + tableName);
						} catch (Exception e) {
							e.printStackTrace();
							throw new Exception(e.getMessage());
						}
					} else {
						// throw new Exception(prefix + tableName + " :视图已经存在.");
					}
				}
			} else {
				/**
				 * 单物理表 分区 视图 触发器
				 */
				// +视图
				String ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW
						+ "'" + tableName + "'";
				Integer r_view = dictDBExecuteService
						.getIfExistsInDB(ifExistsViewSql);// 同名视图
				if (r_view == 1) {
					try {
						this.dictDBExecuteService
								.ExecDllLongForParam("DROP VIEW " + tableName);
					} catch (Exception e) {
						e.printStackTrace();
						throw new Exception(e.getMessage());
					}
				} else {
					// throw new Exception(prefix+tableName+" :视图已经存在.");
				}
				// +触发器
				String ifExistsTriggerSql = DictDBConstants.IF_EXISTS_TYPE_TRIGGER
						+ "'TR_" + prefix + tableName + "'";
				Integer r_trigger = dictDBExecuteService
						.getIfExistsInDB(ifExistsTriggerSql);// 同名触发器
				if (r_trigger == 1) {
					try {
						this.dictDBExecuteService
								.ExecDllLongForParam("DROP TRIGGER " + "TR_"
										+ prefix + tableName);
					} catch (Exception e) {

						e.printStackTrace();
						throw new Exception(e.getMessage());
					}
				} else {
					// throw new Exception("TR_"+prefix+tableName+" :触发器已经存在.");
				}
				// 物理表
				String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
						+ "'" + prefix + tableName + "'";
				Integer r_table = dictDBExecuteService
						.getIfExistsInDB(ifExistsTableSql);
				if (r_table == 1) {
					try {
						this.dictDBExecuteService
								.ExecDllLongForParam("DROP TABLE " + prefix
										+ tableName + " PURGE ");
					} catch (Exception e) {

						e.printStackTrace();
						throw new Exception(e.getMessage());
					}
				} else {
					// throw new Exception(tableName+"物理表不存在.");
				}
			}
		}
	}

	@Override
	public String copyTableDefinitionToModel(String finyear,
			List<Map<String, String>> tableData) {
		StringBuffer message = new StringBuffer("");
		String tableId = "";
		try {

			for (Map<String, String> table : tableData) {
				tableId = table.get("tableid");
				if (this.checkTableIsCopy(tableId, finyear)) {
					message.append(table.get("name") + " : 不能重复复制").append(
							"\n ");
					continue;
				}
				copyTableAndColumnDefinitionToYear(tableId, finyear);
				message.append(table.get("name") + " : 复制成功").append("\n ");
			}
		} catch (Exception e) {
			message = new StringBuffer("");
			message.append("复制失败 ：" + e.getMessage());
		}
		return message.toString();
	}

	private void copyTableAndColumnDefinitionToYear(String tableId,
			String finyear) {
		Map param = new HashMap();
		param.put("tableId", tableId);
		param.put("finyear", finyear);
		dictTModelSelfMapper.copyModelDefineToYear(param);
		dictTFactorSelfMapper.copyFactorDefineToYear(param);
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

	/**
	 * 更新 DictTModelPO 缓存
	 * 
	 * @param dtm
	 * @throws Exception
	 */
	/*
	 * public void updateDictTModelCache(DictTModelPO dictTModel){ Set<String>
	 * set = iCacheService.getKeys(); if(set!=null){ //集合清空 String[] keyStr = {
	 * Constants.CACHE_KEY_MODEL_GETDICTTMODELBYID+"_"+dictTModel.getTableid(),
	 * Constants.CACHE_KEY_MODEL_GETDICTTMODELOFID+"_"+dictTModel.getTableid(),
	 * Constants
	 * .CACHE_KEY_MODEL_GETDICTTMODELBYDBTABLENAME+"_"+dictTModel.getDbtablename
	 * (),
	 * Constants.CACHE_KEY_MODEL_GETDICTTMODELSBYSUITID+"_"+dictTModel.getSuitid
	 * (),
	 * Constants.CACHE_KEY_MODEL_GETDICTTMODELSBYNAME+"_"+dictTModel.getName(),
	 * Constants
	 * .CACHE_KEY_MODEL_GETDICTTMODELSBYTABLETYPE+"_"+dictTModel.getTabletype(),
	 * Constants.CACHE_KEY_MODEL_GETALLDICTTMODELS }; Set<String> keys =
	 * DictDBConstants.findCacheKeyOfIndex(set,keyStr); for(String key : keys){
	 * iCacheService.remove(key); } }
	 * 
	 * }
	 */

	@Override
	public void recoverBakTable(List<Map> needRecoverColsList,
			boolean needInsertColsIntoTable) throws Exception {
		DictTModelPO model = null;
		DictTFactorPO factor = null;
		String tableID = null;
		for (Map recoverCol : needRecoverColsList) {
			tableID = (String)recoverCol.get("TABLE_ID");
			model = this.dictTModelService.getDictTModelByID(
					tableID, false);
			factor = this.dictTFactorService.getDictTFactorByDBColumnName(
					recoverCol.get("COLUMN_NAME").toString(),tableID);
			if (model == null || factor == null) {
				continue;
			}
			try {
				this.dictTFactorSelfService.createDictTFactorForBak(model,
						factor, needInsertColsIntoTable);
				//最后统一调用刷新表视图，避免大集中多分区造成的不一致
				dictDBExecuteService.recreateViews(tableID);
			} catch (Exception e) {
			}
			model = null;
			factor = null;
		}
	}

	/**
	 * 下发功能的实现接口
	 */
	@SuppressWarnings("unused")
	@Override
	public String sendTablesToSubCity(String[] tableIdData, String[] subCityData)
			throws Exception {

		String flag = "{\"error\":\"下发失败！\"}";
		try {
			DictTModelPO model = null;
			String namecn = null;
			String tableDbName = null;
			for (String tableId : tableIdData) {
				model = dictTModelService.getDictTModelByID(tableId, false);
				namecn = model.getName();
				tableDbName = DictDBConstants.PREFIX_TABLE
						+ model.getDbtablename();
				for (String codename : subCityData) {
					String code = codename.substring(codename.indexOf("[") + 1,
							codename.indexOf("]", codename.indexOf("[")));
					Map<Integer, String> maptype = new HashMap<Integer, String>();
					maptype.put(0, DictDBConstants.SYSPADDPARTITIONM);
					maptype.put(1, DictDBConstants.SYSPADDPARTITIONF);
					maptype.put(2, DictDBConstants.SYSPADDPARTITIONS);
					maptype.put(3, DictDBConstants.SYSPADDPARTITIONMC);
					maptype.put(4, DictDBConstants.SYSPADDPARTITIONFC);
					maptype.put(5, tableDbName);
					for (int i = 0; i < maptype.size(); i++) {
						Map<String, String> exemap = new HashMap<String, String>();
						exemap.put("tablename", maptype.get(i));
						exemap.put("code", code);
						this.dictDBExecuteMapper.addPartition(exemap);
					}
					Map<String, Object> param = new HashMap<String, Object>();
					String province = code;
					param.put("tableId", tableId);
					param.put("province", province);
					param.put("isreserve", "1");
					Integer is_insert_factor = 0;
					this.dictTModelSelfMapper.copyModelDefineToProvince(param);
					is_insert_factor = dictTFactorSelfMapper
							.copyFactorDefineToProvince(param);
					this.dictTSuitMapper.copySuitDefineToProvince(param);
					this.dictTModelcodeMapper
							.copyModelCodeDefineToProvince(param);
					this.dictTFactorcodeMapper
							.copyFactorCodeDefineToProvince(param);
					if (is_insert_factor < 1) {
						return flag = "{\"error\":\"业务表[" + namecn + "]已经下发过"
								+ codename + "，请重新选择地区！\"}";
					} else {
						flag = "{\"error\":\"下发成功！\"}";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = "{\"error\":\"下发失败！\"}";
		}
		return flag;
	}

	/**
	 * 得到平台数据元树
	 * 
	 * @return 平台数据元树
	 */
	public List<DictElementPO> getDataElementTreeNodes() {
		// 数据元 ------平台接口
		Map<String, String> remap = new HashMap<String, String>();
		List<DictElementPO> list = new ArrayList<DictElementPO>();
		try {
			IDataElementTypeService dataElementTypeService = (IDataElementTypeService) ServiceFactory
					.getBean("fasp2.dic.dataelement.type.service");
			IDataElementService dataElementService = (IDataElementService) ServiceFactory
					.getBean("fasp2.dic.dataelement.service");
			List<DataElementTypeDTO> dataElementTypeList = dataElementTypeService
					.getAllDataElementType();
			List<DataElementDTO> dataElementList = dataElementService
					.getAllDataElement();
			for (DataElementTypeDTO dataElementType : dataElementTypeList) {
				DictElementPO dictElementTreeNode = new DictElementPO();
				dictElementTreeNode.setId(dataElementType.getGuid());
				dictElementTreeNode.setName(dataElementType.getName());
				dictElementTreeNode.setpId(dataElementType.getSuperguid());
				list.add(dictElementTreeNode);
			}
			for (DataElementDTO dataElement : dataElementList) {
				DictElementPO dictElementTreeNode = DictChangeDtoForSYNC
						.changeDictElementTreeNode(dataElement);
				list.add(dictElementTreeNode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/**
	 * 根据来源表ID取到业务对象来源表列
	 * 
	 * @param map
	 *            参数Map
	 * @return 来源表列集合
	 */
	public List<DictTFactorPO> getDictTFactorByFromTableID(
			Map<String, String> map) {
		return dictTUpdateviewMapper.getDictTFactorByFromTableID(map);
	}

	/**
	 * 
	 * 复制创建业务表
	 * 
	 * @param sourceTableID
	 *            源表ID
	 * @param dtm
	 *            新建业务表对象
	 * 
	 */
	@Transactional(rollbackFor = Exception.class)
	public void replicateDictTModelForPhysics(String sourceTableID,
			DictTModelPO dtm) throws Exception {
		if (dtm.getDealtype() != null && !"".equals(dtm.getDealtype())) {
			dtm.setTableid("");
			dtm.setIsbak("0");
			try {
				// 取到缺省列
				Map<String, Object> parameterMap = new HashMap<String, Object>();
				parameterMap.put("dealid", dtm.getDealtype());

				List<DictTDefaultcolPO> defaultColList = dictTDefaultcolMapper
						.findDictTDefaultcol(parameterMap);
				if (defaultColList != null && defaultColList.size() > 0) {
					String tableName = dtm.getDbtablename().toUpperCase();
					// 1物理表
					String status = dictDBExecuteService.getGlobalIsmultdb();
					String ispartition = dtm.getIspartition();
					String prefix = DictDBConstants.PREFIX_TABLE;
					if (status.equals("0")) {
						prefix = "";
					}
					if (ispartition == null) {
						ispartition = "0";
					}
					Boolean isTask = false;
					if ("1".equals(dtm.getIstask())) {
						isTask = true;
					}
					boolean isConfig = false;
					// 得到处理类型
					String dealTypeID = dtm.getDealtype();
					// 通过处理类型得到是否需要配置视图
					DictTDealtypePO dictTDealtype = dictTDealtypeService
							.getDictTDealtype(dealTypeID, dtm.getAppid());
					if (dictTDealtype != null) {
						if ("1".equals(dictTDealtype.getNeedconfig())) {
							isConfig = true;
						}
					}
					Map<String, DictTDefaultcolPO> defaultColMap = new HashMap<String, DictTDefaultcolPO>();
					// 记录下缺省列，判断视图是否包含全部缺省列，同时列数据类型应该一致
					for (DictTDefaultcolPO dictTDefaultcolPO : defaultColList) {
						defaultColMap.put(dictTDefaultcolPO.getDbcolumnname()
								.trim().toUpperCase(), dictTDefaultcolPO);
					}
					List<DictTFactorPO> sourceFactorList = dictTFactorService
							.getLeafFactorListByTableID(sourceTableID);

					if (sourceFactorList == null || sourceFactorList.size() < 1) {
						throw new SQLException("无法找到来源表登记列信息,请重新选择.");
					}

					for (DictTFactorPO sourceDictTFactor : sourceFactorList) {
						// 如果和缺省列一致,移除缺省列
						if (defaultColMap.containsKey(sourceDictTFactor
								.getDbcolumnname().toUpperCase())) {
							// 移除
							defaultColMap.remove(sourceDictTFactor
									.getDbcolumnname().toUpperCase());
						}
					}
					// 如果缺省列还存在，不能创建视图
					if (defaultColMap != null && defaultColMap.size() > 0) {
						StringBuffer defectColumnInfo = new StringBuffer(
								"复制来源表中缺少该表处理类型下的以下缺省列：");
						for (Iterator<DictTDefaultcolPO> iterator = defaultColMap
								.values().iterator(); iterator.hasNext();) {
							DictTDefaultcolPO defaultCol = iterator.next();
							defectColumnInfo.append(defaultCol.getName())
									.append("[")
									.append(defaultCol.getDbcolumnname())
									.append("]  ");

						}
						defectColumnInfo.append("不能登记,请重新选择处理类型.");
						throw new Exception(defectColumnInfo.toString());
					}

					try {
						if ("0".equals(status) || !"1".equals(ispartition)) {
							// 单创建物理表
							String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
									+ "'" + prefix + tableName + "'";
							Integer isTableExist = this.dictDBExecuteService
									.getIfExistsInDB(ifExistsTableSql);// 同名物理表
							if (isTableExist == 1) {
								throw new SQLException(tableName + "物理表已经存在.",
										"CREATETABLE");
							}

						} else if ("1".equals(ispartition)
								&& ("1".equals(status) || "2".equals(status))) {
							// 创建物理表 +分区
							String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
									+ "'" + prefix + tableName + "'";
							Integer isTableExist = dictDBExecuteService
									.getIfExistsInDB(ifExistsTableSql);// 同名物理表
							if (isTableExist == 1) {
								throw new SQLException(prefix + tableName
										+ " :物理表（分区）已经存在.", "CREATETABLE");
							}
							// +视图
							String ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW
									+ "'" + tableName + "'";
							Integer isViewExist = this.dictDBExecuteService
									.getIfExistsInDB(ifExistsViewSql);// 同名视图
							if (isViewExist == 1) {
								throw new Exception("" + tableName
										+ " :视图已经存在.");
							}
							// 如果有配置视图，创建
							if (isConfig) {
								// +配置视图
								ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW
										+ "'"
										+ tableName
										+ DictDBConstants.SUFFIX_CONFIG_VIEW
										+ "'";
								isViewExist = this.dictDBExecuteService
										.getIfExistsInDB(ifExistsViewSql);// 同名视图
								if (isViewExist == 1) {
									throw new Exception(
											""
													+ tableName
													+ DictDBConstants.SUFFIX_CONFIG_VIEW
													+ " :视图已经存在.");
								}
							}
						}
						// 开始复制
						dictTModelSelfMapper.replicateTable(sourceTableID, dtm
								.getName(), dtm.getDbtablename().toUpperCase(),
								dtm.getDealtype(), dtm.getSuitid());
						dictTModelSelfMapper.updateIsTaskByDBtableName(
								dtm.getIstask(), dtm.getDbtablename()); // 更新是否任务表
						dtm = dictTModelService.getDictTModelByDBtableName(
								dtm.getDbtablename(), true);
						// 刷新视图
						dictDBExecuteService.recreateViews(dtm.getTableid());
						// 清除所有业务表类缓存
						String[] allDICTKeys = { DictCacheKey.CACHE_KEY_DICT };
						dataCacheService.put(allDICTKeys, null);
						// 平台注册
						registerDicTable(dtm);
					} catch (Exception e) {
						e.printStackTrace();
						if (e instanceof SQLException
								&& ((SQLException) e).getSQLState().equals(
										"CREATETABLE")) {
							throw e;

						} else {
							// 直接删除已经登记信息
							dictDBExecuteService
									.ExecDllLongForParam("DELETE DICT_T_MODEL WHERE TABLEID='"
											+ dtm.getTableid() + "'");
							dictDBExecuteService
									.ExecDllLongForParam("DELETE DICT_T_FACTOR WHERE TABLEID='"
											+ dtm.getTableid() + "'");
							// DDL级别回滚
							String ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW
									+ "'" + tableName + "'";
							Integer isViewExist = dictDBExecuteService
									.getIfExistsInDB(ifExistsViewSql);// 同名视图
							if (isViewExist == 1) {
								dictDBExecuteService
										.ExecDllLongForParam("DROP VIEW "
												+ tableName);
							}
							// 如果有配置视图,同步删除
							if (isConfig) {
								ifExistsViewSql = DictDBConstants.IF_EXISTS_TYPE_VIEW
										+ "'"
										+ tableName
										+ DictDBConstants.SUFFIX_CONFIG_VIEW
										+ "'";
								isViewExist = dictDBExecuteService
										.getIfExistsInDB(ifExistsViewSql);// 同名视图
								if (isViewExist == 1) {
									dictDBExecuteService
											.ExecDllLongForParam("DROP VIEW "
													+ tableName
													+ DictDBConstants.SUFFIX_CONFIG_VIEW);
								}
							}
							String ifExistsTriggerSql = DictDBConstants.IF_EXISTS_TYPE_TRIGGER
									+ "'TR_" + prefix + tableName + "'";
							Integer isTriggerExist = dictDBExecuteService
									.getIfExistsInDB(ifExistsTriggerSql);// 同名触发器
							if (isTriggerExist == 1) {
								dictDBExecuteService
										.ExecDllLongForParam("DROP TRIGGER "
												+ "TR_" + prefix + tableName);
							}
							String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE
									+ "'" + prefix + tableName + "'";
							Integer isTableExist = dictDBExecuteService
									.getIfExistsInDB(ifExistsTableSql);// 同名表
							if (isTableExist == 1) {
								dictDBExecuteService
										.ExecDllLongForParam("DROP TABLE "
												+ prefix + tableName
												+ " PURGE ");
							}
							throw e;
						}
					}
				} else {
					throw new Exception("未找到缺省列信息");
				}
			} catch (Exception e) {
				// 删除平台登记信息
				deleteRegisterDicTable(dtm.getDbtablename());
				e.printStackTrace();
				throw new Exception(e.getMessage().replace("\"", "\'"));
			}
		}
	}

	/**
	 * 增加物理表列注释
	 * 
	 * @param dictTModel 物理表对象
	 * @throws Exception
	 */
	public void addColumnComment4Table(DictTModelPO dictTModel)
			throws Exception {
		// 物理表才需要处理
		if ("1".equals(dictTModel.getTabletype())) {
			try {
				List<DictTFactorPO> dictFactorList = dictTFactorMapper
						.getLeafFactorListByTableID(dictTModel.getTableid());
				StringBuffer excuteSql = new StringBuffer("begin ");
				String status = dictDBExecuteService.getGlobalIsmultdb();
				String prefix = DictDBConstants.PREFIX_TABLE;
				if (status.equals("0")) {
					prefix = "";
				}
				int realColumnCount = 0;
				for (DictTFactorPO dictTFactorPO : dictFactorList) {
					// 不是标题和绑定列
					if (dictTFactorPO.getDatatype() != 4
							&& !"1".equals(dictTFactorPO.getIsbandcol())) {
						excuteSql
								.append(" EXECUTE IMMEDIATE Q'{ comment on column ")
								.append(prefix)
								.append(dictTModel.getDbtablename())
								.append(".")
								.append(dictTFactorPO.getDbcolumnname())
								.append(" is '")
								.append(dictTFactorPO.getName().replaceAll("}",
										"")).append("'}';");
						realColumnCount++;
					}
				}
				excuteSql.append(" end;");
				if (realColumnCount > 0) {
					dictDBExecuteService.ExecDllLongForParam(excuteSql
							.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("增加列注释出错。");
			}
		}
	}
}

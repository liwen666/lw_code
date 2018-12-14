package commons.dict.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dao.UtilsMapper;
import com.tjhq.commons.dict.dao.DictDBExecuteMapper;
import com.tjhq.commons.dict.dao.PubTPartitionDividMapper;
import com.tjhq.commons.dict.external.dao.DictTFactorMapper;
import com.tjhq.commons.dict.external.dao.DictTFactorcodeMapper;
import com.tjhq.commons.dict.external.dao.DictTModelMapper;
import com.tjhq.commons.dict.external.dao.DictTModelcodeMapper;
import com.tjhq.commons.dict.external.po.ConsistencyPO;
import com.tjhq.commons.dict.external.po.DictTDefaultcolPO;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTFactorcodePO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.po.PubTPartitionDividPO;
import com.tjhq.commons.dict.service.IDictDBExecuteService;
import com.tjhq.commons.dict.util.DictDBConstants;
import com.tjhq.commons.setting.input.dao.ConditionSetMapper;
import com.tjhq.commons.setting.input.po.ConditionSetPo;

/**
 * 创建 表 sql
 * 
 * @author xujingsi
 * 
 */
@Service
@Transactional(readOnly = true)
public class DictDBExecuteService implements IDictDBExecuteService {

	@Resource
	private PubTPartitionDividMapper partitionDividMapper;

	@Resource
	private DictDBExecuteMapper dictDBExecuteMapper;

	@Resource
	private DictTFactorcodeMapper dictTFactorcodeMapper;

	@Resource
	private DictTModelcodeMapper dictTModelcodeMapper;

	@Resource
	private DictTModelMapper dictTModelMapper;

	@Resource
	private DictTFactorMapper dictTFactorMapper;

	@Resource
	private UtilsMapper utilsMapper;

	@Resource
	private ConditionSetMapper conditionSetMapper;

	/**
	 * 创建物理表SQL
	 * 
	 * @param dtdList
	 *            缺省列列表
	 * @param tableName
	 *            物理表名
	 * @param name
	 *            中文表名
	 * @param dbStatus
	 *            分区状态
	 * @param ispartition
	 *            是否分区
	 * @param secusql
	 *            过滤SQL
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> createPhysicsTablecodeSql(
			List<DictTDefaultcolPO> dtdList, String tableName, String name,
			String dbStatus, String ispartition, String secusql) throws Exception {
		
		Map<String, String> sqlMap = null;
		StringBuilder sb = new StringBuilder();
		String prefix = DictDBConstants.PREFIX_TABLE;
		if (ispartition.equals("0")) {
			prefix = "";
		}
		try {
			// 物理主键
			List<String> listPK = new ArrayList<String>();
			// 视图字段
			Map<String, List<DictTFactorcodePO>> viewColumMap = new HashMap<String, List<DictTFactorcodePO>>();
			// 索引
			if (dtdList != null && dtdList.size() > 0) {
				sqlMap = new HashMap<String, String>();
				sb.append(DictDBConstants.CREAT_TABLE + " ");
				sb.append(prefix + tableName + " ");
				sb.append("( ");
				if (dbStatus.equals("1")) {
					sb.append(" PROVINCE VARCHAR2(9),");
				} else if (dbStatus.equals("2")) {
					sb.append(" PROVINCE VARCHAR2(9)  "
							+ DictDBConstants.NOT_NULL + ",");
				}
				if (!dbStatus.equals("0")) {
					// sb.append(" PROVINCE VARCHAR2(9)  "+DictDBConstants.NOT_NULL+",");
					sb.append(" YEAR CHAR(4)  " + DictDBConstants.NOT_NULL
							+ ",");
					sb.append(" STATUS CHAR(1) DEFAULT '1',");
				}
//				if (isTask) {
//					sb.append(" TASKID VARCHAR2(32),");
//				}
				sb.append(" DBVERSION  TIMESTAMP DEFAULT SYSDATE,");
				if ("1".equals(dbStatus)) {
					
					listPK.add("YEAR"); // 主键
					listPK.add("STATUS"); // 主键
					
					/*
					 * listIndex.add("YEAR"); // 索引 listIndex.add("STATUS"); //
					 * 索引
					 */
				} else if ("2".equals(dbStatus)) {
					
					listPK.add("PROVINCE"); // 主键
					listPK.add("YEAR"); // 主键
					listPK.add("STATUS"); // 主键
					
				}
				Map<String, String> hasPKmap = new HashMap<String, String>();
				for (DictTDefaultcolPO dtd : dtdList) {
					String dbcolumnname = dtd.getDbcolumnname();
					if (dbcolumnname != null)
						dbcolumnname = dbcolumnname.toUpperCase().trim();
					if ("DATAKEY".equals(dbcolumnname)
							|| "AGENCYID".equals(dbcolumnname)) {
						hasPKmap.put(dbcolumnname, dbcolumnname);
					}
				}
				boolean hasDatakey = false;
				boolean hasAgencyID = false;
					for (DictTDefaultcolPO dtd : dtdList) {
						if (!"YEAR".equals(dtd.getDbcolumnname().toUpperCase()
								.trim())
								&& !"PROVINCE".equals(dtd.getDbcolumnname()
										.toUpperCase().trim())
										&& !"STATUS".equals(dtd.getDbcolumnname()
												.toUpperCase().trim())
												&& !"DBVERSION".equals(dtd.getDbcolumnname()
														.toUpperCase().trim())) {

							sb.append(dtd.getDbcolumnname() + " ");
							List<DictTFactorcodePO> listFactor = viewColumMap
									.get(prefix + tableName);
							if (listFactor == null) {
								listFactor = new ArrayList<DictTFactorcodePO>();
								DictTFactorcodePO dtf = new DictTFactorcodePO();
								dtf.setDbcolumnname(dtd.getDbcolumnname()
										.toUpperCase());
								listFactor.add(dtf);
								viewColumMap
								.put(prefix + tableName, listFactor);
							} else {
								DictTFactorcodePO dtf = new DictTFactorcodePO();
								dtf.setDbcolumnname(dtd.getDbcolumnname()
										.toUpperCase());
								listFactor.add(dtf);
								viewColumMap
								.put(prefix + tableName, listFactor);
							}
							
							// plevel varchar2(3) generated always as("")
							// virtual
							
							Integer type = dtd.getDatatype();
							if (type == 3) {
								if (dtd.getDatalength() == 1) {
									sb.append("CHAR ( ");
								} else {
									sb.append(DictDBConstants.dataType
											.get(type) + " ( ");
								}
							} else if (type == 7) {
								sb.append(" VARCHAR2 ( ");
							} else {
								sb.append(DictDBConstants.dataType.get(type)
										+ " ( ");
							}
							if (type == 7) {
								sb.append("4000");
							} else {
								sb.append(dtd.getDatalength());
							}
							
							if (dtd.getDatatype() == 1
									|| dtd.getDatatype() == 2) {
								if (dtd.getScale() != null
										&& !"".equals(dtd.getScale())) {
									sb.append("," + dtd.getScale());
								}
							}
							sb.append(" ) ");
							if (dtd.getDefaultvalue() != null
									&& !"".equals(dtd.getDefaultvalue())) {
								sb.append(DictDBConstants.DEFAULT + " "
										+ dtd.getDefaultvalue() + " ");
							}
							
							sb.append(",");
							
							if ("DATAKEY".equals(dtd.getDbcolumnname()
									.toUpperCase().trim())) {
								hasDatakey = true;
							}
							if ("AGENCYID".equals(dtd.getDbcolumnname()
									.toUpperCase().trim())) {
								hasAgencyID = true;
							}
						}
					}
					String sbsql = sb.substring(0, sb.length() - 1);
					sb.setLength(0);
					sb.append(sbsql);
					sb.append(") \n");
//				}
				
				if (hasAgencyID) {
					listPK.add("AGENCYID"); // 主键
				}
				if (hasDatakey) {
					listPK.add("DATAKEY"); // 主键
				}
				String add_comments = DictDBConstants.ADD_COMMENTS + "  "
						+ prefix + tableName + " " + DictDBConstants.IS + " '"+ name + "' ";
				sqlMap.put(DictDBConstants.TYPE_TABLE_COMMENTS, add_comments);
				if ("0".equals(dbStatus) || "0".equals(ispartition)) {
					/**
					 * ******************************创建物理表sql
					 * ********************************************
					 */
					sqlMap.put(DictDBConstants.TYPE_TABLE, sb.toString());
					
				} else if (("1".equals(dbStatus) || "2".equals(dbStatus)) && "1".equals(ispartition)) {
					
					/**
					 * ******************************创建分区表sql
					 * ********************************************
					 */
					String partitionSql = createPartition(dbStatus, tableName);
					sqlMap.put(DictDBConstants.TYPE_PARTITION, sb.toString()+ partitionSql);
					
				}
				
				/**
				 * ******************************创建视图 sql
				 * ********************************************
				 */
				String viewSql = createFactorcodeView(ispartition, dbStatus, tableName,
						viewColumMap, secusql,  false);
				sqlMap.put(DictDBConstants.TYPE_VIEW, viewSql);
				
				// 如果需要有配置表，同步创建配置表
//				if (isConfig) {
//					String configViewSql = createView(ispartition, dbStatus,
//							tableName, viewColumMap, secusql, isTask, isConfig,isAllDistrict, isAllYear);
//					sqlMap.put(DictDBConstants.TYPE_CFG_VIEW, configViewSql);
//					// 如果配置表的，默认加上temple需要索引加快速度
//					String sqlIndex = new StringBuffer("CREATE INDEX  IDX_" + prefix + tableName + "_F "+DictDBConstants.ON + " ")
//					.append(prefix + tableName+ " (TEMPLATEID, ISTEMPLATE)").toString();
//					sqlMap.put(DictDBConstants.TYPE_INDEX, sqlIndex);
//				}
				
				/**
				 * ******************************创建触发器 sql
				 * ********************************************
				 */
				String triggerSql = createTrigger(dbStatus, tableName, false);
				sqlMap.put(DictDBConstants.TYPE_TRIGGER, triggerSql);
				
				/**
				 * ******************************创建设定主键 sql
				 * ********************************************
				 */
				String sqlPk = createPK(dbStatus, tableName, listPK);
				sqlMap.put(DictDBConstants.TYPE_PRIMARY, sqlPk);
				
				// -------------------------------------------------------
				// _bak-----------------------------
				
				// if(_bak){
				/**
				 * ******************************创建索引sql
				 * ***********************************************
				 */
				// String sqlIndex = createIndex(tableName, listIndex);
				// sqlMap.put(DictDBConstants.TYPE_INDEX, sqlIndex);
				// }
				
			}
		} catch (Exception e) {
			throw e;
		}
		return sqlMap;
	}
	/**
	 * 创建物理表SQL
	 * 
	 * @param dtdList
	 *            缺省列列表
	 * @param tableName
	 *            物理表名
	 * @param name
	 *            中文表名
	 * @param dbStatus
	 *            分区状态
	 * @param ispartition
	 *            是否分区
	 * @param secusql
	 *            过滤SQL
	 * @param _bak
	 *            是否已经生成Bak表
	 * @param isTask
	 *            是否任务表
	 * @param isConfig
	 *            是否需要配置表
	 * @param isAllDistrict
	 *            是否全地区访问
	 * @param factorList
	 *            物理列列表
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> createPhysicsTableSql(
			List<DictTDefaultcolPO> dtdList, String tableName, String name,
			String dbStatus, String ispartition, String secusql, boolean _bak,
			boolean isTask, boolean isConfig, boolean isAllDistrict, boolean isAllYear,
			List<DictTFactorPO> factorList) throws Exception {

		Map<String, String> sqlMap = null;
		StringBuilder sb = new StringBuilder();
		String prefix = DictDBConstants.PREFIX_TABLE;
		if (ispartition.equals("0")) {
			prefix = "";
		}
		try {
			// 物理主键
			List<String> listPK = new ArrayList<String>();
			// 视图字段
			Map<String, List<DictTFactorPO>> viewColumMap = new HashMap<String, List<DictTFactorPO>>();
			// 索引
			// List<String> listIndex = new ArrayList<String>();

			if (dtdList != null && dtdList.size() > 0) {
				sqlMap = new HashMap<String, String>();
				sb.append(DictDBConstants.CREAT_TABLE + " ");
				sb.append(prefix + tableName + " ");
				sb.append("( ");
				if (dbStatus.equals("1")) {
					sb.append(" PROVINCE VARCHAR2(9),");
				} else if (dbStatus.equals("2")) {
					sb.append(" PROVINCE VARCHAR2(9)  "
							+ DictDBConstants.NOT_NULL + ",");
				}
				if (!dbStatus.equals("0")) {
					// sb.append(" PROVINCE VARCHAR2(9)  "+DictDBConstants.NOT_NULL+",");
					sb.append(" YEAR CHAR(4)  " + DictDBConstants.NOT_NULL
							+ ",");
					sb.append(" STATUS CHAR(1) DEFAULT '1',");
				}
				if (isTask) {
					sb.append(" TASKID VARCHAR2(32),");
				}
				sb.append(" DBVERSION  TIMESTAMP DEFAULT SYSDATE,");
				if ("1".equals(dbStatus)) {

					listPK.add("YEAR"); // 主键
					listPK.add("STATUS"); // 主键

					/*
					 * listIndex.add("YEAR"); // 索引 listIndex.add("STATUS"); //
					 * 索引
					 */
				} else if ("2".equals(dbStatus)) {

					listPK.add("PROVINCE"); // 主键
					listPK.add("YEAR"); // 主键
					listPK.add("STATUS"); // 主键

					/*
					 * listIndex.add("PROVINCE"); //索引 listIndex.add("YEAR"); //
					 * 索引 listIndex.add("STATUS"); // 索引
					 */
				}
				if (isTask) {
					listPK.add("TASKID");
				}

				Map<String, String> hasPKmap = new HashMap<String, String>();
				for (DictTDefaultcolPO dtd : dtdList) {
					String dbcolumnname = dtd.getDbcolumnname();
					if (dbcolumnname != null)
						dbcolumnname = dbcolumnname.toUpperCase().trim();
					if ("DATAKEY".equals(dbcolumnname)
							|| "AGENCYID".equals(dbcolumnname)) {
						hasPKmap.put(dbcolumnname, dbcolumnname);
					}
				}
				boolean hasDatakey = false;
				boolean hasAgencyID = false;
				if (_bak) {
					// -------------------------------------------------------------------------bak------------------------------------------------
					sb.append(" BAKVERSION  NUMBER(5) DEFAULT '0',");
					sb.append(" BAKUSERID  VARCHAR2(32),");
					//增加备份类型
					sb.append(" BAKTYPE  CHAR(1) DEFAULT '0',");
					listPK.add("BAKVERSION"); // 主键
					listPK.add("BAKTYPE");
					List<DictTFactorPO> listFactor = viewColumMap.get(prefix
							+ tableName);
					if (listFactor == null) { // 视图
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
					// listIndex.add("BAKVERSION");//索引

					// Map<String,String> map = new HashMap<String, String>();
					// //去重复

					/*
					 * for(DictTDefaultcolPO dtd:dtdList){
					 * if("1".equals(dtd.getIsprimary())){ if
					 * (!"YEAR".equals(dtd
					 * .getDbcolumnname().toUpperCase().trim()) &&
					 * !"PROVINCE".equals
					 * (dtd.getDbcolumnname().toUpperCase().trim()) &&
					 * !"STATUS".
					 * equals(dtd.getDbcolumnname().toUpperCase().trim()) &&
					 * !"BAKVERSION"
					 * .equals(dtd.getDbcolumnname().toUpperCase().trim()) &&
					 * !"BAKUSERID"
					 * .equals(dtd.getDbcolumnname().toUpperCase().trim()) &&
					 * !"DBVERSION"
					 * .equals(dtd.getDbcolumnname().toUpperCase().trim())) {
					 * sb.append(dtd.getDbcolumnname() +" "); //plevel
					 * varchar2(3) generated always as("") virtual Integer type
					 * = dtd.getDatatype(); if(type==3){
					 * if(dtd.getDatalength()==1){ sb.append("CHAR ( "); }else{
					 * sb.append(DictDBConstants.dataType.get(type) +" ( "); }
					 * }else if(type==7){ sb.append(" VARCHAR2 ( "); }else{
					 * sb.append(DictDBConstants.dataType.get(type) +" ( "); }
					 * if(type==7){ sb.append("4000"); }else{
					 * sb.append(dtd.getDatalength()); }
					 * 
					 * if(dtd.getDatatype()==1||dtd.getDatatype()==2){
					 * if(dtd.getScale()!=null&&!"".equals(dtd.getScale())){
					 * sb.append(","+dtd.getScale()); } } sb.append(" ) ");
					 * if(dtd
					 * .getDefaultvalue()!=null&&!"".equals(dtd.getDefaultvalue
					 * ())){ sb.append(DictDBConstants.DEFAULT+" "
					 * +dtd.getDefaultvalue()+" "); }
					 * if("1".equals(dtd.getIsprimary())){
					 * sb.append(DictDBConstants.NOT_NULL); } sb.append(",");
					 * 
					 * List<DictTFactorPO> listFactors =
					 * viewColumMap.get(prefix+tableName);
					 * if(listFactors==null){ listFactors = new
					 * ArrayList<DictTFactorPO>(); DictTFactorPO d = new
					 * DictTFactorPO();
					 * d.setDbcolumnname(dtd.getDbcolumnname().toUpperCase());
					 * listFactors.add(d); viewColumMap.put(prefix+tableName,
					 * listFactors); }else{ DictTFactorPO d = new
					 * DictTFactorPO();
					 * d.setDbcolumnname(dtd.getDbcolumnname().toUpperCase());
					 * listFactors.add(d); viewColumMap.put(prefix+tableName,
					 * listFactors); }
					 * map.put(dtd.getDbcolumnname().toUpperCase(),
					 * dtd.getDbcolumnname().toUpperCase());
					 * //listPK.add(dtd.getDbcolumnname());
					 * if("DATAKEY".equals(dtd
					 * .getDbcolumnname().toUpperCase().trim
					 * ())&&hasPKmap.get(dtd
					 * .getDbcolumnname().toUpperCase().trim())!=null){
					 * hasDatakey = true; }
					 * if("AGENCYID".equals(dtd.getDbcolumnname
					 * ().toUpperCase().trim
					 * ())&&hasPKmap.get(dtd.getDbcolumnname
					 * ().toUpperCase().trim())!=null){ hasAgencyID = true; }
					 * 
					 * }else{ throw new Exception(
					 * "不允许出现'YEAR','PROVINCE','STATUS','DBVERSION','BAKVERSION','BAKUSERID'等字段."
					 * ); } } }
					 */
					if (factorList != null) {
						for (int i = 0; i < factorList.size(); i++) {
							DictTFactorPO dtf = factorList.get(i);
							if (!"YEAR".equals(dtf.getDbcolumnname()
									.toUpperCase().trim())
									&& !"PROVINCE".equals(dtf.getDbcolumnname()
											.toUpperCase().trim())
									&& !"STATUS".equals(dtf.getDbcolumnname()
											.toUpperCase().trim())
									&& !"BAKVERSION".equals(dtf
											.getDbcolumnname().toUpperCase()
											.trim())
									&& !"BAKUSERID".equals(dtf
											.getDbcolumnname().toUpperCase()
											.trim())
									&& !"DBVERSION".equals(dtf
											.getDbcolumnname().toUpperCase()
											.trim())
									&& !"BAKTYPE".equals(dtf
											.getDbcolumnname().toUpperCase()
											.trim())) {
								if (isTask
										&& "TASKID".equals(dtf
												.getDbcolumnname()
												.toUpperCase().trim())) {
									throw new Exception("任务表不允许出现'TASKID'字段.");
								}
								if (dtf.getDatatype() != 4
										&& !"1".equals(dtf.getIsbandcol())) {// 不是标题
																				// 不是绑定列
									sb.append(dtf.getDbcolumnname() + " ");
									// plevel varchar2(3) generated always
									// as("") virtual
									Integer type = dtf.getDatatype();
									if (type == 3) {
										if (dtf.getDatalength() == 1) {
											sb.append("CHAR ( ");
										} else {
											sb.append(DictDBConstants.dataType
													.get(type) + " ( ");
										}
									} else if (type == 7) {
										sb.append(" VARCHAR2 ( ");
									} else {
										sb.append(DictDBConstants.dataType
												.get(type) + " ( ");
									}
									if (type == 7) {
										sb.append("4000");
									} else {
										sb.append(dtf.getDatalength());
									}

									if (dtf.getDatatype() == 1
											|| dtf.getDatatype() == 2) {
										if (dtf.getScale() != null
												&& !"".equals(dtf.getScale())) {
											sb.append("," + dtf.getScale());
										}
									}
									sb.append(" ) ");
									// 虚列
									if ("1".equals(dtf.getIsvirtual())) {
										// plevel varchar2(3) generated always
										// as() virtual
										sb.append(DictDBConstants.GENERATED
												+ " ");
										sb.append(DictDBConstants.ALWAYS + " ");
										sb.append(DictDBConstants.AS + " (");
										sb.append(dtf.getVircontext() + " )");
										sb.append(DictDBConstants.VIRTUAL + " ");
									} else {
										if (dtf.getDefaultvalue() != null
												&& !"".equals(dtf
														.getDefaultvalue())) {
											sb.append(DictDBConstants.DEFAULT
													+ " "
													+ dtf.getDefaultvalue()
													+ " ");
										}
									}
									/*
									 * if("1".equals(dtf.getIskey())||!"1".equals
									 * (dtf.getNullable())){
									 * sb.append(DictDBConstants.NOT_NULL);
									 * //if("1".equals(dtf.getIskey())){ //
									 * listIndex
									 * .add(dtf.getDbcolumnname().toUpperCase
									 * ());//索引 字段 //} }
									 */
									sb.append(",");
									if ("DATAKEY".equals(dtf.getDbcolumnname()
											.toUpperCase().trim())
											&& hasPKmap.get(dtf
													.getDbcolumnname()
													.toUpperCase().trim()) != null) {
										hasDatakey = true;
									}
									if ("AGENCYID".equals(dtf.getDbcolumnname()
											.toUpperCase().trim())
											&& hasPKmap.get(dtf
													.getDbcolumnname()
													.toUpperCase().trim()) != null) {
										hasAgencyID = true;
									}
								}
								// 视图 字段
								List<DictTFactorPO> listFactors = viewColumMap
										.get(prefix + tableName);
								if (listFactors == null) {
									listFactors = new ArrayList<DictTFactorPO>();
									listFactors.add(dtf);
									viewColumMap.put(prefix + tableName,
											listFactors);
								} else {
									listFactors.add(dtf);
									viewColumMap.put(prefix + tableName,
											listFactors);
								}

							} else {
								throw new Exception(
										"不允许出现'YEAR','PROVINCE','STATUS','DBVERSION','BAKVERSION','BAKUSERID','BAKTYPE'等字段.");
							}

						}
						String sbsql = sb.substring(0, sb.length() - 1);
						sb.setLength(0);
						sb.append(sbsql);
						sb.append(") \n");
					} else {
						throw new Exception("系统出现异常，请稍后再试.");
					}

					// -------------------------------------------------------------------------bak------------------------------------------------
				} else {
					for (DictTDefaultcolPO dtd : dtdList) {
						if (!"YEAR".equals(dtd.getDbcolumnname().toUpperCase()
								.trim())
								&& !"PROVINCE".equals(dtd.getDbcolumnname()
										.toUpperCase().trim())
								&& !"STATUS".equals(dtd.getDbcolumnname()
										.toUpperCase().trim())
								&& !"DBVERSION".equals(dtd.getDbcolumnname()
										.toUpperCase().trim())) {
							if (isTask
									&& "TASKID".equals(dtd.getDbcolumnname()
											.toUpperCase().trim())) {
								throw new Exception("任务表不允许出现'TASKID'字段.");
							}
							sb.append(dtd.getDbcolumnname() + " ");
							List<DictTFactorPO> listFactor = viewColumMap
									.get(prefix + tableName);
							if (listFactor == null) {
								listFactor = new ArrayList<DictTFactorPO>();
								DictTFactorPO dtf = new DictTFactorPO();
								dtf.setDbcolumnname(dtd.getDbcolumnname()
										.toUpperCase());
								listFactor.add(dtf);
								viewColumMap
										.put(prefix + tableName, listFactor);
							} else {
								DictTFactorPO dtf = new DictTFactorPO();
								dtf.setDbcolumnname(dtd.getDbcolumnname()
										.toUpperCase());
								listFactor.add(dtf);
								viewColumMap
										.put(prefix + tableName, listFactor);
							}

							// plevel varchar2(3) generated always as("")
							// virtual

							Integer type = dtd.getDatatype();
							if (type == 3) {
								if (dtd.getDatalength() == 1) {
									sb.append("CHAR ( ");
								} else {
									sb.append(DictDBConstants.dataType
											.get(type) + " ( ");
								}
							} else if (type == 7) {
								sb.append(" VARCHAR2 ( ");
							} else {
								sb.append(DictDBConstants.dataType.get(type)
										+ " ( ");
							}
							if (type == 7) {
								sb.append("4000");
							} else {
								sb.append(dtd.getDatalength());
							}

							if (dtd.getDatatype() == 1
									|| dtd.getDatatype() == 2) {
								if (dtd.getScale() != null
										&& !"".equals(dtd.getScale())) {
									sb.append("," + dtd.getScale());
								}
							}
							sb.append(" ) ");
							if (dtd.getDefaultvalue() != null
									&& !"".equals(dtd.getDefaultvalue())) {
								sb.append(DictDBConstants.DEFAULT + " "
										+ dtd.getDefaultvalue() + " ");
							}
							/*
							 * if("1".equals(dtd.getIsprimary())){
							 * sb.append(DictDBConstants.NOT_NULL);
							 * //listPK.add(dtd.getDbcolumnname()); }
							 */
							sb.append(",");

							if ("DATAKEY".equals(dtd.getDbcolumnname()
									.toUpperCase().trim())) {
								hasDatakey = true;
							}
							if ("AGENCYID".equals(dtd.getDbcolumnname()
									.toUpperCase().trim())) {
								hasAgencyID = true;
							}
						}
					}
					String sbsql = sb.substring(0, sb.length() - 1);
					sb.setLength(0);
					sb.append(sbsql);
					sb.append(") \n");
				}

				if (hasAgencyID) {
					listPK.add("AGENCYID"); // 主键
				}
				if (hasDatakey) {
					listPK.add("DATAKEY"); // 主键
				}
				String add_comments = DictDBConstants.ADD_COMMENTS + "  "
						+ prefix + tableName + " " + DictDBConstants.IS + " '"
						+ name + "' ";
				sqlMap.put(DictDBConstants.TYPE_TABLE_COMMENTS, add_comments);
				if ("0".equals(dbStatus) || "0".equals(ispartition)) {
					/**
					 * ******************************创建物理表sql
					 * ********************************************
					 */
					sqlMap.put(DictDBConstants.TYPE_TABLE, sb.toString());

				} else if (("1".equals(dbStatus) || "2".equals(dbStatus))
						&& "1".equals(ispartition)) {

					/**
					 * ******************************创建分区表sql
					 * ********************************************
					 */
					String partitionSql = createPartition(dbStatus, tableName);
					sqlMap.put(DictDBConstants.TYPE_PARTITION, sb.toString()
							+ partitionSql);

				}

				/**
				 * ******************************创建视图 sql
				 * ********************************************
				 */
				String viewSql = createView(ispartition, dbStatus, tableName,
						viewColumMap, secusql, isTask, false, isAllDistrict, isAllYear);
				sqlMap.put(DictDBConstants.TYPE_VIEW, viewSql);

				// 如果需要有配置表，同步创建配置表
				if (isConfig) {
					String configViewSql = createView(ispartition, dbStatus,
							tableName, viewColumMap, secusql, isTask, isConfig,
							isAllDistrict, isAllYear);
					sqlMap.put(DictDBConstants.TYPE_CFG_VIEW, configViewSql);
					// 如果配置表的，默认加上temple需要索引加快速度
					String sqlIndex = new StringBuffer("CREATE INDEX ")
							.append("IDX_" + prefix + tableName + "_F ")
							.append(DictDBConstants.ON + " ")
							.append(prefix + tableName
									+ " (TEMPLATEID, ISTEMPLATE)").toString();
					sqlMap.put(DictDBConstants.TYPE_INDEX, sqlIndex);
				}

				/**
				 * ******************************创建触发器 sql
				 * ********************************************
				 */
				String triggerSql = createTrigger(dbStatus, tableName, isTask);
				sqlMap.put(DictDBConstants.TYPE_TRIGGER, triggerSql);

				/**
				 * ******************************创建设定主键 sql
				 * ********************************************
				 */
				String sqlPk = createPK(dbStatus, tableName, listPK);
				sqlMap.put(DictDBConstants.TYPE_PRIMARY, sqlPk);

				// -------------------------------------------------------
				// _bak-----------------------------

				// if(_bak){
				/**
				 * ******************************创建索引sql
				 * ***********************************************
				 */
				// String sqlIndex = createIndex(tableName, listIndex);
				// sqlMap.put(DictDBConstants.TYPE_INDEX, sqlIndex);
				// }

			}
		} catch (Exception e) {
			throw e;
		}
		return sqlMap;
	}

	/**
	 * 创建表分区 sql
	 * 
	 * @param dbStatus
	 *            数据库状态
	 * @param tableName
	 *            屋里表名称
	 * @return
	 */
	@Override
	public String createPartition(String dbStatus, String tableName) {

		String sql = null;
		List<PubTPartitionDividPO> listYear = this.partitionDividMapper
				.getAllForYear();
		StringBuilder sb = new StringBuilder();
		if (dbStatus != null) {
			if ("0".equals(dbStatus)) {
				// 单财政单年度
			} else if ("1".equals(dbStatus)) {
				// 单财政多年度
				sb.append(DictDBConstants.PARTITION_BY_LIST + " ");
				sb.append("(YEAR) \n");
				sb.append("( ");
				for (PubTPartitionDividPO ptp : listYear) {
					sb.append("\n " + DictDBConstants.PARTITION + " ");
					sb.append("PY" + ptp.getYear() + " ");
					sb.append(DictDBConstants.VALUES + " ");
					sb.append("('" + ptp.getYear() + "')  TABLESPACE "
							+ ptp.getIdTabspace() + ",");
				}
				String str1 = sb.substring(0, sb.length() - 1);
				sb.delete(0, sb.length());
				sb.append(str1);
				sb.append("\n )");
				sql = sb.toString();
				return sql;
			} else if ("2".equals(dbStatus)) {
				// 多财政多年度
				List<PubTPartitionDividPO> listSetting = this.partitionDividMapper
						.getProvinceGroupByYear();
				sb.append(DictDBConstants.PARTITION_BY_LIST + " ");
				sb.append("(PROVINCE) \n");
				sb.append(DictDBConstants.SUBPARTITION_BY_LIST + " ");
				sb.append("(YEAR) \n");
				sb.append("( ");
				for (PubTPartitionDividPO ptp : listSetting) {
					sb.append("\n " + DictDBConstants.PARTITION + " ");
					sb.append("P" + ptp.getDistrictid() + " ");
					sb.append(DictDBConstants.VALUES + " ");
					sb.append("('" + ptp.getDistrictid() + "')  TABLESPACE "
							+ ptp.getIdTabspace() + " \n");
					sb.append("(");
					for (PubTPartitionDividPO ptpChild : listYear) {
						sb.append(" \n " + DictDBConstants.SUBPARTITION + " ");
						sb.append("P" + ptp.getDistrictid() + "_Y"
								+ ptpChild.getYear() + " ");
						sb.append(DictDBConstants.VALUES + " ");
						sb.append("('" + ptpChild.getYear() + "')  TABLESPACE "
								+ ptpChild.getIdTabspace() + ",");
					}
					String str = sb.substring(0, sb.length() - 1);
					sb.delete(0, sb.length());
					sb.append(str);
					sb.append("\n ),");
				}
				String str = sb.substring(0, sb.length() - 1);
				sb.delete(0, sb.length());
				sb.append(str);
				sb.append("\n )");
				sql = sb.toString();
				return sql;
			}
		}
		return sql;
	}

	/**
	 * 创建视图
	 * 
	 * @param ispartition
	 *            是否分区
	 * @param dbStatus
	 *            分区状态
	 * @param tableName
	 *            表名
	 * @param viewColumMap
	 *            视图列map
	 * @param secusql
	 *            过滤语句
	 * @param isTask
	 *            是否任务表
	 * @param isConfig
	 *            是否配置变盘
	 * @param isAllDistrict
	 *            是否全地区访问
	 * @param isAllYear
	 *            是否全年度访问
	 * @return
	 */
	@Override
	public String createView(String ispartition, String dbStatus,
			String tableName, Map<String, List<DictTFactorPO>> viewColumMap,
			String secusql, boolean isTask, boolean isConfig,
			boolean isAllDistrict, boolean isAllYear) {
		// create or replace view '||v_dbname||' as select '||substr(v_tmp,2)||'
		// from '||v_new_dbname||' where YEAR=
		// global_multyear_cz.Secu_f_GLOBAL_PARM(''YEAR'')
		String prefix = DictDBConstants.PREFIX_TABLE;
		StringBuilder sb = new StringBuilder();
		StringBuilder column = new StringBuilder();
		StringBuilder table = new StringBuilder();
		// 取tableID
		DictTModelPO dictTModel = dictTModelMapper
				.getDictTModelByName(tableName);
		String tableID = "";
		if (dictTModel != null) {
			tableID = dictTModel.getTableid();
		}
		String t = prefix + tableName;
		// 配置视图名加后缀
		if (isConfig) {
			tableName += DictDBConstants.SUFFIX_CONFIG_VIEW;
		}
		boolean isband = false;
		for (Entry<String, List<DictTFactorPO>> m : viewColumMap.entrySet()) {
			// from 表
			t = m.getKey().toLowerCase();
			// select t.列 as name
			List<DictTFactorPO> listFactor = m.getValue();

			for (DictTFactorPO dtf : listFactor) {

				if ("1".equals(dtf.getIsbandcol())) {
					// 绑定列
					if (dtf.getBandcolumnid() != null
							&& !"".equals(dtf.getBandcolumnid())
							&& dtf.getBandrefdwcol() != null
							&& !"".equals(dtf.getBandrefdwcol())) {

						DictTFactorcodePO dtfc = this.dictTFactorcodeMapper
								.getDictTFactorcode(dtf.getBandrefdwcol());
						if (dtfc != null) {
							DictTFactorPO d = this.dictTFactorMapper
									.getDictTFactor(dtf.getBandcolumnid());
							DictTModelcodePO dtmc = dictTModelcodeMapper
									.getDictTModelcode(d.getCsid());
							if (dtf != null) {
								isband = true;
								String smallName = dtmc.getDbtablename()
										.toLowerCase();
								column.append(" (SELECT ");
								column.append(smallName + "."
										+ dtfc.getDbcolumnname().toUpperCase()
										+ " ");
								column.append("FROM "
										+ dtmc.getDbtablename().toUpperCase()
										+ " " + smallName + " ");
								column.append("WHERE " + smallName + ".GUID = "
										+ t + "."
										+ d.getDbcolumnname().toUpperCase()
										+ " AND ROWNUM < 2) ");
								column.append(DictDBConstants.AS + " ");
								column.append(dtf.getDbcolumnname() + ",");
							}
						}
					}
				} else if ("1".equals(dtf.getIsvirtual())) {
					// 虚列
					/*
					 * column.append(t+"."+dtf.getDbcolumnname()+" ");
					 * column.append(DictDBConstants.AS+ " ");
					 */
					column.append(dtf.getDbcolumnname() + ",");
				} else {
					// 本列
					/*
					 * column.append(t+"."+dtf.getDbcolumnname()+" ");
					 * column.append(DictDBConstants.AS+ " ");
					 */
					column.append(dtf.getDbcolumnname() + ",");
				}
			}

			if (isband) {
				table.append(m.getKey().toUpperCase() + " " + t + ",");
			} else {
				table.append(m.getKey().toUpperCase() + ",");
			}
		}

		sb.append(DictDBConstants.CREATE_OR_REPLACE + " ");
		sb.append(DictDBConstants.VIEW + " ");
		sb.append(tableName + " ");
		sb.append(DictDBConstants.AS + " ");
		sb.append(DictDBConstants.SELECT + " ");
		/*
		 * column.append(t+".STATUS "); column.append(DictDBConstants.AS+
		 * " STATUS,"); column.append(t+".DBVERSION ");
		 * column.append(DictDBConstants.AS+ " DBVERSION,");
		 */
		column.append(" STATUS,");
		column.append(" DBVERSION,");
		if (isTask) {
			column.append(" TASKID,");
		}
		String c = column.substring(0, column.length() - 1);
		sb.append(c + " ");

		sb.append(DictDBConstants.FROM + " ");

		String str = table.substring(0, table.length() - 1);
		sb.append(str + " ");

		if ("1".equals(dbStatus) && "1".equals(ispartition)) {
			sb.append(" WHERE YEAR=global_multyear_cz.Secu_f_GLOBAL_PARM('YEAR') AND STATUS='1' ");
		} else if ("2".equals(dbStatus) && "1".equals(ispartition)) {
			sb.append(" WHERE 1=1 ");
			if(!isAllYear)
				sb.append(" AND YEAR=global_multyear_cz.Secu_f_GLOBAL_PARM('YEAR') ");
			if (!isAllDistrict) {
				sb.append(" AND PROVINCE=global_multyear_cz.Secu_f_GLOBAL_PARM('DIVID') ");
			}
			sb.append(" AND STATUS='1' ");
		} else {
			sb.append(" WHERE STATUS='1' ");
		}
		if (secusql != null && !"".equals(secusql)) {
			sb.append(" AND ").append(secusql).append(" ");
		}
		if (isConfig) {
			sb.append(" AND  AGENCYID = '*' AND ISTEMPLATE = '1' ");
			// 加入配置表条件过滤
			if (tableID != null && !"".equals(tableID)) {
				ConditionSetPo conSet = conditionSetMapper
						.getCondition(tableID);
				if (conSet != null) {
					String filterCond = conSet.getCondition();
					if (filterCond != null && !"".equals(filterCond)) {
						sb.append(" AND  (").append(filterCond).append(") ");
					}
				}
			}
		}
		if (isTask) {
			if (isConfig) {
				sb.append(" AND TASKID='*' ");
			} else {
				sb.append(" AND TASKID=global_multyear_cz.Secu_f_GLOBAL_PARM('TASKID') ");
			}
		}
		return sb.toString();
	}

	/**
	 * 创建业务对象BAK表视图
	 * 
	 * @param tableName
	 * @param viewColumMap
	 * @param secusql
	 * @param isTask
	 * @return
	 */
	public String createBakViewForUpdateView(String tableName,
			Map<String, List<DictTFactorPO>> viewColumMap, String secusql,
			boolean isTask) {
		StringBuilder sb = new StringBuilder();
		StringBuilder column = new StringBuilder();
		StringBuilder table = new StringBuilder();
		String sourceTableName = "";
		boolean isband = false;
		for (Entry<String, List<DictTFactorPO>> m : viewColumMap.entrySet()) {
			// from 表
			sourceTableName = m.getKey().toLowerCase();
			// select t.列 as name
			List<DictTFactorPO> listFactor = m.getValue();

			for (DictTFactorPO dtf : listFactor) {

				if ("1".equals(dtf.getIsbandcol())) {
					// 绑定列
					if (dtf.getBandcolumnid() != null
							&& !"".equals(dtf.getBandcolumnid())
							&& dtf.getBandrefdwcol() != null
							&& !"".equals(dtf.getBandrefdwcol())) {

						DictTFactorcodePO dtfc = this.dictTFactorcodeMapper
								.getDictTFactorcode(dtf.getBandrefdwcol());
						if (dtfc != null) {
							DictTFactorPO d = this.dictTFactorMapper
									.getDictTFactor(dtf.getBandcolumnid());
							DictTModelcodePO dtmc = dictTModelcodeMapper
									.getDictTModelcode(d.getCsid());
							if (dtf != null) {
								isband = true;
								String smallName = dtmc.getDbtablename()
										.toLowerCase();
								column.append(" (SELECT ");
								column.append(smallName + "."
										+ dtfc.getDbcolumnname().toUpperCase()
										+ " ");
								column.append("FROM "
										+ dtmc.getDbtablename().toUpperCase()
										+ " " + smallName + " ");
								column.append("WHERE " + smallName + ".GUID = "
										+ tableName + "."
										+ d.getDbcolumnname().toUpperCase()
										+ " AND ROWNUM < 2) ");
								column.append(DictDBConstants.AS + " ");
								column.append(dtf.getDbcolumnname() + ",");
							}
						}
					}
				} else if ("1".equals(dtf.getIsvirtual())) {
					// 虚列
					/*
					 * column.append(t+"."+dtf.getDbcolumnname()+" ");
					 * column.append(DictDBConstants.AS+ " ");
					 */
					column.append(dtf.getDbcolumnname() + ",");
				} else {
					// 本列
					/*
					 * column.append(t+"."+dtf.getDbcolumnname()+" ");
					 * column.append(DictDBConstants.AS+ " ");
					 */
					column.append(dtf.getDbcolumnname() + ",");
				}
			}

			if (isband) {
				table.append(m.getKey().toUpperCase() + " " + sourceTableName
						+ ",");
			} else {
				table.append(m.getKey().toUpperCase() + ",");
			}
		}

		sb.append(DictDBConstants.CREATE_OR_REPLACE + " ");
		sb.append(DictDBConstants.VIEW + " ");
		sb.append(tableName + " ");
		sb.append(DictDBConstants.AS + " ");
		sb.append(DictDBConstants.SELECT + " ");
		/*
		 * column.append(t+".STATUS "); column.append(DictDBConstants.AS+
		 * " STATUS,"); column.append(t+".DBVERSION ");
		 * column.append(DictDBConstants.AS+ " DBVERSION,");
		 */
		column.append(" STATUS,");
		column.append(" DBVERSION,");
		if (isTask) {
			column.append(" TASKID,");
		}
		String c = column.substring(0, column.length() - 1);
		sb.append(c + " ");

		sb.append(DictDBConstants.FROM + " ");

		String str = table.substring(0, table.length() - 1);
		sb.append(str + " ");

		if (secusql != null && !"".equals(secusql)) {
			sb.append(" AND ").append(secusql).append(" ");
		}
		if (isTask) {
			sb.append(" AND TASKID=global_multyear_cz.Secu_f_GLOBAL_PARM('TASKID') ");
		}
		return sb.toString();
	}

	/**
	 * 创建触发器
	 * 
	 * @param dbStatus
	 * @param tableName
	 * @return
	 */
	@Override
	public String createTrigger(String dbStatus, String tableName,
			boolean isTask) {

		String prefix = DictDBConstants.PREFIX_TABLE;
		if (dbStatus.equals("0")) {
			prefix = "";
		}
		StringBuilder sb = new StringBuilder();
		if (dbStatus != null && !"".equals(dbStatus)) {
			sb.append(DictDBConstants.CREATE_OR_REPLACE + " ");
			sb.append(DictDBConstants.TRIGGER + " ");
			sb.append("TR_" + prefix + tableName + "  \n");// ////////////NAME
			sb.append("" + DictDBConstants.BEFORE_INSERT_OR_UPDATE_ON + " ");
			sb.append(prefix + tableName + " ");
			sb.append(DictDBConstants.FOR_EACH_ROW + " \n");
			sb.append(DictDBConstants.BEGIN + " \n");
			sb.append(DictDBConstants.IF + " " + DictDBConstants.INSERTING
					+ " " + DictDBConstants.THEN + " \n");
			/*
			 * sb.append(DictDBConstants.NEW_YEAR+" \n");
			 * sb.append(DictDBConstants.NEW_PROVINCE+" \n");
			 */

			if ("1".equals(dbStatus)) {
				sb.append(DictDBConstants.NEW_YEAR + " \n");
			} else if ("2".equals(dbStatus)) {
				sb.append(DictDBConstants.NEW_PROVINCE + " \n");
				sb.append(DictDBConstants.NEW_YEAR + " \n");
			}
			if (isTask) {
				sb.append(DictDBConstants.NEW_TASKID + " \n");
			}
			sb.append(DictDBConstants.NEW_DBVERSION + " \n");
			sb.append(DictDBConstants.END + " " + DictDBConstants.IF + "; \n");

			sb.append(DictDBConstants.IF + " (");
			sb.append(DictDBConstants.IF_TOCHAR + " ) " + DictDBConstants.THEN
					+ " \n");
			sb.append("RETURN ;\n");
			sb.append(DictDBConstants.END + " " + DictDBConstants.IF + "; \n");

			sb.append(DictDBConstants.IF + " " + DictDBConstants.UPDATING + " "
					+ DictDBConstants.THEN + " \n");
			sb.append(DictDBConstants.NEW_VERSION_UPDATE + "\n");
			sb.append(DictDBConstants.END + " " + DictDBConstants.IF + "; \n");
			sb.append(DictDBConstants.END + " ");
			sb.append("TR_" + prefix + tableName);// ////////////NAME
			sb.append(";");
		}

		return sb.toString();
	}

	@Override
	public String getGlobalIsmultdb() throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		map.put("s", "1");
		try {
			this.dictDBExecuteMapper.getGlobalIsmultdb(map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("读取全局系统分区变量出错");
		}
		return map.get("s");
	}

	@Override
	public Integer getIfExistsInDB(String sql) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("v", 1);
		map.put("v_sql", sql);
		try {
			this.dictDBExecuteMapper.getIfExistsInDB(map);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return Integer.parseInt(map.get("v") + "");
	}

	@Override
	public String getUUID() throws Exception {
		return utilsMapper.getDBUniqueID();
	}

	@Override
	public List<Map<String, Object>> getColumnByViewName(String viewName)
			throws Exception {

		return this.dictDBExecuteMapper.getColumnByViewName(viewName);
	}

	@Override
	public List<Map<String, String>> getTableColumnPK(String tableName)
			throws Exception {

		return this.dictDBExecuteMapper.getTableColumnPK(tableName);
	}

	@Override
	public void ExecDllLongForParam(String sql) throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		map.put("dbSql", sql);
		System.out
				.println("*****************************************************************************************************************************");
		System.out.println(sql);
		System.out
				.println("*****************************************************************************************************************************");

		map.put("errorMessage", "");
		try {
			this.dictDBExecuteMapper.callExecDllLongForParam(map);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		String errorMessage = map.get("errorMessage");
		if (errorMessage != null && !"".equals(errorMessage)) {
			throw new Exception(errorMessage);
		}
	}

	/**
	 * 创建 可更新试图 触发器
	 * 
	 * @param viewName
	 * @param settingMap
	 * @param trgs
	 * @param settingmap
	 * @param trg_as
	 * @param factorType
	 * @return
	 */
	public String createTriggerForUpdateView(String viewName,
			Map<String, String> setMap, Map<String, Map<String, String>> trgs,
			Map<String, Map<String, String>> settingmap,
			Map<String, String> trg_as, Map<String, DictTFactorPO> factorType) {
		String prefix = "";// DictDBConstants.PREFIX_TABLE;
		StringBuilder trigger = new StringBuilder();
		trigger.append(DictDBConstants.CREATE_OR_REPLACE + " ");
		trigger.append(DictDBConstants.TRIGGER + " ");
		trigger.append("TR_" + prefix + viewName + " \n");
		trigger.append(DictDBConstants.INSTEAD + " ");
		trigger.append(DictDBConstants.OF + " ");
		trigger.append(DictDBConstants.INSERT + " ");
		trigger.append(DictDBConstants.OR + " ");
		trigger.append(DictDBConstants.UPDATE + " ");
		trigger.append(DictDBConstants.OR + " ");
		trigger.append(DictDBConstants.DELETE + " ");
		trigger.append(DictDBConstants.ON + " ");
		trigger.append(viewName + " \n");
		trigger.append(DictDBConstants.FOR + " ");
		trigger.append(DictDBConstants.EACH + " ");
		trigger.append(DictDBConstants.ROW + " \n");
		trigger.append(DictDBConstants.DECLARE + " \n");
		for (int k = 0; k < trgs.size(); k++) {
			trigger.append(DictDBConstants.V_UPDATE_COL + k
					+ " varchar2(30000); \n");
			trigger.append(DictDBConstants.V_SQL + k + " varchar2(30000);\n");
		}
		trigger.append(DictDBConstants.BEGIN + " \n");
		/**
		 * --------------------------------------------------------------------
		 * -- --------INSERT----------------------------------------------------
		 * -- ---------------
		 */
		trigger.append(DictDBConstants.IF + " ");
		trigger.append(DictDBConstants.INSERTING + " ");
		trigger.append(DictDBConstants.THEN + " \n");

		for (Entry<String, Map<String, String>> dm : trgs.entrySet()) {
			trigger.append(DictDBConstants.INSERT + " ");
			trigger.append(DictDBConstants.INTO + " ");
			trigger.append(dm.getKey() + " (");
			int h = 0;
			StringBuilder xs = new StringBuilder();
			Map<String, String> other = settingmap.get(dm.getKey());
			boolean fg = true;
			for (Entry<String, String> xm : dm.getValue().entrySet()) {
				if (h != 0) {
					trigger.append(",");
					xs.append(",");
				}
				trigger.append(xm.getValue() + " ");
				xs.append(" nvl( ");
				xs.append(":" + DictDBConstants.NEW + "."
						+ trg_as.get(xm.getKey()));
				String defaultvalues = "''";
				DictTFactorPO dictTFactorPO = factorType.get(xm.getKey());
				if (dictTFactorPO != null) {
					defaultvalues = dictTFactorPO.getDefaultvalue();
					if (defaultvalues != null && !"".equals(defaultvalues)) {
						defaultvalues = defaultvalues.replace("\"", "'");
					} else {
						Integer type = factorType.get(xm.getKey())
								.getDatatype();
						if (type != null) {
							defaultvalues = DictDBConstants.dataType_Defaultvalue
									.get(type);
						}
					}
					xs.append(", " + defaultvalues + ")");
				}
				h++;
				if (other != null) {
					other.remove(xm.getKey());
				}
				fg = false;
			}
			if (other != null) {
				h = 0;
				for (Entry<String, String> otherMap : other.entrySet()) {
					if (h != 0 || !fg) {
						trigger.append(",");
						xs.append(",");
					}
					trigger.append(otherMap.getValue() + " ");
					xs.append(" nvl( ");
					xs.append(":" + DictDBConstants.NEW + "."
							+ trg_as.get(otherMap.getKey()));
					String defaultvalues = "''";
					DictTFactorPO dictTFactorPO = factorType.get(otherMap
							.getKey());
					if (dictTFactorPO != null) {
						defaultvalues = dictTFactorPO.getDefaultvalue();
						if (defaultvalues != null && !"".equals(defaultvalues)) {
							defaultvalues = defaultvalues.replace("\"", "'");
						} else {
							Integer type = factorType.get(otherMap.getKey())
									.getDatatype();
							if (type != null) {
								defaultvalues = DictDBConstants.dataType_Defaultvalue
										.get(type);
							}
						}
						xs.append(", " + defaultvalues + ")");
					}
					h++;
				}
			}
			trigger.append(" ) ");
			trigger.append(DictDBConstants.VALUES + " ");
			trigger.append("( ");
			trigger.append(xs.toString());
			trigger.append(" ); \n");
		}
		/**
		 * --------------------------------------------------------------------
		 * --
		 * -UPDATE-------------------------------------------------------------
		 */
		trigger.append(DictDBConstants.ELSIF + " ");
		trigger.append(DictDBConstants.UPDATING + " ");
		trigger.append(DictDBConstants.THEN + " \n");
		int q = 0;
		for (Entry<String, Map<String, String>> dm : trgs.entrySet()) {

			trigger.append(DictDBConstants.V_UPDATE_COL + q + ":= ");
			int h = 0;
			StringBuilder xs = new StringBuilder();
			Map<String, String> other = settingmap.get(dm.getKey());
			boolean fg = true;
			boolean fgl = true;
			for (Entry<String, String> xm : dm.getValue().entrySet()) {
				String settings = setMap.get(xm.getKey());
				if (h != 0 || !fg) {
					trigger.append(" || ");
					if (!fgl) {
						if (settings != null) {
							xs.append(" || ' " + DictDBConstants.AND);
						}
					}
				}
				// where
				if (settings != null) {
					xs.append(" " + xm.getValue() + " = ");
					xs.append(" '''||  nvl(:" + DictDBConstants.OLD + "."
							+ trg_as.get(xm.getKey()) + ", '') ||'''' ");
					fgl = false;
				}
				// set column
				trigger.append(DictDBConstants.CASE + " ");
				trigger.append(DictDBConstants.WHEN + " ");
				String defaultvalues = "''";
				DictTFactorPO dictTFactorPO = factorType.get(xm.getKey());
				if (dictTFactorPO != null) {
					Integer type = factorType.get(xm.getKey()).getDatatype();
					if (type == 1 || type == 2) {
						defaultvalues = "0";
					} else {
						defaultvalues = "'*'";
					}
				}
				trigger.append(" nvl(:" + DictDBConstants.OLD + "."
						+ trg_as.get(xm.getKey()) + ", " + defaultvalues
						+ " ) ");
				trigger.append(" = ");
				trigger.append(" nvl(:" + DictDBConstants.NEW + "."
						+ trg_as.get(xm.getKey()) + ", " + defaultvalues
						+ " ) ");
				trigger.append(DictDBConstants.THEN + "  '' ");
				trigger.append(DictDBConstants.ELSE + "  '");

				trigger.append(xm.getValue() + " = ");
				if ("0".equals(defaultvalues)) {
					trigger.append("nvl(' || nvl(:" + DictDBConstants.NEW + "."
							+ trg_as.get(xm.getKey()) + ", " + defaultvalues
							+ ") ");
					trigger.append(" || '," + defaultvalues + "),' ");
				} else {
					trigger.append("nvl(''' || nvl(:" + DictDBConstants.NEW
							+ "." + trg_as.get(xm.getKey()) + ",  '') ");
					trigger.append(" || ''',''''),' ");
				}

				trigger.append(DictDBConstants.END);
				h++;
				fg = false;
				if (other != null) {
					other.remove(xm.getKey());
				}
			}
			if (other != null) {
				h = 0;
				for (Entry<String, String> otherMap : other.entrySet()) {
					if (h != 0 || !fg) {
						trigger.append(" || ");
						if (!fgl) {
							xs.append(" || ' " + DictDBConstants.AND);
						}
					}
					// where
					xs.append(" " + otherMap.getValue() + " = ");
					xs.append(" '''||  nvl(:" + DictDBConstants.OLD + "."
							+ trg_as.get(otherMap.getKey()) + ", '') ||'''' ");
					fgl = false;
					// set column
					trigger.append(DictDBConstants.CASE + " ");
					trigger.append(DictDBConstants.WHEN + " ");
					String defaultvalues = "''";
					DictTFactorPO dictTFactorPO = factorType.get(otherMap
							.getKey());
					if (dictTFactorPO != null) {
						Integer type = factorType.get(otherMap.getKey())
								.getDatatype();
						if (type == 1 || type == 2) {
							defaultvalues = "0";
						} else {
							defaultvalues = "'*'";
						}
					}
					trigger.append(" nvl(:" + DictDBConstants.OLD + "."
							+ trg_as.get(otherMap.getKey()) + ", "
							+ defaultvalues + " ) ");
					trigger.append(" = ");
					trigger.append(" nvl(:" + DictDBConstants.NEW + "."
							+ trg_as.get(otherMap.getKey()) + ", "
							+ defaultvalues + " ) ");
					trigger.append(DictDBConstants.THEN + "  '' ");
					trigger.append(DictDBConstants.ELSE + "  '");

					trigger.append(otherMap.getValue() + " = ");
					if ("0".equals(defaultvalues)) {
						trigger.append("nvl(' || nvl(:" + DictDBConstants.NEW
								+ "." + trg_as.get(otherMap.getKey()) + ", "
								+ defaultvalues + ") ");
						trigger.append(" || '," + defaultvalues + "),' ");
					} else {
						trigger.append("nvl(''' || nvl(:" + DictDBConstants.NEW
								+ "." + trg_as.get(otherMap.getKey())
								+ ",  '') ");
						trigger.append(" || ''',''''),' ");
					}
					trigger.append(DictDBConstants.END);
					h++;
				}
			}
			trigger.append("; \n" + DictDBConstants.IF + " ");
			trigger.append(DictDBConstants.V_UPDATE_COL + q + "  ");
			trigger.append(DictDBConstants.IS + " ");
			trigger.append(DictDBConstants.NOT_NULL + " ");
			trigger.append(DictDBConstants.THEN + "  \n");
			trigger.append(DictDBConstants.V_SQL + q + " :='");
			trigger.append(DictDBConstants.UPDATE + " ");
			trigger.append(dm.getKey() + " ");
			trigger.append(DictDBConstants.SET + " ' || ");
			trigger.append(DictDBConstants.SUBSTR + "(");
			trigger.append(DictDBConstants.V_UPDATE_COL + q + ",1,");
			trigger.append(DictDBConstants.LENGTH + "("
					+ DictDBConstants.V_UPDATE_COL + q + ") - 1 ) || '");
			trigger.append(" " + DictDBConstants.WHERE + " ");
			trigger.append(xs.toString());
			trigger.append("'; \n");
			trigger.append(DictDBConstants.EXECUTE_IMMEDIATE + " "
					+ DictDBConstants.V_SQL + q + "; \n");
			trigger.append(DictDBConstants.END + " " + DictDBConstants.IF
					+ "; \n");
			q++;
		}

		/**
		 * ------------------------------------------------------------------
		 * DELETE
		 * --------------------------------------------------------------------
		 */
		trigger.append(DictDBConstants.ELSIF + " ");
		trigger.append(DictDBConstants.DELETING + " ");
		trigger.append(DictDBConstants.THEN + " \n");
		for (Entry<String, Map<String, String>> dm : trgs.entrySet()) {
			// Delete from t1 where t11=:Old.f1;
			trigger.append(DictDBConstants.DELETE + " ");
			trigger.append(DictDBConstants.FROM + " ");
			trigger.append(dm.getKey() + " ");
			StringBuilder xs = new StringBuilder();
			Map<String, String> other = settingmap.get(dm.getKey());
			boolean fg = true;
			int h = 0;
			for (Entry<String, String> xm : dm.getValue().entrySet()) {
				String settings = setMap.get(xm.getKey());
				if (h != 0 && !fg) {
					if (settings != null) {
						xs.append(DictDBConstants.AND + " ");
					}
				}
				if (settings != null) {
					xs.append(xm.getValue() + " = ");
					xs.append(":" + DictDBConstants.OLD + "."
							+ trg_as.get(xm.getKey()) + " ");
					fg = false;
				}
				h++;
			}
			if (other != null) {
				h = 0;
				for (Entry<String, String> otherMap : other.entrySet()) {
					if (!fg) {
						xs.append(DictDBConstants.AND + " ");
					}
					xs.append(otherMap.getValue() + " = ");
					xs.append(":" + DictDBConstants.OLD + "."
							+ trg_as.get(otherMap.getKey()) + " ");
					fg = false;
					h++;
				}
			}
			trigger.append(" " + DictDBConstants.WHERE + " ");
			trigger.append(xs.toString());
			trigger.append("; \n");
		}

		// last
		trigger.append(DictDBConstants.END + " ");
		trigger.append(DictDBConstants.IF + "; \n");
		trigger.append(DictDBConstants.END + "; \n");
		return trigger.toString();
	}

	@Override
	public Map<String, String> createViewTriggerForUpdataView(String viewName,
			DictTModelPO dictTModel,
			Map<String, List<DictTFactorPO>> sourceTableMap,
			List<Map<String, String>> settingList, String secusql, String status)
			throws Exception {
		String prefix = "";// DictDBConstants.PREFIX_TABLE;
		if (status.equals("0")) {
			prefix = "";
		}
		Map<String, String> returnMap = new HashMap<String, String>();
		try {
			Map<String, String> settingMap = new HashMap<String, String>();
			for (Map<String, String> m : settingList) {
				if (settingMap.get(m.get("columnid")) != null) {
					String v = settingMap.get(m.get("columnid"));
					v = v + "," + m.get("tocolumnid");
					settingMap.put(m.get("columnid"), v);
				} else {
					settingMap.put(m.get("columnid"), m.get("tocolumnid"));
				}
			}

			/**
			 * 创建视图
			 */
			StringBuilder view = new StringBuilder();
			view.append(DictDBConstants.CREATE_OR_REPLACE + " ");
			view.append(DictDBConstants.VIEW + " ");
			view.append(viewName + " ");
			view.append(DictDBConstants.AS + " ");
			if (secusql != null && !"".equals(secusql)) {
				view.append("SELECT * FROM ( ");
			}

			view.append(DictDBConstants.SELECT + " ");

			StringBuilder tableNameStr = new StringBuilder();
			Map<String, Map<String, String>> trgs = new HashMap<String, Map<String, String>>();
			Map<String, Map<String, String>> setting_map = new HashMap<String, Map<String, String>>();
			Map<String, String> trg_as = new HashMap<String, String>();
			Map<String, DictTFactorPO> factorType = new HashMap<String, DictTFactorPO>();
			for (Entry<String, List<DictTFactorPO>> m : sourceTableMap
					.entrySet()) {
				String tableid = m.getKey();
				List<DictTFactorPO> dtfs = m.getValue();
				DictTModelPO dtm = this.dictTModelMapper.getDictTModel(tableid);
				if (dtm != null && dtfs != null && dtfs.size() > 0) {
					String tableName = dtm.getDbtablename().toUpperCase();
					String viewColumnPrefix = tableName.toLowerCase();
					Map<String, String> trg_column = new HashMap<String, String>();
					for (DictTFactorPO dtf : dtfs) {
						String columnid = dtf.getColumnid();
						factorType.put(columnid, dtf);
						String columnDBName = dtf.getChangename() == null
								|| "".equals(dtf.getChangename()) ? dtf
								.getDbcolumnname().toUpperCase() : dtf
								.getChangename().toUpperCase();
						// DictTFactorPO oldDtf =
						// this.dictTFactorMapper.getDictTFactor(columnid);
						// 如果是绑定列
						if ("1".equals(dtf.getIsbandcol())) {

							DictTFactorcodePO dtfc = this.dictTFactorcodeMapper
									.getDictTFactorcode(dtf.getBandrefdwcol());
							if (dtfc != null) {
								DictTModelcodePO dtmc = dictTModelcodeMapper
										.getDictTModelcode(dtfc.getTableid());
								DictTFactorPO d = this.dictTFactorMapper
										.getDictTFactor(dtf.getBandcolumnid());
								if (d != null && dtmc != null) {
									String smallName = dtmc.getDbtablename()
											.toLowerCase();
									view.append(" (SELECT ");
									view.append(smallName
											+ "."
											+ dtfc.getDbcolumnname()
													.toUpperCase() + " ");
									view.append("FROM "
											+ dtmc.getDbtablename()
													.toUpperCase() + " "
											+ smallName + " ");
									view.append("WHERE " + smallName
											+ ".GUID = " + viewColumnPrefix
											+ "."
											+ d.getDbcolumnname().toUpperCase()
											+ " AND ROWNUM < 2) ");
									view.append(DictDBConstants.AS + " ");
									view.append(columnDBName);
									view.append(",");
								}
							}
						}
						// 如果是虚列
						else if ("1".equals(dtf.getIsvirtual())) {
							// 虚列
							view.append(viewColumnPrefix).append(".");
							view.append(dtf.getDbcolumnname().toUpperCase())
									.append(" ");
							view.append(DictDBConstants.AS).append(" ");
							view.append(columnDBName);
							view.append(",");
						}
						// 非标题列
						else if (dtf.getDatatype() != 4) {
							view.append(viewColumnPrefix).append(".");
							view.append(dtf.getDbcolumnname().toUpperCase())
									.append(" ");
							view.append(DictDBConstants.AS).append(" ");
							// 列 字段
							trg_column.put(columnid,
									(dtf.getDbcolumnname().toUpperCase()));
							view.append(columnDBName);
							view.append(",");
						}
						String str = trg_as.get(columnid);
						if (str == null) {// as 字段
							trg_as.put(columnid, columnDBName);
						}
						String settings = settingMap.get(columnid);
						// 关系 字段
						if (settings != null && !"".equals(settings)) {
							String[] ids = settings.split(",");
							for (String id : ids) {
								DictTFactorPO df = this.dictTFactorMapper
										.getDictTFactor(id);
								DictTModelPO d = this.dictTModelMapper
										.getDictTModel(df.getTableid());
								String tName = d.getDbtablename().toUpperCase();
								tName = prefix + tName;
								Map<String, String> nm = new HashMap<String, String>();

								if (setting_map.get(tName) != null) {
									nm = setting_map.get(tName);
									nm.put(id, df.getDbcolumnname()
											.toUpperCase());
									setting_map.put(tName, nm);
								} else {
									nm.put(id, df.getDbcolumnname()
											.toUpperCase());
									setting_map.put(tName, nm);
								}

								trg_as.put(id, columnDBName);
								factorType.put(id, df);
							}
						}
					}
					trgs.put(prefix + tableName, trg_column);
					// 表名
					tableNameStr.append(tableName).append(" ")
							.append(viewColumnPrefix).append(",");
				}
			}
			view.append(" STATUS,");
			view.append(" DBVERSION,");
			String beforViewStr = view.substring(0, view.length() - 1);
			view.setLength(0);
			view.append(beforViewStr);
			view.append(" ").append(DictDBConstants.FROM).append(" ");
			String tableStr = tableNameStr.substring(0,
					tableNameStr.length() - 1);
			view.append(tableStr + " ");
			if (dictTModel.getTabswhere() != null
					&& !dictTModel.getTabswhere().trim().equals("")) {
				view.append(DictDBConstants.WHERE).append(" ");
				view.append(dictTModel.getTabswhere()).append(" ");
			}
			if (secusql != null && !"".equals(secusql)) {
				view.append(")  WHERE ");
				view.append(secusql).append(" ");
			}
			// put view
			returnMap.put(DictDBConstants.VIEWFORUPDATEVIEW, view.toString());

			String triggersql = createTriggerForUpdateView(viewName,
					settingMap, trgs, setting_map, trg_as, factorType);

			// put trigger
			returnMap.put(DictDBConstants.TRIGGERFORUPDATEVIEW, triggersql);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return returnMap;
	}

	@Override
	public String createPK(String dbStatus, String tableName,
			List<String> listPK) {
		String prefix = DictDBConstants.PREFIX_TABLE;
		if (dbStatus.equals("0")) {
			prefix = "";
		}
		String sql = null;
		if (listPK != null && listPK.size() > 0) {// 设定主键
			StringBuilder sb = new StringBuilder();
			sb.delete(0, sb.length());
			sb.append(DictDBConstants.ALTER_TABLE + " ");
			sb.append(prefix + tableName + " ");
			sb.append(DictDBConstants.ADD_CONSTRAINT + " ");
			sb.append("PK_" + prefix + tableName.toUpperCase() + " ");
			sb.append(DictDBConstants.PRIMARY_KEY + " ");
			int j = 0;
			sb.append(" (");
			for (String str : listPK) {
				sb.append(str);
				if (j < listPK.size() - 1) {
					sb.append(",");
				}
				j++;
			}
			// sb.append(" )  DISABLE ");
			sb.append(" ) ");
			sql = sb.toString();
		}
		return sql;
	}

	@Override
	public String createIndex(String dbstatus, String tableName,
			List<String> listIndex) {

		String prefix = DictDBConstants.PREFIX_TABLE;
		if (dbstatus.equals("0")) {
			prefix = "";
		}
		String sql = null;
		if (listIndex != null && listIndex.size() > 0) {
			StringBuilder newIndex = new StringBuilder();
			newIndex.append("CREATE UNIQUE INDEX ");
			newIndex.append("IN_" + prefix + tableName + " ");
			newIndex.append(DictDBConstants.ON + " ");
			newIndex.append(prefix + tableName + " ");
			int j = 0;
			newIndex.append(" (");
			for (String str : listIndex) {
				newIndex.append(str);
				if (j < listIndex.size() - 1) {
					newIndex.append(",");
				}
				j++;
			}
			newIndex.append(" )");
			sql = newIndex.toString();
		}
		return sql;
	}

	/**
      * 
      */
	@Override
	public int getTableDataCount(String tableName) throws Exception {
		// 如果包已经不存在,直接返回0
		String ifExistsTableSql = DictDBConstants.IF_EXISTS_TYPE_TABLE + "'"
				+ tableName + "'";
		Integer isTableExist = getIfExistsInDB(ifExistsTableSql);
		if (isTableExist == 1) {
			return dictDBExecuteMapper.getTableDataCount(tableName);
		}
		return 0;
	}

	@Override
	public List<ConsistencyPO> getConsistencyByArgs(String tableName)
			throws Exception {

		List<ConsistencyPO> listConsistency = null;
		try {
			listConsistency = null;
			if (tableName != null && !"".equals(tableName)) {
				listConsistency = this.dictDBExecuteMapper
						.getConsistencyByTableName(tableName);
			} else {
				listConsistency = this.dictDBExecuteMapper.getAllConsistency();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return listConsistency;
	}

	@Override
	public List<Map> getNeedRecoverColsForBakTable(String appId, boolean isTable) {
		if (isTable) {
			return this.dictDBExecuteMapper
					.getNeedRecoverColsForBakTable(appId);
		} else {
			return this.dictDBExecuteMapper
					.getNeedRecoverColsViewForBakTable(appId);
		}

	}

	/**
	 * 刷新视图公式触发器
	 * 
	 * @param tableID
	 *            表ID
	 * @return 刷新视图公式触发器
	 * @throws Exception
	 */
	public void createFormulaTrigger(String tableID) throws Exception {
		dictDBExecuteMapper.createFormulaTrigger(tableID);
	}

	/**
	 * 刷新表视图
	 * 
	 * @param tableID
	 *            表ID
	 * @return 刷新表视图
	 * @throws Exception
	 */
	public void recreateViews(String tableID) throws Exception {
		dictDBExecuteMapper.recreateViews(tableID);
	}
	@Override
	public String createFactorcodeView(String ispartition, String dbStatus,
			String tableName,Map<String, List<DictTFactorcodePO>> viewColumMap, String secusql,
			boolean isAllYear) {
		String prefix = DictDBConstants.PREFIX_TABLE;
		StringBuilder sb = new StringBuilder();
		StringBuilder column = new StringBuilder();
		StringBuilder table = new StringBuilder();
		// 取tableID
		DictTModelcodePO dictTModel = dictTModelcodeMapper.getDictTModelcodeByDBName(tableName);
		String tableID = "";
		if (dictTModel != null) {
			tableID = dictTModel.getTableid();
		}
		String t = prefix + tableName;
		// 配置视图名加后缀
//		if (isConfig) {
//			tableName += DictDBConstants.SUFFIX_CONFIG_VIEW;
//		}
		boolean isband = false;
		for (Entry<String, List<DictTFactorcodePO>> m : viewColumMap.entrySet()) {
			// from 表
			t = m.getKey().toLowerCase();
			// select t.列 as name
			List<DictTFactorcodePO> listFactor = m.getValue();

			for (DictTFactorcodePO dtf : listFactor) {
				column.append(dtf.getDbcolumnname() + ",");
			}

			if (isband) {
				table.append(m.getKey().toUpperCase() + " " + t + ",");
			} else {
				table.append(m.getKey().toUpperCase() + ",");
			}
		}

		sb.append(DictDBConstants.CREATE_OR_REPLACE + " ");
		sb.append(DictDBConstants.VIEW + " ");
		sb.append(tableName + " ");
		sb.append(DictDBConstants.AS + " ");
		sb.append(DictDBConstants.SELECT + " ");
		/*
		 * column.append(t+".STATUS "); column.append(DictDBConstants.AS+
		 * " STATUS,"); column.append(t+".DBVERSION ");
		 * column.append(DictDBConstants.AS+ " DBVERSION,");
		 */
		column.append(" STATUS,");
		column.append(" DBVERSION,");
//		if (isTask) {
//			column.append(" TASKID,");
//		}
		String c = column.substring(0, column.length() - 1);
		sb.append(c + " ");

		sb.append(DictDBConstants.FROM + " ");

		String str = table.substring(0, table.length() - 1);
		sb.append(str + " ");

		if ("1".equals(dbStatus) && "1".equals(ispartition)) {
			sb.append(" WHERE YEAR=global_multyear_cz.Secu_f_GLOBAL_PARM('YEAR') AND STATUS='1' ");
		} else if ("2".equals(dbStatus) && "1".equals(ispartition)) {
			sb.append(" WHERE 1=1 ");
			if(!isAllYear)
				sb.append(" AND YEAR=global_multyear_cz.Secu_f_GLOBAL_PARM('YEAR') ");
			sb.append(" AND STATUS='1' ");
		} else {
			sb.append(" WHERE STATUS='1' ");
		}
		if (secusql != null && !"".equals(secusql)) {
			sb.append(" AND ").append(secusql).append(" ");
		}
		return sb.toString();
	}
}

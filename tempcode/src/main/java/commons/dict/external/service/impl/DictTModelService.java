package commons.dict.external.service.impl;

import gov.mof.fasp2.dic.table.service.IDicTableQueryService;
import com.longtu.framework.util.ServiceFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.dict.constants.DictCacheKey;
import com.tjhq.commons.dict.external.dao.DictTFactorMapper;
import com.tjhq.commons.dict.external.dao.DictTModelMapper;
import com.tjhq.commons.dict.external.dao.DictTSuitMapper;
import com.tjhq.commons.dict.external.po.DictTDealtypePO;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTFactorcodePO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.po.DictTSuitPO;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.dict.service.IDictDBExecuteService;
import com.tjhq.commons.dict.service.IDictTDealtypeService;
import com.tjhq.commons.dict.service.IDictTModelSelfService;
import com.tjhq.commons.dict.util.DictDBConstants;
import com.tjhq.commons.setting.input.dao.ConditionSetMapper;
import com.tjhq.commons.setting.input.po.ConditionSetPo;

/**
 * 表接口实现
 * 
 * @author xujingsi
 * 
 */
@Service
@Transactional(readOnly = true)
public class DictTModelService implements IDictTModelService {

	@Resource
	private DictTModelMapper dictTModelMapper;

	@Resource
	private DictTFactorMapper dictTFactorMapper;

	@Resource
	private DictTSuitMapper dictTSuitMapper;

	@Resource
	private IDataCacheService dataCacheService;

	@Resource
	private IDictTModelSelfService dictTModelSelfService;

	@Resource
	private IDictTModelcodeService dictTModelcodeService;

	@Resource
	private DictTFactorcodeService dictTFactorcodeService;

	@Resource
	private ConditionSetMapper conditionSetMapper;

	@Resource
	private IDictTDealtypeService dictTDealtypeService;

	@Resource
	private IDictDBExecuteService dictDBExecuteService;

	/**
	 * 获取单个DictTModel
	 * 
	 * @param tableid
	 *            表id
	 * @param childColumn
	 *            是否包含表中的 全部列 包括标题 List<DictTFactorPO> （默认不带）
	 * @return DictTModel
	 */
	public DictTModelPO getDictTModelByID(String tableid, boolean childColumn) {
		DictTModelPO dtm = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.ID.getCacheKey(), tableid,
				"getDictTModelByID", String.valueOf(childColumn) };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			dtm = (DictTModelPO) dataCacheService.get(keys);
			return dtm;
		}
		if (tableid != null && !"".equals(tableid)) {
			dtm = dictTModelMapper.getDictTModel(tableid);
			if (childColumn && dtm != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tableid", tableid);
				List<DictTFactorPO> dictTFactorList = dictTFactorMapper
						.findDictTFactor(map);
				dtm.setDictTFactorList(dictTFactorList);
			}
			// 加入到缓存
			dataCacheService.put(keys, dtm);
		}
		return dtm;
	}

	/**
	 * 获取单个DictTModel（包含表中的全部列）
	 * 
	 * @param tableid
	 *            表id
	 * @param isleaf
	 *            是否末端节点List<DictTFactorPO>（默认全部）
	 * @return DictTModel
	 */
	public DictTModelPO getDictTModelOFID(String tableid, boolean isleaf) {
		DictTModelPO dtm = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.ID.getCacheKey(), tableid,
				"getDictTModelOFID", String.valueOf(isleaf) };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			dtm = (DictTModelPO) dataCacheService.get(keys);
			return dtm;
		}
		if (tableid != null && !"".equals(tableid)) {
			dtm = dictTModelMapper.getDictTModel(tableid);
			if (dtm != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tableid", tableid);
				if (isleaf) {
					map.put("isleaf", "1");
				}
				List<DictTFactorPO> dictTFactorList = dictTFactorMapper
						.findDictTFactor(map);
				dtm.setDictTFactorList(dictTFactorList);
			}
			// 加入到缓存
			dataCacheService.put(keys, dtm);
		}
		return dtm;
	}

	/**
	 * 获取单个DictTModel
	 * 
	 * @param dbtablename
	 *            物理表名称
	 * @param childColumn
	 *            是否包含表中的 列（默认不带）
	 * @return DictTModel
	 */
	public DictTModelPO getDictTModelByDBtableName(String dbtablename,
			boolean childColumn) {
		DictTModelPO dtm = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.DBNAME.getCacheKey(), dbtablename,
				"getDictTModelByDBtableName", String.valueOf(childColumn) };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			dtm = (DictTModelPO) dataCacheService.get(keys);
			return dtm;
		}
		if (dbtablename != null && !"".equals(dbtablename)) {
			dtm = dictTModelMapper.getDictTModelByName(dbtablename);
			if (childColumn) {
				if (dtm != null) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("tableid", dtm.getTableid());
					List<DictTFactorPO> dictTFactorList = dictTFactorMapper
							.findDictTFactor(map);
					dtm.setDictTFactorList(dictTFactorList);
				}
			}
			// 加入到缓存
			dataCacheService.put(keys, dtm);
		}
		return dtm;
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
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictSuit.ID.getCacheKey(), suitid,
				"getDictTModelsBySuitId", String.valueOf(childColumn) };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			dtmList = (List<DictTModelPO>) dataCacheService.get(keys);
			return dtmList;
		}
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
			// 加入到缓存
			dataCacheService.put(keys, dtmList);
		}
		return dtmList;
	}

	@Override
	public List<DictTModelPO> getDictTModelsByName(String name) {
		List<DictTModelPO> dtmList = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.NAME.getCacheKey(), name,
				"getDictTModelsByName" };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			dtmList = (List<DictTModelPO>) dataCacheService.get(keys);
			return dtmList;
		}
		if (name != null && !"".equals(name)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", name);
			dtmList = this.dictTModelMapper.findDictTModel(map);
			// 加入到缓存
			dataCacheService.put(keys, dtmList);
		}
		return dtmList;
	}

	@Override
	public List<DictTModelPO> getDictTModelsByTableType(String tabletype) {
		List<DictTModelPO> dtmList = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.TYPE.getCacheKey(), tabletype,
				"getDictTModelsByTableType" };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			dtmList = (List<DictTModelPO>) dataCacheService.get(keys);
			return dtmList;
		}
		if (tabletype != null && !"".equals(tabletype)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tabletype", tabletype);
			dtmList = this.dictTModelMapper.findDictTModel(map);
			// 加入到缓存
			dataCacheService.put(keys, dtmList);
		}
		return dtmList;
	}

	@Override
	public List<DictTModelPO> getAllDictTModels() {
		List<DictTModelPO> dtmList = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.ALL.getCacheKey(), "getAllDictTModels" };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			dtmList = (List<DictTModelPO>) dataCacheService.get(keys);
			return dtmList;
		}
		dtmList = this.dictTModelMapper.getAllDictTModel();
		// 加入到缓存
		dataCacheService.put(keys, dtmList);
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

	/**
	 * 通过tableID获取物理表，取出‘执行SQL’字段，进行数据库执行
	 */

	@Override
	public void getProcessInitSQLByTableId(String tableid) {
		try {
			if (null != tableid && !"".equals(tableid)) {
				DictTModelPO dictTModelPO = getDictTModelByID(tableid, false);
				String initsql = dictTModelPO.getInitsql();
				if (null != initsql && !"".equals(initsql)) {
					dictDBExecuteService.ExecDllLongForParam(initsql);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//
	// public List<DictTModelPO> getDictTModelsBySuitIdAndUser(String suitid,
	// boolean childColumn, String userId) {
	//
	// return null;
	// }

	@Override
	public Map<String, Object> getTableByDealtype(String dealtype,
			String tableType, String appId) {
		List<Map<String, Object>> tables = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.DEALTYPE.getCacheKey(), dealtype,
				"getTableByDealtype", tableType, appId };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			tables = (List<Map<String, Object>>) dataCacheService.get(keys);
		} else {
			Map querymap = new HashMap();
			querymap.put("dealtype", dealtype);
			querymap.put("type", tableType);
			querymap.put("appId", appId);
			tables = this.dictTModelMapper.getTableByDealtype(querymap);
			// 加入到缓存
			dataCacheService.put(keys, tables);
		}
		if (tables.size() == 0 || tables.size() > 1) {
			return null;
		} else {
			return tables.get(0);
		}
	}

	/**
	 * 批量同步全部注册平台表
	 * 
	 * @throws Exception
	 */
	public void registerAllTableToDic() throws Exception {
		try {
			List<DictTModelPO> dictTModelList = dictTModelMapper
					.getAllDictTModel();
			IDicTableQueryService dicTableQueryService = (IDicTableQueryService) ServiceFactory
					.getBean("fasp2.dictable.queryService");
			for (DictTModelPO dictTModel : dictTModelList) {
				boolean isRegisteredTable = true;
				String tableDBName = dictTModel.getDbtablename().toUpperCase();
				if (dicTableQueryService.getDicTableWithNoException(tableDBName) == null) {
					isRegisteredTable = false;
				}
				// 如果不存在表信息，补登记表和列信息
				if (!isRegisteredTable) {
					List<DictTFactorPO> dtfList = dictTFactorMapper
							.getLeafFactorListByTableID(dictTModel.getTableid());
					dictTModel.setDictTFactorList(dtfList);
					try {
						dictTModelSelfService.registerDicTable(dictTModel);
					} catch (Exception e) {
						e.printStackTrace();
						throw new Exception("同步注册平台表出错");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("批量同步全部注册平台表出错");
		}
	}

	/**
	 * 重新创建Config视图
	 * 
	 * @param tableID
	 *            业务表ID
	 * @throws Exception
	 */
	public void rereateConfigView(String tableID) throws Exception {
		String prefix = DictDBConstants.PREFIX_TABLE;
		StringBuilder sb = new StringBuilder();
		StringBuilder column = new StringBuilder();
		StringBuilder table = new StringBuilder();
		DictTModelPO dictTModel = getDictTModelOFID(tableID, true);
		String tableName = dictTModel.getDbtablename().toUpperCase();
		String t = (prefix + tableName).toLowerCase();
		Boolean isTask = false;
		if ("1".equals(dictTModel.getIstask())) {
			isTask = true;
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
		// 配置视图名加后缀
		if (isConfig) {
			tableName += DictDBConstants.SUFFIX_CONFIG_VIEW;
		}
		String ispartition = dictTModel.getIspartition();
		String dbStatus = dictDBExecuteService.getGlobalIsmultdb();
		boolean isband = false;
		List<DictTFactorPO> listFactor = dictTModel.getDictTFactorList();

		for (DictTFactorPO dtf : listFactor) {

			if ("1".equals(dtf.getIsbandcol())) {
				// 绑定列
				if (dtf.getBandcolumnid() != null
						&& !"".equals(dtf.getBandcolumnid())
						&& dtf.getBandrefdwcol() != null
						&& !"".equals(dtf.getBandrefdwcol())) {

					DictTFactorcodePO dtfc = this.dictTFactorcodeService
							.getDictTFactorcodePOByColumnId(dtf
									.getBandrefdwcol());
					if (dtfc != null) {
						DictTFactorPO d = this.dictTFactorMapper
								.getDictTFactor(dtf.getBandcolumnid());
						DictTModelcodePO dtmc = dictTModelcodeService
								.getDictTModelcodePOByID(d.getCsid());
						if (dtf != null) {
							isband = true;
							String smallName = dtmc.getDbtablename()
									.toLowerCase();
							column.append(" (SELECT ");
							column.append(smallName + "."
									+ dtfc.getDbcolumnname().toUpperCase()
									+ " ");
							column.append("FROM "
									+ dtmc.getDbtablename().toUpperCase() + " "
									+ smallName + " ");
							column.append("WHERE " + smallName + ".GUID = " + t
									+ "." + d.getDbcolumnname().toUpperCase()
									+ " ) ");
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
			table.append(t.toUpperCase() + " " + t + ",");
		} else {
			table.append(t.toUpperCase() + ",");
		}

		sb.append(DictDBConstants.CREATE_OR_REPLACE + " ");
		sb.append(DictDBConstants.VIEW + " ");
		sb.append(tableName + " ");
		sb.append(DictDBConstants.AS + " ");
		sb.append(DictDBConstants.SELECT + " ");
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
			sb.append(" WHERE YEAR=global_multyear_cz.Secu_f_GLOBAL_PARM('YEAR') ");
			sb.append(" AND ");
			sb.append(" PROVINCE=global_multyear_cz.Secu_f_GLOBAL_PARM('DIVID') AND STATUS='1' ");
		} else {
			sb.append(" WHERE STATUS='1' ");
		}
		String secusql = dictTModel.getSecusql();
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
		try {
			dictDBExecuteService.ExecDllLongForParam(sb.toString());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean getExistRela(String tableID) throws Exception {
		int existRela = dictTModelMapper.existRexistRela(tableID);
		return existRela > 0 ? true : false;
	}
}

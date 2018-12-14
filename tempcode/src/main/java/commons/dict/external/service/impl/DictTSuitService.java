package commons.dict.external.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.dict.constants.DictCacheKey;
import com.tjhq.commons.dict.dao.DictTAppregisterMapper;
import com.tjhq.commons.dict.external.dao.DictTModelMapper;
import com.tjhq.commons.dict.external.dao.DictTSuitMapper;
import com.tjhq.commons.dict.external.po.DictTAppregisterPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTSuitPO;
import com.tjhq.commons.dict.external.service.IDictTSuitService;
import com.tjhq.commons.setting.external.po.TreeNode;

/**
 * 套表接口实现
 * 
 * @author xujingsi
 * 
 */
@Service
@Transactional(readOnly = true)
public class DictTSuitService implements IDictTSuitService {

	@Resource
	private DictTSuitMapper dictTSuitMapper;

	@Resource
	private DictTModelMapper dictTModelMapper;

	@Resource
	private DictTAppregisterMapper dictTAppregisterMapper;

	@Resource
	private IDataCacheService dataCacheService;

	/**
	 * 获取单个套表 BY ID
	 * 
	 * @param suitid
	 *            ID
	 * @return DictTSuit
	 */
	public DictTSuitPO getDictTSuitByID(String suitid) {
		DictTSuitPO dts = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictSuit.ID.getCacheKey(), suitid,
				"getDictTSuitByID" };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			dts = (DictTSuitPO) dataCacheService.get(keys);
			return dts;
		}
		if (suitid != null && !"".equals(suitid)) {
			dts = this.dictTSuitMapper.getDictTSuit(suitid);
			// 加入到缓存
			dataCacheService.put(keys, dts);
		}
		return dts;
	}

	/**
	 * 获取某个级别下的全部套表（树状结构）；
	 * 
	 * @param appid
	 *            应用ID
	 * @param suitid
	 *            套表ID(默认0 获取全部)
	 * @param childTable
	 *            是否获取末端节点的真实表
	 * @return
	 */
	public List<DictTSuitPO> getDictTSuitsForTree(String appid, String suitid,
			boolean childTable) {
		List<DictTSuitPO> dtsList = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictSuit.ID.getCacheKey(), suitid,
				"getDictTSuitsForTree",   appid, String.valueOf(childTable) };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			dtsList = (List<DictTSuitPO>) dataCacheService.get(keys);
			return dtsList;
		}
		if (appid != null && !"".equals(appid)) {

			List<DictTSuitPO> dtss = null;

			Map<String, Object> dtsMap = new HashMap<String, Object>();
			dtsMap.put("appid", appid);
			dtss = this.dictTSuitMapper.findDictTSuit(dtsMap);

			if (suitid == null || "".equals(suitid) || "0".equals(suitid)) {
				// 默认套表ID 获取全部
				DictTSuitPO dts = new DictTSuitPO();
				dts.setSuitid("0");
				dts.setSuperid("-1");
				dts.setIsleaf("0");
				dts = dictTSuitChildren(dts, dtss, childTable);// 递归 tree
																// childTable
																// 是否获取末端节点的真实表
				dtsList = dts.getDictTSuitList();
			} else {
				// 指定ID
				for (DictTSuitPO dts : dtss) {
					if (suitid.equals(dts.getSuitid())) {
						dts = dictTSuitChildren(dts, dtss, childTable);// 递归
																		// tree
																		// childTable
																		// 是否获取末端节点的真实表
						dtsList = dts.getDictTSuitList();
						break;
					}
				}
			}
			// 加入到缓存
			dataCacheService.put(keys, dtsList);

		}
		return dtsList;
	}

	public List<DictTSuitPO> getDictTSuits(String appid, String suitid,
			boolean childTable) {

		List<DictTSuitPO> dtsList = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictSuit.APP.getCacheKey(), appid,
				"getDictTSuits", suitid, String.valueOf(childTable) };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			dtsList = (List<DictTSuitPO>) dataCacheService.get(keys);
			return dtsList;
		}
		if (appid != null && !"".equals(appid)) {
			dtsList = this.dictTSuitMapper.getDictTSuitByAppID(appid);
			if (suitid == null || "".equals(suitid) || "0".equals(suitid)) {
				// 默认 全部
			} else {
				// 指定 suitid
				List<DictTSuitPO> list = new ArrayList<DictTSuitPO>();
				for (DictTSuitPO dts : dtsList) {
					if (suitid.equals(dts.getSuitid())) {
						// list.add(dts);
						list = dictTSuitChildren(dts, dtsList, list);
						break;
					}
				}
				dtsList = list;
			}
			// childTable 是否获取末端节点的真实表
			if (childTable) {
				for (DictTSuitPO dts : dtsList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("suitid", dts.getSuitid());
					dts.setDictTModelList(this.dictTModelMapper
							.findDictTModel(map));
				}
			}
			// 加入到缓存
			dataCacheService.put(keys, dtsList);
		}
		return dtsList;
	}

	// public List<DictTSuitPO> getDictTSuitsByUserForTree(String appid,
	// String suitid, boolean childTable, String userId) {
	//
	// return null;
	// }
	//
	// public List<DictTSuitPO> getDictTSuitsByUser(String appid, String suitid,
	// boolean childTable, String userId) {
	//
	// return null;
	// }

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
				/* if("1".equals(pcs.get(i).getIsleaf())){ */
				list.add(pcs.get(i));
				// }
			}
		}
		if (p.getDictTSuitList().size() > 0) {
			for (DictTSuitPO p_ : p.getDictTSuitList())
				dictTSuitChildren(p_, pcs, list);
		}
		return list;
	}

	/**
	 * 结构排列
	 * 
	 * @param pc
	 * @param pcs
	 * @return
	 */
	@SuppressWarnings("unused")
	private DictTSuitPO dictTSuitChildren(DictTSuitPO p, List<DictTSuitPO> pcs,
			boolean childTable) {
		List<Integer> indexs = new ArrayList<Integer>();
		p.setDictTModelList(new ArrayList<DictTModelPO>());

		for (int i = 0; i < pcs.size(); i++) {
			DictTSuitPO pps = pcs.get(i);
			if (p.getSuitid().equals(pps.getSuperid())) {
				if ("1".equals(pps.getIsleaf()) && childTable) { // 带 套表末端下的真实表
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("suitid", pps.getSuitid());
					pps.setDictTModelList(this.dictTModelMapper
							.findDictTModel(map));
				}
				p.getDictTSuitList().add(pps);
			}
		}
		if (p.getDictTSuitList().size() > 0) {
			for (DictTSuitPO p_ : p.getDictTSuitList())
				dictTSuitChildren(p_, pcs, childTable);
		}
		return p;
	}

	@Override
	public List<DictTSuitPO> getDictTSuitsBySupperid(String appid, String suitid) {

		List<DictTSuitPO> dtsList = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictSuit.APP.getCacheKey(), appid,
				"getDictTSuitsBySupperid",  suitid};
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			dtsList = (List<DictTSuitPO>) dataCacheService.get(keys);
			return dtsList;
		}
		Map<String, Object> dtsMap = new HashMap<String, Object>();
		dtsMap.put("appid", appid);
		dtsMap.put("superid", suitid);
		dtsList = this.dictTSuitMapper.findDictTSuit(dtsMap);
		// 加入到缓存
		dataCacheService.put(keys, dtsList);
		return dtsList;
	}

	@Override
	public List<DictTAppregisterPO> getAllDictTAppregister() {
		return dictTAppregisterMapper.getAllDictTAppregister();
	}

	@Override
	public List<TreeNode> getDictTSuitTree(String appid, String showApp,
			String string) {
		List<TreeNode> suitTreeList = null;
		if (showApp == null || showApp.trim().equals("")){
			showApp = "1";
		}
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictSuit.APP.getCacheKey(), appid,
				"getDictTSuitTree", showApp};
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			suitTreeList = (List<TreeNode>) dataCacheService.get(keys);
			return suitTreeList;
		}
		Map<String, Object> dtsMap = new HashMap<String, Object>();
		dtsMap.put("appID", appid);
		dtsMap.put("showApp", showApp);
		suitTreeList = dictTSuitMapper.findDictTSuitsByAppId(dtsMap);
		// 加入到缓存
		dataCacheService.put(keys, suitTreeList);
		return suitTreeList;
	}

	@Override
	public List<TreeNode> getAllDictTSuitWithModelTree(String appid,
			String showApp) {
		List<TreeNode> suitTreeList = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictSuit.APP.getCacheKey(), appid,
				"getAllDictTSuitWithModelTree", showApp};
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			suitTreeList = (List<TreeNode>) dataCacheService.get(keys);
			return suitTreeList;
		}
		Map<String, Object> dtsMap = new HashMap<String, Object>();
		dtsMap.put("appId", appid);
		suitTreeList = dictTSuitMapper.getAllDictTSuitWithModelTree(dtsMap);
		// 加入到缓存
		dataCacheService.put(keys, suitTreeList);
		return suitTreeList;
	}

	/**
	 * 得到指定业务类型下所有可定义公式报表树
	 * 
	 * @param appID
	 * @return 可定义公式报表树
	 */
	public List<TreeNode> getAllDictTSuitWithModelTree4Formula(String appID) {
		List<TreeNode> suitTreeList = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictSuit.APP.getCacheKey(), appID,
				"getAllDictTSuitWithModelTree4Formula" };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			suitTreeList = (List<TreeNode>) dataCacheService.get(keys);
			return suitTreeList;
		}
		Map<String, Object> dtsMap = new HashMap<String, Object>();
		dtsMap.put("appId", appID);
		suitTreeList = dictTSuitMapper
				.getAllDictTSuitWithModelTree4Formula(dtsMap);
		// 加入到缓存
		dataCacheService.put(keys, suitTreeList);
		return suitTreeList;
	}

}

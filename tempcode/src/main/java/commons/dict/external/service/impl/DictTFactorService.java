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
import com.tjhq.commons.dict.external.dao.DictTFactorMapper;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;

/**
 * 列接口实现
 * 
 * @author xujingsi
 * 
 */
@Service
@Transactional(readOnly = true)
public class DictTFactorService implements IDictTFactorService {

	@Resource
	private DictTFactorMapper dictTFactorMapper;

	@Resource
	private IDataCacheService dataCacheService;

	/**
	 * 获取列
	 * 
	 * @param columnid
	 *            列ID
	 * @return DictTFactor
	 */
	public DictTFactorPO getDictTFactorByColumnId(String columnid) {
		DictTFactorPO dtf = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictColumn.ID.getCacheKey(), columnid,
				"getDictTFactorByColumnId" };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			dtf = (DictTFactorPO) dataCacheService.get(keys);
			return dtf;
		}
		if (columnid != null && !"".equals(columnid)) {
			dtf = dictTFactorMapper.getDictTFactor(columnid);
			// 加入到缓存
			dataCacheService.put(keys, dtf);
		}
		return dtf;
	}

	/**
	 * 获取列
	 * 
	 * @param dbcolumnname
	 *            列名称
	 * @return
	 */
	public DictTFactorPO getDictTFactorByDBColumnName(String dbcolumnname,
			String tableid) {
		DictTFactorPO dtf = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictColumn.DBNAME.getCacheKey(), dbcolumnname,
				"getDictTFactorByDBColumnName", tableid };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			dtf = (DictTFactorPO) dataCacheService.get(keys);
			return dtf;
		}
		if (dbcolumnname != null && !"".equals(dbcolumnname)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableid", tableid);
			map.put("dbcolumnname", dbcolumnname);
			List<DictTFactorPO> dtfs = dictTFactorMapper.findDictTFactor(map);
			if (dtfs != null && dtfs.size() > 0) {
				dtf = dtfs.get(0);
				// 加入到缓存
				dataCacheService.put(keys, dtf);
			}
		}
		return dtf;
	}

	/**
	 * 获取某个表下不可以为空的列
	 * 
	 * @param tableid
	 *            表ID
	 * @return List<DictTFactor>
	 */
	public List<DictTFactorPO> getNotNullDictTFactorsByTableId(String tableid) {
		List<DictTFactorPO> factors = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.ID.getCacheKey(), tableid,
				"getNotNullDictTFactorsByTableId" };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			factors = (List<DictTFactorPO>) dataCacheService.get(keys);
			return factors;
		}
		try {
			if (tableid != null && !"".equals(tableid)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tableid", tableid);
				map.put("nullable", '0');
				factors = dictTFactorMapper.findDictTFactor(map);
				// 加入到缓存
				dataCacheService.put(keys, factors);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return factors;
	}

	/**
	 * 获取某个表下可见并且引用列
	 * 
	 * @param tableid
	 *            表ID
	 * @return List<DictTFactor>
	 */
	public List<DictTFactorPO> getVisibleRefTreeByTableId(String tableid) {
		List<DictTFactorPO> factors = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.ID.getCacheKey(), tableid,
				"getVisibleRefTreeByTableId" };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			factors = (List<DictTFactorPO>) dataCacheService.get(keys);
			return factors;
		}
		try {
			if (tableid != null && !"".equals(tableid)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tableid", tableid);
				map.put("isvisible", '1');
				factors = dictTFactorMapper.findDictTFactorVisilbeRef(map);
				// 加入到缓存
				dataCacheService.put(keys, factors);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return factors;
	}

	/**
	 * 获取某个表下的所有列
	 * 
	 * @param tableid
	 *            表ID
	 * @return List<DictTFactor>
	 */
	public List<DictTFactorPO> getDictTFactorsByTableId(String tableid) {
		List<DictTFactorPO> factors = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.ID.getCacheKey(), tableid,
				"getDictTFactorsByTableId" };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			factors = (List<DictTFactorPO>) dataCacheService.get(keys);
			return factors;
		}
		if (tableid != null && !"".equals(tableid)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableid", tableid);
			factors = dictTFactorMapper.findDictTFactor(map);
			// 加入到缓存
			dataCacheService.put(keys, factors);
		}
		return factors;
	}

	/**
	 * 获取某个表下的所有列 (树状结构)
	 * 
	 * @param tableid
	 *            表ID
	 * @return List<DictTFactor>
	 */
	public List<DictTFactorPO> getDictTFactorsByTableIdForTree(String tableid) {
		List<DictTFactorPO> factors = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.ID.getCacheKey(), tableid,
				"getDictTFactorsByTableIdForTree" };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			factors = (List<DictTFactorPO>) dataCacheService.get(keys);
			return factors;
		}
		if (tableid != null && !"".equals(tableid)) {
			Map<String, Object> map = new HashMap<String, Object>();
			// 取得列索引
			Map<String, Integer> columnIndexMap = new HashMap<String, Integer>();
			map.put("tableid", tableid);
			map.put("isleaf", "1");
			factors = dictTFactorMapper.findDictTFactor(map);
			int i = 0;
			for (DictTFactorPO dtf : factors) {
				columnIndexMap.put(dtf.getColumnid(), i);
				i++;
			}

			map.clear();
			map.put("tableid", tableid);
			List<DictTFactorPO> list = dictTFactorMapper.findDictTFactor(map);
			if (list != null && list.size() > 0) {
				DictTFactorPO root = new DictTFactorPO();
				root.setColumnid("0");
				root.setSuperid("-1");
				root.setAdmdivcode("root");
				root = dictTFactorChildren(root, list, false);
				factors = root.getDictTFactorList();
				for (DictTFactorPO dictTFactorPO : list) {
					if (columnIndexMap.containsKey(dictTFactorPO.getColumnid())) {
						dictTFactorPO.setColumnindex(columnIndexMap
								.get(dictTFactorPO.getColumnid()));
					}
				}
			}
			// 加入到缓存
			dataCacheService.put(keys, factors);
		}
		return factors;
	}

	@Override
	public List<DictTFactorPO> getDictTFactorListByColumnId(String columnid,
			String tableid, boolean childTilte) {

		List<DictTFactorPO> factors = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.ID.getCacheKey(), tableid,
				"getDictTFactorListByColumnId", columnid,
				String.valueOf(childTilte) };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			factors = (List<DictTFactorPO>) dataCacheService.get(keys);
			return factors;
		}
		if (columnid != null && !"".equals(columnid)) {
			factors = new ArrayList<DictTFactorPO>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableid", tableid);
			if (!"-1".equals(columnid)) {
				map.put("superid", columnid);
			}
			factors = this.dictTFactorMapper.findDictTFactor(map);
			// 加入到缓存
			dataCacheService.put(keys, factors);
		}
		return factors;
	}

	/**
	 * 根据superID得到下级列的列表
	 * 
	 * @param superID
	 *            父ID
	 * @param tableID
	 *            表ID
	 * @return 下级列的列表
	 */
	public List<DictTFactorPO> getDictTFactorListBySuperID(String superID,
			String tableID) {
		List<DictTFactorPO> factors = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.ID.getCacheKey(), tableID,
				"getDictTFactorListBySuperID", superID };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			factors = (List<DictTFactorPO>) dataCacheService.get(keys);
			return factors;
		}
		try {
			if (superID != null && !"".equals(superID)) {
				factors = new ArrayList<DictTFactorPO>();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tableID", tableID);
				map.put("superID", superID);
				factors = this.dictTFactorMapper.findDictTFactorBySuperID(map);
				// 加入到缓存
				dataCacheService.put(keys, factors);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return factors;
	}

	@Override
	public List<DictTFactorPO> getDictTFactorByName(String tableid, String name) {

		List<DictTFactorPO> factors = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.ID.getCacheKey(), tableid,
				"getDictTFactorByName", name };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			factors = (List<DictTFactorPO>) dataCacheService.get(keys);
			return factors;
		}
		if (tableid != null && !"".equals(tableid)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableid", tableid);
			map.put("name", name);
			factors = dictTFactorMapper.findDictTFactor(map);
			// 加入到缓存
			dataCacheService.put(keys, factors);
		}
		return factors;
	}

	@Override
	public List<DictTFactorPO> getDictTFactorByTableidAndType(String tableid,
			String isleaf) {

		List<DictTFactorPO> factors = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.ID.getCacheKey(), tableid,
				"getDictTFactorByTableidAndType", isleaf };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			factors = (List<DictTFactorPO>) dataCacheService.get(keys);
			return factors;
		}
		if (tableid != null && !"".equals(tableid)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableid", tableid);
			if (isleaf != null && !"".equals(isleaf)) {
				map.put("isleaf", isleaf);
			}
			factors = dictTFactorMapper.findDictTFactor(map);
			if ("1".equals(isleaf)) {
				int i = 0;
				for (DictTFactorPO dtf : factors) {
					dtf.setColumnindex(i);
					i++;
				}
			}
			// 加入到缓存
			dataCacheService.put(keys, factors);
		}
		return factors;
	}

	@Override
	public List<DictTFactorPO> getDictTFactorByTableidAndIsvisible(
			String tableid, String isvisible) {

		List<DictTFactorPO> factors = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.ID.getCacheKey(), tableid,
				"getDictTFactorByTableidAndIsvisible",
				String.valueOf(isvisible) };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			factors = (List<DictTFactorPO>) dataCacheService.get(keys);
			return factors;
		}
		if (tableid != null && !"".equals(tableid)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableid", tableid);
			if (isvisible != null && !"".equals(isvisible)) {
				map.put("isvisible", isvisible);
			}
			factors = dictTFactorMapper.findDictTFactor(map);
			// 加入到缓存
			dataCacheService.put(keys, factors);
		}
		return factors;
	}

	@Override
	public List<String> getTableidsByGroupByFrmtabid(String tableid) {

		List<String> list = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.ID.getCacheKey(), tableid,
				"getTableidsByGroupByFrmtabid" };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			list = (List<String>) dataCacheService.get(keys);
			return list;
		}
		if (tableid != null && !"".equals(tableid)) {
			list = this.dictTFactorMapper.getTableidsByGroupByFrmtabid(tableid);
			// 加入到缓存
			dataCacheService.put(keys, list);
		}
		return list;
	}

	public List<DictTFactorPO> getDictTFactorsByTableIdAndUser(String tableid,
			String userId) {

		return null;
	}

	public List<DictTFactorPO> getDictTFactorsByTableIdAndUserForTree(
			String tableid, String userId) {
		List<DictTFactorPO> factors = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.ID.getCacheKey(), tableid,
				"getDictTFactorsByTableIdAndUserForTree", userId };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			factors = (List<DictTFactorPO>) dataCacheService.get(keys);
			return factors;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableid", tableid);
		List<DictTFactorPO> list = dictTFactorMapper.findDictTFactor(map);
		if (list != null) {
			DictTFactorPO root = new DictTFactorPO();
			root.setColumnid("0");
			root.setSuperid("-1");
			root.setAdmdivcode("root");
			root = dictTFactorChildren(root, list, false);
			factors = root.getDictTFactorList();
			// 加入到缓存
			dataCacheService.put(keys, list);
		}
		return factors;
	}

	/**
	 * 结构排列
	 * 
	 * @param pc
	 * @param pcs
	 * @return
	 */
	@SuppressWarnings("unused")
	private DictTFactorPO dictTFactorChildren(DictTFactorPO p,
			List<DictTFactorPO> pcs, boolean flag) {
		List<Integer> indexs = new ArrayList<Integer>();
		p.setDictTFactorList(new ArrayList<DictTFactorPO>());
		for (int i = 0; i < pcs.size(); i++) {
			if (p.getColumnid().equals(pcs.get(i).getSuperid()) && flag) {
				p.getDictTFactorList().add(pcs.get(i));
			}
			if (!flag
					&& (pcs.get(i).getSuperid() == null
							|| "0".equals(pcs.get(i).getSuperid()) || ""
								.equals(pcs.get(i).getSuperid()))) {
				p.getDictTFactorList().add(pcs.get(i));
			}
		}
		if (p.getDictTFactorList().size() > 0) {
			for (DictTFactorPO p_ : p.getDictTFactorList())
				dictTFactorChildren(p_, pcs, true);
		}
		return p;
	}

	/**
	 * 获取某个表下的所有列 (以树状结构显示)(带用户ID)
	 * 
	 * @param tableid
	 *            表ID
	 * @param userId
	 *            用户ID 例如：--A(a1,a2) B(B1,B2,B3,B4) C(C1,C2,C3)
	 * @return List<DictTFactor>
	 */
	public List<DictTFactorPO> getDictTFactorsByTableIdForTreeAndChild(
			String tableId, String columnId) {

		List<DictTFactorPO> factors = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.ID.getCacheKey(), tableId,
				"getDictTFactorsByTableIdForTreeAndChild", columnId };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			factors = (List<DictTFactorPO>) dataCacheService.get(keys);
			return factors;
		}
		if (tableId != null && !"".equals(tableId)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableid", tableId);
			factors = dictTFactorMapper.findDictTFactor(map);
			for (DictTFactorPO dictTFactorPO : factors) {
				if (null != dictTFactorPO
						&& "0".equals(dictTFactorPO.getIsleaf())) {
					List<DictTFactorPO> childFactors = null;
					childFactors = findDictTFactorList(dictTFactorPO
							.getColumnid());
					dictTFactorPO.setDictTFactorList(childFactors);
				}
			}
			// 加入到缓存
			dataCacheService.put(keys, factors);
		}
		return factors;
	}

	/**
	 * 结构排列
	 * 
	 * @param pc
	 * @param pcs
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<DictTFactorPO> findDictTFactorList(String superId) {
		List<DictTFactorPO> childFactorsList = null;
		Map<String, Object> superIdMap = new HashMap<String, Object>();
		superIdMap.put("superid", superId);
		childFactorsList = this.dictTFactorMapper.findDictTFactor(superIdMap);
		for (DictTFactorPO dictTFactorPO : childFactorsList) {
			if ("0".equals(dictTFactorPO.getIsleaf())) {
				dictTFactorPO
						.setDictTFactorList(findDictTFactorList(dictTFactorPO
								.getColumnid()));
			}
		}
		return childFactorsList;
	}

	public List<DictTFactorPO> findChildren(List<DictTFactorPO> soureList,
			List<DictTFactorPO> newlist, boolean childTilte) {
		List<DictTFactorPO> s = null;
		for (DictTFactorPO dtf : soureList) {
			if ("0".equals(dtf.getIsleaf())) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("superid", dtf.getColumnid());
				s = this.dictTFactorMapper.findDictTFactor(map);
				findChildren(s, newlist, childTilte);
				if (childTilte)
					newlist.add(dtf);
			} else if ("1".equals(dtf.getIsleaf())) {
				newlist.add(dtf);
			}
		}
		return newlist;
	}

	@Override
	public List<DictTFactorPO> getDictTFactorsPk(String tableid) {

		List<DictTFactorPO> listpk = new ArrayList<DictTFactorPO>();
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.ID.getCacheKey(), tableid,
				"getDictTFactorsPk", };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			listpk = (List<DictTFactorPO>) dataCacheService.get(keys);
			return listpk;
		}
		listpk = this.dictTFactorMapper.getDictTFactorsPk(tableid);
		if (listpk == null) {
			listpk = new ArrayList<DictTFactorPO>();
		}
		// 加入到缓存
		dataCacheService.put(keys, listpk);
		return listpk;
	}

	public List<DictTFactorPO> getAllFactorListTreeByAppID(String appID) {
		List<DictTFactorPO> dictFactorList = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictColumn.APP.getCacheKey(), appID,
				"getAllFactorListTreeByAppID" };
		if (appID == null || appID.trim().equals("")) {
			dictFactorList = dictTFactorMapper.getAllFactorListTree();
		} else {
			// 如果缓存中存在
			if (dataCacheService.get(keys) != null) {
				dictFactorList = (List<DictTFactorPO>) dataCacheService.get(keys);
				return dictFactorList;
			}
			dictFactorList = dictTFactorMapper
					.getAllFactorListTreeByAppID(appID);
		}
		// 加入到缓存
		dataCacheService.put(keys, dictFactorList);
		return dictFactorList;
	}

	public List<DictTFactorPO> getLeafFactorListByTableID(String tableID) {
		List<DictTFactorPO> dictFactorList = null;
		// 缓存key
		String[] keys = { DictCacheKey.CACHE_KEY_DICT,
				DictCacheKey.DictTable.ID.getCacheKey(), tableID,
				"getLeafFactorListByTableID", };
		// 如果缓存中存在
		if (dataCacheService.get(keys) != null) {
			dictFactorList = (List<DictTFactorPO>) dataCacheService.get(keys);
			return dictFactorList;
		}
		dictFactorList = dictTFactorMapper.getLeafFactorListByTableID(tableID);
		// 加入到缓存
		dataCacheService.put(keys, dictFactorList);
		return dictFactorList;
	}

	@Override
	public List<Map<String, String>> getAllDefaultDataLength() {
		List <Map<String, String>> lenSets = dictTFactorMapper.getAllDefaultDataLength();
		return lenSets;
	}

	@Override
	public Object getEditMode() {
		return dictTFactorMapper.getEditMode();
	}
}

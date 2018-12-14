package commons.dict.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.dict.constants.DictCacheKey;
import com.tjhq.commons.dict.dao.DictTSuitSelfMapper;
import com.tjhq.commons.dict.external.po.DictTSuitPO;
import com.tjhq.commons.dict.external.service.IDictTSuitService;
import com.tjhq.commons.dict.service.IDictTSuitSelfService;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;

/**
 * 套表接口实现
 * 
 * @author xujingsi
 * 
 */
@Service
@Transactional
public class DictTSuitSelfService implements IDictTSuitSelfService {

	@Resource
	private DictTSuitSelfMapper dictTSuitSelfMapper;

	@Resource
	private IDataCacheService dataCacheService;

	@Resource
	private IDictTSuitService dictTSuitService;

	public void insertDictTSuit(DictTSuitPO dictTSuit) throws Exception {

		try {
			if (dictTSuit != null) {
				dictTSuitSelfMapper.insertDictTSuit(dictTSuit);
				// 清除缓存
				String suitID = dictTSuit.getSuitid();
				// 清除套表缓存
				String[] tableSuitKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictSuit.ID.getCacheKey(), suitID };
				dataCacheService.put(tableSuitKeys, null);
				String appID = dictTSuit.getAppid();
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
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void updateDictTSuit(DictTSuitPO dictTSuit) throws Exception {

		try {
			if (dictTSuit != null) {
				dictTSuitSelfMapper.updateDictTSuit(dictTSuit);
				// 清除缓存
				String suitID = dictTSuit.getSuitid();
				// 清除套表缓存
				String[] tableSuitKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictSuit.ID.getCacheKey(), suitID };
				dataCacheService.put(tableSuitKeys, null);
				String appID = dictTSuit.getAppid();
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
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteDictTSuit(String suitid) throws Exception {

		try {
			if (suitid != null && !"".equals(suitid)) {
				DictTSuitPO dictTSuit = dictTSuitService
						.getDictTSuitByID(suitid);
				dictTSuitSelfMapper.deleteDictTSuit(suitid);
				// 清除缓存
				String suitID = dictTSuit.getSuitid();
				// 清除套表缓存
				String[] tableSuitKeys = { DictCacheKey.CACHE_KEY_DICT,
						DictCacheKey.DictSuit.ID.getCacheKey(), suitID };
				dataCacheService.put(tableSuitKeys, null);
				String appID = dictTSuit.getAppid();
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
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Grid getDictTSuitHead(String tableid) {

		// 创建Grid
		Grid grid = new Grid();
		// 设置tableID
		grid.setTableID(tableid);
		// Grid类型
		grid.setTableDBName("mytableName");
		grid.setTableName("测试多级表头Grid");
		grid.setAppID("");
		grid.setReadOnly(false);
		int orderNO = 0;
		// 创建列
		Column suitName = new Column();
		suitName.setColumnID("suitname");
		suitName.setColumnDBName("suitname");
		suitName.setColumnName("套表名称");
		suitName.setAlias("套表名称");
		suitName.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		suitName.setShowType(ShowType.SHOW_TYPE_HTML);
		suitName.setOrderID(++orderNO);
		suitName.setDataLength(256);
		suitName.setVisible(true);

		Column orderID = new Column();
		orderID.setColumnID("orderid");
		orderID.setColumnDBName("orderid");
		orderID.setColumnName("排序");
		orderID.setAlias("排序");
		orderID.setDataType(JSTypeUtils.getJSDateType(DataType.INT));
		orderID.setShowType(ShowType.SHOW_TYPE_HTML);
		orderID.setOrderID(++orderNO);
		orderID.setDataLength(9);
		orderID.setVisible(true);

		Column remark = new Column();
		remark.setColumnID("remark");
		remark.setColumnDBName("remark");
		remark.setColumnName("备注");
		remark.setAlias("备注");
		remark.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		remark.setShowType(ShowType.SHOW_TYPE_HTML);
		remark.setOrderID(++orderNO);
		remark.setDataLength(256);
		remark.setVisible(true);

		Column superid = new Column();
		superid.setColumnID("superid");
		superid.setColumnDBName("superid");
		superid.setColumnName("父ID");
		superid.setAlias("父ID");
		superid.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		superid.setShowType(ShowType.SHOW_TYPE_HTML);
		superid.setOrderID(++orderNO);
		superid.setVisible(false);

		Column levelno = new Column();
		levelno.setColumnID("levelno");
		levelno.setColumnDBName("levelno");
		levelno.setColumnName("级次");
		levelno.setAlias("级次");
		levelno.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		levelno.setShowType(ShowType.SHOW_TYPE_HTML);
		levelno.setOrderID(++orderNO);
		levelno.setVisible(false);

		Column suitid = new Column();
		suitid.setKey(true);
		suitid.setColumnID("suitid");
		suitid.setColumnDBName("suitid");
		suitid.setColumnName("套表id");
		suitid.setAlias("套表id");
		suitid.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		suitid.setShowType(ShowType.SHOW_TYPE_HTML);
		suitid.setOrderID(++orderNO);
		suitid.setVisible(false);

		List<Column> columnlist = new ArrayList<Column>();
		columnlist.add(suitName);
		columnlist.add(orderID);
		columnlist.add(remark);
		columnlist.add(superid);
		columnlist.add(levelno);
		columnlist.add(suitid);
		grid.setColumnList(columnlist);
		return grid;
	}

}

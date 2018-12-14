package commons.dict.external.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dict.dao.DictTTabextpropMapper;
import com.tjhq.commons.dict.external.po.DictTTabextpropPO;
import com.tjhq.commons.dict.external.service.IDictTTabextpropService;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;

/**
 * 表拓展属性接口实现
 * 
 * @author xujingsi
 * 
 */
@Service
@Transactional(readOnly = true)
public class DictTTabextpropService implements IDictTTabextpropService {

	@Resource
	private DictTTabextpropMapper dictTTabextpropMapper;

	@Override
	public DictTTabextpropPO getDictTTabextprop(String extid) {

		DictTTabextpropPO dtc = this.dictTTabextpropMapper
				.getDictTTabextprop(extid);
		return dtc;
	}

	@Override
	public List<DictTTabextpropPO> getAllDictTTabextprop() {
		List<DictTTabextpropPO> list = this.dictTTabextpropMapper
				.getAllDictTTabextprop();
		return list;
	}

	@Override
	public List<DictTTabextpropPO> getAllDictTTabextpropByAppid(String appid) {

		List<DictTTabextpropPO> list = null;
		if (appid != null && !"".equals(appid)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("appid", appid);
			list = this.dictTTabextpropMapper.findDictTTabextprop(map);
		}
		return list;
	}

	@Override
	public Grid getDictTTabextpropHead(String tableid) {

		// 创建Grid
		Grid grid = new Grid();
		// 设置tableID
		grid.setTableID(tableid);
		// Grid类型
		grid.setTableDBName("");
		grid.setTableName("");
		grid.setAppID("");
		int orderId = 0;

		// 创建列
		Column extname = new Column();
		extname.setColumnID("extname");
		extname.setColumnDBName("extname");
		extname.setColumnName("扩展名称");
		extname.setAlias("扩展名称");
		extname.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		extname.setShowType(ShowType.SHOW_TYPE_HTML);
		extname.setOrderID(++orderId);
		extname.setDataLength(256);
		extname.setKey(true);
		extname.setVisible(true);

		Column orderid = new Column();
		orderid.setColumnID("orderid");
		orderid.setColumnDBName("orderid");
		orderid.setColumnName("序号");
		orderid.setAlias("序号");
		orderid.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		orderid.setShowType(ShowType.SHOW_TYPE_HTML);
		orderid.setOrderID(++orderId);
		orderid.setDataLength(256);
		orderid.setKey(true);
		orderid.setVisible(false);

		List<Column> columnList = new ArrayList<Column>();
		columnList.add(orderid);
		columnList.add(extname);
		grid.setColumnList(columnList);
		return grid;
	}
}

package commons.dict.external.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dict.dao.DictTColextpropMapper;
import com.tjhq.commons.dict.external.po.DictTColextpropPO;
import com.tjhq.commons.dict.external.service.IDictTColextpropService;

/**
 * 列拓展属性接口实现
 * 
 * @author xujingsi
 * 
 */
@Service
@Transactional(readOnly = true)
public class DictTColextpropService implements IDictTColextpropService {

	@Resource
	private DictTColextpropMapper dictTColextpropMapper;

	@Override
	public DictTColextpropPO getDictTColextprop(String extid) {

		DictTColextpropPO dtc = this.dictTColextpropMapper
				.getDictTColextprop(extid);
		return dtc;
	}

	@Override
	public List<DictTColextpropPO> getAllDictTColextprop() {

		List<DictTColextpropPO> list = this.dictTColextpropMapper
				.getAllDictTColextprop();
		return list;
	}

	@Override
	public List<DictTColextpropPO> getAllDictTColextpropByAppid(String appid) {

		List<DictTColextpropPO> list = null;
		if (appid != null && !"".equals(appid)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("appid", appid);
			list = this.dictTColextpropMapper.findDictTColextprop(map);
		}
		return list;
	}

}

package commons.dict.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dict.dao.DictTModelRelaMapper;
import com.tjhq.commons.dict.service.IDictTModelRelaService;

@Service
@Transactional(readOnly = true)
public class DictTModelRelaService implements IDictTModelRelaService {

	@Resource
	private DictTModelRelaMapper dictTModelRelaMapper;
	
	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class) 
	public void insertDictTModelRela(List<Map<String,String>> params) {
		Map<String,String> map = params.get(0);
		String tableId = map.get("tableId");
		
		//先删后插
		dictTModelRelaMapper.deleteDictTModelRelaByTableId(tableId);
		dictTModelRelaMapper.insertDictTModelRela(params);
	}
	
	@Override
    @Transactional(readOnly = false,rollbackFor=Exception.class) 
	public void deleteDictTModelRelaByTableId(String tableId) {
        dictTModelRelaMapper.deleteDictTModelRelaByTableId(tableId);
	}
	@Override
	public List<String> selectDictTModelRelaSubSet(String tableId) {
		return dictTModelRelaMapper.selectDictTModelRelaSubSet(tableId);
	}
}

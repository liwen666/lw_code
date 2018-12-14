package commons.setting.input.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.setting.input.dao.BaseNumMapper;
import com.tjhq.commons.setting.input.po.DictTSetBaseNumTab;
import com.tjhq.commons.setting.input.service.IBaseNumService;
import com.tjhq.commons.setting.input.service.IEntryService;
import com.tjhq.commons.setting.input.web.ConverTables;

@Service
@Transactional(readOnly = true)
public class BaseNumService implements IBaseNumService {

	@Resource
	private BaseNumMapper baseNumMapper;
	@Resource
	private IEntryService entryService;
	//保存 基本数字表设置 主表
	@Transactional
	public String saveBaseNumTab(Map<String, Object> map) {
		try{
			if(!ConverTables.isNotNull(map.get("guID"))){
				int insert = baseNumMapper.insertBaseNumTab(map); //插入
			}else{			
				String tableID = (String) map.get("tableID");
				DictTSetBaseNumTab po = entryService.getDataBaseTabList(tableID);
				if(po != null){
					String columnID = po.getColumnID();	
					//1、 如果数据库中的 column 与 页面中 不同, 则进行更新
					//2、 并删除 子表中 数据
					if(!columnID.equals(map.get("columnID"))){
						int update = baseNumMapper.updateBaseNumTab(map); //更新
						
						baseNumMapper.deleteBaseNumSubByColumnID(columnID); //删除	
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return (String)map.get("guID");
	}


	
}

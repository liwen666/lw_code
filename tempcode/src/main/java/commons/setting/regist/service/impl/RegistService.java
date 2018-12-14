package commons.setting.regist.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.setting.regist.dao.IRegistMapper;
import com.tjhq.commons.setting.regist.service.IRegistService;

@Service
@Transactional
public class RegistService implements IRegistService{
	@Resource
	IRegistMapper registMapper;
	@Override
	public List<Map<String, Object>> getDataList(Map<String, String> params,
			String queryDBName, String queryTableName) throws Exception{
		return registMapper.getDataList(params,queryDBName,queryTableName);
	}

	private static byte bytes[] = {(byte) 0xC2,(byte) 0xA0};
	@Override
	public void saveData(CommonGrid commonGrid) {
		// 插入数据
		Object appid = commonGrid.getExtProperties().get("appid");
		System.out.println(commonGrid.getInsertValues());
		for (Map<String, Object> map : commonGrid.getInsertValues()) {
			map.put("APPID", appid);
			Object changeDictistrict = map.get("CHANGEDISTRICT");
			String utfSpace = "";
			try {
				utfSpace = new String(bytes, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(changeDictistrict != null)
				changeDictistrict.toString().replaceAll(utfSpace, " ").trim().toUpperCase();
			map.put("CHANGEDISTRICT", changeDictistrict);
			registMapper.addData(map);
		}

		// 更新数据
		System.out.println(commonGrid.getUpdateValues());
		for (Map<String, Object> map : commonGrid.getUpdateValues()) {
			registMapper.updateData(map);
		}

		//刪除数据
		System.out.println(commonGrid.getDeleteValues());
		for (Map<String, Object> map : commonGrid.getDeleteValues()){
			registMapper.delData(map);
		}
	}
}

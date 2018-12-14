package commons.setting.external.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.setting.external.dao.ExpProjectSetInfoMapper;
import com.tjhq.commons.setting.external.po.ProjTabPO;
import com.tjhq.commons.setting.external.po.RECDetailPO;
import com.tjhq.commons.setting.external.po.RECPO;
import com.tjhq.commons.setting.external.service.IExpProjectSetInfoService;

@Service
@Transactional( readOnly = true )
public class ExpProjectSetInfoService implements IExpProjectSetInfoService {

	/** 是分组控件 */
	private static String GROUPCTRL = "1"; //是分组控件。
	/** 不是分组控件 */
	private static String NOGROUPCTRL = "0";
	/** 顶级结点 */
	private static String TOPNODE = "0";
	//项目编码流水号标志
	
	@Resource
	private ExpProjectSetInfoMapper expProjectSetInfoMapper; // 项目设置
	@Resource
	private IDataCacheService cache;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProjTabPO> getProjTabList( Map<String, String> map ) {
		if(map.get("spfid")!=null && !map.get("spfid").toString().equals("")){
			String objectId = map.get("objectid");
			map.put("objectid", map.get("spfid").toString());
			int spfTabCount = expProjectSetInfoMapper.getTabCount(map);
			if(spfTabCount>0){
				map.put("spfType", "1");
			}else{
				map.put("objectid", objectId);
			}
		}
		
		String busi_key = map.get("processId")+"_"+map.get("objectid")+"_"+map.get("typeflag");
		String [] keys = {"HQ.SPF","SETTING_REPORT", "SETTING_REPORT_TAB", busi_key};
		List<ProjTabPO> result = (List<ProjTabPO>) cache.get(keys);
		if(result==null || result.size()==0){
			result = expProjectSetInfoMapper.getProjTabList(map);
			cache.put(keys, result);
		}
		return result;
	}
	
	@Override
	public List<RECPO> getSetRECList( Map<String, String> map ) {
		List<RECPO> list = expProjectSetInfoMapper.getSetRECList( map );
		if(list.size()==0 || list==null){
			String projTypeId = expProjectSetInfoMapper.getProjTypeId(map.get("objectid").toString());
			if(projTypeId!=null && !projTypeId.equals("")){
				map.put("objectid", projTypeId);
			}
						
		}
		return expProjectSetInfoMapper.getSetRECList( map );
	}


	@Override
	public Map<String, List<RECDetailPO>> getSetRECDetailList(String recid) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("recid", recid);
		List<RECDetailPO> list = expProjectSetInfoMapper.getSetRECDetailList(map);
		List<RECDetailPO> fieldsetList = new ArrayList<RECDetailPO>();
		List<RECDetailPO> columnsList = new ArrayList<RECDetailPO>();
		Map<String, List<RECDetailPO>> returnmap = new HashMap<String, List<RECDetailPO>>();

		for (int i = 0; i < list.size(); i++) {
			RECDetailPO dto = list.get(i);
			if (GROUPCTRL.equals(dto.getIsgroupctrl())) { // 是分组控件。
				fieldsetList.add(dto);
			} 
			if (TOPNODE.equals(dto.getSuperid())) {
				columnsList.add(dto);
			}
		}

		List<RECDetailPO> childList = null;
		RECDetailPO fatherdto;
		// 公组框包含的表结构的列
		for (int j = 0; j < fieldsetList.size(); j++) {
			fatherdto = fieldsetList.get(j);
			childList = new ArrayList<RECDetailPO>();
			for (int i = 0; i < list.size(); i++) {
				RECDetailPO dto = list.get(i);
				if (NOGROUPCTRL.equals(dto.getIsgroupctrl())
						&& !TOPNODE.equals(dto.getSuperid())
						&& fatherdto.getCtrlid().equals(dto.getSuperid())) {
					childList.add(dto);
				}
			}
			fatherdto.setListDetail(childList);
		}

		returnmap.put("fieldset", fieldsetList);
		returnmap.put("columns", columnsList);

		return returnmap;
	}
}

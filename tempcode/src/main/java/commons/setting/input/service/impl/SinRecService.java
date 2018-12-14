package commons.setting.input.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.grid.commonGrid.service.impl.CommonGridService;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.external.po.RECDetailPO;
import com.tjhq.commons.setting.external.po.RECPO;
import com.tjhq.commons.setting.external.service.IEntryOuterService;
import com.tjhq.commons.setting.input.dao.SinRecMapper;
import com.tjhq.commons.setting.input.service.ISinRecService;
import com.tjhq.commons.utils.IdGenerator;
import com.tjhq.commons.utils.StringUtil;


@Service
@Transactional(readOnly=true)
public class SinRecService extends CommonGridService implements ISinRecService {

	@Resource
	private SinRecMapper sinRecMapper;
	@Resource
	private IDataCacheService dataCacheService;
	/*@Resource
	private UtilsMapper utilsMapper;*/

	/** 是分组控件 */
	private static String GROUPCTRL = "1"; //是分组控件。
	/** 不是分组控件 */
	private static String NOGROUPCTRL = "0";
	/** 末级结点 */
	private static String LEAF = "1";
	/** 顶级结点 */
	private static String TOPNODE = "0";
	
	@Override
	public List<Map<String, Object>> selectSingleRecord(String tableID) {
		return sinRecMapper.getTableData(tableID);
	}
	@Override
	public List<RECPO> getSetRECList(String tableId) {
		String[] keys = {IEntryOuterService.INPUT,IEntryOuterService.SINREC,tableId};
		List<RECPO> result = (List<RECPO>) dataCacheService.get(keys);
		if(null == result || result.size()==0){
			result = sinRecMapper.getSetRECList(tableId);
			dataCacheService.put(keys, result);
		}
		return result;
	}
	public Map<String, List<RECDetailPO>> getSetRECDetailList(String recId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("recid", recId);
		String[] keys = {IEntryOuterService.INPUT,IEntryOuterService.SINREC,recId};
		List<RECDetailPO>  list = (List<RECDetailPO>) dataCacheService.get(keys);
		
		if(null == list || list.size()==0){
			list =  sinRecMapper.getSinRECDetailList(map);
			dataCacheService.put(keys, list);
		}
		/*List<RECDetailPO>*/ list =  sinRecMapper.getSinRECDetailList(map);
		
		Map<String, List<RECDetailPO>> returnmap = new HashMap<String, List<RECDetailPO>>();
		if (list == null || list.size() == 0) {
			return returnmap;
		}
		List<RECDetailPO> fieldsetList = new ArrayList<RECDetailPO>();
		List<RECDetailPO> columnsList = new ArrayList<RECDetailPO>();

		for (int i = 0; i < list.size(); i++) {
			RECDetailPO dto = list.get(i);
			if (GROUPCTRL.equals(dto.getIsgroupctrl())) { // 是分组控件。
				fieldsetList.add(dto);
			} 
//				else if (TOPNODE.equals(dto.getSuperid())
//					&& NOGROUPCTRL.equals(dto.getIsgroupctrl())
//					&& LEAF.equals(dto.getIsleaf())) { // 表结构的列(不包括公组框下面的属性的列)
//				
//			}
			
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
		returnmap.put( "fieldset", fieldsetList );
		returnmap.put( "columns", columnsList );

		return returnmap;
	}
	@Override
    @Transactional(readOnly=false, rollbackFor=Exception.class)
	public String saveSingleRecord(Map<String, Object> map ) throws Exception {
        String recid = (String)map.get( "recid" );
        String grid = (String)map.get( "grid" );
        String tableid = (String)map.get("tableid");
        String GROUPFLAG = (String)map.get("groupFlag"); 
        //保存主表
        if(StringUtil.isEmpty( recid )){
            this.saveSetREC( map );
            List<RECPO> list = this.getSetRECList(tableid);
            if(list.size()>0){
                RECPO po = list.get(0);
                recid = po.getRECID();
            }
        }else{
            this.updateSetREC( map );
        }
        
        if(!StringUtil.isEmpty( recid )){
            //保存明细表
            CommonGrid commonGrid=(CommonGrid) (new ObjectMapper()).readValue(grid, CommonGrid.class);
            List<Map<String,Object>> insertList = commonGrid.getInsertValues();
            String guid="";
            Map<String,String> groupMap = new HashMap<String, String>();
            
            // 公组框数据保存
            for(int i=0;i<insertList.size();i++){
                map = insertList.get( i );
                map.put( "RECID", recid );
                if(map.get( "CTRLID" ).toString().contains( "group_" ) || !map.get( "ISGROUPCTRL" ).toString().equals( GROUPFLAG )){
                    guid = IdGenerator.get32UUID();
                    groupMap.put( map.get( "CTRLID" ).toString(), guid );
                    map.put( "CTRLID", guid );
                    this.saveSetRECDetail( map );
                }
            }

            // 删除
            List<Map<String,Object>> deleteList = commonGrid.getDeleteValues();
            //getData().get( 0 ).getChangedValues().getDeleteValues();
            for(int i=0;i<deleteList.size();i++){
                map = deleteList.get( i );
                map.put( "RECID", recid );
                this.deleteSetRECDetail( map );
            }
            
            // 属性保存
            for(int i=0;i<insertList.size();i++){
                map = insertList.get( i );
                map.put( "RECID", recid );
                if(map.get( "SUPERID" ).toString().contains( "group_" )
                        || !map.get( "SUPERID" ).toString().equals( "0" )){
                    if(groupMap.get( map.get( "SUPERID" ).toString())!=null){
                        map.put( "SUPERID", groupMap.get( map.get( "SUPERID" ).toString()));
                    }
                    this.saveSetRECDetail( map );
                }else if(map.get( "ISGROUPCTRL" ).toString().equals( GROUPFLAG )){
                    this.saveSetRECDetail( map );
                }
            }
            
            // 更新
            List<Map<String,Object>> updateList = commonGrid.getUpdateValues();
            for(int i=0;i<updateList.size();i++){
                map = updateList.get( i );
                map.put( "RECID", recid );
                this.updateSetRECDetail( map );
            }
        }
        return recid;
	}
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveSetREC( Map<String, Object> map ) throws Exception{
		this.clearCache();
		sinRecMapper.insertSetREC( map );
	}
	/*
	 * 清空缓存
	 */
	private void clearCache() {
		String[] keys = {IEntryOuterService.INPUT,IEntryOuterService.SINREC};
		dataCacheService.put(keys, null);
	}
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void updateSetREC( Map<String, Object> map ) throws Exception{
		this.clearCache();
		sinRecMapper.updateSetREC( map );
	}
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveSetRECDetail( Map<String, Object> map ) throws Exception{
		this.clearCache();
		sinRecMapper.insertSetRECDetail( map );
	}
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void deleteSetRECDetail( Map<String, Object> map ) throws Exception {
		this.clearCache();
		sinRecMapper.deleteSetRECDetail( map );
	}
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void updateSetRECDetail( Map<String, Object> map ) throws Exception {
		this.clearCache();
		sinRecMapper.updateSetRECDetail( map );
	}
}

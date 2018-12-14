package commons.setting.input.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTSuitPO;
import com.tjhq.commons.dict.external.service.IDictTSuitService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.grid.commonGrid.service.impl.CommonGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.setting.external.po.RECPO;
import com.tjhq.commons.setting.input.dao.MainRefMapper;
import com.tjhq.commons.setting.input.dao.MainSubRelaMapper;
import com.tjhq.commons.setting.input.po.DictTSetMainSubRela;
import com.tjhq.commons.setting.input.po.TreeNode;
import com.tjhq.commons.setting.input.service.IMainRefService;
import com.tjhq.commons.setting.input.service.IMainSubRelaService;
import com.tjhq.commons.setting.input.web.ConverTables;
import com.tjhq.exp.setting.external.service.IExpColToTableService;


@Service
@Transactional(readOnly = true)
public class MainSubRelaService extends CommonGridService implements IMainSubRelaService {
	@Resource
	public MainSubRelaMapper mainSubRelaMapper;
	@Resource
	private IMainRefService mainRefService;
	@Resource
	private MainRefMapper mainRefMapper;
	@Resource
    private IDictTSuitService dictTSuitService;
	@Resource
	private IDataCacheService dataCacheService;
	@Resource
	private IExpColToTableService colToTableService;
	
	//采集类型表
	public List<TreeNode> getCollectTypeTreeList() throws ServiceException{
		try {
            return mainSubRelaMapper.getCollectTypeTreeList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_02100,"获取采集类型表出现异常！",false);
        }
	}

	//  表定义
	public Table getTableHead(String collTypeID) throws ServiceException{
		Table grid = new Table();
		grid.setTableID(collTypeID);
		grid.setTableDBName(collTypeID);
		grid.setTableName(collTypeID);
		try {
            setColumns(grid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERR_00000,"封装表头数据出现异常！",false);
        }
		return grid;
	}

	//  列定义
	public void setColumns(Table data) {

		List<Column> list = new ArrayList<Column>();

		Column tableNameCol=new Column();
		tableNameCol.setColumnID("tableName");
		tableNameCol.setColumnName("表名");
		tableNameCol.setAlias("表名");
		tableNameCol.setLeaf(true);
		tableNameCol.setColumnDBName("TABLENAME");
		tableNameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		tableNameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		tableNameCol.setDataLength(10);
		tableNameCol.setWidth(200);
		tableNameCol.setKey(false);
		tableNameCol.setReadOnly(true);
		tableNameCol.setVisible(true);
		tableNameCol.setOrderID(1);

		Column dealTypeCol=new Column();
		dealTypeCol.setColumnID("dealName");
		dealTypeCol.setColumnName("处理类型");
		dealTypeCol.setAlias("处理类型");
		dealTypeCol.setLeaf(true);
		dealTypeCol.setColumnDBName("DEALNAME");
		dealTypeCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		dealTypeCol.setShowType(ShowType.SHOW_TYPE_HTML);
		dealTypeCol.setDataLength(10);
		dealTypeCol.setWidth(200);
		dealTypeCol.setReadOnly(true);
		dealTypeCol.setKey(false);
		dealTypeCol.setVisible(true);
		dealTypeCol.setOrderID(2);
		
		Column orderIDCol=new Column();
		orderIDCol.setColumnID("orderID");
		orderIDCol.setColumnName("显示顺序");
		orderIDCol.setAlias("显示顺序");
		orderIDCol.setLeaf(true);
		orderIDCol.setColumnDBName("ORDERID");
		orderIDCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		orderIDCol.setShowType(ShowType.SHOW_TYPE_HTML);
		orderIDCol.setDataLength(10);
		orderIDCol.setWidth(100);
		orderIDCol.setKey(false);
		orderIDCol.setReadOnly(false);
		orderIDCol.setVisible(true);
		orderIDCol.setOrderID(3);
		
		Column isMainTableCol=new Column();
		isMainTableCol.setColumnID("isMainTable");
		isMainTableCol.setColumnName("是否主表");
		isMainTableCol.setAlias("是否主表");
		isMainTableCol.setLeaf(true);
		isMainTableCol.setColumnDBName("ISMAINTABLE");
		isMainTableCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		isMainTableCol.setShowType(ShowType.SHOW_TYPE_LIST);
		isMainTableCol.setDataLength(10);
		isMainTableCol.setWidth(100);
		isMainTableCol.setKey(false);
		isMainTableCol.setNullable(false);
		isMainTableCol.setReadOnly(false);
		isMainTableCol.setVisible(true);
		isMainTableCol.setOrderID(4);
		
		Column tableCol=new Column();
		tableCol.setColumnID("tableID");
		tableCol.setColumnName("表");
		tableCol.setAlias("表");
		tableCol.setLeaf(true);
		tableCol.setColumnDBName("TABLEID");
		tableCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		tableCol.setShowType(ShowType.SHOW_TYPE_HTML);
		tableCol.setDataLength(10);
		tableCol.setWidth(100);
		tableCol.setKey(true);
		tableCol.setReadOnly(false);
		tableCol.setVisible(false);
		tableCol.setOrderID(5);
		
		Column collTypeIDCol=new Column();
		collTypeIDCol.setColumnID("collTypeID");
		collTypeIDCol.setColumnName("采集类型");
		collTypeIDCol.setAlias("采集类型");
		collTypeIDCol.setLeaf(true);
		collTypeIDCol.setColumnDBName("COLLTYPEID");
		collTypeIDCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		collTypeIDCol.setShowType(ShowType.SHOW_TYPE_HTML);
		collTypeIDCol.setDataLength(10);
		collTypeIDCol.setWidth(100);
		collTypeIDCol.setKey(true);
		collTypeIDCol.setReadOnly(false);
		collTypeIDCol.setVisible(false);
		collTypeIDCol.setOrderID(6);
		
		Column guidCol=new Column();
		guidCol.setColumnID("guID");
		guidCol.setColumnName("主键");
		guidCol.setAlias("主键");
		guidCol.setLeaf(true);
		guidCol.setColumnDBName("GUID");
		guidCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		guidCol.setShowType(ShowType.SHOW_TYPE_HTML);
		guidCol.setDataLength(10);
		guidCol.setWidth(100);
		guidCol.setKey(true);
		guidCol.setReadOnly(false);
		guidCol.setVisible(false);
		guidCol.setOrderID(7);
		
		Column setRelationCol=new Column();
		setRelationCol.setColumnID("setRelation");
		setRelationCol.setColumnName("关系设置");
		setRelationCol.setAlias("关系设置");
		setRelationCol.setLeaf(true);
		setRelationCol.setColumnDBName("SETRELATION");
		setRelationCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		setRelationCol.setShowType(ShowType.SHOW_TYPE_HTML);
		setRelationCol.setDataLength(10);
		setRelationCol.setWidth(150);
		setRelationCol.setKey(false);
		setRelationCol.setReadOnly(true);
		setRelationCol.setVisible(true);
		setRelationCol.setOrderID(8);
		
		Column setDetailCol=new Column();
		setDetailCol.setColumnID("setDetail");
		setDetailCol.setColumnName("详细设置");
		setDetailCol.setAlias("详细设置");
		setDetailCol.setLeaf(true);
		setDetailCol.setColumnDBName("SETDETAIL");
		setDetailCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		setDetailCol.setShowType(ShowType.SHOW_TYPE_HTML);
		setDetailCol.setDataLength(10);
		setDetailCol.setWidth(150);
		setDetailCol.setKey(false);
		setDetailCol.setReadOnly(true);
		setDetailCol.setVisible(true);
		setDetailCol.setOrderID(9);
		
		list.add(tableNameCol);
		list.add(dealTypeCol);
		list.add(orderIDCol);
		list.add(isMainTableCol);
		list.add(tableCol);
		list.add(collTypeIDCol);
		list.add(guidCol);
		list.add(setRelationCol);
		list.add(setDetailCol);

		data.setColumnList(list);
	}
	//查询 数据
	public Object getTableData(String collTypeID) throws ServiceException {
		PageInfo pageInfo = new PageInfo();
		List<Map<String, Object>> dataList = null;
        try {
            dataList = mainSubRelaMapper.getTableData(collTypeID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_02102,"获取主子表列表出现异常！",false);
        }
		super.setGridData(dataList,pageInfo);
		return pageInfo;
	}

	//新增
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void insertTableData(List<Map<String, Object>> lists) throws ServiceException{
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		try {
            mainSubRelaMapper.insertTableData(lists);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_02103,"新增主表或子表出现异常！"+e.getCause().getMessage(),false);
        }
		
	}
	//修改
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void updateTableData(List<Map<String, Object>> lists) throws ServiceException{	
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		try {
            mainSubRelaMapper.updateTableData(lists);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_02103,"修改主表或子表出现异常！"+e.getCause().getMessage(),false);
        }

	}
	//删除
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteTableData(List<Map<String, Object>> lists) throws ServiceException{		
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		Integer is_succ = mainSubRelaMapper.deleteTableData(lists);//DICT_T_SETMAINSUBTAB
		//删除 关系子表中 数据
		if(is_succ > 0){
			mainSubRelaMapper.deleteMainSubRela(lists);//DICT_T_SETMAINSUBRELA
			
			//删除 详细设置
            for (Map<String, Object> del : lists) {
                if (ConverTables.isNotNull(del.get("GUID"))) { // 如果主键不为空
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("OBJECTID", (String) del.get("COLLTYPEID"));
                    params.put("COLLTYPEID", (String) del.get("COLLTYPEID"));
                    params.put("TABLEID", (String) del.get("TABLEID"));
                    // 查询 主表信息
                    List<RECPO> recList = mainRefService.getSetRECList(params);

                    if (ConverTables.isNotNullList(recList)) {
                        String recId = recList.get(0).getRECID();
                        mainRefService.deleteSetREC(params); // 主表

                        mainRefService.delDetailByRecId(recId); // 子表
                    }
                    String mainTableID = "";
                    List<Map<String, String>> tabids = this.getTableIdByCollTypeID((String) del.get("COLLTYPEID"));
                    for (Map<String, String> tabidMap : tabids) {
                        if ("1".equals(tabidMap.get("ISMAINTABLE"))) {
                            mainTableID = tabidMap.get("TABLEID");
                            break;
                        }
                    }
                    if (!"".equals(mainTableID))
                        colToTableService.deleteColToTableInfo(mainTableID, (String) del.get("TABLEID"));// 删除EXP_T_COLTOTABLES关系
                }
            }
		}
	}

	// 主子表关系设置
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void saveMainSubRela(Map<String, Object> relaMap,String operator) throws ServiceException{
		//清空缓存
			String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
			dataCacheService.put(keys, null);
		if(ConverTables.isNotNull(operator)){
			if(operator.equals("insert")) mainSubRelaMapper.insertMainSubRela(relaMap);
			if(operator.equals("update")) mainSubRelaMapper.updateMainSubRela(relaMap);
		}
		
	}
	// 查询主子关系 设置
	public List<DictTSetMainSubRela> selectMainSubRela(String mainSubID) {
		return mainSubRelaMapper.selectMainSubRela(mainSubID);
	}
	// 增加左树
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void saveLeftTree(Map<String, String> treeMap) throws ServiceException{
		//清空缓存
			String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
			dataCacheService.put(keys, null);
			
            try {
                mainSubRelaMapper.insertLeftTree(treeMap);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.SET_02101,"新增左侧采集类型表出现异常!",false);
            }
	}
	//删除左树节点
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteLeftTreeData(Map<String, String> map) throws ServiceException{
		//清空缓存
		String[] keys = {"HQ.BGT","COMMONSETTING_MAINSUB"};
		dataCacheService.put(keys, null);
		//删除关联表的信息
		this.deleteRelTableData(map);
		//删除树节点
		try {
            mainSubRelaMapper.deleteTreeData(map);//CODE_T_COLLECTTYPE
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_02101,"删除CODE_T_COLLECTTYPE表数据出现异常！",false);
        }
	}
	private void deleteRelTableData(Map<String, String> map) throws ServiceException{
		String code=map.get("code");
		//主表ID
		String mainTabID = "";
		//所有表ID
		List<String> refTabIDs = new ArrayList<String>();
		List<Map<String, String>> tabidList = this.getTableIdByCollTypeID(code);
		 if(tabidList.size()>0){
	            for(Map<String,String> tabidMap : tabidList){
	                if("1".equals(tabidMap.get("ISMAINTABLE"))){
	                    mainTabID = tabidMap.get("TABLEID");
	                }
	             refTabIDs.add(tabidMap.get("TABLEID"));
	            }
	        }
		if (refTabIDs.size() > 0) {
            for (String tableID : refTabIDs) {
                // 从 详细设置 主表中查出recid
                List<String> recids = new ArrayList<String>();
                try {
                    recids = mainRefMapper.getRecIDByMainTabId(tableID,code);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ServiceException(ExceptionCode.SET_02200,"根据表ID 获取RECID出现异常！",false);
                }
               
              //------------开始删除-------------
                if (recids.size() > 0) {
                    try {
                        for(String recid : recids){
                        mainRefMapper.delDetailByRecId(recid);//DICT_T_SETRECDETAIL
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ServiceException(ExceptionCode.SET_02102,"删除DICT_T_SETRECDETAIL表数据出现异常！",false);
                    }
                }
                if(!tableID.isEmpty()){
                    Map<String, String> delParamMap = new HashMap<String, String>();
                    try {
                        delParamMap.put("OBJECTID", code);
                        delParamMap.put("TABLEID", tableID);
                        mainRefMapper.deleteSetREC(delParamMap);//DICT_T_SETREC
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ServiceException(ExceptionCode.SET_02102,"删除DICT_T_SETREC表数据出现异常！",false);
                    }
                    try {
                        mainSubRelaMapper.delMainSubRelaDataByMainId(mainTabID,tableID);//DICT_T_SETMAINSUBRELA
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ServiceException(ExceptionCode.SET_02102,"删除DICT_T_SETMAINSUBRELA表数据出现异常！",false);
                    }
                }
            }
        }
		
        if (mainTabID != null || !"".equals(mainTabID)) {
         
            try {
                mainSubRelaMapper.delMainSubTabByColltypeID(code);//DICT_T_SETMAINSUBTAB
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.SET_02102,"删除DICT_T_SETMAINSUBTAB表数据出现异常！",false);
            }
           colToTableService.deleteColToTableInfo(mainTabID, null);//EXP_T_COLTOTABLES
        }
	}

    @Override
    public List<TreeNode> getLeftTreeData(String appID) throws ServiceException {

        List<DictTSuitPO> suitList = null;
        try {
            suitList = dictTSuitService.getDictTSuits(appID, "0", true);
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new ServiceException(ExceptionCode.DCT_01100,"获取"+appID+"系统下的套表出现异常！",false);
        }
        List<TreeNode> treeList = new ArrayList<TreeNode>();
        for (DictTSuitPO po : suitList) {
            TreeNode tree = new TreeNode();
            tree.setId(po.getSuitid());
            tree.setName(po.getSuitname());
            tree.setPId(po.getSuperid());
            tree.setIsLeaf(po.getIsleaf() == null ? "1" : po.getIsleaf());
            tree.setLevelNo(po.getLevelno() == null ? 1 : po.getLevelno());
            tree.setColumnName(po.getSuitname());
            tree.setIsParent("true");
            if (po.getDictTModelList() != null) {
                for (DictTModelPO modelPo : po.getDictTModelList()) {
                    
                    TreeNode tableTree = new TreeNode();
                    tableTree.setId(modelPo.getTableid());
                    tableTree.setName(modelPo.getName());
                    tableTree.setPId(modelPo.getSuitid());
                    tableTree.setIsLeaf(po.getIsleaf() == null ? "1" : po.getIsleaf());
                    tableTree.setLevelNo(po.getLevelno() == null ? 1 : po.getLevelno());
                    tableTree.setDbTableName(modelPo.getDbtablename());
                    
                    treeList.add(tableTree);
                    
                }
            }
            treeList.add(tree);
        }
        if (treeList != null && treeList.size() > 0) {
            List<TreeNode> treeLeft = getCollectTypeTreeList();
            // 已有的树
            Iterator<TreeNode> treeIterator = treeList.iterator();
            while (treeIterator.hasNext()) {
                TreeNode treeNode = treeIterator.next();
                for (TreeNode tt : treeLeft) {
                    if (treeNode.getId().equals(tt.getColumnId())) {
                        treeIterator.remove();
                        break;
                    }
                }
            }
        }
        return treeList;
    }

    @Override
    public List<TreeNode> suitTreeMainSub(String appID, String collTypeID) throws ServiceException {
      //引用 获取 所有套表及下属表
        List<DictTSuitPO> suitList = dictTSuitService.getDictTSuits(appID, "0", true);
       
        List<TreeNode> treeList = new ArrayList<TreeNode> ();
        treeList = treeData(treeList,suitList);
        List<Map<String, Object>> dataList = dataList = mainSubRelaMapper.getTableData(collTypeID);
        //已经存在的不能选
      // 已有的树
         Iterator<TreeNode> treeIterator = treeList.iterator();
        if(suitList.size()>0){
         while (treeIterator.hasNext()) {
             TreeNode tree = treeIterator.next();
             for (Map<String, Object> data : dataList) {
                 if (tree.getId().equals(data.get("TABLEID"))) {
                     treeIterator.remove();
                     break;
                 }
             }
         }
         }
        return treeList;
    }

    private List<Map<String, String>> getTableIdByCollTypeID(String collTypeID) throws ServiceException {
        // 从主表中查出主表ID
        List<Map<String, String>> idlist = null;
        try {
             idlist = mainSubRelaMapper.getMainTabIDByCode(collTypeID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_02200, "根据collTypeID获取表ID出现异常！", false);
        }
        if(idlist.size() <=0){
            throw new ServiceException(ExceptionCode.SET_02200, "没有查询到表ID！", false);
        }
        return idlist;
    }
    private List<TreeNode> treeData(List<TreeNode> treeList,List <DictTSuitPO>list) throws ServiceException{  
        for(DictTSuitPO suit : list){
            TreeNode tree = new TreeNode();
            tree.setId(suit.getSuitid());
            tree.setName(suit.getSuitname());
            tree.setPId(suit.getSuperid());
            tree.setIsLeaf(suit.getIsleaf());
            tree.setLevelNo(suit.getLevelno());
            tree.setAppID(suit.getAppid());
            tree.setIsParent("true");
            tree.setOpen(true);                 
            treeList.add(tree);
            //物理表
            List<DictTModelPO> modelList = suit.getDictTModelList();
        
            if(modelList!=null&&modelList.size()>0){
                for(DictTModelPO model:modelList){
                    TreeNode child = new TreeNode();
                    child.setId(model.getTableid());
                    child.setName(model.getName());
                    child.setPId(model.getSuitid());
                    child.setIsLeaf(suit.getIsleaf());
                    child.setDealType(model.getDealtype()); //表处理类型
                    child.setDealName(model.getDealName());
                    child.setTableType(model.getTabletype()); //表类型
                    child.setAppID(model.getAppid());
                    child.setIsParent("false");                     
                    treeList.add(child);
                }   
            }
            List<DictTSuitPO> suitList = suit.getDictTSuitList();
            if(ConverTables.isNotNullList(suitList)){                   
                treeData(treeList,suitList);                    
            }
        }
        return treeList;
     }
}
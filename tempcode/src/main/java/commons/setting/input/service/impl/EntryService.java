package commons.setting.input.service.impl;

import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dict.dao.DictTModelSelfMapper;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.setting.input.dao.EntryMapper;
import com.tjhq.commons.setting.input.po.DictTSetAnalyPO;
import com.tjhq.commons.setting.input.po.DictTSetBaseNumSub;
import com.tjhq.commons.setting.input.po.DictTSetBaseNumTab;
import com.tjhq.commons.setting.input.po.DictTSetFddefPO;
import com.tjhq.commons.setting.input.po.DictTSetFixPO;
import com.tjhq.commons.setting.external.po.DictTSetGroupPO;
import com.tjhq.commons.setting.input.po.DictTSetHrefParmPO;
import com.tjhq.commons.setting.external.po.DictTSetAngleViewPO;
import com.tjhq.commons.setting.external.po.DictTSetQuerydDetPO;
import com.tjhq.commons.setting.external.po.DictTSetQuerydPO;
import com.tjhq.commons.setting.external.po.DictTSetRefrelaDataPO;
import com.tjhq.commons.setting.external.po.DictTSetRefrelaPO;
import com.tjhq.commons.setting.external.po.DictTSetSortPO;
import com.tjhq.commons.setting.input.po.TreeNode;
import com.tjhq.commons.setting.input.service.IEntryService;
import com.tjhq.commons.setting.input.web.ConverTables;

@Service
@Transactional(readOnly = true)
public class EntryService implements IEntryService {
	@Resource
	private EntryMapper entryMapper;
	@Resource
	private IDictTFactorService dictTFactorService;
	@Resource
	private IDictTModelService dictTModelService;
	@Resource
	private DictTModelSelfMapper dictTModelSelfMapper;
	@Resource
	private IDictTModelcodeService dictTModelcodeService;

	//视角	
	public List<DictTSetAngleViewPO> getDataAngleViewList(String tableID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		return entryMapper.getDataAngleViewList(map);
	}

	// 分组设置
	public List<DictTSetGroupPO> getDataGroupList(String tableID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		return entryMapper.getDataGroupList(map);
	}

	// 排序设置
	public List<DictTSetSortPO> getDataSortList(String tableID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		return entryMapper.getDataSortList(map);
	}
	//引用关系定义 主表
	public List<DictTSetRefrelaPO> getDataRefrelaList(String tableID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		return entryMapper.getDataRefrelaList(map);
	}
	//引用关系定义 列所引用的代码表名
	public String getRefrelaDbTableName(String tableID, String columnID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID); 
		map.put("columnID", columnID);
		
		return entryMapper.getRefrelaDbTable(map);
	}

	@Override
	public List<TreeNode> getRefrelaTree(String tableID, String columnID) throws ServiceException {
		try {
			List<DictTFactorPO> columns
				= dictTModelService.getDictTModelByID(tableID, true).getDictTFactorList();
			DictTModelcodePO dictTModelcodePO = null;
			for(DictTFactorPO column : columns) {
				if(column.getColumnid().equals(columnID)) {
					dictTModelcodePO =
						dictTModelcodeService.getDictTModelcodePOByID(column.getCsid());
				}
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dbTableName", dictTModelcodePO.getDbtablename());
//			map.put("sqlWhere", dictTModelcodePO.getDynamicwhere());
			return entryMapper.getRefrelaDbTableTree(map);
		} catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.INP_00001, "代码表不存在！", false);
		}
	}

	@Override
	public List<TreeNode> getRefrelaTree(String tableID, String relaDbTab,
			String columnID, String dataID, String rightTable) throws ServiceException {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("relaDbTab", relaDbTab);
			map.put("condDataID", dataID);
			map.put("rightTable", rightTable);
			List<TreeNode> checkedNodes = entryMapper.getRefrelaTree(map);

			map.put("columnID", columnID);
			List<DictTFactorPO> columns
				= dictTModelService.getDictTModelByID(tableID, true).getDictTFactorList();
			DictTModelcodePO dictTModelcodePO = null;
			for(DictTFactorPO column : columns) {
				if(column.getColumnid().equals(columnID)) {
					dictTModelcodePO =
						dictTModelcodeService.getDictTModelcodePOByID(column.getCsid());
				}
			}

			map.put("dbTableName", dictTModelcodePO.getDbtablename());
//			map.put("sqlWhere", dictTModelcodePO.getDynamicwhere());
			List<TreeNode> nodes = entryMapper.getRefrelaDbTableTree(map);

			for(TreeNode node : nodes) {
				node.setChecked("false");
				for(TreeNode checked : checkedNodes) {
					if(node.getId().equals(checked.getId())) {
						node.setChecked("true");
					}
				}
			}
			return nodes;
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.INP_00001, "代码表不存在！", false);
		}
	}

	//引用关系定义 列所引用的代码表树
	public List<TreeNode> getRefrelaDbTableTree(String dbTableName) throws Exception {
		DictTModelcodePO dictTModelcodePO = dictTModelcodeService.getDictTModelcodePOByDBtableName(dbTableName);
		String sqlWhere = " ";
		Map<String, Object> map = new HashMap<String, Object>();
		 sqlWhere = dictTModelcodePO.getDynamicwhere();
		map.put("dbTableName", dbTableName);
		map.put("sqlWhere", sqlWhere);
		List<TreeNode> list = new ArrayList<TreeNode>();
		try{//如果 代码表不存在，数据库异常
			list = entryMapper.getRefrelaDbTableTree(map);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("代码表不存在！");
		}
		return list;
	}

	/**
	 * 获得当前登录用户所在地区的下属地区数据集
	 */
	public List<TreeNode> getSubCityTreeNodes(String dbTableName,String code) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dbTableName", dbTableName);
		map.put("code",code);
		List<TreeNode> list = new ArrayList<TreeNode>();
		try{//如果 代码表不存在，数据库异常
			list = entryMapper.getSubCityTreeNodes(map);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("地区表不存在！");
		}
		return list;
	}
	//引用关系定义 引用关系数据
	public List<DictTSetRefrelaDataPO> getRefrelaDbTableData(String relaDbTab,String condDataID,String rightTable) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("relaDbTab", relaDbTab);
		map.put("condDataID", condDataID);
		map.put("rightTable", rightTable);
		return entryMapper.getRefrelaDbTableData(map);
	}

	// 查询（分析引用定义）
	public List<DictTSetAnalyPO> getDataAnalyList(String tableID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		
		return entryMapper.getDataAnalyList(map);
	}
	//  查询（分析引用定义） 详细
	public List<DictTSetHrefParmPO> selectHrefParm(String hrefParmID) {

		return entryMapper.selectHrefParm(hrefParmID);
	}
	// 查询条件设置
	public List<DictTSetQuerydPO> getDataQuerydList(String tableID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		
		return entryMapper.getDataQuerydList(map);
	}
	// 查询条件设置 详细
	public List<DictTSetQuerydDetPO> selectQuerydDet(String tableID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		
		return entryMapper.selectQuerydDet(map);
	}
	// 浮动表设置
	public DictTSetFddefPO getDataFddefList(String tableID) {

		return entryMapper.getDataFddefList(tableID);
	}

	// 固定行列表设置 tableID
	public List<DictTSetFixPO> getDataFixList(String tableID) {

		return entryMapper.getDataFixList(tableID);
	}
	// 固定行列表设置 tableID | typeID
	public DictTSetFixPO getDataFixByTypeList(String tableID, String typeID) {
				
		return entryMapper.getDataFixByTypeList(tableID,typeID);
	}
	//不包含 当前 typeID 的 fdCodeToCols
	public String getOtherToCols(String tableID, String typeID) {
		
		return entryMapper.getOtherToCols(tableID,typeID);
	}
	//创建引用关系 物理表
    @Transactional(readOnly = false, rollbackFor = Exception.class)
	public String createRelaTab(String relaDbTab) {
		//String create_sql = String.format(ConverTables.RELAFACTORY, relaDbTab);
		//String is_table = String.format(ConverTables.IS_TABLE, relaDbTab);
		//String is_view = String.format(ConverTables.IS_VIEW, relaDbTab);

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("relaDbTab", relaDbTab.trim().toUpperCase());
		map.put("is_exits", 0);
		//执行 存储过程
		entryMapper.createRelaTab(map);
		return map.get("is_exits").toString();
	}

	public DictTSetBaseNumTab getDataBaseTabList(String tableID) {
		// 数字基本表设置 主表
		return entryMapper.getDataBaseTabList(tableID);
	}

	public List<DictTSetBaseNumSub> getDataBaseSubList(String columnID, String tableID) {
		String csTBName = this.getRefrelaDbTableName(tableID, columnID);
		// 数字基本表设置 子表
		List<DictTSetBaseNumSub> baseNumSubList = null;
		if(!ConverTables.isNotNull(columnID))
			baseNumSubList = entryMapper.getDataBaseSubByTableID(tableID, csTBName);
		else 
			baseNumSubList = entryMapper.getDataBaseSubByColumnID(columnID, csTBName);
		return baseNumSubList;
	}

	//根据tableID 查询 物理表名
	public String getDbTableName(String tableID){
		String dbTableName = "";
		DictTModelPO model= dictTModelService.getDictTModelByID(tableID, false);
		if(model != null){
			dbTableName = model.getDbtablename();
		}
		return dbTableName;
	}
	//根据columnID  查询 物理列名
	public String getDbColumnName(String columnID){
		String dbColumnName = "";
		DictTFactorPO factor = dictTFactorService.getDictTFactorByColumnId(columnID);
		if(factor != null){ 
			dbColumnName = factor.getDbcolumnname();
		}
		return dbColumnName;
	}
	
	// ------------------- 项目库 阶段
	
	// 分组
	public List<DictTSetGroupPO> getDataGroupByStage(String tableID,String stage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		map.put("stage", stage);
		
		return entryMapper.getDataGroupList(map);
	}
	// 排序
	public List<DictTSetSortPO> getDataSortByStage(String tableID, String stage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		map.put("stage", stage);
		
		return entryMapper.getDataSortList(map);
	}
	// 引用 
	public List<DictTSetRefrelaPO> getDataRefrelaByStage(String tableID,String stage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		map.put("stage", stage);
		
		return entryMapper.getDataRefrelaList(map);
	}
	// 查询分析 
	public List<DictTSetAnalyPO> getDataAnalyByStage(String tableID,String stage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		map.put("stage", stage);
		
		return entryMapper.getDataAnalyList(map);
	}

	// 查询条件设置
	public List<DictTSetQuerydPO> getDataQuerydByStage(String tableID,String stage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		map.put("stage", stage);

		return entryMapper.getDataQuerydList(map);
	}
	
	//查询条件 详细设置
	public List<DictTSetQuerydDetPO> selectQuerydDetByStage(String tableID,String stage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		map.put("stage", stage);
		
		return entryMapper.selectQuerydDet(map);
	}

	public Integer getDataCount(String tableID, String year, String dbColumnName, String columnValue) throws ServiceException {
		Map<String,String> map = new HashMap<String, String>();
		if(ConverTables.isNotNull(tableID)) {
			map.put("tableid", tableID);
			List<DictTModelPO> list = this.dictTModelSelfMapper.getDictTModelDBName(map);
			if(ConverTables.isNotNullList(list)) {
				String dbTableName = list.get(0).getDbtablename();
				map.put("dbTableName", dbTableName);
				year = (year == null || "".equals(year)) ? SecureUtil.getUserSelectYear().toString() : year;
				if(dbColumnName != null && !"".equals(dbColumnName) && columnValue !=null && !"".equals(columnValue)){
					map.put("dbColumnName",dbColumnName);
					map.put("columnValue", columnValue);
					return this.entryMapper.getDataCount2(map);
				}else{
					map.put("year", year);
					return this.entryMapper.getDataCount(map);
				}
			}
		}
		return null;
	}
	
}

package commons.excel2.service.impl;

import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.excel2.service.IExcelFixService;
import com.tjhq.commons.excel2.util.ExcelUtil;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.fixgrid.po.FixGrid;
import com.tjhq.commons.inputcomponent.grid.fixgrid.service.IFixGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.setting.input.po.DictTSetFixPO;
import com.tjhq.commons.setting.input.service.IEntryService;
@Service
@Transactional
public class ExcelFixService extends ExcelService implements IExcelFixService {
	
	@Resource
	public IFixGridService fixGridService;
	
	@Resource
    public IEntryService entryService;
	
	@Override
	public PageInfo getExcelData(String grid, String noData, PageInfo pageInfo) throws ServiceException {
		try{
			FixGrid fixGrid = (FixGrid) new ObjectMapper().readValue(grid, FixGrid.class);
			pageInfo = (PageInfo) this.fixGridService.getData(fixGrid);
			//开始：处理数据，如果agencyid是*，则把agencyid设为所选的agencyid，datakey设为新的datakey
			int dataKeyIndex = 0;
			int agencyIdIndex = 0;
	        if (pageInfo.getColumns().size() > 0) {
	        	dataKeyIndex = pageInfo.getColumns().indexOf("DATAKEY");
	        	agencyIdIndex = pageInfo.getColumns().indexOf("AGENCYID");
	        } 
	        List<Object> tempDataList = null;
	        String agencyIdtemp = null;
	        for (Object data : pageInfo.getDataList()) {
	        	tempDataList = (List<Object>) data;
	        	agencyIdtemp = (String) tempDataList.get(agencyIdIndex);
	        	if("*".equals(agencyIdtemp)){
	        		tempDataList.remove(dataKeyIndex);
	                tempDataList.add(dataKeyIndex, getGUID());
	                tempDataList.remove(agencyIdIndex);
	                tempDataList.add(agencyIdIndex, fixGrid.getExtProperties().get("agencyID"));
	        	}
	        }
	        //结束：处理数据，如果agencyid是*，则把agencyid设为所选的agencyid，datakey设为新的datakey
	        return pageInfo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, e.getMessage(), false);
		}
	}
	
    @Override
	public List<Column> getGridColumnList(String tableID, String grid, String agencyIsLeaf) throws ServiceException{
    	FixGrid fixGrid = new FixGrid();
		fixGrid.setTableID(tableID);
		String userID = SecureUtil.getCurrentUser().getGuid();
		fixGridService.initStructure(fixGrid, userID);
		List<Column> columnList = super.getAllColumnList(fixGrid);
		return columnList;
    }
    
    /*@Override
    public HashMap getTableLevelInfo(String tableID) {
		String sql;
		HashMap tabFieldlevelInfo = new HashMap();

		sql = "select t.dbcolumnname, t2.typeid typeid  from dict_t_factor t,dict_t_setfix t2 where t.tableid='" + tableID + "' and t2.tableid=t.tableid and t2.lvlnanmecol=t.columnid";
		List levelList = this.excelMapper.selectExcelInfo(sql);
		for (int i = 0; i < levelList.size(); i++) {
			HashMap levTMap = (HashMap) levelList.get(i);
			String levnum = (String) levTMap.get("TYPEID");
			String fieldName = (String) levTMap.get("DBCOLUMNNAME");
			tabFieldlevelInfo.put(fieldName, "LEVELNO_" + levnum);
		}

		return tabFieldlevelInfo;
	}*/
    /**
	 * 获取合计列的总数，固定行列表合计行为0，覆盖
	 * @param tableID
	 * @return
	 */
    @Override
    public int getSumFieldCount(String tableID) {
		return 0;
	}
    /**
	 * 导入，处理数据，固定行列表，覆盖
	 * @param dictTModel
	 * @param agencyID
	 * @param insertList
	 * @param updateList
	 * @param checkRepeatMap
	 * @param nowRowNum
	 * @param getDatakeySql
	 * @param hiddenLogicKeyList
	 * @param saveRowMap
	 * @throws ServiceException
	 */
    @Override
    public String[] checkRow(DictTModelPO dictTModel, String agencyID, HashMap<String, Object> checkRepeatMap, String nowRowNum, String getDatakeySql, List<Column> hiddenLogicColumns) throws ServiceException{
		String[] result = new String[2];
		result[0] = "";
		result[1] = "";
		return result;
	}
    /**
	 * 导入，保存数据，固定行列表，覆盖
	 * @param dictTModel
	 * @param agencyID
	 * @param insertList
	 * @param updateList
	 */
    @Override
	public List<String> saveData(DictTModelPO dictTModel, String agencyID, HttpServletRequest request, ArrayList<Map<String, Object>> insertList, ArrayList<Map<String, Object>> updateList) throws ServiceException{
    	
    	String tableID = dictTModel.getTableid();
    	String dbTableName = dictTModel.getDbtablename();
    	
    	//根据tableID查询固定行列表设置信息
        List<DictTSetFixPO> fixs = entryService.getDataFixList(tableID);
        String sql = "";
		if(insertList.size() > 0){
			sql = "select * from " + dbTableName + "_cfg where istemplate = '1' and agencyid = '*' order by FDCODE_1 ";
			List<HashMap<String, Object>> cfgList = this.excelMapper.selectExcelInfo(sql);
			if(cfgList.size() == 0){
				throw new ServiceException(ExceptionCode.ERR_00000, "查询模板cfg表为空，不能导入", false);
			}
			int i = 0;
			for(Map<String, Object> map : insertList){
				Map<String, Object> cfgMap = cfgList.get(i);
				i++;
				map.put("ISTEMPLATE", cfgMap.get("ISTEMPLATE"));
				map.put("TEMPLATEID", cfgMap.get("TEMPLATEID"));
				map.put("CELLSECU", cfgMap.get("CELLSECU"));
				map.put("SYNSTATUS", cfgMap.get("SYNSTATUS"));
				for(String controlDbColumnName : ExcelUtil.getControlFields_A1()){
					for (DictTSetFixPO po : fixs) {
						String controlDbColumnName_ = "";
		                String typeID = po.getTypeID();
		                controlDbColumnName_ += controlDbColumnName + "_" + typeID;
		                
						if(cfgMap.containsKey(controlDbColumnName_)){
							map.put(controlDbColumnName_, cfgMap.get(controlDbColumnName_));
						}else{
							throw new ServiceException(ExceptionCode.ERR_00000, "查询模板cfg表字段错误：" + controlDbColumnName_, false);
						}
					}
				}
			}
		}
		sql = "delete from " + dbTableName + " a where agencyid = '" + agencyID + "'";
		this.excelMapper.updateSql(sql);
		//保存数据
		FixGrid grid = new FixGrid();
		grid.setTableID(tableID);
		grid.setInsertValues(insertList);
		grid.setUpdateValues(updateList);
		grid.setTableDBName(dbTableName);
		grid.setTableName(dictTModel.getName());
		fixGridService.saveData(grid);
		List<String> objectList = new ArrayList<String>();
		objectList.add(agencyID);
		return objectList;
	}
    
    
}

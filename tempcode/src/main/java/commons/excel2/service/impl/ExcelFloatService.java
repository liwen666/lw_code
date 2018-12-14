package commons.excel2.service.impl;

import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.excel2.service.IExcelFloatService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.floatgrid.po.FloatGrid;
import com.tjhq.commons.inputcomponent.grid.floatgrid.service.IFloatGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.PageInfo;
@Service
@Transactional
public class ExcelFloatService extends ExcelService implements IExcelFloatService {
	
	@Resource
	public IFloatGridService floatGridService;
	
	@Override
	public PageInfo getExcelData(String grid, String noData, PageInfo pageInfo) throws ServiceException {
		try{
			FloatGrid floatGrid = (FloatGrid) new com.tjhq.commons.jackson.ObjectMapper().readValue(grid, FloatGrid.class);
			pageInfo = (PageInfo) this.floatGridService.getData(floatGrid);
			
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
	                tempDataList.add(agencyIdIndex, floatGrid.getExtProperties().get("agencyID"));
	        	}
	        }
	        return pageInfo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, e.getMessage(), false);
		}
	}
    @Override
	public List<Column> getGridColumnList(String tableID, String grid, String agencyIsLeaf) throws ServiceException{
    	FloatGrid floatGrid = new FloatGrid();
		floatGrid.setTableID(tableID);
		String userID = SecureUtil.getCurrentUser().getGuid();
		floatGridService.initStructure(floatGrid, userID);
		List<Column> columnList = super.getAllColumnList(floatGrid);
		return columnList;
    }
    /*
    @Override
    public HashMap<String, String> getTableLevelInfo(String tableID) {
		String sql;
		HashMap tabFieldlevelInfo = new HashMap();

		sql = "select t.dbcolumnname, t2.lvlnanmecol levelno  from dict_t_factor t,dict_t_setfddef t2 where t.tableid='" + tableID + "' and t2.tableid=t.tableid and t2.lvlnanmecol=t.columnid";
		List levelList = this.excelMapper.selectExcelInfo(sql);
		if (levelList.size() > 0) {
			HashMap levTMap = (HashMap) levelList.get(0);
			String fieldName = (String) levTMap.get("DBCOLUMNNAME");
			tabFieldlevelInfo.put(fieldName, "LEVELNO");
		}

		return tabFieldlevelInfo;
	}
    */
}

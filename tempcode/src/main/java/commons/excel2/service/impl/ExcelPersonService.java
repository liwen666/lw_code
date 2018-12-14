package commons.excel2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.Constants;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.excel2.service.IExcelPersonService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.po.ResultPO;
@Service
@Transactional
public class ExcelPersonService extends ExcelCommonService implements IExcelPersonService {
	/**
	 * 导入，保存数据，一般录入表
	 * @param dictTModel
	 * @param agencyID
	 * @param insertList
	 * @param updateList
	 */
	@Override
	public List<String> saveData(DictTModelPO dictTModel, String agencyID, HttpServletRequest request, ArrayList<Map<String, Object>> insertList, ArrayList<Map<String, Object>> updateList) throws ServiceException{
		/*
		 * 检查当前用户对业务主体的操作权限
		 */
		List<String> objectList = new ArrayList<String>();
		for (Map<String, Object> data : insertList) {
            if (!objectList.contains(data.get(Constants.COL_DBNAME_AGENCYID))) {
                objectList.add((String)data.get(Constants.COL_DBNAME_AGENCYID));
            }
            data.put(Constants.COL_DBNAME_CONFIRMFLAG, "0");
        }
        for (Map<String, Object> data : updateList) {
            if (!objectList.contains(data.get(Constants.COL_DBNAME_AGENCYID))) {
                objectList.add((String)data.get(Constants.COL_DBNAME_AGENCYID));
            }
            //data.put(Constants.COL_DBNAME_CONFIRMFLAG, "2");//此处逻辑放入excelService的handleAndSaveExcelData中，人员表特殊处理，查出CONFIRMFLAG来判断导入的CONFIRMFLAG的值，如果修改的数据集中的CONFIRMFLAG为新增，则还是新增，其他改为修改待确认。
        }
        //如果数据中没有AGENCYID，则把所选agencyID放入objectList
        if(objectList.size() == 0){
        	if(StringUtils.isNotEmpty(agencyID)){
        		objectList.add(agencyID);
    		}
        }
        String taskID = request.getParameter("taskID");
        String appID = request.getParameter("appID");
		String docID = request.getParameter("docID");
		String flowType = request.getParameter("flowType");
		String nodeID = request.getParameter("nodeID");
		String wfVersion = request.getParameter("wfVersion");
		ResultPO resPO = flowSecuService.checkUserSecuForObject(appID, docID, taskID, flowType, nodeID, wfVersion, objectList);
		if (!resPO.isSuccessFlag()) {
			throw new ServiceException(ExceptionCode.ERR_00000, resPO.getErrMsg(), false);
        }
		
		/*
		 * 保存数据
		 */
		String tableID = dictTModel.getTableid();
		CommonGrid commonGrid = new CommonGrid();
		commonGrid.setTableID(tableID);
		commonGrid.setInsertValues(insertList);
		commonGrid.setUpdateValues(updateList);
		commonGrid.setTableDBName(dictTModel.getDbtablename());
		commonGrid.setTableName(dictTModel.getName());
		//保存数据
		commonGridService.saveData(commonGrid);
		
		return objectList;
	}
}

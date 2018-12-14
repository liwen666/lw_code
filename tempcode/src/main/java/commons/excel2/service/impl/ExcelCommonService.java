package commons.excel2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.Constants;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.excel2.service.IExcelCommonService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.po.Condition;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.utils.UserInfo;
import com.tjhq.commons.utils.UserUtil;
import com.tjhq.exp.datainput_oa.secu.service.IFlowSecuService;
import com.tjhq.exp.datainput_oa.table.utils.service.ICommonGridConditionUtilService;
@Service
@Transactional
public class ExcelCommonService extends ExcelService implements IExcelCommonService {
	
	@Resource
    public ICommonGridConditionUtilService commonGridConditionUtilService;
	
	@Resource
    public IFlowSecuService flowSecuService;
	
	/**
	 * 查询数据
	 */
	@Override
	public PageInfo getExcelData(String grid, String noData, PageInfo pageInfo) throws ServiceException {
		try {
			//得到一般录入表的Grid对象
			CommonGrid commonGrid = (CommonGrid) new ObjectMapper().readValue(grid, CommonGrid.class);
			UserInfo userInfo = UserUtil.getUserInfo();
            String userAgency = userInfo.getAgency();
            String fromDeptID = userInfo.getUpAgencyID();
            String userID = userInfo.getGuid();
            commonGrid.getExtProperties().put("userAgency", userAgency);
            commonGrid.getExtProperties().put("userID", userID);
            commonGrid.getExtProperties().put("fromDeptID", fromDeptID);
            //设置条件
            commonGridConditionUtilService.commonGridSetCondition(commonGrid);
			//去掉分组
			commonGrid.setGroupColumnsList(null);
			//分页查询
			commonGrid.setPagination(true);
			//放入分页对象
			commonGrid.setPageInfo(pageInfo);
			//空模板则放入一个查不到数据的条件，激活合计SQL
			if("1".equals(noData)){
				Condition condition = new Condition();
				condition.setExpression(" datakey = '123214356675765765876866543'");
				List<Condition> conditionList = commonGrid.getQueryPanelParamList();
				if (conditionList == null){
					conditionList = new ArrayList<Condition>();
				}
				conditionList.add(condition);
				commonGrid.setQueryPanelParamList(conditionList);
			}
			//查询数据
			pageInfo = (PageInfo) this.getData(commonGrid);
			return pageInfo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, e.getMessage(), false);
		}
	}
	
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
        }
        for (Map<String, Object> data : updateList) {
            if (!objectList.contains(data.get(Constants.COL_DBNAME_AGENCYID))) {
                objectList.add((String)data.get(Constants.COL_DBNAME_AGENCYID));
            }
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

package commons.excel2.service.impl;

import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.Constants;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.excel2.service.IExcelTransferPayService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.po.Condition;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.utils.UserInfo;
import com.tjhq.commons.utils.UserUtil;
@Service
@Transactional
public class ExcelTransferPayService extends ExcelCommonService implements IExcelTransferPayService {
    
	/**
	 * 查询数据
	 */
	@Override
	public PageInfo getExcelData(String grid, String noData, PageInfo pageInfo) throws ServiceException {
		try{
			//查询数据
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
			
			Condition condition = new Condition();
			if("1".equals(noData)){//空模板
				condition.setExpression(" DATAKEY = '123214356675765765876866543'");
			}else{
				condition.setExpression(" INPUTDISTRICT=(SELECT ADMDIV FROM FASP_T_CAUSER  WHERE GUID = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('USER')) ");
			}
			List<Condition> conditionList = commonGrid.getQueryPanelParamList();
			if (conditionList == null){
				conditionList = new ArrayList<Condition>();
			}
			conditionList.add(condition);
			commonGrid.setQueryPanelParamList(conditionList);
			pageInfo = (PageInfo) this.getData(commonGrid);
			
			return pageInfo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, e.getMessage(), false);
		}
	}
	/**
	 * 导入，保存数据，转移支付表
	 * @param dictTModel
	 * @param agencyID
	 * @param insertList
	 * @param updateList
	 */
	@Override
	public List<String> saveData(DictTModelPO dictTModel, String agencyID, HttpServletRequest request, ArrayList<Map<String, Object>> insertList, ArrayList<Map<String, Object>> updateList) throws ServiceException{
		
		String taskID = request.getParameter("taskID");
		
		String inputdistrict = SecureUtil.getCurrentUser().getUpagencyid();
		String sql = "SELECT DISTRLVL FROM CODE_T_DISTRICT T WHERE T.GUID = '" + inputdistrict + "'";
		List<HashMap<String, Object>> tmpList = this.excelMapper.selectExcelInfo(sql);
		
		if(tmpList.size() == 0 ){
			throw new ServiceException(ExceptionCode.ERR_00000, "用户地区级次为空，不能导入", false);
		}
		HashMap<String, Object> tmpMap = tmpList.get(0);
		BigDecimal distrlvl = (BigDecimal) tmpMap.get("DISTRLVL");
		if(StringUtils.isEmpty(distrlvl + "")){
			throw new ServiceException(ExceptionCode.ERR_00000, "用户地区级次为空，不能导入", false);
		}
		
		//查询对账结果默认值
		DictTFactorPO dictTFactorPO = dictTFactorService.getDictTFactorByDBColumnName(Constants.COL_DBNAME_DZJG, dictTModel.getTableid());
		String dzjg = "";
		if(dictTFactorPO != null){
			dzjg = dictTFactorPO.getDefaultvalue();
			if(StringUtils.isNotEmpty(dzjg)){
				sql = "SELECT " + dzjg + " as dzjg FROM dual ";
				tmpList = this.excelMapper.selectExcelInfo(sql);
				tmpMap = tmpList.get(0);
				dzjg = ((String) tmpMap.get("DZJG")).trim();
			}
		}
		String iswzz = "0";
		/*
		 * 检查当前用户对业务主体的操作权限
		 */
		List<String> objectList = new ArrayList<String>();
		for (Map<String, Object> data : insertList) {
            if (!objectList.contains(data.get(Constants.COL_DBNAME_AGENCYID))) {
                objectList.add((String)data.get(Constants.COL_DBNAME_AGENCYID));
            }
        	data.put(Constants.COL_DBNAME_BTASKID, taskID);
        	data.put(Constants.COL_DBNAME_INPUTLVL, distrlvl+"");
        	data.put(Constants.COL_DBNAME_INPUTDISTRICT, inputdistrict+"");
        	data.put(Constants.COL_DBNAME_DZJG, dzjg);
        	data.put(Constants.COL_DBNAME_ISWZZ, iswzz);
        }
        for (Map<String, Object> data : updateList) {
            if (!objectList.contains(data.get(Constants.COL_DBNAME_AGENCYID))) {
                objectList.add((String)data.get(Constants.COL_DBNAME_AGENCYID));
            }
        	data.put(Constants.COL_DBNAME_BTASKID, taskID);
        	data.put(Constants.COL_DBNAME_INPUTLVL, distrlvl+"");
        	data.put(Constants.COL_DBNAME_INPUTDISTRICT, inputdistrict+"");
        	data.put(Constants.COL_DBNAME_DZJG, dzjg);
        	data.put(Constants.COL_DBNAME_ISWZZ, iswzz);
        }
        //如果数据中没有AGENCYID，则把所选agencyID放入objectList
        if(objectList.size() == 0){
        	if(StringUtils.isNotEmpty(agencyID)){
        		objectList.add(agencyID);
    		}
        }
        
        String agencyStr = "";
		for(String str : objectList){
			agencyStr += str + "','";
        }
		agencyStr = agencyStr.substring(0, agencyStr.length() - 3);
		
		//任务，单位，级次跟用户级次不同，有数据，不能导入
		sql = "select t.agencyid as agencyid, a.code||'-'||a.name as name from " + dictTModel.getDbtablename() + " t left join code_t_agency_spf a on a.guid = t.agencyid where inputlvl <> '" + distrlvl + "' and btaskid = '" + taskID + "' and agencyid in ('" + agencyStr + "')";
		tmpList = this.excelMapper.selectExcelInfo(sql);
		if(tmpList.size() > 0){
			Iterator<HashMap<String, Object>> it1 = tmpList.iterator();
			String nameStr = "";
			while (it1.hasNext()) {
				HashMap<String, Object> map = it1.next();
				String name = (String) map.get("NAME");
				nameStr += name +",";
			}
			throw new ServiceException(ExceptionCode.ERR_00000, "本任务某些单位下数据的级次和用户级次不同，不能导入，单位包括："+nameStr, false);
		}
        
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
		CommonGrid commonGrid = new CommonGrid();
		commonGrid.setTableID(dictTModel.getTableid());
		commonGrid.setInsertValues(insertList);
		commonGrid.setUpdateValues(updateList);
		commonGrid.setTableDBName(dictTModel.getDbtablename());
		commonGrid.setTableName(dictTModel.getName());
		commonGridService.saveData(commonGrid);
		
		return objectList;
	}
	
}

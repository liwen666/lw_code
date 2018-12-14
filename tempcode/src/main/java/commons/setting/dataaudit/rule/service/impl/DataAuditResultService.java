package commons.setting.dataaudit.rule.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.setting.dataaudit.rule.dao.DataAuditResultDAO;
import com.tjhq.commons.setting.dataaudit.rule.po.CheckContionPO;
import com.tjhq.commons.setting.dataaudit.rule.po.CheckErrorPO;
import com.tjhq.commons.setting.dataaudit.rule.po.CheckResultPO;
import com.tjhq.commons.setting.dataaudit.rule.service.IDataAuditResultService;
import com.tjhq.exp.datainput.dataaudit.service.impl.DataAuditService;

/**
 *@desc:数据审核结果service
 *@author： wxn
 *@date:2015-11-20上午11:26:55
 */
@Service
@Transactional(readOnly=true)
public class DataAuditResultService implements IDataAuditResultService {
	Logger logger = Logger.getLogger(DataAuditResultService.class);
	@Resource
	private  DataAuditResultDAO dataAuditResultDAO;

	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void saveCheckResult(List<CheckResultPO> dataList) throws ServiceException {
		for(CheckResultPO po:dataList){
			saveCheckResult(po);
		}
		
	}

	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void saveCheckResult(CheckResultPO checkResultPO)  throws ServiceException {
		try{
		dataAuditResultDAO.insertCheckResult(checkResultPO);
		}catch(Exception e){
			throw this.handleServiceException(ExceptionCode.ERR_00000,"保存审核结果信息出错",e);
		}
	}

	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void saveCheckError(CheckErrorPO checkErrorPO)  throws ServiceException{
		try{
		dataAuditResultDAO.insertCheckError(checkErrorPO);
		}catch(Exception e){
			throw this.handleServiceException(ExceptionCode.ERR_00000,"保存审核错误信息出错",e);
		}
	}

	@Override
	public List<CheckResultPO> getCheckResult(String busiCheckid,
			String taskID, String agencyID,String deidContion) throws ServiceException {
		Map<String,Object> paraMap=new HashMap<String,Object>();
		if(null!=busiCheckid && !"".equals(busiCheckid)){
			paraMap.put("busiCheckID", busiCheckid);
		}
		if(null!=taskID && !"".equals(taskID)){
			paraMap.put("taskID", taskID);
		}
		if(null!=agencyID && !"".equals(agencyID)){
			paraMap.put("agencyID", agencyID);
		}
		if(null!=deidContion && !"".equals(deidContion)){
			paraMap.put("deidContion", deidContion);
		}
		try{
		return dataAuditResultDAO.findCheckResult(paraMap);
		}catch(Exception e){
			logger.debug("查询参数为:"+paraMap);
			throw this.handleServiceException(ExceptionCode.ERR_00000,"查询审核结果信息失败",e);
		}
	}

	@Override
	/**
	 * 根据checkResultID 来查询 审核错误数据
	 */
	public List<Map<String, String>> getCheckErrorByCheckResultID(
			String checkResultID) throws ServiceException {
		try{
		return dataAuditResultDAO.findCheckErrorByCheckResultID(checkResultID);
		}catch(Exception e){
			logger.debug("checkResultID="+checkResultID);
			throw this.handleServiceException(ExceptionCode.ERR_00000,"查询审核错误信息 失败",e);
		}
	}

	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void delCheckResult(String taskID, String busiCheckID,
			String agencyID) throws ServiceException {
		Map<String,Object> paraMap=new HashMap<String,Object>();
		paraMap.put("taskID", taskID);
		paraMap.put("busiCheckID", busiCheckID);
		paraMap.put("agencyID", agencyID);
		try{
		dataAuditResultDAO.delCheckResult(paraMap);
		}catch(Exception e){
			logger.debug("paraMap="+paraMap);
			throw this.handleServiceException(ExceptionCode.ERR_00000,"删除审核结果信息失败",e);
		}
	}

	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void delCheckErrorByCheckResultID(String checkResultID)
			throws ServiceException {
		try{
		dataAuditResultDAO.delCheckErrorByCheckResultID(checkResultID);
		}catch(Exception e){
			logger.debug("checkResultID="+checkResultID);
			throw this.handleServiceException(ExceptionCode.ERR_00000,"删除审核错误信息 失败",e);
		}
		
	}

	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void copyCheckError(String srcCheckResultID, String toCheckResultID)
			throws ServiceException {
		Map<String,Object> paraMap=new HashMap<String,Object>();
		paraMap.put("srcCheckResultID", srcCheckResultID);
		paraMap.put("toCheckResultID", toCheckResultID);
		try{
		dataAuditResultDAO.copyCheckError(paraMap);
		}catch(Exception e){
			logger.debug("paraMap="+paraMap);
			throw this.handleServiceException(ExceptionCode.ERR_00000,"复制审核错误信息 失败",e);
		}
	}

	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void delCheckErrorBybusiCheckID(String busiCheckID, String taskID) throws ServiceException{
		Map<String,Object> paraMap=new HashMap<String,Object>();
		paraMap.put("busiCheckID", busiCheckID);
		paraMap.put("taskID", taskID);
		try{
		dataAuditResultDAO.delCheckErrorBybusiCheckID(paraMap);
		}catch(Exception e){
			logger.debug("paraMap="+paraMap);
			throw this.handleServiceException(ExceptionCode.ERR_00000,"删除审核错误信息 失败",e);
		}
		
	}

	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void delCheckResultBybusiCheckID(String busiCheckID, String taskID) throws ServiceException{
		Map<String,Object> paraMap=new HashMap<String,Object>();
		paraMap.put("busiCheckID", busiCheckID);
		paraMap.put("taskID", taskID);
		try{
		dataAuditResultDAO.delCheckResultBybusiCheckID(paraMap);
		}catch(Exception e){
			logger.debug("paraMap="+paraMap);
			throw this.handleServiceException(ExceptionCode.ERR_00000,"删除审核结果信息 失败",e);
		}

	}
	private ServiceException handleServiceException(String errorCode, String errorMsg,
			Exception e) {
		e.printStackTrace();
		return new ServiceException(errorCode,errorMsg,false);
	}

	@Override
	public List<Map<String, String>> getCheckErrorExtByCheckResultID(
			String checkResultID) throws ServiceException {
		try{
		return dataAuditResultDAO.findCheckErrorExtByCheckResultID(checkResultID);
		}catch(Exception e){
			logger.debug("checkResultID="+checkResultID);
			throw this.handleServiceException(ExceptionCode.ERR_00000,"查询审核错误信息 失败",e);
		}
	}

	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void delCheckContionByCheckResultID(String checkResultID)
			throws ServiceException {
		dataAuditResultDAO.delCheckContionByCheckResultID(checkResultID);
		
	}

	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void delCheckResultByGuid(String checkResultID)
			throws ServiceException {
		dataAuditResultDAO.delCheckResultByGuid(checkResultID);
	}

	@Override
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void saveCheckContion(CheckContionPO contionPO)
			throws ServiceException {
		dataAuditResultDAO.insertCheckContion(contionPO);
		
	}


}


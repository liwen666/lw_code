package com.exception.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exception.dao.IExceptionDAO;
import com.exception.po.ExceptionInfoPO;
import com.exception.service.IExceptionService;


@Service
@Transactional(readOnly = true)
public class ExceptionService implements IExceptionService {
	
	Logger logger = Logger.getLogger(ExceptionService.class);
	
	@Resource
	private IExceptionDAO exceptionDAO;
	
	public IExceptionDAO getExceptionDAO() {
		return exceptionDAO;
	}

	public void setExceptionDAO(IExceptionDAO exceptionDAO) {
		this.exceptionDAO = exceptionDAO;
	}


	@Override
	public List<ExceptionInfoPO> loadException() throws Exception {
		List<ExceptionInfoPO> exceptionList = null;
        try {
            exceptionList = getExceptionDAO().loadException();
        } catch (Exception e) {
            logger.debug("Load Exception Fail !");
        }
		if (exceptionList != null) {
		    logger.debug("Load Exception Num: "+exceptionList.size());   
		}
		return exceptionList;
	}
	
	@Override
	public ExceptionInfoPO loadException(String code) throws Exception {
		ExceptionInfoPO exceptionInfoPo = null;
        try {
            exceptionInfoPo = getExceptionDAO().loadExceptionByCode(code);
        } catch (Exception e) {
            logger.debug("Load Exception Fail !");
        }
		if (exceptionInfoPo != null) {
		    logger.debug("Load Exception Info: " + exceptionInfoPo.toString());
		}
		return exceptionInfoPo;
	}
}

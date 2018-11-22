package com.exception.service;

import java.util.List;

import com.exception.po.ExceptionInfoPO;


public interface IExceptionService {
	
	public List<ExceptionInfoPO> loadException() throws Exception;
	
	public ExceptionInfoPO loadException(String code) throws Exception;
}

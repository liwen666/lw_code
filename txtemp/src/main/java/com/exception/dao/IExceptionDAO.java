package com.exception.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.exception.po.ExceptionInfoPO;
import common.mybatis.SuperMapper;


public interface IExceptionDAO extends SuperMapper {
	
	public List<ExceptionInfoPO> loadException();
	
	public ExceptionInfoPO loadExceptionByCode(@Param("code")String code);
}

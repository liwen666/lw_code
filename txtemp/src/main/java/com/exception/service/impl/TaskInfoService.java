/**
 * @Title: TaskInfoService.java
 * @Copyright (C) 2016 太极华青
 * @Description: 对外开放， 获取任务相关信息
 * @Revision 1.0 2016-5-3  CAOK
 */
 

package com.exception.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exception.ServiceException;
import com.exception.service.ITaskInfoService;


/**
 * @ClassName: TaskInfoService
 * @Description: 对外开放， 获取任务相关信息
 * @author: CAOK 2016-5-3 下午03:06:28
 */
@Service
@Transactional(readOnly = true)
public class TaskInfoService implements ITaskInfoService {

	@Override
	public String getFinYearByDocID(String docID) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFinYearByTaskID(String taskID) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
    
  
}

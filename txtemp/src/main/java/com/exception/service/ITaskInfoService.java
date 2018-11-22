/**
 * @Title: TaskInfoService.java
 * @Copyright (C) 2016 太极华青
 * @Description: 对外开放， 获取任务相关信息
 * @Revision 1.0 2016-5-3  CAOK
 */

package com.exception.service;

import com.exception.ServiceException;

/**
 * @ClassName: ITaskInfoService
 * @Description: 对外开放， 获取任务相关信息
 * @author: CAOK 2016-5-3 下午03:00:53
 */

public interface ITaskInfoService {
    
    /**.
     * 根据公文ID获取当前公文对应任务的财年
     * @param docID 公文ID
     * @return 
     * @throws ServiceException
     */
    public String getFinYearByDocID(String docID) throws ServiceException;
    
    /**.
     * 根据任务ID获取当前财年
     * @param taskID 任务ID
     * @return
     * @throws ServiceException
     */
    public String getFinYearByTaskID(String taskID) throws ServiceException;
}

package commons.setting.dataaudit.auditrule.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.setting.dataaudit.auditrule.po.AuditDataContionPO;
import com.tjhq.commons.setting.dataaudit.auditrule.po.AuditDataErrorPO;
import com.tjhq.commons.setting.dataaudit.auditrule.po.AuditDataResultPO;

/**
 *@desc:审核结果service
 *@author： wxn
 *@date:2015-11-20上午11:26:33
 */
public interface IAuditDataResultService {
	/**
	 * 批量保存审核结果
	 * @param dataList
	 */
	public void saveCheckResult(List<AuditDataResultPO> dataList) throws ServiceException;
	/**
	 * 一次保存一个审核结果记录
	 * @param ResultPO
	 */
	public void saveCheckResult(AuditDataResultPO checkResultPO) throws ServiceException;
	/**
	 * 保存 审核错误数据
	 * @param checkErrorPO
	 */
	public void saveCheckError(AuditDataErrorPO checkErrorPO) throws ServiceException;
	/**
	 * 查询历史审核数据
	 * @param busiCheckid
	 * @param taskID
	 * @param agencyID
	 * @return
	 * @throws ServiceException
	 */
	public List<AuditDataResultPO> getCheckResult(String busiCheckid, String taskID, String agencyID, String deidContion)throws ServiceException;
	/**
	 * 根据 
	 * @param checkResultID
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, String>> getCheckErrorByCheckResultID(String checkResultID)throws ServiceException;
	/**
	 * 删除 checkResutl
	 * @param taskID
	 * @param busiCheckID
	 * @param agencyID
	 * @throws ServiceException
	 */
	public void delCheckResult(String taskID, String busiCheckID, String agencyID) throws ServiceException;
	/**
	 *删除  审核错误信息
	 * @param checkResultID
	 * @throws ServiceException
	 */
	public void delCheckErrorByCheckResultID(String checkResultID) throws ServiceException;
	/**
	 *删除  审核错误信息
	 * @param checkResultID
	 * @throws ServiceException
	 */
	public void copyCheckError(String srccheckResultID, String toCheckResultID) throws ServiceException;
	/**
	 * 根据业务审核ID 和taskID删除审核错误数据
	 * @param busiCheckID
	 * @param taskID
	 */
	public void delCheckErrorBybusiCheckID(String busiCheckID, String taskID)throws ServiceException;
	/**
	 * 根据  业务审核ID 任务ID  删除审核结果记录
	 * @param busiCheckID
	 * @param taskID
	 */
	public void delCheckResultBybusiCheckID(String busiCheckID, String taskID)throws ServiceException;
	/**
	 * 获得数据审核错误信息
	 * @param checkResultID
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, String>> getCheckErrorExtByCheckResultID(
            String checkResultID)throws ServiceException;
	/**
	 * 删除审核条件
	 * @param checkResultID
	 * @throws ServiceException
	 */
	public void delCheckContionByCheckResultID(String checkResultID)throws ServiceException;
	/**
	 * 根据iD删除审核结果
	 * @param checkResultID
	 * @throws ServiceException
	 */
	public void delCheckResultByGuid(String checkResultID)throws ServiceException;
	/**
	 * 保存审核条件
	 * @param contionPO
	 * @throws ServiceException
	 */
	public void saveCheckContion(AuditDataContionPO contionPO)throws ServiceException;


}


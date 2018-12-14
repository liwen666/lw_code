package commons.setting.dataaudit.rule.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.setting.dataaudit.rule.po.CheckContionPO;
import com.tjhq.commons.setting.dataaudit.rule.po.CheckErrorPO;
import com.tjhq.commons.setting.dataaudit.rule.po.CheckResultPO;

/**
 *@desc:TODO
 *@author： wxn
 *@date:2015-11-20上午11:28:27
 */
public interface DataAuditResultDAO extends SuperMapper {
	/**
	 * 保存 审核结果
	 * @param po
	 */
	public void insertCheckResult(CheckResultPO po);
	/**
	 * 保存审核错误数据
	 * @param po
	 */
	public void insertCheckError(CheckErrorPO po);
	/**
	 * 查询审核结果数据
	 * @param paraMap
	 * @return
	 */
	public List<CheckResultPO> findCheckResult(Map<String, Object> paraMap);
	/**
	 *根据审核结果id查询审核数据
	 * @param checkResultID
	 * @return
	 */
	public List<Map<String, String>> findCheckErrorByCheckResultID(
            String checkResultID);
	/**
	 * 删除审核结果
	 * @param paraMap
	 */
	public void delCheckResult(Map<String, Object> paraMap);
	/**
	 * 删除审核错误信息
	 * @param checkResultID
	 */
	public void delCheckErrorByCheckResultID(String checkResultID);
	/**
	 * 拷贝数据
	 * @param paraMap
	 */
	public void copyCheckError(Map<String, Object> paraMap);
	/**
	 * 根据 业务审核设置的ID 删除审核错误数据
	 * @param paraMap
	 */
	public void delCheckErrorBybusiCheckID(Map<String, Object> paraMap);
	/**
	 * 根据 业务审核设置的ID 删除审核结果数据
	 * @param paraMap
	 */
	public void delCheckResultBybusiCheckID(Map<String, Object> paraMap);
	/**
	 * 综合审核查询错误信息
	 * @param checkResultID
	 * @return
	 */
	public List<Map<String, String>> findCheckErrorExtByCheckResultID(
            String checkResultID);
	/**
	 * 删除审核条件
	 * @param checkResultID
	 */
	public void delCheckContionByCheckResultID(String checkResultID);
	/**
	 * 根据 ID删除审核结果
	 * @param checkResultID
	 */
	public void delCheckResultByGuid(String checkResultID);
	/**
	 * 插入审核条件
	 * @param contionPO
	 */
	public void insertCheckContion(CheckContionPO contionPO);

}


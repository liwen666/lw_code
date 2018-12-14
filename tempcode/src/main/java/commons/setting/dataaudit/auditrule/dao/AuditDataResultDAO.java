package commons.setting.dataaudit.auditrule.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.setting.dataaudit.auditrule.po.AuditDataContionPO;
import com.tjhq.commons.setting.dataaudit.auditrule.po.AuditDataErrorPO;
import com.tjhq.commons.setting.dataaudit.auditrule.po.AuditDataResultPO;

/**
* @ClassName: AuditDataResultDAO
* @Description:审核数据结果操作dao
* @author xiehongtao
* @date 2017-6-15 下午1:18:24
*
 */
public interface AuditDataResultDAO extends SuperMapper {
	/**
	 * 保存 审核结果
	 * @param po
	 */
	public void insertCheckResult(AuditDataResultPO po);
	/**
	 * 保存审核错误数据
	 * @param po
	 */
	public void insertCheckError(AuditDataErrorPO po);
	/**
	 * 查询审核结果数据
	 * @param paraMap
	 * @return
	 */
	public List<AuditDataResultPO> findCheckResult(Map<String, Object> paraMap);
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
	public void insertCheckContion(AuditDataContionPO contionPO);

}


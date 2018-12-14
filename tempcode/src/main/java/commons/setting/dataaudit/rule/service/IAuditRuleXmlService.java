package commons.setting.dataaudit.rule.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.setting.dataaudit.rule.po.ColPO;

/**
 *@desc:数据审核规则xml导入导出service
 *@author： wxn
 *@date:2016-2-29下午1:38:48
 */
public interface IAuditRuleXmlService {
	/**
	 * 查询数据审核数据
	 * @param paraMap
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String,String>> findAuditData(Map<String, Object> paraMap) throws ServiceException;
	/**
	 * 获得列配置信息
	 * @return
	 */
	public List<ColPO> findAuditColList();
	/**
	 * 查询当前时间
	 * @return
	 * @throws ServiceException
	 */
	public String getSysDate()throws ServiceException;
	/**
	 * 判断checkID是否存在
	 * @param paraMap
	 * @return
	 * @throws ServiceException
	 */
	public boolean isExistCheckID(Map<String, Object> paraMap)throws ServiceException;
	/**
	 * 是否存在checkSortID id
	 * @param paraMap
	 * @return
	 * @throws ServiceException
	 */
	public boolean isExistCheckSortID(Map<String, Object> paraMap)throws ServiceException;
	/**
	 * 验证数据
	 * @param paraMap
	 * @return
	 * @throws ServiceException
	 * @throws Exception 
	 */
	public boolean verifyData(Map<String, Object> paraMap)throws ServiceException;
	
	/**
	 * 
	 * @param paraMap
	 * @return
	 * @throws ServiceException
	 */
	public boolean isExistTable(String tableID)throws ServiceException;
	/**
	 * 保存审核规则
	 * @param formMap
	 */
	public void saveOrUpdateAuditData(Map<String, Object> paraMap)throws ServiceException;
	/**
	 * 查询审核分类数据
	 * @param paraMap
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String,String>> findAuditSortDataByExp(Map<String, Object> paraMap) throws ServiceException;
	/**
	 * 查询审核分类数据
	 * @param paraMap
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String,String>> findAuditSortDataFromRoot(Map<String, Object> paraMap) throws ServiceException;
	/**
	 * 查询分类数据
	 * @return
	 */
	public List<ColPO> findAuditSortColList();
	/**
	 * 导入xml数据
	 * @param paramMap
	 * @param root 
	 * @throws ServiceException
	 */
	public void impXmlData(HashMap<String, Object> paramMap, Element root)throws ServiceException;
	/**
	 * 查询审核视图对应的物理表
	 * @param paraMap
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String,String>> findViewToTable(Map<String, Object> paraMap) throws ServiceException;
	/**
	 * 查询录入对象对应审核对象
	 * @param paraMap
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String,String>> findInputObjToAuditObj(Map<String, Object> paraMap) throws ServiceException;
	/**
	 * 视图对应表列信息
	 * @return
	 */
	public List<ColPO> findViewToTableColList();
	/**
	 * 录入对象对应审核对象列信息
	 * @return
	 */
	public List<ColPO> findInputObjToAuditObjColList();
	
}


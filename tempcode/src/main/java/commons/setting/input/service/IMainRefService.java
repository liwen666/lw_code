package commons.setting.input.service;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.setting.external.po.RECPO;


public interface IMainRefService {

	/**
	 * 录入表实体保存方法
	 * @Title: saveProjTabEntity
	 * @Description: 保存录入表实体
	 * @date: 2014-1-14
	 * @return void
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	public void saveProjTabEntity(Map<String, String> map) throws ServiceException;


	/**
	 * 更新录入表实体
	 * @Title: updateProjTabEntity
	 * @Description: 更新录入表实体
	 * @date: 2014-1-14
	 * @return void
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	public void updateProjTabEntity(Map<String, String> map) throws ServiceException;


	/**
	 * 删除录入表
	 * @Title: deleteProjTabEntityById
	 * @Description: 删除录入表
	 * @date: 2014-1-14
	 * @return void
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	public void deleteProjTabEntityById(String id) throws ServiceException;


	/**
	 * 报表设置表保存方法
	 * @Title: saveProjTabEntity
	 * @Description: 报表设置表保存
	 * @date: 2014-1-14
	 * @return void
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	 String saveSetREC(Table table, String objectid, String tableid,
                       String showcols, String titlewidth, String recid) throws ServiceException;


	/**
	 * 更新报表设置表
	 * @Title: updateSetREC
	 * @Description: 更新报表设置表
	 * @date: 2014-1-14
	 * @return void
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	//public void updateSetREC( Map<String, Object> map ) throws ServiceException;


	/**
	 * 删除报表设置表
	 * @Title: deleteSetRECById
	 * @Description: 删除报表设置表
	 * @date: 2014-1-14
	 * @return void
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	public void deleteSetRECById(String id) throws ServiceException;
	
	/**
	 * 删除明细主表
	 * @param map
	 */
	public void deleteSetREC(Map map) throws ServiceException;


	/**
	 * 保存单页面明细设置
	 * @Title: saveSetRECDetail
	 * @Description: 保存单页面明细设置
	 * @date: 2014-1-14
	 * @return void
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	//public void saveSetRECDetail( Map map ) throws ServiceException;


	/**
	 * 更新单页面明细设置
	 * @Title: updateSetRECDetail
	 * @Description: 更新单页面明细设置
	 * @date: 2014-1-14
	 * @return void
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	//public void updateSetRECDetail( Map map) throws ServiceException;


	/**
	 * 删除单页面明细设置
	 * @Title: deleteSetRECDetailById
	 * @Description: 删除单页面明细设置
	 * @date: 2014-1-14
	 * @return void
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	//public void deleteSetRECDetail( Map map ) throws ServiceException;

	/**
	 * 录入列表定义
	 * @Title: getProjReportListData
	 * @Description: 录入列表定义
	 * @date: 2014-2-20
	 * @return List
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	//public Table getProjReportDefine() throws ServiceException;


	/**
	 * 录入列表数据查询
	 * @Title: getProjReportListData
	 * @Description: map(objectid typeflag)
	 * @date: 2014-2-20
	 * @return List
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	public List getProjReportListData(Map<String, String> map) throws ServiceException;


	/**
	 * 属性列表定义
	 * @Title: getPageColumnDefine
	 * @Description: 单页面属性列表定义
	 * @date: 2014-2-20
	 * @return Grid
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	public Table getPageColumnDefine() throws ServiceException;


	/**
	 * 录入表属性列表数据查询
	 * @Title: getPageColumnListData
	 * @Description: objectid tableid
	 * @date: 2014-2-18
	 * @return List
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	public Object getPageColumnListData(Map<String, String> map) throws ServiceException;


	/**
	 * 生成32位码
	 * @Title: getDBUniqueID
	 * @date: 2014-3-3
	 * @return String
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	public String getDBUniqueID() throws ServiceException;
	
	/**
	 * 查询是否设置主表
	 * @param objectId
	 * @return
	 */
	public int getMainCount(String objectId, String typeflag) throws ServiceException;
	
	/**
	 * 删除主表设置记录
	 * @param Id
	 */
	public void deleteMainTable(String Id) throws ServiceException;
	
	/**
	 * 主表设置列定义
	 * @return
	 */
	//public Table getMainTabDefine();
	
	/**
	 * 保存关系设置信息
	 * @param map
	 */
	public String saveMain(Map<String, Object> map) throws ServiceException;
	
	/**
	 * 获取设置关系信息
	 * @param id
	 * @return
	 */
	public List getMainList(String id) throws ServiceException;
	
	/**
	 * 获取报表明细主表信息
	 * @param map
	 * @return
	 */
	public List<RECPO> getSetRECList(Map<String, Object> map) throws ServiceException;
	
	/**
	 * 获取过程环节数据
	 * @param typeflag
	 * @return
	 */
	public List<Map<String, Object>> getProcessList(String typeflag) throws ServiceException;
	
	/**
	 * 获取报表列表信息（复制）
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getTabList(Map<String, Object> params) throws ServiceException;
	
	/**
	 * 复制过程环节信息
	 * @param objectId
	 * @param typeflag
	 * @param processId
	 * @param List
	 * @return
	 */
	public String saveTabInfo(String OBJECTID, String TYPEFLAG, String PROCESSID, List list)  throws ServiceException;
	
	/**
	 * 删除单页面明细设置 by recID
	 * @param recId
	 */
	public void delDetailByRecId(String RECID) throws ServiceException;
	
	/**
	 * 获取详细信息记录数
	 * @param recId
	 * @return
	 */
	public int getDetailCountByRecId(String RECID) throws ServiceException;
	
	/**
	 * 保存继承自项目类别设置的明细信息(非保留并修改的记录)
	 * @param paramsMap
	 */
	public void savePColumn(String paramsMap) throws ServiceException;
	
	/**
	 * 保存继承自项目类别设置的信息(非保留的记录)
	 * @param objectId
	 * @param list
	 * @throws Exception
	 */
	public void saveTabFormParent(String objectId, List<Map<String, Object>> list) throws ServiceException;
	
	/**
	 * 批量复制报表设置
	 * @param objectId
	 * @param typeflag
	 * @param processId
	 * @param List
	 * @return
	 * @throws Exception
	 */
	public String saveBatchCopy(String OBJECTID, String TYPEFLAG, String PROCESSID, List list)  throws ServiceException;

}

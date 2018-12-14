package commons.setting.input.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.setting.external.po.RECPO;

public interface MainRefMapper extends SuperMapper{

	/**
	 * 录入表实体保存方法
	 * @Title: insertProjTabEntity
	 * @Description: 保存录入表实体
	 * @date: 2014-1-14
	 * @return void
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	public void insertProjTabEntity(Map<String, String> map);


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
	public void updateProjTabEntity(Map<String, String> map);


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
	public void deleteProjTabEntityById(String id);


	/**
	 * 报表设置表保存方法
	 * @Title: insertProjTabEntity
	 * @Description: 报表设置表保存
	 * @date: 2014-1-14
	 * @return void
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	 void insertSetREC(Map<String, Object> map);


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
	public void updateSetREC(Map<String, Object> map);


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
	public void deleteSetRECById(String id);
	
	/**
	 * 删除明细主表
	 * @param map
	 */
	public void deleteSetREC(Map map);


	/**
	 * 保存单页面明细设置
	 * @Title: insertSetRECDetail
	 * @Description: 保存单页面明细设置
	 * @date: 2014-1-14
	 * @return void
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	public void insertSetRECDetail(Map map);
	
	/**
	 * 保存单页面明细设置
	 * @Title: insertSetRECDetail
	 * @Description: 保存单页面明细设置(公组框)
	 * @date: 2014-1-14
	 * @return void
	 * @throws
	 * @author: liangdb
	 * @version: 1.0
	 */
	public void insertSetRECDetailGroup(Map map);
	
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
	public void updateSetRECDetail(Map map);


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
	public void deleteSetRECDetail(Map map);

	/**
	 * 录入列表设置查询
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getProjReportListData(Map<String, String> map);

	/**
	 * 表属性列表设置查询
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getPageColumnListData(Map<String, String> map);
	
	/**
	 * 获取主表设置记录
	 * @param Id
	 * @return
	 */
	public List getMainList(String Id);
	
	/**
	 * 保存主表设置
	 * @param map
	 */
	public void saveMainTable(Map map);
	
	/**
	 * 更新主表设置
	 * @param map
	 */
	public void updateMainTable(Map map);
	
	/**
	 * 删除主表设置记录
	 * @param Id
	 */
	public void deleteMainTable(String Id);
	
	/**
	 * 查询是否设置主表
	 * @param objectId
	 * @return
	 */
	public int getMainCount(@Param(value = "OBJECTID") String objectid, @Param(value = "TYPEFLAG") String typeflag);
	
	/**
	 * 获取报表明细主表信息
	 * @param map
	 * @return
	 */
	public List<RECPO> getSetRECList(Map<String, Object> map);
	
	/**
	 * 获取项目过程环节数据 spf_t_pprojstep 
	 * @return
	 */
	public List<Map<String, Object>> getProjProcess();
	
	/**
	 * 获取专项资金过程环节数据  spf_t_fspfstep
	 * @return
	 */
	public List<Map<String, Object>> getSPFjProcess();
	
	
	/**
	 * 获取主表信息（复制）
	 * @return
	 */
	public List<Map<String, Object>> getTabList(Map<String, Object> params);
	
	/**
	 * 获取明细主表信息（复制）
	 * @return
	 */
	public List<Map<String, Object>> getTabRecList(Map<String, Object> params);
	
	/**
	 * 获取明细信息（复制）
	 * @return
	 */
	public List<Map<String, Object>> getTabDetail(String RECID);
	
	/**
	 * 插入主表信息（复制）
	 */
	public void insertTab(Map<String, Object> params);
	
	/**
	 * 插入明细主表信息（复制）
	 * @param params
	 */
	public void insertRec(Map<String, Object> params);
	
	/**
	 * DICT_T_SETRECDETAI
	 * 删除单页面明细设置 by recID
	 * @param recId
	 */
	public void delDetailByRecId(String RECID);
	 /**
     * 根据tableid从DICT_T_SETREC表中获取recid
     * @param mainTabId
     * @return
     * @throws
     */
    List<String> getRecIDByMainTabId(@Param(value = "tableID") String tableID, @Param(value = "code") String code);
	
	/**
	 * 获取详细信息记录数
	 * @param recId
	 * @return
	 */
	public int getDetailCountByRecId(String RECID);
	
	/**
	 * 获取报表设置主表 by ID
	 * @param guid
	 * @return
	 */
	public List<Map<String, Object>> getProjTabById(String guid);
}

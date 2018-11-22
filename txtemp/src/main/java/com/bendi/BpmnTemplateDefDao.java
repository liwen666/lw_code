package com.bendi;

import java.util.List;
import java.util.Map;

import com.hq.bpmn.common.Mybatis.SuperMapper;
import com.hq.bpmn.processinstance.bean.BpmnTicket;
import com.hq.bpmn.templatedef.bean.BpmnCode;
import com.hq.bpmn.templatedef.bean.BpmnTemplateDef;

/**
 * 流程模版定义数据访问层接口。
 * 
 * @author qinlm
 *
 */
public interface BpmnTemplateDefDao extends SuperMapper{
	
	/** 根据部署Id查询流程绑定表单信息 */
	List<Map<String, String>> queryProcessFormInfo(String depId);
	
	/** 查询所有的流程模版定义 */
	List<BpmnTemplateDef> selectTemplateDef();
	
	/** 根据查询条件查询流程模版定义 */
	List<BpmnTemplateDef> selectTemplateDefByQueryCondition(Map<String, Object> param);
	
	/**
	 * 根据模版分类查询流程模版定义记录。
	 * 
	 * @param category 模版分类。
	 * @return 模版定义集合。
	 */
	List<BpmnTemplateDef> selectTemplateDefByCategory(String category);
	
	/** 根据id查询代码记录 */
	BpmnTemplateDef selectTemplateDefById(String id);
	
	/** 根据代码类型查询代码记录 */
	List<BpmnCode> selectBpmnCodeByType(String codeType);
	
	/** 查询某种类型的流程定义模版总数 */
	int selectTempCountByCategory(String category);
	
	/** 增加一条新的流程模版定义 */
	void insertBpmnTemplateDef(BpmnTemplateDef bpmnTemplateDef);
	
	/** 修改一条新的流程模版定义 */
	int updateBpmnTempDef(BpmnTemplateDef bpmnTemplateDef);
	
	/** 根据id删除流程模版定义 */
	int deleteTempDefById(String id);
	
	/** 修改部署状态 */
	int updateDeployStateById(Map<String, Object> map);
	
	/** 修改版本状态 */
	int updateVersionStateById(BpmnTemplateDef templateDef);
	
	/** 根据分类修改版本状态 */
	int updateVersionStateByCategory(Map<String, Object> map);
	
	/** 根据类型id查询是否有对应的模版定义*/
	List<BpmnTemplateDef> selectCategoryValidateByCategory(String category);

	/**
	 * 获取主键
	 * @return
	 */
	String selectSysId();
	
	 /**
	  * 获取某流程最大版本号
	  */
	int selectMaxVersionByCategory(String category);
	
	 /**
	  * 获取某流程最大版本号
	  */
	List<BpmnTemplateDef> selectTemplateDefByCategoryAndState(Map<String, Object> map);
	
	
	List<BpmnCode> selectCustomButtonInBpmnCodeResult(Map<String, Object> buttonType); 
	
	List<BpmnTemplateDef> queryTemplateDefByCategory(String category);

	List<BpmnCode> selectCustomButtonInBpmnCodeResult();
	/**
	 * 修改部署的流程定义文件
	 * 
	 * @param bpmnTemplateDef
	 * @return
	 */
	int updateBpmnTempDefWithDep(Map<String, Object> param);
	
	BpmnTemplateDef selectTemplateDefByTickAndCategory(BpmnTicket bpmnTicket);
	
	/**
	 * 查询流程Id信息（包含分类，父分类等）
	 * 
	 * @return
	 */
	List<Map<String, Object>> selectProcessIds();
	/**
	 * 根据部署Id查询
	 * @param deploymentId
	 * @return
	 */
	BpmnTemplateDef selectTemplateDefBydeploymentId(String deploymentId);
}
package com.h2;

import java.util.List;



import com.hq.bpmn.common.Mybatis.SuperMapper;
import com.hq.bpmn.templatedef.bean.BpmnTemplateDef;

/**
 * 流程模版定义数据访问层接口。
 * 
 *
 */
public interface BpmnTemplateDefDaoH2 extends SuperMapper {
	
	
	/** 查询所有的流程模版定义 */
	List<BpmnTemplateDef> selectTemplateDef();
	/**
	 * 将数据加入内存数据库
	 */

//	int addBpmnDefTemp(com.client.BpmnTemplateDef bpmnTemplateDef);
	int addBpmnDefTemp1(BpmnTemplateDef bpmnDef);
}
package test.com.hq.bpmn.test.h2;

import java.util.List;

import com.client.BpmnTemplateDef;
import com.h2.BpmnTemplateDefH2;
import com.hq.bpmn.common.Mybatis.SuperMapper;

/**
 * 流程模版定义数据访问层接口。
 * 
 * @author qinlm
 *
 */
public interface BpmnTemplateDefDao extends SuperMapper {
	
	
	/** 查询所有的流程模版定义 */
	List<BpmnTemplateDefH2> selectTemplateDef();
	void addBpmnTemlateDef(BpmnTemplateDef bpmnTemplateDef);
}
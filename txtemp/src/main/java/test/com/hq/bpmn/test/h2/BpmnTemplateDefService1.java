package test.com.hq.bpmn.test.h2;

import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;

import com.hq.bpmn.common.bean.ProcessResult;
import com.hq.bpmn.common.util.TreeJson;
import com.hq.bpmn.exception.BpmnException;
import com.hq.bpmn.processinstance.bean.BpmnCustomButton;
import com.hq.bpmn.templatedef.bean.BpmnCode;
import com.hq.bpmn.templatedef.bean.BpmnIdGroup;
import com.hq.bpmn.templatedef.bean.BpmnIdUser;
import com.hq.bpmn.templatedef.bean.BpmnTask;
import com.hq.bpmn.templatedef.bean.BpmnTempCategory;
import com.hq.bpmn.templatedef.bean.BpmnTemplateDef;
import com.hq.bpmn.unify.bean.BpmnVariable;

public interface BpmnTemplateDefService1 {

	ProcessResult<List<BpmnTemplateDef>> queryTemplateDef();
	
}

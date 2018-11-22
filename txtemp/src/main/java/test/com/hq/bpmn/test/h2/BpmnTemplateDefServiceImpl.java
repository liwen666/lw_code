//package test.com.hq.bpmn.test.h2;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Service;
//
//import com.hq.bpmn.common.bean.PRM;
//import com.hq.bpmn.common.bean.ProcessResult;
//import com.hq.bpmn.templatedef.bean.BpmnTemplateDef;
////@Service
//public class BpmnTemplateDefServiceImpl implements BpmnTemplateDefService1 {
//	Logger logger = Logger.getLogger(BpmnTemplateDefServiceImpl.class);
//	@Resource
//	private BpmnTemplateDefDao bpmnTemplateDefDao;
//	
//
//
//	/**
//	 * 查询所有的流程模版定义。
//	 * 
//	 * @return 流程模版定义集合。
//	 */
//	public ProcessResult<List<com.hq.bpmn.templatedef.bean.BpmnTemplateDef>> queryTemplateDef() {
//		ProcessResult<List<com.hq.bpmn.templatedef.bean.BpmnTemplateDef>> pr = new ProcessResult<List<BpmnTemplateDef>>();
//		List<com.hq.bpmn.templatedef.bean.BpmnTemplateDef> list = bpmnTemplateDefDao.selectTemplateDef();
//		pr.setResult(list);
//		pr.setSuccess(true);
//		return pr;
//	}
//
//	
//}

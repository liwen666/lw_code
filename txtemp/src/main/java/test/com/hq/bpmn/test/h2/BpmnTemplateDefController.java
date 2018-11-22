package test.com.hq.bpmn.test.h2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSONArray;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.util.io.InputStreamSource;
import org.activiti.engine.impl.util.io.StreamSource;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.hq.bpmn.common.bean.PRM;
import com.hq.bpmn.common.bean.ProcessResult;
import com.hq.bpmn.common.command.ProcessImageDiagramCmd;
import com.hq.bpmn.common.util.HqBpmnXMLConverter;
import com.hq.bpmn.common.util.JsonUtil;
import com.hq.bpmn.common.util.TreeJson;
import com.hq.bpmn.exception.BpmnException;
import com.hq.bpmn.templatedef.bean.BpmnCode;
import com.hq.bpmn.templatedef.bean.BpmnField;
import com.hq.bpmn.templatedef.bean.BpmnIdGroup;
import com.hq.bpmn.templatedef.bean.BpmnIdUser;
import com.hq.bpmn.templatedef.bean.BpmnTable;
import com.hq.bpmn.templatedef.bean.BpmnTempCategory;
import com.hq.bpmn.templatedef.bean.BpmnTemplateDef;
import com.hq.bpmn.templatedef.service.BpmnTableService;
import com.hq.bpmn.templatedef.service.BpmnTempCategoryService;
import com.hq.bpmn.templatedef.service.BpmnTemplateDefService;
import com.hq.bpmn.unify.bean.BpmnVariable;

/**
 * 流程模版定义控制器。
 * 
 * @author
 * 
 */
//@Controller
@RequestMapping("/**/templateDef")
public class BpmnTemplateDefController {
		@Resource
	BpmnTemplateDefService1 bpmnTemplateDefService;
	
	public ProcessResult<List<BpmnTemplateDef>> queryTemplateDef() {
		ProcessResult<List<BpmnTemplateDef>> list = bpmnTemplateDefService.queryTemplateDef();
		return list;
	}
}

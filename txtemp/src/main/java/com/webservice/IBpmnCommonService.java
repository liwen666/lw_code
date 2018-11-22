package com.webservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.exception.po.ExceptionInfoPO;
import com.h2.BpmnTemplateDef;

@WebService
public interface IBpmnCommonService {

	
	public String initBpmnData(@WebParam(name = "userId") String userId);
//			@WebParam(name = "currentDocId") String currentDocId,
//			@WebParam(name = "busiTypeId") String busiTypeId,
//			@WebParam(name = "currentNodeId") String currentNodeId,
//			@WebParam(name = "processDefId") String processDefId,
//			@WebParam(name = "currentUserCode") String currentUserCode,
//			@WebParam(name = "lstTragetDocParams") String strTragetDocParams,
//			@WebParam(name = "parentNodeId") String parentNodeId,
//			@WebParam(name = "extendParam") String strExtendParam);
	public List<BpmnTemplateDef> initBpmnTemplateDef();
	public List<ExceptionInfoPO> pmnTemplateDef(@WebParam(name = "a") List<String> a);
}

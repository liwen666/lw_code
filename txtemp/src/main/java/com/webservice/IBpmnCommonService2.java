package com.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IBpmnCommonService2 {

	
	public String initBpmnData(@WebParam(name = "userId") String userId);
//			@WebParam(name = "currentDocId") String currentDocId,
//			@WebParam(name = "busiTypeId") String busiTypeId,
//			@WebParam(name = "currentNodeId") String currentNodeId,
//			@WebParam(name = "processDefId") String processDefId,
//			@WebParam(name = "currentUserCode") String currentUserCode,
//			@WebParam(name = "lstTragetDocParams") String strTragetDocParams,
//			@WebParam(name = "parentNodeId") String parentNodeId,
//			@WebParam(name = "extendParam") String strExtendParam);
	
}

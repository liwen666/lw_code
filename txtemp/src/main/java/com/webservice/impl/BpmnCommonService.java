package com.webservice.impl;

import java.util.List;

import javax.jws.WebService;

import com.exception.po.ExceptionInfoPO;
import com.h2.BpmnTemplateDef;
import com.webservice.IBpmnCommonService;

@WebService(endpointInterface = "com.webservice.IBpmnCommonService", serviceName = "webservice/common/bpmn")
public class BpmnCommonService implements IBpmnCommonService {

	@Override
	public String initBpmnData(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmnTemplateDef> initBpmnTemplateDef() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExceptionInfoPO> pmnTemplateDef(List<String> a) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
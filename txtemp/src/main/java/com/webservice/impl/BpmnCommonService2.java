package com.webservice.impl;

import javax.jws.WebService;

import com.webservice.IBpmnCommonService;
import com.webservice.IBpmnCommonService2;

@WebService(endpointInterface = "com.webservice.IBpmnCommonService2", serviceName = "webservice/common/bpmn2")
public class BpmnCommonService2 implements IBpmnCommonService2 {

	@Override
	public String initBpmnData(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
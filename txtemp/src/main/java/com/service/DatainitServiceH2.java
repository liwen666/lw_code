package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bendi.BpmnTemplateDefDao;
import com.h2.BpmnTemplateDefDaoH2;
import com.h2.BpmnTemplateDefH2;
import com.hq.bpmn.templatedef.bean.BpmnTemplateDef;

@Service
public class DatainitServiceH2 {
	@Autowired
	private BpmnTemplateDefDaoH2 bpmnDefDaoH2;
	@Autowired
	private BpmnTemplateDefDao bpmnDefDao;
	
	
	public int addBpmnDefTempH2(BpmnTemplateDef bpmnDef){
		System.out.println("调用H2service服务");
		return bpmnDefDaoH2.addBpmnDefTemp1(bpmnDef);
	}


	public List<BpmnTemplateDef> selectTemplateDefH2() {
		// TODO Auto-generated method stub
		return bpmnDefDaoH2.selectTemplateDef();
	}
	public void addBpmnDefTempBendi(BpmnTemplateDef bpmnDef){
		System.out.println("调用H2service服务");
	 bpmnDefDao.insertBpmnTemplateDef(bpmnDef);
	}
	
	
	public List<BpmnTemplateDef> selectTemplateDefBendi() {
		// TODO Auto-generated method stub
		return bpmnDefDao.selectTemplateDef();
	}
	
}

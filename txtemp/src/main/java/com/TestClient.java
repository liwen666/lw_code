//package com;
//
//import client.IEfmisCommonService;
//import client.Webservice_002fCommon_002fEfmis;
//import clienttest.IBpmnCommonService;
//import clienttest.Webservice_002fCommon_002fBpmn;
//
//
//
//public class TestClient {
//
//	public static void main(String[] args) {
//		// 创建服务视图
//		Webservice_002fCommon_002fBpmn efmis = new Webservice_002fCommon_002fBpmn();
//		// 获取服务实现类
//		   IBpmnCommonService bpmnCommonServicePort = efmis.getBpmnCommonServicePort();
//		// 调用查询方法
//		   String initBpmnData = bpmnCommonServicePort.initBpmnData("01xabc");
//		
//		System.out.println(initBpmnData);
//	}
//	
//}
//             
package com.wsdl;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import com.webservice.impl.BpmnCommonService;

@WebService
public class HelloService {

public String sayHello(String name){
return "Hello "+name;
}

/**
 * @param args
 */
public static void main(String[] args) {

Endpoint.publish("http://127.0.0.1:8081/hqbpmn/webservice/common/service/bpmn", new BpmnCommonService());
System.out.println("发布成功");
}

}
package com.temp.code.controller.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class BusiLogSchedulService {
    private static final Logger logger = LoggerFactory.getLogger(BusiLogSchedulService.class);

    private RestTemplate restTemplate;
    void sychTempAndCategory() {
    String url ="/";
        //调服务端删除模板和模板类型
        restTemplate.delete(url + "/hqbpmn/bpmnAction/template/category/" + 1);
        restTemplate.delete(url + "/hqbpmn/bpmnAction/template/" + 1);
        //服务端插入模板和类型
        ResponseEntity<Integer> integerResponseEntity = restTemplate.postForEntity(url + "/hqbpmn/bpmnAction/template", new Object(), Integer.class);
        HttpStatus statusCode = integerResponseEntity.getStatusCode();
        System.out.println("HttpStatus = " + statusCode);
        ResponseEntity<Integer> categoryResponseEntity = restTemplate.postForEntity(url + "/hqbpmn/bpmnAction/template/category",  new Object(), Integer.class);
        Integer category = categoryResponseEntity.getBody();

    }

}

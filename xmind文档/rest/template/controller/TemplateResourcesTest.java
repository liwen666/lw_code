package com.hq.bpmn.rest.template.controller;

import com.hq.bpmn.templatedef.bean.BpmnTempCategory;
import com.hq.bpmn.templatedef.bean.BpmnTemplateDef;
import com.hq.bpmn.templatedef.dao.BpmnTemplateDefDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author Administrator
 * @date 2018/11/23 11:25
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-wf.xml")*/
public class TemplateResourcesTest {

    private String targetURL = "http://localhost:8080/hqbpmn/bpmnAction/template";
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    // bpmnTemplateDefDao.selectTemplateDefOnly()

    @Test
    public void testSaveTemplateCategory() {

        Map<String, Object> map = new HashMap<>();

        List<BpmnTempCategory> list = new ArrayList<BpmnTempCategory>();
        BpmnTempCategory t1 = new BpmnTempCategory();

        t1.setId(UUID.randomUUID().toString().replace("-", ""));
        t1.setName("name1");
        t1.setCategory("category1");
        t1.setpId("pid1");
        t1.setDescription("Description1");
        t1.setPdKey("PdKey1");
        t1.setOrderId(10);
        t1.setDeptId("DeptId1");
        t1.setYear("year1");
        t1.setProvince("Province1");
        BpmnTempCategory t2 = new BpmnTempCategory();

        t2.setId(UUID.randomUUID().toString().replace("-", ""));
        t2.setName("name2");
        t2.setCategory("category2");
        t2.setpId("pid2");
        t2.setDescription("Description2");
        t2.setPdKey("PdKey2");
        t2.setOrderId(10);
        t2.setDeptId("DeptId2");
        t2.setYear("Year2");
        t2.setProvince("Province2");
        list.add(t1);
        list.add(t2);
        map.put("appId", "hqbpmn");
        map.put("bpmnTypeList", list);


        //restTemplate.delete(targetURL + "/category/"+"zyzf_gk");

       /* ResponseEntity<Integer> integerResponseEntity = restTemplate.postForEntity(targetURL + "/category", map, Integer.class);
        Integer body = integerResponseEntity.getBody();
        System.out.println("body = " + body);

        System.out.println("body = " + "ooo");*/


        Map<String, Object> map1 = new HashMap<>();
        BpmnTemplateDef b1 = new BpmnTemplateDef();
        b1.setId(UUID.randomUUID().toString().replace("-", ""));
        b1.setCategory("lwcs001");
        List<BpmnTemplateDef> l1 = new ArrayList<BpmnTemplateDef>();
        l1.add(b1);
        map1.put("appId", "hqbpmn");
        map1.put("bpmnTemplateDefList", l1);

        Integer integer = restTemplate.postForObject(targetURL, map1, Integer.class);

        System.out.println("body = " + integer);

        System.out.println("body = " + "ooo");
    }


}
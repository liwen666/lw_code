package com.temp.code.controller.rest;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2018/11/23 10:55
 * 通过rest  传递过来的object 无法强转成指定的对象进行取值  只能用指定对象进行接收进行解决
 */
@RestController
@RequestMapping("/**/template")
public class TemplateResources {




    @RequestMapping(method = RequestMethod.POST)
    public int saveTemplate(@RequestBody Map<String, Object> map) {
        String appId = (String) map.get("appId");
        List<Object> tempData = (List<Object>) map.get("bpmnTemplateDefList");
        int i = 0;
//        for(BpmnTemplateDef b:tempData){
        Object bpmnTemplateDef = tempData.get(0);
//        return Object.(map);
        return 1;
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public int saveTemplateCategory(@RequestBody Map<String, Object> map) {
//        return bpmnTempCategoryService.saveBpmnTemplateCategory(map);
        return 1;
    }

    @RequestMapping(value = "/{appId}", method = RequestMethod.DELETE)
    public int removeTemplateByAppId(@PathVariable("appId") String appId) {
//        return bpmnTemplateDefService.removeBpmnTemplateByAppId(appId);
        return 1;
    }

    @RequestMapping(value = "/category/{appId}", method = RequestMethod.DELETE)
    public int removeTemplateCategoryByAppId(@PathVariable("appId") String appId) {
//        return bpmnTempCategoryService.removeBpmnTemplateCategoryByAppId(appId);
        return 1;
    }
}

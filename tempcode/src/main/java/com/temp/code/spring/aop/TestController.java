package com.temp.code.spring.aop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/test")
public class TestController extends BaseController {


    @RequestMapping(value="", method= RequestMethod.POST)
    public ResponseEntity postTest(@RequestParam(name = "accessToken") String accessToken) {
        ResponseEntity responseEntity = new ResponseEntity(null);

        return responseEntity;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity deleteTest(@RequestParam(name = "accessToken") String accessToken) {
        ResponseEntity responseEntity = new ResponseEntity(null);

        return responseEntity;
    }

    /**
    * 带了IgnorePermission注解，不进行权限检查。
    */
    @IgnorePermission
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity getTestList(@RequestParam(name = "accessToken") String accessToken) {

        ResponseEntity responseEntity = new ResponseEntity(null);

        return responseEntity;
    }
}

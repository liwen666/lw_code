package com.temp.code.spring.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class PermissionAspect {

    private Logger logger = LoggerFactory.getLogger(PermissionAspect.class);

    private static final String DEFAULT_TOKEN_NAME = "accessToken";

    private TokenManager tokenManager;
    private String tokenName;

    private boolean processing = true;

    @Autowired
    private BasicService BasicService;

    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public void setTokenName(String tokenName) {
        if (StringUtils.isEmpty(tokenName)) {
            tokenName = DEFAULT_TOKEN_NAME;
        }
        this.tokenName = tokenName;
    }

    //ProceedingJoinPoint只有around的方式才可用
    //JoinPoint则around、before和after均可用
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        processing = true;
        ResponseEntity responseEntity = new ResponseEntity();

        // 从切点上获取目标方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        // 若目标方法忽略权限检查，则直接调用目标方法
        if (method.isAnnotationPresent(IgnorePermission.class)) {
            return joinPoint.proceed();
        }

        // 检查 该用户是否已经开通
//        System.out.println("******************* PermissionAspect start ************************");
        logger.info("******************* PermissionAspect start ************************");

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // 从 request header 中获取当前 token
        String token = StringUtils.isBlank(request.getHeader(tokenName)) ? request.getParameter("accessToken") : request.getHeader(tokenName);

        String userId = tokenManager.getUserId(token);

        TestBasic TestBasic = BasicService.findByUserId(userId);

        try {

            if (null == TestBasic) {
                String message = String.format("Test basic userId [%s] is not exist.", userId);
                responseEntity.failure(ResponseConstant.CODE_000, "尚未申请开通功能。");
                processing = false;
                throw new Exception(message);
            }

            if (TestConstant.Test_BASIC_IS_PASS_PASSED != TestBasic.getIsPass()) {
                String message = String.format("Test basic userId [%s] has no permission.", userId);
                String tips = "不具备权限功能。";
                if (TestConstant.Test_BASIC_IS_PASS_PROCESSING == TestBasic.getIsPass()) {
                    tips = "权限正在审核中。";
                } else if (TestConstant.Test_BASIC_IS_PASS_DENY == TestBasic.getIsPass()) {
                    tips = "不具备权限。";
                }
                responseEntity.failure(ResponseConstant.CODE_000, tips);
                processing = false;
                throw new Exception(message);
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
           System.out.println("******************* PermissionAspect end ************************");
            logger.info("******************* PermissionAspect end ************************");
            if (processing) {
                return joinPoint.proceed();//如果具备权限则执行相应的方法
            } 
            else {
               return responseEntity;
//                //如果不具备权限返回相应的json数据
           }
        }
    }
}
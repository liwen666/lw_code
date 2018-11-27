package com.temp.code.exception;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.temp.code.sys.interfac.IController;


/**
* @author lw
* @2016年12月4日上午11:43:31
* @return AppBaseController.java
*/
@Component
@RequestMapping(value={"/",""})
public class AppBaseController implements IController {
	/**
	 * 多路径使用以下方式
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value={"index","/","basepage"})
		public String index(Model model) throws Exception{
//		测试跳转错误页面并携带错误
//		Exception ex=null;
//			try {
//				int a=1/0;
//			} catch (Exception e) {
//				String stackTrace = ExceptionUtil.getStackTrace(e);
//				ex=new Exception(stackTrace);
//			}
//			throw ex;
			return"index";
		}
}

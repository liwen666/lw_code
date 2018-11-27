package com.templete.code.util;

import java.awt.List;

import org.springframework.util.StringUtils;

/**
 * @author  作者 :lw E-mail:
 * @date 创建时间：2016年11月28日 上午9:38:21
 * @version 1.0 * @parameter 
 * @since 
 * @return 
 */
public abstract class ContextUtil {
	public static String  getServId(){
		
		return ServerNameContext.getInstancce().getServerId();
	}
	public static long  getServIdLong(){
		
		return ServerNameContext.getInstancce().getServerIdLong();
	}
	public static String  getServName(){
		
		return ServerNameContext.getInstancce().getServerName();
	}
	public static String getChannel(IDataContext dataContext){
		return dataContext.getMessage(FappPath.SRCCHANL_PUB_PATH)
	}
	public static String getValTrim(IDataContext dataContext,String xmlPath){
		String value=dataContext.getMessage(xmlPath);
		if(value==null){
			return null;
		}
		return value.trim();
	}
	public static boolean validateContextPath(IDataContext dataContext,List<String> xmlPathList){
		for (String xmlPath:xmlPathList) {
			if(!validateContextPath(dataContext,xmlPath)){
				return false;
			}
			
		}
		return true;
	}
	public static boolean validateContextPath(IDataContext dataContext,String xmlPath){
		String value=dataContext.getMessage(xmlPath);
			if(StringUtils.isEmpty(value)){
				return false;
			}
		return true;
	}
}

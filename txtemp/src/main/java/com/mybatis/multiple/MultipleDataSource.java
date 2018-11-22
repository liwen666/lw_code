package com.mybatis.multiple;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;  
 
public class MultipleDataSource extends AbstractRoutingDataSource {  
  
    private static ThreadLocal<String> threadLocalDatasource=new ThreadLocal<String>(){  
        @Override  
        protected String initialValue() {  
            return null;  
        }  
    };  
  
    public static void setThreadLocalDatasource(String dsName){  
        threadLocalDatasource.set(dsName);  
    }  
  
  
    @Override  
    protected Object determineCurrentLookupKey() {  
        return threadLocalDatasource.get();  
    }  
}  

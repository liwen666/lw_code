package commons.log.service.impl;

import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.BeansException;

import com.tjhq.commons.log.service.ISqlLogService;
import com.tjhq.commons.utils.ApplicationContextUtil;
import com.tjhq.commons.utils.SystemProperty;

@SuppressWarnings("unchecked")
public class SqlLogService implements ISqlLogService {
    
    static SqlLogService sqlLogService = null;
    Class logServiceClass = null;
    Object logService = null;
    Class cacheServiceClass = null;
    Object cacheService = null;
    Class logDTOClass = null;
    ExecutorService executor = null;
    
    private SqlLogService() {
        logServiceClass = getClass("gov.mof.fasp2.fw.log.logsql.service.ISqlLogService");
        logService = getLogService("fasp2.sql.log");
        cacheServiceClass = getClass("com.longtu.framework.cache.service.ICacheService");
        cacheService = getLogService("fasp2.log.sql.users");
        logDTOClass = getClass("gov.mof.fasp2.fw.log.logsql.dto.SqlLogDTO");
        executor = Executors.newFixedThreadPool(5);
    }
    
    public static synchronized SqlLogService getInstance() {
        if (sqlLogService == null) {
            sqlLogService = new SqlLogService();
        }
        return sqlLogService;
    }
    
    

    @Override
    public void log(String sql, Object params, long usedTime, List resultset) {
        if (logDTOClass != null && logServiceClass != null && logService != null && SecureUtil.getCurrentUser() != null) {
            Object logDTO = getNewInstance(logDTOClass);
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            callClassMethod(logDTOClass, logDTO, "setAppid", String.class, SystemProperty.getSystemAppID().toLowerCase());
            callClassMethod(logDTOClass, logDTO, "setSql", String.class, sql);
            callClassMethod(logDTOClass, logDTO, "setCreatetime", String.class, df.format(System.currentTimeMillis()));
            callClassMethod(logDTOClass, logDTO, "setOperator", String.class, SecureUtil.getCurrentUser().getGuid());
            callClassMethod(logDTOClass, logDTO, "setConsumetime", Long.class, usedTime);
            callClassMethod(logDTOClass, logDTO, "setResultset", List.class, resultset);
            callClassMethod(logDTOClass, logDTO, "setExcutecount", int.class, resultset == null ? 0 : resultset.size());
            List<Object> paramList = new ArrayList<Object>();
            if (params instanceof List) {
                paramList = (List)params;
            } else if (params instanceof Map) {
                Map<Object, Object> p = (Map)params;
                for (Map.Entry entry : p.entrySet()) {
                    paramList.add(entry.getValue());
                }
            } else {
                paramList.add(params);
            }
            
            executor.execute(new CallRemoteMethod(logDTO, SecureUtil.getCurrentUser().getGuid()));
        }

    }
    
    class CallRemoteMethod implements Runnable {
        
        Object logDTO;
        String userID;
        
        public CallRemoteMethod(Object logDTO, String userID){
            this.logDTO = logDTO;
            this.userID = userID;
        }

        @Override
        public void run() {
            //System.out.println(Thread.currentThread().getName() + ">>>>>>>>>>>>>>>start save log !");
            callMethod(getMethod(cacheServiceClass, "put", String.class, Object.class), cacheService, userID, "");
            callClassMethod(logServiceClass, logService, "save", logDTOClass, logDTO);
            //System.out.println(Thread.currentThread().getName() + ">>>>>>>>>>>>>>>end save log !");
        }
        
    }
    
    
    private Object getLogService(String beanID) {
        try {
            return ApplicationContextUtil.getContext().getBean(beanID);
        } catch (BeansException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    private Class getClass(String className) {
        try {
           return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        
        return null;
    }
    
    private Object getNewInstance(Class cls) {
        if (cls != null) {
            try {
                return cls.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        
        return null;
    }
    
    private void callClassMethod(Class cls, Object instance, String methodName, Class paramClassType,  Object value) {
        callMethod(getMethod(cls, methodName, paramClassType),instance,  value);
    }
    
    private Method getMethod(Class cls, String methodName, Class... paramClassType) {
        try {
            return cls.getMethod(methodName, paramClassType);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private void callMethod(Method method, Object instance,Object... value) {
        try {
            method.invoke(instance, value);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}

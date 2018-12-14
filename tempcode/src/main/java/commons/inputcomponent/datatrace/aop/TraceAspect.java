package commons.inputcomponent.datatrace.aop;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import com.tjhq.commons.inputcomponent.datatrace.service.IDataTraceService;
import com.tjhq.commons.inputcomponent.po.Table;

@Service
@Aspect
public class TraceAspect {
	
	@Resource
	private IDataTraceService dataTraceService;
	
	public IDataTraceService getDataTraceService() {
		return dataTraceService;
	}

	public void setDataTraceService(IDataTraceService dataTraceService) {
		this.dataTraceService = dataTraceService;
	}

	@Pointcut("execution(* com.tjhq.exp..service..save*(..))")  
	//@Pointcut("@within(org.springframework.transaction.annotation.Transactional)")
    public void dataChange(){}
	
	/**
	 * 前执行方法，处理数据日志
	 * 因为其中包括删除的数据明细，在保存正式数据前处理数据之前，能够查询到删除之前库中数据
	 * @param joinPoint
	 */
	@Before(value = "dataChange()")
	public void dataChangeBefore(JoinPoint joinPoint) {
		try {
			Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
			if(method.getAnnotation(DataTraceDesc.class) == null){
				return;
			}
			String operation = "";
			if(method.getAnnotation(DataTraceDesc.class).value().equals("")){
				operation = method.getClass().getName() + "." + method.getName();
			}else{
				operation = method.getAnnotation(DataTraceDesc.class).value();
			}
			
			Table table = null;
			for (Object param : joinPoint.getArgs()) {
				if (param instanceof Table) {
					table = (Table)param;
					continue;
				}
			}
			if (table == null) {
				return;
			}
		
			getDataTraceService().handleLogData(table, operation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 后执行方法，保存数据日志，保存日志到日志库中
	 * 因为保存正式数据时可能发生异常回滚，所以在注解方法执行完毕后执行此方法提交数据日志。
	 * @param JoinPoint
	 */
	@After(value = "dataChange()")
	public void dataChangeAfter(JoinPoint joinPoint) {
		try {
			Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
			if(method.getAnnotation(DataTraceDesc.class) == null){
				return;
			}
			String operation = "";
			if(method.getAnnotation(DataTraceDesc.class).value().equals("")){
				operation = method.getClass().getName() + "." + method.getName();
			}else{
				operation = method.getAnnotation(DataTraceDesc.class).value();
			}
			Table table = null;
			for (Object param : joinPoint.getArgs()) {
				if (param instanceof Table) {
					table = (Table)param;
					continue;
				}
			}
			if (table == null) {
				return;
			}
			getDataTraceService().saveLogData(table, operation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

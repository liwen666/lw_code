package commons.setting.manage.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.longtu.demo.logger.Logger;
import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.setting.manage.service.IMviewRefreshService;
/**
 * 刷物化视图定时任务 
 * 2015-9-10 上午11:53:21
 * MviewRefreshTaskPlan.java
 */
@Component
public class MviewRefreshTaskPlan {
	private Logger logger  = Logger.getLogger(MviewRefreshTaskPlan.class);
	@Resource 
	private IDataCacheService dataCacheService;
	@Resource 
	private IMviewRefreshService mviewRefreshService;
	
	public void execute(){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Object isRunObj = dataCacheService.get("isRun");
		boolean isRun = false;
		if(isRunObj!=null){
			isRun = ((Boolean)isRunObj).booleanValue();//任务是否正在执行标记 ：false--未执行； true--正在执行； 默认未执行
		}
        if (isRun) {
    		logger.info("刷物化视图定时任务,前一次未执行完,跳过本次任务 :"+sdf.format(d));
            // 任务正在执行，跳过本次执行
            return;
        }
        isRun = true;
        dataCacheService.put("isRun", isRun);
		logger.info("刷物化视图定时任务 :"+sdf.format(d));
		mviewRefreshService.execute();
        isRun = false;
        dataCacheService.put("isRun", isRun);
	}
}

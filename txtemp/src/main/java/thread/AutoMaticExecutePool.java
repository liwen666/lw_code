package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//import javax.persistence.Query;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

//import net.bytebuddy.implementation.bind.annotation.BindingPriority;

@Configuration
@EnableAsync
public class AutoMaticExecutePool {

	private ThreadPoolExecutor threadPoolExecutor;
	  
    private int corePoolSize = 10;//线程池维护线程的最少数量

    private int maxPoolSize = 30;//线程池维护线程的最大数量

    private int queueCapacity = 8; //缓存队列

    private int keepAlive = 60;//允许的空闲时间  


//    @Bean("myExecutor")
    public ThreadPoolExecutor myExecutor() {
    	 BlockingQueue<AutoTheard> bq = new ArrayBlockingQueue<AutoTheard>(10);
    	threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
    			maxPoolSize, keepAlive, TimeUnit.SECONDS,
                new LinkedBlockingDeque());
        return threadPoolExecutor;
    }
    
//    @Bean("threadMap")
    public  Map<String,AutoTheard> threadMap(){
    	
    	
		return new HashMap<String,AutoTheard>();
   	
    };
}



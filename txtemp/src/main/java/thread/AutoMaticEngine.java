package thread;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AutoMaticEngine {

  @Autowired
  private ThreadPoolExecutor myExecutor;
  @Autowired
  private Map<String,AutoTheard> threadMap;
  
  public static void main(String[] args) throws Exception {
	
	new AutoMaticEngine().engine(new AutoTheard());
}
   public void engine(AutoTheard autoTheard) throws InterruptedException{
	   ThreadPoolExecutor service=(ThreadPoolExecutor) Executors.newCachedThreadPool();
			   service.submit(autoTheard);
			   System.out.println("-------");
	  /**
	   * 3秒后让张三线程停止
	   */
			   
	   Thread.sleep(3000);
	   autoTheard.setWhileTrue(true);
	   Thread t = ThreadMap.threadMap.get("张三");
//	   t.wait();
//	   Thread.sleep(3000);
	   /**
	    *唤醒线程
	    */
	  
//	   threadMap.put("张三", autoTheard);
//      if(myExecutor.getQueue().size()==0){
//    	  myExecutor.shutdown();
//      }
	   
   }
	public void suspend() throws InterruptedException{
		AutoTheard autoTheard =threadMap.get("张三");
		autoTheard.wait();
	}
}

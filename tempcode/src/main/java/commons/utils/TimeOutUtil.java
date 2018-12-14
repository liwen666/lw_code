package commons.utils;

/**
 * <p>Title: 超时处理类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 北京太极华青信息系统有限公司</p>
 * @author 
 * @version 1.0
 */

import org.apache.log4j.Logger;


public class TimeOutUtil extends Thread
{
 private static Logger log = Logger.getLogger(TimeOutUtil.class);
	private int time;
	public TimeOutUtil(int time)
	{
		this.time = time;
		// TODO 自动生成构造函数存根
	}

	/* （非 Javadoc）
	 * @see java.lang.Runnable#run()
	 */
	public void run()
	{

		try
		{
			Thread.sleep(time);
		}
		catch (InterruptedException e)
		{
			// TODO 自动生成 catch 块
			log.info("TimeOutUtil->run():" + e.toString());
		}

	}

}

package commons.cache.context;

import org.apache.log4j.Logger;

public class CacheContext {
	
	Logger logger = Logger.getLogger(CacheContext.class);
	
	private static CacheContext context = new CacheContext();
	private boolean isEnable = true;
	
	private CacheContext() {
	}
	
	public static CacheContext getInstance() {
		return context;
	}
	
	public void disable() {
		logger.info("System cache service disable .");
		this.isEnable = false;
	}
	
	public void enable() {
		logger.info("System cache service enable .");
		this.isEnable = true;
	}
	
	public boolean isEnable() {
		return this.isEnable;
	}
}

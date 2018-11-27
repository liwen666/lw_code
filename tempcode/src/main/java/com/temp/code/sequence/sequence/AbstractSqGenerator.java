package com.templete.code.sequence;

import com.templete.code.util.ContextUtil;

/**
 * @author  作者 :lw E-mail:
 * @date 创建时间：2016年11月28日 上午9:16:55
 * @version 1.0 * @parameter 
 * @since 
 * @return 
 */
public class AbstractSqGenerator {
	protected static final long SERVID_BITS=4L;
	protected static final long MAX_SERVID=-1L ^ (-1L << SERVID_BITS);
	protected static final long SEQUENCE_BITS=8L;
	protected static final long SEQUENCE_MAX=-1L ^ (-1L << SERVID_BITS);
	
	protected static final long SERVID_SHIFT=SEQUENCE_BITS;
	protected static final long TIMESTAMP_SHIFT=SEQUENCE_BITS+SERVID_BITS;

	protected final long servId;

	public AbstractSqGenerator() {
		super();
		long servIdTmp = ContextUtil.getServIdLong();
		/**
		 * if(servIdTmp>MAX_SERVID||servIdTmp<0){
		 * throw new IllegalArgumentException("servId 超出范围")
		 * }
		 */
		this.servId = servIdTmp;
	}
	private long getSysTimeMills(){
		return System.currentTimeMillis();
	}
	
	protected long getNextMillis(long lastTimstamp){
		long timeStamp=getSysTimeMills();
		while (timeStamp<=lastTimstamp) {
			timeStamp=getSysTimeMills();
			
		}
		return timeStamp;
	}
	protected long getNextId(long timeStamp){
		return 0L;
	}
}

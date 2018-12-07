package com.temp.code.sequence;


/**
 * @author  ���� :lw E-mail:
 * @date ����ʱ�䣺2016��11��28�� ����9:16:55
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
//		long servIdTmp = ContextUtil.getServIdLong();
		/**
		 * if(servIdTmp>MAX_SERVID||servIdTmp<0){
		 * throw new IllegalArgumentException("servId ������Χ")
		 * }
		 */
//		this.servId = servIdTmp;
		this.servId = 1;
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

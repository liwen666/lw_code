package com.temp.code.sequence;

import org.junit.Test;

public final class CurtIDMaker {
	private final static long twepoch=112341541245L;
	//机器标示位数
	private final static long workerIdBits=5L;
	//数据中心标示位数
	private final static long dataCenterIdBits=5L;
	//机器ID最大值
	private final static long maxWorkerId=-1L ^ (-1L << workerIdBits);
	//数据中心ID最大值
	private final static long maxDatacenterId=-1L ^ (-1L << dataCenterIdBits);
	//毫秒内自增
	private final static long sequenceBits=12L;
	//
	private final static long sequenceMask=-1L ^ (-1L << sequenceBits);
	//机器中心偏左移12位
	private final static long workerIdShift=sequenceBits;
	//数据中心ID左移17位
	private final static long datacenterIdShift=sequenceBits+workerIdBits;
	//时间毫秒左移22位
	private final static long timestampLeftShift= sequenceBits+workerIdBits+dataCenterIdBits;
	
	private static long laseTimestamp= -1L;
	 
	private long sequence = 0L;
	
	private final long makerId;
	private final long datacenterId;
	public CurtIDMaker(long makerId ,long datacenterId) throws Exception{
		if(makerId>maxWorkerId|| makerId<0){
			throw new Exception("workerId can't be greter than %d or less than 0");
		}
		if(datacenterId>maxDatacenterId|| datacenterId<0){
			throw new Exception();
		}
		this.makerId=makerId;
		this.datacenterId=datacenterId;
		
	}
	
	public synchronized long nextId(){
		long timestamp= timeGen();
		if (timestamp < laseTimestamp){
			try {
				throw new Exception("");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(laseTimestamp == timestamp){
			//当前毫秒+1
			sequence = (sequence+1) & sequenceMask;
			if(sequence == 0){
				//当前毫秒计数满，等待下一秒
				timestamp = tilNextMillis(laseTimestamp);
			}
		}else{
			sequence=0;
		}
		laseTimestamp= timestamp;
		//ID偏移组合生成最终的ID并返回
		long nextId = ((timestamp - twepoch) << timestampLeftShift)
						| (datacenterId <<datacenterIdShift)
						| (makerId << workerIdShift)
						| sequence;
		return nextId;
	}
		
	private long tilNextMillis(final long lastTimestamp){
	long timestamp= this.timeGen();
	while (timestamp <=lastTimestamp){
		timestamp= this.timeGen();}
		return timestamp;
	}
	
	private long timeGen() {
		return System.currentTimeMillis();
	}
	public static void main(String[] args) {
		try {
			CurtIDMaker cr = new CurtIDMaker(2, 2);
			long nextId = cr.nextId();
			System.out.println(nextId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

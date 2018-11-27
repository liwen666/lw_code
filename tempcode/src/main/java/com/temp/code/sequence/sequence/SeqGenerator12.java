package com.templete.code.sequence;

/**
 * @author 作者 :lw E-mail:
 * @date 创建时间：2016年11月28日 下午2:54:58
 * @version 1.0 * @parameter
 * @since
 * @return
 */
public class SeqGenerator12 extends AbstractSqGenerator {
	private static final long TIMESTAMP_BITS = 39L;
	private static final long TIMESTAMPMASK = -1 ^ (-1L << TIMESTAMP_BITS);

	private static final byte[] LOCK = new byte[0];

	private long sequence = 0L;
	private long lastTimeStamp = -1L;

	private SeqGenerator12() {
		super();
	}

	private static class SeqGenerator12Holder {
		public static final SeqGenerator12 instance = new SeqGenerator12();
	}

	public static SeqGenerator12 getInstance() {
		return SeqGenerator12Holder.instance;
	}

	@Override
	protected long getNextId(long timeStamp) {
		synchronized (LOCK) {
			if (this.lastTimeStamp == timeStamp) {
				this.sequence = (this.sequence + 1) & SEQUENCE_MAX;
				if (this.sequence == 0) {
					timeStamp = this.getNextMillis(timeStamp);
				}
			} else {
				this.sequence = 0L;
			}
			if (timeStamp < this.lastTimeStamp) {
				// TODO 异常处理
			}
			this.lastTimeStamp = timeStamp;
			long servIdTmp = servId;
			return ((timeStamp << TIMESTAMP_SHIFT) & TIMESTAMPMASK) | (servIdTmp << SERVID_SHIFT) | this.sequence;

		}

	}
}

package com.temp.code.sequence;

/**
 * @author ���� :lw E-mail:
 * @date ����ʱ�䣺2016��11��28�� ����2:54:58
 * @version 1.0 * @parameter
 * @since
 * @return
 */
public class SeqGenerator18 extends AbstractSqGenerator {
	private static final long TIMESTAMP_BITS = 47L;
	private static final long TIMESTAMPMASK = -1 ^ (-1L << TIMESTAMP_BITS);

	private static final byte[] LOCK = new byte[0];

	private long sequence = 0L;
	private long lastTimeStamp = -1L;

	private SeqGenerator18() {
		super();
	}

	private static class SeqGenerator18Holder {
		public static final SeqGenerator18 instance = new SeqGenerator18();
	}

	public static SeqGenerator18 getInstance() {
		return SeqGenerator18Holder.instance;
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
				// TODO �쳣����
			}
			this.lastTimeStamp = timeStamp;
			long servIdTmp = servId;
			return ((timeStamp << TIMESTAMP_SHIFT) & TIMESTAMPMASK) | (servIdTmp << SERVID_SHIFT) | this.sequence;

		}

	}
}

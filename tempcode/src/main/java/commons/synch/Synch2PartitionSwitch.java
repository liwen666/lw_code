package commons.synch;

public class Synch2PartitionSwitch {
	 
	private static ThreadLocal<Synch2PartitionInfo> synch2PartitionInfoHolder = new ThreadLocal<Synch2PartitionInfo>();

	public static Synch2PartitionInfo getSynch2PartitionInfo() {
		return synch2PartitionInfoHolder.get();
	}

	public static void setSynch2PartitionInfo(Synch2PartitionInfo synch2PartitionInfo) {
		synch2PartitionInfoHolder.set(synch2PartitionInfo);
	}
}

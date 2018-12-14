package commons.synch;
/**
 * 同步上下文holder
 * @author bizaiyi
 * May 5, 2015 4:58:04 PM
 * SynchContextHolder.java
 */
public class SynchContextHolder {
  private static ThreadLocal<SynchContext> synchContextHolder = new ThreadLocal<SynchContext>();

  public static void setSynchContext(SynchContext synchContext) {
	  synchContextHolder.set(synchContext);
  }

  public static SynchContext getSynchContext() {
    return synchContextHolder.get();
  }
}
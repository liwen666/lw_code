package thread;

import java.util.concurrent.Callable;





public class AutoTheard implements Callable<Object>{

	private String  name;
	
	private boolean mbSeekBarStopFlg;
	private boolean whileTrue;

	@Override
	public Object call() throws Exception { 
		Thread.currentThread().setName("张三");
		ThreadMap.threadMap.put("张三", Thread.currentThread());
				for(int i=0;i<100000;i++){
					if(whileTrue){
						System.out.println("线程等待");
						Thread.currentThread().wait();
					}
					System.out.println("当前线程是---"+Thread.currentThread().getName());
					Thread.sleep(1000);
					}

		return null;
	}

	public boolean isWhileTrue() {
		return whileTrue;
	}

	public void setWhileTrue(boolean whileTrue) {
		this.whileTrue = whileTrue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public boolean isMbSeekBarStopFlg() {
		return mbSeekBarStopFlg;
	}

	public void setMbSeekBarStopFlg(boolean mbSeekBarStopFlg) {
		this.mbSeekBarStopFlg = mbSeekBarStopFlg;
	}

}

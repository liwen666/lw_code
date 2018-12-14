package commons.dbbak.po;

public class BackupInfoPO {
	
	private int orderID;
	private String  command_line;
	private String start_time;
	private String exec_result;
	private String logfile_content;
	private String elapsed_time;
	
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getCommand_line() {
		return command_line;
	}
	public void setCommand_line(String commandLine) {
		command_line = commandLine;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String startTime) {
		start_time = startTime;
	}
	public String getExec_result() {
		return exec_result;
	}
	public void setExec_result(String execResult) {
		exec_result = execResult;
	}
	public String getLogfile_content() {
		return logfile_content;
	}
	public void setLogfile_content(String logfileContent) {
		logfile_content = logfileContent;
	}
	public String getElapsed_time() {
		return elapsed_time;
	}
	public void setElapsed_time(String elapsedTime) {
		elapsed_time = elapsedTime;
	}
	
	
}

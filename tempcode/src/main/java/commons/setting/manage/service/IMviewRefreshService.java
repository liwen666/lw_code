package commons.setting.manage.service;
/**
 * 刷物化视图
 * 2015-9-10 上午11:14:42
 * IMviewRefreshTask.java
 */
public interface IMviewRefreshService{
	/**
	 * 定时任务执行方法
	 */
	public void execute();
	/**
	 * 刷AGENCY物化视图
	 */
	public void refreshMview();
	/**
	 * 刷CODE_M_DISTRICT,CODE_M_DEPARTMENT物化视图
	 */
	public void mviewRefreshDisDept();
}

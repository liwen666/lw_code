package commons.setting.dataaudit.rule.util;
/**
 *@desc:TODO
 *@author： wxn
 *@date:2014-10-15下午2:45:10
 */
public class ReturnMsg {
	public static String OK="操作成功";
	public static String FALSE="操作失败";
	private boolean flag;
	private String msg;
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
   public static ReturnMsg getMessage(boolean flag,String message){
	   ReturnMsg returnMsg = new ReturnMsg();
	   returnMsg.setFlag(flag);
	   returnMsg.setMsg(message);
	   return returnMsg;
   }
}


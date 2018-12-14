
package commons.inputcomponent.po;

import java.io.Serializable;

/**
 * Author:CAOK 2015-11-17 上午10:37:38 Version 1.0
 */
public class ResultPO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 标识当前返回结果成功或失败 flag = true 成功 flag = false 失败
     */
    private boolean successFlag;
    /**
     * flag = true 返回结果
     */
    private Object result;
    /**
     * flag = false 错误号
     */
    private String errCode;
    /**
     * flag = false 错误信息
     */
    private String errMsg;
    
    public ResultPO() {
       
    }
    
    public ResultPO(Object result) {
        this.successFlag = true;
        this.result = result;
    }
    
    public ResultPO(String errCode, String errMsg) {
        this.successFlag = false;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public boolean isSuccessFlag() {
        return successFlag;
    }

    public void setSuccessFlag(boolean successFlag) {
        this.successFlag = successFlag;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}

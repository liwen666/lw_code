package commons.execscript.external.service;

import java.util.Map;
/**
 * 执行脚本外部接口
 * @author bizaiyi
 * 2016-1-7 上午10:45:24
 * IExtScriptService.java
 */
public interface IExtScriptService {

    /**
     * 执行全部未执行的脚本
     * @throws Exception
     * appID ：业务系统标识
     */
    public void execScript(Map<String, String> param) throws Exception;
}

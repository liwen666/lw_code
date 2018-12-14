package commons.communication;

import gov.mof.fasp2.dic.dataset.dto.DataSetDTO;
import gov.mof.fasp2.dic.dataset.dto.DataSetRangeDTO;
import gov.mof.fasp2.dic.dataset.service.IDataSetService;
import gov.mof.fasp2.dic.dto.DicMessageResultDTO;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.longtu.framework.communication.Message;
import com.longtu.framework.communication.handler.IMessageHandler;
import com.longtu.framework.util.ServiceFactory;
import com.tjhq.commons.setting.manage.service.IMviewRefreshService;
/**
 * 业务系统与平台系统消息机制处理类接收平台发送给业务系统的消息，同步的要给予响应
 * @author bzy
 */
public class AgencyChangedHanlder implements IMessageHandler {

    private IDataSetService dsService = (IDataSetService) ServiceFactory.getBean("fasp2.dic.dataset.service");
    @Resource
    private IMviewRefreshService mviewRefreshService;

    /************* 常量定义 ***********************/
    private static final String OPTIONTYPE_AYNCADD = "dic.datasetrange.ayncadd"; // 异步新增
    private static final String OPTIONTYPE_AYNCEDIT = "dic.datasetrange.ayncedit"; // 异步修改
    private static final String OPTIONTYPE_AYNCDEL = "dic.datasetrange.ayncdel"; // 异步删除
    private static final String OPTIONTYPE_AYNCSETSTATUS = "dic.datasetrange.ayncsetstatus"; // 异步状态变更
    
    private static final String DWCCDM = "CS041"; // 常量 ，用来标识CS041-单位层次代码

    /**
     * 异步方法(无返回值:平台仅通知业务系统)
     */
    @Override
    public void handleAsyncMessage(Message arg0) {
        String key = arg0.getKey(); // 操作类型
        if (
                key.equals(OPTIONTYPE_AYNCADD)  || //新增
                key.equals(OPTIONTYPE_AYNCEDIT)  || // 修改 
                key.equals(OPTIONTYPE_AYNCDEL)  || //删除
                key.equals(OPTIONTYPE_AYNCSETSTATUS)//停用启用
                ) {
            try {
                @SuppressWarnings("unchecked")
                List<DataSetRangeDTO> ls = (ArrayList<DataSetRangeDTO>) arg0.getContent();
                DataSetRangeDTO dto = ls.get(0);
                String dsguid = dto.getDsguid();
                DataSetDTO dDto = dsService.getDataSetByGuid(dsguid);
                String code = dDto.getCode();
                if (code.equals(DWCCDM)) {// 校验CS041-单位层次代码的操作权限
                    try {
                        mviewRefreshService.refreshMview();// 修改单位后刷物化视图
                    } catch (Exception e) {
                        throw new Exception("刷单位物化视图失败! ");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
                
        }
    }

    /**
     * 同步方法(带返回结果:平台需要业务系统给予操作许可,允许平台操作或不允许操作)
     */
    @Override
    public DicMessageResultDTO handleSyncMessage(Message arg0) {
        boolean result = true;
        String message = "可以进行此项操作!";
        // 封装返回给平台的结果对象
        DicMessageResultDTO dto = new DicMessageResultDTO();
        if (result) {
            dto.setResult(true); // 允许平台操作
        } else {
            dto.setResult(false); // 禁止平台操作
        }
        dto.setResultmessage(message); // 返回给平台的提示信息或原因
        return dto;
    }
}

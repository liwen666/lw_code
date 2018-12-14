/**
 * @Title: ConditionSetController.java
 * @Copyright (C) 2015 太极华青
 * @Description: 固定行列表浮动表模板条件设置Controller
 * @Revision 6.0 2015-11-23  ZhengQ
 */
package commons.setting.input.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.setting.input.po.ConditionSetPo;
import com.tjhq.commons.setting.input.service.IConditionSetService;

/**
 * @ClassName: ConditionSetController
 * @Description: 固定行列表浮动表模板条件设置
 * @author: ZhengQ 2015-11-24 上午11:12:29
 */

@Controller
@RequestMapping(value = "/commons/setting/input/conSet/")
public class ConditionSetController {
    /**
     * @Fields conditionSetService : 条件设置接口
     */
    @Resource
    private IConditionSetService conditionSetService;

    /**
     * @return conditionSetService
     */
    public IConditionSetService getConditionSetService() {
        return conditionSetService;
    }

    /**
     * @param conditionSetService 要设置的 conditionSetService
     */
    public void setConditionSetService(IConditionSetService conditionSetService) {
        this.conditionSetService = conditionSetService;
    }

    /**
     * 保存校验条件.
     * @param appID appID
     * @param tableID tableID
     * @param tableType tableType
     * @param option 操作，校验或是保存
     * @param condition 输入的条件
     * @return 操作结果成功或是失败
     * @throws Exception 异常
     */
    @RequestMapping(value = "saveCondition")
    @ResponseBody
    public Object saveCondition(String appID, String tableID, String tableType, String option, String condition)
            throws Exception {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            resMap = getConditionSetService().vertifyCon(tableID, condition);
            // 校验
            if ("verify".equals(option)) {
                if ("succ".equals(resMap.get("res"))) {
                    resMap.put("res", "校验成功！！");
                }
                return resMap;
            }
            // ------------保存 校验不成功 不能保存
            if (!"succ".equals(resMap.get("res"))) {
                return resMap;
            }
            getConditionSetService().saveCon(appID, tableID, tableType, condition);
            resMap.put("res", "保存成功！！");
        } catch (Exception e) {
            resMap.put("res", e.getCause().getMessage());
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 查询.
     * @param tableID tableID
     * @return 返回已经存过的条件
     */
    @RequestMapping(value = "getCondition")
    @ResponseBody
    public Object getCondition(String tableID) {
        ConditionSetPo resList = getConditionSetService().getCondition(tableID);
        return resList;
    }
}

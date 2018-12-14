/**
 * @Title: ConditionSetController.java
 * @Copyright (C) 2015 太极华青
 * @Description: 固定行列表浮动表模板条件设置接口
 * @Revision 6.0 2015-11-23  ZhengQ
 */

package commons.setting.input.service;

import java.util.Map;

import com.tjhq.commons.setting.input.po.ConditionSetPo;

/**
 * @ClassName: IConditionSetService
 * @Description: 固定行列表浮动表模板条件设置接口
 * @author: ZhengQ 2015-11-24 上午11:25:41
 */

public interface IConditionSetService {
    /**
     * 校验条件是否可以执行成功.
     * @param tableID tableID
     * @param condition 条件
     * @return 校验结果
     * @throws Exception 异常
     */
    Map<String, Object> vertifyCon(String tableID, String condition) throws Exception;

    /**
     * 保存模板条件设置.
     * @param appID appID
     * @param tableID tableID
     * @param tableType tableType
     * @param condition 模板条件
     * @throws Exception 异常
     */
    void saveCon(String appID, String tableID, String tableType, String condition) throws Exception;

    /**
     * 查询表中是否已经存在该表的设置条件.
     * @param tableID tableID
     * @return 模板条件
     */
    ConditionSetPo getCondition(String tableID);

}

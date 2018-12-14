/**
 * @Title: ConditionSetController.java
 * @Copyright (C) 2015 太极华青
 * @Description: 固定行列表浮动表模板条件设置Service实现类
 * @Revision 6.0 2015-11-23  ZhengQ
 */

package commons.setting.input.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.setting.input.dao.ConditionSetMapper;
import com.tjhq.commons.setting.input.po.ConditionSetPo;
import com.tjhq.commons.setting.input.service.IConditionSetService;

/**
 * @ClassName: ConditionSetService
 * @Description: 固定行列表浮动表模板条件设置Service实现类
 * @author: ZhengQ 2015-11-24 上午11:21:18
 */

@Service
@Transactional(readOnly = true)
public class ConditionSetService implements IConditionSetService {
    /**
     * @Fields conditionSetMapper : 固定行列表浮动表模板条件设置Mapper
     */
    @Resource
    private ConditionSetMapper conditionSetMapper;
    /**
     * @Fields dictTModelService : 接口
     */
    @Resource
    private IDictTModelService dictTModelService;

    /**
     * @return conditionSetMapper
     */
    public ConditionSetMapper getConditionSetMapper() {
        return conditionSetMapper;
    }

    /**
     * @param conditionSetMapper 要设置的 conditionSetMapper
     */
    public void setConditionSetMapper(ConditionSetMapper conditionSetMapper) {
        this.conditionSetMapper = conditionSetMapper;
    }

    /**
     * @return dictTModelService
     */
    public IDictTModelService getDictTModelService() {
        return dictTModelService;
    }

    /**
     * @param dictTModelService 要设置的 dictTModelService
     */
    public void setDictTModelService(IDictTModelService dictTModelService) {
        this.dictTModelService = dictTModelService;
    }

    @Override
    public ConditionSetPo getCondition(String tableID) {
        ConditionSetPo conSet = new ConditionSetPo();
        if (!"".equals(tableID) && tableID != null) {
            conSet = getConditionSetMapper().getCondition(tableID);
        }
        return conSet;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void saveCon(String appID, String tableID, String tableType, String condition) throws Exception {
        ConditionSetPo conSet = getConditionSetMapper().getCondition(tableID);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tableID", tableID);
        paramMap.put("tableType", tableType);
        paramMap.put("condition", condition);
        paramMap.put("appID", appID);
        // conSet>0 已经存在 就update
        try {
            if (conSet != null) {
             // 数据库中已经存在 前台清空 则删除
                if (condition.isEmpty()) {
                    getConditionSetMapper().deleteCon(tableID);
                } else {
                    // 修改
                    getConditionSetMapper().updateCon(paramMap);
                }
            } else {
                // 插入
                if (!condition.isEmpty()) {
                    getConditionSetMapper().insertCon(paramMap);
                }
            }
            // 保存完成 调用刷新视图接口
            getDictTModelService().rereateConfigView(tableID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Map<String, Object> vertifyCon(String tableID, String condition) throws Exception {
        Map<String, Object> resM = new HashMap<String, Object>();
        resM.put("res", "succ");
        String tableName = getConditionSetMapper().getTableName(tableID);
        ConditionSetPo conSet = getConditionSetMapper().getCondition(tableID);
        try {
            if (conSet == null) {
                // 该表暂未存过条件
                if (condition.trim().isEmpty()) {
                    resM.put("res", "请输入条件！");
                    return resM;
                }
            }
         // 保存过条件或输入条件不为空
            getConditionSetMapper().vertifyCon(tableName, condition);
        } catch (Exception e) {
            e.printStackTrace();
            resM.put("res", "校验失败！原因:" + e.getCause().getMessage());
        }
        return resM;
    }

}

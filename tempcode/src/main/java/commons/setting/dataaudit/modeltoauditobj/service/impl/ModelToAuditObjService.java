/**
 * @Title: ModelToAuditObjService.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用设置功能实现类
 * @Revision 6.0 2015-11-23  ZhengQ
 */

package commons.setting.dataaudit.modeltoauditobj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.setting.dataaudit.modeltoauditobj.dao.IModelToAuditObjDAO;
import com.tjhq.commons.setting.dataaudit.modeltoauditobj.service.IModelToAuditObjService;
import com.tjhq.commons.setting.input.po.TreeNode;

/**
 * @ClassName: ModelToAuditObjService
 * @Description: 录入对象登记功能实现类
 * @author: ZhengQ 2015-11-27 下午04:44:52
 */

@Service
@Transactional(readOnly = true)
public class ModelToAuditObjService implements IModelToAuditObjService {
    /**
     * @Fields modelToAuditObjMapper : 录入对象登记数据服务接口
     */
    @Resource
    private IModelToAuditObjDAO modelToAuditObjMapper;

    @Override
    public Object getObjTree(String tableType) throws ServiceException {
        List<TreeNode> objTree = null;
        try {
            objTree = modelToAuditObjMapper.getObjTree(tableType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_00200, "根据tableType获取录入对象报错!!", false);
        }
        return objTree;
    }

    @Override
    public Object getTabTree(String inputTableID) throws ServiceException {
        // 全部业务对象
        List<TreeNode> objTree = null;
        // 关系表中存在的数据
        List<String> modelIDs = null;
        try {
            objTree = modelToAuditObjMapper.getObjTree("0");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_00200, "获取全部的业务对象报错！", false);
        }

        try {
            modelIDs = modelToAuditObjMapper.getMidByInpID(inputTableID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_00200, "获取关系表中的数据报错！", false);
        }
        for (TreeNode tn : objTree) {
            for (String modelID : modelIDs) {
                if (!"".equals(modelID) && modelID != null) {
                    if (modelID.equals(tn.getId())) {
                        tn.setChecked("true");
                    }
                }
            }
        }
        return objTree;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Boolean saveModelToAuditobj(String addModelIDs, String delModelIDs, String inputTableID)
            throws ServiceException {
        String[] addModelids = addModelIDs.split(",");
        String[] delModelids = delModelIDs.split(",");
        try {
            if (delModelids.length > 0) {
                for (String delID : delModelids) {
                    if(!delID.isEmpty()){
                    modelToAuditObjMapper.delModelToAuditobj(delID, inputTableID);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_00102, "删除取消勾选的对象时报错！", false);
        }

        try {
            if (addModelids.length > 0) {
                for (String modelID : addModelids) {
                    if(!modelID.isEmpty()){
                    modelToAuditObjMapper.saveModelToAuditobj(modelID, inputTableID);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_00100, "保存勾选单位到关系表时报错！！", false);
        }
        return true;
    }
}

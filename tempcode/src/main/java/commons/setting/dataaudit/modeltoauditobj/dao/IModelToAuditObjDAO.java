/**
 * @Title: IModelToAuditObjDAO.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用设置功能数据服务接口
 * @Revision 6.0 2015-11-23  ZhengQ
 */
package commons.setting.dataaudit.modeltoauditobj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.setting.input.po.TreeNode;

/**
 * @ClassName: IModelToAuditObjDAO
 * @Description: 录入对象登记数据服务接口
 * @author: ZhengQ 2015-11-27 下午04:16:09
 */

public interface IModelToAuditObjDAO extends SuperMapper {
    /**.
     * 根据tableType获取录入对象
     * @param tableType 表类型
     * @return List<TreeNode>
     */
     List<TreeNode> getObjTree(@Param("tableType") String tableType);

    /**.
     * 保存到关系表
     * @param modelID 业务表ID
     * @param inputTableID 录入对象ID
     */
     void saveModelToAuditobj(@Param("modelID") String modelID, @Param("inputTableID") String inputTableID);

    /**.
     * 根据录入表id在关系表中查数据
     * @param inputTableID 录入对象ID
     * @return List<String>
     */
     List<String> getMidByInpID(@Param("inputTableID") String inputTableID);

    /**.
     * 删除关系表中的数据
     * @param modelID 业务表ID
     * @param inputTableID 录入对象ID
     */
     void delModelToAuditobj(@Param("modelID") String modelID, @Param("inputTableID") String inputTableID);
}

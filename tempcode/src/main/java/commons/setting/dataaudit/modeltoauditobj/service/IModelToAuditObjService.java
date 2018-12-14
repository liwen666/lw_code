/**
 * @Title: IModelToAuditObjService.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用设置功能接口
 * @Revision 6.0 2015-11-23  ZhengQ
 */
package commons.setting.dataaudit.modeltoauditobj.service;

import com.tjhq.commons.exception.ServiceException;

/**
 * @ClassName: IModelToAuditObjService
 * @Description: 录入对象登记功能接口
 * @author: ZhengQ 2015-11-27 下午04:41:27
 */

public interface IModelToAuditObjService {
    /**.
     * 获取录入对象
     * @param tableType 表类型
     * @return Object
     * @throws Exception 业务异常
     */
     Object getObjTree(String tableType) throws ServiceException;

    /**.
     * 获取所有录入表
     * @param tableID 表ID
     * @return Object
     * @throws Exception 异常
     */
     Object getTabTree(String tableID) throws ServiceException;

    /**.
     * 保存到关系表
     * @param addModelIDs  要增加的IDs
     * @param delModelIDs 要删除的IDs
     * @param objTableID  录入对象ID
     * @return Boolean
     * @throws Exception 异常
     */
     Boolean saveModelToAuditobj(String addModelIDs, String delModelIDs, String objTableID) throws ServiceException;

}

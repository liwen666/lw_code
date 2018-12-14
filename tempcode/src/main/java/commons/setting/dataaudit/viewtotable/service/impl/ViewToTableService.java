/**
 * @Title: ViewToTableService.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用设置功能实现类
 * @Revision 6.0 2015-11-23  ZhengQ
 */

package commons.setting.dataaudit.viewtotable.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.grid.commonGrid.service.impl.CommonGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.setting.dataaudit.viewtotable.dao.IViewToTableDAO;
import com.tjhq.commons.setting.dataaudit.viewtotable.service.IViewToTableService;
import com.tjhq.commons.setting.external.service.IAuditRuleOutService;
import com.tjhq.commons.setting.input.po.TreeNode;

/**
 * @ClassName: ViewToTableService
 * @Description: 视图对应物理表登记功能实现类
 * @author: ZhengQ 2015-11-27 下午05:02:11
 */

@Service
@Transactional(readOnly = true)
public class ViewToTableService extends CommonGridService implements IViewToTableService {
    /**
     * @Fields logger : 日志
     */
    private Logger logger = Logger.getLogger(ViewToTableService.class);
    /**
     * @Fields viewToTableMapper : 视图对应物理表登记功能数据服务接口
     */
    @Resource
    private IViewToTableDAO viewToTableMapper;
    /**
     * @Fields auditRuleOutService : 数据审核对外接口
     */
    @Resource
    private IAuditRuleOutService auditRuleOutService;
    /**
     * @Fields dictTModelService :  表接口
     */
    @Resource
    private IDictTModelService dictTModelService;

    @Override
    public Object getModelTree(String tableType, String viewID) throws ServiceException {
        // 所有物理表列表
        List<TreeNode> modelPOs = null;
        // 关系表中该视图下的所有物理表
        List<Map<String, Object>> viewToTabs = null;
        try {
            modelPOs = viewToTableMapper.getViewTree(tableType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_00200, "获取视图列表报错！！", false);
        }
        logger.debug("tableType:" + tableType + ",viewID:" + viewID);
        if ("1".equals(tableType) && (!"".equals(viewID) && viewID != null)) {
            // 首先查看是否有已经定义过的表
            try {
                viewToTabs = viewToTableMapper.getTableDataByViewID(viewID);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.SET_00200, "获取该视图的所有物理表列表报错！！", false);
            }
            for (Map<String, Object> vt : viewToTabs) {
                Iterator<TreeNode> it = modelPOs.iterator();
                while (it.hasNext()) {
                    String tableID = it.next().getId();
                    if (tableID.equals(vt.get("TABLEID"))) {
                        it.remove();
                    }
                }
            }
        }
        return modelPOs;
    }

    @Override
    public Table getTableDefine() throws ServiceException {
        Table grid = new Table();
        grid.setTableID("");
        grid.setTableDBName("");
        grid.setTableName("");
        try {
            setColumns(grid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERP_00002, "封装自定义表头时报错！", false);
        }
        return grid;
    }

    @Override
    public Object getData(Grid grid) throws ServiceException {
        String viewID = (String) grid.getExtProperties().get("viewID");
        List<Map<String, Object>> gridList = null;
        // 取得数据
        PageInfo pageInfo = new PageInfo();
        try {
            gridList = viewToTableMapper.getTableDataByViewID(viewID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_00200, "获取该视图下 的所有物理表列表报错！", false);
        }
        super.setGridData(gridList, pageInfo);
        return pageInfo;
    }

    /**
     * . 列定义
     * @param data Table对象
     * @throws
     */
    private void setColumns(Table data) {

        List<Column> list = new ArrayList<Column>();

        Column tableIDCol = new Column("TABLEID", "表ID", "表ID", "TABLEID", JSTypeUtils.getJSDateType(DataType.STRING),
                ShowType.SHOW_TYPE_HTML, 32, 0, 100, true, false, false, true);

        Column nameCol = new Column("NAME", "物理表", "物理表", "NAME", JSTypeUtils.getJSDateType(DataType.STRING),
                ShowType.SHOW_TYPE_HTML, 20, 0, 200, true, true, true, true);

        Column tabWhereCol = new Column("TABWHERE", "条件", "条件", "TABWHERE", JSTypeUtils.getJSDateType(DataType.STRING),
                ShowType.SHOW_TYPE_TEXT_AREA, 60, 0, 500, true, true, false, true);

        Column viewIDCol = new Column("VIEWID", "视图ID", "视图ID", "VIEWID", JSTypeUtils.getJSDateType(DataType.STRING),
                ShowType.SHOW_TYPE_HTML, 10, 0, 100, true, false, false, true);

        list.add(tableIDCol);
        list.add(nameCol);
        list.add(tabWhereCol);
        list.add(viewIDCol);

        data.setColumnList(list);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean saveViewToTab(String viewID, String tableIDs) throws ServiceException {
        String[] tableIDS = tableIDs.split(",");
        try {
            for (String tableID : tableIDS) {
                viewToTableMapper.saveViewToTab(viewID, tableID);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_00100, "保存数据到关系表报错！", false);
        }
        return true;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean updateViewToTab(Grid grid) throws ServiceException {
        String opt = (String) grid.getExtProperties().get("option");
        String isCanDelTab = (String) grid.getExtProperties().get("isCanDelTab");
        if ("del".equals(opt)) {
            // 获取删除数据
            List<Map<String, Object>> deleteList = grid.getDeleteValues();
            if (deleteList.size() > 0) {
                for (Map<String, Object> dataMap : deleteList) {
                    String tableID = (String) dataMap.get("TABLEID");
                    String viewID = (String) dataMap.get("VIEWID");
                    // 如果在审核定义中已经使用，则不能删除该视图对应的所有表，至少保留一条；如果没有使用，则可以全部删除
                    if (auditRuleOutService.canDelViewTOMaterialRaltion(viewID) || "1".equals(isCanDelTab)) {
                        try {
                            viewToTableMapper.delViewToTab(viewID, tableID);
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new ServiceException(ExceptionCode.SET_00102, "删除在关系表中的数据报错！", false);
                        }
                    } else {
                        // 存在引用关系，删除时必须保留一条数据
                        if ("0".equals(isCanDelTab)) {
                            throw new ServiceException(ExceptionCode.SET_00102, "该视图已在审核定义中使用，删除时至少保留一条数据！", false);
                        }
                    }
                }
            }
        }
        try {
            if ("upd".equals(opt)) {
                // 获取要修改的数据（保存条件）
                List<Map<String, Object>> updateList = grid.getUpdateValues();
                if (updateList.size() > 0) {
                    for (Map<String, Object> map : updateList) {
                        String tableID = (String) map.get("TABLEID");
                        String viewID = (String) map.get("VIEWID");
                        String tabWhere = (String) map.get("TABWHERE");
                        // 先校验 再保存
                       String dbTableName = dictTModelService.getDictTModelByID(tableID, false).getDbtablename();
                        viewToTableMapper.vetifyCon(tabWhere, dbTableName);
                        viewToTableMapper.updViewToTab(viewID, tableID, tabWhere);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_00101, "修改数据时报错！" + e.getCause().getMessage(), false);
        }
        return true;
    }
}

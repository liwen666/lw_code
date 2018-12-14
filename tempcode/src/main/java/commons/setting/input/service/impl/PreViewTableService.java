
package commons.setting.input.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.form.baseform.service.IBaseFormService;
import com.tjhq.commons.inputcomponent.grid.basegrid.service.impl.BaseGridService;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.grid.commonGrid.service.ICommonGridService;
import com.tjhq.commons.inputcomponent.grid.fixgrid.po.FixGrid;
import com.tjhq.commons.inputcomponent.grid.fixgrid.service.IFixGridService;
import com.tjhq.commons.inputcomponent.grid.floatgrid.po.FloatGrid;
import com.tjhq.commons.inputcomponent.grid.floatgrid.service.IFloatGridService;
import com.tjhq.commons.inputcomponent.grid.propertygrid.po.PropertyGrid;
import com.tjhq.commons.inputcomponent.grid.propertygrid.service.IPropertyGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Condition;
import com.tjhq.commons.inputcomponent.po.Fieldset;
import com.tjhq.commons.inputcomponent.po.Form;
import com.tjhq.commons.inputcomponent.po.FormItem;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.TableUtil;
import com.tjhq.commons.inputcomponent.utils.ToolUtil;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.setting.external.po.RECDetailPO;
import com.tjhq.commons.setting.external.po.RECPO;
import com.tjhq.commons.setting.input.service.IPreViewTableService;
import com.tjhq.commons.setting.input.service.ISinRecService;
import com.tjhq.commons.utils.UserUtil;

@Service
@Transactional(readOnly = true)
public class PreViewTableService extends BaseGridService implements IPreViewTableService {
    @Resource
    private IPropertyGridService propertyGridService;
    @Resource
    private ICommonGridService commonGridInputService;
    @Resource
    private IFloatGridService floatGridService;
    @Resource
    private IFixGridService fixGridService;
    @Resource
    private IBaseFormService baseFormService;
    @Resource
    private ISinRecService sinRecService;

    @Override
    public Object getData(String grid, String dealType) throws ServiceException {
        // 设置agency约束条件
        String whereExpression = "";
        whereExpression = " ROWNUM <1  ";
        Condition relAgencyCon = new Condition();
        relAgencyCon.setExpression(whereExpression);

        if ("62".equals(dealType)) {// 基本数字表
            PropertyGrid propertyGrid = (PropertyGrid) (new ObjectMapper()).readJson(grid, PropertyGrid.class);
            List<Condition> queryPanelParamList = propertyGrid.getQueryPanelParamList();
            if (null == queryPanelParamList) {
                queryPanelParamList = new ArrayList<Condition>();
            }
            queryPanelParamList.add(relAgencyCon);
            propertyGrid.setQueryPanelParamList(queryPanelParamList);
            return propertyGridService.getData(propertyGrid);
        } else if ("A0".equals(dealType) || "A0*01".equals(dealType) || "A0*02".equals(dealType)) {// 一般录入表 ||人员表
            CommonGrid commonGrid = (CommonGrid) (new ObjectMapper()).readJson(grid, CommonGrid.class);
            List<Condition> queryPanelParamList = commonGrid.getQueryPanelParamList();
            if (null == queryPanelParamList) {
                queryPanelParamList = new ArrayList<Condition>();
            }
            queryPanelParamList.add(relAgencyCon);
            commonGrid.setQueryPanelParamList(queryPanelParamList);
            return commonGridInputService.getData(commonGrid);
        } else if ("B0".equals(dealType)) {// 一般录入表（ 无单位） 则无条件
            CommonGrid commonGrid = (CommonGrid) (new ObjectMapper()).readJson(grid, CommonGrid.class);
            return commonGridInputService.getData(commonGrid);
        } else if ("A1".equals(dealType)) {// 固定行列表
            FixGrid fixGrid = (FixGrid) (new ObjectMapper()).readJson(grid, FixGrid.class);
            List<Condition> queryPanelParamList = fixGrid.getQueryPanelParamList();
            if (null == queryPanelParamList) {
                queryPanelParamList = new ArrayList<Condition>();
            }
            queryPanelParamList.add(relAgencyCon);
            fixGrid.setQueryPanelParamList(queryPanelParamList);
            return fixGridService.getData(fixGrid);
        } else if ("A2".equals(dealType)) {// 浮动表
            FloatGrid floatGrid = (FloatGrid) (new ObjectMapper()).readJson(grid, FloatGrid.class);
            List<Condition> queryPanelParamList = floatGrid.getQueryPanelParamList();
            if (null == queryPanelParamList) {
                queryPanelParamList = new ArrayList<Condition>();
            }
            queryPanelParamList.add(relAgencyCon);
            floatGrid.setQueryPanelParamList(queryPanelParamList);
            return floatGridService.getData(floatGrid);
        } else {
            Grid grid_z = (Grid) (new ObjectMapper()).readJson(grid, Grid.class);
            List<Condition> queryPanelParamList = grid_z.getQueryPanelParamList();
            if (null == queryPanelParamList) {
                queryPanelParamList = new ArrayList<Condition>();
            }
            queryPanelParamList.add(relAgencyCon);
            grid_z.setQueryPanelParamList(queryPanelParamList);
            return super.getData(grid_z);
        }
    }

    @Override
    public Object getDefine(String tableID, String dealType, String typeID, String processID) throws ServiceException {
        String userId = UserUtil.getUserInfo().getGuid();
        Table grid = null;
        if ("A0".equals(dealType) || "A0*01".equals(dealType) || "A0*02".equals(dealType)) {// 一般录入表 || 人员表 || 增量表
            CommonGrid commonGrid = new CommonGrid();
            commonGrid.setTableID(tableID);
            grid = (CommonGrid) commonGridInputService.initStructure(commonGrid, userId);
           
        } else if ("62".equals(dealType)) {// 基本数字表
            PropertyGrid propertyGrid = new PropertyGrid();
            propertyGrid.setTableID(tableID);
            grid = (Grid) propertyGridService.initStructure(propertyGrid, userId);
        } else if ("A1".equals(dealType)) {// 固定行列表
            FixGrid fixGrid = new FixGrid();
            fixGrid.setTableID(tableID);
             grid = (FixGrid) fixGridService.initStructure(fixGrid, userId);
            return grid;
        } else if ("A2".equals(dealType)) {// 浮动表
            FloatGrid floatGrid = new FloatGrid();
            floatGrid.setTableID(tableID);
             grid = (FloatGrid) floatGridService.initStructure(floatGrid, userId);
        } else if ("63".equals(dealType)) {// 单记录表
            Form form = new Form();
            form.setTableID(tableID);
            baseFormService.initStructure(form, userId);
            form.setVisiable(true);
            setFormDefine(form);
            return form;
        } else {
            Table table = new Table();// 其他表
            table.setTableID(tableID);
            grid = initStructure(table, userId);
        }
        grid.setVisiable(true);
        return grid;
    }
    @Override
    public Table initStructure(Table table, String userID) throws ServiceException {
        return super.initStructure(table, userID);
    }
    /**
     * 取单页面定义
     * 
     * @param form
     * @throws ServiceException 
     */
    private void setFormDefine(Form form) throws ServiceException {
        // 取Form定义
        List<RECPO> recPoList = sinRecService.getSetRECList(form.getTableID());
        if(recPoList==null || recPoList.size()==0){
            throw new ServiceException(ExceptionCode.COM_00001,"未进行单记录表的form设置！",false);
        }  
        RECPO recPO = recPoList.get(0);
        Map<String, List<RECDetailPO>>  recDetailPOListMap = sinRecService.getSetRECDetailList(recPO.getRECID());
        List<RECDetailPO> details = recDetailPOListMap.get("columns");
        //List<RECDetailPO> fieldsetList =  recDetailPOListMap.get("fieldset");
        //details.addAll(fieldsetList);
        
        form.setColumnSize(Integer.parseInt(recPO.getSHOWCOLS()));
        form.setLabelWidth(Integer.parseInt(recPO.getTITLEWIDTH()));
        form.setAbsolutePosition(Integer.parseInt(recPO.getABSOLUTEPOSITION()));
        form.setLableTextAlign(recPO.getLABLETEXTALIGN());

        Map<String, FormItem> formItems = new HashMap<String, FormItem>();
        for (Column column : TableUtil.getLeafColumnList(form)) {
            FormItem item = (FormItem) column;
            formItems.put(item.getColumnID(), item);
        }

        if(details==null){
            return;
        }
        if(details!=null && details.size()==0){
            throw new ServiceException(ExceptionCode.COM_00001,"列表数据为空！",false);
        }
        for (RECDetailPO detail : details) {
            setFormItem(form, formItems, null, detail);
        }
        
        for (Map.Entry<String, FormItem> entry : formItems.entrySet()) {
            entry.getValue().setVisible(false);
            form.getFormItemList().add(entry.getValue());
        }
    }
    private void setFormItem(Form form, Map<String, FormItem> formItems,
            Fieldset parentFieldset, RECDetailPO detail) {
        if ("1".equals(detail.getIsgroupctrl())) {// 如果是分组框
            Fieldset fieldset = new Fieldset();
            fieldset.setColSpan(detail.getColspan());
            fieldset.setName(detail.getCtrlname());
            fieldset.setOrderId(detail.getOrderid());
            fieldset.setRowSpan(detail.getRowspan());
            fieldset.setLeftcols(detail.getLEFTCOLS());
            fieldset.setToprows(detail.getTOPROWS());
            fieldset.setTopgroups(detail.getTOPGROUPS());
            fieldset.setBorder(detail.getBORDER());
            fieldset.setVisiable(ToolUtil.toBoolean(detail.getIsshow()));
            form.getFormItemList().add(fieldset);
            if (detail.getListDetail() == null
                    || detail.getListDetail().size() == 0)
                return;
            for (RECDetailPO po : detail.getListDetail()) {
                setFormItem(form, formItems, fieldset, po);
            }
        } else {
            FormItem item = formItems.get(detail.getCtrlid());
            if(item==null){
                System.out.println();
                return;
            }
            formItems.remove(detail.getCtrlid());
            item.setRowSpan(detail.getRowspan());
            item.setColSpan(detail.getColspan());
            if (item.isVisible()) {
                item.setVisible(ToolUtil.toBoolean(detail.getIsshow()));
            }

            if (parentFieldset != null) {
                parentFieldset.getFormItemList().add(item);
            } else {
                form.getFormItemList().add(item);
            }

        }
    }
}


package commons.setting.input.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.cache.service.IDataCacheService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.floatgrid.po.FloatGrid;
import com.tjhq.commons.inputcomponent.po.ResultPO;
import com.tjhq.commons.setting.external.service.IEntryOuterService;
import com.tjhq.commons.setting.input.po.DictTSetFddefPO;
import com.tjhq.commons.setting.input.service.IFixedService;
import com.tjhq.commons.setting.input.service.IFloatService;
import com.tjhq.commons.utils.UserUtil;

/**
 * 类名称：FloatController 类描述：浮动表设置 创建人： zz 修改于2016-12-27 创建时间：Mar 5, 2014 7:15:22 AM
 * @version
 */
@Controller
@RequestMapping(value = "/commons/setting/input/float")
public class FloatController {
    @Resource
    public IFloatService floatService;
    @Resource
    public IFixedService fixedService;
    @Resource
    private IDataCacheService dataCacheService;

    /**
     * 取业务数据
     * @Title: getData
     * @param @param table
     * @param @return
     * @param @throws Exception 设定文件
     * @return List 返回类型
     * @throws
     */
    @RequestMapping(value = "getData")
    @ResponseBody
    public Object getData(String grid) throws Exception {
        FloatGrid floatGrid = (FloatGrid) (new ObjectMapper()).readValue(grid, FloatGrid.class);

        Object floatData = floatService.getData(floatGrid);

        return floatData;
    }

    /**
     * 获得固定行列表保存数据
     * @param businessObjId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "saveData")
    @ResponseBody
    public Object saveData(String grid) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        try {
            FloatGrid floatGrid = (FloatGrid) (new ObjectMapper()).readValue(grid, FloatGrid.class);
            floatService.saveData(floatGrid);

            map.put("flag", "true");
            map.put("result", "保存成功！");
        } catch (ServiceException e) {
            e.printStackTrace();
            map.put("result", e.getErrorMessage());
        }
        return map;
    }

    /**
     * 清缓存
     * @param keys
     */
    private void clearCatch(String[] keys) {
        Object commInputCatch = dataCacheService.get(keys);
        if (commInputCatch != null) {
            dataCacheService.put(keys, null);
        }
    }

    /**
     * 获得浮动表定义
     * @param businessObjId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getDefine")
    @ResponseBody
    public Object getDefine(@RequestParam(value = "tableID") String tableID) throws Exception {
        String userId = UserUtil.getUserInfo().getGuid(); 
        FloatGrid grid = new FloatGrid();
        grid.setTableID(tableID);
        FloatGrid floatgrid = (FloatGrid) floatService.initStructure(grid, userId);
        return floatgrid;
    }

    /**
     * 初始化浮动表的数据
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "initFloat")
    @ResponseBody
    public Object initFloat(String tableID, String dealType, String opt) {
        // * 为模板数据
        ResultPO resultPO = null;

        try {
            if ("A2".equals(dealType)) {
                floatService.initFloatData(tableID, opt);
            }
            if ("A1".equals(dealType)) {
                fixedService.initFixData(tableID);
            }
            resultPO = new ResultPO("");
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        return resultPO;
    }

    /**
     * .删除表内全部模板数据
     * @param tableID
     * @return
     * @throws
     */
    @RequestMapping(value = "clearInitData")
    @ResponseBody
    public Object clearInitData(String tableID) {
        ResultPO resPO = null;
        try {
            floatService.deleteAllTemplateData(tableID);
            resPO = new ResultPO("");
        } catch (ServiceException e) {
            e.printStackTrace();
            resPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        return resPO;
    }

    /*
     * 保存整表设置
     */
    @RequestMapping(value = "saveFloatWhole")
    @ResponseBody
    public String saveFloatWhole(HttpServletRequest request) throws Exception {
        // JSON数据
        String jsonTable = request.getParameter("jsonTable");
        String operator = request.getParameter("operator"); // 操作
        String dealType = request.getParameter("dealType");

        String flag = "";
        DictTSetFddefPO fddef = (new ObjectMapper()).readValue(jsonTable, DictTSetFddefPO.class);
        if (ConverTables.isNotNull(operator)) {
            // 是否为固定浮动表
            if (null == fddef.getIsFix() || ("").equals(fddef.getIsFix()))
                fddef.setIsFix("0");
            flag = floatService.saveFloatWhole(fddef, operator, dealType);

            String[] keys = {"HQ.COMM", IEntryOuterService.SETFDDEF, fddef.getTableID() };
            this.clearCatch(keys);
        }
        return flag;
    }

    // 获取层次名称列
    @RequestMapping(value = "getLvlNanmeCol")
    @ResponseBody
    public String getLvlNanmeCol(HttpServletRequest request) throws Exception {
        String tableID = request.getParameter("tableID");
        String dealType = request.getParameter("dealType");
        Map<String, Object> map = new HashMap<String, Object>();

        if (dealType.equals("A2"))
            map = floatService.getLvlNanmeCol_1(tableID);
        if (dealType.equals("A1"))
            map = fixedService.getLvlNanmeCol_2(tableID);

        String lvlNanme = "";
        if (ConverTables.isNotNull(map.get("lvlNanme"))) {
            lvlNanme = map.get("lvlNanme").toString();
        }
        return lvlNanme;
    }

    // 设置单元格权限 ，如果单元格中包含公式 0
    @RequestMapping(value = "saveCellSecu")
    @ResponseBody
    public String saveCellSecu(HttpServletRequest request) throws Exception {
        String dataKey = request.getParameter("dataKey"); // 主键
        String cIndex = request.getParameter("cIndex"); // 更新位置
        String cSecu = request.getParameter("cSecu"); // 0,1
        String cellSecu = request.getParameter("cellSecu"); // 0,1
        String tableID = request.getParameter("tableID");

        String is_success = "{\"flag\":\"true\"}";
        try {
            floatService.saveCellSecu(dataKey, cellSecu, cIndex, cSecu, tableID);

        } catch (Exception e) {
            e.printStackTrace();
            is_success = "{\"flag\":\"false\"}";
        }

        return is_success;
    }

    /**
     * 刷新浮动表设置 一、将浮动表 <设置表DICT_T_SETFDDEF> 中 colOrder 列 更新 二、将浮动表中 CELLSECU 单元权限 根据colOrder列 调整顺序、删除、增加 等
     * @param tableID
     * @param dealType
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "refreshFloatSet")
    @ResponseBody
    public String refreshFloatSet(@RequestParam(value = "tableID") String tableID,
            @RequestParam(value = "dealType") String dealType) throws Exception {

        String is_success = "{\"flag\":\"true\"}";
        try {
            floatService.refreshFloatSet(tableID, dealType);

        } catch (Exception e) {
            e.printStackTrace();
            is_success = "{\"flag\":\"false\"}";
        }

        return is_success;
    }

    /**
     * 在factor表中isVisible 为 1, 并且不包含缺省列。
     * @param tableID
     * @param dealType
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "factorTree")
    @ResponseBody
    public Object getFactorTree(String tableID, String dealType) {
        ResultPO resultPO = null;
        try {
            resultPO = new ResultPO(floatService.getFactorTree(tableID, dealType));
        } catch (ServiceException e) {
            e.printStackTrace();
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        return resultPO;
    }

    @RequestMapping(value = "getResCount")
    @ResponseBody
    public Object getResCount(String tableID) {
        ResultPO resPO = null;
        Integer resCount = 0;
        try {
            resCount = floatService.getResCount(tableID);
            resPO = new ResultPO(resCount);
        } catch (ServiceException e) {
            resPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        return resPO;
    }

    @RequestMapping(value = "getCodeCount")
    @ResponseBody
    public Object getCodeCount(String tableID) {
        ResultPO resultPO = null;
        try {
            resultPO = new ResultPO(floatService.getCodeCount(tableID));
        } catch (ServiceException e) {
            resultPO = new ResultPO(e.getCode(), e.getErrorMessage());
        }
        return resultPO;
    }
}

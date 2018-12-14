/**
 * @Title: ExceptionManageService.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用设置功能实现类
 * @Revision 6.0 2015-11-23  ZhengQ
 */

package commons.setting.exception.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.tjhq.commons.setting.exception.dao.IExceptionManageDAO;
import com.tjhq.commons.setting.exception.service.IExceptionManageService;

/**
 * @ClassName: ExceptionManageService
 * @Description: 异常管理功能实现类
 * @author: ZhengQ 2015-11-26 上午11:15:34
 */

@Service
@Transactional(readOnly = true)
public class ExceptionManageService extends CommonGridService implements IExceptionManageService {

    /**
     * @Fields logger : 日志
     */
    private Logger logger = Logger.getLogger(ExceptionManageService.class);

    /**
     * @Fields exceptionManageDao : 异常管理数据服务层接口
     */
    @Resource
    private IExceptionManageDAO exceptionManageDao;

    /**
     * @return exceptionManageDao
     */
    public IExceptionManageDAO getExceptionManageDao() {
        return exceptionManageDao;
    }

    /**
     * @param exceptionManageDao 要设置的 exceptionManageDao
     */
    public void setExceptionManageDao(IExceptionManageDAO exceptionManageDao) {
        this.exceptionManageDao = exceptionManageDao;
    }

    /**
     * @Fields EXCPTIONMANAGE_TABNAME : 异常管理表表名
     */
    private static final String EXCPTIONMANAGE_TABNAME = "DICT_T_EXCEPTION";

    /**
     * .
     * <p>
     * Title: getTableData
     * </p>
     * <p>
     * Description:获取异常管理表数据
     * </p>
     * @param grid Grid对象
     * @return PageInfo对象
     * @throws ServiceException 业务异常
     * @see com.tjhq.commons.setting.exception.service.IExceptionManageService#getTableData
     * (com.tjhq.commons.inputcomponent.po.Grid)
     */
    @Override
    public Object getTableData(Grid grid) throws ServiceException {
        String code = (String) grid.getExtProperties().get("code");

        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

        PageInfo pageInfo = new PageInfo();
        logger.debug("开始获取表数据");
        try {
            dataList = getExceptionManageDao().getExceptionDataByCode(code); // 取得数据
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_01200, "该表取数据时发生异常", true);
        }
        super.setGridData(dataList, pageInfo);

        logger.debug("取出：  " + dataList.size() + " 条数据");

        return pageInfo;
    }

    /**
     * .
     * <p>
     * Title: getTableDefine
     * </p>
     * <p>
     * Description:获取异常管理表表头
     * </p>
     * @return Table 对象
     * @throws ServiceException 业务异常
     * @see com.tjhq.commons.setting.exception.service.IExceptionManageService#getTableDefine()
     */
    @Override
    public Table getTableDefine() throws ServiceException {
        Table grid = new Table();
        grid.setTableID("");
        grid.setTableDBName(EXCPTIONMANAGE_TABNAME);
        grid.setTableName("");
        try {
            setColumns(grid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERP_00002, "该表封装时报错", true);
        }
        return grid;
    }

    /**
     * . 自定义显示的异常管理表的列
     * @param data Table对象
     * @throws ServiceException 业务异常
     * @throws
     */
    private void setColumns(Table data) throws ServiceException {

        List<Column> list = new ArrayList<Column>();

        // GUID列 隐藏
        Column guidCol = new Column("GUID", "GUID", "GUID", "GUID", JSTypeUtils.getJSDateType(DataType.STRING),
                ShowType.SHOW_TYPE_HTML, 32, 0, 100, true, false, true, false);
        // code列
        Column codeCol = new Column("CODE", "CODE", "CODE", "CODE", JSTypeUtils.getJSDateType(DataType.STRING),
                ShowType.SHOW_TYPE_HTML, 20, 0, 200, true, true, false, false);
        // 备注列
        Column remarkCol = new Column("REMARK", "备注", "备注", "REMARK", JSTypeUtils.getJSDateType(DataType.STRING),
                ShowType.SHOW_TYPE_HTML, 60, 0, 300, true, true, false, false);
        // 提示信息列
        Column messageCol = new Column("MESSAGE", "提示信息", "提示信息", "MESSAGE",
                JSTypeUtils.getJSDateType(DataType.STRING), ShowType.SHOW_TYPE_HTML, 200, 0, 300, true, true, false,
                false);
        // 详细信息列
        Column detailMessageCol = new Column("DETAILMESSAGE", "详细信息", "详细信息", "DETAILMESSAGE", JSTypeUtils
                .getJSDateType(DataType.STRING), ShowType.SHOW_TYPE_TEXT_AREA, 60, 0, 400, true, true, false, false);

        list.add(guidCol);
        list.add(codeCol);
        list.add(remarkCol);
        list.add(messageCol);
        list.add(detailMessageCol);

        data.setColumnList(list);
    }

    /**
     * .
     * <p>
     * Title: saveData
     * </p>
     * <p>
     * Description: 保存表数据（新增、修改、删除）
     * </p>
     * @param table Table 对象
     * @return boolean
     * @throws ServiceException 业务异常
     * @see com.tjhq.commons.inputcomponent.component.service.impl.BaseTableService#saveData
     * (com.tjhq.commons.inputcomponent.po.Table)
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean saveData(Table table) throws ServiceException {
        List<Map<String, Object>> insertValues = table.getInsertValues();
        List<Map<String, Object>> updateValues = table.getUpdateValues();
        List<Map<String, Object>> deleteValues = table.getDeleteValues();
        logger.debug("insertValues:" + insertValues + "\n updateValues" + updateValues + "\n deleteValues"
                + deleteValues);
        // 校验非空
        validateData(table);

        if (!insertValues.isEmpty()) {
            vetifyCodeStyle(insertValues);
            vetifyCode(insertValues);
            logger.debug("开始保存表数据");
            try {
                for (Map<String, Object> map : insertValues) {
                    getExceptionManageDao().saveData(map);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.SET_01100, "该表保存数据发生异常", true);
            }
        }
        if (!updateValues.isEmpty()) {
            vetifyCodeStyle(updateValues);
            vetifyCode(updateValues);
            logger.debug("开始修改表数据");
            try {
                for (Map<String, Object> map : updateValues) {
                    getExceptionManageDao().updateData(map);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.SET_01101, "该表修改数据发生异常", true);
            }
        }
        if (!deleteValues.isEmpty()) {
            logger.debug("开始删除表数据");
            try {
                getExceptionManageDao().deleteData(deleteValues);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.SET_01102, "该表删除数据发生异常", true);
            }
        }
        return true;
    }

    /**
     * . 校验code是否重复重复
     * @param values 修改、新增的数据
     * @throws ServiceException 业务异常
     * @throws
     */
    private void vetifyCode(List<Map<String, Object>> values) throws ServiceException {
        List<Map<String, Object>> tableData = new ArrayList<Map<String, Object>>();
        logger.debug("校验的参数为:" + values);
        logger.debug("开始获取表数据");
        try {
            // 取得表中所有数据
            tableData = getExceptionManageDao().getExceptionDataByCode("");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_01200, "该表取数据时发生异常", true);
        }
        for (Map<String, Object> value : values) {
            for (Map<String, Object> td : tableData) {
                // CODE相同 但guid不同 即不是同一条数据
                if (value.get("CODE").equals(td.get("CODE")) && !value.get("GUID").equals(td.get("GUID"))) {
                    throw new ServiceException(ExceptionCode.INP_00204, "该表主键校验重复，CODE=" + value.get("CODE"), false);
                }
            }
        }
    }

    /**
     * . code校验 XXX_000000 样式
     * @param values 参数
     * @throws ServiceException 业务异常
     * @throws
     */
    private  void vetifyCodeStyle(List<Map<String, Object>> values) throws ServiceException {
        String code = "";
        for (Map<String, Object> vv : values) {
            code = (String) vv.get("CODE");
            //code格式必须为 XXX_000FF   三个字母组合_三个数字加上两个十六进制的数
            String regExp = "[A-Z]{3}_[0-9A-Z]{3}[0-9A-F]{2}";
            Pattern pattern = Pattern.compile(regExp);
            // 忽略大小写的写法
            // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(code);
            logger.debug(matcher.matches());
            // 字符串是否与正则表达式相匹配
            if (!matcher.matches()) {
                throw new ServiceException(ExceptionCode.INP_00203, "code格式不对，必须为XXX_00000,且字母要大写,最后两位为16进制数!", false);
            }
        }
    }
}

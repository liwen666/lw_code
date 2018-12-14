
package commons.inputcomponent.form.baseform.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.stereotype.Service;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.component.service.impl.BaseTableService;
import com.tjhq.commons.inputcomponent.form.baseform.dao.IBaseFormMapper;
import com.tjhq.commons.inputcomponent.form.baseform.service.IBaseFormService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Form;
import com.tjhq.commons.inputcomponent.po.FormItem;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.TableUtil;

/**
 * Author:CAOK 2014-9-4 下午02:23:54 Version 1.0
 */
@Service("baseFormService")
public class BaseFormService extends BaseTableService implements IBaseFormService {

    @Resource
    private IBaseFormMapper baseFormMapper;

    public IBaseFormMapper getBaseFormMapper() {
        return baseFormMapper;
    }

    public void setBaseFormMapper(IBaseFormMapper baseFormMapper) {
        this.baseFormMapper = baseFormMapper;
    }

    @Override
    protected Column getNewColumn() {
        return new FormItem();
    }

    @Override
    public Object getData(Table table) throws ServiceException {
        Form form = (Form) table;
        if (form.getColumnList() == null || form.getColumnList().size() == 0) {
            super.setTableColumns(form);
        }
        Map<String, String> parameterMap = new HashMap<String, String>();
        parameterMap.put("tableName", table.getTableDBName());
        parameterMap.put("selectElement", TableUtil.getSqlSelect(table));// 查找的元素
        parameterMap.put("rowWriteSecu", form.getRowWriteSecu());
        parameterMap.put("rowVisibleSecu", form.getRowVisibleSecu());
        String sqlWhere = TableUtil.getTableCondition(table);
        if (form.getCondition() != null && form.getCondition().trim().length() > 0) {
            if (sqlWhere != null && sqlWhere.length() > 0) {
                sqlWhere += " AND (" + form.getCondition() + ")";
            } else {
                sqlWhere = form.getCondition();
            }
        }

        parameterMap.put("sqlWhere", sqlWhere);
        try {
            return getBaseFormMapper().getFormData(parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getCause() instanceof TooManyResultsException) {
                throw new ServiceException(ExceptionCode.ERR_00000, "单记录表查询到多行数据!", false);
            }
            throw new ServiceException(ExceptionCode.ERR_00000, "获取单记录表数据错误!", false);
        }
    }

}

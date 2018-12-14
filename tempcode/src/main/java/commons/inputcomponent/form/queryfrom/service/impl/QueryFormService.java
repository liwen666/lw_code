
package commons.inputcomponent.form.queryfrom.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.form.baseform.service.impl.BaseFormService;
import com.tjhq.commons.inputcomponent.form.queryfrom.po.QueryForm;
import com.tjhq.commons.inputcomponent.form.queryfrom.po.QueryFormItem;
import com.tjhq.commons.inputcomponent.form.queryfrom.service.IQueryFormService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Form;
import com.tjhq.commons.inputcomponent.utils.TableUtil;
import com.tjhq.commons.inputcomponent.utils.ToolUtil;
import com.tjhq.commons.setting.external.po.DictTSetQuerydDetPO;
import com.tjhq.commons.setting.external.po.DictTSetQuerydPO;
import com.tjhq.commons.setting.input.dao.EntryMapper;

@Service("queryFormService")
public class QueryFormService extends BaseFormService implements IQueryFormService {
    public final String REFHEAD = "SNAME_";
    public final String OPERATORHEAD = "OPERATOR_";
    @Resource
    private EntryMapper entryMapper;

    @Override
    protected Column getNewColumn() {
        return new QueryFormItem();
    }

    @Override
    public void setFormDefine(QueryForm queryForm) throws ServiceException {

        DictTSetQuerydPO queryPO = null;
        try {
            queryPO = getEntryOuterService().getDataQuerydList(queryForm.getTableID());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERR_00000, "加载查询定义失败!", false);
        }

        if (null == queryPO || null == queryPO.getQuerydDet() || queryPO.getQuerydDet().size() == 0)
            return;

        super.setTableDefine(queryForm);

        queryForm.setShowTitle(ToolUtil.toBoolean(queryPO.getIsShowTitle()));
        queryForm.setColumnSize(queryPO.getShowCols());
        queryForm.setLabelWidth(queryPO.getTitleWidth());

        try {
            setQueryFormItem(queryForm, queryPO, queryPO.getQuerydDet());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERR_00000, "转换查询定义失败!", false);
        }
    }

    protected void setQueryFormItem(QueryForm queryForm, DictTSetQuerydPO queryPO,
                                    List<DictTSetQuerydDetPO> queryItems) {

        Map<String, QueryFormItem> formItems = new HashMap<String, QueryFormItem>();
        List<Object> queryFormItemArray = new ArrayList<Object>();
        double cols = 0;
        double height = 0;
        for (Column column : TableUtil.getLeafColumnList(queryForm)) {
            QueryFormItem item = (QueryFormItem) column;
            formItems.put(item.getColumnID(), item);
        }

        for (DictTSetQuerydDetPO queryItem : queryItems) {
            QueryFormItem item = formItems.get(queryItem.getCtrlID());
            item.setColSpan(queryItem.getColspan());
            item.setOperator(queryItem.getOperator());
            item.setRowSpan(1);
            item.setDefaultValue(queryItem.getDefvalue());
            if (item.isVisible()) {
                item.setVisible(ToolUtil.toBoolean(queryItem.getIsShow()));
            }
            queryFormItemArray.add(item);
            if (item.isVisible()) {
                cols += item.getColSpan();
            }

        }

        try {
            if (queryForm.getColumnSize() == 0)
                queryForm.setColumnSize(1);
            height = (int) Math.ceil(cols / queryForm.getColumnSize()) * 40 + 10;
        } catch (Exception e) {
            height = 0;
        }

        queryForm.setHeight((int) height);

        queryForm.setFormItemList(queryFormItemArray);

    }

    @Override
    @Transactional(readOnly = true)
    public Object getDefaultData(Form form) throws ServiceException {
        DictTSetQuerydPO queryPO = getEntryOuterService().getDataQuerydList(form.getTableID());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tableID", form.getTableID());

        if (null == queryPO || null == queryPO.getQuerydDet() || queryPO.getQuerydDet().size() == 0) {
            return map;
        }

        List<DictTSetQuerydDetPO> list = entryMapper.selectQuerydDet(map);
        if (list != null && list.size() > 0) {
            for (DictTSetQuerydDetPO dictTSetQuerydDetPO : list) {
                map.put(OPERATORHEAD + dictTSetQuerydDetPO.getDbColumnName(), dictTSetQuerydDetPO.getOperator());
                map.put(dictTSetQuerydDetPO.getDbColumnName(), dictTSetQuerydDetPO.getDefvalue());
                if ("1".equals(dictTSetQuerydDetPO.getIsRef())) {// 如果是引用列，拼sname_xxx
                    map.put(REFHEAD + dictTSetQuerydDetPO.getDbColumnName(), dictTSetQuerydDetPO
                            .getSNAME_defvalue());
                }
            }
        }
        return map;
    }
}

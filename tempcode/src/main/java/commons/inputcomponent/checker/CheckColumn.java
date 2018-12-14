
package commons.inputcomponent.checker;

import org.apache.commons.lang3.StringUtils;

import com.longtu.demo.logger.Logger;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.utils.dao.IUtilDAO;
import com.tjhq.commons.utils.ApplicationContextUtil;

/**
 * @ClassName: CheckColumn
 * @Description: Description of this class
 * @author: CAOK 2015-12-25 上午10:18:07
 */

public class CheckColumn {

    static IUtilDAO utilDAO;

    static CheckColumn checkColumn = null;

    Logger logger = Logger.getLogger(CheckColumn.class);

    private CheckColumn() {
        utilDAO = (IUtilDAO) ApplicationContextUtil.getBean("IUtilDAO");
    }

    public static synchronized CheckColumn getInstance() {
        if (checkColumn == null) {
            checkColumn = new CheckColumn();
        }

        return checkColumn;
    }

    public void check(Column column) throws ServiceException {
        checkRefTable(column);
        checkColDefault(column);
    }

    private void checkRefTable(Column column) throws ServiceException {
        if (column.getRefTableDBName() == null || column.getRefTableDBName().trim().length() == 0) {
            return;
        }

        String sql = "SELECT 1 FROM " + column.getRefTableDBName() + " WHERE ROWNUM = 1";
        logger.debug("校验引用代码表 : " + sql);
        try {
            utilDAO.queryForList(sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00210, column.getAlias() + "列引用代码表 ["
                    + column.getRefTableDBName() + "]无效，请检查同义词!", false);
        }
    }

    private void checkColDefault(Column column) throws ServiceException {

        if (column.getDefaultValue() == null || column.getDefaultValue().trim().length() == 0) {
            return;
        }

        StringBuffer defaultValueSql = new StringBuffer();

        defaultValueSql.append("SELECT   '").append(column.getColumnDBName()).append("'  AS COLUMNNAME, ");
        defaultValueSql.append(" ").append(column.getDefaultValue()).append(" || '' AS DEFAULTVALUE ,");

        if (StringUtils.isEmpty(column.getRefTableDBName())) {

            defaultValueSql.append(" ").append(column.getDefaultValue()).append(" || ''  AS DEFAULTSHOWVALUE ");

        } else {// 如果是引用
            defaultValueSql.append(" (SELECT NAME FROM ").append(column.getRefTableDBName()).append(" WHERE GUID=")
                    .append(column.getDefaultValue()).append(") AS DEFAULTSHOWVALUE ");
        }

        defaultValueSql.append(" FROM DUAL   ");
        logger.debug("校验引用代码表 : " + defaultValueSql);
        try {
            utilDAO.queryForList(defaultValueSql.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00211, column.getAlias() + "列默认值设置错误!", false);
        }
    }

}

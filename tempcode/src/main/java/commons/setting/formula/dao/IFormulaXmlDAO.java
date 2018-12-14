/**
 * @Title: IFormulaXmlDAO.java
 * @Copyright (C) 2016 太极华青
 * @Description: 公式导入导出xml
 * @Revision 1.0 2016-3-29  ZhengQ
 */
package commons.setting.formula.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.exception.ServiceException;

/**
 * @ClassName: IFormulaXmlDAO
 * @Description: 公式导入导出xml
 * @author: ZhengQ 2016-3-29 上午11:18:57
 */
public interface IFormulaXmlDAO extends SuperMapper{
        /**.
         * 获取公式表FORMULA_T_FORMULADEF表的所有列
         * @return List<Map<String, String>>
         * @throws
         */
        List<Map<String, String>> getAllFormulaCols(Map<String, Object> map);
        /**.
         * 查询当前时间
         * @return String
         */
         String getSysDate();
         /**.
          * 判断表是否存在
         * @param tableID 表ID
         * @return Integer
         * @throws ServiceException
         * @throws
         */
         Integer tableCount(String tableID)throws ServiceException;
         /**.
          * 判断公式是否存在
         * @param String
         * @return Integer
         * @throws
         */
         Integer formulaCountByFormulaID(String formulaID);
         /**.
          * 插入公式
         * @param map 参数
         * @throws
         */
        void insertFormulaByXml(Map<String, Object> map);
         /**.
          * 修改公式
         * @param map 参数
         * @throws
         */
        void updateFormulaByXml(Map<String, Object> map);
        /**.
         * 清除已经删除的数据
         */
         void clearFormulaData();
         /**.
          * 获取浮动表或固定行列表中的一行
         * @param dbTableName 表名
         * @param templateID 模板ID
         * @return Map
         * @throws
         */
        Map<String, String> getFloatRowByTemplateid(@Param("dbTableName") String dbTableName, @Param("templateID") String templateID);
}

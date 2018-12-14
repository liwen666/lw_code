
package commons.setting.input.service;

import java.util.List;
import java.util.Map;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.floatgrid.service.IFloatGridService;
import com.tjhq.commons.setting.input.po.DictTSetFddefPO;

public interface IFloatService extends IFloatGridService {

    /**
     * 根据表ID，where条件查询数据
     * @param tableID
     * @param whereCondition
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getFloatData(String tableID, String whereCondition) throws ServiceException;

    /**
     * 更新单元格权限 参数： floatData 数据库中数据 colOrder 排序列 tableID 表 dbTableName 物理表名 updateIsLeaf 处理后的是否末级
     */
    public void updateCellSecu(List<Map<String, Object>> floatData, String colOrder, String tableID,
                               String dbTableName, List<Map<String, Object>> updateIsLeaf);

    /**
     * 根据表ID，where条件查询数据 初始化表
     * @throws Exception
     */
    public void initFloatData(String tableID, String opt) throws ServiceException;
    /**.删除全部模板数据
     * @param tableID
     * @throws ServiceException
     * @throws
     */
    public void deleteAllTemplateData(String tableID) throws ServiceException;

    /**
     * 根据表ID，获取浮动表 设置信息
     * @param tableID
     * @return
     */
    public Map<String, Object> getLvlNanmeCol_1(String tableID);

    /**
     * 保存 浮动表 整表设置
     * @param fddef
     * @param operator
     * @param dealType
     * @return
     */
    public String saveFloatWhole(DictTSetFddefPO fddef, String operator, String dealType);

    /**
     * 设置单元格 权限
     * @param dataKey 主键
     * @param cellSecu 行权限
     * @param cIndex 更新位置
     * @param cSecu 0,1
     * @return
     */
    public void saveCellSecu(String dataKey, String cellSecu, String cIndex, String cSecu, String tableID);

    /**
     * 刷新浮动表设置 一、将浮动表 <设置表DICT_T_SETFDDEF> 中 colOrder 列 更新 二、将浮动表中 CELLSECU 单元权限 根据colOrder列 调整顺序、删除、增加 等
     * @param tableID
     * @param dealType
     * @param floatData List<Object>
     * @return
     * @throws Exception
     */
    public String refreshFloatSet(String tableID, String dealType) throws ServiceException;

    /**
     * 根据TEMPLATEID获取浮动表信息，用于删除公式时修改单元格权限
     * @param TEMPLATEID
     */
    public List<Map<String, Object>> getFloatDataByTemplateID(String tableName, String templateID);

    /**
     * @param tableID
     * @return
     * @throws ServiceException
     * @throws
     */
    public Integer getResCount(String tableID) throws ServiceException;

    /**
     * .获取列
     * @param tableID
     * @param dealType
     * @return
     * @throws ServiceException
     * @throws
     */
    Object getFactorTree(String tableID, String dealType) throws ServiceException;

    /**
     * 处理 浮动表级次 、是否末级 返回方式： Map<String,Object> {10_levelno=1,10_isleaf=1,10_superid=F4C798851FE64D56E040A8C020032117}
     */
    public Map<String, Object> dataToLeveNo(List<Map<String, Object>> floatData, Map<String, Object> keyMap,
                                            String layerIndent, String tableID, String mark);

    /**
     * 处理 末级编码 及 单元格权限 返回方式： List<Map<String,Object>> [{FDCODE=10,ISLEAF=1,LEVELNO=1,SUPERID=F4C798851FE64D56E040A8C020032117}]
     */
    public List<Map<String, Object>> selIsLeaf(Map<String, Object> level, String colOrder, String mark) ;
    /** 判断 级次、末级、上级编码是否发生改变
     * @param floatData
     * @param updateIsLeaf
     * @return
     * @throws
     */
    public List<Map<String, Object>> isLeafChange(List<Map<String, Object>> floatData,
                                                  List<Map<String, Object>> updateIsLeaf);
    /**获取排序列
     * @param tableID
     * @param dealType
     * @return
     * @throws
     */
    public String getColOrder(String tableID, String dealType);
    /**处理每组 fdCodeToCols 对应列 的单元格权限
     * @param colOrder
     * @param subColOrder
     * @param cellSecu
     * @param newCellSecu
     * @return
     * @throws
     */
    public String cellSecuByCols(String colOrder, String subColOrder, String cellSecu, String newCellSecu);
    /**把公式list转为map
     * @param tableID
     * @return
     * @throws
     */
    public Map<String, String> getFormulaColumn(String tableID);
    /**获取浮动列的代码表数据
     * @param colID
     * @param levelno
     * @return
     * @throws ServiceException
     * @throws
     */
    public List<Map<String, Object>> getCodeTableData(String colID, int levelno) throws ServiceException;
    /**判断该表内是否已经有数据，如果有先删除再重新生成一遍
     * @param tableID
     * @param floatName
     * @throws ServiceException
     * @throws
     */
    public void deleteAllFloatData(String tableID, String floatName) throws ServiceException;
    boolean getCodeCount(String tableID) throws ServiceException;
}

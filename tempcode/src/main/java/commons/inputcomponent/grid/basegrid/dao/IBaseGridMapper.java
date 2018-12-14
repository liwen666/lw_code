/**
 * @Title: IBaseGridMapper.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用录入底层类接口
 * @Revision 6.0 2015-11-23  CAOK
 */
package commons.inputcomponent.grid.basegrid.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;

/**
 * @ClassName: IBaseGridMapper
 * @Description: 通用录入底层类接口
 * @author: CAOK 2015-11-24 下午02:19:50
 */

public interface IBaseGridMapper  extends SuperMapper {

    /**.
     * 获取合计行数据
     * @param map 查询数据的参数
     * @return 合计行数据
     * @throws Exception 查询异常
     */
    Map<String, Object> getSumData(Map<String, Object> map)throws Exception;
    /**.
     * 获取满足条件的总条数
     * @param map 查询参数
     * @return 数据条数
     * @throws Exception 查询异常
     */
    int getRecordsCount(Map<String, Object> map)throws Exception;
    /**.
     * 获取表格数据
     * @param map 查询参数
     * @return 数据集合
     * @throws Exception 查询异常
     */
    List<Map<String, Object>> getGridData(Map<String, Object> map)throws Exception;

}

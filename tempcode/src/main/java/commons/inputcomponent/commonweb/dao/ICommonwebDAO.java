/**
 * @Title: IBaseGridService.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用录入底层类接口
 * @Revision 6.0 2015-11-26  CAOK
 */

package commons.inputcomponent.commonweb.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;

/**
 * @ClassName: ICommonwebMapper
 * @Description: 通用录入底层类接口
 * @author: CAOK 2015-11-26 下午02:20:23
 */

public interface ICommonwebDAO extends SuperMapper {

    /**
     * . 查询关联列数据
     * @param map 参数
     * @return 结果
     * @throws Exception 查询异常
     */
    List<Map<String, Object>> queryRelData(Map<String, Object> map) throws Exception;

    /**
     * . 查询关联列数据
     * @param map 参数
     * @return 结果
     * @throws Exception 查询异常
     */
    List<Map<String, Object>> queryListData(Map<String, Object> map) throws Exception;

    /**
     * . 通用查询接口，指定sql语句查询。
     * @param sql 查询SQL
     * @return 结果
     * @throws Exception 查询异常
     */
    List<Map<String, Object>> queryForList(Map<String, Object> sql) throws Exception;

}

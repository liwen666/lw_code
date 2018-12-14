/**
 * @Title: IExceptionManageDAO.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用设置功能数据服务层接口
 * @Revision 6.0 2015-11-23  ZhengQ
 */

package commons.setting.exception.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;

/**
 * @ClassName: IExceptionManageDAO
 * @Description: 异常管理数据服务层接口
 * @author: ZhengQ 2015-11-26 上午11:04:45
 */

public interface IExceptionManageDAO extends SuperMapper {
    /**
     * . 根据code查询异常管理表数据
     * @param code 异常代码
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> getExceptionDataByCode(@Param("code") String code);

    /**
     * . 保存异常到异常管理表
     * @param map 异常信息Map
     */
    void saveData(Map<String, Object> map);

    /**
     * . 修改异常管理
     * @param map 要修改的信息
     */
    void updateData(Map<String, Object> map);

    /**.
     * 删除选中异常
     * @param lists 选中异常信息
     */
    void deleteData(List<Map<String, Object>> lists);
}

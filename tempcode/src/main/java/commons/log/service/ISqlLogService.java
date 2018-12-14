/**
 * @Title: ISqlLogService.java
 * @Copyright (C) 2015 太极华青
 * @Description:
 * @Revision 1.0 2015-12-24  CAOK
 */
 

package commons.log.service;

import java.util.List;

/**
 * @ClassName: ISqlLogService
 * @Description: Description of this class
 * @author: CAOK 2015-12-24 下午04:17:28
 */

public interface ISqlLogService extends ILogService {
    
    /**. 保存sql日志
     * @param sql 操作sql
     * @param params 查询参数
     * @param usedTime 使用时间
     * @param resultset 结果集
     * @throws
     */
    @SuppressWarnings("unchecked")
    void log(String sql, Object params, long usedTime, List resultset);
}

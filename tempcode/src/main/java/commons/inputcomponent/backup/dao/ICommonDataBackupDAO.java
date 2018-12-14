/**
 * @Title: ICommonDataBackupDAO.java
 * @Copyright (C) 2016 太极华青
 * @Description:
 * @Revision 1.0 2016-1-7  CAOK
 */
 

package commons.inputcomponent.backup.dao;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;

/**
 * @ClassName: ICommonDataBackupDAO
 * @Description: Description of this class
 * @author: CAOK 2016-1-7 上午09:27:27
 */

public interface ICommonDataBackupDAO extends SuperMapper {

    /**.
     * 更新备份版本
     * @param backDbName 备份表名
     */
    void updateBackDataVersion(@Param("bakDbName") String backDbName, @Param("bakWhere") String bakWhere, @Param("bakType") String bakType);
    
    /**.
     * 备份数据
     * @param backupSql 备份SQL
     */
    void backupData(@Param("backupSql") String backupSql);
}

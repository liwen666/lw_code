package commons.secu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.secu.po.SecuTRowPO;
/**
 * 行权限数据库访问类
 * @version 1.0
 * @CreateDate 2014/1/13
 * @author zushuxin
 *
 */
public interface SecuTRowMapper  extends SuperMapper {

	List<SecuTRowPO> getRowLimitListByTableId(Map<String, String> param);

	void saveTableRowLimit(Map<String, String> param);

	void updateTableRowLimit(Map<String, String> param);

	void cancelTableRowLimitLimit(Map<String, String> param);

	List<SecuTRowPO> getRole2TableRowLimitLists(Map param);

	void deleteRowSecuLimit(Map param);

	String getTableRowSecuSql(Map<String, String> paramMap); 
	
	//--Add new method of 2016-08-31
	int getUserRoleCount(@Param("userID") String userID);
	
    List<String> getTableRowSecu(@Param("userID") String userID, @Param("tableID") String tableID,
                                 @Param("baseSecu") String baseSecu);
	
}

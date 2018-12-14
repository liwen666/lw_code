package commons.secu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.secu.po.SecuTColPO;
/**
 * 列权限数据库访问类
 * @version 1.0
 * @CreateDate 2014/1/13
 * @author zushuxin
 *
 */
public interface SecuTColMapper  extends SuperMapper {
	/**
	 * 通过角色IDs获得对这个table的列限制
	 * @param params
	 * @return List<SecuTCol> 列权限集合
	 */
	List<SecuTColPO> getTableColsAuthByRoles(Map<String, Object> params);

	List<SecuTColPO> getColLimitListByTableId(Map<String, String> param);

	void deleteSecuTColData(Map deleteData);

	void insertSecuTColData(Map<String, String> editData);

	void deleteColSecuLimit(Map param);

	List<Map<String, String>> getTableColsAuthWithDataBaseCache(
            Map<String, String> param);
	
	//--Add new method of 2016-08-31
	List<SecuTColPO> getTableColSecu(@Param("userID") String userID, @Param("tableID") String tableID);
	
}

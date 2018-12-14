package commons.secu.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.secu.po.SecuTColPO;
import com.tjhq.commons.secu.po.SecuTLimitcolPO;
import com.tjhq.commons.secu.po.SecuTLimittabPO;
/**
 * 输入限制列权限数据库访问类
 * @version 1.0
 * @CreateDate 2014/1/13
 * @author zushuxin
 *
 */
public interface SecuTLimitcolMapper  extends SuperMapper {

	void deleteColInputLimit(String tableId);

	List<Map<String,String>> getTableCellLimits(String tableId);

	SecuTLimitcolPO queryInputColLimit(Map param);

	void saveColLimitData(Map param);

	void updateColLimitData(Map param);

	void clearLimitWindow(Map param);
	
	List<Map<String,String>> getRowCellSecuBy(Map param);

	List<String> getBeAffectedColumnListForTableCellLimit(String tableId);
}

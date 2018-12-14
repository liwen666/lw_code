package commons.secu.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.secu.po.SecuTLimittabPO;
/**
 * 输入限制表权限数据库访问类
 * @version 1.0
 * @CreateDate 2014/1/13
 * @author zushuxin
 *
 */
public interface SecuTLimittabMapper  extends SuperMapper {

	List<Map<String, String>> getInputLimitItemList(Map param);
	List<SecuTLimittabPO> getInputLimitItemsList(Map param);
	void deleteAcctItemTreeData(Map param);

	void updateSecuTLimittabData(Map<String, String> editData);

	void insertSecuTLimittabData(Map<String, String> addData);

	void deleteTableInputLimit(String tableId);
	
}

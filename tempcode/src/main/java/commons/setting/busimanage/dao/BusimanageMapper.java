package commons.setting.busimanage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;

public interface BusimanageMapper extends SuperMapper {
	List<Map<String, Object>> getDataList(Map<String, Object> params);
    int getDataListCount(Map<String, Object> params);
	void saveData(Map<String, Object> map);
    
	void updateData(Map<String, Object> map);

	void delData(Map<String, Object> map);
}

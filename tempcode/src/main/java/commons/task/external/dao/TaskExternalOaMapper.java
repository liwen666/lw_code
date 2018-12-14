package commons.task.external.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.task.po.TaskPO;

public interface TaskExternalOaMapper extends SuperMapper {
	
	 Map<String,String> getDistrictInfoByAgencyId(@Param("agencyId") String agencyId);
	 
	 List<String> getDistrictTreeByAgencyId(@Param("agencyId") String agencyId);
	 
	 List<TaskPO> getTaskInfosByDistrictIdList(@Param("districtList") List<String> districtList, @Param("appId") String appId);
	 
	 List<TaskPO> getTaskInfosByAgencyId(@Param("agencyId") String agencyId, @Param("appId") String appId);
}

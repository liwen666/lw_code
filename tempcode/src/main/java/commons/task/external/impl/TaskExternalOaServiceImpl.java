package commons.task.external.impl;

import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.task.external.ITaskExternalOaService;
import com.tjhq.commons.task.external.dao.TaskExternalOaMapper;
import com.tjhq.commons.task.po.TaskPO;

@Service
@Transactional(readOnly=true)
public class TaskExternalOaServiceImpl implements ITaskExternalOaService{

	@Resource
	private TaskExternalOaMapper taskExternalOaMapper;
	
	@Override
	public List<TaskPO> getTaskInfosByUserAppId(String userId,
			String appId) throws Exception {
		String agencyId = SecureUtil.getCurrentUser().getAdmdiv();
		Map<String,String> agencyInfo = taskExternalOaMapper.getDistrictInfoByAgencyId(agencyId);
		String code = agencyInfo.get("CODE");
		String suffix = code.substring(code.length()-3, code.length()-1);
		if(suffix.equals("00")){
			//如果是本级，取superguid
			String superguid = agencyInfo.get("SUPERGUID");
			List<String> districtList = taskExternalOaMapper.getDistrictTreeByAgencyId(superguid);
			if(districtList == null || districtList.size() == 0){
				throw new Exception("未找到地区Id在code_t_district表中！");
			}else{
				return taskExternalOaMapper.getTaskInfosByDistrictIdList(districtList, appId);
			}
		}else{
			return taskExternalOaMapper.getTaskInfosByAgencyId(agencyId, appId);
		}
	}


	
	
}

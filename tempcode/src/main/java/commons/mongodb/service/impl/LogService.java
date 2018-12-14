package commons.mongodb.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.tjhq.commons.mongodb.dao.IMongoDbDAO;
import com.tjhq.commons.mongodb.po.MongoConditionDTO;
import com.tjhq.commons.mongodb.po.MongoConditionType;
import com.tjhq.commons.mongodb.po.MongoLogDTO;
import com.tjhq.commons.mongodb.po.MongoOrderDTO;
import com.tjhq.commons.mongodb.po.MongoPaginationDTO;
import com.tjhq.commons.mongodb.service.ILogService;
import com.tjhq.commons.mongodb.service.IMongoDbConnectService;
import com.tjhq.commons.utils.SystemProperty;
import com.tjhq.commons.utils.UserUtil;

@Service
public class LogService implements ILogService {

	private Logger logger = Logger.getLogger(LogService.class);
	@Resource
	private IMongoDbConnectService mongoDbConnectService;

	@Resource
	private IMongoDbDAO mongoDbDAO;
	
	private String appID = SystemProperty.getSystemAppID();

	public void saveData(MongoLogDTO logdto) {
		try {
			MongoTemplate mongoTemplate = mongoDbConnectService
					.mongoDbConnect();
			if (mongoTemplate == null) {
				logger.info("Mongodb 连接失败！");
				return;
			}

			logdto.setAppID(appID);
			// 时间处理
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str = sdf.format(new Date());
			logdto.setDataTime(str);
			// 用于模糊查询
			StringBuffer condation = new StringBuffer("");
			Map<String, Object> map = logdto.getMap();
			for (Object value : map.values()) {
				condation.append(value + "|");
			}
			logdto.setCondation(condation.toString());
			mongoTemplate.insert(logdto, appID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MongoPaginationDTO queryData(List<MongoConditionDTO> conditionList, MongoPaginationDTO pageCondition, List<MongoOrderDTO> orderList) {
		try {
			MongoTemplate mongoTemplate = mongoDbConnectService
					.mongoDbConnect();
			if (mongoTemplate == null) {
				logger.error("Mongodb 连接失败！");
				return null;
			}
			// 按条件查询
			Criteria criteria = new Criteria();
			for (MongoConditionDTO condition : conditionList) {
				//模糊查询
				if (condition.getType().equals(MongoConditionType.FUZZY)) {
					
					Pattern queryPattern = Pattern.compile(condition.getValue());
					criteria.and(condition.getKey()).regex(queryPattern);
				}
				if (condition.getType().equals(MongoConditionType.COMPLETE)) {
					criteria.and(condition.getKey()).is(condition.getValue());
				}
			}
			Query query = new Query();
			query.addCriteria(criteria);
			
			// 分页
			int count = (int) mongoTemplate.count(query, this.appID);
			query.skip(pageCondition.getCurrPage());
			query.limit(pageCondition.getRows());
			// 排序
			for (MongoOrderDTO order : orderList) {
				query.with(new Sort(order.getOrder(), order.getKey()));
			}
			List<MongoLogDTO> list = mongoTemplate.find(query,
					MongoLogDTO.class, this.appID);
			pageCondition.setTotalRows(count);
			pageCondition.setResult(list);
			return pageCondition;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public void saveDataAll(List<MongoLogDTO> list, String taskID) {
		try {
			MongoTemplate mongoTemplate = mongoDbConnectService.mongoDbConnect();
			if (mongoTemplate == null) {
				logger.error("Mongodb 连接失败！");
				return;
			}
			String taskName = null;
			if (list.size() > 0 && taskID != null) {
				taskName = mongoDbDAO.getTaskNameByID(taskID);
			} else {
				return;
			}
			String userName = UserUtil.getUserInfo().getName();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str = sdf.format(new Date());
			StringBuffer condation = new StringBuffer("");
			for (MongoLogDTO mongoLogDTO : list) {
				mongoLogDTO.setDataTime(str);
				mongoLogDTO.setAppID(appID);
				mongoLogDTO.setUserName(userName);
				mongoLogDTO.setTaskName(taskName);
				Map<String, Object> map = mongoLogDTO.getMap();
				for (Object value : map.values()) {
					condation.append(value + "|");
				}
				mongoLogDTO.setCondation(condation.toString());
				condation = new StringBuffer("");
			}
			mongoTemplate.insert(list, this.appID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveData(List<Map<String, Object>> list, String tableID, String function, String taskID, String opration) {
		try {
			MongoTemplate mongoTemplate = mongoDbConnectService.mongoDbConnect();
			if (mongoTemplate == null) {
				logger.error("Mongodb 连接失败！");
				return;
			}
			String taskName = null;
			if ( taskID != null) {
				taskName = mongoDbDAO.getTaskNameByID(taskID);
			} else {
				return;
			}
			String userName = UserUtil.getUserInfo().getName();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dataTime = sdf.format(new Date());
			StringBuffer condation = new StringBuffer("");
			List<MongoLogDTO> resList = new ArrayList<MongoLogDTO>();
			for (Map<String, Object> map : list) {
				MongoLogDTO mongologdto=new MongoLogDTO();
				mongologdto.setAppID(appID);
				mongologdto.setDataTime(dataTime);
				mongologdto.setFunction(function);
				mongologdto.setMap(map);
				mongologdto.setOpration(opration);
				mongologdto.setTableID(tableID);
				mongologdto.setTaskID(taskID);
				mongologdto.setTaskName(taskName);
				mongologdto.setUserName(userName);
				for (Object value : map.values()) {
					condation.append(value + "|");
				}
				mongologdto.setCondation(condation.toString());
				resList.add(mongologdto);
				condation = new StringBuffer("");
			}
			logger.error("mongo number:" + resList.size());
			mongoTemplate.insert(resList, this.appID);
			resList = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

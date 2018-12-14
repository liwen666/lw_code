package commons.mongodb.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.tjhq.commons.mongodb.dao.IMongoDbDAO;
import com.tjhq.commons.mongodb.po.MongoDBPO;
import com.tjhq.commons.mongodb.service.IMongoDbConnectService;
import com.tjhq.commons.utils.SystemProperty;
@Service
public class MongoDbConnectService implements IMongoDbConnectService {
	private Logger logger = Logger.getLogger(MongoDbConnectService.class);
	@Resource
	private IMongoDbDAO mongoDbMapper;

	public IMongoDbDAO getMongoDbMapper() {
		return mongoDbMapper;
	}

	public void setMongoDbMapper(IMongoDbDAO mongoDbMapper) {
		this.mongoDbMapper = mongoDbMapper;
	}

	public static MongoClient mongo;
	
	public static MongoTemplate mongoTemplate;
	
	@PostConstruct
	public void initExceptionMessage() {
		try {
			List<MongoDBPO> resultList = mongoDbMapper.getMongoDB(SystemProperty.getSystemAppID());
			if (resultList.size() == 0) {
				logger.error("Mongodb 缺少配置信息！");
			} else {
				MongoDBPO mongodbpo = resultList.get(0);
				mongo = new MongoClient(mongodbpo.getIpAddress(), mongodbpo.getPort());
				mongo.getAllAddress();
				logger.error("Mongodb 连接成功！");
				mongoTemplate = new MongoTemplate(mongo, mongodbpo.getdBase());
				mongoDbConnect();
			}

		} catch (Exception e) {
			logger.error("Mongodb 连接失败！");
		}

	}
	public MongoTemplate mongoDbConnect() {
		try {
			if (mongoTemplate == null) {
				List<MongoDBPO> resultList = mongoDbMapper.getMongoDB(SystemProperty.getSystemAppID());
				if (resultList.size() == 0) {
					logger.info("Mongodb 缺少配置信息！");
					return null;
				} else {
					MongoDBPO mongodbpo = resultList.get(0);
					/*	
				 	List<ServerAddress> addresses = new ArrayList<ServerAddress>();
					ServerAddress address1 = new ServerAddress("192.168.6.164",27001);
					ServerAddress address2 = new ServerAddress("192.168.6.164",27002);
					ServerAddress address3 = new ServerAddress("192.168.6.164",27003);
					addresses.add(address1);    
					addresses.add(address2);   
					addresses.add(address3);
					*/
					mongo = new MongoClient(mongodbpo.getIpAddress(), mongodbpo.getPort());
					mongoTemplate = new MongoTemplate(mongo, mongodbpo.getdBase());
				}
			}
			mongo.getAllAddress();
			return mongoTemplate;
		} catch (Exception e) {
			logger.error("Mongodb 连接失败！");
			mongo = null;
			mongoTemplate = null;
			return null;
		}

	}

}

package commons.mongodb.service;

import org.springframework.data.mongodb.core.MongoTemplate;

public interface IMongoDbConnectService {

	public MongoTemplate mongoDbConnect();
}

package commons.mongodb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.mongodb.po.MongoDBPO;
import com.tjhq.commons.utils.SystemProperty;
@Repository("mongoDbMapper")
public interface IMongoDbDAO extends SuperMapper{
	
	
	public List<MongoDBPO> getMongoDB(String appid);
	public String getTaskNameByID(String taskID);

}

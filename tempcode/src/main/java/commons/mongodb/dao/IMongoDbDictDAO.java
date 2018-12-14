package commons.mongodb.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.mongodb.po.MongoDBPO;

public interface IMongoDbDictDAO extends SuperMapper{
	public List<MongoDBPO> getAllMongoDbDict();


	public void addMongoDbDict(Map<String, Object> map);

	public void delMongoDbDict(Map<String, Object> map);

	public void updateMongoDbDict(Map<String, Object> map);
}

package commons.dict.dao;

import java.util.List;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dict.external.po.PubTPartitionTablenamePO;

public interface PubTPartitionTablenameMapper extends SuperMapper{

	/**
	 * 全部
	 * @return
	 */
	public List<PubTPartitionTablenamePO> getAllPubTPartitionTablename();
}

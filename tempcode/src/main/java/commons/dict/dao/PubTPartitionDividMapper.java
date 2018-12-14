package commons.dict.dao;

import java.util.List;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.dict.external.po.PubTPartitionDividPO;

public interface PubTPartitionDividMapper extends SuperMapper{

	/**
	 * 全部
	 * @return
	 */
	public List<PubTPartitionDividPO> getAllPubTPartitionDivid();
	
	/**
	 * group by year
	 *  
	 * @return
	 */
	public List<PubTPartitionDividPO> getAllForYear();
	
	/**
	 * group by year
	 *  
	 * @return
	 */
	public List<PubTPartitionDividPO> getProvinceGroupByYear();
	
}

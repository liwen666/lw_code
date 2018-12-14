package commons.mongodb.service;



import java.util.List;
import java.util.Map;

import com.tjhq.commons.mongodb.po.MongoConditionDTO;
import com.tjhq.commons.mongodb.po.MongoLogDTO;
import com.tjhq.commons.mongodb.po.MongoOrderDTO;
import com.tjhq.commons.mongodb.po.MongoPaginationDTO;

public interface ILogService {
	
	/**
	 * 存储日志到mongodb 数据库 
	 * @param MongoLogDTO对象
	 * 
	 */
	public void saveData(MongoLogDTO logDTO);
	/**
	 * 查询日志
	 * @param List<MongoConditionDTO> 查询条件  ，MongoPaginationDTO 分页，List<MongoOrderDTO> 排序；
	 *
	 */
	public MongoPaginationDTO queryData(List<MongoConditionDTO> conditionList, MongoPaginationDTO pageCondition, List<MongoOrderDTO> orderList);

	/**
	 * 批量存储日志
	 * @param List<MongoLogDTO> 
	 *  MongoLogDTO 对象中 condation 和 dataTime 属性不需作处理  
	 *  
	 */
	public void saveDataAll(List<MongoLogDTO> list, String taskID);
	
	/**
	 * 批量存储日志 
	 * @param List<Map<String, Object>> 每条数据的列英文名称 和 对应值 
	 *         tableID 操作的表ID function进行了什么操作（可在常量类Constants.java里取） taskID 任务id 
	 *           opration 日志来源  
	 */         
	
	public void  saveData(List<Map<String, Object>> list, String tableID, String function, String taskID, String opration);
}

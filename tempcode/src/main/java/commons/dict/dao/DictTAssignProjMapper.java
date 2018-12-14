package commons.dict.dao;

import java.util.List;

import com.tjhq.commons.Mybatis.SuperMapper;
/**
 * 项目下发设置表
 * @author bizaiyi
 * Apr 23, 2015 2:48:59 PM
 * DictTAssignProjMapper.java
 */
public interface DictTAssignProjMapper extends SuperMapper{
	
	/**
	 * 添加
	 * tableIdList ：List<TableId>
	 */
	public void insertDictTAssignProj(List<String> tableIdList);
	
	/**
	 * 删除,清空表
	 */
	public void deleteDictTAssignProj();

	/**
	 * 查全表
	 * return : List<TableId>
	 */
	public List<String> selectDictTAssignProj();
}

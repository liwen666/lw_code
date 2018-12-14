package commons.inputcomponent.grid.floatgrid.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;

public interface IFloatGridMapper extends SuperMapper {

	List<Map<String, Object>> getFloatGridData(Map<String, Object> paramMap);

	String generateKey();  
	
	void getFormulaCell(Map<String, Object> paramMap);

}

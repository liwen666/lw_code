package commons.inputcomponent.grid.fixgrid.dao;

import java.util.List;
import java.util.Map;

import com.tjhq.commons.Mybatis.SuperMapper;

public interface IFixGridMapper extends SuperMapper {

	public List<Map<String, Object>> getTemplateSetData(Map<String, String> paramMap);

	public List<Map<String, Object>> getFixGridData(Map<String, Object> paramMap);

	public String getAllFdcode(Map<String, Object> paramMap); 
	
	public void getFormulaCell(Map<String, Object> paramMap);

}

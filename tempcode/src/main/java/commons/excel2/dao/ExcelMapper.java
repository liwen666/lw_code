package commons.excel2.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tjhq.commons.Mybatis.SuperMapper;

public interface ExcelMapper extends SuperMapper {
	public Map<String, String> queryAgency(String agencyID);
	public List<HashMap<String, Object>> selectExcelInfo(@Param("sql") String sql);
	public void updateSql(@Param("sql") String sql);
	public void callProcedure_p_calculateformula_0(Map map);
	public void callProcedure_p_clear_deleted_data(Map map);
	/**
     * 调取存储过程
     */
    public void callProcedure(Map<String, Object> map);
}
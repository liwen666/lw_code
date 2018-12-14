package commons.excel.dao;

import com.tjhq.commons.Mybatis.SuperMapper;
import com.tjhq.commons.excel.po.ExcelFilePO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ExcelFileMapper extends SuperMapper {
	public void updateXlsx(ExcelFilePO po);
	public void insertXlsx(ExcelFilePO po);
	public ExcelFilePO SelectXlsxOgnTpl(String tableid);
	public ExcelFilePO SelectXlsxNullTpl(String tableid);
	public ExcelFilePO SelectXlsxDatapart(String tableid);
	public ExcelFilePO SelectXlsxStylepart(String tableid);
	public HashMap SelectXlsmVBATpl(String tableid);
	public Map SelectXlsxSize(String tableid);
	public Map SelectDivName(String agencyID);
	public Map SelectModelTable(String tableid);
	public List selectExcelInfo(@Param("sql") String sql);
	public void updateSql(@Param("sql") String sql);
	public List SelectXlsxColMap(String tableid); 
	public void updateXlsmTpl(Map map);
	public void insertXlsmTpl(Map map);
	public void mergeXlsmTpl(Map map);
	public void callProcedure(Map map);
	public void callProcedure_p_clear_deleted_data(Map map);
}
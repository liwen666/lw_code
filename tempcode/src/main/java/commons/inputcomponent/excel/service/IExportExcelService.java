package commons.inputcomponent.excel.service;

import javax.servlet.http.HttpServletResponse;

import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
/**
 * @author sunyafei
 *
 */
public interface IExportExcelService {
	/**
	 * 导出数据
	 * @param obj pageInfo（数据）对象 
	 * @param commonGrid 表头对象
	 * @param response
	 * @throws Exception
	 */
	public void exportExcel(Object obj, CommonGrid commonGrid, HttpServletResponse response) throws Exception;
}

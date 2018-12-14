package commons.excel2.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.exception.ServiceException;

public interface IExcelService {
	/**
	 * 导出
	 * @return XSSFWorkbook
	 * @throws ServiceException
	 */
	public SXSSFWorkbook exportData(String grid, String appID, DictTModelPO dictTModel, String agencyID, String taskID, String noData, String agencyIsLeaf) throws ServiceException;
	/**
	 * 理Dict_T_Factorxlsx表的功能提取到service外与getData的事务分开，在查询数据慢时不锁表Dict_T_Factorxlsx
	 * @param tableID
	 * @param grid
	 * @throws ServiceException
	 */
	public void saveDictTFactorxlsx(String tableID, String grid, String agencyIsLeaf) throws ServiceException;
	
	/**
	 * 导入
	 * @param tableID
	 * @param bais
	 * @param agencyID
	 * @return
	 * @throws ServiceException
	 */
	public HashMap<String, Object> importData(HttpServletRequest request, InputStream inputStream) throws ServiceException;
	
	/**
	 * 刷新列间公式
	 * @param tableID
	 * @param agencyID
	 * @param appID
	 * @throws ServiceException
	 */
	
	public void refreshFormula_0(String tableID, String agencyID, String appID) throws ServiceException;
	
	/**
	 * 
	 */
	public void callProcedure(Map<String, Object> map, String mainTaskID) throws ServiceException;
}
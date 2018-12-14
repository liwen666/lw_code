package commons.excel2.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.tjhq.commons.exception.ServiceException;

public interface IExcelCodeService {
	
	/**
	 * 代码表导出
	 * @param 
	 * @return
	 * @throws ServiceException
	 */
	public SXSSFWorkbook exportData(HttpServletRequest request) throws ServiceException;
}
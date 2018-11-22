package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.exception.ServiceException;
import com.exception.service.ITaskInfoService;

public class UrlFilter implements Filter {
    
    private ITaskInfoService taskInfoService;

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException,
            ServletException {
        String finYear = request.getParameter("finYear");
        if ((null == finYear || finYear.equals("")) && null != request.getAttribute("finYear")) {
            finYear = (String)request.getAttribute("finYear");
        }
        String docID = request.getParameter("docID");
        String taskID = request.getParameter("taskID");
        if (null == finYear || finYear.equals("")) {
            try {
                taskInfoService = (ITaskInfoService)ApplicationContextUtil.getBean("taskInfoService");
                if (null != taskID) {
                    request.setAttribute("finYear", taskInfoService.getFinYearByTaskID(taskID));
                } else if (null != docID) {
                    request.setAttribute("finYear", taskInfoService.getFinYearByDocID(docID));
                } else {
                    request.setAttribute("finYear", null);
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("finYear", finYear);
        }
        filter.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}

package commons.setting.manage.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BZYTestServlet extends HttpServlet{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String batchConditions = request.getParameter("conditions");//post
        String sysId = request.getParameter("sysId");//get
        System.out.println("sysId==========="+sysId);
        System.out.println("batchConditions1==========="+batchConditions); 
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println(batchConditions);
    }
}

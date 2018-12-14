package commons.urlfilter;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

public class ScriptFilterServlet extends HttpServlet {
	private static final long serialVersionUID = 9219468169487210162L; 

	protected void service(HttpServletRequest request,HttpServletResponse response)throws ServletException{
		 String url=request.getParameter("url"); 
		 try { 
			//response.sendRedirect(basePath+url);   
			response.setHeader("Content-Encoding","gzip");   
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);      
			dispatcher.forward(request, response);   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  
		}

	} 
}
 
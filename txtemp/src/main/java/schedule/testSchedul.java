package schedule;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testSchedul {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("schedule/TimeConfig.xml");
	}
	  public static void execute() throws IOException
	    {
		  
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        System.out.println("do my job"+dateFormat.format(new Date()));
//	        Runtime.getRuntime().exec("cmd /c start E:/mbl/BusinessOffice/MoneDB/bin/bakup.bat");
	    }
//	  public static void systemout() throws IOException
//	  {
//		  System.out.println("method 2---");
//		  
//	  }
}

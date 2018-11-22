package h2.remokemem;   
  
import java.sql.Connection;   
import java.sql.DriverManager;   
import java.sql.ResultSet;   
import java.sql.SQLException;   
import java.sql.Statement;
import org.h2.tools.Server;   
  
public class H2Demo {   
    private Server server;   


    private String port = "8888";    
    private static String sourceURL1 = "jdbc:h2:tcp://127.0.0.1:8888/mem:h2db"; 
//    private static String sourceURL1 = "jdbc:h2:mem:h2db"; 
    private static String sourceURL2 = "jdbc:h2:tcp://127.0.0.1:8888/mem:h2db"; 

    private String user = "shorturl";   
    private String password = "123456";   
  
    public void startServer() {   
        try {   
            System.out.println("正在启动h2...");   
            server = Server.createTcpServer(   
                    new String[] {"-tcpAllowOthers", "-tcpPort", port }).start();   
        } catch (SQLException e) {   
            System.out.println("启动h2出错：" + e.toString());   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
            throw new RuntimeException(e);   
        }   
    }   
  
    public void stopServer() {   
        if (server != null) {   
            System.out.println("正在关闭h2...");   
            server.stop();   
            System.out.println("关闭成功.");   
        }   
    }   
  
    public void useH2() {   
        try {   
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(sourceURL1,user, password);   
            Statement stat = conn.createStatement();   
            // insert data   
            stat.execute("CREATE MEMORY Table TEST(NAME VARCHAR)");  
            stat.execute("INSERT INTO TEST VALUES('Hello World')");
            //stat.execute("delete mappedURL");   
  
            // use data   
            ResultSet result = stat.executeQuery("select name from test "); 
            int i = 1;   
            while (result.next()) {   
                System.out.println(i++ + ":" + result.getString("name"));   
            }   
            result.close();   
            stat.close();   
            conn.close();   
        } catch (Exception e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
        }   
    }   
  
    public void useH2i() {   
        try {   
            Class.forName("org.h2.Driver");     
            //Connection conn = DriverManager.getConnection("jdbc:h2:" + dbDir+";AUTO_SERVER=TRUE;MVCC=TRUE",user, password);  
            Connection conn = DriverManager.getConnection(sourceURL2,user, password);   
            Statement stat = conn.createStatement();   
            // use data   
            ResultSet result = stat.executeQuery("select name from test");
            int i = 1;   
            while (result.next()) {   
                System.out.println(i++ + ":" + result.getString("name"));   
            }   
            result.close();   
            stat.close();   
            conn.close();   
        } catch (Exception e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
        }   
    }   
  
    public static void main(String[] args) {   
        H2Demo h2 = new H2Demo();   
        h2.startServer();   
        h2.useH2();     
        h2.useH2i(); 
        h2.stopServer();   
        System.out.println("==END==");   
    }   
}  
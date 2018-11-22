package aspectj;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


//Dependency Injection
//Inverse of Control
public class UserServiceTest {

  public static void main(String[] args) {
	  ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("aspectj/springContextTest.xml");
      
      
      UserService service = (UserService)ctx.getBean("userService");
      System.out.println(service.getClass());
      service.add(new User());
      System.out.println("###");
      
      ctx.destroy();
}
       

}
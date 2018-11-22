package h2.remokemem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"application-context.xml"})
public class InitMethodJUnitTest {

    @Autowired
    private InitMethodTest initMethodTest;

    @Test
    public void  initMethodTest(){
    	 ApplicationContext context = new ClassPathXmlApplicationContext(
                 "h2/remokemem/application-context.xml");
    	 AbstractApplicationContext c = (AbstractApplicationContext) context;
    	 c.close();
    	 
    }
}
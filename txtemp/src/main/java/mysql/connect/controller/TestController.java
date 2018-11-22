package mysql.connect.controller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import transaction.service.TestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:transaction/applicationContext.xml")
@Controller
public class TestController {   
    @Autowired
    private TestService testService;
    //MySQL的数据库引擎必须是InnoDB，否则无法回滚
    @Test
    public void test(){
        testService.test();
    }


    public void test2(){
        testService.update();
    }

    public void test3(){
        testService.test3();
    }
    public static void main(String[] args) {
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("mysql/connect/application-context.xml"); 
    	System.out.println(ctx);
    	System.out.println(ctx.getBean("jdbcTemplate"));
    	
    	JdbcTemplate masterJdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
    	System.out.println(masterJdbcTemplate.queryForInt("select 1"));
    	masterJdbcTemplate.execute("INSERT INTO ACT_HQ_TEM_CATEGORY (ID, NAME, CATEGORY, PARENTID, DESCRIPTION, PROC_DEF_KEY_,ORDER_ID,DEPT_ID) VALUES (replace(uuid(), '-',''), '1', '1', '1', '1', '1', 1,'1')");
    	
	}
}
package test.testmanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.temp.code.sys.domain.AdmainDao;
import com.temp.code.sys.domain.UserInfDomain;
import com.temp.code.sys.service.AdmainQueryModel;

import testcode.resources.CustomerModel;
import testcode.resources.CustomerQueryModel;
import testcode.resources.ICustomerService;

@Service
public class Client {
	@Autowired
	private AdmainDao dao = null;
	
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("testcode/resources/applicationContext.xml");
		Client client = (Client)ctx.getBean("client");
		List<UserInfDomain> findDomainList = client.dao.findDomainList(new AdmainQueryModel());
		System.out.println(findDomainList);
		
		
	}
}

package restfull.demo;

import javax.jws.WebService;

@WebService
public interface CXFDemo {
	public String sayHello(String foo);
}

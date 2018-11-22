package restfull.demo.rs;

import java.util.Date;

import org.springframework.stereotype.Service;


@Service("trackerService")
public class TrackerServiceImpl implements TrackerService {

    public Person get(String id) {
        // TODO Auto-generated method stub
    	Person p = new Person();
        return p;
    }
}
package transaction.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import transaction.dao.TestSlaveDao;

@Repository
public class TestSlaveDaoImpl implements TestSlaveDao{

    @Resource(name="slaveJdbcTemplate")
    JdbcTemplate slaveJdbcTemplate;
    @Override
    public String slave() {
        slaveJdbcTemplate.execute("insert into  salver (name) values('salver')");            
        return "success";
    }   

}
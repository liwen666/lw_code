package common.exception.core.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hq.bpmn.common.Mybatis.SuperMapper;
import common.exception.core.message.po.ExceptionInfoPO;


public interface IExceptionDAO extends SuperMapper {
	
	public List<ExceptionInfoPO> loadException();
	
	public ExceptionInfoPO loadExceptionByCode(@Param("code")String code);
}

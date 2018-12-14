package commons.dbbak.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dbbak.dao.IDataBaseBakMapper;
import com.tjhq.commons.dbbak.po.BackupInfoPO;
import com.tjhq.commons.dbbak.po.DataBaseFilePO;
import com.tjhq.commons.dbbak.service.IDataBaseBakService;

@Service
@Transactional(readOnly = true)
public class DataBaseBakService implements IDataBaseBakService {

	@Resource
	private IDataBaseBakMapper dataBaseDAO;
	
	public IDataBaseBakMapper getDataBaseDAO() {
		return dataBaseDAO;
	}

	public void setDataBaseDAO(IDataBaseBakMapper dataBaseDAO) {
		this.dataBaseDAO = dataBaseDAO;
	}

	@Override
	public void backUpDataBase() {
		getDataBaseDAO().backUpDataBase();
	}
	
	@Override
	public List<BackupInfoPO> getDataBaseBakInfoList() {
		return getDataBaseDAO().getDataBaseBakInfoList();
	}

	@Override
	public DataBaseFilePO getDataBaseBakFile(String orderID, String fileID) {
		return getDataBaseDAO().getDataBaseBakFile(orderID, fileID);
	}

	@Override
	public List<Map<String, Object>> getDataBaseBakList(String orderID) {
		return getDataBaseDAO().getDataBaseBakList(orderID);
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String deleteBackup(String orderID) {
		try {
			getDataBaseDAO().deleteBackup(orderID);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	}

}

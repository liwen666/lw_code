package commons.setting.billdef.service.impl;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.po.TreePO;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.setting.billdef.dao.IBillDefTypeMapper;
import com.tjhq.commons.setting.billdef.service.IBillDefTypeService;

@Service
@Transactional(readOnly=true)
public class BillDefTypeService implements IBillDefTypeService {
	@Resource
	private IBillDefTypeMapper billDefTypeMapper;
	@Resource
	private ISettingGridService settingGridService;
	
	@Override
	public Object getTableDefined(String appId) throws ServiceException {
		Table table = new Table();
		table.setAppID(appId);
		table.setTableName("记账定义表");
		table.setTableID("DICT_T_BILLDEF");
		table.setTableDBName("DICT_T_BILLDEF");
		table.setColumnList(setColumn());
		UserDTO user = SecureUtil.getCurrentUser();
		settingGridService.initStructure(table, user.getGuid());
		return table;
	}
	
	private List<Column> setColumn(){
		List<Column> cols = new ArrayList<Column>();
		//guid
		Column guid=new Column();
		guid.setColumnID("GUID");
		guid.setColumnName("GUID");
		guid.setColumnDBName("GUID");
		guid.setDataLength(32);
		guid.setKey(true);
		guid.setVisible(false);
		
		//BILLTYPE
		Column billType=new Column();
		billType.setColumnID("BILLTYPE");
		billType.setColumnName("BILLTYPE");
		billType.setColumnDBName("BILLTYPE");
		billType.setDataLength(32);
		billType.setKey(true);
		billType.setVisible(false);
		
		//DEFID
		Column billDefId=new Column();
		billDefId.setColumnID("DEFID");
		billDefId.setColumnName("DEFID");
		billDefId.setColumnDBName("DEFID");
		billDefId.setAlias("记账定义名称");
		billDefId.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		billDefId.setShowType(ShowType.SHOW_TYPE_HTML);
		billDefId.setDataLength(200);
		billDefId.setWidth(300);
		
		//BILLTYPENAME
		Column billTypeName=new Column();
		billTypeName.setColumnID("BILLTYPENAME");
		billTypeName.setColumnName("BILLTYPENAME");
		billTypeName.setColumnDBName("BILLTYPENAME");
		billTypeName.setAlias("记账类型名称");
		billTypeName.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		billTypeName.setShowType(ShowType.SHOW_TYPE_HTML);
		billTypeName.setDataLength(200);
		billTypeName.setWidth(300);
		billTypeName.setReadOnly(true);
		
		//BUSIKEY
		Column busiKey=new Column();
		busiKey.setColumnID("BUSIKEY");
		busiKey.setColumnName("BUSIKEY");
		busiKey.setColumnDBName("BUSIKEY");
		busiKey.setAlias("业务主键");
		busiKey.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		busiKey.setShowType(ShowType.SHOW_TYPE_HTML);
		busiKey.setDataLength(200);
		busiKey.setWidth(300);
		
		//REMARK
		Column remark=new Column();
		remark.setColumnID("REMARK");
		remark.setColumnName("REMARK");
		remark.setColumnDBName("REMARK");
		remark.setAlias("记账类型描述");
		remark.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		remark.setShowType(ShowType.SHOW_TYPE_HTML);
		remark.setDataLength(200);
		remark.setWidth(300);
		
		cols.add(billTypeName);
		cols.add(billDefId);
		cols.add(busiKey);
		cols.add(remark);
		cols.add(billType);
		cols.add(guid);
		
		return cols;
	}
	
	@Override
	public Object getTableData(Grid data) throws ServiceException {
		try {
			List<Map<String, Object>> list = billDefTypeMapper.getBillTypeList(data.getExtProperties());
			settingGridService.transferGridData(list, data.getPageInfo());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_1H202, e.getCause().getMessage(), false);
		}
		return data.getPageInfo();
	}
	
	@Override
	public void saveData(Grid data) throws ServiceException {
		if(data.getUpdateValues().size()>0){
			for(Map<String, Object> po : data.getUpdateValues()){
				try {
					billDefTypeMapper.updateData(po);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServiceException(ExceptionCode.ERR_00000, e.getCause().getMessage(), false);
				}
			}
		}
		
	}
	
	@Override
	public List<TreePO> getBillDefList(String appId) throws ServiceException {
		List<TreePO> treeList = null;
		try {
			treeList = billDefTypeMapper.getBillDefList(appId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, e.getCause().getMessage(), false);
		}
		return treeList;
	}

}

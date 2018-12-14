package commons.setting.manage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.setting.input.po.TreeNode;
import com.tjhq.commons.setting.manage.constants.Constants;
import com.tjhq.commons.setting.manage.dao.IManageMapper;
import com.tjhq.commons.setting.manage.service.IManageService;

@Service
@Transactional(readOnly = true)
public class ManageService implements IManageService{
	@Resource
	private IManageMapper manageMapper;
	
	@Override
	public List<Map<String, String>> queryDictTPublic(Map<String, String> params) {
	    return manageMapper.queryDictTPublic(params);
	}
	@Override
	public Table setTableDefine(String curTab)  throws Exception{
		Table table = new Table();

		table.setAppID("xxxAppID");
		table.setTableName("xxTableName");
		table.setTableID("xxxTableName");
		table.setTableDBName("xxxTableDBName");
		
		List<Column> columnList = new ArrayList<Column>(); 

		Column districtCol=new Column();
		districtCol.setColumnID("DISTRICTID");
		districtCol.setColumnName("DISTRICTID");
		districtCol.setColumnDBName("DISTRICTID");
		districtCol.setAlias("地区编码");
		districtCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		districtCol.setShowType(ShowType.SHOW_TYPE_HTML);
		districtCol.setDataLength(32);
		districtCol.setKey(false);
		districtCol.setOrderID(2);
		districtCol.setReadOnly(true);
		districtCol.setVisible(true);
		districtCol.setWidth(180);      
		columnList.add(districtCol);
		
		Column districtNameCol=new Column();
		districtNameCol.setColumnID("DISTRICTNAME");
		districtNameCol.setColumnName("DISTRICTNAME");
		districtNameCol.setColumnDBName("DISTRICTNAME");
		districtNameCol.setAlias("地区名称");
		districtNameCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		districtNameCol.setShowType(ShowType.SHOW_TYPE_HTML);
		districtNameCol.setDataLength(32);
		districtNameCol.setKey(false);
		districtNameCol.setOrderID(2);
		districtNameCol.setReadOnly(true);
		districtNameCol.setVisible(true);
		districtNameCol.setWidth(180);      
		columnList.add(districtNameCol);
		
		if(Constants.PARTITION.equals(curTab)){
			Column yearExtCol=new Column();
			yearExtCol.setColumnID("YEAR");
			yearExtCol.setColumnName("YEAR");
			yearExtCol.setColumnDBName("YEAR");
			yearExtCol.setAlias("年度");
			yearExtCol.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
			yearExtCol.setShowType(ShowType.SHOW_TYPE_HTML);
			yearExtCol.setDataLength(32);
			yearExtCol.setKey(false);
			yearExtCol.setOrderID(3);
			yearExtCol.setVisible(true);
			yearExtCol.setReadOnly(true);
			yearExtCol.setWidth(130);     
			columnList.add(yearExtCol);
		}
		
		table.setColumnList(columnList);		
		return table;
	}

	@Override
	public List<Map<String, Object>> getPartitionDataList()  throws Exception{
		try {
			return manageMapper.getPartitionDataList();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	@Override
	public List<Map<String, Object>> getInitPartitionDataList(String gridFlag, String province, String year)
			throws Exception {
		List<Map<String, Object>> initPartitionDataList = manageMapper.getInitPartitionDataList(year);
/*		if(Constants.INITPARTITION_LEFTGRID.equals(gridFlag)){//如果是左侧源分区表格，勾选默认分区
			for (Map<String, Object> partitionData : initPartitionDataList) {
				if(province.equals(partitionData.get("DISTRICTID"))){//是默认分区
					partitionData.put("check", "1");
				}else{
					partitionData.put("check", null);					
				}
			}
		}*/
		return initPartitionDataList;
	}
	@Override
	public List<TreeNode> getDistinctTree()  throws Exception{
		try {
			return manageMapper.getDistinctTree();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	@Override
	public List<TreeNode> getSysYears() {
        return manageMapper.getSysYears();
	}
	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class) 
	public void savaPartition(Map<String, Object> map) throws Exception{
		try {
			manageMapper.savaPartition(map);
			map.put("year", "*");
			manageMapper.savaPartition(map);
			manageMapper.addPartitionAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class) 
	public void initPartition(Map<String, Object> map)  throws Exception{
		try {
		    System.out.println(map);
			manageMapper.initPartition(map);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}

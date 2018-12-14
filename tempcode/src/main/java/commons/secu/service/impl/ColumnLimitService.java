package commons.secu.service.impl;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.po.DictTModelcodePO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.external.service.IDictTModelcodeService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.grid.commonGrid.service.impl.CommonGridService;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.secu.dao.ColumnLimitMapper;
import com.tjhq.commons.secu.po.ColumnLimitPO;
import com.tjhq.commons.secu.service.IColumnLimitService;
import com.tjhq.commons.setting.input.po.TreeNode;
import com.tjhq.commons.setting.input.service.IEntryService;

@Component
@Transactional(readOnly=true)
public class ColumnLimitService extends CommonGridService implements IColumnLimitService {
	
	@Resource
	private ColumnLimitMapper columnLimitMapper;
	@Resource
	private ISettingGridService settingGridService;
	@Resource
	private IDictTModelService dictTModelService;
	@Resource
	private IDictTFactorService dictTFactorService;
	@Resource
	private IDictTModelcodeService modelCodeService;
	@Resource
	private IEntryService entryService;
	
	/**
	 * 查询 套表和表
	 */
	public List<Map<String, String>> querySuitModel(String appID) throws ServiceException {
		try {
			Map<String, String> map = new HashMap<String, String>();
	    	map.put("appID", appID);
	    	List<Map<String, String>> list = columnLimitMapper.querySuitModel(map);
	    	return list;
		} catch (Exception e) {
			e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERR_00000, "获取套表和表报错！", false);
		}
	}
	/**
	 * 查询 列
	 */
	public List<Map<String, String>> queryFactorTree(String tableid) throws ServiceException {
		try {
			Map<String, String> map = new HashMap<String, String>();
	    	map.put("tableid", tableid);
	    	List<Map<String, String>> list = columnLimitMapper.queryFactorTree(map);
	    	return list;
		} catch (Exception e) {
			e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERR_00000, "获取套表和表报错！", false);
		}
	}
	/**
	 * 查询 套表和表
	 */
	public List<Map<String, String>> queryFactorColumnLimitDetail(String roleid, String tableid, String columnid) throws ServiceException {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("roleid", roleid);
	    	map.put("tableid", tableid);
	    	map.put("columnid", columnid);
	    	List<Map<String, String>> list = columnLimitMapper.queryFactorColumnLimitDetail(map);
	    	return list;
		} catch (Exception e) {
			e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERR_00000, "获取套表和表报错！", false);
		}
	}
	
	public static Column newColoum(String columnID, String columnName, String columnDBName, boolean isKey, int orderID){
		Column col = new Column();
		col.setColumnID(columnID);
		col.setColumnName(columnName);
		col.setAlias(columnName);
		col.setColumnDBName(columnDBName);
		col.setDataType(JSTypeUtils.getJSDateType(DataType.STRING));
		col.setShowType(ShowType.SHOW_TYPE_HTML);
		col.setDataLength(32);
		col.setKey(isKey);
		col.setOrderID(orderID);
		col.setNullable(isKey?false:true);
		col.setVisible(isKey?false:true);
		return col;
	}
	
	/**
	 * 获取表头
	 * @return
	 * @throws ServiceException
	 */
    public Table getDefine1() throws ServiceException{
    	try {
	    	Table table=new Table();
	    	table.setTableID("1");
			table.setAppID("1");
			table.setTableDBName("1");
			table.setTableName("1");
	    	
	    	
			List<Column> list = new ArrayList<Column>();
			int orderId=0;
			
			Column column4 = newColoum("GUID", "ID", "GUID", true, orderId++);
			column4.setReadOnly(true);
			list.add(column4);
			
			Column column0 = newColoum("COLUMNID", "COLUMNID", "COLUMNID", true, orderId++);
			column0.setReadOnly(true);
			list.add(column0);
			
			Column column1 = newColoum("COLUMNNAME", "限制列名称", "COLUMNNAME", false, orderId++);
			column1.setReadOnly(true);
			column1.setWidth(150);
			list.add(column1);
			
			Column column2 = newColoum("DESCRIBE", "说明", "DESCRIBE", false, orderId++);
			column2.setReadOnly(true);
			list.add(column2);
			
			table.setColumnList(list);
			
			UserDTO user = SecureUtil.getCurrentUser();
			
	    	table = settingGridService.initStructure(table, user.getGuid());
	    	
			return table;
	    } catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, "查询表出错异常", false);
		}
    }

    /**
	 * 获取数据
	 * @return
	 * @throws ServiceException
	 */
    public Object getData1(Table table) throws ServiceException{
    	try {
    		CommonGrid commonGrid = (CommonGrid) table;
    		//获取页面参数
    		Map<String, Object> extProperties = commonGrid.getExtProperties();
    		if( !extProperties.containsKey("tableid") || StringUtils.isEmpty(extProperties.get("tableid").toString())){
    			return new PageInfo();
    		}
	    	List<Map<String, Object>> resultList = columnLimitMapper.queryData1(extProperties);
	    	PageInfo pageInfo = new PageInfo();
			this.setGridData(resultList, pageInfo);
	        return pageInfo;
	    } catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, "查询表出错异常", false);
		}
    }
    /**
	 * 获取表头
	 * @return
	 * @throws ServiceException
	 */
    public Table getDefine2() throws ServiceException{
    	try {
	    	Table table=new Table();
	    	table.setTableID("1");
			table.setAppID("1");
			table.setTableDBName("1");
			table.setTableName("1");
	    	
	    	
			List<Column> list = new ArrayList<Column>();
			int orderId=0;
			
			Column column0 = newColoum("GUID", "主键", "GUID", true, orderId++);
			column0.setReadOnly(true);
			list.add(column0);
			
			Column column4 = newColoum("COLUMNID", "被限制列ID", "COLUMNID", true, orderId++);
			column4.setReadOnly(true);
			list.add(column4);
			
			Column column3 = newColoum("COLUMNID_LIMIT", "限制列ID", "COLUMNID_LIMIT", true, orderId++);
			column3.setReadOnly(true);
			list.add(column3);
			
			Column column1 = newColoum("COLUMNID_LIMIT_NAME", "限制列名称", "COLUMNID_LIMIT_NAME", false, orderId++);
			column1.setReadOnly(true);
			list.add(column1);
			
			Column column2 = newColoum("CONDITION", "条件", "CONDITION", false, orderId++);
			column2.setReadOnly(true);
			list.add(column2);
			
			table.setColumnList(list);
			
			UserDTO user = SecureUtil.getCurrentUser();
			
	    	table = settingGridService.initStructure(table, user.getGuid());
	    	
			return table;
	    } catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, "查询表出错异常", false);
		}
    }
    /**
	 * 获取数据
	 * @return
	 * @throws ServiceException
	 */
    public Object getData2(Table table) throws ServiceException{
    	try {
    		CommonGrid commonGrid = (CommonGrid) table;
    		//获取页面参数
    		Map<String, Object> extProperties = commonGrid.getExtProperties();
    		if( !extProperties.containsKey("columnid") || StringUtils.isEmpty(extProperties.get("columnid").toString())){
    			return new PageInfo();
    		}
	    	List<Map<String, Object>> resultList = columnLimitMapper.queryData2(extProperties);
	    	PageInfo pageInfo = new PageInfo();
			this.setGridData(resultList, pageInfo);
	        return pageInfo;
	    } catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, "查询表出错异常", false);
		}
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
	public String saveTableData(String operate, ColumnLimitPO columnLimitPO) throws ServiceException {
    	
    	//处理主表bgt_t_columnlimit
		if("insert".equals(operate)){
			int count = columnLimitMapper.queryColumnLimit_Check(columnLimitPO);
			if(count > 0){
				throw new ServiceException(ExceptionCode.ERR_00000, "限制列已配置，请选择其他列", false);
			}
			String guid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
			columnLimitPO.setGuid(guid);
			columnLimitMapper.insertColumnLimit(columnLimitPO);
		}else{
			columnLimitMapper.updateColumnLimit(columnLimitPO);
		}
		
		if(StringUtils.isNotEmpty(columnLimitPO.getColumnidLimit()) && !"null".equals(columnLimitPO.getColumnidLimit())){
			//当运算符选择is null或者is not null的时候，condition强制为空
			String operator = columnLimitPO.getOperator();
			if("is null".equals(operator) || "is not null".equals(operator)){
				columnLimitPO.setCondition("");
				columnLimitPO.setConditionCn("");
			}
	    	//校验条件是否可以执行
	    	String tableid = columnLimitPO.getTableid();
	    	DictTModelPO model = dictTModelService.getDictTModelByID(tableid, false);
	    	String tableName = model.getDbtablename();
	    	DictTFactorPO factor = dictTFactorService.getDictTFactorByColumnId(columnLimitPO.getColumnidLimit());
	    	String dbColumnName = factor.getDbcolumnname();
	    	int dataType = factor.getDatatype();
	    	String condition = columnLimitPO.getCondition();
	    	if("in".equals(operator) || "not in".equals(operator)){
	    		if(dataType == 1 || dataType == 2){
	    			condition = "(" + condition + ")";
	    		}else{
	    			condition = "('" + condition.replaceAll(",", "','") + "')";
	    		}
	    	}
	    	
	    	if(StringUtils.isNotEmpty(tableName) && StringUtils.isNotEmpty(dbColumnName)){
	    		String sql = "select count(1) from " + tableName + " where " + dbColumnName + " " + columnLimitPO.getOperator() + " " + condition;
	    		try {
	    			columnLimitMapper.querySql_Check(sql);
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    			throw new ServiceException(ExceptionCode.ERR_00000, "输入的值或格式不对，请重新选择或输入", false);
	    		}
	    	}
	    	
			columnLimitMapper.deleteColumnLimitDetail(columnLimitPO);
			columnLimitMapper.insertColumnLimitDetail(columnLimitPO);
		}
		return columnLimitPO.getGuid();
	}
	
	/**
	 * 删除
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String deleteColumnLimit(String jsonStr) throws ServiceException {
		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		columnLimitMapper.deleteColumnLimit_byColumnid(jsonArray);
		columnLimitMapper.deleteColumnLimitDetail_byColumnid(jsonArray);
		//返回结果
		String result = "删除成功";
		return result;
	}
	/**
	 * 删除
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String deleteColumnLimitDetail(ColumnLimitPO columnLimitPO) throws ServiceException {
		columnLimitMapper.deleteColumnLimitDetail(columnLimitPO);
		//返回结果
		String result = "删除成功";
		return result;
	}
	
	/**
	 * 查询 代码表数据
	 */
	public List<TreeNode> getModelCodeDataByCsid(String csid, String selectId) throws ServiceException {
		try {
			DictTModelcodePO dictTModelcodePO = modelCodeService.getDictTModelcodePOByID(csid);
			List<TreeNode> nodes = null;
			if(dictTModelcodePO != null){
				String dbTableName = dictTModelcodePO.getDbtablename();
				nodes = entryService.getRefrelaDbTableTree(dbTableName);
				String sqlWhere = " ";
				Map<String, Object> map = new HashMap<String, Object>();
				sqlWhere = dictTModelcodePO.getDynamicwhere();
				map.put("dbTableName", dbTableName);
				String agencyID = SecureUtil.getCurrentUser().getUpagencyid();
				if(StringUtils.isNotEmpty(sqlWhere)){
					sqlWhere = sqlWhere.replaceAll("\\$AGENCYID\\$", agencyID);
				}
				map.put("sqlWhere", sqlWhere);
				List<TreeNode> list = new ArrayList<TreeNode>();
				nodes = columnLimitMapper.getRefrelaDbTableTree(map);
				
				
				if(StringUtils.isNotEmpty(selectId) && !"null".equals(selectId)){
					String[] arr = selectId.split(",");
					for(int i = 0; i < arr.length; i++){
						String tempStr = arr[i];
						for(TreeNode treeNode : nodes){
							if(tempStr.equals(treeNode.getId())){
								treeNode.setChecked("true");
							}
						}
					}
				}
			}
			return nodes;
		} catch (Exception e) {
			e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERR_00000, "代码表不存在！", false);
		}
	}
}

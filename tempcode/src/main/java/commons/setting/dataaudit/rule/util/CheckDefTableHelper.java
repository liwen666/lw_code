package commons.setting.dataaudit.rule.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;

/**
 *@desc:BGT_T_checkdef 表的帮助类
 *@author： wxn
 *@date:2014-10-14上午11:00:47
 */
public class CheckDefTableHelper {
	
  /**
   * 	生成table对象
   * @param tableID  BGT_T_CHECKDEF
   * @param appId BGT
   * @param tableDBName BGT_T_CHECKDEF
   * @param TableName 审核规则列表
   * @return
   */
  public static Table createTable(){
		Table table=new Table();
		table.setTableID("BGT_T_CHECKDEF");
		table.setAppID("BGT");
		table.setTableDBName("BGT_T_CHECKDEF");
		table.setTableName("审核规则列表");
	  return table;
  }	
   /**
    * 设置列表要显示的列
    * @param table
    */
	public static void setColumns(Table table) {
	List<Column> list=new ArrayList<Column>();
	int orderId=0;
	list.add(newColoum("CHECKID","主键Id","CHECKID",true,orderId++));
	Column c_SERID=newColoum("SERID","序列号","SERID",false,orderId++);
	//c_SERID.setWidth(80);
	c_SERID.setReadOnly(true);
	list.add(c_SERID);
	
	Column c_DEFNAME=newColoum("DEFNAME","审核名称","DEFNAME",false,orderId++);
	c_DEFNAME.setReadOnly(true);
	c_DEFNAME.setWidth(400);
	list.add(c_DEFNAME);
	
	//左表
	Column c_LMODELID=newColoum("LMODELID","左表","LMODELID",false,orderId++);
	c_LMODELID.setReadOnly(true);
	c_LMODELID.setVisible(false);
	list.add(c_LMODELID);
	Column c_sname_LMODELID=newColoum("SNAME_LMODELID","左表","SNAME_LMODELID",false,orderId++);
	c_sname_LMODELID.setVisible(true);
	c_sname_LMODELID.setReadOnly(true);
	c_sname_LMODELID.setWidth(200);
	list.add(c_sname_LMODELID);
	//右表
	Column c_RMODELID=newColoum("RMODELID","右表","RMODELID",false,orderId++);
	c_RMODELID.setReadOnly(true);
	c_RMODELID.setVisible(false);
	list.add(c_RMODELID);
	Column c_SNAME_RMODELID=newColoum("SNAME_RMODELID","右表","SNAME_RMODELID",false,orderId++);
	c_SNAME_RMODELID.setReadOnly(true);
	c_SNAME_RMODELID.setWidth(200);
	c_SNAME_RMODELID.setVisible(true);
	list.add(c_SNAME_RMODELID);
	//审查误差
	Column c_errorDef=newColoum("ERRORDEF","审核误差","ERRORDEF",false,orderId++);
	c_errorDef.setDataType(DataType.NUMBER);
	c_errorDef.setDefaultValue("0.00");
	c_errorDef.setScale(2);
	//c_errorDef.setWidth(150);
	c_errorDef.setReadOnly(true);
	list.add(c_errorDef);
	//审查类型
	Column c_CHECKTYPE=newColoum("CHECKTYPE","审核类型","CHECKTYPE",false,orderId++);
	c_CHECKTYPE.setRefTableID("BGT_T_PUBCHECKDEF");
	c_CHECKTYPE.setRefTableDBName("BGT_T_PUBCHECKDEF");
	c_CHECKTYPE.setShowType(ShowType.SHOW_TYPE_LIST);
	//c_CHECKTYPE.setWidth(150);
	c_CHECKTYPE.setReadOnly(true);
	list.add(c_CHECKTYPE);
	//审核分类id
	Column c_CHECKSORTID=newColoum("CHECKSORTID","审核分类id","CHECKSORTID",false,orderId++);
	c_CHECKSORTID.setShowType(ShowType.SHOW_TYPE_HTML);
	c_CHECKSORTID.setVisible(false);
	list.add(c_CHECKSORTID);
	//审核分类id
	Column c_CREATEBGTLEVEL=newColoum("CREATEBGTLEVEL","创建人地方级次","CREATEBGTLEVEL",false,orderId++);
	c_CREATEBGTLEVEL.setShowType(ShowType.SHOW_TYPE_HTML);
	c_CREATEBGTLEVEL.setVisible(false);
	list.add(c_CREATEBGTLEVEL);

  //list.add(newColoum("ERRORDEF","审核误差","ERRORDEF",DataType.NUMBER,orderId++));	
	table.setColumnList(list);

	}
	public static Column newColoum(String columnID,String columnName,String columnDBName,String dataType,String showType,int dataLength,boolean isKey,int orderID,boolean nullable,boolean visible){
		//TASKTYPEID-1
		Column col=new Column();
		col.setColumnID(columnID);
		col.setColumnName(columnName);
		col.setAlias(columnName);
		col.setColumnDBName(columnDBName);
		col.setDataType(JSTypeUtils.getJSDateType(dataType));
		col.setShowType(showType);
		col.setDataLength(dataLength);
		
		col.setKey(isKey);
		col.setOrderID(orderID);
		col.setNullable(nullable);
		col.setVisible(visible);
		return col;
	}
	public static Column newColoum(String columnID,String columnName,String columnDBName,boolean isKey,int orderID){
		Column col=new Column();
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
	
	public static StringBuffer getCondition4SortId(Map<String,String> map){
		StringBuffer sql = new StringBuffer(50);
        sql.append(" checksortid in(select checksortid from BGT_T_checksort ")
			.append(" start with checksortid='"+map.get("CHECKSORTID").trim()+"'")
			.append(" connect by prior checksortid= superid ) ");
        return sql;
	}
	/*
	 * 审核检查定义
	 */
	public static void setColumns4getAuditRuleDefCheck(Grid table) {
		List<Column> list=new ArrayList<Column>();
		int orderId=0;
		list.add(newColoum("CHECKID","主键Id","CHECKID",true,orderId++));
		Column c_SERID=newColoum("SERID","序列号","SERID",false,orderId++);
		c_SERID.setReadOnly(true);
		list.add(c_SERID);
		
		Column c_DEFNAME=newColoum("DEFNAME","审核名称","DEFNAME",false,orderId++);
		c_DEFNAME.setReadOnly(true);
		list.add(c_DEFNAME);
		//审查类型
		Column c_CHECKTYPE=newColoum("CHECKTYPE","审核类型","CHECKTYPE",false,orderId++);
		c_CHECKTYPE.setReadOnly(true);
		list.add(c_CHECKTYPE);


	  //list.add(newColoum("ERRORDEF","审核误差","ERRORDEF",DataType.NUMBER,orderId++));	
		table.setColumnList(list);
		
	}
	

}


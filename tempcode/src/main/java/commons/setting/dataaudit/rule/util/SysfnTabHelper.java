package commons.setting.dataaudit.rule.util;

import java.util.ArrayList;
import java.util.List;

import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;

/**
 *@desc:TODO
 *@author： wxn
 *@date:2014-10-24下午4:31:29
 */
public class SysfnTabHelper {
	 /**
	    * 设置列表要显示的列
	    * @param table
	    */
		public static void setColumns(Table table) {
		List<Column> list=new ArrayList<Column>();
		int orderId=0;
		list.add(newColoum("GUID","主键Id","GUID",true,orderId++));
		Column name=newColoum("CODE","名称","CODE",false,orderId++);
		name.setWidth(170);
		name.setNullable(false);
		list.add(name);
		Column desc=newColoum("NAME","描述","NAME",false,orderId++);
		desc.setWidth(200);
		desc.setNullable(false);
		list.add(desc);
		table.setColumnList(list);

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
}


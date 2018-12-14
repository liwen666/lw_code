package commons.setting.dataaudit.auditrule.util;

import java.util.ArrayList;
import java.util.List;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;


/**
* @ClassName: AuditRuleCheckSortTableHelper
* @Description: 审核类型 表定义信息工具类
* @author xiehongtao
* @date 2017-6-27 下午2:27:00
*
 */
public class AuditRuleCheckTypeTableHelper {
	 /**
	   * 	生成table对象
	   * @param tableID  bgt_T_CHECKSORT
	   * @param appId BGT
	   * @param tableDBName bgt_T_CHECKSORT
	   * @param TableName 审核规则列表
	   * @return
	   */
	public static Table createTable() {
		Table table = new Table();
		table.setTableID("bgt_t_checksort");
		table.setAppID("BGT");
		table.setTableDBName("bgt_t_checksort");
		table.setTableName("审核类型");
		return table;
	}

   /**
    * 设置列表要显示的列
    * @param table
    */
	public static void setColumns(Table table) {
		List<Column> list = new ArrayList<Column>();
		
		int orderId = 0;
		
		list.add(newColoum("CHECKSORTID", "主键Id", "CHECKSORTID", true,orderId++));
		
		//序号
		Column c_LVLID = newColoum("LVLID", "序号", "LVLID", false, orderId++);
		c_LVLID.setNullable(false);
		c_LVLID.setWidth(70);
		c_LVLID.setRegExpression("^\\+?[1-9][0-9]*$");
		c_LVLID.setRegInfo("请输入正确的序号：正整数");
		list.add(c_LVLID);
		
		//审核分类名称
		Column c_CHECKSORTNAME = newColoum("CHECKSORTNAME", "审核分类名称","CHECKSORTNAME", false, orderId++);
		c_CHECKSORTNAME.setWidth(150);
		c_CHECKSORTNAME.setNullable(false);
		list.add(c_CHECKSORTNAME);

		//审查类型
		Column c_REMARK = newColoum("REMARK", "备注", "REMARK", false, orderId++);
		c_REMARK.setShowType(ShowType.SHOW_TYPE_HTML);
		c_REMARK.setWidth(200);
		list.add(c_REMARK);

		//审核分类id
		Column c_SUPERID = newColoum("SUPERID", "上级id", "SUPERID", false,orderId++);
		c_SUPERID.setShowType(ShowType.SHOW_TYPE_HTML);
		c_SUPERID.setVisible(false);
		list.add(c_SUPERID);

		//末级标志
		Column c_ENDFLAG = newColoum("ENDFLAG", "末级标志", "ENDFLAG", false,orderId++);
		c_ENDFLAG.setShowType(ShowType.SHOW_TYPE_HTML);
		c_ENDFLAG.setVisible(false);
		list.add(c_ENDFLAG);
		
		//子系统id
		Column c_appID = newColoum("APPID", "子系统id", "APPID", false, orderId++);
		c_appID.setVisible(false);
		list.add(c_appID);
		
		table.setColumnList(list);

	}
	
	/**
	* @Title: newColoum
	* @Description: 创建一个新列
	* @param @param columnID
	* @param @param columnName
	* @param @param columnDBName
	* @param @param dataType
	* @param @param showType
	* @param @param dataLength
	* @param @param isKey
	* @param @param orderID
	* @param @param nullable
	* @param @param visible
	* @param @return
	* @return Column
	* @throws
	 */
	public static Column newColoum(String columnID, String columnName,
			String columnDBName, String dataType, String showType,
			int dataLength, boolean isKey, int orderID, boolean nullable,
			boolean visible) {
		// TASKTYPEID-1
		Column col = new Column();
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
	
	/**
	* @Title: newColoum
	* @Description: 创建新列
	* @param @param columnID
	* @param @param columnName
	* @param @param columnDBName
	* @param @param isKey
	* @param @param orderID
	* @param @return
	* @return Column
	* @throws
	 */
	public static Column newColoum(String columnID, String columnName,
			String columnDBName, boolean isKey, int orderID) {
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
		col.setNullable(isKey ? false : true);
		col.setVisible(isKey ? false : true);
		return col;
	}
	
	/**
	* @Title: getNULLExpression
	* @Description: null 表达式
	* @param @param checkSortId_str
	* @param @return
	* @return String
	* @throws
	 */
	public static String getNULLExpression(String checkSortId_str) {

		return "(" + checkSortId_str + " is null or  " + checkSortId_str + " ='')";
	}

}

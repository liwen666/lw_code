package commons;

/**
 * 公用全局常量类
 * @author YAN
 * @CreateDate 2014-1-15
 * @version 1.0
 */
public final class Constants {
	/**
	 * 获得处室表，单位表，科目表，经济分类表物理表名使用的表类型常量
	 */
	public static final String COMMON_DictTAPPCODE_TABLETYPE_AGENCY="AgencyID";//单位表
	public static final String COMMON_DictTAPPCODE_TABLETYPE_DEPT="DeptID"; //处室表
	public static final String COMMON_DictTAPPCODE_TABLETYPE_EXPFUNC="ExpFuncID";//科目表
	public static final String COMMON_DictTAPPCODE_TABLETYPE_EXPECO="ExpEcoID";//经济分类表
	public static final String COMMON_DictTAPPCODE_TABLETYPE_FUNCSOURCE="FUNDSOURCEID";//资金来源表

	public static final String TT_START = "TT("; //TT(收入项目录入表).   (按此 'TT(' 或 ').'进行查找和定位)
	public static final String TT_END = ").";
	
	public static final String RF_START = "RF("; //RF(收入项目录入表).   (按此 'RF(' 或 ').'进行查找和定位)
	public static final String RF_END = ").";

	public static final String WHERE_START = "〖";  //条件：〖(『单位编码』='0') and 1=1〗
	public static final String WHERE_END = "〗";
	
	public static final String COMCOL_START = "{";  //表达式 : {sum(『纳入公共财政预算管理的非税收入小计』)+10}
	public static final String COMCOL_END = "}";
	
	public static final String COLUMN_START = "『"; //列 :『专项收入安排的资金』
	public static final String COLUMN_END = "』"; 
	
	public static final String COLUMN_AT = "@"; 
	
	public static final String BPMN_TYPE_BGT="budget_bpmn"; //工作流类型：部门预算
	public static final String BPMN_TYPE_BIM="info_bpmn";//工作流类型 : 基础信息
	public static final String BPMN_TYPE_CDT = "cdtTask_bpmn"; //工作流类型 : 通用数据采集
	
	public static final String CACHE_KEY_VISIT_COUNT="cache_key_visit_count"; //首页访问次数
	
	public static final String NET_TYPE_INNER = "0";//内网
	public static final String NET_TYPE_OUTER = "1";//外网
	
	/**
	 * 县级专项级次
	 */
	public static final String COUNTY_LEVEL_SPF = "4";
	
	public static final String APPID_BGT = "BGT";
	public static final String APPID_BAS = "BAS";
	public static final String APPID_SPF = "SPF";
	public static final String APPID_INDI = "INDI";
	public static final String APPID_KPI = "KPI";
	public static final String APPID_BD = "BD";
	
	//-------------------------------纵向流程方向-----------------------------------
	/**
	 * 纵向流程方向——起点
	 */
	public static final String WFDIRECTION_ORIGIN = "A";
	/**
	 * 纵向流程方向——从上往下（下发）
	 */
	public static final String WFDIRECTION_DOWN = "0";
	/**
	 * 纵向流程方向——从下往上（上报）
	 */
	public static final String WFDIRECTION_UP = "1";
	/**
	 * 纵向流程方向——回退
	 */
	public static final String WFDIRECTION_RETURN = "2";
	/**
	 * 纵向流程方向——撤回
	 */
	public static final String WFDIRECTION_REVOKE = "3";	
	/**
	 * 纵向流程方向——删除
	 */
	public static final String WFDIRECTION_DELETE = "7";
	/**
	 * 纵向流程方向——办结
	 */
	public static final String WFDIRECTION_DONE = "8";

	/**
	 * 纵向流程方向——代录
	 */
	public static final String WFDIRECTION_INSTEAD = "9";
	
	
	//-------------------------------表处理类型-----------------------------------
	public static final String TABLE_DEALTYPE_A0 = "A0";		//一般录入表
	public static final String TABLE_DEALTYPE_A0_01 = "A0*01";	//人员表
	public static final String TABLE_DEALTYPE_A1 = "A1";		//固定行列表
	public static final String TABLE_DEALTYPE_A2 = "A2";		//浮动表
	public static final String TABLE_DEALTYPE_A0_05 = "A0*05";	//转移支付表
	public static final String TABLE_DEALTYPE_62 = "62";		//单位数字表
	/**
	 * @Fields SNAME_START : 引用列前缀
	 */
	public static final String SNAME_START = "SNAME_";
	//--------------------列名常量-----------------------
	/**
	 * 物理主键DATAKEY
	 */
	public static final String COL_DBNAME_DATAKEY = "DATAKEY";
	/**
	 * 物理主键STATUS
	 */
	public static final String COL_DBNAME_STATUS = "STATUS";
	/**
	 * 单位
	 */
	public static final String COL_DBNAME_AGENCYID = "AGENCYID";
	/**
	 * 物理主键DBVERSION
	 */
	public static final String COL_DBNAME_DBVERSION = "DBVERSION";
	/**
	 * 转移支付表 任务ID
	 */
	public static final String COL_DBNAME_BTASKID = "BTASKID";
	/**
	 * 转移支付表 级次
	 */
	public static final String COL_DBNAME_INPUTLVL = "INPUTLVL";
	/**
	 * 转移支付表 地区ID
	 */
	public static final String COL_DBNAME_INPUTDISTRICT = "INPUTDISTRICT";
	/**
	 * 转移支付表 结果
	 */
	public static final String COL_DBNAME_DZJG = "DZJG";
	/**
	 * 转移支付表 是否稳增长
	 */
	public static final String COL_DBNAME_ISWZZ = "ISWZZ";
	/**
	 * 项目类型ID列
	 */
	public static final String COL_DBNAME_PROJTYPE = "PROJTYPEID";
	/**
	 * 专项名称
	 */
	public static final String COL_DBNAME_SPFNAME = "SPFNAME";
	/**
	 * 人员表状态
	 */
	public static final String COL_DBNAME_CONFIRMFLAG = "CONFIRMFLAG";
	
	//--------------------MongoDB-----------------------
	public static final String MONGO_FUNCTION_INSERT = "新增";
	
	public static final String MONGO_FUNCTION_UPDATE = "修改";
	
	public static final String MONGO_FUNCTION_DELETE = "删除";
	
	public static final String MONGO_FUNCTION_IMPORT = "导入";
	
	//代码表别名
	public static final String TABLE_NAME_ALIAS = " A_1 ";
	//--------------------EXCEL-----------------------
	//EXCEL KEY
	public static final String EXCEL_KEY_DBCOLUMNNAME = "DBCOLUMNNAME_";
	//EXCEL KEY
	public static final String EXCEL_KEY_DATATYPE = "DBCOLUMNNAME_";
	//EXCEL KEY
	public static final String EXCEL_LINE = "\r\n";
}

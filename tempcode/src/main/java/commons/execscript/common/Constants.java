package commons.execscript.common;

public interface Constants {
	public final String SCRIPT_GRID = "script"; 
	public final String LOG_GRID = "log";

	public final String USERDIR = System.getProperty("user.dir"); 
	public final String SCRIPT_SRCPATH = "/src/com/tjhq/commons/execscript/sql/";//脚本编号用
	public final String SCRIPT_BASEPATH = "com/tjhq/commons/execscript/sql/";//页面用
	public final String SCRIPT_DIVNAME = "sql";
	public final String SCRIPT_COMMONPATH = "common";
	
	//业务系统目录名称，同appid
	public final String SCRIPT_COMPATH = "*";//通用
	public final String SCRIPT_BGTPATH = "bgt";//预算
	public final String SCRIPT_SPFPATH = "spf";//项目库
	public final String SCRIPT_BASPATH = "bas";//基本数据采集
    public final String SCRIPT_KPIPATH = "kpi";//绩效
    public final String SCRIPT_INDIPATH = "indi";//指标
    public final String SCRIPT_BILPATH = "bil";//通用单据
	public final String SCRIPT_OLDPATH = "old";//dbupdate旧脚本
	
	public final String SCRIPT_TABLENAME = "EFM_T_DBUPDATE_4BS";
	public final String SCRIPT_LOGTABNAME = "EFM_T_DBUPDATE_LOG";
	public final String SCRIPT_APPIDCOLNAME = "APPID";
	

	public final String SCRIPT_OLDDIVNAME = "old";
	public final String SCRIPT_OLDTYPEID = "DBUpdate";
	public final String SCRIPT_OLDHEAD = "busi";
	
	public final String SCRIPTRECORD_FILENAME = "scriptRecord.txt";//脚本记录文件名称
	public final String SCRIPTSPLITFLAG = "  ||  ";//脚本记录文件中脚本编号名和真实脚本名的分隔符
	public final String SCRIPTSPLITFLAGSTRING = "\\|\\|";//脚本记录文件中脚本编号名和真实脚本名的分隔符，split方法用
	public final String SCRIPTVARSIONDATA_FILENAME="ScriptVersionData.xml";//脚本版本数据xml文件名称
	
	public final String SCRIPT_EXECTYPE = "jdbc";//脚本用jdbc方式直接执行
	
	public final String SCRIPT_INSTALLER = "installer";//平台远程调用系统安装的执行者
	public final String SCRIPT_DFBZX = "DF_";//杜崇明添加，脚本用DF_方式开头，如果只87分区，跳过不分区
	
}

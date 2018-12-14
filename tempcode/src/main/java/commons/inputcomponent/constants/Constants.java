package commons.inputcomponent.constants;

public final class Constants {
	
	/**
	 * @Fields DATAKEY : 行唯一标识
	 */
	public final static String DATAKEY = "DATAKEY";
	/**
     * @Fields DATAKEY : 增量表备份行唯一标识
     */
    public final static String BAKDATAKEY = "BAKDATAKEY";
	
	/**
	 * @Fields ROWSECU : 行权限标识
	 */
	public final static String ROWSECU = "ROWSECU";
	
	/**
	 * @Fields BAKVERSION : 数据版本号
	 */
	public final static String BAKVERSION = "BAKVERSION";
	
	/**
	 * @Fields FLOAT_FDCODE : 浮动表层次码列
	 */
	public final static String FLOAT_FDCODE = "FDCODE";
	
	/**
	 * @Fields SNAME_START : 引用列前缀
	 */
	public static final String SNAME_START = "SNAME_";
	
	/**
	 * @Fields ORACLE_TIMESTAMP : Oracle时间类型
	 */
	public static final String ORACLE_TIMESTAMP = "oracle.sql.TIMESTAMP";
	
	/**
	 * @Fields INCREMENT_FLAG : 增量表 增量列名
	 */
	public static final String INCREMENT_FLAG = "INCREMENTFLAG";
	
	/**
	 * @Fields INCREMENT_FLAG_ADD : 增量表新增数据
	 */
	public static final String INCREMENT_FLAG_ADD = "1";
	
	/**
	 * @Fields INCREMENT_FLAG_MODIFY : 增量表 正式表修改数据
	 */
	public static final String INCREMENT_FLAG_MODIFY = "2";
	
	/**
	 * @Fields INCREMENT_FLAG_BAK_MODIFY : 增量表 备份表修改数据
	 */
	public static final String INCREMENT_FLAG_BAK_MODIFY = "3";
	
	/**
	 * @Fields INCREMENT_FLAG_DELETE : 增量表 删除数据
	 */
	public static final String INCREMENT_FLAG_DELETE = "4";
	
	//--------------------备份类型常量-----------------------
    public static final class BakType {
        
        /**
         * @Fields BAK_DEFAULT : 普通备份
         */
        public static final String BAK_DEFAULT = "0";
        
        /**
         * @Fields BAK_EST : 测算模型备份
         */
        public static final String BAK_EST = "1";
        
        /**
         * @Fields BAK_TASKCONVERT : 任务转换备份
         */
        public static final String BAK_TASKCONVERT = "2";
        
        /**
         * @Fields BAK_DATAINPUT : 录入界面手工备份
         */
        public static final String BAK_DATAINPUT = "3";
        
        /**
         * @Fields BAK_BATCH : 数据指批量备份
         */
        public static final String BAK_BATCH = "4";
        
    }

}

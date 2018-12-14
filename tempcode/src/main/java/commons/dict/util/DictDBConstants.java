package commons.dict.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;

/**
 * 静态变量 
 * @author xujingsi
 *
 */
public class DictDBConstants {

	//表
	public static final String TABLE_TYPE_TABLE = "1";
	//视图
	public static final String TABLE_TYPE_VIEW = "2";
	//业务对象
	public static final String TABLE_TYPE_BUSIOBJ = "3";
	
	public static final String TYPE_TABLE = "TABLE";
	public static final String TYPE_PARTITION = "PARTITION";
	public static final String TYPE_VIEW = "VIEW";
	public static final String TYPE_CFG_VIEW = "CONFIGVIEW";
	public static final String TYPE_TRIGGER = "TRIGGER";
	public static final String TYPE_PRIMARY = "PRIMARY";
	public static final String TYPE_INDEX = "INDEX";
	public static final String TYPE_TABLE_COMMENTS = "COMMENTS";
	
	public static final String IF_EXISTS_TYPE_TABLE = "SELECT COUNT(1) FROM USER_TABLES WHERE TABLE_NAME = ";
	public static final String IF_EXISTS_TYPE_VIEW = "SELECT COUNT(1) FROM USER_VIEWS WHERE VIEW_NAME = ";
	public static final String IF_EXISTS_TYPE_TRIGGER = "SELECT COUNT(1) FROM USER_TRIGGERS WHERE TRIGGER_NAME = ";
	public static final String IF_EXISTS_TYPE_PK = "SELECT COUNT(1) FROM USER_CONSTRAINTS T WHERE  T.CONSTRAINT_NAME =";  
	public static final String IF_EXISTS_TYPE_COLUMN = "SELECT COUNT(1) FROM USER_TAB_COLUMNS WHERE TABLE_NAME =";
	public static final String IF_EXISTS_TYPE_INDEX = "SELECT COUNT(1) FROM USER_INDEXES  U WHERE U.TABLE_NAME =";
	//
	public static final String PREFIX_TABLE="P#";
	public static final String SUFFIX_CONFIG_VIEW="_CFG";
	public static final String CREAT_TABLE = "CREATE TABLE";
	public static final String ALTER_TABLE = "ALTER TABLE";
	public static final String ADD_CONSTRAINT = "ADD CONSTRAINT";
	public static final String PRIMARY_KEY = "PRIMARY KEY";
	public static final String NOT_NULL = "NOT NULL";
	
	public static final String PARTITION_BY_LIST = "PARTITION BY LIST";
	public static final String SUBPARTITION_BY_LIST = "SUBPARTITION BY LIST";
	public static final String PARTITION = "PARTITION";
	public static final String SUBPARTITION = "SUBPARTITION";
	public static final String VALUES = "VALUES";
	 
	public static final String CREATE_OR_REPLACE = "CREATE OR REPLACE";
	public static final String VIEW = "VIEW";
	public static final String AS = "AS";
	public static final String SELECT = "SELECT";
	public static final String FROM = "FROM";
	public static final String WHERE = "WHERE";
	public static final String AND = "AND";
	
	public static final String TRIGGER = "TRIGGER";
	public static final String BEFORE_INSERT_ON  = "BEFORE INSERT ON ";
	public static final String BEFORE_INSERT_OR_UPDATE_ON  = "BEFORE INSERT OR UPDATE ON ";
	public static final String FOR_EACH_ROW = "FOR EACH ROW";
	public static final String BEGIN = "BEGIN";
	public static final String END = "END";
	public static final String NEW_PROVINCE = ":NEW.PROVINCE:=NVL(:NEW.PROVINCE,GLOBAL_MULTYEAR_CZ.V_PMDIVID);";
	public static final String NEW_YEAR = ":NEW.YEAR:=NVL(:NEW.YEAR,GLOBAL_MULTYEAR_CZ.V_PMYEAR);";
	public static final String NEW_DBVERSION = ":NEW.DBVERSION := CASE WHEN TO_CHAR(:NEW.DBVERSION, 'yyyy-mm-dd') = '2012-01-01' THEN TO_DATE('2012-01-01', 'yyyy-mm-dd') ELSE SYSDATE END;";
	public static final String NEW_VERSION_UPDATE = ":NEW.DBVERSION:=SYSDATE;";
	public static final String NEW_TASKID = ":NEW.TASKID:=NVL(:NEW.TASKID,GLOBAL_MULTYEAR_CZ.V_TASKID);";
	public static final String IF_TOCHAR = "TO_CHAR(:NEW.DBVERSION,'yyyy-mm-dd') ='2012-01-01' AND ((UPDATING AND UPDATING('DBVERSION')) or INSERTING )";
	public static final String ADD_COMMENTS = " COMMENT ON TABLE ";
	
	public static final String ADD = "ADD";
	public static final String DEFAULT = "DEFAULT";
	public static final String DROP = "DROP";
	public static final String CONSTRAINT = "CONSTRAINT";
	public static final String CASCADE = "CASCADE";
	
	public static final String COLUMN = "COLUMN";
	//alter table P#AABB drop constraint PK_P#AABB  cascade;
	
	public static final String RENAME = "RENAME";
	public static final String MODIFY = "MODIFY";
	public static final String TO = "TO";
	
	//generated always as() virtual
	public static final String GENERATED = "GENERATED";
	public static final String ALWAYS = "ALWAYS";
	public static final String VIRTUAL = "VIRTUAL";
	
	
	
	//trigger
	public static final String INSTEAD = "INSTEAD";
	public static final String OF = "OF";
	public static final String INSERT = "INSERT";
	public static final String OR = "OR";
	public static final String UPDATE = "UPDATE";
	public static final String DELETE = "DELETE";
	public static final String ON = "ON";
	public static final String FOR = "FOR";
	public static final String EACH = "EACH";
	public static final String ROW = "ROW";
	public static final String DECLARE = "DECLARE";
	public static final String IF = "IF";
	public static final String INSERTING = "INSERTING";
	public static final String THEN = "THEN";
	public static final String INTO = "INTO";
	public static final String NEW = "NEW";
	public static final String ELSIF = "ELSIF";
	public static final String UPDATING = "UPDATING";
	public static final String DELETING = "DELETING";
	public static final String SET = "SET";
	public static final String OLD = "OLD";
	
	
	public static final String V_UPDATE_COL = "v_update_col";
	public static final String V_SQL = "v_sql";
	public static final String EXECUTE_IMMEDIATE = "EXECUTE IMMEDIATE";
	public static final String CASE = "CASE";
	public static final String WHEN = "WHEN";
	public static final String ELSE = "ELSE";
	public static final String IS = "IS";
	public static final String SUBSTR = "SUBSTR";
	public static final String LENGTH = "LENGTH";
	public static final String CODETDISTRICT="code_t_district";
	public static final String SYSPADDPARTITIONF="P#DICT_T_FACTOR";
	public static final String SYSPADDPARTITIONM="P#DICT_T_MODEL";
	public static final String SYSPADDPARTITIONMC="P#DICT_T_MODELCODE";
	public static final String SYSPADDPARTITIONFC="P#DICT_T_FACTORCODE";
	public static final String SYSPADDPARTITIONS="P#DICT_T_SUIT";

	
	//
	public static final String VIEWFORUPDATEVIEW = "VIEWFORUPDATEVIEW";
	public static final String TRIGGERFORUPDATEVIEW = "TRIGGERFORUPDATEVIEW";
	
	public static final String FASP_DBLINKNAME="FASP";
	
	
	
	
	//扩展属性 位数
	public static int DICT_DB_EXEPROP = 32;
	
	
	/**
	 * 字段类型
	 */
	public static Map<Integer,String> dataType = new HashMap<Integer, String>();
	/**
	 * 字段类型中文
	 */
	public static Map<Integer,String> dataType_ZH = new HashMap<Integer, String>();
	/**
	 * 数据库状态
	 */
	public static Map<String,String> status_DB = new HashMap<String, String>();
	
	/**
	 * 字段类型 默认值
	 */
	public static Map<Integer,String> dataType_Defaultvalue = new HashMap<Integer, String>();
	
	/**
	 * 界面显示处理方式
	 */
	public static Map<String,String> showformat = new HashMap<String, String>();
	
	static{
		
		dataType.put(1, "NUMBER");
		dataType.put(2, "NUMBER");
		dataType.put(3, "VARCHAR2");
		dataType.put(4, "VARCHAR2");
		dataType.put(6, "VARCHAR2");
		dataType.put(7, "CLOB");
		//dataType.put(8, "DATE");
		//dataType.put(9, "VARCHAR2");
		//dataType.put(10, "TIMESTAMP");
		
		
		//1整型、 2小数、3字符、4标题、6引用、7大文本、8日期  9附件   10 TIMESTAMP 
		
		
		dataType_ZH.put(1, "整型");
		dataType_ZH.put(2, "小数");
		dataType_ZH.put(3, "字符");
		//dataType_ZH.put(4, "标题");
		dataType_ZH.put(6, "引用");
		//dataType_ZH.put(7, "大文本");
		//dataType_ZH.put(8, "日期");
		//dataType_ZH.put(9, "附件");
		//dataType_ZH.put(10, "时间戳");
		
		dataType_Defaultvalue.put(1, "0");
		dataType_Defaultvalue.put(2, "0");
		dataType_Defaultvalue.put(3, "''");
		dataType_Defaultvalue.put(4, "''");
		dataType_Defaultvalue.put(6, "''");
		dataType_Defaultvalue.put(7, "''");
		/*dataType_Defaultvalue.put(8, "sysdate()");
		dataType_Defaultvalue.put(9, "''");
		dataType_Defaultvalue.put(10, "current_timestamp()");*/
		
		
		status_DB.put("0", "单财政单年度");
		status_DB.put("1", "单财政多年度");
		status_DB.put("2", "多财政多年度");
		// --0单财政单年度；1：单财政多年度；2多财政多年度。
		
		
		
//		showformatMAP/界面显示处理方式：TEXT = "0" MASK_TEXT = "1" Date="2" CHECK = "3" LIST="4" 
		//BUTTON="5" RADIO = "6" SHORT DATE="7" POP_WINDOW = "8" HTML="9" 大文本="A" Picture="B" File="C"  PASSWORD="D"
		showformat.put("0", "TEXT");
		showformat.put("1", "MASK_TEXT");
		showformat.put("2", "DATE");
		showformat.put("3", "CHECK");
		showformat.put("4", "LIST");
		showformat.put("5", "BUTTON");
		showformat.put("6", "RADIO");
		showformat.put("7", "SHORT DATE");
		showformat.put("8", "POP_WINDOW");
		showformat.put("9", "HTML");
		showformat.put("A", "大文本");
		showformat.put("B", "Picture");
		showformat.put("C", "File");
		showformat.put("D", "PASSWORD");
		showformat.put("E", "MULTIPLE_LIST");//add by xl
	}
	
	

	/**
	 * 
	 * 从 Unicode 形式的字符串转换成对应的编码的特殊字符串。 如 "\u9EC4" to "黄".
	 * Converts encoded \\uxxxx to unicode chars
	 * and changes special saved chars to their original forms
	 * @param in Unicode编码的字符数组。
	 * @param off转换的起始偏移量。
	 * @param len 转换的字符长度。
	 * @param convtBuf转换的缓存字符数组。
	 * @return 完成转换，返回编码前的特殊字符串。
	 */

	public static String fromEncodedUnicode(char[] in, int off, int len) {
		char aChar;
		char[] out = new char[len]; // 只短不长
		int outLen = 0;
		int end = off + len;
		while (off < end) {
			aChar = in[off++];
			if (aChar == '\\') {
				aChar = in[off++];
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = in[off++];
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
						}
					}
					out[outLen++] = (char) value;
				} else {
					if (aChar == 't') {
						aChar = '\t';
					} else if (aChar == 'r') {
						aChar = '\r';
					} else if (aChar == 'n') {
						aChar = '\n';
					} else if (aChar == 'f') {
						aChar = '\f';
					}
					out[outLen++] = aChar;
				}
			} else {
				out[outLen++] = (char) aChar;
			}
		}
		return new String(out, 0, outLen);

	}
	
	
	
	
	/**
     * 
     * @param newd
     * @param oldd
     * @return
     */
    public static DictTFactorPO changeDataForDictTFactorPOUpdate(DictTFactorPO newd,DictTFactorPO oldd){
    	
    	if(newd.getTableid()!=null&&newd.getTableid().equals(nullAble(oldd.getTableid()))){
			newd.setTableid(null);
		}
    	if(newd.getName()!=null&&newd.getName().equals(nullAble(oldd.getName()))){
			newd.setName(null);
		}
    	if(newd.getDbcolumnname()!=null&&newd.getDbcolumnname().equals(nullAble(oldd.getDbcolumnname()))){
			newd.setDbcolumnname(null);
		}
//    	if(newd.getDatatype()!=null&&newd.getDatatype().equals(nullAble(oldd.getDatatype()))){
//			newd.setDatatype(null);
//		}
    	if(newd.getDatalength()!=null&&newd.getDatalength().equals(nullAble(oldd.getDatalength()))){
			newd.setDatalength(null);
		}
    	if(newd.getScale()!=null&&newd.getScale().equals(nullAble(oldd.getScale()))){
			newd.setScale(null);
		}
    	if(newd.getNullable()!=null&&newd.getNullable().equals(nullAble(oldd.getNullable()))){
			newd.setNullable(null);
		}
    	if(newd.getDefaultvalue()!=null&&newd.getDefaultvalue().equals(nullAble(oldd.getDefaultvalue()))){
			newd.setDefaultvalue(null);
		}
    	if(newd.getShowformat()!=null&&newd.getShowformat().equals(nullAble(oldd.getShowformat()))){
			newd.setShowformat(null);
		}
    	if(newd.getSuperid()!=null&&newd.getSuperid().equals(nullAble(oldd.getSuperid()))){
			newd.setSuperid(null);
		}
    	if(newd.getExtprop()!=null&&newd.getExtprop().equals(nullAble(oldd.getExtprop()))){
			newd.setExtprop(null);
		}
    	if(newd.getCsid()!=null&&newd.getCsid().equals(nullAble(oldd.getCsid()))){
			newd.setCsid(null);
		}
    	if(newd.getIsupdate()!=null&&newd.getIsupdate().equals(nullAble(oldd.getIsupdate()))){
			newd.setIsupdate(null);
		}
    	if(newd.getOrderid()!=null&&newd.getOrderid().equals(nullAble(oldd.getOrderid()))){
			newd.setOrderid(null);
		}
    	if(newd.getSuperid()!=null&&newd.getSuperid().equals(nullAble(oldd.getSuperid()))){
			newd.setSuperid(null);
		}
    	if(newd.getIsleaf()!=null&&newd.getIsleaf().equals(nullAble(oldd.getIsleaf()))){
			newd.setIsleaf(null);
		}
    	if(newd.getLevelno()!=null&&newd.getLevelno().equals(nullAble(oldd.getLevelno()))){
			newd.setLevelno(null);
		}
    	if(newd.getAlias()!=null&&newd.getAlias().equals(nullAble(oldd.getAlias()))){
			newd.setAlias(null);
		}
    	if(newd.getColformat()!=null&&newd.getColformat().equals(nullAble(oldd.getColformat()))){
			newd.setColformat(null);
		}
    	if(newd.getShowwidth()!=null&&newd.getShowwidth().equals(nullAble(oldd.getShowwidth()))){
			newd.setShowwidth(null);
		}
    	if(newd.getIskey()!=null&&newd.getIskey().equals(nullAble(oldd.getIskey()))){
			newd.setIskey(null);
		}
    	if(newd.getIsvisible()!=null&&newd.getIsvisible().equals(nullAble(oldd.getIsvisible()))){
			newd.setIsvisible(null);
		}
    	if(newd.getIsreserve()!=null&&newd.getIsreserve().equals(nullAble(oldd.getIsreserve()))){
			newd.setIsreserve(null);
		}
    	if(newd.getIssum()!=null&&newd.getIssum().equals(nullAble(oldd.getIssum()))){
			newd.setIssum(null);
		}
    	if(newd.getIsregex()!=null&&newd.getIsregex().equals(nullAble(oldd.getIsregex()))){
			newd.setIsregex(null);
		}
		if(newd.getRegexpr()!=null&&newd.getRegexpr().equals(nullAble(oldd.getRegexpr()))){
			newd.setRegexpr(null);
		}
		if(newd.getRegexprinfo()!=null&&newd.getRegexprinfo().equals(nullAble(oldd.getRegexprinfo()))){
			newd.setRegexprinfo(null);
		}
		if(newd.getIsbandcol()!=null&&newd.getIsbandcol().equals(nullAble(oldd.getIsbandcol()))){
			newd.setIsbandcol(null);
		}
		if(newd.getBandcolumnid()!=null&&newd.getBandcolumnid().equals(nullAble(oldd.getBandcolumnid()))){
			newd.setBandcolumnid(null);
		}
		if(newd.getBandrefdwcol()!=null&&newd.getBandrefdwcol().equals(nullAble(oldd.getBandrefdwcol()))){
			newd.setBandrefdwcol(null);
		}
		if(newd.getExtprop()!=null&&newd.getExtprop().equals(nullAble(oldd.getExtprop()))){
			newd.setExtprop(null);
		}
		if(newd.getColtips()!=null&&newd.getColtips().equals(nullAble(oldd.getColtips()))){
			newd.setColtips(null);
		}
		if(newd.getFrmtabid()!=null&&newd.getFrmtabid().equals(nullAble(oldd.getFrmtabid()))){
			newd.setFrmtabid(null);
		}
		if(newd.getFrmcolid()!=null&&newd.getFrmcolid().equals(nullAble(oldd.getFrmcolid()))){
			newd.setFrmcolid(null);
		}
		if(newd.getIsvirtual()!=null&&newd.getIsvirtual().equals(nullAble(oldd.getIsvirtual()))){
			newd.setIsvirtual(null);
		}
		if(newd.getVircontext()!=null&&newd.getVircontext().equals(nullAble(oldd.getVircontext()))){
			newd.setVircontext(null);
		}
		if(newd.getBgtlvl()!=null&&newd.getBgtlvl().equals(nullAble(oldd.getBgtlvl()))){
			newd.setBgtlvl(null);
		}
		if(newd.getIshref()!=null&&newd.getIshref().equals(nullAble(oldd.getIshref()))){
			newd.setIshref(null);
		}
		if(newd.getHrefloc()!=null&&newd.getHrefloc().equals(nullAble(oldd.getHrefloc()))){
			newd.setHrefloc(null);
		}
		if(newd.getHrefparmid()!=null&&newd.getHrefparmid().equals(nullAble(oldd.getHrefparmid()))){
			newd.setHrefparmid(null);
		}
    	return newd;
    }
	
	
    
    
    public static Object nullAble(Object obj){
    	return obj==null?"":obj;
    }
	
	/**
	   * 
	   * @param newd
	   * @param oldd
	   * @return
	   */
		public static DictTModelPO changeDataForDictTModelPOUpdate(DictTModelPO newd,DictTModelPO oldd) {
			
			if(newd.getName()!=null&&newd.getName().equals(nullAble(oldd.getName()))){
				newd.setName(null);
			}
			if(newd.getOrderid()!=null&&newd.getOrderid().equals(nullAble(oldd.getOrderid()))){
				newd.setOrderid(null);
			}
			if(newd.getDbtablename()!=null&&newd.getDbtablename().equals(nullAble(oldd.getDbtablename()))){
				newd.setDbtablename(null);
			}
			if(newd.getTabletype()!=null&&newd.getTabletype().equals(nullAble(oldd.getTabletype()))){
				newd.setTabletype(null);
			}
			if(newd.getIsshow()!=null&&newd.getIsshow().equals(nullAble(oldd.getIsshow()))){
				newd.setIsshow(null);
			}
			if(newd.getAppid()!=null&&newd.getAppid().equals(nullAble(oldd.getAppid()))){
				newd.setAppid(null);
			}
			if(newd.getRemark()!=null&&newd.getRemark().equals(nullAble(oldd.getRemark()))){
				newd.setRemark(null);
			}
			if(newd.getSuitid()!=null&&newd.getSuitid().equals(nullAble(oldd.getSuitid()))){
				newd.setSuitid(null);
			}
			if(newd.getDealtype()!=null&&newd.getDealtype().equals(nullAble(oldd.getDealtype()))){
				newd.setDealtype(null);
			}
			if(newd.getIsreserved()!=null&&newd.getIsreserved().equals(nullAble(oldd.getIsreserved()))){
				newd.setIsreserved(null);
			}
			if(newd.getInputlvl()!=null&&newd.getInputlvl().equals(nullAble(oldd.getInputlvl()))){
				newd.setInputlvl(null);
			}
			if(newd.getIsadd()!=null&&newd.getIsadd().equals(nullAble(oldd.getIsadd()))){
				newd.setIsadd(null);
			}
			if(newd.getShorttitle()!=null&&newd.getShorttitle().equals(nullAble(oldd.getShorttitle()))){
				newd.setShorttitle(null);
			}
			if(newd.getExtprop()!=null&&newd.getExtprop().equals(nullAble(oldd.getExtprop()))){
				newd.setExtprop(null);
			}
			if(newd.getBgtlvl()!=null&&newd.getBgtlvl().equals(nullAble(oldd.getBgtlvl()))){
				newd.setBgtlvl(null);
			}
			if(newd.getSecusql()!=null&&newd.getSecusql().equals(nullAble(oldd.getSecusql()))){
				newd.setSecusql(null);
			}
			if (oldd.getIssumtab().equals(newd.getIssumtab())){
				newd.setIssumtab(null);
			}
			if (oldd.getIsman().equals(newd.getIsman())){
				newd.setIsman(null);
			}
			if(newd.getMainuptab()!=null&&newd.getMainuptab().equals(nullAble(oldd.getMainuptab()))){
				newd.setMainuptab(null);
			}
			if(newd.getRelatab()!=null&&newd.getRelatab().equals(nullAble(oldd.getRelatab()))){
				newd.setRelatab(null);
			}
			if(newd.getTabswhere()!=null&&newd.getTabswhere().equals(nullAble(oldd.getTabswhere()))){
				newd.setTabswhere(null);
			}
			if(oldd.getIspartition().equals(newd.getIspartition())){
				newd.setIspartition(null);
			}
			if (oldd.getIsbak().equals(newd.getIsbak())){
				newd.setIsbak(null);
			}
			if (oldd.getIstask().equals(newd.getIstask())){
				newd.setIstask(null);
			}
			return newd;
		}
		
		
		
		/**
		 * 
		 * @param set
		 * @param keyStr
		 * @return
		 */
		public static Set<String> findCacheKeyOfIndex(Set<String> set ,String[] keyStr){
			Set<String> keys = new HashSet<String>();
			for(String key :set){
				for(String str:keyStr){
				   if(key.indexOf(str)!=-1){
					   keys.add(key);
				    }
				}
			}
			return keys;
		}
}

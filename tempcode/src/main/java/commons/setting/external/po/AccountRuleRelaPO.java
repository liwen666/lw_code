package commons.setting.external.po;

import java.io.Serializable;

public class AccountRuleRelaPO implements Serializable{

	/**
	 * 目标表对应列公式PO
	 */
	private static final long serialVersionUID = 1L;
	//guid
	private String relaId;
	// billdefid
	private String billDefId;
	//目标表列名称
	private String tgtDBColName;
	//列对应公式
	private String srcSql;
	//是否更新主键
	private String isUpdateKey;
	//是否删除主键
	private String isDeleteKey;
	
	
	public String getRelaId() {
		return relaId;
	}
	public void setRelaId(String relaId) {
		this.relaId = relaId;
	}
	public String getBillDefId() {
		return billDefId;
	}
	public void setBillDefId(String billDefId) {
		this.billDefId = billDefId;
	}
	public String getTgtDBColName() {
		return tgtDBColName;
	}
	public void setTgtDBColName(String tgtDBColName) {
		this.tgtDBColName = tgtDBColName;
	}
	public String getSrcSql() {
		return srcSql;
	}
	public void setSrcSql(String srcSql) {
		this.srcSql = srcSql;
	}
	public String getIsUpdateKey() {
		return isUpdateKey;
	}
	public void setIsUpdateKey(String isUpdateKey) {
		this.isUpdateKey = isUpdateKey;
	}
	public String getIsDeleteKey() {
		return isDeleteKey;
	}
	public void setIsDeleteKey(String isDeleteKey) {
		this.isDeleteKey = isDeleteKey;
	}
	
	/**
	 * SELECT A.GENSRCTABID SRCTABLEID,
       (SELECT C.DBTABLENAME
          FROM DICT_T_MODEL C
         WHERE TABLEID = A.GENSRCTABID) SRCDBTABLENAME,
       TO_CHAR(A.SRCWHERE) SRCWHERE,
       A.TGTTABID TGTTABLEID,
       (SELECT C.DBTABLENAME FROM DICT_T_MODEL C WHERE TABLEID = A.TGTTABID) TGTDBTABLENAME,
       (SELECT F.DBCOLUMNNAME
          FROM DICT_T_FACTOR F
         WHERE F.COLUMNID = A.OFFSETCOL) OFFSETCOLNAME,
       (SELECT F.DBCOLUMNNAME
          FROM DICT_T_FACTOR F
         WHERE F.COLUMNID = A.BILLCOL) BILLCOLNAME,
       (SELECT F.DBCOLUMNNAME
          FROM DICT_T_FACTOR F
         WHERE F.COLUMNID = B.TGTCOLID) TGTDBCOLNAME,
       to_char(B.SRCSQL) SRCSQL,
       B.ISUPDATE ISUPDATEKEY,
       B.ISDELETE ISDELETEKEY
  FROM DICT_T_BILLDEF A
  LEFT JOIN DICT_T_TGTRELATION B
    ON A.GUID = B.BILLDEFID
 WHERE A.APPID = 'SPF'
	 */
	
}

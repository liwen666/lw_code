CREATE OR REPLACE PROCEDURE P_CONTROL_SPFID(V_SPFID IN VARCHAR2, V_RESULT OUT VARCHAR2) IS
  V_DBTABLENAME             VARCHAR2(100);
  V_SQL                     VARCHAR2(1000);
  V_FINANCE_ID              VARCHAR2(32);--处室ID
  V_FINANCE_AGENCYID        VARCHAR2(32);--处室的agencyID
  V_DEPT_ID                 VARCHAR2(32);--部门ID
  V_NUM                     INTEGER;
  V_COLUMN1                 VARCHAR2(2048);--A6    列名称
  V_COLUMN2                 VARCHAR2(2048);--专项资金预算支出明细表        列名称
  V_REALID varchar2(32);
BEGIN

  --判断    A6    表是否存在
  select count(1) into V_NUM from dict_t_model where dealtype = 'A6';
  if V_NUM = 1 then
    select dbtablename into V_DBTABLENAME from dict_t_model where dealtype = 'A6';
  else
    V_RESULT := '处理类型为A6的表重复或不存在';
    return;
  end if;

  if V_DBTABLENAME is null then
    V_RESULT := '处理类型类型为A6的表表名为空';
    return;
  end if;

  select count(1) into V_NUM from user_tab_columns where table_name = V_DBTABLENAME and column_name in( 'DATAKEY','AGENCYID','FINANCE_ID','DEPT_ID','SPFID','REALID') ;
  if V_NUM <> 6 then
    V_RESULT := '控制数表中缺少某个默认列DATAKEY AGENCYID FINANCE_ID DEPT_ID SPFID REALID 请检查';
    return;
  end if;

  --查询专项的firagencyid，deptid
  select count(1) into V_NUM from spf_t_fbaseinfo where spfid = V_SPFID;
  if V_NUM = 1 then
    select firagencyid, deptid into V_DEPT_ID, V_FINANCE_ID from spf_t_fbaseinfo where spfid = V_SPFID;
  else
    V_RESULT := 'spf_t_fbaseinfo中重复或不存在此spfid：' || V_SPFID;
    return;
  end if;

  if V_DEPT_ID is null then
    V_RESULT := 'spf_t_fbaseinfo中此项目的专项的firagencyid为空：' || V_SPFID;
    return;
  end if;

  if V_FINANCE_ID is null then
    V_RESULT := 'spf_t_fbaseinfo中此项目的专项的deptid为空：' || V_SPFID;
    return;
  end if;

  --查询处室的agencyid
  select count(1) into V_NUM from CODE_T_FIRAGENCY where guid = V_DEPT_ID;
  if V_NUM = 1 then
    select superguid into V_FINANCE_AGENCYID from CODE_T_FIRAGENCY where guid = V_DEPT_ID;
  else
    V_RESULT := 'CODE_T_FIRAGENCY中重复或不存在此guid：' || V_SPFID;
    return;
  end if;
  if V_FINANCE_AGENCYID is null then
    V_RESULT := 'CODE_T_FIRAGENCY中此部门的superguid为空：' || V_SPFID;
    return;
  end if;

  V_SQL := 'delete from  ' || V_DBTABLENAME ||  ' where spfid = :1 ';

  --删除数据
  EXECUTE IMMEDIATE V_SQL using V_SPFID;

  --查询数据源
  FOR i IN
    (
      SELECT A.DBCOLUMNNAME ADBCOLUMNNAME, B.DBCOLUMNNAME BDBCOLUMNNAME FROM
        (SELECT DBCOLUMNNAME ,DEID FROM DICT_T_FACTOR WHERE TABLEID = (SELECT TABLEID FROM DICT_T_MODEL WHERE DEALTYPE ='A6') and datatype =2) A,
        (SELECT DBCOLUMNNAME ,DEID FROM DICT_T_FACTOR WHERE TABLEID = (SELECT TABLEID FROM DICT_T_MODEL WHERE DEALTYPE ='4*50') and datatype =2) B
      WHERE A.DEID = B.DEID
    )
   LOOP
    --拼数据源列名称
    V_COLUMN1 := V_COLUMN1 || ',' || i.ADBCOLUMNNAME;
    V_COLUMN2 := V_COLUMN2 || ',sum(' || i.BDBCOLUMNNAME || ')';
  END LOOP;
  V_SQL := 'INSERT INTO  ' || V_DBTABLENAME ||
           ' (DATAKEY, AGENCYID, FINANCE_ID, DEPT_ID, SPFID, REALID '||V_COLUMN1 || ')
					 (SELECT SYS_GUID(), :1,:2,:3,:4,:5'|| V_COLUMN2 ||' 
					 FROM SPF_T_FYSZCMXB WHERE SPFID = :6  
					 AND FINYEAR = (GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM(''YEAR'')))';
  --插入财政
  	V_REALID := rawtohex(sys_guid());
  EXECUTE IMMEDIATE V_SQL using V_FINANCE_AGENCYID, V_FINANCE_ID, V_DEPT_ID, V_SPFID,V_REALID, V_SPFID;

  if V_FINANCE_ID <> V_DEPT_ID then
    --插入部门
    EXECUTE IMMEDIATE V_SQL using V_DEPT_ID, V_FINANCE_ID, V_DEPT_ID, V_SPFID,V_REALID, V_SPFID;
  END IF;

END P_CONTROL_SPFID;
--12、存储过程，专项控制数(修改后)
--P_CONTROL_SPFID

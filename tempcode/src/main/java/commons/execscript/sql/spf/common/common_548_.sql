CREATE OR REPLACE PROCEDURE P_CONTROL_PROJECT(V_PROJECTIDS IN VARCHAR2,V_ISCANCLE VARCHAR2, V_RESULT OUT VARCHAR2) IS
  V_DBTABLENAME      VARCHAR2(100);
  V_SQL              VARCHAR2(1000);
  V_FINANCE_ID       VARCHAR2(32); --����ID
  V_FINANCE_AGENCYID VARCHAR2(32); --���ҵ�agencyID
  V_DEPT_ID          VARCHAR2(32); --����ID
  V_AGENCY_ID        VARCHAR2(32); --��Ŀ�ĵ�λID
  V_SPFID            VARCHAR2(32); --��Ŀ��ר��ID
  V_PROJECTID        VARCHAR2(32); --��ĿID
  V_REALID           VARCHAR2(32); 
  V_SQLPROJ          VARCHAR2(32000);
  V_CUR              SYS_REFCURSOR;
  V_NUM              INTEGER;
  V_COLUMN1          VARCHAR2(2048);--A7    ������
  V_COLUMN2          VARCHAR2(2048);--����Ԥ���м��        ������
BEGIN
  --�ж�A7�ı��Ƿ����
  select count(1) into V_NUM from dict_t_model where dealtype = 'A7';
  if V_NUM = 1 then
    select dbtablename into V_DBTABLENAME from dict_t_model where dealtype = 'A7';
  else
    V_RESULT := '��������ΪA7�ı��ظ��򲻴���';
    return;
  end if;

  if V_DBTABLENAME is null then
    V_RESULT := '������������ΪA7�ı����Ϊ��';
    return;
  end if;

  select count(1) into V_NUM from user_tab_columns where table_name = V_DBTABLENAME and column_name in( 'DATAKEY','AGENCYID','FINANCE_ID','DEPT_ID','SPFID','PROJECTID','REALID') ;
  if V_NUM <> 7 then
    V_RESULT := '����������ȱ��ĳ��Ĭ����DATAKEY AGENCYID FINANCE_ID DEPT_ID SPFID PROJECTID ,REALID����';
    return;
  end if;
      --��ѯ����Դ
    FOR i IN
      (
        SELECT A.DBCOLUMNNAME ADBCOLUMNNAME, B.DBCOLUMNNAME BDBCOLUMNNAME FROM
          (SELECT DBCOLUMNNAME,DEID FROM DICT_T_FACTOR WHERE TABLEID = (SELECT TABLEID FROM DICT_T_MODEL WHERE DEALTYPE ='A7') and datatype = 2) A,
          (SELECT DBCOLUMNNAME,DEID FROM DICT_T_FACTOR WHERE TABLEID = (SELECT TABLEID FROM DICT_T_MODEL WHERE DBTABLENAME ='EXP_T_PBUDGETTEMP') and datatype = 2) B
        WHERE A.DEID = B.DEID
      )
     LOOP
      --ƴ����Դ������
      V_COLUMN1 := V_COLUMN1 || ',' || i.ADBCOLUMNNAME;
      V_COLUMN2 := V_COLUMN2 || ',sum(' ||i.BDBCOLUMNNAME||')';
    END LOOP;
    
 V_SQLPROJ := 'SELECT SPFID,PROJECTID FROM spf_t_pbaseinfo T
   WHERE PROJTYPEID IN (SELECT PROJTYPEID FROM SPF_T_PROJECTTYPE WHERE PROJTYPE IN (''2'', ''9''))
     AND PROJECTID IN '||V_PROJECTIDS;
 /* DBMS_OUTPUT.PUT_LINE(V_SQLPROJ);*/
  OPEN V_CUR FOR  V_SQLPROJ;
  BEGIN
    LOOP
      FETCH V_CUR INTO V_SPFID,V_PROJECTID;
      EXIT WHEN V_CUR%NOTFOUND;

      --��ѯ��Ŀ��agencyid��spfid    
      select agencyid, spfid into V_AGENCY_ID, V_SPFID from spf_t_pbaseinfo where projectid = V_PROJECTID;
      
      --��ѯר���firagencyid��deptid
      select firagencyid, deptid into V_DEPT_ID, V_FINANCE_ID from spf_t_fbaseinfo where spfid = V_SPFID;
     
      --��ѯ���ҵ�agencyid
     select superguid into V_FINANCE_AGENCYID from CODE_T_FIRAGENCY where guid = V_DEPT_ID;

      V_SQL := 'delete from  ' || V_DBTABLENAME ||
               ' where agencyid = :1 and finance_id = :2 and dept_id = :3 and spfid = :4 and projectid = :5 ';
      EXECUTE IMMEDIATE V_SQL
        using V_FINANCE_AGENCYID, V_FINANCE_ID, V_DEPT_ID, V_SPFID, V_PROJECTID;
      EXECUTE IMMEDIATE V_SQL
        using V_DEPT_ID, V_FINANCE_ID, V_DEPT_ID, V_SPFID, V_PROJECTID;
      EXECUTE IMMEDIATE V_SQL
        using V_AGENCY_ID, V_FINANCE_ID, V_DEPT_ID, V_SPFID, V_PROJECTID;

      --����REALID
       V_REALID := rawtohex(sys_guid());
 
      IF V_ISCANCLE='0' then
        V_SQL := 'insert into  ' || V_DBTABLENAME ||
                 ' (datakey, agencyid, finance_id, dept_id, spfid, projectid,REALID '||V_COLUMN1 || ') (SELECT sys_guid(), :1,:2,:3,:4,:5,:6  '|| V_COLUMN2 ||' FROM EXP_T_PBUDGETTEMP WHERE  PROJECTID = :7  and finyear=GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM(''YEAR''))';
        EXECUTE IMMEDIATE V_SQL
          using V_FINANCE_AGENCYID, V_FINANCE_ID, V_DEPT_ID, V_SPFID, V_PROJECTID,V_REALID,V_PROJECTID;
        select count(1) into V_NUM from Code_t_Firagency where ISCZ ='0' AND  guid = V_DEPT_ID;
        if V_NUM = 1 then
           EXECUTE IMMEDIATE V_SQL
             using V_DEPT_ID, V_FINANCE_ID, V_DEPT_ID, V_SPFID, V_PROJECTID,V_REALID,V_PROJECTID;
         end if;
        EXECUTE IMMEDIATE V_SQL
          using V_AGENCY_ID, V_FINANCE_ID, V_DEPT_ID, V_SPFID, V_PROJECTID,V_REALID,V_PROJECTID;
        END IF;

    END LOOP;
  EXCEPTION WHEN OTHERS THEN
    CLOSE V_CUR;
    RAISE;
  END;
  CLOSE V_CUR;
  end P_CONTROL_PROJECT;

--P_CONTROL_PROJECT

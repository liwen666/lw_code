DECLARE
  V_RET VARCHAR2(4000);
BEGIN
  FOR C_PAR IN (SELECT DISTRICTID,YEAR FROM PUB_T_PARTITION_DIVID WHERE YEAR <> '*') LOOP
    SELECT GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_SETPARM('',C_PAR.DISTRICTID,C_PAR.YEAR,'') INTO V_RET FROM DUAL;

delete from DICT_T_PUBLIC t where typeid = 'FORMULA_COMPUTE';
insert into DICT_T_PUBLIC (APPID, TYPEID, TYPENAME, KEYID, KEYNAME, ISYES, RESERVE_1, RESERVE_2, CNUM, REMARK, STATUS)
values ( 'BGT', 'FORMULA_COMPUTE', '���ʽ6��������ģ��', '001', '���ʽ��������ģ��', '1', null, null, 0, 'CREATE OR REPLACE TRIGGER @REP1@_TR_6
  BEFORE DELETE OR INSERT  OR UPDATE OF @REP6@ @REP2@ ON @REP1@ FOR EACH ROW
DECLARE
  --��ǰ�������г���
  V_TABLEID    VARCHAR2(32);
  V_UPDATE_COL VARCHAR2(30000);
  V_SQL        VARCHAR2(32000);
  V_DBNAME     VARCHAR2(100);
  V_AGENCYID      VARCHAR2(32);
  --
  V_PROJTYPEID VARCHAR2(32);
  V_EXPECOID VARCHAR2(32);
  V_RELACOL    VARCHAR2(30000);
  V_COLNAME VARCHAR2(32);
BEGIN
  --��ʼ����ֵ
  V_TABLEID := ''@REP3@'';
  IF INSERTING THEN
     V_AGENCYID   := :NEW.AGENCYID;
  ELSE
     V_AGENCYID   := :OLD.AGENCYID;
  END IF;
  --�õ�������
  IF UPDATING THEN --ֻ�и���ʱִ��
    V_UPDATE_COL :=V_UPDATE_COL||''^''
                   @REP4@;
  END IF;
  --�õ���ǰ��������
  --SELECT DBTABLENAME INTO V_DBNAME FROM DICT_T_MODEL WHERE TABLEID = ''@REP5@'';
  --�����������ֱ��ı����й�ʽ
  FOR C_RECORD IN (SELECT X.TABLEID,Y.DBTABLENAME,X.PROJTYPEID,X.EXPECOID,X.FUNDSOURCEID,Z.DBCOLUMNNAME
,CASE WHEN COUNT(*) = 2 THEN MAX(CASE WHEN X.AGENCYID = ''*'' THEN '''' ELSE X.FORMULAID END) ELSE MAX(X.FORMULAID) END AS FORMULAID
  FROM CAL_T_OUTWINDOWSET X,DICT_T_MODEL Y,DICT_T_FACTOR Z
 WHERE X.AGENCYID IN (''*'',V_AGENCYID)
   AND X.FORMULAID NOT IN (''001'',''002'')
   AND X.EXPECOID <> ''*''
   AND X.TABLEID = Y.TABLEID
   AND X.TABLEID = Z.TABLEID
   AND X.FUNDSOURCEID = Z.COLUMNID
 GROUP BY X.TABLEID,Y.DBTABLENAME,X.PROJTYPEID,X.EXPECOID,X.FUNDSOURCEID,Z.DBCOLUMNNAME) LOOP
    V_DBNAME := C_RECORD.DBTABLENAME;
    V_COLNAME := C_RECORD.DBCOLUMNNAME;
    --SELECT DBCOLUMNNAME INTO V_COLNAME FROM DICT_T_FACTOR WHERE TABLEID = C_RECORD.TABLEID AND COLUMNID = C_RECORD.FUNDSOURCEID;
    --�õ����µ��У���������PROJTYPEID��EXPECOID
    V_PROJTYPEID := C_RECORD.PROJTYPEID;
    V_EXPECOID:= C_RECORD.EXPECOID;
    --�õ������
    SELECT NVL((SELECT REPLACE(CONNSTRA('',''||DICT_T_FACTOR.DBCOLUMNNAME||'',''),'',,'','','') FROM CAL_T_FORMULA,DICT_T_FACTOR
    WHERE DICT_T_FACTOR.TABLEID = V_TABLEID AND CAL_T_FORMULA.FORMULAID= C_RECORD.FORMULAID AND INSTR(CAL_T_FORMULA.REFCOLID,DICT_T_FACTOR.COLUMNID,1,1) > 0),''*'')
    INTO V_RELACOL FROM DUAL;
    ----
    IF DELETING OR INSERTING OR UPDATING(''STATUS'') OR ((PKG_FORMULA.ISEQUSTR(V_UPDATE_COL ,V_RELACOL) AND UPDATING) @REP7@ ) THEN
      --���±����±���ص�NEEDUPDATE
      V_SQL := ''UPDATE ''||V_DBNAME||'' SET NEEDUPDATE = NVL(NEEDUPDATE,'''' '''')||'''',''||V_COLNAME||'',6,'''' WHERE FINYEAR=''''@REP8@'''' AND AGENCYID = ''''''||V_AGENCYID||'''''' AND PROJTYPEID =''''''||V_PROJTYPEID||''''''  AND EXPECOID=''''''||V_EXPECOID||''''''  AND INSTR(NVL(NEEDUPDATE,'''' ''''),'''',''||V_COLNAME||'',6,'''',1,1) < 1'';
      EXECUTE IMMEDIATE V_SQL;
    END IF;
  END LOOP;
END @REP1@_TR_6;', '1');


insert into DICT_T_PUBLIC (APPID, TYPEID, TYPENAME, KEYID, KEYNAME, ISYES, RESERVE_1, RESERVE_2, CNUM, REMARK, STATUS)
values ( 'BGT', 'FORMULA_COMPUTE', '���ʽ6��ͼ������ģ��', '002', '���ʽ��ͼ������ģ��', '1', null, null, 0, 'CREATE OR REPLACE TRIGGER @REP1@_TR_6
  INSTEAD OF DELETE OR INSERT  OR UPDATE ON @REP1@ FOR EACH ROW
DECLARE
  --��ǰ�������г���
  V_TABLEID    VARCHAR2(32);
  V_UPDATE_COL VARCHAR2(30000);
  V_SQL        VARCHAR2(32000);
  V_DBNAME     VARCHAR2(100);
  V_AGENCYID      VARCHAR2(32);
  --
  V_PROJTYPEID VARCHAR2(32);
  V_EXPECOID VARCHAR2(32);
  V_RELACOL    VARCHAR2(30000);
  V_COLNAME VARCHAR2(32);
BEGIN
  --��ʼ����ֵ
  V_TABLEID := ''@REP3@'';
  IF INSERTING THEN
     V_AGENCYID   := :NEW.AGENCYID;
  ELSE
     V_AGENCYID   := :OLD.AGENCYID;
  END IF;
  --�õ�������
  IF UPDATING THEN --ֻ�и���ʱִ��
    V_UPDATE_COL :=V_UPDATE_COL||''^''
                   @REP4@;
  END IF;
  --�õ���ǰ��������
  --SELECT DBTABLENAME INTO V_DBNAME FROM DICT_T_MODEL WHERE TABLEID = ''@REP5@'';
  --�����������ֱ��ı����й�ʽ
  FOR C_RECORD IN (SELECT X.TABLEID,Y.DBTABLENAME,X.PROJTYPEID,X.EXPECOID,X.FUNDSOURCEID,Z.DBCOLUMNNAME
,CASE WHEN COUNT(*) = 2 THEN MAX(CASE WHEN X.AGENCYID = ''*'' THEN '''' ELSE X.FORMULAID END) ELSE MAX(X.FORMULAID) END AS FORMULAID
  FROM CAL_T_OUTWINDOWSET X,DICT_T_MODEL Y,DICT_T_FACTOR Z
 WHERE X.AGENCYID IN (''*'',V_AGENCYID)
   AND X.FORMULAID NOT IN (''001'',''002'')
   AND X.EXPECOID <> ''*''
   AND X.TABLEID = Y.TABLEID
   AND X.TABLEID = Z.TABLEID
   AND X.FUNDSOURCEID = Z.COLUMNID
 GROUP BY X.TABLEID,Y.DBTABLENAME,X.PROJTYPEID,X.EXPECOID,X.FUNDSOURCEID,Z.DBCOLUMNNAME) LOOP
    V_DBNAME := C_RECORD.DBTABLENAME;
    V_COLNAME := C_RECORD.DBCOLUMNNAME;
    --SELECT DBCOLUMNNAME INTO V_COLNAME FROM DICT_T_FACTOR WHERE TABLEID = C_RECORD.TABLEID AND COLUMNID = C_RECORD.FUNDSOURCEID;
    --�õ����µ��У���������PROJTYPEID��EXPECOID
    V_PROJTYPEID := C_RECORD.PROJTYPEID;
    V_EXPECOID:= C_RECORD.EXPECOID;
    --�õ������
    SELECT NVL((SELECT REPLACE(CONNSTRA('',''||DICT_T_FACTOR.DBCOLUMNNAME||'',''),'',,'','','') FROM CAL_T_FORMULA,DICT_T_FACTOR
    WHERE DICT_T_FACTOR.TABLEID = V_TABLEID AND CAL_T_FORMULA.FORMULAID= C_RECORD.FORMULAID AND INSTR(CAL_T_FORMULA.REFCOLID,DICT_T_FACTOR.COLUMNID,1,1) > 0),''*'')
    INTO V_RELACOL FROM DUAL;
    ----
    IF DELETING OR INSERTING OR UPDATING(''STATUS'') OR ((PKG_FORMULA.ISEQUSTR(V_UPDATE_COL ,V_RELACOL) AND UPDATING) @REP7@ ) THEN
      --���±����±���ص�NEEDUPDATE
      V_SQL := ''UPDATE ''||V_DBNAME||'' SET NEEDUPDATE = NVL(NEEDUPDATE,'''' '''')||'''',''||V_COLNAME||'',6,'''' WHERE FINYEAR=''''@REP8@'''' AND AGENCYID = ''''''||V_AGENCYID||'''''' AND PROJTYPEID =''''''||V_PROJTYPEID||''''''  AND EXPECOID=''''''||V_EXPECOID||''''''  AND INSTR(NVL(NEEDUPDATE,'''' ''''),'''',''||V_COLNAME||'',6,'''',1,1) < 1'';
      EXECUTE IMMEDIATE V_SQL;
    END IF;
  END LOOP;
END @REP1@_TR_6;', '1');
END LOOP;
END;


--���ʽ������ģ��
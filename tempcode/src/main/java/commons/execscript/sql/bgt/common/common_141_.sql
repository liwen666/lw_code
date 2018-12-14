--�Զ�����Ԥ������ܱ�
CREATE OR REPLACE PROCEDURE P_CREATE_VIEW_COLIDATA(P_APPID VARCHAR2) IS
  V_SQL VARCHAR2(32760);
  V_COLLIST VARCHAR2(4000);
  V_VIEW_NAME VARCHAR2(30);
  V_CNNAME VARCHAR2(200);
  P_SRC_TABLEID VARCHAR2(32);
  P_SRC_SUITID VARCHAR2(32);
  P_DEST_TABLEID VARCHAR2(32);
BEGIN
  --֧����Ŀ��ϸ����tableID
  SELECT TABLEID,SUITID into P_SRC_TABLEID,P_SRC_SUITID FROM DICT_T_MODEL WHERE APPID = P_APPID AND DEALTYPE = '2101';
  if P_SRC_TABLEID is null or P_SRC_SUITID is null then
    return;
  end if;
  V_CNNAME := 'Ԥ������ܱ�';
  V_VIEW_NAME := P_APPID||'_V_COLIDATA';
  --��ȡԤ������ܱ���tableID�����û�в鵽��������һ��
  SELECT TABLEID into P_DEST_TABLEID FROM DICT_T_MODEL WHERE APPID = P_APPID AND DEALTYPE = 'D0';
  if P_DEST_TABLEID is null then
    insert into DICT_T_MODEL (APPID, BGTLVL, DBTABLENAME, DBVERSION, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, NAME, ORDERID, RELATAB, REMARK, SECUSQL, INITSQL, INITSQLTIME, SHORTTITLE, STATUS, SUITID, TABLEID, TABLETYPE, TABSWHERE, ISBAK, INSERTVERSION, ISALLDISTRICT, ISALLYEAR, INITSQLBTNNAME, ISEXCELIMP, DESCFILE)
    values (P_APPID, null, V_VIEW_NAME, sysdate(), 'D0', '0000000000000000000000000000000', '3', null, null, '0', '0', '1', '0', '0', null, V_CNNAME, 305, null, null, null, null, '1', null, '1', P_SRC_SUITID, sys_guid(), '2', null, '0',  sysdate(), '0', '0', null, null, null);
    SELECT TABLEID into P_DEST_TABLEID FROM DICT_T_MODEL WHERE APPID = P_APPID AND DEALTYPE = 'D0';
  end if;
  
  --ɾ�� ���� Ĭ���й����� �е��� �� ���� �����ʽ���Դ �е���
  DELETE FROM DICT_T_FACTOR 
  WHERE TABLEID = P_DEST_TABLEID
  and trim(DBCOLUMNNAME) not in (select trim(DBCOLUMNNAME) from dict_t_defaultcol c where dealid = 'D0')
  and trim(DBCOLUMNNAME) not in (select trim(Y.TOBASECOL) from FASP_V_PUBBGTSOURCE X, EXP_S_PROJINVEST Y where X.GUID = Y.FUNDSOURCEID);
  --��Ĭ���й�������ȡ����
  INSERT INTO DICT_T_FACTOR( ALIAS, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISBANDCOL, OPENWINDOWTYPE,ISKEY,ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE,LEVELNO, NAME, NULLABLE, ORDERID, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS,SUPERID,TABLEID)
  select null, null, null, name, sys_guid(), csid, datalength, datatype, trim(dbcolumnname), defaultvalue, dealid, '0', '0', islogickey, '1', '0', isreserve, '0', isupdate, isvisible, '1', name, '1', orderid, scale, null, null, null, status, '0', P_DEST_TABLEID TABLEID
  from dict_t_defaultcol c 
  where dealid = 'D0'
  and trim(DBCOLUMNNAME) not in (select trim(DBCOLUMNNAME) from DICT_T_FACTOR WHERE TABLEID = P_DEST_TABLEID);
  --ȡ֧����Ŀ��ϸ��BGT_T_ZCXMMXB�������ʽ���Դ��
  INSERT INTO DICT_T_FACTOR (ALIAS,BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISBANDCOL, OPENWINDOWTYPE,ISKEY,ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE,LEVELNO, NAME,NULLABLE, ORDERID, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS,SUPERID,TABLEID)
  SELECT ALIAS,BGTLVL, COLFORMAT, COLTIPS, sys_guid(), CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISBANDCOL, OPENWINDOWTYPE,ISKEY,'1',ISREGEX,ISRESERVE,'0','0',ISVIRTUAL,'1','1', NAME,NULLABLE,50, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS,'0', P_DEST_TABLEID TABLEID
  FROM DICT_T_FACTOR 
  WHERE TABLEID = P_SRC_TABLEID
  and trim(DBCOLUMNNAME) in (select trim(Y.TOBASECOL) from FASP_V_PUBBGTSOURCE X, EXP_S_PROJINVEST Y where X.GUID = Y.FUNDSOURCEID)
  and trim(DBCOLUMNNAME) not in (select trim(DBCOLUMNNAME) from DICT_T_FACTOR WHERE TABLEID = P_DEST_TABLEID);

  SELECT substr(CONNSTRA(DBCOLUMNNAME||', '''' '), 0, length(CONNSTRA(DBCOLUMNNAME||', '''' ')) - 5) INTO V_COLLIST FROM DICT_T_FACTOR WHERE TABLEID = P_DEST_TABLEID AND ISLEAF = '1';

  V_SQL := 'CREATE OR REPLACE VIEW '||V_VIEW_NAME||' AS SELECT '''' '||V_COLLIST||' FROM DUAL';
    EXECUTE IMMEDIATE V_SQL;
  RETURN;
END P_CREATE_VIEW_COLIDATA;

--�ۺ� �ű�2 �Զ�����Ԥ������ܱ�P_CREATE_VIEW_COLIDATA
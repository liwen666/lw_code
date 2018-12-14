CREATE OR REPLACE PROCEDURE P_VIEW_ACCOUNT(P_SRC_TABLEID VARCHAR2,P_BILLDEFID VARCHAR2,P_TABLEID VARCHAR2) IS
    V_TABLE VARCHAR2(3000);
    V_SQL VARCHAR2(32760);
    V_COLLIST VARCHAR2(4000);
    V_VIEW_NAME VARCHAR2(30);
    V_CNNAME VARCHAR2(200);
    V_CNT NUMBER;
    V_EXISTS NUMBER;
    TYPE R_ID_NAME IS RECORD
  (
    COLUMNID VARCHAR2(32),
    DBCOLUMNNAME VARCHAR2(30)
  );
  TYPE R_LIST IS TABLE OF R_ID_NAME;
  V_COLARRAY R_LIST;
  V_INDEX BINARY_INTEGER;
  V_SUFIX VARCHAR2(30);
  BEGIN

    SELECT DBTABLENAME,NAME INTO V_TABLE,V_CNNAME FROM DICT_T_MODEL WHERE TABLEID = P_SRC_TABLEID;
    V_VIEW_NAME := V_TABLE||'_TP';
    V_CNNAME := V_CNNAME||'(记账)';

    SELECT CONNSTRA(X.DBCOLUMNNAME||','),COUNT(*) INTO V_COLLIST,V_CNT
    FROM DICT_T_FACTOR X,DICT_T_BILLBASIC Y
    WHERE X.COLUMNID = Y.COLUMNID
    AND Y.BILLDEFID = P_BILLDEFID
    AND X.TABLEID = P_SRC_TABLEID;

    SELECT COUNT(*) INTO V_EXISTS FROM DICT_T_MODEL WHERE TABLEID = P_TABLEID;
    IF V_EXISTS < 1 THEN
      SELECT MAX(SUBSTR(DBTABLENAME,-3)) INTO V_SUFIX FROM P#DICT_T_MODEL WHERE REGEXP_LIKE(DBTABLENAME,'^'||V_VIEW_NAME||'[0-9]{3}$');
      V_SUFIX := NVL(TRIM(V_SUFIX),'000');
      V_VIEW_NAME := V_VIEW_NAME||TRIM(TO_CHAR(TO_NUMBER(V_SUFIX) + 1,'000'));

      INSERT INTO DICT_T_MODEL(APPID, BGTLVL, DBTABLENAME, DEALTYPE, EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION, ISRESERVED, ISSHOW, ISTASK, ISSUMTAB, MAINUPTAB, NAME, ORDERID, RELATAB, REMARK, SECUSQL, INITSQLTIME, SHORTTITLE, SUITID, TABLEID, TABLETYPE,ISBAK,ISALLDISTRICT)
      SELECT APPID, BGTLVL,V_VIEW_NAME DBTABLENAME, 'A0' DEALTYPE, RPAD('0',100,'0') EXTPROP, INPUTLVL, ISADD, ISMAN, ISPARTITION,'0' ISRESERVED,'1' ISSHOW,'0' ISTASK, ISSUMTAB, MAINUPTAB, V_CNNAME NAME, ORDERID, RELATAB, REMARK, SECUSQL,'1' INITSQLTIME, SHORTTITLE, SUITID, P_TABLEID TABLEID, '2' TABLETYPE,'0' ISBAK,ISALLDISTRICT
      FROM DICT_T_MODEL
      WHERE TABLEID = P_SRC_TABLEID;

      INSERT INTO DICT_T_FACTOR(ALIAS,BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISBANDCOL, OPENWINDOWTYPE,ISKEY,ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE,LEVELNO, NAME,NULLABLE, ORDERID, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS,SUPERID,TABLEID)
      SELECT ALIAS,BGTLVL, COLFORMAT, COLTIPS, RAWTOHEX(SYS_GUID()) COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, '0' ISBANDCOL, OPENWINDOWTYPE,'0' ISKEY,'1' ISLEAF,'0' ISREGEX,'1' ISRESERVE,'0' ISSUM,'0' ISUPDATE,'0' ISVIRTUAL,'1' ISVISIBLE,1 LEVELNO, NAME,'0' NULLABLE,ROWNUM ORDERID, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS,'0' SUPERID,P_TABLEID TABLEID
      FROM DICT_T_FACTOR
      WHERE TABLEID = P_SRC_TABLEID
      AND (COLUMNID IN (SELECT Y.COLUMNID FROM DICT_T_BILLBASIC Y WHERE Y.BILLDEFID = P_BILLDEFID)
      OR DBCOLUMNNAME = 'DATAKEY' );

      --INSERT INTO DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME,  DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT,  PARENTNODECANCHECK, XLSXCOLUMNID)
      --VALUES ('资金来源', NULL, NULL, NULL, NULL, NULL, RAWTOHEX(SYS_GUID()), NULL, 32, 3, 'FUNDSOURCEGUID',  NULL, NULL, '0000000000000000000000000000000', NULL, NULL, NULL, NULL, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '资金来源', '1', V_CNT + 1, NULL, NULL, 0, '0', NULL, '1', '0', P_TABLEID, NULL,  '0', NULL);

      --INSERT INTO DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME,  DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT,  PARENTNODECANCHECK, XLSXCOLUMNID)
      --VALUES ('金额', NULL, NULL, NULL, NULL, NULL, RAWTOHEX(SYS_GUID()), NULL, 24, 2, 'AMT',  NULL, NULL, '0000000000000000000000000000000', NULL, NULL, NULL, NULL, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '金额', '1', V_CNT + 2, NULL, NULL, 6, '0', NULL, '1', '0', P_TABLEID, NULL,  '0', NULL);
    ELSE
      SELECT DBTABLENAME INTO V_SUFIX FROM DICT_T_MODEL WHERE TABLEID = P_TABLEID;
      IF NOT REGEXP_LIKE(V_SUFIX,'^'||V_VIEW_NAME||'[0-9]{3}$') THEN
        SELECT MAX(SUBSTR(DBTABLENAME,-3)) INTO V_SUFIX FROM P#DICT_T_MODEL WHERE REGEXP_LIKE(DBTABLENAME,'^'||V_VIEW_NAME||'[0-9]{3}$')
        AND (PROVINCE,YEAR) NOT IN (SELECT GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID') PROVINCE,GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR') YEAR FROM DUAL)
        AND STATUS = '1';
        V_SUFIX := NVL(TRIM(V_SUFIX),'000');
        V_VIEW_NAME := V_VIEW_NAME||TRIM(TO_CHAR(TO_NUMBER(V_SUFIX) + 1,'000'));
        UPDATE DICT_T_MODEL SET DBTABLENAME = V_VIEW_NAME WHERE TABLEID = P_TABLEID;
      ELSE
        V_VIEW_NAME := V_SUFIX;
      END IF;

      --先保存已经存在的末级列ID
      SELECT COLUMNID,DBCOLUMNNAME BULK COLLECT INTO V_COLARRAY FROM DICT_T_FACTOR WHERE TABLEID = P_TABLEID AND ISLEAF = '1';
      --删除
      DELETE FROM DICT_T_FACTOR WHERE TABLEID = P_TABLEID;

      INSERT INTO DICT_T_FACTOR(ALIAS,BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, ISBANDCOL, OPENWINDOWTYPE,ISKEY,ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE,LEVELNO, NAME,NULLABLE, ORDERID, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS,SUPERID,TABLEID)
      SELECT ALIAS,BGTLVL, COLFORMAT, COLTIPS, RAWTOHEX(SYS_GUID()) COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, '0' ISBANDCOL, OPENWINDOWTYPE,'0' ISKEY,'1' ISLEAF,'0' ISREGEX,'1' ISRESERVE,'0' ISSUM,'0' ISUPDATE,'0' ISVIRTUAL,'1' ISVISIBLE,1 LEVELNO, NAME,'0' NULLABLE,ROWNUM ORDERID, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS,'0' SUPERID,P_TABLEID TABLEID
      FROM DICT_T_FACTOR
      WHERE TABLEID = P_SRC_TABLEID
      AND (COLUMNID IN (SELECT Y.COLUMNID FROM DICT_T_BILLBASIC Y WHERE Y.BILLDEFID = P_BILLDEFID)
      OR DBCOLUMNNAME = 'DATAKEY' );

      --INSERT INTO DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME,  DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT,  PARENTNODECANCHECK, XLSXCOLUMNID)
      --VALUES ('资金来源', NULL, NULL, NULL, NULL, NULL, RAWTOHEX(SYS_GUID()), NULL, 32, 3, 'FUNDSOURCEGUID',  NULL, NULL, '0000000000000000000000000000000', NULL, NULL, NULL, NULL, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '资金来源', '1', V_CNT + 1, NULL, NULL, 0, '0', NULL, '1', '0', P_TABLEID, NULL,  '0', NULL);

      --INSERT INTO DICT_T_FACTOR (ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME,  DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT,  PARENTNODECANCHECK, XLSXCOLUMNID)
      --VALUES ('金额', NULL, NULL, NULL, NULL, NULL, RAWTOHEX(SYS_GUID()), NULL, 24, 2, 'AMT',  NULL, NULL, '0000000000000000000000000000000', NULL, NULL, NULL, NULL, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '金额', '1', V_CNT + 2, NULL, NULL, 6, '0', NULL, '1', '0', P_TABLEID, NULL,  '0', NULL);

      V_INDEX := V_COLARRAY.FIRST;
      --处理缓存的列
      WHILE V_INDEX <= V_COLARRAY.LAST LOOP
        UPDATE DICT_T_FACTOR SET COLUMNID = V_COLARRAY(V_INDEX).COLUMNID
        WHERE TABLEID = P_TABLEID AND DBCOLUMNNAME = V_COLARRAY(V_INDEX).DBCOLUMNNAME AND ISLEAF = '1';
        V_INDEX := V_COLARRAY.NEXT(V_INDEX);
      END LOOP;
    END IF;
    --V_TABLE := 'SELECT '||V_COLLIST||' FROM '||V_TABLE;
    --V_COLLIST := TRIM(TRAILING ',' FROM V_COLLIST);
    V_SQL := 'CREATE OR REPLACE VIEW '||V_VIEW_NAME||' AS
SELECT '||V_COLLIST||'DATAKEY,DBVERSION
FROM '||V_TABLE ||'';
    EXECUTE IMMEDIATE V_SQL;

  RETURN;
END P_VIEW_ACCOUNT;

--P_VIEW_ACCOUNT

CREATE OR REPLACE PROCEDURE SYS_P_CREATE_INDI_VIEW(P_TABLEID VARCHAR2) IS
  V_TABLE VARCHAR2(30);
  V_SQL VARCHAR2(4000);
BEGIN
  SELECT DBTABLENAME INTO V_TABLE FROM DICT_T_MODEL WHERE TABLEID = P_TABLEID;
  V_SQL := 'CREATE OR REPLACE VIEW '||V_TABLE||'_INDI AS
SELECT ';
  FOR C_COL IN (SELECT DBCOLUMNNAME,
       (SELECT ELEMENTCODE FROM FASP_T_DICDE WHERE GUID = DEID) AS DECOLUMN
       FROM DICT_T_FACTOR
        WHERE TABLEID = P_TABLEID) LOOP

    V_SQL := V_SQL ||C_COL.DBCOLUMNNAME||',';
    IF C_COL.DECOLUMN IS NOT NULL THEN
      IF C_COL.DECOLUMN <> C_COL.DBCOLUMNNAME THEN
        V_SQL := V_SQL ||C_COL.DBCOLUMNNAME||' AS '||C_COL.DECOLUMN||',';
      END IF;
    END IF;
  END LOOP;

  V_SQL := REGEXP_REPLACE(V_SQL,',$');
  V_SQL := V_SQL||'
FROM '||V_TABLE;
  EXECUTE IMMEDIATE V_SQL;

END SYS_P_CREATE_INDI_VIEW;
--���鴺_SYS_P_CREATE_INDI_VIEW

DECLARE
  V_SQL VARCHAR2(32000);
	V_CNT NUMBER;
BEGIN
	SELECT COUNT(*) INTO V_CNT FROM USER_TAB_COLS WHERE TABLE_NAME = 'P#BIL_T_MENUBILLTYPE' AND COLUMN_NAME = 'PROVINCE';
	IF V_CNT < 1 THEN
		RETURN;
	END IF;
V_SQL := 'CREATE TABLE T_TMP_MENUBILLTYPE AS 
SELECT MENUGUID,BILLTYPEID,STATUS,MENUTYPE,SYSTIMESTAMP DBVERSION FROM(
SELECT DISTINCT MENUGUID,BILLTYPEID,STATUS,MENUTYPE
 FROM P#BIL_T_MENUBILLTYPE)';
EXECUTE IMMEDIATE V_SQL;

V_SQL := 'ALTER TABLE T_TMP_MENUBILLTYPE MODIFY STATUS DEFAULT ''1''';
EXECUTE IMMEDIATE V_SQL;


V_SQL := 'DROP TABLE P#BIL_T_MENUBILLTYPE';
EXECUTE IMMEDIATE V_SQL;
V_SQL := 'RENAME T_TMP_MENUBILLTYPE TO P#BIL_T_MENUBILLTYPE';
EXECUTE IMMEDIATE V_SQL;
V_SQL := 'ALTER TABLE P#BIL_T_MENUBILLTYPE ADD CONSTRAINT PK_BIL_T_MENUBILLTYPE  PRIMARY KEY(STATUS, MENUGUID, BILLTYPEID)';
EXECUTE IMMEDIATE V_SQL;
V_SQL := Q'{CREATE OR REPLACE VIEW BIL_T_MENUBILLTYPE AS
SELECT BILLTYPEID, DBVERSION, MENUGUID, MENUTYPE, STATUS
  FROM P#BIL_T_MENUBILLTYPE
 WHERE  STATUS = '1'}';
EXECUTE IMMEDIATE V_SQL;
V_SQL := Q'{CREATE OR REPLACE TRIGGER TR_P#BIL_T_MENUBILLTYPE
 BEFORE INSERT OR UPDATE ON P#BIL_T_MENUBILLTYPE FOR EACH ROW 
BEGIN 
  IF INSERTING THEN 
    :NEW.DBVERSION:=CASE WHEN TO_CHAR(:NEW.DBVERSION,'YYYY-MM-DD') ='2012-01-01' THEN TO_DATE('2012-01-01','YYYY-MM-DD') ELSE SYSTIMESTAMP END;
  END IF; 
  IF (TO_CHAR(:NEW.DBVERSION,'YYYY-MM-DD') ='2012-01-01' AND ((UPDATING AND UPDATING('DBVERSION')) OR INSERTING )) THEN 
    RETURN ; 
  END IF; 
  IF UPDATING THEN 
    :NEW.DBVERSION:=SYSTIMESTAMP; 
  END IF; 
END  TR_P#BIL_T_MENUBILLTYPE;}';

EXECUTE IMMEDIATE V_SQL;
END;

--修改P#BIL_T_MENUBILLTYPE表为非分区表

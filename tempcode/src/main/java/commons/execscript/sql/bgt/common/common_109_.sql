CREATE OR REPLACE TRIGGER "TR_P#CDT_T_TASKTYPE"
BEFORE INSERT OR UPDATE ON  P#CDT_T_TASKTYPE FOR EACH ROW 
BEGIN 
  IF INSERTING THEN 
    :NEW.YEAR:=NVL(:NEW.YEAR,GLOBAL_MULTYEAR_CZ.V_PMYEAR); 
    :NEW.DBVERSION := CASE WHEN TO_CHAR(:NEW.DBVERSION, 'YYYY-MM-DD') = '2012-01-01' THEN TO_DATE('2012-01-01', 'YYYY-MM-DD') ELSE SYSDATE END; 
  END IF; 
  IF (TO_CHAR(:NEW.DBVERSION,'YYYY-MM-DD') ='2012-01-01' AND ((UPDATING AND UPDATING('DBVERSION')) OR INSERTING ) ) THEN 
    RETURN ;
  END IF; 
  IF UPDATING THEN 
    :NEW.DBVERSION:=SYSDATE;
  END IF;
END P#CDT_T_TASKTYPE;
--����CDT_T_TASKTYPE������

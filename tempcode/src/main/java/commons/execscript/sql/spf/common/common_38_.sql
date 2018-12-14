BEGIN
  EXECUTE IMMEDIATE Q'{CREATE OR REPLACE TRIGGER TR_SPF_T_PBASEINFO_CZHD
  AFTER INSERT OR UPDATE OR DELETE
  ON P#SPF_T_PBASEINFO 
  FOR EACH ROW
DECLARE
  V_PROVINCE P#SPF_T_PBASEINFO.PROVINCE%TYPE;
  V_YEAR P#SPF_T_PBASEINFO.YEAR%TYPE;
  V_AGENCYID P#SPF_T_FBASEINFO.AGENCYID%TYPE;
  V_SPFID P#SPF_T_PBASEINFO.SPFID%TYPE;
  V_PROJETID P#SPF_T_PBASEINFO.PROJECTID%TYPE;
  V_SFZDHZ P#SPF_T_FBASEINFO.SFZDHZ%TYPE;
  V_NUM NUMBER;
BEGIN
  IF (INSERTING AND :NEW.STATUS = '1' AND :NEW.CZHD = '1') 
    OR (UPDATING AND (
                      (:NEW.STATUS = '1' AND NVL(:OLD.CZHD,'0') <> NVL(:NEW.CZHD,'0')) 
                   OR (:OLD.STATUS = '1' AND :NEW.STATUS <> '1' AND :OLD.CZHD = '1')
                      )
        ) 
    OR (DELETING AND :OLD.STATUS = '1' AND :OLD.CZHD = '1') THEN
    V_PROVINCE := :NEW.PROVINCE;
    V_YEAR := :NEW.YEAR;
    IF INSERTING OR UPDATING THEN
      V_PROVINCE := :NEW.PROVINCE;
      V_YEAR := :NEW.YEAR;
      V_SPFID :=:NEW.SPFID;
      V_PROJETID :=:NEW.PROJECTID;
    ELSE
      V_PROVINCE := :OLD.PROVINCE;
      V_YEAR := :OLD.YEAR;
      V_SPFID := :OLD.SPFID;
      V_PROJETID :=:OLD.PROJECTID;
    END IF;
    SELECT AGENCYID,SFZDHZ INTO V_AGENCYID,V_SFZDHZ
    FROM P#SPF_T_FBASEINFO 
    WHERE PROVINCE = V_PROVINCE AND YEAR = V_YEAR AND STATUS = '1' AND SPFID = V_SPFID;
    
    IF NVL(V_SFZDHZ,'0') = '0' THEN
      RETURN;
    END IF;
    
    IF INSERTING AND :NEW.STATUS = '1' AND :NEW.CZHD = '1' THEN
      V_NUM := 1;
    END IF;
    
    IF UPDATING AND :NEW.STATUS = '1' AND NVL(:OLD.CZHD,'0') = '0' AND  NVL(:NEW.CZHD,'0') = '1' THEN
      V_NUM := 1;
    END IF;
    
    IF UPDATING AND :NEW.STATUS = '1' AND NVL(:OLD.CZHD,'0') = '1' AND  NVL(:NEW.CZHD,'0') = '0' THEN
      V_NUM := -1;
    END IF;
    
    IF UPDATING AND :OLD.STATUS = '1' AND :NEW.STATUS <> '1' AND :OLD.CZHD = '1' THEN
      V_NUM := -1;
    END IF;
    
    IF DELETING AND :OLD.STATUS = '1' AND :OLD.CZHD = '1' THEN
      V_NUM := -1;
    END IF;
    
    MERGE INTO P#SPF_T_YJXMCZHDJEB X
    USING (SELECT PROVINCE,YEAR,STATUS,SPFID, FINYEAR,SUM(BUDGETNUM) BDGNUM
    FROM P#BGT_T_ZCXMMXB
    WHERE PROVINCE = V_PROVINCE AND YEAR = V_YEAR AND STATUS = '1' AND SPFID = V_SPFID AND PROJECTID = V_PROJETID
    GROUP BY PROVINCE,YEAR,STATUS,SPFID,FINYEAR ) Y
    ON (X.PROVINCE = Y.PROVINCE AND X.YEAR = Y.YEAR AND X.STATUS = '1' AND X.SPFID = Y.SPFID AND X.FINYEAR = Y.FINYEAR)
    WHEN MATCHED THEN
      UPDATE 
      SET AGENCYID = V_AGENCYID,
      AUDITVALUE = AUDITVALUE + (Y.BDGNUM * V_NUM)
    WHEN NOT MATCHED THEN
    INSERT (PROVINCE,YEAR,STATUS,DATAKEY,ORDERID,FINYEAR,AGENCYID,SPFID,AUDITVALUE)
    VALUES(Y.PROVINCE,Y.YEAR,Y.STATUS,RAWTOHEX(SYS_GUID()),1,Y.FINYEAR,V_AGENCYID,Y.SPFID,Y.BDGNUM);
  END IF;
  RETURN;
END TR_SPF_T_PBASEINFO_CZHD;
}';
END;

--ckChuFaQi1
--����������
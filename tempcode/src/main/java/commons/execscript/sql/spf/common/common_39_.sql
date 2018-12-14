BEGIN
  EXECUTE IMMEDIATE Q'{CREATE OR REPLACE TRIGGER TR_BGT_T_ZCXMMXB_BUDGETNUM
  AFTER INSERT OR UPDATE OR DELETE
  ON P#BGT_T_ZCXMMXB 
  FOR EACH ROW
DECLARE
  V_PROVINCE P#BGT_T_ZCXMMXB.PROVINCE%TYPE;
  V_YEAR P#BGT_T_ZCXMMXB.YEAR%TYPE;
  V_AGENCYID P#SPF_T_FBASEINFO.AGENCYID%TYPE;
  V_SPFID P#BGT_T_ZCXMMXB.SPFID%TYPE;
  V_PROJETID P#BGT_T_ZCXMMXB.PROJECTID%TYPE;
  V_BDGNUM P#BGT_T_ZCXMMXB.BUDGETNUM%TYPE;
  V_OLDBDGNUM P#BGT_T_ZCXMMXB.BUDGETNUM%TYPE;
  V_FINYEAR P#BGT_T_ZCXMMXB.FINYEAR%TYPE;
  V_OLDFINYEAR P#BGT_T_ZCXMMXB.FINYEAR%TYPE;
  V_CZHD P#SPF_T_PBASEINFO.CZHD%TYPE;
  V_SFZDHZ P#SPF_T_FBASEINFO.SFZDHZ%TYPE;
BEGIN
  IF (INSERTING) 
    OR (UPDATING AND (
                      (:NEW.STATUS = '1' AND (
                                              :OLD.STATUS <> :NEW.STATUS 
                                              OR NVL(:OLD.BUDGETNUM,0) <> NVL(:NEW.BUDGETNUM,0) 
                                              OR NVL(:OLD.FINYEAR,'0000') <> NVL(:NEW.FINYEAR,'0000')
                                              )
                      ) 
                   OR (:OLD.STATUS = '1' AND :NEW.STATUS <> '1')
                      )
        ) 
    OR (DELETING AND :OLD.STATUS = '1') THEN
    V_PROVINCE := :NEW.PROVINCE;
    V_YEAR := :NEW.YEAR;
    IF INSERTING OR UPDATING THEN
      V_PROVINCE := :NEW.PROVINCE;
      V_YEAR := :NEW.YEAR;
      V_SPFID :=:NEW.SPFID;
      V_PROJETID := :NEW.PROJECTID;
      V_BDGNUM := :NEW.BUDGETNUM;
      V_FINYEAR := NVL(:NEW.FINYEAR,'0000');
      V_OLDFINYEAR := NVL(:OLD.FINYEAR,'0000');
    ELSE
      V_PROVINCE := :OLD.PROVINCE;
      V_YEAR := :OLD.YEAR;
      V_SPFID := :OLD.SPFID;
      V_PROJETID := :OLD.PROJECTID;
      V_BDGNUM := :OLD.BUDGETNUM;
      V_FINYEAR := NVL(:OLD.FINYEAR,'0000');
      V_OLDFINYEAR := NVL(:OLD.FINYEAR,'0000');
    END IF;
    
    IF V_PROJETID = '*' THEN
      RETURN;
    END IF;
    BEGIN
      SELECT SFZDHZ INTO V_SFZDHZ FROM P#SPF_T_FBASEINFO 
      WHERE PROVINCE = V_PROVINCE AND YEAR = V_YEAR AND STATUS = '1' AND SPFID = V_SPFID;
      
      SELECT CZHD INTO V_CZHD FROM P#SPF_T_PBASEINFO 
      WHERE PROVINCE = V_PROVINCE AND YEAR = V_YEAR AND STATUS = '1' AND SPFID = V_SPFID AND PROJECTID = V_PROJETID;
    EXCEPTION WHEN NO_DATA_FOUND THEN
      RETURN;
    END;
    
    IF NVL(V_SFZDHZ,'0') = '0' THEN
      RETURN;
    END IF;
    
    IF NVL(V_CZHD,'0') = '0' THEN
      RETURN;
    END IF;
    
    IF INSERTING THEN
      V_BDGNUM := :NEW.BUDGETNUM;
      V_OLDBDGNUM := 0;
    END IF;
    --此处不考虑BUDGETNUM金额为0的状态
    IF UPDATING THEN
      IF :OLD.STATUS = '1' THEN
        IF :NEW.STATUS <> '1' THEN
          --STATUS不等于1，则减去当前数值
          V_BDGNUM := 0 - :OLD.BUDGETNUM;
          V_OLDBDGNUM := 0;
        ELSE
          IF NVL(:OLD.BUDGETNUM,0) <> NVL(:NEW.BUDGETNUM,0) THEN
            --STATUS='1'没变,BUDGETNUM变了，计算出变化金额
            V_BDGNUM := NVL(:NEW.BUDGETNUM,0) - NVL(:OLD.BUDGETNUM,0);
            V_OLDBDGNUM := 0;
          ELSE
            IF NVL(:OLD.FINYEAR,'0000') <> NVL(:NEW.FINYEAR,'0000') THEN
              --FINYEAR变化，新的和旧的年度数据都需要更新
              V_OLDBDGNUM := 0 - NVL(:OLD.BUDGETNUM,0);
              V_BDGNUM := NVL(:NEW.BUDGETNUM,0);
              V_FINYEAR := NVL(:OLD.FINYEAR,'0000');
              V_OLDFINYEAR := NVL(:OLD.FINYEAR,'0000');
            ELSE
              --STATUS相同，BUDGETNUM相同，FINYEAR相同，则不更新
              V_BDGNUM := 0;
              V_OLDBDGNUM := 0;
              RETURN;
            END IF;
          END IF;
        END IF;
      ELSE
        IF :NEW.STATUS = '1' THEN
          --STATUS等于1，则加当前数值
          V_BDGNUM := :NEW.BUDGETNUM;
          V_OLDBDGNUM := 0;
        ELSE
          V_BDGNUM := 0;
          V_OLDBDGNUM := 0;
          RETURN;
        END IF;
      END IF;
    END IF;
    
    IF DELETING THEN
      V_BDGNUM := :OLD.BUDGETNUM;
      V_OLDBDGNUM := 0;
    END IF;
    
    --数值没有变化直接返回
    IF V_BDGNUM = 0 AND V_OLDBDGNUM = 0 THEN
      RETURN;
    END IF;
    
    SELECT AGENCYID INTO V_AGENCYID 
    FROM P#SPF_T_FBASEINFO 
    WHERE PROVINCE = V_PROVINCE AND YEAR = V_YEAR AND STATUS = '1' AND SPFID = V_SPFID;
    
    MERGE INTO P#SPF_T_YJXMCZHDJEB X
    USING (SELECT V_PROVINCE PROVINCE,V_YEAR YEAR,'1' STATUS,V_SPFID SPFID,V_FINYEAR FINYEAR,V_BDGNUM BDGNUM FROM DUAL) Y
    ON (X.PROVINCE = Y.PROVINCE AND X.YEAR = Y.YEAR AND X.STATUS = '1' AND X.SPFID = Y.SPFID AND X.FINYEAR = Y.FINYEAR)
    WHEN MATCHED THEN
      UPDATE 
      SET AGENCYID = V_AGENCYID,
      AUDITVALUE = AUDITVALUE + Y.BDGNUM
    WHEN NOT MATCHED THEN
    INSERT (PROVINCE,YEAR,STATUS,DATAKEY,ORDERID,FINYEAR,AGENCYID,SPFID,AUDITVALUE)
    VALUES(Y.PROVINCE,Y.YEAR,Y.STATUS,RAWTOHEX(SYS_GUID()),1,Y.FINYEAR,V_AGENCYID,Y.SPFID,Y.BDGNUM);
    
    IF V_FINYEAR <> V_OLDFINYEAR AND V_OLDBDGNUM <> 0 THEN
      MERGE INTO P#SPF_T_YJXMCZHDJEB X
      USING (SELECT V_PROVINCE PROVINCE,V_YEAR YEAR,'1' STATUS,V_SPFID SPFID,V_OLDFINYEAR FINYEAR,V_OLDBDGNUM BDGNUM FROM DUAL) Y
      ON (X.PROVINCE = Y.PROVINCE AND X.YEAR = Y.YEAR AND X.STATUS = '1' AND X.SPFID = Y.SPFID AND X.FINYEAR = Y.FINYEAR)
      WHEN MATCHED THEN
        UPDATE 
        SET AGENCYID = V_AGENCYID,
        AUDITVALUE = AUDITVALUE + Y.BDGNUM
      WHEN NOT MATCHED THEN
      INSERT (PROVINCE,YEAR,STATUS,DATAKEY,ORDERID,FINYEAR,AGENCYID,SPFID,AUDITVALUE)
      VALUES(Y.PROVINCE,Y.YEAR,Y.STATUS,RAWTOHEX(SYS_GUID()),1,Y.FINYEAR,V_AGENCYID,Y.SPFID,Y.BDGNUM);
    END IF;
  END IF;
  RETURN;
END TR_BGT_T_ZCXMMXB_BUDGETNUM;}';
END;
--ckChuFaQi2

CREATE OR REPLACE PROCEDURE SPF_P_IMPSPFBASE(BUSITYPE VARCHAR2) IS
  V_USERID   VARCHAR2(32);
  V_DOCID    VARCHAR2(32);
  V_AGENCYID VARCHAR2(32);
  V_UPAGENCYID VARCHAR2(32);
  V_AGENCYNAME VARCHAR2(100);
  V_DISTRICTID VARCHAR2(32);
  V_ISEXIST  NUMBER;
  V_ID       VARCHAR2(32);
BEGIN
  V_USERID := NVL(GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('USER'), ' ');
  SELECT NVL(MAX(A.ORGID), ' '),NVL(MAX(a.UPAGENCYID), ' ')
    INTO V_AGENCYID, V_UPAGENCYID
    FROM SECU_T_USER A
   WHERE  A.GUID = V_USERID;
   
select (case isself
         when '1' then
          superguid
         else
          guid
       end)
  into V_DISTRICTID
  from code_t_district_spf a
 where code = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID');


  EXECUTE IMMEDIATE 'UPDATE SPF_T_FBASETEMP SET IMPFLAG=:1 WHERE IMPFLAG IS NULL OR IMPFLAG<>:2'
    USING '9', '1';

  FOR C_ROW IN (SELECT * FROM SPF_T_FBASETEMP WHERE IMPFLAG = '9') LOOP
    BEGIN
      SAVEPOINT ROLL_BLACK;
      SELECT COUNT(1)
        INTO V_ISEXIST
        FROM SPF_T_FBASEINFO
       WHERE SPFID = C_ROW.SPFID;
      IF V_ISEXIST = 0 THEN
        V_ID := C_ROW.SPFID;
        INSERT INTO SPF_T_FBASEINFO
          (SFSYWZCCZZCZJ,
          SFJSBZ,
         FZTCZJFPJHDBM,
          AGENCYID,
           SPFID,
           PROJTYPEID,
           SPFNAME,
           SPFCODE,
           FUNDMANAGE,
           FUNDLEVEL,
           DEPTID,
           FIRAGENCYID,
           BEGINYEAR,
           ENDYEAR,
           EXPFUNCID,
           DISTRICTID,
           CREATEUSER,
           CUTOFFTIME,
           DECLRANGE,
           ISTEMP)
        VALUES
          ( C_ROW.SFSYWZCCZZCZJ,
           C_ROW.SFJSBZ,
           C_ROW.FZTCZJFPJHDBM,
          V_AGENCYID,
           C_ROW.SPFID,
           C_ROW.PROJTYPEID,
           C_ROW.SPFNAME,
           C_ROW.SPFCODE,
           C_ROW.FUNDMANAGE,
           C_ROW.FUNDLEVEL,
           C_ROW.DEPTID,
           C_ROW.FIRAGENCYID,
           C_ROW.BEGINYEAR,
           C_ROW.ENDYEAR,
           C_ROW.EXPFUNCID,
           C_ROW.DISTRICTID,
           V_USERID,
           '12-31',
           '1111',
           '0');

           INSERT INTO SPF_T_FDECLAREREGION(SPFID,DISTRICTID) VALUES(C_ROW.SPFID,V_DISTRICTID);
           INSERT INTO SPF_T_FDECLAREAGENCY(SPFID,AGENCYID) VALUES(C_ROW.SPFID,V_UPAGENCYID);

        EXECUTE IMMEDIATE 'UPDATE SPF_T_FBASETEMP SET IMPFLAG=:1 WHERE SPFID=:2 AND IMPFLAG=:3'
          USING '1', C_ROW.SPFID, '9';

         SELECT SYS_GUID() INTO V_DOCID FROM DUAL;
        IF V_DOCID IS NOT NULL THEN
          INSERT INTO SPF_T_OARELATION
            (DOCID, ORGID, TASKID, TASKTYPE, USERID)
          VALUES
            (V_DOCID, V_AGENCYID, C_ROW.SPFID, '0', V_USERID);
        END IF;
        select name into V_AGENCYNAME from CODE_T_FIRAGENCY where guid = C_ROW.SENDAGENCYID;
        OA_P_IMPSENDDOC(BUSITYPE,
                          V_USERID,
                          C_ROW.TITLE,
                          V_DOCID,
                          V_AGENCYNAME,
                          C_ROW.DOCNUMBER,
                          C_ROW.SENDTIME,
                          C_ROW.REMARK);

      ELSE
        EXECUTE IMMEDIATE 'UPDATE SPF_T_FBASETEMP SET IMPFLAG=:1 WHERE SPFID=:2 AND IMPFLAG=:3'
          USING '1', C_ROW.SPFID, '9';
      END IF;
      COMMIT;
    EXCEPTION
      WHEN OTHERS THEN
        DBMS_OUTPUT.put_line('sqlcode : ' ||sqlcode);
        DBMS_OUTPUT.put_line('sqlerrm : ' ||sqlerrm);
        ROLLBACK TO ROLL_BLACK;
        EXECUTE IMMEDIATE 'UPDATE SPF_T_FBASETEMP SET IMPFLAG=:1 WHERE SPFID=:2 AND IMPFLAG=:3'
          USING '2', V_ID, '9';
        COMMIT;

    END;
  END LOOP;

END SPF_P_IMPSPFBASE;
--SPF_P_IMPSPFBASE

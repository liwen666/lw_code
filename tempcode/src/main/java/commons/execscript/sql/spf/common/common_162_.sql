CREATE OR REPLACE VIEW SPF_V_FMAIN AS
SELECT (SELECT S.NAME FROM CODE_T_FIRAGENCY S WHERE S.GUID = A.FIRAGENCYID) FIRAGENCYNAME,
       A.SPFID,
       A.SPFNAME  SPFNAME,
       A.SPFNAME || '{' || A.SPFID || '}' SPFGROUP_NAME,
       (SELECT S.NAME FROM CODE_T_SP_TYPE S WHERE S.GUID = A.PROJTYPEID) PROJTYPENAME,
       A.SPFCODE,
       A.AGENCYID,
        (select  '[' || AGENCY.CODE || ']' || AGENCY.NAME from code_t_firagency  AGENCY where  a.AGENCYID = AGENCY.GUID ) AGENCYNAME,
       A.BEGINYEAR,
       A.ENDYEAR,
       A.EXPFUNCID,
       (SELECT ACCT.CODE
          FROM CODE_V_ACCTCODE_OUT_SPF ACCT
         WHERE ACCT.GUID = A.EXPFUNCID) EXPFUNCCODE,
       (SELECT ACCT.NAME
          FROM CODE_V_ACCTCODE_OUT_SPF ACCT
         WHERE ACCT.GUID = A.EXPFUNCID) EXPFUNCNAME,
       A.CREATETIME,
       A.SETUPSTATUS,
       A.PROJTYPEID,
       CASE (SELECT DISTINCT (LEVELNO)
           FROM CODE_V_ACCTCODE_OUT_SPF T
          WHERE CODE LIKE '2%'
            AND GUID = EXPFUNCID)
         WHEN 3 THEN
          (SELECT SUPERGUID
             FROM CODE_V_ACCTCODE_OUT_SPF
            WHERE GUID = EXPFUNCID)
         ELSE
          (SELECT SUPERGUID
             FROM CODE_T_ACCTITEM_SPF
            WHERE YEAR = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR') AND GUID = (SELECT SUPERGUID
                            FROM CODE_V_ACCTCODE_OUT_SPF
                           WHERE GUID = EXPFUNCID))
       END AS EXPFUNCID2,
       (SELECT V.LEICODENAME
          FROM CODE_V_ACCTCODE_OUT_SPF V
         WHERE V.GUID = A.EXPFUNCID) EXPFUNCCLASS,
       NVL((SELECT SUM(NVL(B.CTRLNUM, 0))
       FROM SPF_T_FFOUDCONTROL B
       WHERE B.SPFID = A.SPFID),
        0) CTRLNUMS,
       A.FIRAGENCYID,
       A.DEPTID,
       (SELECT NAME FROM CODE_T_DEPT_SPF WHERE GUID= A.DEPTID) DEPTNAME,
       A.DISTRICTID,
       A.ISRELEASE,
       A.APPLYPRJEFM,
       A.APPROVALTYPE,
       A.ISGROVPROC,
       A.FUNDLEVEL,
       A.FUNDMANAGE,
       A.ISTEMP,
       A.DATAKEY,
       A.DBVERSION,
       A.STATUS
  FROM SPF_T_FBASEINFO A
--DF_SPF_V_FMAIN
--SPF_V_FMAIN

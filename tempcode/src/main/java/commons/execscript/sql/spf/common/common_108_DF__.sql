BEGIN
    EXECUTE IMMEDIATE Q'/CREATE OR REPLACE FORCE VIEW  SPF_V_PSPFCHOOSE AS 
  SELECT T.DATAKEY,
       A.NAME AGENCYNAME,
       (SELECT S.NAME FROM CODE_T_SP_TYPE S WHERE S.GUID = T.PROJTYPEID) PROJTYPENAME,
       T.SPFNAME SPFNAME,
       T.SPFNAME || '{' || T.SPFID || '}' SPFGROUP_NAME,
       T.SPFCODE,
       T.SPFID,
       A.NAME FIRAGENCYNAME,
       T.PROJTYPEID,
       (SELECT D.NAME FROM CODE_T_DISTRICT D WHERE D.GUID = A.DISTRICTID) DISTRICTNAME,
       T.DECLRANGE,
       T.SETUPSTATUS,
       T.APPLYPRJEFM,
       T.FUNDMANAGE,
       T.APPROVALTYPE,
       T.ISPUBSHOW,
       T.EXPFUNCID,
       (SELECT P.NAME
          FROM CODE_V_ACCTCODE_OUT_SPF P
         WHERE P.GUID = T.EXPFUNCID) SNAME_EXPFUNCID,
       T.ISRELEASE,
       T.ISGROVPROC,
       T.APPLYINNER,
       T.ISTEMP,
       T.DISTRICTID,
       CASE
         WHEN (to_number(replace(T.CUTOFFTIME, '-', '')) -
              to_number(to_char(SYSDATE, 'MMDD'))) >= 0 THEN
          0
         ELSE
          1
       END ISOVERTIME,
       oa.title as docName,
       oa.docid as destdocId
  FROM SPF_T_FBASEINFO T,
       CODE_T_FIRAGENCY A,
       (select distinct t1.docid, t1.title, s1.TASKID
          from hqoa_t_docbgtrelation t1, spf_t_oarelation s1
         where t1.docId = s1.DOCID
           and s1.TASKTYPE = '0') oa
 WHERE T.FIRAGENCYID = A.GUID
   AND nvl(T.FUNDMANAGE, '1') <> '2'
   and oa.taskid = t.SPFID
 order by oa.title/';
END;
--DF_SPF_V_PSPFCHOOSE

CREATE OR REPLACE VIEW CODE_T_PROJECTANDSPF AS
SELECT TT."GUID",
       TT."NAME",
       TT."SPFNAME",
       TT."AGENCYNAME",
       TT."AGENCYID",
       TT."PROJTYPEID",
       TT."CODE",
       TT."SUPERGUID",
       TT."ISLEAF",
       TT."LEVELNO",
       TT."TASKTYPE",
       TT."DEPTID",
       TT.STATUS,
       TT.PROVINCE,
       TT.YEAR,
       GUID AS ID,
       CODE AS SHOWID,
       ISLEAF AS ENDFLAG,
       SUBSTR(LPAD(ROWNUM, 4, '0'), -4) AS LVLID,
       CASE
         WHEN SUPERGUID = '0' THEN
          '#'
         ELSE
          NVL(SUPERGUID, '#')
       END SUPERID
  FROM (SELECT A.PROJECTID GUID,
               A.PROJNAME NAME,
               B.SPFNAME,
               (SELECT NAME FROM CODE_T_AGENCY_SPF WHERE GUID = A.AGENCYID) AS AGENCYNAME,
               A.AGENCYID,
               B.PROJTYPEID,
               NVL(A.PROJCODE,'0') CODE,
               A.SPFID AS SUPERGUID,
               '1' ISLEAF,
               '2' LEVELNO,
               '1' TASKTYPE,
               B.DEPTID,
               A.STATUS,
               A.PROVINCE,
               A.YEAR
          FROM P#SPF_T_PBASEINFO A, P#SPF_T_FBASEINFO B
         WHERE A.STATUS = '1' AND A.SPFID = B.SPFID
        UNION ALL
        SELECT S.SPFID GUID,
               S.SPFNAME NAME,
               S.SPFNAME,
              (SELECT AGENCY.NAME
                  FROM CODE_T_FIRAGENCY AGENCY
                 WHERE S.AGENCYID = AGENCY.GUID
               )AGENCYNAME,
               S.AGENCYID,
               S.PROJTYPEID,
               NVL(S.SPFCODE,'0') CODE,
               '0' SUPERGUID,
               '0' ISLEAF,
               '1' LEVELNO,
               '0' TASKTYPE,
               DEPTID,
               S.STATUS,
               S.PROVINCE,
               S.YEAR
          FROM P#SPF_T_FBASEINFO S
          WHERE S.STATUS = '1') TT
--yanmingCODE_T_PROJECTANDSPF_DF_

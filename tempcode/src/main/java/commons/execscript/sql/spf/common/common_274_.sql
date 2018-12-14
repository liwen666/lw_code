CREATE OR REPLACE VIEW CODE_T_PROJECTANDSPF AS
WITH SDQ AS --查询本地上级及本及专项
 (SELECT GUID
    FROM CODE_T_DISTRICT
   WHERE DISTRLVL <=
         (SELECT DISTRLVL
            FROM CODE_T_DISTRICT C
           WHERE C.CODE = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID'))),
ZY AS --查询中央级次专项
 (SELECT SPFID
    FROM ((SELECT SPFID
             FROM P#SPF_T_FBASEINFO VV，SDQ
            WHERE VV.DISTRICTID = SDQ.GUID) UNION ALL
          (SELECT SPFID
             FROM P#SPF_T_FBASEINFO VV，SDQ
            WHERE VV.AGENCYID = SDQ.GUID) UNION ALL
          (SELECT SPFID
             FROM P#SPF_T_FBASEINFO G
            WHERE G.FUNDLEVEL = '1'
              AND EXISTS
            (SELECT 1
                     FROM CODE_T_AGENCY_SPF
                    WHERE (CODE =
                          GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID') AND
                          LEVELNO = '2')
                       OR GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID') IN
                          ('440300', '210200', '330200', '370200', '350200')))

         ))

SELECT TT."GUID",
       TT."NAME",
       TT."SPFNAME",
       TT."AGENCYNAME",
       TT."AGENCYID",
       TT."AGENCYID" AS AGENCYGUID,
       TT."PROJTYPEID",
       TT."CODE",
       TT."SUPERGUID",
       TT."ISLEAF",
       TT."LEVELNO",
       TT."TASKTYPE",
       TT."DEPTID",
       TT."FUNDLEVEL",
       TT.PROVINCE,
       TT.STATUS,
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
               (SELECT NAME FROM CODE_T_AGENCY WHERE GUID = A.AGENCYID) AS AGENCYNAME,
               A.AGENCYID,
               B.PROJTYPEID,
               nvl(A.PROJCODE,'0') CODE,
               '#' SUPERGUID,
               '1' ISLEAF,
               '1' LEVELNO,
               '1' TASKTYPE,
               B.DEPTID,
               B.FUNDLEVEL,
               B.PROVINCE,
               A.STATUS
          FROM P#SPF_T_PBASEINFO A, P#SPF_T_FBASEINFO B
         WHERE A.STATUS = '1'
           AND A.SPFID = B.SPFID
        UNION ALL
        SELECT S.SPFID GUID,
               S.SPFNAME NAME,
               S.SPFNAME,
               DECODE((SELECT AGENCY.NAME
                  FROM CODE_T_FIRAGENCY AGENCY
                 WHERE S.AGENCYID = AGENCY.GUID
                 ),'',(SELECT dept.NAME
                  FROM Code_t_Dept dept
                 WHERE S.AGENCYID = dept.GUID
                 ),(SELECT AGENCY.NAME
                  FROM CODE_T_FIRAGENCY AGENCY
                 WHERE S.AGENCYID = AGENCY.GUID
                 )) NAME,
               S.AGENCYID,
               S.PROJTYPEID,
               nvl(s.SPFCODE,'0') CODE,
               '#' SUPERGUID,
               '1' ISLEAF,
               '1' LEVELNO,
               '0' TASKTYPE,
               DEPTID,
               S.FUNDLEVEL,
               S.PROVINCE,
               S.STATUS
          FROM P#SPF_T_FBASEINFO S, ZY
         WHERE S.STATUS = '1'
           AND S.SPFID = ZY.SPFID

        ) TT
--专项项目代码表CODE_T_PROJECTANDSPF
--CODE_T_PROJECTANDSPF

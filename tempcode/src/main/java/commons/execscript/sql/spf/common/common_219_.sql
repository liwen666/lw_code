BEGIN
execute immediate Q'{CREATE OR REPLACE VIEW CODE_T_PROJECTANDSPF AS
select TT."GUID",
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
       TT.status,
       GUID as ID,
       CODE AS SHOWID,
       ISLEAF as EndFlag,
       substr(lpad(rownum, 4, '0'), -4) as LvlID,
       CASE
         WHEN SUPERGUID = '0' THEN
          '#'
         ELSE
          nvl(SUPERGUID, '#')
       END SuperID
  from (SELECT A.PROJECTID GUID,
               A.PROJNAME NAME,
               B.SPFNAME,
               (select name from CODE_T_AGENCY_SPF where guid = a.agencyid) AS AGENCYNAME,
               A.AGENCYID,
               b.PROJTYPEID,
               A.PROJCODE CODE,
               '#' SUPERGUID,
               '1' ISLEAF,
               '1' levelno,
               '1' tasktype,
               b.deptid,
               A.status
          FROM P#SPF_T_PBASEINFO A, P#SPF_T_FBASEINFO B
         WHERE A.Status = '1' AND A.SPFID = B.SPFID
        /*AND A.Status = '1'*/
        union all
        select s.SPFID guid,
               s.SPFNAME name,
               s.spfname,
               (select AGENCY.NAME
                  from code_t_firagency AGENCY
                 where s.AGENCYID = AGENCY.GUID) NAME,
               s.AGENCYID,
               s.PROJTYPEID,
               s.SPFCODE,
               '#' SUPERGUID,
               '1' ISLEAF,
               '1' levelno,
               '0' tasktype,
               deptid,
               s.status
          from P#SPF_T_FBASEINFO s
          where s.status = '1') TT}';
END;
--  CODE_T_PROJECTANDSPF

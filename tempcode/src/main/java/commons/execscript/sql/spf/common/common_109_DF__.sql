BEGIN
EXECUTE IMMEDIATE Q'/CREATE OR REPLACE FORCE VIEW  SPF_V_PSTAMAIN AS 
  SELECT A.DATAKEY,
       A.AGENCYID,
       F.FIRAGENCYID,
       A.SPFID,
       A.PROJTYPEID,
       A.PROJECTID,
       A.PROJCODE,
       A.PROJNAME PROJNAME,
       A.PROJNAME || '{' || A.PROJECTID || '}' PROJGROUP_NAME,
       A.DISTRICTID,
       F.EXPFUNCID,
       F.DEPTID
  FROM SPF_T_PBASEINFO A
 inner join SPF_T_FBASEINFO F
    on A.SPFID = F.SPFID
 inner join CODE_T_FIRAGENCY AGENCY
    on F.FIRAGENCYID = AGENCY.GUID/';
END;
--DF_SPF_V_PSTAMAIN

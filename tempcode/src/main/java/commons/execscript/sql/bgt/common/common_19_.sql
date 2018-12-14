CREATE OR REPLACE VIEW BGT_V_COLIDATA AS
SELECT AGENCYNAME,
       SPFNAME,
       AGENCYCODE,
       SPFID,
       AGENCYID,
       SPFCODE,
       FINYEAR,
       BUDGETNUM,
       PROJTYPEID,
       DATAID,
       SUPERID,
       LEVELNO,
       PROJTYPECODE,
       ISSPF,
       ORDERID,
       NEEDUPDATE,
       DATAKEY,
       PROJCODE,
       DATAFLAG,
       (SELECT FUNDMANAGE FROM SPF_T_FBASEINFO T WHERE T.SPFID = BGT_V_DATA.SPFID) FUNDMANAGE,
       DBVERSION
  FROM BGT_V_DATA
--����_BGT_V_COLIDATA

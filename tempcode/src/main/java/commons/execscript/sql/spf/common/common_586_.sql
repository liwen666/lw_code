BEGIN
EXECUTE IMMEDIATE Q'/CREATE OR REPLACE VIEW SPF_T_SHOWSPFINFO AS
SELECT T.SPFNAME,
        T.PROJTYPEID,
        T.deptID,
        DEPTIDSPF.NAME DEPTNAME,
        T.FIRAGENCYID,
        DEPTSPF.NAME FIRAGENCYNAME,
        T.DISTRICTID,
        T.BEGINYEAR FINYEAR,
         1 NEEDUPDATE,
         1 ORDERID,
         T.datakey,
         T.SPFID,
         T.ISRELEASE,
         T.Fundmanage,
         T.Spfcode,
         T.CUTOFFTIME,
         DISTRICTSPF.CODE DISTRICTCODE,
         DISTRICTSPF.NAME DISTRICTIDNAME,
         T.Dbversion
      FROM P#SPF_T_FBASEINFO T
       INNER JOIN P#SPF_T_FBASESTATUS FB
    ON T.SPFID = FB.SPFID and FB.STATUS ='1'  and FB.setupStatus = '2'
        INNER JOIN CODE_T_DISTRICT_SPF DISTRICTSPF
    ON DISTRICTSPF.GUID = T.DISTRICTID
     INNER JOIN Code_t_Firagency DEPTSPF
    ON DEPTSPF.GUID = T.FIRAGENCYID
        INNER JOIN CODE_T_DEPT_SPF DEPTIDSPF
    ON DEPTIDSPF.GUID = T.Deptid
      where  t.status='1'
      AND FB.Year = GLOBAL_MULTYear_CZ.Secu_f_Global_Parm('YEAR')/';

end;--为专项资金申报准备的专项查询视图登记

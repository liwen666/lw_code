begin 
EXECUTE IMMEDIATE Q'/CREATE OR REPLACE VIEW CODE_V_SPF2DIST AS
SELECT "SPFID","SPFNAME","SPFCODE","DEPTID","FIRAGENCYID","DISTRICTID","ISCUSTODY","ISTEMP" ,"FUNDMANAGE"FROM (
SELECT S.SPFID,
       S.SPFNAME,
       S.SPFCODE,
       S.DEPTID,
       S.FIRAGENCYID,
       S.DISTRICTID,
       '0' ISCUSTODY,
       S.ISTEMP,
       S.FUNDMANAGE
  FROM SPF_V_FBASEINFO S
UNION
SELECT T.SPFID,
       T1.SPFNAME,
       T1.SPFCODE,
       T.CUSTODYDEPTID DEPTID,
       T.CUSRODYDEPT FIRAGENCYID,
       T.DISTRICTID,
       '1' ISCUSTODY,
       T1.ISTEMP,
       T1.FUNDMANAGE
  FROM SPF_T_CUSTODY T, SPF_V_FMAIN T1
 WHERE T.SPFID = T1.SPFID )/';                        
end;--修改用户对专项所用视图脚本

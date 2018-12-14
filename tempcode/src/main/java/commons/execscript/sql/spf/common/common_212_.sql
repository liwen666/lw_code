CREATE OR REPLACE VIEW SPF_V_FBASEINFO_A AS
SELECT "PROVINCE","YEAR","STATUS","DBVERSION","DATAKEY","AGENCYID","SPFID","INVALID","PROJTYPEID","SPFNAME","SPFCODE","DISTRICTID","EXPFUNCID","FUNDMANAGE","FUNDLEVEL","EFFTYPE","BEGINYEAR","ENDYEAR","ISPUBSHOW","PRJREPLY","ISNEEDJX","ISRELEASE","HOTTYPE","FIRAGENCYID","DEPTID","CREATEUSER","CREATETIME","APPROVALTYPE","SETUPSTATUS","CUTOFFTIME","SZGX","ISMATCHFUNDS","DECLRANGE","ISREPEATAPP","ISGROVPROC","SHOWPROJLOGDAYS","ISMOREYEARPROJ","ISTEMP","APPLYINNER","APPLYPRJEFM","ZXDM","ZJXZ","SQLX","SFZDHZ"  FROM P#SPF_T_FBASEINFO p#spf_t_fbaseinfo WHERE STATUS='1'
--SPF_V_FBASEINFO_A
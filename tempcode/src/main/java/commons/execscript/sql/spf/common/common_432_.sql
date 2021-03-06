CREATE OR REPLACE VIEW CODE_T_SECUAGENCY_SPF AS
SELECT  X.GUID,X.NAME
FROM CODE_T_AGENCY_SPF X,
(
--角色对单位
SELECT  A.LVLID
  FROM SECU_T_AGENCY S, CODE_T_AGENCY_SPF A
 WHERE A.GUID = S.AGENCYID
   AND S.MANTYPE = '1'
   AND S.MANID IN
       (SELECT ROLEGUID
          FROM FASP_T_CAUSERROLE
         WHERE USERGUID = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('USER'))
   AND S.APPID='SPF'
UNION ALL
--用户对单位
SELECT A.LVLID
  FROM SECU_T_AGENCY S, CODE_T_AGENCY_SPF A
 WHERE A.GUID = S.AGENCYID
   AND S.MANTYPE = '0'
   AND S.MANID = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('USER')
   AND S.APPID='SPF'
   ) Y
WHERE (X.LVLID LIKE Y.LVLID||'%' OR Y.LVLID LIKE X.LVLID||'%')
--CODE_T_SECUAGENCY_SPF用户对单位

CREATE OR REPLACE VIEW CODE_T_DISTRICT_ALL AS
SELECT DISTINCT GUID,
                CODE,
                '[' || CODE || ']' || NAME NAME,
                SUPERGUID,
                ISLEAF,
                CASE
                  WHEN SUBSTR(CODE, -2, 2) = '00' THEN
                   LEVELNO - 1
                  ELSE
                   LEVELNO
                END DISTRLVL
  FROM P#FASP_T_PUPFASP001
 WHERE (SELECT CODE
          FROM CODE_T_DISTRICT
         WHERE GUID = (SELECT DISTRICTID
                         FROM CODE_T_AGENCY_SPF
                        WHERE GUID = (SELECT UPAGENCYID
                                        FROM FASP_T_CAUSER
                                       WHERE GUID =
                                             GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('USER')
                                         AND ROWNUM = 1))) =
       PKG_CONSTANTS.GETDEFAULTPROVINCE()
   AND YEAR = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR')
   AND STATUS = '1'
   AND CODE LIKE
       (SELECT CODE
          FROM P#FASP_T_PUPFASP001
         WHERE GUID IN (SELECT SUPERGUID
                          FROM P#FASP_T_PUPFASP001
                         WHERE GUID = (SELECT CASE
                                                WHEN SUBSTR(CODE, -2, 2) = '00' THEN
                                                 SUPERGUID
                                                ELSE
                                                 GUID
                                              END
                                         FROM P#FASP_T_PUPFASP001
                                        WHERE CODE =
                                              PKG_CONSTANTS.GETDEFAULTPROVINCE()
                                          AND YEAR =
                                              GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR')
                                          AND STATUS = '1'))
           AND YEAR = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR')
           AND STATUS = '1') || '%'
UNION
SELECT GUID,
       CODE,
       '[' || CODE || ']' || NAME NAME,
       SUPERGUID,
       ISLEAF,
       CASE
         WHEN SUBSTR(CODE, -2, 2) = '00' THEN
          LEVELNO - 1
         ELSE
          LEVELNO
       END DISTRLVL
  FROM P#FASP_T_PUPFASP001
 WHERE CODE IN  ('87','8700') 
 AND YEAR = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR')
   AND STATUS = '1'
   AND (SELECT CODE
          FROM CODE_T_DISTRICT
         WHERE GUID = (SELECT UPAGENCYID
                                        FROM FASP_T_CAUSER
                                       WHERE GUID =
                                             GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('USER')
                                         AND ROWNUM = 1)) IN ('210200', '330200', '350200', '370200', '440300')
UNION
SELECT GUID,
       CODE,
       '[' || CODE || ']' || NAME NAME,
       SUPERGUID,
       ISLEAF,
       CASE
         WHEN SUBSTR(CODE, -2, 2) = '00' THEN
          LEVELNO - 1
         ELSE
          LEVELNO
       END DISTRLVL
  FROM P#FASP_T_PUPFASP001
 WHERE CODE LIKE
       SUBSTR(PKG_CONSTANTS.GETDEFAULTPROVINCE(),
              1,
              LENGTH(PKG_CONSTANTS.GETDEFAULTPROVINCE()) - 2) || '%'
   AND YEAR = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR')
   AND STATUS = '1'


--CODE_T_DISTRICT_ALL

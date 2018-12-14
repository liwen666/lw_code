begin
  EXECUTE IMMEDIATE Q'/
    CREATE OR REPLACE VIEW CODE_T_AGENCY_SPF_QUERY AS
      WITH T_DATA AS
       (SELECT X.YEAR,
               X.PROVINCE,
               X.GUID,
               X.CODE,
               X.NAME,
               X.LEVELNO + (Y.LEVELNO - 1) LEVELNO,
               X.ISLEAF,
               X.FININTORG,
               CASE
                 WHEN X.SUPERGUID = '0' THEN
                  Y.GUID
                 ELSE
                  X.SUPERGUID
               END SUPERGUID,
               '0' ISDISTRICT,
               '0' DIVTYPE,
               Y.GUID DISTRICTID,
               Y.NAME DISTRICTNAME,
               Y.CODE DISTRICTCODE,
               CAST(CASE
                      WHEN SUBSTR(Y.CODE, -2, 2) = '00' THEN
                       Y.LEVELNO - 1
                      ELSE
                       (Y.LEVELNO + 1) - 1
                    END AS VARCHAR2(32)) DISTRICTLVL,
               X.AGENCYTYPE,
               CAST(F_GET_LPAD(Y.CODE, '2,2,2') || F_GET_LPAD(X.CODE, '3-3-3-3-3') AS
                    VARCHAR2(60)) AS LVLID
          FROM P#FASP_T_PUBAGENCY X, P#FASP_T_PUPFASP001 Y
         WHERE X.YEAR = Y.YEAR
           AND X.PROVINCE = Y.CODE
           AND (Y.CODE LIKE
               SUBSTR(GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID'),
                       1,
                       LENGTH(GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID')) - 2) || '%' OR
               (GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID') = '8700'))
           AND X.STATUS = '1'
           AND Y.STATUS = '1'
        UNION
        SELECT YEAR,
               CODE PROVINCE,
               GUID,
               CODE,
               NAME,
               LEVELNO - 1 LEVELNO,
               0 ISLEAF,
               '' FININTORG,
               CASE
                 WHEN CODE =
                      SUBSTR(GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID'),
                             1,
                             LENGTH(GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID')) - 2) THEN
                  '0'
                 ELSE
                  SUPERGUID
               END SUPERGUID,
               '1' ISDISTRICT,
               '0' DIVTYPE,
               GUID DISTRICTID,
               NAME DISTRICTNAME,
               CODE DISTRICTCODE,
               CAST(CASE
                      WHEN SUBSTR(CODE, -2, 2) = '00' THEN
                       LEVELNO - 1
                      ELSE
                       (LEVELNO + 1) - 1
                    END AS VARCHAR2(32)) DISTRICTLVL,
               '' AGENCYTYPE,
               CAST(F_GET_LPAD(CODE, '2,2,2') AS VARCHAR2(60)) AS LVLID
          FROM P#FASP_T_PUPFASP001
         WHERE (CODE LIKE
               SUBSTR(GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID'),
                       1,
                       LENGTH(GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID')) - 2) || '%' OR
               (GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID') = '8700'))
           AND STATUS = '1'
        UNION ALL
        SELECT X.YEAR,
               X.PROVINCE,
               X.GUID,
               X.CODE,
               X.NAME,
               Y.LEVELNO,
               1 ISLEAF,
               '' FININTORG,
               X.DISTRICTID SUPERGUID,
               '0' ISDISTRICT,
               X.DIVTYPE,
               X.DISTRICTID,
               Y.CODE DISTRICTCODE,
               Y.NAME DISTRICTNAME,
               CAST(CASE
                      WHEN SUBSTR(Y.CODE, -2, 2) = '00' THEN
                       Y.LEVELNO - 1
                      ELSE
                       (Y.LEVELNO + 1) - 1
                    END AS VARCHAR2(32)) DISTRICTLVL,
               '' AGENCYTYPE,
               CAST(F_GET_LPAD(Y.CODE, '2,2,2') || F_GET_LPAD(X.CODE, '3-3-3-3-3') AS
                    VARCHAR2(60)) AS LVLID
          FROM P#SPF_T_AGENCY X, P#FASP_T_PUPFASP001 Y
         WHERE X.YEAR = Y.YEAR
           AND X.DISTRICTID = Y.GUID
           AND X.STATUS = '1'
           AND Y.STATUS = '1')
      SELECT YEAR,
             PROVINCE,
             GUID,
             CODE,
             NAME,
             LEVELNO,
             NVL((SELECT 0
                   FROM T_DATA X
                  WHERE YEAR = Y.YEAR
                    AND SUPERGUID = Y.GUID
                    AND ROWNUM < 2),
                 1) ISLEAF,
             FININTORG,
             SUPERGUID,
             ISDISTRICT,
             DIVTYPE,
             DISTRICTID,
             DISTRICTNAME,
             DISTRICTCODE,
             DISTRICTLVL,
             AGENCYTYPE,
             LVLID
        FROM T_DATA Y
       WHERE YEAR = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR')
    /';
end;
--项目综合查询左侧树查询视图

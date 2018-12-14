CREATE OR REPLACE VIEW CODE_V_SPFBASEINFOTREE AS
SELECT GUID, SUPERGUID, NAME, CODE, TYPEFLAG, AGENCYID, ISLEAF, ORDERID
  FROM (SELECT P.PROJTYPEID AS GUID,
               P.SUPERID AS SUPERGUID,
               P.PROJTYPENAME NAME,
               P.CODE AS CODE,
               '1' AS TYPEFLAG, --1 项目类型
               '' AGENCYID,
               '0' ISLEAF,
               P.ORDERID AS ORDERID
          FROM SPF_T_PROJECTTYPE P
         WHERE P.PROJTYPE <> '9'
        UNION ALL
        SELECT BF.SPFID GUID,
               BF.PROJTYPEID SUPERGUID,
               BF.SPFNAME NAME,
               BF.SPFCODE AS CODE,
               '0' AS TYPEFLAG, --0 专项
               BF.AGENCYID AS AGENCYID,
               '1' AS ISLEAF,
               0 AS ORDERID
          FROM SPF_T_FBASEINFO BF
         WHERE (BF.FUNDMANAGE = '2' OR BF.FUNDMANAGE = '3')
           AND BF.PROJTYPEID IN (SELECT A.PROJTYPEID
                                   FROM SPF_T_PROJECTTYPE A
                                  WHERE A.PROJTYPE <> '9'))
 ORDER BY TYPEFLAG DESC, CODE
--CODE_V_SPFBASEINFOTREE

CREATE OR REPLACE VIEW SPF_V_FDECLAREREGION AS
SELECT T1.CODE,
                      T1.NAME NAME,
                       T1.GUID,
                       B.DECLQUOTA,
                       B.DECLTIMES,
                       DECODE(B.DISTRICTID, T1.GUID, '1', '0') CHECKED,
                       T1.PROVINCE_SH,
                       T1.CITY,
                       T1.COUNTY,
                       B.DATAKEY,
                       B.SPFID,
                       T1.YEAR,T1.PROVINCE,T1.SUPERGUID,T1.ISLEAF,T1.LEVELNO
                  FROM CODE_T_DISTRICT T1
                  LEFT JOIN (SELECT * FROM SPF_T_FDECLAREREGION ) B
                    ON T1.GUID = B.DISTRICTID
                  WHERE  CITY IS NOT NULL AND COUNTY IS NOT NULL
--SPF_V_FDECLAREREGION

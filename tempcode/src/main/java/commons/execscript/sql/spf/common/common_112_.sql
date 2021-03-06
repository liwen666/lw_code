CREATE OR REPLACE VIEW CODE_T_DISTRICT_SPF AS
SELECT PROVINCE,
       GUID,
       CODE,
       '['||CODE||']'||NAME NAME,
       ISLEAF,
       LEVELNO,
       SUPERGUID,
       '' ALIAS,
       '' PINYIN,
       '' REMARK,
       '' CREATEDATE,
       '' STARTDATE,
       '' ENDDATE,
       '' SRCGUID,
       '' DESGUID,
       SRCSCALE,
       STATUS,
       DBVERSION,
       '' EMW,
       BGTLEVEL,
       STDCODE,
       DISTRLVL,
       ZGXIAN,
       ISSELF,
       PROVINCE_SH,
       CITY,
       COUNTY,
       PROVINCE_SHID,
       CITYID,
       COUNTYID,
       YEAR
  FROM CODE_M_DISTRICT
 WHERE YEAR = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR')
  ORDER BY CODE
--CODE_T_DISTRICT_SPF_ADDCODE

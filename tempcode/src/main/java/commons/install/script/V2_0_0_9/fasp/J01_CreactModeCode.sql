BEGIN
EXECUTE IMMEDIATE Q'{create or replace view fasp_v_pubfundlevel as
select t.GUID ID,t.code ShowID,CASE WHEN SUPERGUID = '0' THEN '#' ELSE nvl(SUPERGUID,'#') END SuperID,t.ISLEAF EndFlag, f_get_lpad(t.code,'4') LvlID, t."SUPERGUID",t."ISLEAF",t."LEVELNO",t."VERSION",t."PROVINCE",t."YEAR",t."REMARK",t."CREATETIME",t."STARTTIME",t."ENDTIME",t."SRCGUID",t."SRCSCALE",t."DESGUID",t."ADMDIV",t."ALIAS",t."PINYIN",t."GUID",t."CODE",t."NAME",t."STATUS",t."DBVERSION" from FASP_T_PUBFUNDLEVEL t where year = global_multyear_cz.Secu_f_GLOBAL_PARM('YEAR')}';
EXECUTE IMMEDIATE Q'{
create or replace view fasp_v_pubfundmm as
select t.GUID ID,t.code ShowID,CASE WHEN SUPERGUID = '0' THEN '#' ELSE nvl(SUPERGUID,'#') END SuperID,t.ISLEAF EndFlag, f_get_lpad(t.code,'4') LvlID, t."ADMDIV",t."ALIAS",t."PINYIN",t."GUID",t."CODE",t."NAME",t."STATUS",t."SUPERGUID",t."ISLEAF",t."LEVELNO",t."VERSION",t."PROVINCE",t."YEAR",t."REMARK",t."CREATETIME",t."STARTTIME",t."ENDTIME",t."SRCGUID",t."SRCSCALE",t."DESGUID",t."DBVERSION" from FASP_T_PUBFUNDMM t where year = global_multyear_cz.Secu_f_GLOBAL_PARM('YEAR')}';


/*创建视图*/
DECLARE
begin
  
EXECUTE IMMEDIATE Q'/create or replace view spf_v_backlevel_zx as
select '11' as ID,'11' as code, '中央财政' as name, '' as SUPERGUID ,'1' as ISLEAF
  from dual
union
select '12' as ID, '12' as code, '中央部门' as name, '' as SUPERGUID ,'1' as ISLEAF
  from dual
union
select '21' as ID ,'21' as code, '省财政' as name, '' as SUPERGUID ,'1' as ISLEAF
  from dual
union
select  '22' as ID, '22' as code, '省部门' as name, '' as SUPERGUID ,'1' as ISLEAF
  from dual
union
select '31' as ID, '31' as code, '市财政' as name, '' as SUPERGUID ,'1' as ISLEAF
  from dual
union
select  '32' as ID, '32' as code, '市部门' as name, '' as SUPERGUID ,'1' as ISLEAF
  from dual
union
select '41' as ID,'41' as code, '县财政' as name, '' as SUPERGUID ,'1' as ISLEAF
  from dual
union
select '42' as ID,'42' as code, '县部门' as name, '' as SUPERGUID,'1' as ISLEAF
  from dual
union
select '9' as ID,'9' as code, '申报单位' as name, '' as SUPERGUID,'1' as ISLEAF
  from dual
union
select '0' as ID,'0' as code, '回退上一步' as name, '' as SUPERGUID,'1' as ISLEAF
  from dual/';

end;
--专项项目回退级次

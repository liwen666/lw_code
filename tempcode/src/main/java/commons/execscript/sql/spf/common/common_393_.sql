/*创建视图*/
DECLARE
begin
  
EXECUTE IMMEDIATE Q'/create or replace view spf_v_backlevel_yj as
select  '1' as ID, '1' as code, '财政' as name, '' as SUPERGUID, '1' as ISLEAF
  from dual
union
select '2' as ID, '2' as code, '主管部门' as name, '' as SUPERGUID, '1' as ISLEAF
  from dual
union
select '0' as ID,'0' as code, '回退上一步' as name, '' as SUPERGUID,'1' as ISLEAF
  from dual/';

end;
--一级项目回退级次

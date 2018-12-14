/*创建视图*/
DECLARE
begin
  
EXECUTE IMMEDIATE Q'/create or replace view spf_v_backlevel_next as
select '0' as ID,'0' as code, '回退上一步' as name, '' as SUPERGUID,'1' as ISLEAF
  from dual/';

end;
--专项回退上一步级次

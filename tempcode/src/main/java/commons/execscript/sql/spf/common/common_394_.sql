/*������ͼ*/
DECLARE
begin
  
EXECUTE IMMEDIATE Q'/create or replace view spf_v_backlevel_next as
select '0' as ID,'0' as code, '������һ��' as name, '' as SUPERGUID,'1' as ISLEAF
  from dual/';

end;
--ר�������һ������

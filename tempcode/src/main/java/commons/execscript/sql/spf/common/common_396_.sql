/*������ͼ*/
DECLARE
begin
  
EXECUTE IMMEDIATE Q'/create or replace view spf_v_backlevel_ej as
select  '1' as ID, '1' as code, '����' as name, '' as SUPERGUID, '1' as ISLEAF
  from dual
union
select '2' as ID, '2' as code, '���ܲ���' as name, '' as SUPERGUID, '1' as ISLEAF
  from dual
union
select  '9' as ID, '9' as code, '�걨��λ' as name, '' as SUPERGUID, '1' as ISLEAF
  from dual
  union
select '0' as ID,'0' as code, '������һ��' as name, '' as SUPERGUID,'1' as ISLEAF
  from dual/';

end;
--������Ŀ���˼���

/*������ͼ*/
DECLARE
begin
  
EXECUTE IMMEDIATE Q'/create or replace view spf_v_backlevel_ejsj as
select  '5' as ID, '5' as code, '�ϼ�����' as name, '' as SUPERGUID, '1' as ISLEAF
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
--������Ŀ���˼���(�ϼ�����)

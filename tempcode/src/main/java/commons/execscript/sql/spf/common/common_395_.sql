/*������ͼ*/
DECLARE
begin
  
EXECUTE IMMEDIATE Q'/create or replace view spf_v_backlevel_zx as
select '11' as ID,'11' as code, '�������' as name, '' as SUPERGUID ,'1' as ISLEAF
  from dual
union
select '12' as ID, '12' as code, '���벿��' as name, '' as SUPERGUID ,'1' as ISLEAF
  from dual
union
select '21' as ID ,'21' as code, 'ʡ����' as name, '' as SUPERGUID ,'1' as ISLEAF
  from dual
union
select  '22' as ID, '22' as code, 'ʡ����' as name, '' as SUPERGUID ,'1' as ISLEAF
  from dual
union
select '31' as ID, '31' as code, '�в���' as name, '' as SUPERGUID ,'1' as ISLEAF
  from dual
union
select  '32' as ID, '32' as code, '�в���' as name, '' as SUPERGUID ,'1' as ISLEAF
  from dual
union
select '41' as ID,'41' as code, '�ز���' as name, '' as SUPERGUID ,'1' as ISLEAF
  from dual
union
select '42' as ID,'42' as code, '�ز���' as name, '' as SUPERGUID,'1' as ISLEAF
  from dual
union
select '9' as ID,'9' as code, '�걨��λ' as name, '' as SUPERGUID,'1' as ISLEAF
  from dual
union
select '0' as ID,'0' as code, '������һ��' as name, '' as SUPERGUID,'1' as ISLEAF
  from dual/';

end;
--ר����Ŀ���˼���

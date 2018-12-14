declare
  v_n number;
begin
  --ר��
  select count(0) into v_n from user_indexes ui where ui.index_name = 'IDX_FBASEINFO_001' and ui.table_name='P#SPF_T_FBASEINFO';
  if v_n = 1 then
    execute immediate 'drop index IDX_FBASEINFO_001';
  end if;
  execute immediate 'create index IDX_FBASEINFO_001 on P#SPF_T_FBASEINFO(STATUS, SPFID)';
  
  --��Ŀ
  select count(0) into v_n from user_indexes ui where ui.index_name = 'IDX_PBASEINFO_001' and ui.table_name='P#SPF_T_PBASEINFO';
  if v_n = 1 then
    execute immediate 'drop index IDX_PBASEINFO_001';
  end if;
  execute immediate 'create index IDX_PBASEINFO_001 on P#SPF_T_PBASEINFO(STATUS, PROJECTID)';
end;
--��Ŀ�����ר�����������

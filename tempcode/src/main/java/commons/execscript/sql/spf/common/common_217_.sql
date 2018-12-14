declare
  v_n number;
begin
  --专项
  select count(0) into v_n from user_indexes ui where ui.index_name = 'IDX_FBASEINFO_001' and ui.table_name='P#SPF_T_FBASEINFO';
  if v_n = 1 then
    execute immediate 'drop index IDX_FBASEINFO_001';
  end if;
  execute immediate 'create index IDX_FBASEINFO_001 on P#SPF_T_FBASEINFO(STATUS, SPFID)';
  
  --项目
  select count(0) into v_n from user_indexes ui where ui.index_name = 'IDX_PBASEINFO_001' and ui.table_name='P#SPF_T_PBASEINFO';
  if v_n = 1 then
    execute immediate 'drop index IDX_PBASEINFO_001';
  end if;
  execute immediate 'create index IDX_PBASEINFO_001 on P#SPF_T_PBASEINFO(STATUS, PROJECTID)';
end;
--项目主表和专项主表加索引

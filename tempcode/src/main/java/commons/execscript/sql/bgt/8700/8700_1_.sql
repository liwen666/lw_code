declare 
v_n number(8):=0;

begin
select count(0) into v_n  from user_tables tab where tab.TABLE_NAME='P#BGT_T_BUSINESSCHECKDEF';
if v_n=1 then
  select count(0) into v_n from user_constraints uc where table_name = 'P#BGT_T_BUSINESSCHECKDEF' and constraint_type='P';
  if v_n=0 then
    
  select count(0) into v_n from user_indexes t where t.index_name='BUSINESSCHECKDEF_PKY';
     --如果有索引删除
     if v_n=1 then
       execute immediate 'drop index BUSINESSCHECKDEF_PKY';
     end if;
  --重新创建主键索引  
  execute immediate 'alter table P#BGT_T_BUSINESSCHECKDEF add constraint BUSINESSCHECKDEF_PKY primary key (PROVINCE, YEAR, STATUS, GUID)';
  
  end if;

  
end if;


end;
--重建P#BGT_T_BUSINESSCHECKDEF表主键

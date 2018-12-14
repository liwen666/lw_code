declare
  v_n1 number(8):= 0;
begin
  select count(1) into v_n1 from user_views where upper(view_name) = 'BGT_T_BUSIRULETOROLE';
   if v_n1=0 then 
    execute immediate Q'{create table BGT_T_BUSIRULETOROLE(guid VARCHAR2(32) default SYS_GUID() not null,BUSIRULEID VARCHAR2(32),ROLEID  VARCHAR2(32))}';
    execute immediate 'alter table BGT_T_BUSIRULETOROLE add constraint BGT_T_BUSIRULETOROLE_PKY  primary key (guid)';
    SYS_P_PARTITION_TABLE('BGT_T_BUSIRULETOROLE');

   end if;
 

end;

--创建表业务审核规则对角色表BGT_BUSIRULETOROLE

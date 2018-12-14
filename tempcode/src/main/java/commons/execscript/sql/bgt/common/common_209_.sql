Declare
  v_n1 number(8) := 0;
begin
  select count(1)
    into v_n1
    from user_views
   where upper(view_name) = 'BGT_T_TRANSFERPAYSETTING';
  if v_n1 = 0 then
    execute immediate Q'{
    CREATE TABLE BGT_T_TRANSFERPAYSETTING(
    APPID          VARCHAR2(32) NOT NULL,
    GUID           VARCHAR2(32) DEFAULT SYS_GUID() NOT NULL,
    CURDISTRICT    VARCHAR2(32) NOT NULL,
    RELDISTRICT    VARCHAR2(32) NOT NULL   
  )}';
    execute immediate 'alter table BGT_T_TRANSFERPAYSETTING add constraint BGT_T_TRANSFERPAYSETTING_PKY  primary key (APPID, GUID)';
    SYS_P_PARTITION_TABLE('BGT_T_TRANSFERPAYSETTING');
  
  end if;
end;
--杨喜梅_20170421_转移支付设置新增功能

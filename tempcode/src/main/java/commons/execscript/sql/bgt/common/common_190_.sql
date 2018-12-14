declare
  v_n1 number(8) := 0;
begin
  select count(1)
    into v_n1
    from user_views
   where upper(view_name) = 'BGT_T_FLOW_OPINION';
  if v_n1 = 0 then
    execute immediate Q'{create table BGT_T_FLOW_OPINION (
    DATAKEY VARCHAR2(32) default SYS_GUID() not null,
    FKDATAKEY VARCHAR2(32),
    CREATEUSER  VARCHAR2(32),
    REMARK VARCHAR2(2000),
    DBVERSION TIMESTAMP
    )}';
    execute immediate 'alter table BGT_T_FLOW_OPINION add constraint BGT_T_FLOW_OPINION  primary key (DATAKEY)';
    SYS_P_PARTITION_TABLE('BGT_T_FLOW_OPINION');
    execute immediate Q'{create or replace view bgt_t_flow_opinion as
    select CREATEUSER,DATAKEY,DBVERSION,FKDATAKEY,REMARK,STATUS from P#BGT_T_FLOW_OPINION where  STATUS='1'}';
      end if;
end;--9-BGT_T_FLOW_OPINION

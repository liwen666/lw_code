
declare
  v_n1 number(8) := 0;
begin
  select count(1)
    into v_n1
    from user_views
   where upper(view_name) = 'BGT_T_FLOW_ATTACHMENT';
  if v_n1 = 0 then
    execute immediate Q'{create table BGT_T_FLOW_ATTACHMENT(
    DATAKEY VARCHAR2(32) default SYS_GUID() not null,
    FKDATAKEY VARCHAR2(32),
    CREATEUSER  VARCHAR2(32),
    ATTACHID VARCHAR2(32),
    DBVERSION TIMESTAMP
    )}';
    execute immediate 'alter table BGT_T_FLOW_ATTACHMENT add constraint BGT_T_FLOW_ATTACHMENT  primary key (DATAKEY)';
    SYS_P_PARTITION_TABLE('BGT_T_FLOW_ATTACHMENT');
    execute immediate Q'{create or replace view bgt_t_flow_attachment as
    select ATTACHID,CREATEUSER,DATAKEY,DBVERSION,FKDATAKEY,STATUS from P#BGT_T_FLOW_ATTACHMENT where  STATUS='1'}';
    end if;
end;
--10-BGT_T_FLOW_ATTACHMENT

declare
  v_n1 number(8) := 0;
begin
  select count(1)
    into v_n1
    from user_views
   where upper(view_name) = 'BGT_T_RFLOW_LOG';
  if v_n1 = 0 then
    execute immediate Q'{create table BGT_T_RFLOW_LOG(guid VARCHAR2(32) default SYS_GUID() not null,
    TASKID VARCHAR2(32) not null,CFLOWBATCHID  VARCHAR2(32) ,OBJECTID  VARCHAR2(32) not null,BFLOWBATCHID VARCHAR2(32) ,
    NODEID VARCHAR2(32),WFVERSION VARCHAR2(32))}';
    execute immediate 'alter table BGT_T_RFLOW_LOG add constraint BGT_T_RFLOW_LOG  primary key (guid,TASKID,OBJECTID,NODEID,WFVERSION)';
    SYS_P_PARTITION_TABLE('BGT_T_RFLOW_LOG');
  end if;
   execute immediate Q'{ create or replace view BGT_T_RFLOW_LOG as
select BFLOWBATCHID,CFLOWBATCHID,DBVERSION,GUID,NODEID,OBJECTID,STATUS,TASKID,WFVERSION from P#BGT_T_RFLOW_LOG where   STATUS='1'}';
end;

--创建横纵向流程关联表20170612_zz

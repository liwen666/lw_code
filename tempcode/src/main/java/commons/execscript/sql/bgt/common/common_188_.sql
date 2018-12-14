declare
  v_n1 number(8) := 0;
begin
  select count(1)
    into v_n1
    from user_views
   where upper(view_name) = 'BGT_T_BFLOW_LOG';
  if v_n1 = 0 then
    execute immediate Q'{create table BGT_T_BFLOW_LOG (
    DATAKEY VARCHAR2(32) default SYS_GUID() not null, 
    TASKID VARCHAR2(32),
    OBJECTID VARCHAR2(32),
    CFLOWBATCHID  VARCHAR2(32),
    BATCHID VARCHAR2(32),
    OBJSTATUS CHAR(1) default '0',
    DBVERSION TIMESTAMP,
    CREATETIME TIMESTAMP DEFAULT SYSTIMESTAMP
    )}';
    execute immediate 'alter table BGT_T_BFLOW_LOG add constraint BGT_T_BFLOW_LOG  primary key (DATAKEY)';
    SYS_P_PARTITION_TABLE('BGT_T_BFLOW_LOG');
    execute immediate Q'{create or replace view bgt_t_bflow_log as
    select BATCHID,DATAKEY,DBVERSION,OBJECTID,STATUS,TASKID,CFLOWBATCHID, OBJSTATUS,CREATETIME   from P#BGT_T_BFLOW_LOG where  STATUS='1'
    }';
  end if;
end;
--8-BGT_T_BFLOW_LOG

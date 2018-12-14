declare
  v_n1 number(8) := 0;
begin
  select count(1)
    into v_n1
    from user_views
   where upper(view_name) = 'EFM_T_BFLOW_LOG';
  if v_n1 = 0 then
    execute immediate Q'{create  table EFM_T_BFLOW_LOG (
    DATAKEY VARCHAR2(32) default SYS_GUID() not null, 
    AGENCYID VARCHAR2(32),
    SOURCEBATCHID VARCHAR2(32),
    BATCHID VARCHAR2(32),
    BUSITYPEID  VARCHAR2(32), 
    SOURCENODEID  VARCHAR2(32),
    NODEID VARCHAR2(32),
    WFSTATUS  CHAR(1),
    CREATEUSER  VARCHAR2(32),
    CREATETIME TIMESTAMP DEFAULT SYSTIMESTAMP,
    REMARK VARCHAR2(200),
    WFVERSION VARCHAR2(32),
    DBVERSION TIMESTAMP
    )}';
    execute immediate 'alter table EFM_T_BFLOW_LOG add constraint EFM_T_BFLOW_LOG  primary key (DATAKEY)';
    SYS_P_PARTITION_TABLE('EFM_T_BFLOW_LOG');
    execute immediate Q'{create or replace view efm_t_bflow_log as
    select AGENCYID,WFVERSION,BATCHID,BUSITYPEID,CREATEUSER,CREATETIME,DATAKEY,DBVERSION,NODEID,REMARK,SOURCEBATCHID,SOURCENODEID,STATUS,WFSTATUS from P#EFM_T_BFLOW_LOG where  STATUS='1'
    }';
  end if;
end;
--7-EFM_T_BFLOW_LOG

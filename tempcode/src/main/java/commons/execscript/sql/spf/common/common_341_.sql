declare 
v_count number(1);--查看表是否存在
begin 
  select count(*) INTO v_count from user_tables t where  t.table_name = 'P#SPF_T_CFLOW_ACT';
  if v_count = 0 THEN
   execute immediate Q'{
     CREATE TABLE P#SPF_T_CFLOW_ACT
		(
		 DATAKEY        VARCHAR2(32) DEFAULT SYS_GUID() NOT NULL,
		  SPFID          VARCHAR2(32),
		  OBJECTID       VARCHAR2(32),
		  SOURCEAGENCYID VARCHAR2(32),
		  TARGETAGENCYID VARCHAR2(32),
		  SOURCEBATCHID  VARCHAR2(32),
		  BATCHID        VARCHAR2(32),
		  BUSITYPEID     VARCHAR2(32),
		  WFSTATUS       CHAR(1),
		  WFDIRECTION    CHAR(1) DEFAULT '#',
		  REMARK         VARCHAR2(200),
		  CREATEUSER     VARCHAR2(32),
		  DBVERSION      TIMESTAMP(6),
		  STEP           VARCHAR2(32),
		  STATUS         CHAR(1) DEFAULT 1 NOT NULL,
		  CREATETIME     TIMESTAMP(6) DEFAULT SYSDATE,
		  YEAR           CHAR(4) NOT NULL,
		  PROVINCE       VARCHAR2(9) NOT NULL,
		  DISTRICTID     VARCHAR2(32)
		)
	  }';
  	--刷P#SPF_T_CFLOW_ACT表触发器
    execute immediate Q'{alter table P#SPF_T_CFLOW_ACT add constraint PK_P#SPF_T_CFLOW_ACT primary key (PROVINCE, YEAR, STATUS, DATAKEY)}';
  	SYS_P_PARTITION_TABLE('P#SPF_T_CFLOW_ACT') ;
    execute immediate Q'{create index IDX_SPF_T_CFLOW_ACT1 on P#SPF_T_CFLOW_ACT (DATAKEY)}';
    execute immediate Q'{create index IDX_SPF_T_CFLOW_ACT2 on P#SPF_T_CFLOW_ACT (BUSITYPEID, BATCHID, OBJECTID)}';
    execute immediate Q'{create index IDX_SPF_T_CFLOW_ACT3 on P#SPF_T_CFLOW_ACT (BUSITYPEID, BATCHID, TARGETAGENCYID)}';
    execute immediate Q'{create index IDX_SPF_T_CFLOW_ACT4 on P#SPF_T_CFLOW_ACT (BUSITYPEID, BATCHID, TARGETAGENCYID, WFDIRECTION)}';
    execute immediate Q'{create index IDX_SPF_T_CFLOW_ACT5 on P#SPF_T_CFLOW_ACT (DATAKEY, STEP)}';
    execute immediate Q'{create index IDX_SPF_T_CFLOW_ACT6 on P#SPF_T_CFLOW_ACT (BUSITYPEID, TARGETAGENCYID, DISTRICTID, WFDIRECTION)}';
  execute immediate Q'{
CREATE OR REPLACE VIEW SPF_T_CFLOW_ACT AS
SELECT BATCHID,
       BUSITYPEID,
       CREATETIME,
       CREATEUSER,
       DATAKEY,
       DBVERSION,
       OBJECTID,
       REMARK,
       SOURCEAGENCYID,
       SOURCEBATCHID,
       SPFID,
       STATUS,
       STEP,
       TARGETAGENCYID,
       WFDIRECTION,
       WFSTATUS,
       DISTRICTID
  FROM P#SPF_T_CFLOW_ACT
 WHERE STATUS = '1'}';
  end if;
end;

--创建活动表SPF_T_CFLOW_ACT

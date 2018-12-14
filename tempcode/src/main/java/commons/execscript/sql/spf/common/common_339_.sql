DECLARE 
V_COUNT NUMBER(1);--查看表是否存在
BEGIN 
  SELECT COUNT(*) INTO V_COUNT FROM USER_TABLES T WHERE  T.TABLE_NAME = 'P#SPF_T_CFLOW_HIST';
  IF V_COUNT = 0 THEN
     EXECUTE IMMEDIATE Q'{create table P#SPF_T_CFLOW_HIST
	(
	  datakey        VARCHAR2(32) default SYS_GUID() not null,
	  spfid          VARCHAR2(32),
	  objectid       VARCHAR2(32),
	  sourceagencyid VARCHAR2(32),
	  targetagencyid VARCHAR2(32),
	  sourcebatchid  VARCHAR2(32),
	  batchid        VARCHAR2(32),
	  busitypeid     VARCHAR2(32),
	  wfstatus       CHAR(1),
	  wfdirection    CHAR(1),
	  remark         VARCHAR2(200),
	  createuser     VARCHAR2(32),
	  dbversion      TIMESTAMP(6),
	  step           VARCHAR2(32),
	  status         CHAR(1) default 1 not null,
	  createtime     TIMESTAMP(6) default SYSDATE,
	  year           CHAR(4) not null,
	  province       VARCHAR2(9) not null,
	  districtid     VARCHAR2(32)
	)
	  }';
	
	--刷P#SPF_T_CFLOW_HIST表触发器
    EXECUTE IMMEDIATE Q'{alter table P#SPF_T_CFLOW_HIST add constraint PK_P#SPF_T_CFLOW_HIST_KEY primary key (PROVINCE, YEAR, STATUS, DATAKEY)}';
	SYS_P_PARTITION_TABLE('P#SPF_T_CFLOW_HIST') ;
    EXECUTE IMMEDIATE Q'{create index IDX_SPF_T_BFLOW_ACT1 on P#SPF_T_CFLOW_HIST (BUSITYPEID, BATCHID, TARGETAGENCYID)}';
    EXECUTE IMMEDIATE Q'{create index IDX_SPF_T_BFLOW_ACT2 on P#SPF_T_CFLOW_HIST (BUSITYPEID, BATCHID, SOURCEAGENCYID, WFDIRECTION)}';
    EXECUTE IMMEDIATE Q'{create index IDX_SPF_T_BFLOW_ACT3 on P#SPF_T_CFLOW_HIST (BUSITYPEID, BATCHID, TARGETAGENCYID, WFDIRECTION)}';
    EXECUTE IMMEDIATE Q'{create index IDX_SPF_T_BFLOW_ACT4 on P#SPF_T_CFLOW_HIST (DATAKEY)}';
    EXECUTE IMMEDIATE Q'{
CREATE OR REPLACE VIEW SPF_T_CFLOW_HIST AS
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
  FROM P#SPF_T_CFLOW_HIST
 WHERE  STATUS = '1'
 }';
  END IF;
END;

--创建历史表P#SPF_T_CFLOW_HIST

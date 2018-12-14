declare 
v_count number(1);
begin 
  select count(*) INTO v_count from user_tables t where  t.table_name = 'P#SPF_T_FLOWLOG';
  if v_count = 0 THEN
   execute immediate Q'{
     CREATE TABLE P#SPF_T_FLOWLOG
		(
		  DATAKEY        VARCHAR2(32) DEFAULT SYS_GUID() NOT NULL,
		  CFLOWBATCHID   VARCHAR2(32),
		  BFLOWBATCHID   VARCHAR2(32),
		  OBJECTID       VARCHAR2(32) not null,
		  NODEID         VARCHAR2(32),
		  WFVERSION      VARCHAR2(32),
		  DBVERSION      TIMESTAMP(6),
		  STATUS         CHAR(1) DEFAULT 1 NOT NULL,
		  YEAR           CHAR(4) NOT NULL,
		  PROVINCE       VARCHAR2(9) NOT NULL
		)
	  }';
    execute immediate Q'{alter table P#SPF_T_FLOWLOG add constraint PK_P#SPF_T_FLOWLOG primary key (PROVINCE, YEAR, STATUS, DATAKEY)}';
  	SYS_P_PARTITION_TABLE('P#SPF_T_FLOWLOG') ;
  execute immediate Q'{
	CREATE OR REPLACE VIEW SPF_T_FLOWLOG AS
		SELECT DATAKEY,       
		      CFLOWBATCHID, 
		      BFLOWBATCHID,  
		      OBJECTID,      
		      NODEID,         
		      WFVERSION,
			  DBVERSION
	   FROM P#SPF_T_FLOWLOG
	   WHERE STATUS = '1'}';
  end if;
end;
--创建纵向横向关系表

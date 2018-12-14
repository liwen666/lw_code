declare 
  i integer;
begin
   select count(1) into i from user_tab_cols where table_name='P#CDT_T_TASKTYPE' and column_name='NEEDACCOUNT';
   if i = 0 then
    execute immediate 'alter table P#CDT_T_TASKTYPE add needaccount CHAR(1)';
   end if;
   execute immediate q'{CREATE OR REPLACE VIEW CDT_T_TASKTYPE AS
SELECT TASKTYPEID,TASKTYPENAME,TASKTYPEDESC,DISTRICTID,ORDERID,TASKBUSITYPE,TASKCYCLE_TYPE,CODERULE,NAMERULE,TARGET_VIEWNAME,STATUS,APPID,PROCESS_TPLID,TRANSFORMTYPE,DBVERSION,COLLECTRANGE,CONFIRMFUNC,ISSUM,SUMCYC,REFPROC,ISSECUCTRL,NEEDACCOUNT
  FROM P#CDT_T_TASKTYPE
 WHERE STATUS='1'}';
 select count(1) into i from user_tables where table_name='P#CDT_T_TASKTYPEACC';
 IF i = 0 then
   execute immediate q'{create table P#CDT_T_TASKTYPEACC
  (
    tasktypeid VARCHAR2(32) not null,
    accountid       VARCHAR2(32) not null,
    orderid    NUMBER(10) default 0,
    dbversion  TIMESTAMP(6) default SYSDATE,
    status     CHAR(1) default '1' not null,
    guid       VARCHAR2(32) default SYS_GUID() not null
  )}';
  execute immediate 'alter table P#CDT_T_TASKTYPEACC add constraint PK_P#CDT_T_TASKTYPEACC primary key (GUID)';
  execute immediate q'{CREATE OR REPLACE VIEW CDT_T_TASKTYPEACC AS
    SELECT  GUID,TASKTYPEID,ACCOUNTID,ORDERID,STATUS, DBVERSION FROM P#CDT_T_TASKTYPEACC WHERE  STATUS='1'}';
 end if;
  
end;
--任务类型管理新增对应INDI单据类型并且因为移除CDT包重构修改URL链接

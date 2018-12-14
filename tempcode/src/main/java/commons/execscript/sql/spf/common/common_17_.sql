declare
v_tn number;
begin
  --审核结果表
  select count(1) into v_tn from user_tables a where a.TABLE_NAME='P#SPF_T_CHECKRESULT';
  if v_tn=0 then
  execute immediate Q'{create table P#SPF_T_CHECKRESULT
    (
      province       VARCHAR2(9) not null,
      year           CHAR(4) not null,
      status         CHAR(1) default '1' not null,
      dbversion      TIMESTAMP(6) default SYSDATE,
      datakey        VARCHAR2(32) default sys_guid() not null,
      guid           VARCHAR2(32) default sys_guid(),
      projectid      VARCHAR2(32),
      processid      VARCHAR2(50),
      busicheckid    VARCHAR2(32),
      ckdate         VARCHAR2(32) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
      ckflag         CHAR(1),
      userguid       VARCHAR2(32),
      userupagencyid VARCHAR2(32),
      userdistrictid VARCHAR2(32)
    )}';
execute immediate 'alter table P#SPF_T_CHECKRESULT add constraint PK_P#SPF_T_CHECKRESULT primary key (PROVINCE, YEAR, STATUS, DATAKEY, GUID, PROJECTID, PROCESSID, BUSICHECKID)';

  sys_p_partition_table('P#SPF_T_CHECKRESULT');
  end if;

  --审核错误明细表
  select count(1) into v_tn from user_tables a where a.TABLE_NAME='P#SPF_T_CHECKERROR';
  if v_tn=0 then
    
  execute immediate Q'{create table P#SPF_T_CHECKERROR
(
  province      VARCHAR2(9) not null,
  year          CHAR(4) not null,
  status        CHAR(1) default '1' not null,
  dbversion     TIMESTAMP(6) default SYSDATE,
  datakey       VARCHAR2(32) default sys_guid() not null,
  guid          VARCHAR2(32) default sys_guid(),
  checkresultid VARCHAR2(32),
  rightvalue    VARCHAR2(500),
  leftvalue     VARCHAR2(500),
  deviation     VARCHAR2(500),
  isleafagency  CHAR(1),
  ckresult      CLOB,
  checkdata     CLOB
)}';

  execute immediate 'alter table P#SPF_T_CHECKERROR add constraint PK_P#SPF_T_CHECKERROR primary key (PROVINCE, YEAR, STATUS, DATAKEY, CHECKRESULTID, GUID)';

  sys_p_partition_table('P#SPF_T_CHECKERROR');
   end if;
end;
--FMH_项目库审核结果及错误表建表语句

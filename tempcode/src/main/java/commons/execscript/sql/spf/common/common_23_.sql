begin
  execute immediate Q'{
create table P#SPF_T_AUDITROLE(
 year      CHAR(4) not null,
 province  VARCHAR2(9) not null,
 status       CHAR(1) default 1 not null,
 dbversion    TIMESTAMP(6) default SYSDATE,
 orderid      NUMBER(9) default 0,
 auditid   varchar2(32) not null,
 guid      VARCHAR2(32) default sys_guid() not null,
 roleid    varchar2(32) not null
)}';
  execute immediate Q'{alter table P#SPF_T_AUDITROLE add constraint PK_P#SPF_T_AUDITROLE primary key (PROVINCE, YEAR, STATUS, AUDITID, ROLEID, GUID)}';
  sys_p_partition_table('P#SPF_T_AUDITROLE');
end;
--FMH_创建项目报表设置_审核关联角色表

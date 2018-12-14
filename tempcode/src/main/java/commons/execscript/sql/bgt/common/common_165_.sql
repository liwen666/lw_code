declare
  v_n1 number(8) := 0;
begin
  select count(1)
    into v_n1
    from user_views
   where upper(view_name) = 'BGT_T_CUSTOMCONDITION';
  if v_n1 = 0 then
    execute immediate Q'{create table BGT_T_CUSTOMCONDITION(guid VARCHAR2(32) default SYS_GUID() not null,
    TABLEID VARCHAR2(32) not null,ID  VARCHAR2(32) not null,PID  VARCHAR2(32) not null,USERID VARCHAR2(32) NOT NULL,
    ISLOGIC CHAR(1),ISROOT CHAR(1),LOGIC VARCHAR2(10),ORDERNUM NUMBER(9),COL VARCHAR2(30),COLID VARCHAR2(32),COLNAME VARCHAR2(100),COLTYPE CHAR(1),CSID VARCHAR2(32), OPT VARCHAR2(10),VAL VARCHAR2(4000),VALNAME VARCHAR2(4000),ISLEAF CHAR(1))}';
    execute immediate 'alter table BGT_T_CUSTOMCONDITION add constraint PK_BGT_T_CUSTOMCONDITION  primary key (guid,TABLEID,ID,USERID)';
    SYS_P_PARTITION_TABLE('BGT_T_CUSTOMCONDITION');
  end if;
end;
--创建自定义条件表20161010_zq

declare
  v_n1 number(8) := 0;
BEGIN
  select count(1)
    into v_n1
    from user_tables
   where upper(table_name) = 'P#SPF_T_LISTREGIST';
  if v_n1 = 0 then
    execute immediate Q'{
create table SPF_T_LISTREGIST(
  GUID      VARCHAR2(32) default SYS_GUID() not null,
  DNAME     VARCHAR2(200),
  DEALID    VARCHAR2(50),
  PROVINCE  VARCHAR2(9) not null,
  YEAR      CHAR(4) not null,
  STATUS    CHAR(1) default '1' not null,
  DBVERSION TIMESTAMP(6) default SYSDATE,
  RESPONSER VARCHAR2(100))
     }';
    execute immediate 'alter table SPF_T_LISTREGIST add constraint SPF_T_LISTREGIST  primary key (guid)';
    SYS_P_PARTITION_TABLE('SPF_T_LISTREGIST');
  end if;
end;

--¥¥Ω®±ÌSPF_T_LISTREGIST

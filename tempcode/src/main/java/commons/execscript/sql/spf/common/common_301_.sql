 declare
  v_n1 number(8) := 0;
BEGIN
  select count(1)
    into v_n1
    from user_tables
   where upper(table_name) = 'P#SPF_T_LISTCONDISET';
  if v_n1 = 0 then
    execute immediate Q'{create table SPF_T_LISTCONDISET(guid VARCHAR2(32) default SYS_GUID() not null,
     MODECODE  VARCHAR2(200),MODELID  VARCHAR2(32),FACTOR VARCHAR2(50),ORDERID NUMBER(9),TABLEID VARCHAR(32))}';
    execute immediate 'alter table SPF_T_LISTCONDISET add constraint SPF_T_LISTCONDISET  primary key (guid)';
    SYS_P_PARTITION_TABLE('SPF_T_LISTCONDISET');
  end if;
   select count(1)
    into v_n1
    from user_tables
   where upper(table_name) = 'SPF_T_LISTCONDISET_BAK';
  if v_n1 = 0 then
    execute immediate Q'{create table SPF_T_LISTCONDISET_BAK(guid VARCHAR2(32) default SYS_GUID() not null,
     MODECODE  VARCHAR2(200),MODELID  VARCHAR2(32),FACTOR VARCHAR2(50),ORDERID NUMBER(9),TABLEID VARCHAR(32))}';
    execute immediate 'alter table SPF_T_LISTCONDISET_BAK add constraint SPF_T_LISTCONDISET_BAK  primary key (guid)';
  end if;
end;
--¥¥Ω®±ÌSPF_T_LISTCONDISET

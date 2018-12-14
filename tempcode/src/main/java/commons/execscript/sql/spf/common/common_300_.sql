declare
  v_n1 number(8) := 0;
BEGIN
  select count(1)
    into v_n1
    from user_tables
   where upper(table_name) = 'P#SPF_T_CONDISET';
  if v_n1 = 0 then
    execute immediate Q'{create table SPF_T_CONDISET(guid VARCHAR2(32) default SYS_GUID() not null,
     CONDITION  CLOB,SENTENCES CLOB,TABLEID VARCHAR(32),WHERETYPE VARCHAR(2),NOTE varchar2(1000))}';
    execute immediate 'alter table SPF_T_CONDISET add constraint SPF_T_CONDISET  primary key (guid)';
     SYS_P_PARTITION_TABLE('SPF_T_CONDISET');
  end if;
  select count(1)
    into v_n1
    from user_tables
   where upper(table_name) = 'SPF_T_CONDISET_BAK';
  if v_n1 = 0 then
    execute immediate Q'{create table SPF_T_CONDISET_BAK(guid VARCHAR2(32) default SYS_GUID() not null,
     CONDITION  CLOB,SENTENCES CLOB,TABLEID VARCHAR(32),WHERETYPE VARCHAR(2),NOTE varchar2(1000))}';
    execute immediate 'alter table SPF_T_CONDISET_BAK add constraint SPF_T_CONDISET_BAK  primary key (guid)';
  end if;
end;
--¥¥Ω®±ÌSPF_T_CONDISET

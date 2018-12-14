Declare
  v_n1 number(8) := 0;
begin
  select count(1)
    into v_n1
    from user_views
   where upper(view_name) = 'CODE_T_PERSONSTATUS';
  if v_n1 = 0 then
    execute immediate Q'{create table CODE_T_PERSONSTATUS(guid VARCHAR2(32) default SYS_GUID() not null, code VARCHAR2(7) not null, name VARCHAR2(120) not null,superguid  VARCHAR2(32),isleaf NUMBER(1), levelno NUMBER(1))}';
    SYS_P_PARTITION_TABLE('CODE_T_PERSONSTATUS');
    
  end if;
end;
--CREATE_TABLE_CODE_T_PERSONSTATUS

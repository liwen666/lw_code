Declare
  v_n1 number(8) := 0;
begin
  select count(1)
    into v_n1
    from user_views
   where upper(view_name) = 'CODE_T_PERSONCONFIRM';
  if v_n1 = 0 then
    execute immediate Q'{create table CODE_T_PERSONCONFIRM(guid VARCHAR2(32) default SYS_GUID() not null, code VARCHAR2(7) not null, name VARCHAR2(120) not null,superguid  VARCHAR2(32),isleaf NUMBER(1), levelno NUMBER(1))}';
    execute immediate 'alter table CODE_T_PERSONCONFIRM add constraint CODE_T_PERSONCONFIRM_PK  primary key (GUID)';
    SYS_P_PARTITION_TABLE('CODE_T_PERSONCONFIRM');
    EXECUTE IMMEDIATE Q'{create or replace view code_t_personconfirm as
            select CODE,DBVERSION,GUID,ISLEAF,LEVELNO,NAME,STATUS,SUPERGUID from P#CODE_T_PERSONCONFIRM where STATUS='1'}';
  end if;
end;--1-CODE_T_PERSONCONFIRM_CREATE

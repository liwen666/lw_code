      declare
  v_n1 number(8) := 0;
BEGIN
DELETE FROM dict_t_settingtabinfo WHERE GUID ='2C7B42071382A5B1E050A8C02105133';
insert into dict_t_settingtabinfo (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID,  STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
values ('2C7B42071382A5B1E050A8C02105133', 'P#SPF_T_FUNDTPTOSOUR', '资金性质对来源记录表', null, null, '*', '1', '1', '0', '1', '0', '0', null);
COMMIT;
  select count(1)
    into v_n1
    from user_tables
   where upper(table_name) = 'P#SPF_T_FUNDTPTOSOUR';
  if v_n1 = 0 then
    execute immediate Q'{create table SPF_T_FUNDTPTOSOUR(guid VARCHAR2(32) default SYS_GUID() not null,
     FUNDTYPEID  VARCHAR2(32),FUNDSOURCENAME VARCHAR2(200))}';
    execute immediate 'alter table SPF_T_FUNDTPTOSOUR add constraint SPF_T_FUNDTPTOSOUR  primary key (guid)';
    SYS_P_PARTITION_TABLE('SPF_T_FUNDTPTOSOUR');
  end if;
end;
--资金性质对来源记录表

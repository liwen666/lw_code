declare
  v_n1 number(8) := 0;
begin
  select count(1)
    into v_n1
    from user_views
   where upper(view_name) = 'BGT_T_PERSONMOVE';
  if v_n1 = 0 then
    execute immediate Q'{create table BGT_T_PERSONMOVE(ID VARCHAR2(32) default SYS_GUID() not null,GUID VARCHAR2(32) default SYS_GUID() not null,
   INPERSONTABLEID VARCHAR2(32) not null,OUTPERSONTABLEID VARCHAR2(32) NOT NULL,MOVEPERSONSTATUSID VARCHAR2(32) NOT NULL)}';
    execute immediate 'alter table BGT_T_PERSONMOVE add constraint BGT_T_PERSONMOVE  primary key (GUID,ID,INPERSONTABLEID)';
    SYS_P_PARTITION_TABLE('BGT_T_PERSONMOVE');
  end if;
end;
--creat_t_personmove

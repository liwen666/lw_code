declare
  v_n1 number(8) := 0;
begin
  select count(1)
    into v_n1
    from user_views
   where upper(view_name) = 'BGT_T_SETHIDDENCOLUMN';
  if v_n1 = 0 then
    execute immediate Q'{create table BGT_T_SETHIDDENCOLUMN(guid VARCHAR2(32) default SYS_GUID() not null,
    TABLEID VARCHAR2(32) not null,COLUMNID  VARCHAR2(32) not null,USERID VARCHAR(32) NOT NULL,
     COLUMNDBNAME VARCHAR(100),COLUMNNAME VARCHAR(100))}';
    execute immediate 'alter table BGT_T_SETHIDDENCOLUMN add constraint BGT_T_SETHIDDENCOLUMN  primary key (guid,TABLEID,COLUMNID,USERID)';
    SYS_P_PARTITION_TABLE('BGT_T_SETHIDDENCOLUMN');
  
  end if;
end;
--新建存储列隐藏设置表160407郑桥

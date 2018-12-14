declare
  v_n1 number(8) := 0;
  v_n2 number(8) := 0;
begin
  select count(1)
    into v_n1
    from user_views
   where upper(view_name) = 'BGT_T_CHECKRESULT';
  if v_n1 = 1 then
    select count(1)
      into v_n1
      from user_tab_columns
     where upper(column_name) = 'USERUPAGENCYID' and upper(TABLE_NAME)='P#BGT_T_CHECKRESULT';
    if v_n1 = 0 then
      execute immediate 'drop view BGT_T_CHECKRESULT ';
      execute immediate 'drop table P#BGT_T_CHECKRESULT';
      v_n2 := 1;
    end if;
  
  else
    v_n2 := 1;
  end if;

  if v_n2 = 1 then
  
    execute immediate Q'{create table BGT_T_CHECKRESULT
      (
      guid           VARCHAR2(32) default SYS_GUID() not null,
      agencyid       VARCHAR2(32),
      taskid         VARCHAR2(32),
      ckdate         TIMESTAMP(6) default SYSDATE,
      ckflag         CHAR(1),
      busicheckid    VARCHAR2(32) not null,
      userguid       VARCHAR2(32),
      userupagencyid VARCHAR2(32),
      userdistrictid VARCHAR2(32)
      )}';
  
    execute immediate 'alter table BGT_T_CHECKRESULT add constraint BGT_T_CHECKRESULT_PKY  primary key (guid)';
    SYS_P_PARTITION_TABLE('BGT_T_CHECKRESULT');
  
  end if;

end;
--王晓宁_创建表bgt_t_checkResult

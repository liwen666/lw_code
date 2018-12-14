declare
  v_n1 number(8) := 0;
begin
  select count(1)
    into v_n1
    from user_views
   where upper(view_name) = 'BGT_T_CUSTOMSORT';
  if v_n1 = 0 then
    execute immediate Q'{create table BGT_T_CUSTOMSORT(ID VARCHAR2(32) default SYS_GUID() not null,GUID VARCHAR2(32) default SYS_GUID() not null,
    TABLEID VARCHAR2(32) not null,USERID VARCHAR2(32) NOT NULL,ORDERNUM NUMBER(9) default 0,
     ASCFLAG CHAR(1),COL VARCHAR2(30),ISRESERVE CHAR(1))}';
    execute immediate 'alter table BGT_T_CUSTOMSORT add constraint PK_BGT_T_CUSTOMSORT  primary key (GUID,ID,TABLEID,COL,USERID)';
    SYS_P_PARTITION_TABLE('BGT_T_CUSTOMSORT');
  end if;
end;
--创建自定义排序表20161011zq

declare
v_n1 number(8) :=0;
v_n2 number(16) :=0;
v_n3 number(8) :=0;
begin
  select count(1) into v_n1 from user_tables where upper(table_name)='P#BGT_T_BUSINESSCHECKDEF';
  select count(1) into v_n2 from BGT_T_BUSINESSCHECKDEF;
  select count(1) into v_n3 from user_tables where upper(table_name)='BGT_T_BUSINESSCHECKDEF_BAK';
 
  if v_n1=0 then 
  
       if v_n2>0 then 
         --有数据
          if v_n3>0 then 
            execute immediate 'drop table BGT_T_BUSINESSCHECKDEF_BAK';
           end if;
         
         execute immediate 'create table BGT_T_BUSINESSCHECKDEF_BAK as select * from BGT_T_BUSINESSCHECKDEF';
         execute immediate 'drop table BGT_T_BUSINESSCHECKDEF';
         execute immediate 'create table BGT_T_BUSINESSCHECKDEF as select * from BGT_T_BUSINESSCHECKDEF_BAK where rownum<1';
         execute immediate 'alter table BGT_T_BUSINESSCHECKDEF add constraint BUSINESSCHECKDEF_PKY  primary key (guid)';
	 
       end if;
    
       SYS_P_PARTITION_TABLE('BGT_T_BUSINESSCHECKDEF'); 
       execute immediate 'alter table P#BGT_T_BUSINESSCHECKDEF modify status default 1';
       
        if v_n2> 0 then 
          --恢复数据
          execute immediate 'insert into BGT_T_BUSINESSCHECKDEF(CHECKID, BUSINESSTYPE, ERRORTYPE, ISUSE, ISMIDCHECK, ISADDCHECK, ISSAVECHECK, CHECKTYPE, ISDIRECT, LDIRECTCOL, RDIRECTCOL, BUDGETLEVEL, NAME, STATUS, GUID, ORDERID, EXPCATEGORY)(select CHECKID, BUSINESSTYPE, ERRORTYPE, ISUSE, ISMIDCHECK, ISADDCHECK, ISSAVECHECK, CHECKTYPE, ISDIRECT, LDIRECTCOL, RDIRECTCOL, BUDGETLEVEL, NAME, STATUS, GUID, ORDERID, EXPCATEGORY from BGT_T_BUSINESSCHECKDEF_BAK )';
          execute immediate 'drop table BGT_T_BUSINESSCHECKDEF_BAK';
         end if;
     
  end if;
   
  
  
 end;
--王晓宁_刷审核定义表分区

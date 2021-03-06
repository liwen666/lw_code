declare
--添加createBgtLevel字段  
v_n number(8);
begin
  
--审核定义加字段  
select count(0) into v_n from  user_tab_columns tab where tab.TABLE_NAME='P#BGT_T_CHECKDEF' and tab.COLUMN_NAME='CREATEBGTLEVEL';
if v_n=0 then 
  execute immediate 'alter table P#BGT_T_CHECKDEF add  CREATEBGTLEVEL number(8) default 1';
  execute immediate Q'{ CREATE OR REPLACE VIEW BGT_T_CHECKDEF AS
SELECT APPID,CREATEBGTLEVEL,BUDGETLEVEL,CHECKEXCELSQL,CHECKID,CHECKSORTID,CHECKSQL,CHECKTYPE,DBVERSION,DEFNAME,ERRORDEF,LCOMPCOL,LDESC,LEFTGROUP,LMODELID,LQUERY,PUBGROUP,RCOMPCOL,RDESC,RELATYPE,RIGHTGROUP,RMODELID,RQUERY,SERID,SHOWTEXT,USERDISTRICTID,STATUS
 FROM P#BGT_T_CHECKDEF WHERE PROVINCE=GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID') AND YEAR=GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR') AND STATUS='1'
}';
end if;

--业务审核定义加字段  

select count(0) into v_n from  user_tab_columns tab where tab.TABLE_NAME='P#BGT_T_BUSINESSCHECKDEF' and tab.COLUMN_NAME='CREATEBGTLEVEL';
if v_n=0 then 
   execute immediate 'alter table P#BGT_T_BUSINESSCHECKDEF add  CREATEBGTLEVEL number(8) default 1';
   execute immediate Q'{create or replace view bgt_t_businesscheckdef as
select CREATEBGTLEVEL,BUDGETLEVEL,BUSINESSTYPE,CHECKID,CHECKTYPE,DBVERSION,ERRORTYPE,EXPCATEGORY,GUID,ISADDCHECK,ISDIRECT,ISMIDCHECK,ISSAVECHECK,ISUSE,LDIRECTCOL,NAME,ORDERID,RDIRECTCOL,STATUS from P#BGT_T_BUSINESSCHECKDEF where PROVINCE=Global_MultYear_CZ.Secu_f_GLOBAL_PARM('DIVID') and Year=Global_MultYear_CZ.Secu_f_GLOBAL_PARM('YEAR') AND STATUS='1'}';
end if;
end;
--审核定义表添加createbgtlevel字段

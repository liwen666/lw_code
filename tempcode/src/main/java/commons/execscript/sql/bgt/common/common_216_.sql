declare
--BGT_T_BUSINESSCHECKDEF 添加auditRange字段
v_n number(8):=0;

begin


--添加AUDITRANGE 审核范围字段
select count(0) into v_n from  user_tab_columns tab where tab.TABLE_NAME='P#BGT_T_BUSINESSCHECKDEF' and tab.COLUMN_NAME='AUDITRANGE';
if v_n=0 then 
   execute immediate 'ALTER TABLE P#BGT_T_BUSINESSCHECKDEF add  AUDITRANGE CHAR(1)';
End if;

--刷新视图

if v_n=0 then 
--创建表结构
execute immediate Q'{CREATE OR REPLACE VIEW BGT_T_BUSINESSCHECKDEF AS
SELECT AUDITRANGE,AGENCYIDPASS,FLOWDIRECTION,CREATEBGTLEVEL,BUDGETLEVEL,BUSINESSTYPE,CHECKID,CHECKTYPE,DBVERSION,ERRORTYPE,EXPCATEGORY,GUID,ISADDCHECK,ISDIRECT,ISMIDCHECK,ISSAVECHECK,ISUSE,LDIRECTCOL,NAME,ORDERID,RDIRECTCOL,STATUS from P#BGT_T_BUSINESSCHECKDEF where PROVINCE=Global_MultYear_CZ.Secu_f_GLOBAL_PARM('DIVID') and Year=Global_MultYear_CZ.Secu_f_GLOBAL_PARM('YEAR') AND STATUS='1'}';
--初始化默认值
execute immediate Q'{Update  p#bgt_t_businesscheckdef Set auditrange='1' Where status='1' And substr(flowdirection,2,1)='1'}';

End if;

end;
--17_businesscheckdef增加auditrange字段

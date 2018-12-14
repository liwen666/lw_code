declare
v_n number(8) :=0;
begin 
SELECT count(0) into v_n FROM user_tab_columns WHERE upper(TABLE_NAME)='P#BGT_T_CHECKERROR' and upper(column_name)='LDBTABLENAME'; 
if v_n=0 then

execute immediate Q'{alter table P#BGT_T_CHECKERROR add LDBTABLENAME varchar(30)}';
execute immediate Q'{alter table P#BGT_T_CHECKERROR add RDBTABLENAME varchar(30)}';
execute immediate Q'{create or replace view bgt_t_checkerror as
select GUID,RIGHTVALUE,LEFTVALUE,DEVIATION,CHECKDATA,CHECKRESULTID,ISLEAFAGENCY,CKRESULT,LDBTABLENAME,RDBTABLENAME,DBVERSION,STATUS from P#BGT_T_CHECKERROR where PROVINCE=Global_MultYear_CZ.Secu_f_GLOBAL_PARM('DIVID') and Year=Global_MultYear_CZ.Secu_f_GLOBAL_PARM('YEAR') AND STATUS='1'}';
end if;
end;
--¸øcheckErrorÌí¼ÓLRdbableName

begin
  --项目库数据审核定义添加roleid列
  execute immediate Q'{alter table p#spf_t_businesscheckdef add(roleid varchar2(32))}';
  execute immediate Q'{CREATE OR REPLACE VIEW SPF_T_BUSINESSCHECKDEF AS
  SELECT CHECKID,
         BUSINESSTYPE,
         ERRORTYPE,
         ISUSE,
         ISMIDCHECK,
         ISADDCHECK,
         ISSAVECHECK,
         CHECKTYPE,
         ISDIRECT,
         LDIRECTCOL,
         RDIRECTCOL,
         BUDGETLEVEL,
         NAME,
         STATUS,
         GUID,
         ORDERID,
         OBJECTID,
         PROCESSID,
         TYPEFLAG,
         DBVERSION,
         ROLEID
    FROM P#SPF_T_BUSINESSCHECKDEF
   WHERE PROVINCE = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID')
     AND YEAR = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR')
     AND STATUS = '1'}';
  end;

--FMH_项目库审核定义添加roleid列

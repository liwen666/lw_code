declare
  n_count number;
BEGIN                                                                   
  select count(1) into n_count from user_tab_columns   where table_name = 'KPI_T_SETINPUTTAB'  and column_name = 'ISOPENED' ;
  if n_count > 0 then
    EXECUTE IMMEDIATE 'alter table p#KPI_T_SETINPUTTAB drop COLUMN ISOPENED';
  end if;
  EXECUTE IMMEDIATE Q'{alter table p#KPI_T_SETINPUTTAB add ISOPENED varchar(2) default '1'}' ;
   EXECUTE IMMEDIATE Q'{ CREATE OR REPLACE VIEW KPI_T_SETINPUTTAB AS
SELECT STATUS,DATAKEY,NEEDUPDATE,ORDERID,FINYEAR,GUID,OBJECTID,TABLEID,PROCESSID,KPITYPE,ISREADONLY,ISMAINTABLE,REMARK,DBVERSION
, ISOPENED 
  FROM P#KPI_T_SETINPUTTAB
 WHERE PROVINCE=GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID')
   AND YEAR=GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR')
   AND STATUS='1'}';
END;
--��Ч_WTF_20160708_��Ч¼��������ֶ��Ƿ�չ��
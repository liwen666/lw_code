BEGIN
  update dict_t_settingtabinfo c set c.YEARPART='1',c.PROVINCEPART='1',c.INITIALIZE='0'
  WHERE GUID='1C0DEACBED854968B641C772CAB12354' AND DBTABLENAME='P#SPF_T_INBUDGETTEMP';
  EXECUTE IMMEDIATE Q'/
  CREATE OR REPLACE VIEW SPF_T_INBUDGETTEMP AS
select DATAKEY,
       DBVERSION,
       INBUDGET_DATAKEY,
       INVEST_DATAKEY,
       ISSELECT,
       STATUS,
       ZCMX_DATAKEY
  from P#SPF_T_INBUDGETTEMP
 where PROVINCE = Global_MultYear_CZ.Secu_f_GLOBAL_PARM('DIVID')
   and Year = Global_MultYear_CZ.Secu_f_GLOBAL_PARM('YEAR')
   AND STATUS = '1'
  /'; 
  
END;
--SPF_T_INBUDGETTEMP加非全年度访问

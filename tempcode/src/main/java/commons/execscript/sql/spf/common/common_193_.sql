DECLARE
  a integer;
BEGIN
  SELECT COUNT(*) INTO a FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'P#KPI_T_SETKPICALIBER' AND COLUMN_NAME = 'CSCOLUMNID';
  IF a = 0 THEN
    EXECUTE IMMEDIATE Q'{ alter table P#KPI_T_SETKPICALIBER add CSCOLUMNID VARCHAR2(32)}';
  END IF;

  --重建绩效口径设置表视图
 EXECUTE IMMEDIATE Q'{ CREATE OR REPLACE VIEW KPI_T_SETKPICALIBER AS
SELECT STATUS,DATAKEY,NEEDUPDATE,ORDERID,FINYEAR,GUID,KPITYPENAME,KPITYPE,TABLEID,REMARK,DBVERSION,CSCOLUMNID
  FROM P#KPI_T_SETKPICALIBER
 WHERE PROVINCE=GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID')
   AND YEAR=GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR')
   AND STATUS='1'}';

--删除部门绩效指标
 EXECUTE IMMEDIATE Q'{ DELETE FROM KPI_T_SETKPICALIBER where guid ='07B5497B0ABFBC39E050A8C0210553A3'}';
--添加部门绩效指标数据
 EXECUTE IMMEDIATE Q'{insert into kpi_t_setkpicaliber (STATUS, DATAKEY, NEEDUPDATE, ORDERID, FINYEAR, GUID, KPITYPENAME, KPITYPE, TABLEID, REMARK, CSCOLUMNID)
values ('1', '0F141DD3D7914E2383F6551F91F5E0A3', null, 7, '2015', '07B5497B0ABFBC39E050A8C0210553A3', '部门绩效指标', 'A3', '509B11FEDEFD465289BCD9538F66F7E3', '不要更改绩效口径',  null)}';
--删除财政绩效指标
EXECUTE IMMEDIATE Q'{  DELETE FROM KPI_T_SETKPICALIBER where guid ='07B5497B0ABFBC39E050A8C0210553A4'}';
--添加部门绩效指标数据
 EXECUTE IMMEDIATE Q'{insert into kpi_t_setkpicaliber (STATUS, DATAKEY, NEEDUPDATE, ORDERID, FINYEAR, GUID, KPITYPENAME, KPITYPE, TABLEID, REMARK, CSCOLUMNID)
values ('1', '0F141DD3D7914E2383F6551F91F5E0A4', null, 8, '2015', '07B5497B0ABFBC39E050A8C0210553A4', '财政绩效指标', 'A4', '33AAEFE9F21242E2E050A8C0F0002853', '不要更改绩效口径',  null)}';
END;
--绩效_20160621_YLL绩效口径设置表添加字段

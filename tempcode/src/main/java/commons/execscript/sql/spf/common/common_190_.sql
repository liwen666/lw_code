declare
  n_count number;
BEGIN  
  select count(1) into n_count from user_objects o where o.object_type='VIEW' AND OBJECT_NAME='KPI_T_COLTOTABLES';
  if n_count>0 then
    EXECUTE IMMEDIATE 'drop view kpi_t_coltotables';
  end if;
 select count(1) into n_count from user_objects o where o.object_type='TABLE' AND OBJECT_NAME='KPI_T_COLTOTABLES';
  if n_count>0 then
    EXECUTE IMMEDIATE 'drop TABLE kpi_t_coltotables';
  end if;  

  select count(1) into n_count from user_objects o where o.object_type='TABLE' AND OBJECT_NAME='P#KPI_T_COLTOTABLES';
  if n_count>0 then
    EXECUTE IMMEDIATE 'drop TABLE p#kpi_t_coltotables';
  end if;  
  
  EXECUTE IMMEDIATE Q'{create table kpi_t_coltotables
  (
   province     VARCHAR2(9) not null,
  year         CHAR(4) not null,
  APPID        VARCHAR2(20),
  status       CHAR(1) default '1' not null,
  dbversion    TIMESTAMP(6) default SYSDATE,
  needupdate   VARCHAR2(4000),
  orderid      NUMBER(9),
  finyear      VARCHAR2(4) default TO_CHAR(SYSDATE,'YYYY'),
  guid         VARCHAR2(32) default SYS_GUID(),
  columnid     VARCHAR2(32),
  tableid      VARCHAR2(32),
  PROJTABID VARCHAR2(32),
  colvaluename VARCHAR2(200),
  colvalueid   VARCHAR2(200),
  isshow       CHAR(1) default 1,
  isread       CHAR(1) default 0
  )}';
    
  SYS_P_PARTITION_TABLE('kpi_t_coltotables');

END;

--WTF_20160616_主表对应附表设置表

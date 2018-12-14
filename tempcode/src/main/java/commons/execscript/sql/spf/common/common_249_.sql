declare 
  v_count integer;
  begin
  SELECT COUNT(1) INTO v_count FROM USER_TABLES WHERE TABLE_NAME='P#KPI_T_PROCESSMENU';
  IF v_count=0 THEN 
    EXECUTE IMMEDIATE Q'{create table P#KPI_T_PROCESSMENU
( menuguid   VARCHAR2(32) not null,
 processid    VARCHAR2(32) not null,
 parentid    VARCHAR2(32) not null,
  status     CHAR(1) default '1' not null,
  menutype   VARCHAR2(32))}';
    EXECUTE IMMEDIATE 'alter table P#KPI_T_PROCESSMENU add constraint PK_KPI_T_PROCESSMENU primary key (STATUS, MENUGUID)';
    sys_p_partition_table('P#KPI_T_PROCESSMENU');
    EXECUTE IMMEDIATE Q'{
 create or replace view kpi_t_processmenu as
select MENUGUID,MENUTYPE,PARENTID ,STATUS,PROCESSID from P#KPI_T_PROCESSMENU where  STATUS='1'}';
END IF;
END;
--WUTF_20160815绩效阶段中间表

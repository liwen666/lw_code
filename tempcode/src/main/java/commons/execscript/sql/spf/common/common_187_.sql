declare
v_tn number;
begin
  --财政绩效指标表
  select count(1) into v_tn from user_tables a where a.TABLE_NAME='P#KPI_T_SETFNCKPI';
  if v_tn=0 then
  execute immediate Q'{create table P#KPI_T_SETFNCKPI
    (
  province      VARCHAR2(9) not null,
  year          CHAR(4) not null,
  status        CHAR(1) default '1' not null,
  dbversion     TIMESTAMP(6) default SYSDATE,
  datakey       VARCHAR2(32) default SYS_GUID() not null,
  needupdate    VARCHAR2(4000),
  orderid       NUMBER(9),
  finyear       VARCHAR2(4) default TO_CHAR(SYSDATE,'YYYY'),
  guid          VARCHAR2(32) default SYS_GUID(),
  superguid     VARCHAR2(32),
  kpiname       VARCHAR2(200),
  levelno       NUMBER(1) default 0,
  isleaf        CHAR(1),
  score         NUMBER(9) default 0,
  requiredchild CHAR(1),
  explanation   VARCHAR2(500),
  standard      VARCHAR2(500),
  isinsert      CHAR(1) default 0,
  kpiprop       CHAR(1) default '1'
    )}';
execute immediate Q'{alter table P#KPI_T_SETFNCKPI add constraint PK_P#KPI_T_SETFNCKPI primary key (PROVINCE, YEAR, STATUS, DATAKEY)}';

  sys_p_partition_table('P#KPI_T_SETFNCKPI');
  end if;

end;--绩效_20160621_3_YLL创建财政绩效指标表

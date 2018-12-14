declare
v_tn number(1);

begin 
  select count(*) INTO v_tn from user_tables t where  t.table_name = 'P#KPI_T_SETDEPKPI';
  if v_tn > 0
   then execute immediate Q'{DROP TABLE P#KPI_T_SETDEPKPI}' ;
  end if;
 
  --创建部门绩效指标表
  execute immediate Q'{
  create table P#KPI_T_SETDEPKPI
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
   )
   }';
execute immediate Q'{alter table P#KPI_T_SETDEPKPI add constraint PK_P#KPI_T_SETDEPKPI primary key (PROVINCE, YEAR, STATUS, DATAKEY)}';

 --刷P#KPI_T_SETDEPKPI表触发器
  
  SYS_P_PARTITION_TABLE('P#KPI_T_SETDEPKPI') ;

end;--绩效_20160621_1_YLL创建部门绩效指标表

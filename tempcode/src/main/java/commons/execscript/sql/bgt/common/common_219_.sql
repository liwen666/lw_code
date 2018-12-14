DECLARE
  N      INTEGER;
  A      INTEGER;
  SQLSTR VARCHAR2(2000);
BEGIN
  SELECT COUNT(1)
    INTO N
    FROM USER_TABLES T
   WHERE T.TABLE_NAME = 'BGT_T_CHART';
  IF N = 1 THEN
    EXECUTE IMMEDIATE 'drop table BGT_T_CHART';
  END IF;
  SELECT COUNT(1)
    INTO N
    FROM USER_TABLES T
   WHERE T.TABLE_NAME = 'P#BGT_T_CHART';
   IF N = 0 THEN
     SQLSTR := 'create table BGT_T_CHART
            (
              guid       VARCHAR2(32) default sys_guid() not null,
              appid      VARCHAR2(3),
              type       VARCHAR2(20),
              sql        clob,
              name       VARCHAR2(100),
              text       VARCHAR2(100),
              subtext    VARCHAR2(100),
              tooltip_name VARCHAR2(100),
              saveAsImage_is_show char(1)
            )';
      EXECUTE IMMEDIATE SQLSTR;
      execute immediate 'alter table BGT_T_CHART add constraint PK_BGT_T_CHART  primary key (guid)';
      SYS_P_PARTITION_TABLE('BGT_T_CHART');
   END IF;  
END;

--图表登记表添加分区

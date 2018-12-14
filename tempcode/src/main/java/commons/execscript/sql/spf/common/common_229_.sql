DECLARE
  ISEXISTS INTEGER;
BEGIN
  SELECT COUNT(1)
    INTO ISEXISTS
    FROM USER_TABLES
   WHERE TABLE_NAME = 'SPF_T_OARELATIONCOPY';
  IF ISEXISTS = 0 THEN
    EXECUTE IMMEDIATE Q'{create table SPF_T_OARELATIONCOPY
(
  docid     VARCHAR2(32),
  taskid    VARCHAR2(32),
  userid    VARCHAR2(32),
  orgid     VARCHAR2(32),
  remark    VARCHAR2(100),
  guid      VARCHAR2(32) default SYS_GUID() not null,
  dbversion TIMESTAMP(6) default SYSDATE,
  status    CHAR(1) default 1 not null,
  tasktype  CHAR(1) default '0',
  isgoback  CHAR(1) default '1'
)
}';
  END IF;

  SELECT COUNT(1)
    INTO ISEXISTS
    FROM USER_TABLES
   WHERE TABLE_NAME = 'SPF_T_OARUNPATH';
  IF ISEXISTS = 0 THEN
    EXECUTE IMMEDIATE Q'{
create table SPF_T_OARUNPATH
(
  docid     VARCHAR2(32),
  taskid    VARCHAR2(32),
  userid    VARCHAR2(32),
  orgid     VARCHAR2(32),
  remark    VARCHAR2(100),
  guid      VARCHAR2(32) default SYS_GUID() not null,
  dbversion TIMESTAMP(6) default SYSDATE,
  status    CHAR(1) default 1 not null,
  tasktype  CHAR(1) default '0',
  sendtime  TIMESTAMP(6) default SYSDATE,
  targetid  VARCHAR2(32)
)
}';
  END IF;
END;
--新增回退选择表、轨迹日志表
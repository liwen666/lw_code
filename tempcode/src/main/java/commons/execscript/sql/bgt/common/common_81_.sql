declare 
  v_count integer;
begin
  SELECT COUNT(1) INTO v_count FROM DICT_T_DEALTYPE WHERE DEALID='BIL01';
  IF v_count=0 THEN
    insert into dict_t_dealtype(appid,dealid,dealname,orderid,needconfig)values ('*','BIL01','单据主表','101','0');
    insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID)
     values ('BIL01', 1, '单据ID', 'BILLID', 3, 32, null, null, '1', '1', '25BBAC45ED9BC624E050A8C02105645A', '1', SYSDATE,  'SYS_GUID()', null, '1500C1njnCmF');
    insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID)
     values ('BIL01', 2, '单据编号', 'BILLNO', 3, 32, null, null, '0', '1', '25BBAC45ED9CC624E050A8C02105645A', '1', SYSDATE, null, null, null);
    insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID)
     values ('BIL01', 3, '制单人', 'USERID', 3, 32, null, null, '0', '0', '25BBAC45ED9DC624E050A8C02105645A', '1', SYSDATE, null, null, null);
    insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID)
     values ('BIL01', 3, '单据类型ID', 'BILLTYPEID', 3, 32, null, null, '0', '1', '25AACC45ED9DC624E050A8C02105645B', '1', SYSDATE,  null, null, null);
    insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID)
     values ('BIL01', 4, '行标识', 'DATAKEY', 3, 32, null, null, '1', '1', '25BBAC45ED9FC624E050A8C02105645A', '1', SYSDATE,  'SYS_GUID()', null, null);
    insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID)
     values ('BIL01', 5, '排序序号', 'ORDERID', 3, 32, null, null, '0', '1', '25BBAC45EDA0C624E050A8C02105645A', '1', SYSDATE, '0', null, null);
    insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID)
     values ('BIL01', 6, '单位编码(BILLID)', 'AGENCYID', 3, 32, null, null, '0', '1', '25123445ED9BC624E050A8C02105645A', '1', SYSDATE, null, '0', null);
    insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID)
     values ('BIL01', 7, '更新标识', 'NEEDUPDATE', 3, 4000, null, null, '0', '1', '25BBAC45567BC624E050A8C02105645A', '1', SYSDATE, null, '0', null);
  END IF;
  
   SELECT COUNT(1) INTO v_count FROM fasp_T_pubmenu WHERE NAME='新单据BIL';
  IF v_count=1 THEN
    DELETE FROM FASP_T_PUBMENU WHERE GUID='14DDD0CFC65689ECE050A8C0210563FC';
  END IF;
  
  SELECT COUNT(1) INTO v_count FROM fasp_T_pubmenu WHERE NAME='单据设置';
  IF v_count=0 THEN
    insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
    values ('14DDD0CFC65689ECE050A8C0210563FC', 1, 0, '1', '005009002', '单据设置', '100001FF', '#', 10000, null, null, 2, null, null, 'bgt', null, null, null, null, null, null, 1);
  END IF;

  SELECT COUNT(1) INTO v_count FROM fasp_T_pubmenu WHERE NAME='单据编码设置';
  IF v_count=0 THEN
    insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
    values ('14DDD0CFC65689ECE050A8C0210563FE', 2, 1, '1', '005009002003', '单据编码设置', '14DDD0CFC65689ECE050A8C0210563FC', '/bgt/bil/setting/codesetting.do', 1, null, null, 2, null, null, 'bgt', null, null, null, null, null, null, 1);
  END IF;
  
  SELECT COUNT(1) INTO v_count FROM fasp_T_pubmenu WHERE NAME='单据类型设置';
  IF v_count=0 THEN
    insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
    values ('14DDD0CFC65689ECE050A8C0210563FD', 2, 1, '1', '005009002001', '单据类型设置', '14DDD0CFC65689ECE050A8C0210563FC', '/bgt/bil/setting/billType.do', 1, null, null, 2, null, null, 'bgt', null, null, null, null, null, null, 1);
  END IF;
  
  SELECT COUNT(1) INTO v_count FROM fasp_T_pubmenu WHERE NAME='单据主子表设置';
  IF v_count=0 THEN
    insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
    values ('14DDD0CFC65889ECE050A8C0210563FF', 2, 1, '1', '005009002002', '单据主子表设置', '14DDD0CFC65689ECE050A8C0210563FC', '/bgt/bil/relationset.do', 1, null, null, 2, null, null, 'bgt', null, null, null, null, null, null, 1);
  END IF;
  
  SELECT COUNT(1) INTO v_count FROM fasp_T_pubmenu WHERE NAME='单据录入入口';
  IF v_count=0 THEN
   insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
   values ('14DDD0CFC65610ECE050A8C0210563FG', 1, 0, '1', '005009001', '单据录入入口', '100001FF', '#', 10000, null, null, 2, null, null, 'bgt', null, null, null, null, null, null, 1);
  END IF;
  
  SELECT COUNT(1) INTO v_count FROM USER_TABLES WHERE TABLE_NAME='P#BIL_T_BILLTYPE';
  IF v_count=0 THEN
    EXECUTE IMMEDIATE Q'{create table P#BIL_T_BILLTYPE
( billtypeid   VARCHAR2(32) not null,
  billtypename VARCHAR2(32),
  orderid      NUMBER(10) default 0,
  superid      VARCHAR2(32),
  dbversion    TIMESTAMP(6) default SYSDATE not null,
  status       CHAR(1) default '1' not null,
  isleaf       VARCHAR2(32),
  code         VARCHAR2(200),
  levelno      NUMBER(1))}'; 
    EXECUTE IMMEDIATE Q'{alter table P#BIL_T_BILLTYPE add constraint PK_BIL_T_BILLTYPE primary key (BILLTYPEID,STATUS)}';
    sys_p_partition_table('P#BIL_T_BILLTYPE');
  END IF;
  
  SELECT COUNT(1) INTO v_count FROM USER_TABLES WHERE TABLE_NAME='P#BIL_T_SETCODE';
  IF v_count=0 THEN 
    EXECUTE IMMEDIATE Q'{create table P#BIL_T_SETCODE
( codeid     VARCHAR2(32) not null,
  typeid     NUMBER default 0 not null,
  orderid    NUMBER(9) default 0,
  basetypeid VARCHAR2(32) not null,
  codeshow   VARCHAR2(200),
  dbversion  TIMESTAMP(6) default SYSDATE,
  bgtlvl     CHAR(1),
  status     CHAR(1) default 1 not null,
  billtypeid VARCHAR2(32))}';
  	EXECUTE IMMEDIATE 'alter table P#BIL_T_SETCODE add constraint PK_BIL_T_SETCODE primary key (STATUS, TYPEID, BASETYPEID, CODEID)';
    sys_p_partition_table('P#BIL_T_SETCODE');
  END IF;
  
  SELECT COUNT(1) INTO v_count FROM USER_TABLES WHERE TABLE_NAME='P#BIL_T_SETCODE_B';
  IF v_count=0 THEN 
    EXECUTE IMMEDIATE Q'{create table P#BIL_T_SETCODE_B
( basetypeid   VARCHAR2(32) not null,
  basetypename VARCHAR2(100),
  isspestr     CHAR(1),
  isuse        CHAR(1),
  orderid      NUMBER(9) default 0,
  codeshow     VARCHAR2(200),
  dbversion    TIMESTAMP(6) default SYSDATE,
  bgtlvl       CHAR(1),
  status       CHAR(1) default 1 not null,
  typeid       NUMBER(9) default 0)}';
  	EXECUTE IMMEDIATE 'alter table P#BIL_T_SETCODE_B add constraint PK_BIL_T_SETCODE_B primary key (STATUS, BASETYPEID)';
    sys_p_partition_table('P#BIL_T_SETCODE_B');
  END IF;
  
  SELECT COUNT(1) INTO v_count FROM USER_TABLES WHERE TABLE_NAME='P#BIL_T_BUSINESSCHECKDEF';
  IF v_count=0 THEN 
    EXECUTE IMMEDIATE Q'{create table P#BIL_T_BUSINESSCHECKDEF
( checkid      VARCHAR2(32),
  businesstype VARCHAR2(100),
  errortype    CHAR(1),
  isuse        CHAR(1),
  ismidcheck   CHAR(1),
  isaddcheck   CHAR(1),
  issavecheck  CHAR(1),
  checktype    VARCHAR2(10),
  isdirect     CHAR(1),
  ldirectcol   VARCHAR2(200),
  rdirectcol   VARCHAR2(200),
  budgetlevel  VARCHAR2(10),
  name         VARCHAR2(100),
  status       CHAR(1) default 1 not null,
  busicheckid  VARCHAR2(32) not null,
  orderid      NUMBER(38) default 0 not null,
  billtypeid   VARCHAR2(32),
  processid    VARCHAR2(20),
  typeflag     CHAR(1),
  dbversion    TIMESTAMP(6) default SYSDATE,
  roleid       VARCHAR2(32))}';
  	EXECUTE IMMEDIATE 'alter table P#BIL_T_BUSINESSCHECKDEF add constraint PK_BIL_T_BUSINESSCHECKDEF primary key (STATUS, BUSICHECKID,BILLTYPEID)';
    sys_p_partition_table('P#BIL_T_BUSINESSCHECKDEF');
  END IF;
  
  SELECT COUNT(1) INTO v_count FROM USER_TABLES WHERE TABLE_NAME='P#BIL_T_REPORTSECU';
  IF v_count=0 THEN 
    EXECUTE IMMEDIATE Q'{create table P#BIL_T_REPORTSECU
( status     CHAR(1) default '1' not null,
  dbversion  TIMESTAMP(6) default SYSDATE,
  datakey    VARCHAR2(32) default sys_guid() not null,
  orderid    NUMBER(9) default 0,
  needupdate VARCHAR2(4000),
  finyear    VARCHAR2(4),
  agencyid   VARCHAR2(32),
  columnid   VARCHAR2(32),
  tabrelaid  VARCHAR2(32),
  colsecu    CHAR(1),
  appid      VARCHAR2(20),
  guid       VARCHAR2(32) default sys_guid() not null)}';
  	EXECUTE IMMEDIATE 'alter table P#BIL_T_REPORTSECU add constraint PK_BIL_T_REPORTSECU primary key (STATUS, GUID)';
    sys_p_partition_table('P#BIL_T_REPORTSECU');
  END IF;
  
  SELECT COUNT(1) INTO v_count FROM USER_TABLES WHERE TABLE_NAME='P#BIL_T_SETTABRELATION';
  IF v_count=0 THEN 
    EXECUTE IMMEDIATE Q'{create table P#BIL_T_SETTABRELATION
( maintabid VARCHAR2(32) not null,
  tabrelaid VARCHAR2(32) not null,
  tableid   VARCHAR2(32) not null,
  mainfkid  VARCHAR2(32) not null,
  fkid      VARCHAR2(32) not null,
  dbversion TIMESTAMP(6) default SYSDATE,
  bgtlvl    CHAR(1),
  status    CHAR(1) default 1 not null,
  guid      VARCHAR2(32) default SYS_GUID() not null)}';
  	EXECUTE IMMEDIATE 'alter table P#BIL_T_SETTABRELATION add constraint PK_BIL_T_SETTABRELATION primary key (STATUS, TABRELAID, MAINTABID, TABLEID, GUID)';
    sys_p_partition_table('P#BIL_T_SETTABRELATION');
  END IF;
  
  SELECT COUNT(1) INTO v_count FROM USER_TABLES WHERE TABLE_NAME='P#BIL_T_OARELATION';
  IF v_count=0 THEN 
    EXECUTE IMMEDIATE Q'{create table P#BIL_T_OARELATION
( docid      VARCHAR2(32),
  billid     VARCHAR2(32),
  userid     VARCHAR2(32),
  orgid      VARCHAR2(32),
  remark     VARCHAR2(100),
  guid       VARCHAR2(32) default SYS_GUID() not null,
  dbversion  TIMESTAMP(6) default SYSDATE,
  status     CHAR(1) default 1 not null,
  billtypeid VARCHAR2(32))}';
  	EXECUTE IMMEDIATE 'alter table P#BIL_T_OARELATION add constraint PK_BIL_T_OARELATION primary key (STATUS, GUID)';
    sys_p_partition_table('P#BIL_T_OARELATION');
    EXECUTE IMMEDIATE Q'{create or replace view bil_t_oarelation as 
    select DBVERSION,DOCID,GUID,ORGID,REMARK,STATUS,BILLID,BILLTYPEID,USERID from P#BIL_T_OARELATION where Year=Global_MultYear_CZ.Secu_f_GLOBAL_PARM('YEAR') AND STATUS='1'}';
  END IF;
  
  SELECT COUNT(1) INTO v_count FROM USER_TABLES WHERE TABLE_NAME='P#BIL_T_SETTAB';
  IF v_count=0 THEN 
    EXECUTE IMMEDIATE Q'{create table P#BIL_T_SETTAB
( billtypeid  VARCHAR2(32) not null,
  guid        VARCHAR2(32) default SYS_GUID() not null,
  orderid     NUMBER(9) default 0,
  tableid     VARCHAR2(32),
  isreserve   CHAR(1),
  dbversion   TIMESTAMP(6) default SYSDATE,
  bgtlvl      CHAR(1),
  status      CHAR(1) default 1 not null,
  ismaintable CHAR(1) default 0,
  isreadonly  CHAR(1) default 0,
  isopened    CHAR(1) default 0)}';
  	EXECUTE IMMEDIATE 'alter table P#BIL_T_SETTAB add constraint PK_P#BIL_T_SETTAB primary key (STATUS, BILLTYPEID, GUID)';
    sys_p_partition_table('P#BIL_T_SETTAB');
  END IF;
  
  SELECT COUNT(1) INTO v_count FROM USER_TABLES WHERE TABLE_NAME='P#BIL_T_MENUBILLTYPE';
  IF v_count=0 THEN 
    EXECUTE IMMEDIATE Q'{create table P#BIL_T_MENUBILLTYPE
( menuguid   VARCHAR2(32) not null,
  billtypeid VARCHAR2(32) not null,
  status     CHAR(1) default '1' not null,
  dbversion  TIMESTAMP(6) default SYSDATE,
  menutype   VARCHAR2(32))}';
  	EXECUTE IMMEDIATE 'alter table P#BIL_T_MENUBILLTYPE add constraint PK_BIL_T_MENUBILLTYPE primary key (STATUS, MENUGUID, BILLTYPEID)';
    sys_p_partition_table('P#BIL_T_MENUBILLTYPE');
  END IF;

  SELECT COUNT(1) INTO v_count FROM USER_TABLES WHERE TABLE_NAME='P#BIL_T_CHECKRESULT';
  IF v_count=0 THEN 
    EXECUTE IMMEDIATE Q'{create table P#BIL_T_CHECKRESULT
      ( guid           VARCHAR2(32) default sys_guid() not null,
        billtypeid     VARCHAR2(32) not null,
        busicheckid    VARCHAR2(32) not null,
        ckdate         VARCHAR2(32) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
        ckflag         CHAR(1),
        userguid       VARCHAR2(32),
        userupagencyid VARCHAR2(32),
        userdistrictid VARCHAR2(32),
        status         CHAR(1) default '1' not null,
        dbversion      TIMESTAMP(6) default SYSDATE,
        billid         VARCHAR2(32))}';
  	EXECUTE IMMEDIATE 'alter table P#BIL_T_CHECKRESULT add constraint PK_BIL_T_CHECKRESULT primary key (STATUS, GUID, BILLTYPEID, BUSICHECKID)';
    sys_p_partition_table('P#BIL_T_CHECKRESULT');
  END IF;

  SELECT COUNT(1) INTO v_count FROM USER_TABLES WHERE TABLE_NAME='P#BIL_T_CHECKERROR';
  IF v_count=0 THEN 
    EXECUTE IMMEDIATE Q'{create table P#BIL_T_CHECKERROR
( dbversion     TIMESTAMP(6) default SYSDATE,
  guid          VARCHAR2(32) default sys_guid() not null,
  checkresultid VARCHAR2(32) not null,
  rightvalue    VARCHAR2(500),
  leftvalue     VARCHAR2(500),
  deviation     VARCHAR2(500),
  ckresult      CLOB,
  checkdata     CLOB,
  status        CHAR(1) default '1' not null)}';
  	EXECUTE IMMEDIATE 'alter table P#BIL_T_CHECKERROR add constraint PK_BIL_T_CHECKERROR primary key (STATUS, GUID)';
    sys_p_partition_table('P#BIL_T_CHECKERROR');
  END IF;

  SELECT COUNT(1) INTO v_count FROM USER_TABLES WHERE TABLE_NAME='P#BIL_T_AUDITROLE';
  IF v_count=0 THEN 
    EXECUTE IMMEDIATE Q'{create table P#BIL_T_AUDITROLE
( status      CHAR(1) default 1 not null,
  dbversion   TIMESTAMP(6) default SYSDATE,
  orderid     NUMBER(9) default 0,
  busicheckid VARCHAR2(32) not null,
  guid        VARCHAR2(32) default sys_guid() not null,
  roleid      VARCHAR2(32) not null
)}';
    EXECUTE IMMEDIATE 'alter table P#BIL_T_AUDITROLE add constraint PK_BIL_T_AUDITROLE primary key (STATUS, BUSICHECKID, ROLEID, GUID)';
    sys_p_partition_table('P#BIL_T_AUDITROLE');
END IF;

SELECT COUNT(1) INTO v_count FROM USER_TABLES WHERE TABLE_NAME='P#BIL_T_SINGLERECORD';
  IF v_count=0 THEN 
    EXECUTE IMMEDIATE Q'{create table P#BIL_T_SINGLERECORD
( objectid         VARCHAR2(32) not null,
  tableid          VARCHAR2(32) not null,
  recid            VARCHAR2(32) not null,
  showcols         NUMBER(9) default 0,
  titlewidth       NUMBER(9) default 0,
  remark           VARCHAR2(500),
  processid        VARCHAR2(32),
  status           CHAR(1) default '1' not null,
  dbversion        TIMESTAMP(6) default SYSDATE,
  absoluteposition NUMBER(9) default 0,
  labletextalign   CHAR(1) default '0'
)}';
    EXECUTE IMMEDIATE 'alter table P#BIL_T_SINGLERECORD add constraint PK_BIL_T_SINGLERECORD primary key (STATUS, OBJECTID, TABLEID, RECID)';
   sys_p_partition_table('P#BIL_T_SINGLERECORD');
END IF;

SELECT COUNT(1) INTO v_count FROM USER_TABLES WHERE TABLE_NAME='P#BIL_T_SINRECDETAIL';
  IF v_count=0 THEN 
    EXECUTE IMMEDIATE Q'{create table P#BIL_T_SINRECDETAIL
( recid       VARCHAR2(32) not null,
  ctrlid      VARCHAR2(32) not null,
  ctrlname    VARCHAR2(100),
  superid     VARCHAR2(32),
  isleaf      CHAR(1) default 1,
  levelno     NUMBER(1) default 0,
  orderid     NUMBER(9) default 0,
  istext      CHAR(1),
  colspan     NUMBER(9) default 0,
  rowspan     NUMBER(9) default 0,
  isgroupctrl CHAR(1),
  isshow      CHAR(1),
  guid        VARCHAR2(32) default SYS_GUID() not null,
  status      CHAR(1) default '1' not null,
  dbversion   TIMESTAMP(6) default SYSDATE,
  leftcols    NUMBER(9) default 0,
  toprows     NUMBER(9) default 0,
  topgroups   NUMBER(9) default 0,
  border      CHAR(1) default '1'
)}';
    EXECUTE IMMEDIATE 'alter table P#BIL_T_SINRECDETAIL add constraint PK_BIL_T_SINRECDETAIL primary key (STATUS, GUID)';
    sys_p_partition_table('P#BIL_T_SINRECDETAIL');
END IF;
end;
--单据脚本_1#

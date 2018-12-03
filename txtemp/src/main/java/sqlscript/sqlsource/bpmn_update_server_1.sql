






/* 2018.8.29 LW 独立部署工作流模板扩展字段*/
/*添加系统标识*/
  /*添加系统标识APP_ID_ 业务系统模板部署标识DEPLOYMENT_STATE_ 部署ID字段*/
  declare i integer;
begin
   select count(*) into i from user_tables t where t.table_name = 'ACT_HQ_TEM_DEF';
 if i = 0 then
             execute immediate '
            create table ACT_HQ_TEM_DEF
            (
              id            VARCHAR2(32) not null,
              name          VARCHAR2(64),
              category      VARCHAR2(255),
              version       INTEGER,
              create_by     VARCHAR2(64),
              modify_by     VARCHAR2(64),
              create_time   TIMESTAMP(6),
              modify_time   TIMESTAMP(6),
              deploy_state  INTEGER,
              version_state INTEGER,
              deployment_id VARCHAR2(255),
              content_bytes BLOB,
              init_num      INTEGER,
              canvaswidth   VARCHAR2(32),
              canvasheight  VARCHAR2(32),
              table_ids     VARCHAR2(255),
              table_names   VARCHAR2(255)
            )';
              execute immediate 'alter table ACT_HQ_TEM_DEF add primary key (ID)';
 end if;
  select count(*) into i from user_tables t where t.table_name = 'ACT_HQ_TEM_CATEGORY';
             if i = 0 then
              execute immediate '
             create table ACT_HQ_TEM_CATEGORY
            (
              id            VARCHAR2(32) not null,
              name          VARCHAR2(64),
              category      VARCHAR2(255),
              parentid      VARCHAR2(32),
              description   VARCHAR2(32),
              proc_def_key_ VARCHAR2(255)
            )';
             execute immediate 'alter table ACT_HQ_TEM_CATEGORY add primary key (ID)';
 end if;
   


  SELECT COUNT(*) INTO i FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'ACT_HQ_TEM_DEF' AND COLUMN_NAME = 'APP_ID_';
  if i=0
    then execute immediate 'ALTER TABLE ACT_HQ_TEM_DEF  ADD (APP_ID_ varchar(32))';
  end if;
  SELECT COUNT(*) INTO i FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'ACT_HQ_TEM_DEF' AND COLUMN_NAME = 'DEPLOYMENT_ID_';
  if i=0
    then execute immediate 'ALTER TABLE ACT_HQ_TEM_DEF  ADD (DEPLOYMENT_ID_  varchar(255))';
  end if;
   SELECT COUNT(*) INTO i FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'ACT_HQ_TEM_DEF' AND COLUMN_NAME = 'DEPLOYMENT_STATE_';
  if i=0
    then execute immediate 'ALTER TABLE ACT_HQ_TEM_DEF  ADD (DEPLOYMENT_STATE_ INTEGER DEFAULT 0)';
    /**execute immediate ' UPDATE ACT_HQ_TEM_DEF aht set aht.deployment_state_= (case when aht.deployment_id is not null then 1 else 0 end )';*/
    /**execute immediate 'commit';  */  
  end if;
  /**创建模板code表*/
  select count(*) into i from user_tables t where t.table_name = 'ACT_HQ_CODE';
				 if i = 0 then
				 execute immediate '
				 create table ACT_HQ_CODE
					(
					  id        VARCHAR2(32) not null,
					  code_key  VARCHAR2(64),
					  code_name VARCHAR2(200),
					  super_id  VARCHAR2(32),
					  code_type VARCHAR2(64),
					  order_id  NUMBER(10),
					  dept_id   VARCHAR2(32)
					)';
  execute immediate 'alter table ACT_HQ_CODE add primary key (ID)';
 end if;
 /**创建field表*/
  select count(*) into i from user_tables t where t.table_name = 'ACT_HQ_FIELD';
				 if i = 0 then
				 execute immediate '
				  create table ACT_HQ_FIELD
						(
						  id          VARCHAR2(32) not null,
						  name        VARCHAR2(64),
						  dbname      VARCHAR2(32),
						  description VARCHAR2(255),
						  tableid     VARCHAR2(32)
						)';
  execute immediate 'alter table ACT_HQ_FIELD add primary key (ID)';
 end if;
 
 select count(*) into i from user_tables t where t.table_name = 'ACT_HQ_TABLE';
				 if i = 0 then
				 execute immediate 'create table ACT_HQ_TABLE
				(
				  id        VARCHAR2(32) not null,
				  name      VARCHAR2(100),
				  dbname    VARCHAR2(30),
				  parentid  VARCHAR2(32),
				  bpmn_type VARCHAR2(200)
				)';
  execute immediate 'alter table ACT_HQ_TABLE add primary key (ID)';
 end if;
 
  /**
   *创建高频模板分析表
   */
  select count(*) INTO i from USER_TABLES u where u.TABLE_NAME='ACT_HQ_PIPDDEPLYANALY';
  if i=0
    then execute immediate 'create table ACT_HQ_PIPDDEPLYANALY
                (
                  proc_inst_id_     NVARCHAR2(64),
                  proc_def_id_      NVARCHAR2(64),
                  deployment_id_    NVARCHAR2(64),
                  proc_inst_status_ NUMBER(1),
                  app_id_           NVARCHAR2(64),
                  year_             CHAR(4)
                )
               ';
    end if;
  /**
   *创建任务分析表
   */
  select count(*) INTO i from USER_TABLES u where u.TABLE_NAME='ACT_HQ_TASKANALY';
  if i=0
    then execute immediate 'create table ACT_HQ_TASKANALY
                  (
                    proc_inst_id_  NVARCHAR2(64),
                    proc_def_id_   NVARCHAR2(64),
                    deployment_id_ NVARCHAR2(64),
                    assignee_      NVARCHAR2(64),
                    act_id_        NVARCHAR2(255),
                    act_name_      NVARCHAR2(255),
                    task_type_     NUMBER(1),
                    start_time_    TIMESTAMP(6),
                    end_time_      TIMESTAMP(6),
                    duration_      NUMBER(20),
                    app_id_        NVARCHAR2(64),
                    year_          CHAR(4)
                  )';
    end if;
  
  
  /**
   *创建用户表
   */
  select count(*) INTO i from USER_TABLES u where u.TABLE_NAME='ACT_ID_USER';
  if i=0
    then execute immediate 'create table ACT_ID_USER
          (
            ID_         NVARCHAR2(64) not null,
            REV_        INTEGER,
            FIRST_      NVARCHAR2(255),
            LAST_       NVARCHAR2(255),
            EMAIL_      NVARCHAR2(255),
            PWD_        NVARCHAR2(255),
            PICTURE_ID_ NVARCHAR2(64),
            STATUS_     VARCHAR2(10) default 1,
            STATE_      VARCHAR2(10) default 1,
            PROVINCE    VARCHAR2(32)
          )';
    end if;
  
  /**
   *创建分组表
   */
  select count(*) INTO i from USER_TABLES u where u.TABLE_NAME='ACT_ID_GROUP';
  if i=0
    then execute immediate 'create table ACT_ID_GROUP
              (
                ID_       NVARCHAR2(64) not null,
                REV_      INTEGER,
                NAME_     NVARCHAR2(255),
                TYPE_     NVARCHAR2(255),
                PID_      NVARCHAR2(64),
                CATEGORY_ NVARCHAR2(64),
                ORDERCODE NUMBER(18),
                ORGCODE   VARCHAR2(200),
                PROVINCE  VARCHAR2(32)
              )';
    end if;
  
/**
   *创建分组用户关系表
   */
  select count(*) INTO i from USER_TABLES u where u.TABLE_NAME='ACT_ID_MEMBERSHIP';
  if i=0
    then execute immediate 'create table ACT_ID_MEMBERSHIP
              (
                USER_ID_  NVARCHAR2(64) not null,
                GROUP_ID_ NVARCHAR2(64) not null
              )';
    end if;

  /**
   *创建系统标识表
   */
  select count(*) INTO i from USER_TABLES u where u.TABLE_NAME='FW_T_SYSAPP';
  if i=0
    then execute immediate 'create table FW_T_SYSAPP
                (
                  APPID        VARCHAR2(100) not null,
                  APPNAME      VARCHAR2(600),
                  STATUS       NUMBER(1),
                  DEVELOPER    VARCHAR2(300),
                  DBVERSION    TIMESTAMP(6) default systimestamp,
                  ISCOMMONMENU CHAR(1),
                  DIIDE        VARCHAR2(32),
                  ORDERNUM     NUMBER
                )';
    end if;
  
  /**
   *创建系统域名表
   */
  select count(*) INTO i from USER_TABLES u where u.TABLE_NAME='FW_T_SYSDOMAIN';
  if i=0
    then execute immediate 'create table FW_T_SYSDOMAIN
            (
              APPID         VARCHAR2(50) not null,
              DOMAINIP      VARCHAR2(300),
              DOMAINPORT    VARCHAR2(10),
              DOMAINMARK    VARCHAR2(300),
              ISLOCAL       NUMBER(1),
              ROOTPATH      VARCHAR2(300),
              YEAR          VARCHAR2(20),
              PROVINCE      VARCHAR2(20),
              DSGUID        VARCHAR2(32),
              PARTITIONTYPE VARCHAR2(1),
              DBVERSION     TIMESTAMP(6) default systimestamp
            )';
    end if;
  /**
   *创建日志分析表
   */
  select count(*) INTO i from USER_TABLES u where u.TABLE_NAME='ACT_HQ_LOGINCRANALY';
  if i=0
    then execute immediate 'create table ACT_HQ_LOGINCRANALY
              (
                ID_          NVARCHAR2(64),
                DATA_TYPE_   NUMBER(1),
                ACTION_TYPE_ NUMBER(1),
                MODE_        NUMBER(1)
              )';
    end if;
    select count(*)
    into i
    from user_tables t
   where t.table_name = 'ACT_HQ_CONFIG';
  if i = 0 then
    execute immediate 'create table ACT_HQ_CONFIG(key_ varchar2(255), value1_ varchar2(255), value2_ number(1) )';
    execute immediate 'alter table ACT_HQ_CONFIG add primary key (key_)';
  end if;
  commit;
end;

DECLARE 
  v_name VARCHAR2(30);
  v_type VARCHAR2(30);
BEGIN
  v_name := 'BGT_T_YSJZSJB_ADJUST';
  SELECT nvl((SELECT object_type FROM user_objects WHERE object_name = v_name),'') INTO v_type FROM dual;
  IF v_type = 'TABLE' THEN
    EXECUTE IMMEDIATE 'DROP TABLE '|| v_name;
  END IF;
   IF v_type = 'VIEW' THEN
    EXECUTE IMMEDIATE 'DROP VIEW '|| v_name;
    EXECUTE IMMEDIATE 'DROP TABLE P#'|| v_name;
  END IF;
  
  EXECUTE IMMEDIATE Q'{create table BGT_T_YSJZSJB_ADJUST
                      (
                        province       VARCHAR2(9) not null,
                        year           CHAR(4) not null,
                        status         CHAR(1) default '1' not null,
                        dbversion      TIMESTAMP(6) default SYSDATE,
                        guid           VARCHAR2(32) default sys_guid() not null,
                        needupdate     VARCHAR2(4000),
                        orderid        NUMBER(9) default 0,
                        finyear        VARCHAR2(32) default to_char(sysdate,'YYYY'),
                        agencyguid     VARCHAR2(32),
                        proguid        VARCHAR2(32),
                        agencyexpguid  VARCHAR2(32),
                        expfuncguid    VARCHAR2(32),
                        expecoguid     VARCHAR2(32),
                        finintorgguid  VARCHAR2(32),
                        puritemguid    VARCHAR2(32),
                        gppsitemguid   VARCHAR2(32),
                        fundtypeguid   VARCHAR2(32),
                        fundsourceguid VARCHAR2(32),
                        amt            NUMBER(18,4) default 0,
                        taskid         VARCHAR2(32),
                        datakey        VARCHAR2(32),
                        sfjz           CHAR(1) default '0',
                        srcversion     TIMESTAMP(6),
                        iswriteoff     CHAR(1) default '0',
                        isdistrict     VARCHAR2(32),
                        incomesortguid VARCHAR2(32),
                        bgttypeguid    VARCHAR2(32),
                        locincstrguid  VARCHAR2(32),
                        xmbm           VARCHAR2(50),
                        locexpstrguid  VARCHAR2(32),
                        wh             VARCHAR2(100),
                        bgtsourceguid  VARCHAR2(32),
                        safundsguid    VARCHAR2(32)
                      )}';
  

  PKG_MULTYEAR.Secu_SP_MANY_Y_CZ_Do(v_name);
  EXECUTE IMMEDIATE 'ALTER TABLE P#'|| v_name ||' ADD CONSTRAINT PK_'|| v_name ||' PRIMARY KEY (PROVINCE,YEAR,STATUS,GUID)';
        
END;
--BGT_T_YSJZSJB_ADJUST_CREATE

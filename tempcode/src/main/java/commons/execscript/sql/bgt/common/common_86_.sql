DECLARE 
  v_i NUMBER(10);
  v_name VARCHAR2(30);
  v_type VARCHAR2(30);
BEGIN
  v_name := 'BGT_T_CONFIRMLOG';
  SELECT nvl((SELECT object_type FROM user_objects WHERE object_name = v_name),'') INTO v_type FROM dual;
  IF v_type = 'TABLE' THEN
    EXECUTE IMMEDIATE 'DROP TABLE '|| v_name;
  END IF;
   IF v_type = 'VIEW' THEN
    EXECUTE IMMEDIATE 'DROP VIEW '|| v_name;
    EXECUTE IMMEDIATE 'DROP TABLE P#'|| v_name;
  END IF;
  
  EXECUTE IMMEDIATE 'CREATE TABLE BGT_T_CONFIRMLOG (
       GUID VARCHAR2(32)  DEFAULT SYS_GUID() NOT NULL ,
       RULEID VARCHAR2(32),
       AGENCYID VARCHAR2(32),
       RUNTIME timestamp(6)
       )';
  
  EXECUTE IMMEDIATE 'alter table '|| v_name ||' ADD YEAR CHAR(4) NOT NULL';
  EXECUTE IMMEDIATE 'alter table '|| v_name ||' ADD PROVINCE VARCHAR2(9) NOT NULL'; 
  EXECUTE IMMEDIATE 'alter table '|| v_name ||' ADD DBVERSION TIMESTAMP(6) DEFAULT SYSDATE'; 
  EXECUTE IMMEDIATE 'alter table '|| v_name ||' ADD STATUS CHAR(1) DEFAULT 1';
  
  SELECT COUNT(1) INTO v_i FROM USER_OBJECTS WHERE OBJECT_NAME = 'PKG_MULTYEAR';

  IF v_i = 0 THEN
     EXECUTE IMMEDIATE Q'{CALL GLOBAL_MULTYEAR_CZ.Secu_SP_MANY_Y_CZ_Do('BGT_T_CONFIRMLOG')}';
  ELSE
     EXECUTE IMMEDIATE Q'{CALL PKG_MULTYEAR.Secu_SP_MANY_Y_CZ_Do('BGT_T_CONFIRMLOG')}';
  END IF;
  
  EXECUTE IMMEDIATE 'ALTER TABLE P#'|| v_name ||' ADD CONSTRAINT PK_'|| v_name ||' PRIMARY KEY (PROVINCE,YEAR,STATUS,GUID)';
  EXECUTE IMMEDIATE 'create index IDX_'|| v_name ||'0001 ON P#'|| v_name ||'("PROVINCE", "YEAR", "STATUS","RULEID","AGENCYID")';
        
END;
--BGT_T_CONFIRMLOG

DECLARE
v_i NUMBER(2);
BEGIN
    SELECT COUNT(1)  INTO v_i FROM USER_CONSTRAINTS  WHERE CONSTRAINT_NAME = 'PK_CDT_T_TASKTYPE';
    IF v_i > 0 THEN 
      EXECUTE IMMEDIATE 'alter table P#CDT_T_TASKTYPE drop constraint PK_CDT_T_TASKTYPE cascade drop index';
    END IF;
    
     SELECT COUNT(1) INTO v_i FROM USER_INDEXES WHERE INDEX_NAME = 'PK_CDT_T_TASKTYPE';
     IF v_i > 0 THEN 
      EXECUTE IMMEDIATE 'drop index PK_CDT_T_TASKTYPE';
     END IF;
 
    EXECUTE IMMEDIATE 'alter table P#CDT_T_TASKTYPE add constraint PK_CDT_T_TASKTYPE primary key (STATUS, YEAR, TASKTYPEID)';
END;--CDT_T_TASKTYPE�޸�������������

DECLARE
v_i NUMBER(2);
BEGIN
    SELECT COUNT(1) INTO v_i FROM USER_INDEXES WHERE INDEX_NAME = 'IDX$$_2B730001';
    IF v_i = 0 THEN 
      EXECUTE IMMEDIATE 'create index IDX$$_2B730001 ON P#BGT_T_OARELATION("PROVINCE", "YEAR", "STATUS","DOCID","TASKTYPE")';
    END IF;
END;
--����_ ����BGT_T_OARELATION����

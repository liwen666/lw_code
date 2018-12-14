DECLARE
v_i NUMBER(2);
BEGIN
    SELECT COUNT(1) INTO v_i FROM USER_INDEXES WHERE INDEX_NAME = 'IDX$$_2B730001';
    IF v_i > 0 THEN 
      EXECUTE IMMEDIATE 'DROP INDEX IDX$$_2B730001';
    END IF;
    EXECUTE IMMEDIATE 'CREATE INDEX IDX$$_2B730001 on P#BGT_T_OARELATION (YEAR, STATUS, DOCID, TASKTYPE)';
END;--����_ �޸�BGT_T_OARELATION����
DECLARE
  v_i NUMBER(10);
BEGIN
  SELECT COUNT(1) INTO v_i FROM User_Tab_Columns WHERE table_Name = 'P#CDT_T_TASKINFO' AND column_Name = 'TASKSOURCE';
  IF v_i = 0 THEN
    EXECUTE IMMEDIATE 'ALTER TABLE P#CDT_T_TASKINFO ADD TASKSOURCE VARCHAR2(32)';
  END IF;
  EXECUTE IMMEDIATE Q'{COMMENT ON COLUMN P#CDT_T_TASKINFO.TASKSOURCE IS '任务来源'}';
  EXECUTE IMMEDIATE Q'{CREATE OR REPLACE VIEW CDT_T_TASKINFO AS
                              SELECT TASKID,
                               TASKNAME,
                               SUPERTASKID,
                               TASKTYPEID,
                               ROOTTASKID,
                               DISTRICTID,
                               RECEIVEID,
                               TASKLVLID,
                               TASKCODE,
                               TASKCYCLE,
                               DATASTARTDATE,
                               DATAENDDATE,
                               CREATOR,
                               CREATEDATE,
                               TASKDESC,
                               ENDDATE,
                               TASKSTATUS,
                               REMARK,
                               LEVELNO,
                               APPID,
                               STATUS,
                               ISINSTEAD,
                               ISLEAF,
                               FINYEAR,
                               TASKSOURCE
                          FROM P#CDT_T_TASKINFO
                         WHERE STATUS = '1'}';

END;

--杨喜梅__任务表添加任务来源字段

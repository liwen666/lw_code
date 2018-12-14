DECLARE
  v_i NUMBER(10);
BEGIN
  SELECT COUNT(1) INTO v_i FROM User_Tab_Columns WHERE table_Name = 'P#CDT_T_TASKINFO' AND column_Name = 'ISSHOW';
  IF v_i = 0 THEN
    EXECUTE IMMEDIATE 'ALTER TABLE P#CDT_T_TASKINFO ADD ISSHOW CHAR(1) DEFAULT 1';
  END IF;
  EXECUTE IMMEDIATE Q'{COMMENT ON COLUMN P#CDT_T_TASKINFO.TASKSOURCE IS '�Ƿ������ݲ鿴������ʾ'}';
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
                               TASKSOURCE,
                               ISSHOW
                          FROM P#CDT_T_TASKINFO
                         WHERE STATUS = '1'}';

END;


--��ϲ÷_���������ֶΡ��Ƿ������ݲ鿴������ʾ��
--20160629��ϲ÷_���������ֶΡ��Ƿ������ݲ鿴������ʾ��

DECLARE 
v_count NUMBER(10);
BEGIN
  SELECT COUNT(1) INTO v_count FROM User_Tab_Cols WHERE table_Name = 'P#CDT_T_TASKINFO' AND column_Name = 'FINYEAR';
  IF v_count = 0 THEN 
    EXECUTE IMMEDIATE Q'{ALTER TABLE P#CDT_T_TASKINFO ADD FINYEAR VARCHAR2(32)}';
  END IF;
  EXECUTE IMMEDIATE Q'{UPDATE P#CDT_T_TASKINFO SET FINYEAR = (SELECT YEAR FROM FW_T_YEAR WHERE ISDEFAULT = '1') WHERE FINYEAR IS NULL}';
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
                               FINYEAR
                          FROM P#CDT_T_TASKINFO
                         WHERE STATUS = '1'}';
END;
--CDT_T_TASKINFOÌí¼ÓFINYEAR

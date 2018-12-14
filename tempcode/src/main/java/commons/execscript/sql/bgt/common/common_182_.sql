DECLARE
v_count NUMBER(10);
BEGIN
  SELECT COUNT(1) INTO v_count FROM User_Tab_Cols WHERE table_Name = 'P#CDT_T_TASKTYPE' AND column_Name = 'BUSITYPEID';
  IF v_count = 0 THEN 
    EXECUTE IMMEDIATE Q'{ALTER TABLE p#cdt_t_tasktype ADD BUSITYPEID VARCHAR(32)}';
  END IF;
  EXECUTE IMMEDIATE Q'{CREATE OR REPLACE VIEW CDT_T_TASKTYPE_ALL AS
                               SELECT TASKTYPEID,
                               TASKTYPENAME,
                               TASKTYPEDESC,
                               DISTRICTID,
                               BUSITYPEID,
                               ORDERID,
                               TASKBUSITYPE,
                               TASKCYCLE_TYPE,
                               CODERULE,
                               NAMERULE,
                               TARGET_VIEWNAME,
                               STATUS,
                               APPID,
                               PROCESS_TPLID,
                               TRANSFORMTYPE,
                               DBVERSION,
                               COLLECTRANGE,
                               CONFIRMFUNC,
                               ISSUM,
                               SUMCYC,
                               REFPROC,
                               ISSECUCTRL,
                               NEEDACCOUNT,
                               TASKREPEAT,
                               CYCLEREPEAT,
                               YEAR AS FINYEAR
                               FROM P#CDT_T_TASKTYPE
                               WHERE STATUS = '1'}';
   EXECUTE IMMEDIATE Q'{CREATE OR REPLACE VIEW CDT_T_TASKTYPE AS
                     SELECT TASKTYPEID,
                               TASKTYPENAME,
                               TASKTYPEDESC,
                               DISTRICTID,
                               BUSITYPEID,
                               ORDERID,
                               TASKBUSITYPE,
                               TASKCYCLE_TYPE,
                               CODERULE,
                               NAMERULE,
                               TARGET_VIEWNAME,
                               STATUS,
                               APPID,
                               PROCESS_TPLID,
                               TRANSFORMTYPE,
                               DBVERSION,
                               COLLECTRANGE,
                               CONFIRMFUNC,
                               ISSUM,
                               SUMCYC,
                               REFPROC,
                               ISSECUCTRL,
                               NEEDACCOUNT,
                               TASKREPEAT,
                               CYCLEREPEAT
                          FROM P#CDT_T_TASKTYPE
                         WHERE YEAR = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR')
                           AND STATUS = '1'}';
END;
--2-添加任务类型对应业务类型字段

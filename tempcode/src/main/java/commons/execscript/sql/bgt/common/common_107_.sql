DECLARE 
  v_count NUMBER;
BEGIN
  SELECT COUNT(1) INTO v_count FROM User_Tab_Cols WHERE table_name = 'P#CDT_T_TASKTYPE' AND column_Name = 'YEAR';
  IF v_count = 0 THEN
     EXECUTE IMMEDIATE 'ALTER TABLE P#CDT_T_TASKTYPE ADD YEAR CHAR(4)';
  END IF;
  
  EXECUTE IMMEDIATE Q'{UPDATE P#CDT_T_TASKTYPE SET YEAR = (SELECT YEAR FROM FW_T_YEAR WHERE ISDEFAULT = '1') WHERE YEAR IS NULL}';
  
  EXECUTE IMMEDIATE Q'{UPDATE DICT_T_SETTINGTABINFO SET YEARPART = '1' WHERE DBTABLENAME = 'P#CDT_T_TASKTYPE'}'; 
  
  EXECUTE IMMEDIATE Q'{CREATE OR REPLACE VIEW CDT_T_TASKTYPE AS
                        SELECT TASKTYPEID,
                               TASKTYPENAME,
                               TASKTYPEDESC,
                               DISTRICTID,
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
                               NEEDACCOUNT
                          FROM P#CDT_T_TASKTYPE
                         WHERE YEAR = GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR')
                           AND STATUS = '1'}';
                           
   EXECUTE IMMEDIATE Q'{CREATE OR REPLACE VIEW CDT_T_TASKTYPE_ALL AS
                        SELECT TASKTYPEID,
                               TASKTYPENAME,
                               TASKTYPEDESC,
                               DISTRICTID,
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
                               YEAR AS FINYEAR
                          FROM P#CDT_T_TASKTYPE
                         WHERE STATUS = '1'}';                        

END;
--任务类型添加分区

DECLARE v_exist number(8) := 0;

BEGIN
  -- 给项目绩效评价指标表添加是否可删除字段 BEGIN
  SELECT COUNT(1) INTO v_exist FROM user_tab_columns WHERE COLUMN_NAME ='ISDELETE' AND TABLE_NAME = 'P#KPI_T_SETPROJEVAL';

  IF v_exist=0 THEN
    EXECUTE IMMEDIATE 'ALTER TABLE P#KPI_T_SETPROJEVAL ADD ISDELETE CHAR(1) DEFAULT 0';
    EXECUTE IMMEDIATE Q'{CREATE OR REPLACE VIEW KPI_T_SETPROJEVAL AS 
      SELECT 
        STATUS, DATAKEY, NEEDUPDATE, ORDERID, FINYEAR, GUID, 
        SUPERGUID, EVALNAME, INDITYPE, GETDATAFUNC, LEVELNO, 
        ISLEAF, SCORE, REQUIREDCHILD, EXPLANATION, STANDARD, 
        ISINSERT, ISUPDATE, ISDELETE, KPIPROP, DBVERSION,
        FRMKPIID FROM P#KPI_T_SETPROJEVAL 
      WHERE PROVINCE=GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID') 
      AND YEAR=GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR') AND STATUS='1'}';
  END IF;
  -- 给项目绩效评价指标表添加是否可删除字段 END

  -- 给专项绩效评价指标表添加是否可删除字段 BEGIN
  SELECT COUNT(1) INTO v_exist FROM user_tab_columns WHERE COLUMN_NAME ='ISDELETE' AND TABLE_NAME = 'P#KPI_T_SETSPFEVAL';

  IF v_exist=0 THEN
    EXECUTE IMMEDIATE 'ALTER TABLE P#KPI_T_SETSPFEVAL ADD ISDELETE CHAR(1) DEFAULT 0';
    EXECUTE IMMEDIATE Q'{CREATE OR REPLACE VIEW KPI_T_SETSPFEVAL AS 
      SELECT 
        STATUS, DATAKEY, NEEDUPDATE, ORDERID, FINYEAR, GUID, 
        SUPERGUID, EVALNAME, INDITYPE, GETDATAFUNC, LEVELNO, 
        ISLEAF, SCORE, REQUIREDCHILD, EXPLANATION, STANDARD, 
        ISINSERT, ISUPDATE, ISDELETE, KPIPROP, DBVERSION,
        FRMKPIID FROM P#KPI_T_SETSPFEVAL 
      WHERE PROVINCE=GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID') 
      AND YEAR=GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR') AND STATUS='1'}';
  END IF;
  -- 给专项绩效评价指标表添加是否可删除字段 END


  -- 给部门绩效评价指标表添加是否可删除字段 BEGIN
  SELECT COUNT(1) INTO v_exist FROM user_tab_columns WHERE COLUMN_NAME ='ISDELETE' AND TABLE_NAME = 'P#KPI_T_SETDEPTEVAL';

  IF v_exist=0 THEN
    EXECUTE IMMEDIATE 'ALTER TABLE P#KPI_T_SETDEPTEVAL ADD ISDELETE CHAR(1) DEFAULT 0';
    EXECUTE IMMEDIATE Q'{CREATE OR REPLACE VIEW KPI_T_SETDEPTEVAL AS 
      SELECT 
        STATUS, DATAKEY, NEEDUPDATE, ORDERID, FINYEAR, GUID, 
        SUPERGUID, EVALNAME, INDITYPE, GETDATAFUNC, LEVELNO, 
        ISLEAF, SCORE, REQUIREDCHILD, EXPLANATION, STANDARD, 
        ISINSERT, ISUPDATE, ISDELETE, KPIPROP, DBVERSION,
        FRMKPIID FROM P#KPI_T_SETDEPTEVAL 
      WHERE PROVINCE=GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID') 
      AND YEAR=GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR') AND STATUS='1'}';
  END IF;
  -- 给部门绩效评价指标表添加是否可删除字段 END

  -- 给财政绩效评价指标表添加是否可删除字段 BEGIN
  SELECT COUNT(1) INTO v_exist FROM user_tab_columns WHERE COLUMN_NAME ='ISDELETE' AND TABLE_NAME = 'P#KPI_T_SETFINEVAL';

  IF v_exist=0 THEN
    EXECUTE IMMEDIATE 'ALTER TABLE P#KPI_T_SETFINEVAL ADD ISDELETE CHAR(1) DEFAULT 0';
    EXECUTE IMMEDIATE Q'{CREATE OR REPLACE VIEW KPI_T_SETFINEVAL AS 
      SELECT 
        STATUS, DATAKEY, NEEDUPDATE, ORDERID, FINYEAR, GUID, 
        SUPERGUID, EVALNAME, INDITYPE, GETDATAFUNC, LEVELNO, 
        ISLEAF, SCORE, REQUIREDCHILD, EXPLANATION, STANDARD, 
        ISINSERT, ISUPDATE, ISDELETE, KPIPROP, DBVERSION,
        FRMKPIID FROM P#KPI_T_SETFINEVAL 
      WHERE PROVINCE=GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID') 
      AND YEAR=GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR') AND STATUS='1'}';
  END IF;
  -- 给财政绩效评价指标表添加是否可删除字段 END
END;
--绩效_zzk_161122_所有绩效评价指标表添加是否可删除字段

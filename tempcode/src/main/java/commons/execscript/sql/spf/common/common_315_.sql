DECLARE
  ISEXISTS INT;
BEGIN
  SELECT COUNT(1) INTO ISEXISTS FROM user_views  WHERE view_name ='SPF_T_PINVESTPLAN_TP';
  IF ISEXISTS >0 THEN
    EXECUTE IMMEDIATE 'DROP view SPF_T_PINVESTPLAN_TP';
  END IF;
  DELETE FROM P#DICT_T_FACTOR
 WHERE TABLEID IN
       (SELECT tableid FROM P#DICT_T_MODEL WHERE DBTABLENAME = 'SPF_T_PINVESTPLAN_TP');
DELETE FROM P#dict_t_model WHERE dbtablename = 'SPF_T_PINVESTPLAN_TP' ;
END;
--删除项目投资计划来源表

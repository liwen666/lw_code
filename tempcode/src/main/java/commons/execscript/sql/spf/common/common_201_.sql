DECLARE
BEGIN
FOR TAB IN (SELECT TABLEID FROM dict_t_model WHERE appid ='KPI' AND tabletype IN('1','3') ) LOOP
  UPDATE P#DICT_T_MODEL SET ISALLYEAR ='1' WHERE TABLEID = TAB.TABLEID;
  sys_p_recreate_views(TAB.TABLEID);
END LOOP;
END;

--绩效刷新全年度

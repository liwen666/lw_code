BEGIN
update P#dict_t_model
   set secusql = ' spfid in (select spfid from spf_V_fbaseinfo_A where districtid = 
   (select guid from code_t_district_spf where code = global_multyear_cz.Secu_f_GLOBAL_PARM(''DIVID'')))'
 where dbtablename = 'EXP_T_PBUDGETTEMP';
END;--YM_项目库修正项目选择范围

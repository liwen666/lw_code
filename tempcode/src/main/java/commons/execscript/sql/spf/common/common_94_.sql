BEGIN
update fasp_t_pubmenu t 
   set  t.appsysid = '1'
    where t.appid in('spf','bas','bgt','kpi');
 END;
--fasp_t_pubmenu_sysId

i integer;
BEGIN
select count(1) into i  from FW_T_SYSAPP t where APPID in ('bgt','bas','spf','kpi');
 if i = 0 
 then 
  DELETE FROM FW_T_SYSAPP WHERE APPID IN('bgt','bas','spf','kpi');
  INSERT INTO FW_T_SYSAPP(APPID, APPNAME, DEVELOPER)
  VALUES('bgt','预算','TJHQ');

  INSERT INTO FW_T_SYSAPP(APPID, APPNAME, DEVELOPER)
  VALUES('bas','基础资料采集','TJHQ');

  INSERT INTO FW_T_SYSAPP(APPID, APPNAME, DEVELOPER)
  VALUES('spf','项目库','TJHQ');
  
  INSERT INTO FW_T_SYSAPP(APPID, APPNAME, DEVELOPER)
  VALUES('kpi','绩效评价','TJHQ');
 end if;
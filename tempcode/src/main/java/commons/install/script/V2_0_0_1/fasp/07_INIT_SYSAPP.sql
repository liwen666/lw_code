i integer;
BEGIN
select count(1) into i  from FW_T_SYSAPP t where APPID in ('bgt','bas','spf','kpi');
 if i = 0 
 then 
  DELETE FROM FW_T_SYSAPP WHERE APPID IN('bgt','bas','spf','kpi');
  INSERT INTO FW_T_SYSAPP(APPID, APPNAME, DEVELOPER)
  VALUES('bgt','Ԥ��','TJHQ');

  INSERT INTO FW_T_SYSAPP(APPID, APPNAME, DEVELOPER)
  VALUES('bas','�������ϲɼ�','TJHQ');

  INSERT INTO FW_T_SYSAPP(APPID, APPNAME, DEVELOPER)
  VALUES('spf','��Ŀ��','TJHQ');
  
  INSERT INTO FW_T_SYSAPP(APPID, APPNAME, DEVELOPER)
  VALUES('kpi','��Ч����','TJHQ');
 end if;
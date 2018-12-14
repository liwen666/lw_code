declare 
  V_COUNT NUMBER;
begin
  SELECT COUNT(1) INTO V_COUNT FROM fasp_t_pubmenu WHERE GUID='7AB2071676FE489EA6F8DE6C2C1B9869';
  IF V_COUNT = 0 THEN
     insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
     values ('7AB2071676FE489EA6F8DE6C2C1B9869', 2, 1, '1', '004001012', '转移支付下发', '120001', '/spf/spf/export/spfSetting.do?dealtype=4*01', 12, 'remark', null, 2, null, null, 'spf', null, null, null, null, null, null, 1);
  END IF;
end;



--补充转移支付下发菜单

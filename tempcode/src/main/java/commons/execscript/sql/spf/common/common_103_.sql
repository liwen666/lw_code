BEGIN
  DELETE FROM fasp_t_pubmenu WHERE GUID ='004009011';
  insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
  values ('004009011', 1, 1, '1', '004009011', '数据审核公式定义', '120012', '/spf/commons/setting/auditrule/auditRulePage.do?appId=SPF', 11, null, null, 1, null, null, 'spf', null, null, null, null, null, null, 1, null);
  UPDATE dict_t_factor SET isupdate ='1' WHERE dbcolumnname ='CZBKBZXJ';--修改是否可修改
 DELETE FROM fasp_t_pubmenu WHERE GUID ='004010008';
insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('004010008', 2, 1, '1', '004010008', '录入界面定义', '120021', '/spf/commons/setting/input.do?appId=SPF', 10, null, null, 1, null, null, 'spf', null, null, null, null, null, null, 1, null);
DELETE FROM fasp_t_pubmenu WHERE GUID ='12001205';
END;
--DF添加数据审核公式定义

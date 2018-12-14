BEGIN
  DELETE FROM fasp_t_pubmenu WHERE GUID ='120012111';
  insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('120012111', 2, 1, '1', '120012111', '公式刷新_无任务', '120012', '/spf/exp/formulaCalculate.do?appID=SPF', 6, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1, null);
END;
--新增公式刷新

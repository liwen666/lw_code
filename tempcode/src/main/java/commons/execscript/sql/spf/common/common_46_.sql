BEGIN
  delete from fasp_t_pubmenu where guid='12000411';
  delete from fasp_t_pubmenu where guid='12000307';
  insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
  values ('12000307', 2, 1, '1', '004003004', '一级项目备份', '120003', '/spf/spf/baklist/main.do?style=2'||'&'||'dealtype=36'||'&'||'appid=SPF'||'&'||'spfmType=2'||'&'||'isTemp=0'||'&'||'bakLogDealType=4*52', 7, 'remark', null, 2, null, null, 'spf', null, null, null, null, null, null, 1);
END;
--姚丽丽修改菜单

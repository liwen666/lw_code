begin

delete from fasp_t_pubmenu where guid = '9FBB7C0E54F0DE897ACD5A2D5FC19E21';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('9FBB7C0E54F0DE897ACD5A2D5FC19E21', 2, 1, '1', '005008017', '图表设置', '114000', '/bgt/exp/chartSet/main.do?appID=BAS', null, '', sysdate, null, '', '', 'bas', '', '', '', '', '', '', 1, '');

delete from fasp_t_pubmenu where guid = '9FBB7C0E54F0DE897ACD5A2D5FC19123';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('9FBB7C0E54F0DE897ACD5A2D5FC19123', 2, 1, '1', '005008017', '图表设置', '114000', '/bgt/exp/chartSet/main.do?appID=BGT', null, '', sysdate, null, '', '', 'bgt', '', '', '', '', '', '', 1, '');


end;

--图表设置菜单存储过程

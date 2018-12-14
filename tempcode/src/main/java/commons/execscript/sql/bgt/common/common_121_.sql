BEGIN
DELETE FROM fasp_t_pubmenu WHERE GUID = '8BE0AF5EAC904E5088B91DFE84DB97E5';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('8BE0AF5EAC904E5088B91DFE84DB97E5', 2, 1, '1', '005001018', '任务数据综合查看', '130001', '/bgt/exp/taskdataview/main.do?appID=BGT', 18, '', null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);
END;
--数据填报_任务查看菜单

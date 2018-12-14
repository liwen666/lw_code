begin

delete from Fasp_t_Pubmenu where guid = '33303EC41746A944E050A8C0F00022FC';
insert into Fasp_t_Pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('33303EC41746A944E050A8C0F00022FC', 2, 1, '1', '005002004', '转移支出项目预算填报_代录', 'W5D11351D47911FEE050A8C021055CA1', '/bgt/exp/datainputOA/main.do?appID=BGT'||CHR(38)||'operate=45', 1, 'remark', null, 1, null, null, 'bgt', null, null, null, null, null, null, 1, null);

end;

--对下转移项目添加菜单

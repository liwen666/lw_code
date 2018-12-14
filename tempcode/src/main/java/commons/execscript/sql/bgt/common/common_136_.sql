begin

delete from fasp_t_pubmenu where guid = 'BAA3FBB31CC1D1CEEE52119C74DB723F';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('BAA3FBB31CC1D1CEEE52119C74DB723F', 2, 1, '1', '005002004', '周期任务', '110003', '/bgt/exp/taskdoc/taskTransform.do?appID=BGT', 20, '', '', null, '', '', 'bgt', '', '', '', '', '', '', 1, '');


delete from fasp_t_pubmenu where guid = '36C6C2511C0D2D0BE053CB01A8C04265';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('36C6C2511C0D2D0BE053CB01A8C04265', 2, 1, '1', '003003005', '周期任务', '15761DCAC024390DE050A8C021050E32', '/bgt/exp/taskdoc/taskTransform.do?appID=BAS', 20, '', '', null, '', '', 'bas', '', '', '', '', '', '', 1, '');

end;

--菜单_周期任务
--周期任务添加菜单_

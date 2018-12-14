begin

delete from fasp_t_pubmenu where guid = 'BAA3FBB31CC1D1CEEE52119C74DB723F';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('BAA3FBB31CC1D1CEEE52119C74DB723F', 2, 1, '1', '005002004', '周期任务', '130001', '/bgt/exp/taskdoc/taskTransform.do?appID=BGT', 20, '', '', null, '', '', 'bgt', '', '', '', '', '', '', 1, '');

end;

--菜单_周期任务

begin

 --删除绩效指标库菜单
  DELETE FROM fasp_t_pubmenu where guid in ('2200903', '2200904');

--部门绩效指标库菜单
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('2200903', 2, 1, '1', '006006003', '部门绩效指标库', '22009', '/spf/kpi/setting/projkpi.do?kType=A3', 3, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1, null);

--财政绩效指标库菜单
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('2200904', 2, 1, '1', '006006004', '财政绩效指标库', '22009', '/spf/kpi/setting/projkpi.do?kType=A4', 4, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1, null);

end;

--绩效_20160621_YLL绩效指标库菜单

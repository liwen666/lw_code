begin
	 --删除绩效综合查询菜单
  	DELETE FROM fasp_t_pubmenu where guid in ('2200108','220010801','2200307','220030701','2200508','220050801','2200407','220040701');
	 --添加绩效综合查询菜单
	
--项目绩效综合查询 2200108 006001006   
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200108', 2, 0, '1', '006001006', '项目绩效综合查询管理', '22001', null, 33, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010801', 3, 1, '1', '006001006001', '项目绩效综合查询', '2200108', '/spf/kpi/intequery/comprehsv.do?dealtype=KP51&appID=KPI&projmType=1&isReadOnly=1', 1, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);

--专项绩效综合查询 2200307 006002005
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200307', 2, 0, '1', '006002005', '专项绩效综合查询管理', '22003', null, 64, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030701', 3, 1, '1', '006002005001', '专项绩效综合查询', '2200307', '/spf/kpi/intequery/comprehsv.do?dealtype=KF41&appID=KPI&spfmType=1&isReadOnly=1', 1, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);

--财政绩效综合查询 2200508  006004005
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200508', 2, 0, '1', '006004005', '财政绩效综合查询管理', '22005', null, 5, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220050801', 3, 1, '1', '006004005001', '财政绩效综合查询', '2200508', '/spf/kpi/intequery/comprehsv.do?dealtype=71&appID=KPI&kpiType=1&isReadOnly=1', 1, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);

--部门绩效综合查询 2200407 006003007
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200407', 2, 0, '1', '006003007', '部门绩效综合查询管理', '22004', null, 85, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040701', 3, 1, '1', '006003007001', '部门绩效综合查询', '2200407', '/spf/kpi/intequery/comprehsv.do?dealtype=61&appID=KPI&kpiType=1&isReadOnly=1', 1, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);

end;--绩效_20160621_YLL绩效综合查询菜单

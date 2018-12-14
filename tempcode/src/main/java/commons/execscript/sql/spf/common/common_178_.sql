begin
	 --删除绩效设置管理菜单
  	DELETE FROM fasp_t_pubmenu where guid='22006';
	 --添加绩效设置管理菜单
	insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
	values ('22006', 1, 0, '1', '006000', '绩效设置管理', '092022D819058EFEE050A8C02105570B', null, 5, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);
	 --删除绩效设置管理表
	DELETE FROM Dict_t_Suit where SUITID='F6A4E2F5QF0996DFE040E8E0200390B6';
	--添加绩效设置管理表
	insert into Dict_t_Suit (APPID, ISLEAF, LEVELNO, ORDERID, REMARK, STATUS, SUITID, SUITNAME, SUITTYPE, SUPERID)
	values ('KPI', '1', 1, 6, '绩效设置管理', '1', 'F6A4E2F5QF0996DFE040E8E0200390B6', '绩效设置管理表', '1', '0');
	--修改原菜单的parentid
	update fasp_t_pubmenu set parentid='22006' where guid in ('22008','22009','22010','22011','22012');
end;
--绩效_YLL绩效设置管理菜单

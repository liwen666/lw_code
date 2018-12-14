BEGIN

DELETE FROM fasp_t_pubmenu WHERE GUID = '32111351D47911FEE050A8C021055CA1';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('32111351D47911FEE050A8C021055CA1', 2, 1, '1', '005001016', '基本支出调整', '130001', '/bgt/exp/datainputOA/main.do?appID=BGT'||'&'||'operate=82', 16, 'remark', null, 2, null, null, 'bgt', null, null, null, null, null, null, 1);

DELETE FROM fasp_t_pubmenu WHERE GUID = '42432FDC2907BD9CE050A8C0210575E2';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('42432FDC2907BD9CE050A8C0210575E2', 2, 1, '1', '211', '数据查看(预算调整)', '130001', '/bgt/exp/datainputOA/main.do?appID=BGT'||'&'||'operate=82'||'&'||'opt=view', 0, 'remark', null, 2, null, null, 'bgt', null, null, null, null, null, null, 1);

DELETE FROM fasp_t_pubmenu WHERE GUID = '56432FDC2908BD9CE050A8C0210575E2';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('56432FDC2908BD9CE050A8C0210575E2', 2, 1, '1', '221', '预算确认(预算调整)', '130001', '/bgt/exp/datainputOA/main.do?appID=BGT'||'&'||'operate=81', 0, 'remark', null, 2, null, null, 'bgt', null, null, null, null, null, null, 1);

END;
--预算调整菜单

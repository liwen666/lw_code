BEGIN
DELETE FROM fasp_t_pubmenu where GUID IN('11200231','12000414');
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200231', 2, 1, '1', '004002003', '项目代录', '120002', '/spf/spf/proj/input/inputMain.do?style=2' || chr(38) || 'projmType=1' || chr(38) || 'dealtype=50' || chr(38) || 'spfchsdealtype=5*11' || chr(38) || 'instead=1' || chr(38) || 'attention=0' || chr(38) || 'appid=SPF' || chr(38) || 'istemp=0' || chr(38) || 'isreadonly=0' || chr(38) || 'isedit=0', 1, 'remark', null, 2, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000414', 2, 1, '1', '004004003', '二级项目代录', '120004', '/spf/spf/proj/input/inputMain.do?style=2' || chr(38) || 'projmType=2' || chr(38) || 'dealtype=P0' || chr(38) || 'spfchsdealtype=5*11' || chr(38) || 'instead=1' || chr(38) || 'attention=0' || chr(38) || 'appid=SPF' || chr(38) || 'istemp=0' || chr(38) || 'isreadonly=0' || chr(38) || 'isedit=0', 1, 'remark', null, 2, null, null, 'spf', null, null, null, null, null, null, 1);
END;

--代录菜单

BEGIN
  DELETE FROM fasp_t_pubmenu WHERE GUID IN('DD93AD73A6B04062911175AFB2577329','62E4B32357ED42409988EEC74425ED57','A8287138518F4157ACE87CC24205A12D','256D064E14684681BABC5BBB1A611640');
  insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('DD93AD73A6B04062911175AFB2577329', 2, 1, '1', '004001019', '专项资金回退选择', '120001', '/spf/spf/backspaceSelect/selectMain.do?spfmType=1'||CHR(38)||'istemp=0'||CHR(38)||'dealtype=41', 19, null, null, 1, null, null, 'spf', null, null, null, null, null, null, 1, null);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('62E4B32357ED42409988EEC74425ED57', 2, 1, '1', '004002027', '项目回退选择', '120002', '/spf/spf/proj/backspaceSelect/selectMain.do?projmType=1'||CHR(38)||'istemp=0'||CHR(38)||'dealtype=51'||CHR(38)||'samespf=0'||CHR(38)||'projstep=audit', 16, null, null, 1, null, null, 'spf', null, null, null, null, null, null, 1, null);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('A8287138518F4157ACE87CC24205A12D', 2, 1, '1', '004003008', '一级项目回退选择', '120003', '/spf/spf/backspaceSelect/selectMain.do?spfmType=2'||CHR(38)||'istemp=0'||CHR(38)||'dealtype=31', 8, null, null, 1, null, null, 'spf', null, null, null, null, null, null, 1, null);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('256D064E14684681BABC5BBB1A611640', 2, 1, '1', '004004022', '二级项目回退选择', '120004', '/spf/spf/proj/backspaceSelect/selectMain.do?projmType=2'||CHR(38)||'istemp=0'||CHR(38)||'dealtype=P1'||CHR(38)||'samespf=0'||CHR(38)||'projstep=audit
', 16, null, null, 1, null, null, 'spf', null, null, null, null, null, null, 1, null);

END;
--添加回退问选择菜单

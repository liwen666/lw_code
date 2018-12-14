 BEGIN
 DELETE FROM fasp_t_pubmenu WHERE GUID ='29481D6E981A028AE0530100007F8351';  
 insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE) 
values ('29481D6E981A028AE0530100007F8351', 2, 1, '1', '004004019', '二级项目预算调整', '120004', '/spf/spf/proj/bgtadjust/auditMain.do?istemp=0' || chr(38) || 'dealtype=P1' || chr(38) || 'samespf=1' || chr(38) || 'projstep=projbgtadjust', 2, 'remark', null, 2, null, null, 'spf', null, null, null, null, null, null, 1);
END;
--二级项目预算调整菜单

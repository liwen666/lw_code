
BEGIN
DELETE FROM fasp_t_pubmenu where GUID = '2A37B530B2658B53E050A8C021051764';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2A37B530B2658B53E050A8C021051764', 2, 1, '1', '004001013', '对下项目确认', '120001', '/spf/spf/proj/inBudget/projectAdjust/projectAdjustMain.do?dealtype=40'||chr(38) || 'step=lowerProjAudit'||chr(38) || 'spfmType=1', 1, 'remark', null, 2, null, null, 'spf', null, null, null, null, null, null, 1);

END;
--对下项目确认菜单

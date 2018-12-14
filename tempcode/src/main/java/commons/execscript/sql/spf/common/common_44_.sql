--新增专项下发设置
BEGIN
DELETE FROM fasp_t_pubmenu WHERE GUID in ('7AB2071676FE489EA6F8DE6C2C1B9869','27CCC3197B1F4D0FBEF43C083ADF07B1');
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('7AB2071676FE489EA6F8DE6C2C1B9869', 2, 1, '1', '004001012', '专项下发设置', '120001', '/spf/spf/export/spfSetting.do?dealtype=4*01', 9, 'remark', null, 2, null, null, 'spf', null, null, null, null, null, null, 1);
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('27CCC3197B1F4D0FBEF43C083ADF07B1', 2, 1, '1', '004001013', 'SPF上下级同步', '120001', '/spf/synch2/config.do', 9, null, null, 2, null, null, 'spf', null, null, null, null, null, null, 1);
END;
--专项下发设置菜单

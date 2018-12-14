BEGIN
DELETE FROM fasp_t_pubmenu WHERE GUID = 'DE10C677BFA24F2691C1A4D2F97FF8BA';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('DE10C677BFA24F2691C1A4D2F97FF8BA', 2, 1, '1', '005008015', '资金来源耦合口径设置', '114000', '/bgt/exp/fundSplit/moneyCoupling/main.do', 15, '', null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);
END;--moneyCouplingMenu

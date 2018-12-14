BEGIN
DELETE FROM fasp_t_pubmenu WHERE GUID = '4CDC73A92F8A398EE0533A06A8C009FC';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('4CDC73A92F8A398EE0533A06A8C009FC', 2, 1, '1', '005005007', '定额公式数据对比', '113000', '/bgt/exp/quotamanage/actualdatacompare.do', 7, '', null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);
END;
--16_wkn_CREAT_MENU

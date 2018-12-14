BEGIN
DELETE FROM fasp_t_pubmenu WHERE GUID = '5014B91F2DFE5ACAE0533A06A8C02064';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('5014B91F2DFE5ACAE0533A06A8C02064', 2, 1, '1', '005008021', '人员调动配置', '114000', '/bgt/exp/setting/personmoveset.do?appId=BGT', 21, '', null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

DELETE FROM fasp_t_pubmenu WHERE GUID = '5014B91F2E005ACAE0533A06A8C02064';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('5014B91F2E005ACAE0533A06A8C02064', 2, 1, '1', '003008017', '人员调动配置', '15761DCAC038390DE050A8C021050EEW', '/bas/exp/setting/personmoveset.do?appId=BAS', 17, '', null, 1, null, null, 'bas', null, null, null, null, null, null, 1);
END;

--creat_personmoveset_menu

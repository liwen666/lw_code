
BEGIN
  DELETE FROM fasp_t_pubmenu WHERE GUID = '389ADE47B3BD1425E053CB01A8C02169';
  insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
  values ('389ADE47B3BD1425E053CB01A8C02169', 2, 1, '1', '005008016', '预算退回单位选择', '114000', '/bgt/exp/oabridge/oaAgencyRelation.do?appID=BGT', 16, '', null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);

  DELETE FROM fasp_t_pubmenu WHERE GUID = '389ADE47B3BE1425E053CB01A8C02169';
  insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
  values ('389ADE47B3BE1425E053CB01A8C02169', 2, 1, '1', '003008016', '采集退回单位选择', '15761DCAC038390DE050A8C021050EEW', '/bas/exp/oabridge/oaAgencyRelation.do?appID=BAS', 16, '', null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

END;

--公文退回单位选择菜单登记

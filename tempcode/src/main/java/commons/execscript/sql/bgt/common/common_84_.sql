declare
	v_count integer;
begin

  DELETE FROM fasp_T_pubmenu WHERE GUID='14DDD0CFC65689ECE050A8C0210563FB';
  insert into FASP_T_PUBMENU (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
  values ('14DDD0CFC65689ECE050A8C0210563FB', 1, 0, '1', '005009', '通用单据', '100001FF', '#', 10000, '', '', 1, '', '', 'bgt', '0', '', '', '', '', '', 1);
  
  DELETE FROM FASP_T_PUBMENU WHERE GUID='14DDD0CFC65689ECE050A8C0210563FC';
  insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3,   PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
  values ('14DDD0CFC65689ECE050A8C0210563FC', 1, 0, '1', '005009002', '单据设置', '100001FF', '#', 10000, null, null, 2, null, null, 'bgt', null, null, null, null, null, null,1);

  DELETE FROM fasp_T_pubmenu WHERE GUID='14DDD0CFC65689ECE050A8C0210563FE';
  insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
 values ('14DDD0CFC65689ECE050A8C0210563FE', 3, 1, '1', '005009002003', '单据编码设置', '14DDD0CFC65689ECE050A8C0210563FC', '/bgt/bil/setting/codesetting.do', 1, null, null, 2, null, null, 'bgt', null, null, null, null, null, null, 1);
  
  DELETE FROM fasp_T_pubmenu WHERE GUID='14DDD0CFC65689ECE050A8C0210563FD';
  insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
  values ('14DDD0CFC65689ECE050A8C0210563FD', 3, 1, '1', '005009002001', '单据类型设置', '14DDD0CFC65689ECE050A8C0210563FC', '/bgt/bil/setting/billType.do', 1, null, null, 2, null, null, 'bgt', null, null, null, null, null, null, 1);
  
  DELETE FROM fasp_T_pubmenu WHERE GUID='14DDD0CFC65889ECE050A8C0210563FF';
  insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
  values ('14DDD0CFC65889ECE050A8C0210563FF', 3, 1, '1', '005009002002', '单据主子表设置', '14DDD0CFC65689ECE050A8C0210563FC', '/bgt/bil/relationset.do', 1, null, null, 2, null, null, 'bgt', null, null, null, null, null, null, 1);

  DELETE FROM fasp_T_pubmenu WHERE GUID='14DDD0CFC65610ECE050A8C0210563FG';
  insert into fasp_T_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
  values ('14DDD0CFC65610ECE050A8C0210563FG', 2, 0, '1', '005009001', '单据录入入口', '14DDD0CFC65689ECE050A8C0210563FB', '#', 10000, null, null, 2, null, null, 'bgt', null, null, null, null, null, null, 1);
  
end;--单据脚本4#

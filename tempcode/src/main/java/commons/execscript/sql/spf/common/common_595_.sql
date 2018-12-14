begin
  
  delete from fasp_t_pubmenu where guid in('52D9B6DFFA972D51E0533906A8C0A4AD','52D9B6DFFA982D51E0533906A8C0A4AD','28A64972D272028DE050A8C021050A79');

  insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS, ISPTADMIN)
  values ('52D9B6DFFA972D51E0533906A8C0A4AD', 2, 1, '1', '004002025', '二级项目综合查询(管理)', '120002', '/spf/project/comprePage/page.do?dealtype=ZHCX*01'||CHR(38)||'isdec=0'||CHR(38)||'appId=SPF'||CHR(38)||'itemType=1'||CHR(38)||'isDT=1'||CHR(38)||'projType=2', null, null, 1, null, null, 'spf', null, null, null, null, null, null, 1, null, null);

  insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS, ISPTADMIN)
  values ('52D9B6DFFA982D51E0533906A8C0A4AD', 2, 1, '1', '004002026', '专项项目综合查询(管理)', '120002', '/spf/project/comprePage/page.do?dealtype=ZHCX*01'||CHR(38)||'isdec=0'||CHR(38)||'appId=SPF'||CHR(38)||'itemType=1'||CHR(38)||'isDT=1'||CHR(38)||'projType=3', null, null, null, null, null, 'spf', null, null, null, null, null, null, 1, null, null);

  insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS, ISPTADMIN)
  values ('28A64972D272028DE050A8C021050A79', 2, 1, '1', '004002024', '项目综合查询(管理)', '120002', '/spf/project/comprePage/page.do?dealtype=ZHCX*01'||CHR(38)||'isdec=0'||CHR(38)||'appId=SPF'||CHR(38)||'itemType=1'||CHR(38)||'projType=2', 3, 'remark', 1, null, null, 'spf', null, null, null, null, null, null, 1, null, null);

end;
--项目综合查询添加默认(二级项目综合查询管理、专项项目综合查询管理)

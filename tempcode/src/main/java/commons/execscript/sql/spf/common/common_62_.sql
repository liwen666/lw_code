begin
 delete FROM SPF_T_PPROJSTEP WHERE GUID ='28A5D336339588FEE050A8C021050AFE';
insert into SPF_T_PPROJSTEP ( CODE, GUID, NAME, ORDERID, STATUS)
values ('projbgtadjust', '28A5D336339588FEE050A8C021050AFE', '预算调整', 26, '1');
     
 delete from fasp_t_pubmenu a where guid ='2A6528A83F97725BE0530100007F850A';
  insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
  values ('2A6528A83F97725BE0530100007F850A', 2, 1, '1', '004002013', '项目预算调整', '120002', '/spf/spf/proj/bgtadjust/auditMain.do?istemp=0'||'&'||'dealtype=51'||'&'||'samespf=0'||'&'||'projstep=projbgtadjust', 2, 'remark', null, 2, null, null, 'spf', null, null, null, null, null, null, 1);

end;
--项目预算调整插入阶段及菜单

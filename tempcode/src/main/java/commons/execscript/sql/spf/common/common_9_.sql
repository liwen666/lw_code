begin
--姚丽丽添加项目备份菜单
delete from fasp_t_pubmenu where guid in('22957CD1D93491A2E050A8C0210562F7','120002003');
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('120002003', 2, 1, '1', '004002005', '项目备份', '120002', '/spf/spf/proj/baklist/main.do?style=2'||'&'||'dealtype=53'||'&'||'appid=SPF'||'&'||'projmType=1'||'&'||'isTemp=0'||'&'||'bakLogDealType=4*53', 1, 'remark', null, 2, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22957CD1D93491A2E050A8C0210562F7', 2, 1, '1', '004004008', '二级项目备份', '120004', '/spf/spf/proj/baklist/main.do?style=2'||'&'||'projmType=2'||'&'||'dealtype=P3'||'&'||'spfchsdealtype=5*11'||'&'||'instead=0'||'&'||'attention=0'||'&'||'appid=SPF'||'&'||'istemp=0'||'&'||'isreadonly=1'||'&'||'bakLogDealType=4*53', 1, 'remark', null, 2, null, null, 'spf', null, null, null, null, null, null, 1);
end;
--yaolili

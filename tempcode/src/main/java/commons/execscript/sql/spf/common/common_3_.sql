begin
delete from fasp_t_pubmenu where guid in('12000413','11200209');
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000413', 2, 1, '1', '004004004', '二级项目删除', '120004', '/spf/spf/proj/del/page.do?projmType=2'||'&'||'dealtype=P0', 1, 'remark', null, 2, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('11200209', 2, 1, '1', '004002002', '项目删除', '120002', '/spf/spf/proj/del/page.do?projmType=1'||'&'||'dealtype=50', 1, 'remark', null, 2, null, null, 'spf', null, null, null, null, null, null, 1);
end;
--冯明华添加项目删除菜单

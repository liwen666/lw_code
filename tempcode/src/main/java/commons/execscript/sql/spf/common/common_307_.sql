
DECLARE
begin
delete from fasp_t_pubmenu where guid='BAAF20DF44B34831A79ECB5A7399232A';
 insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('BAAF20DF44B34831A79ECB5A7399232A', 2, 1, '1', '004009009', '主列表条件设置', '120010', '/spf/spf/setting/listCondiSet.do', 12, '', '', 1, '', '', 'spf', '', '', '', '', '', '', 1, '');
  
end;
--树设置加菜单

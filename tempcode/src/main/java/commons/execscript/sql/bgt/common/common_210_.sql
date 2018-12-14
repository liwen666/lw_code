begin
delete from fasp_t_pubmenu where GUID = '4DE4C23D4FF17FAEE0533A06A8C03C6E';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('4DE4C23D4FF17FAEE0533A06A8C03C6E', 2, 1, '1', '5002005', '转移支付地区设置', 'W5D11351D47911FEE050A8C021055CA1', '/bgt/exp/setting/transferPayArea.do?appID=BGT', 1, 'remark', sysdate, 1, null, null, 'bgt', null, null, null, null, null, null, 1, null);
end;--转移支付地区设置插入菜单

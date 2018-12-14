
begin
delete from fasp_t_pubmenu where GUID = '4DF5A805C7BC067AE0533A06A8C05560';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('4DF5A805C7BC067AE0533A06A8C05560', 2, 1, '1', '003001007', '转移支付地区设置', '15761DCAC038390DE050A8C021050E32', '/bas/exp/setting/transferPayArea.do?appID=BAS', 7, 'remark', 1, null, null, 'bas', null, null, null, null, null, null, 1, null);
end;--王帅_20170425_bas转移支付地区设置菜单

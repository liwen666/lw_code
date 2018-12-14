begin
delete from fasp_t_pubmenu where GUID = '51E5FD94AF393753E0533A06A8C0CDAB';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS, ISPTADMIN)
values ('51E5FD94AF393753E0533A06A8C0CDAB', 2, 1, '1', '005001021', '备份数据查看', '130001', '/bgt/exp/backupdataview/common/main.do?appID=BGT', 22, null, 1, null, null, 'bgt', null, null, null, null, null, null, 1, null, null);

delete from fasp_t_pubmenu where GUID = '5247896662735C5EE0533A06A8C094C8';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS, ISPTADMIN)
values ('5247896662735C5EE0533A06A8C094C8', 2, 1, '1', '003001008', '备份数据查看', '15761DCAC038390DE050A8C021050E32', '/bas/exp/backupdataview/common/main.do?appID=BAS', 22, null, 1, null, null, 'bas', null, null, null, null, null, null, 1, null, null);
end;
--王帅_2017-6-19_备份数据查看菜单

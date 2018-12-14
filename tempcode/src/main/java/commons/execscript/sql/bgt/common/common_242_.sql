begin
delete from fasp_t_pubmenu where guid='D7A32277A18CBDEC5076813E51C0C2D0';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK,APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS, ISPTADMIN)
values ('D7A32277A18CBDEC5076813E51C0C2D0', 2, 1, '1', '005004010', '列限制管理', '15761DCAC037390DE050A8C021050E32', '/bas/commons/secu/columnLimit.do?appID=BAS', 10, '', null, '', '', 'bas', '', '', '', '', '', '', 1, '', '');
end;
--BAS列限制菜单20170517

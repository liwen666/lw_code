begin

delete from fasp_t_pubmenu where guid ='12000412';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, 

PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('12000412', 2, 1, '1', '004004009', '二级项目倒挂', '120004', 

'/spf/spf/normalHang/getMainPage.do?dealtype=P0'||chr(38)||'appId=SPF', 15, 'remark', null, 2, null, null, 'spf', null, null, null, null, 

null, null, 1);
end;
--二级项目倒挂菜单

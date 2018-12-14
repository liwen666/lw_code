begin

delete from fasp_t_pubmenu where guid ='22957CD1D93691A2E050A8C0210562G8';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, 

PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('22957CD1D93691A2E050A8C0210562G8', 2, 1, '1', '004002009', '项目倒挂', '120002', 

'/spf/spf/normalHang/getMainPage.do?dealtype=50'||chr(38)||'appId=SPF', 16, 'remark', null, 2, null, null, 'spf', null, null, null, null, 

null, null, 1);
end;
--专项项目倒挂菜单

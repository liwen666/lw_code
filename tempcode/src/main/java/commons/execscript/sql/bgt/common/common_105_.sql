begin 
delete from fasp_t_pubmenu where guid = '31E8B9EF5BF812B7E050A8C0210550C6';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('31E8B9EF5BF812B7E050A8C0210550C6', 2, 1, '1', '003008012', '数据同步任务管理', '15761DCAC038390DE050A8C021050EEW', '/bas/synch2/timeplan/timeplan/timeplan.do', 6, 'remark', null, 1, null, null, 'bas', null, null, null, null, null, null, 1);

delete from fasp_t_pubmenu where guid = '41E8B9EF5BF812B7E050A8C0210550C6';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('41E8B9EF5BF812B7E050A8C0210550C6', 2, 1, '1', '005008014', '数据同步任务管理', '114000', '/bgt/synch2/timeplan/timeplan/timeplan.do', 6, 'remark', null, 1, null, null, 'bgt', null, null, null, null, null, null, 1);


end ;--数据同步任务管理菜单

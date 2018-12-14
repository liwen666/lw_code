BEGIN
--杜崇明新增批量申报项目
delete from fasp_t_pubmenu where guid in('2295863373578E3CE050A8C021056471','2295863373578E3CE050A8C021056472');
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2295863373578E3CE050A8C021056471', 2, 1, '1', '004002004', '批量申报项目', '120002', '/spf/spf/proj/input/inputMain.do?style=2' || chr(38) || 'projmType=1' || chr(38) || 'dealtype=50' || chr(38) || 'spfchsdealtype=5*11' || chr(38) || 'instead=0' || chr(38) || 'attention=0' || chr(38) || 'appid=SPF' || chr(38) || 'istemp=0' || chr(38) || 'isreadonly=0' || chr(38) || 'isedit=0' || chr(38) || 'isLimit=0', 1, null, null, null, null, null, 'spf', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2295863373578E3CE050A8C021056472', 2, 1, '1', '004004002', '二级项目批量申报', '120004', '/spf/spf/proj/input/inputMain.do?style=2' || chr(38) || 'projmType=2' || chr(38) || 'dealtype=P0' || chr(38) || 'spfchsdealtype=5*11' || chr(38) || 'instead=0' || chr(38) || 'attention=0' || chr(38) || 'appid=SPF' || chr(38) || 'istemp=0' || chr(38) || 'isreadonly=0' || chr(38) || 'isedit=0' || chr(38) || 'isLimit=0', 1, null, null, null, null, null, 'spf', null, null, null, null, null, null, 1);
END;
--duchongmingpiliangshenbao

BEGIN

DELETE FROM fasp_t_pubmenu WHERE GUID = 'E9CB8079705F10D80795799AD744D6DA';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('E9CB8079705F10D80795799AD744D6DA', 2, 1, '1', '005004004', '�û�����������', '110001', '/bgt/exp/secu/roleToTaskType/main.do?type=0'||chr(38)||'appID=BGT', 4, '', '', null, '', '', 'bgt', '', '', '', '', '', '', 1, '');

DELETE FROM fasp_t_pubmenu WHERE GUID = 'BCF13ED648BF18820E6D7E70C9A227F0';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('BCF13ED648BF18820E6D7E70C9A227F0', 2, 1, '1', '005004005', '��ɫ����������', '110001', '/bgt/exp/secu/roleToTaskType/main.do?type=1'||chr(38)||'appID=BGT', 5, '', '', null, '', '', 'bgt', '', '', '', '', '', '', 1, '');

DELETE FROM fasp_t_pubmenu WHERE GUID = 'A48C440E4F1F4B1799EC020C7FB5FEFC';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('A48C440E4F1F4B1799EC020C7FB5FEFC', 2, 1, '1', '005004004', '�û�����������', '15761DCAC037390DE050A8C021050E32', '/bas/exp/secu/roleToTaskType/main.do?type=0'||chr(38)||'appID=BAS', 4, '', '', null, '', '', 'bas', '', '', '', '', '', '', 1, '');

DELETE FROM fasp_t_pubmenu WHERE GUID = '11CEB8E696C3424BB003766CC673ACBE';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('11CEB8E696C3424BB003766CC673ACBE', 2, 1, '1', '005004005', '��ɫ����������', '15761DCAC037390DE050A8C021050E32', '/bas/exp/secu/roleToTaskType/main.do?type=1'||chr(38)||'appID=BAS', 5, '', '', null, '', '', 'bas', '', '', '', '', '', '', 1, '');

END;
--�û���ɫ��Ӧ��������_�˵�

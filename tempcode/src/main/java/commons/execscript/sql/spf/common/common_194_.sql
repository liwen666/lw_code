begin

 --ɾ����Чָ���˵�
  DELETE FROM fasp_t_pubmenu where guid in ('2200903', '2200904');

--���ż�Чָ���˵�
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('2200903', 2, 1, '1', '006006003', '���ż�Чָ���', '22009', '/spf/kpi/setting/projkpi.do?kType=A3', 3, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1, null);

--������Чָ���˵�
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('2200904', 2, 1, '1', '006006004', '������Чָ���', '22009', '/spf/kpi/setting/projkpi.do?kType=A4', 4, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1, null);

end;

--��Ч_20160621_YLL��Чָ���˵�

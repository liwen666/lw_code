begin
	 --ɾ����Ч�ۺϲ�ѯ�˵�
  	DELETE FROM fasp_t_pubmenu where guid in ('2200108','220010801','2200307','220030701','2200508','220050801','2200407','220040701');
	 --��Ӽ�Ч�ۺϲ�ѯ�˵�
	
--��Ŀ��Ч�ۺϲ�ѯ 2200108 006001006   
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200108', 2, 0, '1', '006001006', '��Ŀ��Ч�ۺϲ�ѯ����', '22001', null, 33, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220010801', 3, 1, '1', '006001006001', '��Ŀ��Ч�ۺϲ�ѯ', '2200108', '/spf/kpi/intequery/comprehsv.do?dealtype=KP51&appID=KPI&projmType=1&isReadOnly=1', 1, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);

--ר�Ч�ۺϲ�ѯ 2200307 006002005
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200307', 2, 0, '1', '006002005', 'ר�Ч�ۺϲ�ѯ����', '22003', null, 64, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220030701', 3, 1, '1', '006002005001', 'ר�Ч�ۺϲ�ѯ', '2200307', '/spf/kpi/intequery/comprehsv.do?dealtype=KF41&appID=KPI&spfmType=1&isReadOnly=1', 1, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);

--������Ч�ۺϲ�ѯ 2200508  006004005
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200508', 2, 0, '1', '006004005', '������Ч�ۺϲ�ѯ����', '22005', null, 5, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220050801', 3, 1, '1', '006004005001', '������Ч�ۺϲ�ѯ', '2200508', '/spf/kpi/intequery/comprehsv.do?dealtype=71&appID=KPI&kpiType=1&isReadOnly=1', 1, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);

--���ż�Ч�ۺϲ�ѯ 2200407 006003007
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('2200407', 2, 0, '1', '006003007', '���ż�Ч�ۺϲ�ѯ����', '22004', null, 85, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);

insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('220040701', 3, 1, '1', '006003007001', '���ż�Ч�ۺϲ�ѯ', '2200407', '/spf/kpi/intequery/comprehsv.do?dealtype=61&appID=KPI&kpiType=1&isReadOnly=1', 1, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);

end;--��Ч_20160621_YLL��Ч�ۺϲ�ѯ�˵�

begin
	 --ɾ����Ч���ù���˵�
  	DELETE FROM fasp_t_pubmenu where guid='22006';
	 --��Ӽ�Ч���ù���˵�
	insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
	values ('22006', 1, 0, '1', '006000', '��Ч���ù���', '092022D819058EFEE050A8C02105570B', null, 5, 'remark', null, 1, null, null, 'kpi', null, null, null, null, null, null, 1);
	 --ɾ����Ч���ù����
	DELETE FROM Dict_t_Suit where SUITID='F6A4E2F5QF0996DFE040E8E0200390B6';
	--��Ӽ�Ч���ù����
	insert into Dict_t_Suit (APPID, ISLEAF, LEVELNO, ORDERID, REMARK, STATUS, SUITID, SUITNAME, SUITTYPE, SUPERID)
	values ('KPI', '1', 1, 6, '��Ч���ù���', '1', 'F6A4E2F5QF0996DFE040E8E0200390B6', '��Ч���ù����', '1', '0');
	--�޸�ԭ�˵���parentid
	update fasp_t_pubmenu set parentid='22006' where guid in ('22008','22009','22010','22011','22012');
end;
--��Ч_YLL��Ч���ù���˵�

begin
  --˫�� Ԥ��ȷ��
delete from fasp_t_pubmenu where guid='113144';
insert into FASP_T_PUBMENU (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('113144', 2, 1, '1', '005001011', 'Ԥ��ȷ��', '130001', '/bgt/exp/datainputOA/main.do?appID=BGT'||CHR(38)||'operate=71', 11, 'remark', null, 1, null, null, 'bgt', null, null, null, null, null, null, 1, null);

  --���� Ԥ��ȷ��
delete from fasp_t_pubmenu where guid='2DD1F5167A0AF317E050A8C0210536F8';
insert into FASP_T_PUBMENU (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE, ALIAS)
values ('2DD1F5167A0AF317E050A8C0210536F8', 2, 1, '1', '005001012', 'Ԥ��ȷ��_���ģʽ', '130001', '/bgt/exp/datainputOA/main.do?appID=BGT'||CHR(38)||'operate=61', 12, 'remark', null, 1, null, null, 'bgt', null, null, null, null, null, null, 1, null);

end;
--Ԥ��ȷ�ϲ˵�

begin
  
  delete from dict_t_dealtype where dealid in('A3','A4','A5');
  --���    �����ɹ�Ԥ���ר��һ��¼�����Ŀһ��¼���     ��������
  insert into dict_t_dealtype (appid, dealid, dealname, orderid, needconfig, isunique)
  values ('SPF', 'A3', '�����ɹ�Ԥ���', 83, '0', '0');

  insert into dict_t_dealtype (appid, dealid, dealname, orderid, needconfig, isunique)
  values ('SPF', 'A4', 'ר��һ��¼���', 84, '0', '0');

  insert into dict_t_dealtype (appid, dealid, dealname, orderid, needconfig, isunique)
  values ('SPF', 'A5', '��Ŀһ��¼���', 85, '0', '0');

  /*--�޸�    �����ɹ�Ԥ���  ��������Ϊ����������
  update p#dict_t_model set dealtype = 'A3' where tableid = 'B4448D1B9474427BB96D93AD4F2694BE'and appid = 'SPF';
  */
  --���   �����ɹ�Ԥ���ȱʡ��
  delete from dict_t_defaultcol where  dealid ='A3';
  
  insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
  values ('A3', 1, '�б�־', 'DATAKEY', 3, 32, null, null, '1', '1', '5054681C8F4442DCE0533906A8C05624', '1', 'sys_guid()', null, '080001', '0', '0');

  insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
  values ('A3', 2, '��ĿΨһ��ʶ', 'PROJECTID', 3, 32, null, null, '0', '1', '5054681C8F4542DCE0533906A8C05624', '1', null, '1', '10804', '0', '0');
  
  --���   ר��һ��¼���ȱʡ��
  delete from dict_t_defaultcol where  dealid ='A4';
  
  insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
  values ('A4', 1, '�б�־', 'DATAKEY', 3, 32, null, null, '1', '0', '5055E82B3C454BD9E0533906A8C0B80E', '1', 'sys_guid()', null, '080001', '0', '0');

  insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
  values ('A4', 2, 'ר��Ψһ��ʶ', 'SPFID', 3, 32, null, null, '0', '1', '5055E82B3C464BD9E0533906A8C0B80E', '1', null, null, '10801', '0', '0');

  --���   ��Ŀһ��¼���ȱʡ��
  delete from dict_t_defaultcol where  dealid ='A5';
  
  insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
  values ('A5', 1, '�б�־', 'DATAKEY', 3, 32, null, null, '1', '1', '5055E82B3C474BD9E0533906A8C0B80E', '1', 'sys_guid()', null, '080001', '0', '0');

  insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
  values ('A5', 2, '��ĿΨһ��ʶ', 'PROJECTID', 3, 32, null, null, '0', '1', '5055E82B3C484BD9E0533906A8C0B80E', '1', null, null, '10804', '0', '0');

end;
--������������(ר��һ��¼�����Ŀһ��¼��������ɹ�Ԥ���)

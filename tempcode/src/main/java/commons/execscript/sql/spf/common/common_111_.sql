BEGIN
  DELETE FROM dict_t_billproc WHERE procid ='P_TRANSPOSE_ZCXMMXB_SPF';
  insert into dict_t_billproc (APPID,PROCID, PROCNAME, STATUS)
  values ('SPF', 'P_TRANSPOSE_ZCXMMXB_SPF', '��Ŀ֧����Դ��ת��', '1');
  DELETE FROM Dict_t_Billcols WHERE GUID ='2A20C8AG4GCED2BDE050C8C021055647';
  insert into Dict_t_Billcols (COLUMNNAME, DBCOLNAME,GUID, ORDERID, STATUS)
  values ('���', 'YEAR', '2A20C8AG4GCED2BDE050C8C021055647', null, '1');
END;
--BillProc

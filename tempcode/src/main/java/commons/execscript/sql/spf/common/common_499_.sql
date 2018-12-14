begin
  
  delete from dict_t_dealtype where dealid in('A3','A4','A5');
  --添加    政府采购预算表、专项一般录入表、项目一般录入表     表处理类型
  insert into dict_t_dealtype (appid, dealid, dealname, orderid, needconfig, isunique)
  values ('SPF', 'A3', '政府采购预算表', 83, '0', '0');

  insert into dict_t_dealtype (appid, dealid, dealname, orderid, needconfig, isunique)
  values ('SPF', 'A4', '专项一般录入表', 84, '0', '0');

  insert into dict_t_dealtype (appid, dealid, dealname, orderid, needconfig, isunique)
  values ('SPF', 'A5', '项目一般录入表', 85, '0', '0');

  /*--修改    政府采购预算表  表处理类型为：特殊类型
  update p#dict_t_model set dealtype = 'A3' where tableid = 'B4448D1B9474427BB96D93AD4F2694BE'and appid = 'SPF';
  */
  --添加   政府采购预算表缺省列
  delete from dict_t_defaultcol where  dealid ='A3';
  
  insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
  values ('A3', 1, '行标志', 'DATAKEY', 3, 32, null, null, '1', '1', '5054681C8F4442DCE0533906A8C05624', '1', 'sys_guid()', null, '080001', '0', '0');

  insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
  values ('A3', 2, '项目唯一标识', 'PROJECTID', 3, 32, null, null, '0', '1', '5054681C8F4542DCE0533906A8C05624', '1', null, '1', '10804', '0', '0');
  
  --添加   专项一般录入表缺省列
  delete from dict_t_defaultcol where  dealid ='A4';
  
  insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
  values ('A4', 1, '行标志', 'DATAKEY', 3, 32, null, null, '1', '0', '5055E82B3C454BD9E0533906A8C0B80E', '1', 'sys_guid()', null, '080001', '0', '0');

  insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
  values ('A4', 2, '专项唯一标识', 'SPFID', 3, 32, null, null, '0', '1', '5055E82B3C464BD9E0533906A8C0B80E', '1', null, null, '10801', '0', '0');

  --添加   项目一般录入表缺省列
  delete from dict_t_defaultcol where  dealid ='A5';
  
  insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
  values ('A5', 1, '行标志', 'DATAKEY', 3, 32, null, null, '1', '1', '5055E82B3C474BD9E0533906A8C0B80E', '1', 'sys_guid()', null, '080001', '0', '0');

  insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
  values ('A5', 2, '项目唯一标识', 'PROJECTID', 3, 32, null, null, '0', '1', '5055E82B3C484BD9E0533906A8C0B80E', '1', null, null, '10804', '0', '0');

end;
--新增表处理类型(专项一般录入表、项目一般录入表、政府采购预算表)

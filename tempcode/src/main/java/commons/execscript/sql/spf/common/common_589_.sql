BEGIN
  DELETE FROM DICT_T_DEFAULTCOL WHERE dealid ='4*05';
 
  insert into DICT_T_DEFAULTCOL (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE) 
  VALUES ('4*05', 1, '�б�־', 'DATAKEY', 3, 32, 0, null, '1', '1', '1', 'sys_guid()', '1', '080001', '0', '0');
END;
--�޸���ǰsql����ɾ��4*05������--�޸���ǰsql����ɾ��405������
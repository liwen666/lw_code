BEGIN
  
DELETE FROM dict_t_defaultcol WHERE GUID ='29BF7298290F1466E0530100007F9A7E';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS)
values ('ZHCX*01', 1, 'Ψһ����', 'DATAKEY', 3, 32, null, null, '1', null, '29BF7298290F1466E0530100007F9A7E', '1');

DELETE FROM dict_t_dealtype WHERE dealid='ZHCX*01' AND appid ='SPF';
insert into dict_t_dealtype (APPID, DEALID, DEALNAME, ORDERID, NEEDCONFIG)
values ('SPF', 'ZHCX*01', '�ۺϲ�ѯ��ͼ', 1001, '0');
end;

--�ۺ���ͼdealtype��ȱʡ��
--�ۺ���ͼdealtype��ȱʡ��

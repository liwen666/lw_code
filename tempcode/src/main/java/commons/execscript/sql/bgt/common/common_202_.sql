begin
DELETE FROM DICT_T_DEALTYPE WHERE DEALID='A0*06' AND  APPID='BGT';
INSERT INTO DICT_T_DEALTYPE (DEALID, DEALNAME, APPID, ORDERID, NEEDCONFIG)
VALUES ('A0*06', 'ת����֧�������', 'BGT', 999, '0');


DELETE FROM DICT_T_DEFAULTCOL WHERE DEALID='A0*06' AND GUID IN('4BC772D978B062ADE0533A06A8C0B57A','4BC772D978B162ADE0533A06A8C0B57A','4BC772D978B262ADE0533A06A8C0B57A','4BC772D978B362ADE0533A06A8C0B57A','4BC772D978B462ADE0533A06A8C0B57A','4BC772D978B562ADE0533A06A8C0B57A');
insert into DICT_T_DEFAULTCOL (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*06', 1, '�б�־', 'DATAKEY', 3, 32, null, '', '1', '1', '4BC772D978B062ADE0533A06A8C0B57A', '1', sysdate, 'sys_guid()', '', '080001', '0', '0');

insert into DICT_T_DEFAULTCOL (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*06', 2, '�������', 'ORDERID', 1, 9, 0, '', '0', '1', '4BC772D978B162ADE0533A06A8C0B57A', '1', sysdate, '', '', '', '0', '0');

insert into DICT_T_DEFAULTCOL (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*06', 3, 'ˢ�±�־', 'NEEDUPDATE', 3, 4000, null, '', '0', '1', '4BC772D978B262ADE0533A06A8C0B57A', '1', sysdate, '', '', '', '0', '0');

insert into DICT_T_DEFAULTCOL (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*06', 4, '����', 'FINYEAR', 3, 4, null, '', '0', '1', '4BC772D978B362ADE0533A06A8C0B57A', '1', sysdate, '', '', '10206', '0', '0');

insert into DICT_T_DEFAULTCOL (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*06', 5, 'Ԥ�㵥λ', 'AGENCYID', 3, 32, null, '', '1', '1', '4BC772D978B462ADE0533A06A8C0B57A', '1', sysdate, '', '', '10401', '0', '0');

insert into DICT_T_DEFAULTCOL (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*06', 6, 'ר��ID', 'SPFID', 3, 32, null, '2763D47FA30301AEE0530A80013501AE', '0', '1', '4BC772D978B562ADE0533A06A8C0B57A', '1', sysdate, '', '', '', '0', '0');
end;
--��ϲ÷_20170412_ת��֧�������
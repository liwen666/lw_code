--������������ҵ�����ȱʡ��
BEGIN
DELETE DICT_T_DEFAULTCOL WHERE DEALID = '2105';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, DEID)
values ('2105', 1, '�б�ʶ', 'DATAKEY', 3, 32, null, '1', '1', '2A655E3A1198735BE0530100007FFC01', '1', 'SYS_GUID()', '080001');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, DEID)
values ('2105', 2, '��ĿID', 'PROJECTID', 3, 32, null, null, '1', '2A655E3A1199735BE0530100007FFC01', '1', null, null);

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, DEID)
values ('2105', 3, '��Ŀ����', 'PROJECTNAME', 3, 200, null, null, '1', '2A655E3A119A735BE0530100007FFC01', '1', null, null);

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, DEID)
values ('2105', 4, '��', 'FINYEAR', 3, 32, '9786827170284907A734AF1107A3B65D', null, '1', '2A655E3A119B735BE0530100007FFC01', '1', null, null);

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, DEID)
values ('2105', 5, 'ר��ID', 'SPFID', 3, 32, null, null, '1', '2A655E3A119C735BE0530100007FFC01', '1', null, null);

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, DEID)
values ('2105', 6, '���ܿ�Ŀ', 'EXPFUNCID', 3, 32, '19EAEBD200754DBC9E40C85279920B53', null, '1', '2A655E3A119D735BE0530100007FFC01', '1', null, null);

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, DEID)
values ('2105', 7, '���ÿ�Ŀ', 'EXPECOID', 3, 32, '2C752C92CBCF4D78B5C89C0F31DA1FD0', null, '1', '2A655E3A119E735BE0530100007FFC01', '1', null, null);

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, DEID)
values ('2105', 8, '��Ŀ����ID', 'PROJTYPEID', 3, 32, null, '0', '1', '2A655E3A119F735BE0530100007FFC01', '1', null, null);
END;
--ZZK_160128_������������ҵ�����ȱʡ��
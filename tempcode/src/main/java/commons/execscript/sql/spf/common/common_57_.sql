BEGIN
SYS_P_ADD_PARTITION_TRIGGER('P#DICT_T_APPREGISTER');
DELETE FROM DICT_T_APPREGISTER;
insert into DICT_T_APPREGISTER (APPID, APPNAME, DBID, ORDERID, STATUS)
values ('SPF', '��Ŀ����', 'EF9941E930ECC27EE040A8C0200359F4', 1, '1');

insert into DICT_T_APPREGISTER (APPID, APPNAME, DBID, ORDERID, STATUS)
values ('BGT', 'Ԥ�����', '146FB9F2380ACB42E050A8C021052E93', 3, '1');

insert into DICT_T_APPREGISTER (APPID, APPNAME, DBID, ORDERID, STATUS)
values ('KPI', '��Ч����', '146FB9F23810CB42E050A8C021052E93', 9, '1');

insert into DICT_T_APPREGISTER (APPID, APPNAME, DBID, ORDERID, STATUS)
values ('BAS', 'Ԥ��������ݹ���', '1465EA8098ED34A0E050A8C02105185E', 0, '1');
END;
--DICT_T_APPREGISTER
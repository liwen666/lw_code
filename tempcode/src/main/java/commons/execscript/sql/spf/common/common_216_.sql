BEGIN
DELETE FROM dict_t_settingtabinfo WHERE GUID ='2C7B42071382A5B1E050A8C021051321';
insert into dict_t_settingtabinfo (GUID, DBTABLENAME, NAME, REMARK, TABSWHERE, APPID, PROVINCE, YEAR, STATUS, YEARPART, TASKPART, PROVINCEPART, INITIALIZE, ISLEAD, CHANGEDISTRICT)
values ('2C7B42071382A5B1E050A8C021051321', 'P#SPF_T_DATAACCOUNTLOG', '记帐增量', null, null, '*', '1500', '2016', '1', '0', '0', '0', '0', '0', null);
END;
--增量记帐表

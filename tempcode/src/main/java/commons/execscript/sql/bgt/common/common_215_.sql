begin
delete from dict_t_defaultcol where DEALID = 'A0*07' and GUID = '4DE60D38B56210C5E0533A06A8C00304';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*07', 1, '行标志', 'DATAKEY', 3, 32, null, null, '1', '1', '4DE60D38B56210C5E0533A06A8C00304', '1', sysdate, 'sys_guid()', null, '080001', '0', '0');

delete from dict_t_defaultcol where DEALID = 'A0*07' and GUID = '4DE60D38B56310C5E0533A06A8C00304';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*07', 2, '排序序号', 'ORDERID', 1, 9, 0, null, '0', '1', '4DE60D38B56310C5E0533A06A8C00304', '1', sysdate, null, null, null, '0', '0');

delete from dict_t_defaultcol where DEALID = 'A0*07' and GUID = '4DE64E85D54C1399E0533A06A8C044A6';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*07', 3, '刷新标志', 'NEEDUPDATE', 3, 4000, null, null, '0', '1', '4DE64E85D54C1399E0533A06A8C044A6', '1', sysdate, null, null, null, '0', '0');

delete from dict_t_defaultcol where DEALID = 'A0*07' and GUID = '4DE64E85D54D1399E0533A06A8C044A6';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*07', 4, '财年', 'FINYEAR', 3, 4, null, null, '0', '1', '4DE64E85D54D1399E0533A06A8C044A6', '1', sysdate, null, null, '10206', '0', '0');

delete from dict_t_defaultcol where DEALID = 'A0*07' and GUID = '4DE64E85D54E1399E0533A06A8C044A6';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*07', 5, '预算单位', 'AGENCYID', 3, 32, null, null, '1', '1', '4DE64E85D54E1399E0533A06A8C044A6', '1', sysdate, null, null, '10401', '0', '0');

delete from dict_t_defaultcol where DEALID = 'A0*08' and GUID = '4DE6C6D9F9F11842E0533A06A8C0A097';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*08', 1, '行标志', 'DATAKEY', 3, 32, null, null, '1', '1', '4DE6C6D9F9F11842E0533A06A8C0A097', '1', sysdate, 'sys_guid()', null, '080001', null, null);

delete from dict_t_defaultcol where DEALID = 'A0*08' and GUID = '4DE6C6D9F9F21842E0533A06A8C0A097';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*08', 2, '排序序号', 'ORDERID', 1, 9, 0, null, '0', '1', '4DE6C6D9F9F21842E0533A06A8C0A097', '1', sysdate, null, null, null, null, null);

delete from dict_t_defaultcol where DEALID = 'A0*08' and GUID = '4DE6C6D9F9F31842E0533A06A8C0A097';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*08', 3, '刷新标志', 'NEEDUPDATE', 3, 4000, null, null, '0', '1', '4DE6C6D9F9F31842E0533A06A8C0A097', '1', sysdate, null, null, null, null, null);

delete from dict_t_defaultcol where DEALID = 'A0*08' and GUID = '4DE6C6D9F9F41842E0533A06A8C0A097';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*08', 4, '财年', 'FINYEAR', 3, 4, null, null, '0', '1', '4DE6C6D9F9F41842E0533A06A8C0A097', '1', sysdate, null, null, '10206', null, null);

delete from dict_t_defaultcol where DEALID = 'A0*08' and GUID = '4DE6C6D9F9F51842E0533A06A8C0A097';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*08', 5, '预算单位', 'AGENCYID', 3, 32, null, null, '1', '1', '4DE6C6D9F9F51842E0533A06A8C0A097', '1', sysdate, null, null, '10401', null, null);

delete from dict_t_defaultcol where DEALID = 'A0*09' and GUID = '4DE6878E4BDA1629E0533A06A8C09080';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*09', 1, '行标志', 'DATAKEY', 3, 32, null, null, '1', '1', '4DE6878E4BDA1629E0533A06A8C09080', '1', sysdate, 'sys_guid()', null, '080001', null, null);

delete from dict_t_defaultcol where DEALID = 'A0*09' and GUID = '4DE6878E4BDB1629E0533A06A8C09080';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*09', 2, '排序序号', 'ORDERID', 1, 9, 0, null, null, '1', '4DE6878E4BDB1629E0533A06A8C09080', '1', sysdate, null, null, null, null, null);

delete from dict_t_defaultcol where DEALID = 'A0*09' and GUID = '4DE6878E4BDC1629E0533A06A8C09080';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*09', 3, '刷新标志', 'NEEDUPDATE', 3, 4000, null, null, null, '1', '4DE6878E4BDC1629E0533A06A8C09080', '1', sysdate, null, null, null, null, null);

delete from dict_t_defaultcol where DEALID = 'A0*09' and GUID = '4DE6878E4BDD1629E0533A06A8C09080';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*09', 4, '财年', 'FINYEAR', 3, 4, null, null, null, '1', '4DE6878E4BDD1629E0533A06A8C09080', '1', sysdate, null, null, '10206', null, null);

delete from dict_t_defaultcol where DEALID = 'A0*09' and GUID = '4DE6878E4BDE1629E0533A06A8C09080';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*09', 5, '预算单位', 'AGENCYID', 3, 32, null, null, '1', '1', '4DE6878E4BDE1629E0533A06A8C09080', '1', sysdate, null, null, '10401', null, null);

delete from dict_t_defaultcol where DEALID = 'A0*10' and GUID = '4DE71C69FB011BC6E0533A06A8C023AD';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*10', 1, '行标志', 'DATAKEY', 3, 32, null, null, '1', '1', '4DE71C69FB011BC6E0533A06A8C023AD', '1', sysdate, 'sys_guid()', null, '080001', '0', '0');

delete from dict_t_defaultcol where DEALID = 'A0*10' and GUID = '4DE71C69FB021BC6E0533A06A8C023AD';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*10', 2, '排序序号', 'ORDERID', 1, 9, 0, null, null, '1', '4DE71C69FB021BC6E0533A06A8C023AD', '1', sysdate, null, null, null, '0', null);

delete from dict_t_defaultcol where DEALID = 'A0*10' and GUID = '4DE71C69FB031BC6E0533A06A8C023AD';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*10', 3, '刷新标志', 'NEEDUPDATE', 3, 4000, null, null, null, '1', '4DE71C69FB031BC6E0533A06A8C023AD', '1', sysdate, null, null, null, '0', null);

delete from dict_t_defaultcol where DEALID = 'A0*10' and GUID = '4DE71C69FB041BC6E0533A06A8C023AD';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*10', 4, '财年', 'FINYEAR', 3, 4, null, null, null, '1', '4DE71C69FB041BC6E0533A06A8C023AD', '1', sysdate, null, null, '10206', null, null);

delete from dict_t_defaultcol where DEALID = 'A0*10' and GUID = '4DE71C69FB051BC6E0533A06A8C023AD';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*10', 5, '预算单位', 'AGENCYID', 3, 32, null, null, '1', '1', '4DE71C69FB051BC6E0533A06A8C023AD', '1', sysdate, null, null, '10401', null, null);
end;


--添加转移支付表默认列

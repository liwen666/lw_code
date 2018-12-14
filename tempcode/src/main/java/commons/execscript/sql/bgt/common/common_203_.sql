begin
--表类型
DELETE FROM dict_t_dealtype WHERE DEALID = 'A0*05' AND APPID='BAS' ;
insert into dict_t_dealtype (APPID, DEALID, DEALNAME, ORDERID, NEEDCONFIG, ISUNIQUE)
values ('BAS', 'A0*05', '转移支付指标表', 999, '0', '0');



--缺省列
DELETE FROM dict_t_defaultcol WHERE DEALID = 'A0*05' 
AND GUID IN( '4B3CF61C6D507F78E0533A06A8C01677','4B3D2D1DBBA302BCE0533A06A8C0C503','4B3D2D1DBBA402BCE0533A06A8C0C503','4B3D2D1DBBA502BCE0533A06A8C0C503','4B3D2D1DBBA602BCE0533A06A8C0C503', '4B3D2D1DBBA702BCE0533A06A8C0C503', '4B3D2D1DBBA802BCE0533A06A8C0C503');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*05', 1, '行标志', 'DATAKEY', 3, 32, null, null, '1', '1', '4B3CF61C6D507F78E0533A06A8C01677', '1', sysdate, 'sys_guid()', null, '080001', null, null);

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*05', 2, '排序序号', 'ORDERID', 1, 9, 0, null, '0', '1', '4B3D2D1DBBA302BCE0533A06A8C0C503', '1', sysdate, null, null, null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*05', 3, '刷新标志', 'NEEDUPDATE', 3, 4000, null, null, '0', '1', '4B3D2D1DBBA402BCE0533A06A8C0C503', '1', sysdate, null, null, null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*05', 4, '财年', 'FINYEAR', 3, 4, null, null, '0', '1', '4B3D2D1DBBA502BCE0533A06A8C0C503', '1', sysdate, null, null, '10206', '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*05', 5, '预算单位', 'AGENCYID', 3, 32, null, null, '1', '1', '4B3D2D1DBBA602BCE0533A06A8C0C503', '1', sysdate, null, null, '10401', '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*05', 6, '任务', 'BTASKID', 3, 32, null, null, '0', '1', '4B3D2D1DBBA702BCE0533A06A8C0C503', '1', sysdate, null, null, null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*05', 7, '金额', 'AMT', 1, 18, 4, null, '0', '1', '4B3D2D1DBBA802BCE0533A06A8C0C503', '1', sysdate, null, null, null, '0', '0');

end;--杨喜梅_20170322_转移支付指标_表类型和缺省列

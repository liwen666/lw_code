begin
delete from dict_t_dealtype where APPID = 'BGT' and dealid = 'A0*04';
insert into dict_t_dealtype (APPID, DEALID, DEALNAME, ORDERID, NEEDCONFIG, ISUNIQUE)
values ('BGT', 'A0*04', '控制数表', 1210, '0', '1');


delete from dict_t_defaultcol where guid = '233BD91D6EAC7D98E050A8C021054B4D';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE) 
values ('A0*04', 1, '行标志', 'DATAKEY', 3, 32, 0, null, '1', '1', '233BD91D6EAC7D98E050A8C021054B4D', '1', sysdate(), 'sys_guid()', '0', '080001', '0', '0');

delete from dict_t_defaultcol where guid = '233BD91D6EAE7D98E050A8C021054B4D';                                                                                                                                                                                                              
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE) 
values ('A0*04', 2, '排序序号', 'ORDERID', 1, 9, 0, null, '0', '1', '233BD91D6EAE7D98E050A8C021054B4D', '1', sysdate(), null, '0', null, '0', '0');

delete from dict_t_defaultcol where guid = '233BD91D6EA47D98E050A8C021054B4D';                                                                                                                                                                                                            
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE) 
values ('A0*04', 3, '刷新标志', 'NEEDUPDATE', 3, 400, 0, null, '0', '1', '233BD91D6EA47D98E050A8C021054B4D', '1', sysdate(), null, '0', null, '0', '0');

delete from dict_t_defaultcol where guid = '233BD91D6EAD7D98E050A8C021054B4D';                                                                                                                                                                                                               
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE) 
values ('A0*04', 4, '财年', 'FINYEAR', 3, 4, 0, null, '0', '1', '233BD91D6EAD7D98E050A8C021054B4D', '1', sysdate(), null, '0', null, '0', '0');
            
delete from dict_t_defaultcol where guid = '233BD91D6EAB7D98E050A8C021054B4D';                                                                                                                                                                                                         
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE) 
values ('A0*04', 5, '预算单位', 'AGENCYID', 3, 32, 0, null, '0', '1', '233BD91D6EAB7D98E050A8C021054B4D', '1', sysdate(), null, '0', null, '0', '0');

delete from dict_t_defaultcol where guid = '987BD91D6EAB7D98E050A8C021054B4D';                                                                                                                                                                                                         
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE) 
values ('A0*04', 6, '财政处室ID', 'FINANCE_ID', 3, 32, 0, null, '0', '1', '987BD91D6EAB7D98E050A8C021054B4D', '1', sysdate(), null, '0', null, '0', '0');

delete from dict_t_defaultcol where guid = '666BD91D6EAB7D98E050A8C021054B4D';                                                                                                                                                                                                         
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE) 
values ('A0*04', 7, '部门ID', 'DEPT_ID', 3, 32, 0, null, '0', '1', '666BD91D6EAB7D98E050A8C021054B4D', '1', sysdate(), null, '0', null, '0', '0');

delete from dict_t_defaultcol where guid = '23DB905CE83D4EE3B41257568DF98B28';                                                                                                                                                 
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE) 
values ('A0*04', 9, '专项唯一标识', 'SPFID', 3, 32, 0, null, '0', '1', '23DB905CE83D4EE3B41257568DF98B28', '1', sysdate(), null, '0', null, '0', '0');

delete from dict_t_defaultcol where guid = '23096E712E0B73A7E050A8C02105191A';                                                                                                                                                                                                           
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE) 
values ('A0*04', 10, '项目唯一标识', 'PROJECTID', 3, 32, 0, null, '0', '1', '23096E712E0B73A7E050A8C02105191A', '1', sysdate(), null, '0', null, '0', '0');

delete from dict_t_defaultcol where guid = '1238F696D989691DE050A8C02105513A';
insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('A0*04', 11, '关联ID', 'RELAID', 3, 32, 0, null, '0', '0', '1238F696D989691DE050A8C02105513A', '1',sysdate(), null, '0', null, '0', '0');

end;


--14-20170307控制数表-初始化数据

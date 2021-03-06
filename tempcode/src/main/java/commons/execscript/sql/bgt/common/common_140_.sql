begin

delete from dict_t_defaultcol where DEALID = 'D0';

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 1, '行标志', 'DATAKEY', 3, 32, 0, null, '1', '1', '1707BA565B0FE97DE050A8C021055633', '1', sysdate(), null, '0', '080001', '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 2, '项目名称', 'SPFNAME', 3, 380, null, null, '0', '1', '64CD8232961D4D4191BD692F0D1675B4', '1', sysdate(), null, '0', null, '1', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 3, '刷新标志', 'NEEDUPDATE', 3, 4000, 0, null, '0', '1', '1707BA565B12E97DE050A8C021055633', '1', sysdate(), null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 3, '排序序号', 'ORDERID ', 1, 9, 0, null, '0', '1', '1707BA565B10E97DE050A8C021055633', '1', sysdate(), null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 3, '单位编码', 'AGENCYCODE', 3, 200, null, null, '0', '1', '183E15B659D54D73A0CEAEE5E8C8E9DF', '1', sysdate(), null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 4, '财年', 'FINYEAR', 3, 4, 0, null, '0', '1', '1707BA565B11E97DE050A8C021055633', '1', sysdate(), null, '0', '10206', '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 5, '单位名称', 'AGENCYNAME', 3, 457, null, null, '0', '1', '8DD234CF644F49F49C0D0AEBF0760480', '1', sysdate(), null, '0', null, '1', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 6, '预算单位', 'AGENCYID', 3, 32, 0, null, '0', '1', '1707BA565B0AE97DE050A8C021055633', '1', sysdate(), null, '0', '10401', '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 10, '专项唯一标识', 'SPFID', 3, 32, null, null, '0', '1', 'B5AA13B57F53400E84365948CCF51AE8', '1', sysdate(), null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 12, '专项编码', 'SPFCODE', 3, 186, null, null, '0', '1', '3B939CBA40104AB89972119B68323015', '1', sysdate(), null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 14, '预算金额', 'BUDGETNUM', 2, 18, 2, null, '0', '1', 'F1EBCCE63CEC4FCAB82E6A337BE03BA7', '1', sysdate(), null, '0', null, '1', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 15, '项目类型', 'PROJTYPEID', 3, 32, null, null, '0', '1', 'EE32DA12AD2A448593B21952525551DB', '1', sysdate(), null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 16, '数据唯一标识', 'DATAID', 3, 41, null, null, '0', '1', '1A040995306B4A48A479FF8EB0695F2D', '1', sysdate(), null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 17, '上级编码', 'SUPERID', 3, 39, null, null, '0', '1', '4B6B2519A1144777A93313667F173B9C', '1', sysdate(), null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 18, '层次码', 'LEVELNO', 1, 0, 0, null, '0', '1', 'D509F007DA1D46EC80FE4E1F42F0E112', '1', sysdate(), null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 19, '项目类别编码', 'PROJTYPECODE', 3, 200, null, null, '0', '1', '0ED9409A834C46C8A3431B1B0AE977C8', '1', sysdate(), null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 20, 'ISSPF', 'ISSPF', 3, 1, null, null, '0', '1', '41713A164BE04284BAC3B7ACF8408556', '1', sysdate(), null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 24, '项目编码', 'PROJCODE', 3, 60, null, null, '0', '1', '37A642C2C1DE4345AE74EF258ED3999B', '1', sysdate(), null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 25, '数据状态', 'DATAFLAG', 3, 1, null, null, '0', '1', 'CF435D8AFA72498BA9FF9F55EF1D367E', '1', sysdate(), null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DBVERSION, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('D0', 26, 'FUNDMANAGE', 'FUNDMANAGE', 3, 32, null, null, '0', '1', '7645CF60A00942F8A8BB18E0842D1E8A', '1', sysdate(), null, '0', null, '0', '0');


end;--聚合 脚本1 初始化表dict_t_defaultcol D0的数据 

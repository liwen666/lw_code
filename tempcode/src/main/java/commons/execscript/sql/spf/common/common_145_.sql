BEGIN
delete from dict_t_defaultcol where dealid in('4*04','4*15','5*04');


insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 12, '是否倒挤行', 'ISDJ', 3, 1, 0, null, '0', '1', '2D0C29463049A9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 22, '附件类别', 'FJLB', 3, 32, null, null, '0', '1', '2D0C2946304AA9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 16, '是否其中项', 'ISQZX', 3, 1, 0, null, '0', '1', '2D0C2946304BA9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 19, '附件大类', 'FIRATTNAME', 3, 200, null, null, '0', '1', '2D0C2946304CA9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 4, '单元格是否可录', 'CELLSECU', 3, 1000, 0, null, '0', '1', '2D0C2946304DA9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 7, '级次码', 'LEVELNO', 1, 1, 0, null, '0', '1', '2D0C2946304EA9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 13, '是否可修改', 'ISUPDATE', 3, 1, 0, null, '0', '1', '2D0C2946304FA9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 15, '是否模板', 'ISTEMPLATE', 3, 1, 0, null, '0', '1', '2D0C29463050A9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 17, '同步状态', 'SYNSTATUS', 3, 1, 0, null, '0', '1', '2D0C29463051A9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 27, 'STATUS', 'STATUS', 3, 1, null, null, '0', '1', '2D0C29463052A9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 25, '是否公开', 'ISPUBLIC', 6, 1, null, null, '0', '1', '2D0C29463053A9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 18, '原始编码', 'ORIGCODE', 3, 32, 0, null, '0', '1', '2D0C29463054A9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 14, '是否可插入', 'ISINSERT', 3, 1, 0, null, '0', '1', '2D0C29463055A9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 9, '是否末级', 'ISLEAF', 3, 1, 0, null, '0', '1', '2D0C29463056A9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 1, '财政级次', 'BGTLVL', 3, 1, null, null, '0', '1', '2D0C29463057A9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 21, '财年', 'FINYEAR', 3, 4, null, null, '0', '1', '2D0C29463058A9B5E050A8C021052154', '1', 'to_char(sysdate,''YYYY'')', '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 5, '浮动表显示列', 'FDCODE', 3, 100, 0, null, '0', '1', '2D0C29463059A9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 20, '专项附件', 'ATTACHID', 3, 32, 0, null, '0', '1', '2D0C2946305AA9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 19, '专项唯一标识', 'SPFID', 3, 32, 0, null, '0', '1', '2D0C2946305BA9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 6, '附件名称', 'ATTNAME', 3, 200, 0, null, '0', '1', '2D0C2946305CA9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 20, '刷新标志', 'NEEDUPDATE', 3, 4000, null, null, '0', '1', '2D0C2946305DA9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 1, '行标志', 'DATAKEY', 3, 32, 0, null, '1', '1', '2D0C2946305EA9B5E050A8C021052154', '1', 'sys_guid()', '1', '080001', '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 21, '附件细类', 'DETCLASSID', 3, 32, 0, null, '0', '1', '2D0C29463060A9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 2, '排序序号', 'ORDERID', 1, 9, 0, null, '0', '1', '2D0C29463061A9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 11, '上级编码', 'SUPERID', 3, 32, 0, null, '0', '1', '2D0C29463062A9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*04', 8, '模板标识', 'TEMPLATEID', 3, 32, 0, null, '0', '1', '2D0C29463063A9B5E050A8C021052154', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*15', 25, '专项唯一标识', 'SPFID', 3, 32, null, null, '0', '1', '2D0D8E001B99B5A7E050A8C0210525BD', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*15', 22, '刷新标志', 'NEEDUPDATE', 3, 4000, null, null, '0', '1', '2D0D8E001B9BB5A7E050A8C0210525BD', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*15', 27, '附件名称', 'ATTNAME', 3, 200, null, null, '0', '1', '2D0D8E001B9DB5A7E050A8C0210525BD', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*15', 21, '是否必需附件', 'ISMUST', 6, 1, null, null, '0', '1', '2D0D8E001BA1B5A7E050A8C0210525BD', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*15', 26, '附件模板', 'TEMPLATENAME', 3, 32, null, null, '0', '1', '2D0D8E001BA3B5A7E050A8C0210525BD', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*15', 23, '财年', 'FINYEAR', 3, 4, null, null, '0', '1', '2D0D8E001BA4B5A7E050A8C0210525BD', '1', 'to_char(sysdate,''YYYY'')', '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*15', 28, 'STATUS', 'STATUS', 3, 1, null, null, '0', '1', '2D0D8E001BA5B5A7E050A8C0210525BD', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*15', 24, '项目附件', 'ATTACHID', 3, 32, null, null, '0', '1', '2D0D8E001BA7B5A7E050A8C0210525BD', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('4*15', 1, 'DATAKEY', 'DATAKEY', 3, 32, 0, null, '1', '1', 'F82A8A4226547D5AE040A8C020031354', '1', 'sys_guid()', '0', '080001', '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('5*04', 11, '附件说明', 'MEMO', 3, 1000, 0, null, '0', '1', '11EC5A36EDD5494B8A03241B26BDE340', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('5*04', 10, '级次码', 'LEVELNO', 1, 9, 0, null, '0', '1', '1ED30C026B174A5E86E2F4760AE2920C', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('5*04', 2, '浮动编码', 'FDCODE', 3, 200, 0, null, '0', '1', '282842135C3C410CB73A5338A618CC6B', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('5*04', 5, '是否末级', 'ISLEAF', 3, 1, 0, null, '0', '1', '511C530C9AD54ED3A1B7D0E464E5237E', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('5*04', 7, '是否其中项', 'ISQZX', 3, 1, 0, null, '0', '1', '5EA2274793514CA58B9807C09A0E9604', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('5*04', 8, '是否模板', 'ISTEMPLATE', 3, 1, 0, null, '0', '1', '65429445344A4F15A7250BC238104177', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('5*04', 4, '是否可插入下级', 'ISINSERT', 3, 1, 0, null, '0', '1', '6EF2740AAF194BD89509AEDECF565C12', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('5*04', 16, '附件模板说明', 'TEMPLETREMARK', 3, 1000, 0, null, '0', '1', '7573B3007ADC4785A3E6FC6C12D4BA11', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('5*04', 6, '是否必须', 'ISMUST', 3, 1, 0, null, '0', '1', '79E00A4C16274905940BFAEEF3927CC6', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('5*04', 9, '是否可更新', 'ISUPDATE', 3, 1, 0, null, '0', '1', '89EAA7AAC49348C39B1D2BABD4425489', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('5*04', 15, '附件模板', 'TEMPLETID', 3, 32, 0, null, '0', '1', 'AD4E6C1A458247D9BE5647CE659E5184', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('5*04', 14, '模板标识', 'TEMPLATEID', 3, 32, 0, null, '0', '1', 'AE2CBC6ADD454D9F992B8C5C38BE3813', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('5*04', 18, '附件名称', 'ATTACHNAME', 3, 200, 0, null, '0', '1', 'B8E46CAAF2B349B186F5C48898234A58', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('5*04', 19, '项目ID', 'PROJECTID', 3, 32, 0, null, '0', '1', 'D807820A59254186B749F5C0AE4B4321', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('5*04', 12, '排序序号', 'ORDERID', 1, 9, 0, null, '0', '1', 'D88591C1737C47C5B0B7CE5895F38560', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('5*04', 17, '项目附件', 'ATTACHID', 3, 32, 0, null, '0', '1', 'EB1FA633201F4F519CC4258DE8F4C795', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('5*04', 13, '上级编码', 'SUPERID', 3, 32, 0, null, '0', '1', 'F35B1B33E8DD45D4B531B312FA839DCE', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('5*04', 3, '是否倒挤行', 'ISDJ', 3, 1, 0, null, '0', '1', 'F508C76A64DF4F388E9FE78328679BDC', '1', null, '0', null, '0', '0');

insert into dict_t_defaultcol (DEALID, ORDERID, NAME, DBCOLUMNNAME, DATATYPE, DATALENGTH, SCALE, CSID, ISPRIMARY, ISRESERVE, GUID, STATUS, DEFAULTVALUE, ISLOGICKEY, DEID, ISVISIBLE, ISUPDATE)
values ('5*04', 1, 'DATAKEY', 'DATAKEY', 3, 32, 0, null, '1', '1', 'F82A8A4226547D5AE040A8C020031D8A', '1', 'sys_guid()', '0', '080001', '0', '0');
END;
--附件表缺省列

--专项资金收账
begin
delete from fasp_t_pubmenu where guid ='24A46FC5BB30EF34E050A8C021057D70';
insert into fasp_t_pubmenu (GUID, LEVELNO, ISLEAF, STATUS, CODE, NAME, PARENTID, URL, MENUORDER, REMARK, DBVERSION, APPSYSID, PROVINCE, YEAR, APPID, PARAM1, PARAM2, PARAM3, PARAM4, PARAM5, SSOFLAG, ADMINTYPE)
values ('24A46FC5BB30EF34E050A8C021057D70', 2, 1, '1', '004001011', '专项资金记帐', '120001', '/spf/spf/check/initSpfTally.do?istemp=0'||'&'||'dealtype=41', 0, 'remark', null, 2, null, null, 'spf', null, null, null, null, null, null, 1);
end;
--wufucanzhuanxiangShouZhang

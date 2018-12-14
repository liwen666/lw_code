DECLARE
begin

delete from p#dict_t_factor where columnid='3A6A5FE533CE60DDE053CB01A8C06FD9';

for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
begin
insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '', '', '', '', '', '项目id', '3A6A5FE533CE60DDE053CB01A8C06FD9', '14DADAE88B8B57D0E050A8C0210557DD', 32, 6, 'PROJECTID', '', '10804', '0000000000000000000000000000000', '14C0C3214F28189EE050A8C0210507CA', '14C0C3214F27189EE050A8C0210507CA', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '项目名称', '0', 4, '', '', 0, '4', 0, '1', '0', '3A6A5FE533CC60DDE053CB01A8C06FD9', '', '0', '');

end;
end loop;

end;--项目投资计划添加项目名称sql

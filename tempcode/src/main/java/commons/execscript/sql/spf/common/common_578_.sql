DECLARE
begin
EXECUTE IMMEDIATE Q'/
create or replace view spf_t_fbasecflow as
select
  cact.batchid
  ,(select title from BD_T_BATCH where batchid=cact.batchid) title
  ,cact.busitypeid
  ,cact.createuser
  ,cact.datakey
  ,cact.objectid
  ,cact.districtid
  ,cact.remark
  ,cact.sourceagencyid
  ,cact.sourcebatchid
  ,cact.step
  ,cact.targetagencyid
  ,cact.wfdirection
  ,cact.wfstatus
  ,fbase.spfid
  ,fbase.SPFNAME
  ,fbase.agencyid
  ,fs.SETUPSTATUS
  ,(case when (fs.SETUPSTATUS=0) then '未设立' else '已设立' end) SETUPSTATUSNAME
  ,1 NEEDUPDATE
    ,1 ORDERID
    ,(select busitypename from BD_T_BUSITYPE where busitypeid=cact.busitypeid) busitypename
    ,fbase.beginyear FINYEAR
    ,fbase.PROJTYPEID PROJTYPEID
from spf_t_cflow_act cact
  ,spf_t_fbaseinfo fbase
  ,spf_t_fbasestatus fs
where fbase.spfid = cact.objectid and fs.SPFID=fbase.SPFID
/';

delete p#dict_t_factor  where columnid in('524CCFF417C559F5E0533906A8C019CC','4BD986CF5E4C6E24E0533A06A8C04FF3');

for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
begin
    insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '', '', '', '', '', '', '4BD986CF5E4C6E24E0533A06A8C04FF3', '', 32, 3, 'SPFID', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', 1, '专项id', '0', 13, '', '', null, '0', null, '1', '0', '4BD986CF5E3F6E24E0533A06A8C04FF3', '', '0', '');

    insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
    values (v_row.year, v_row.districtid, '', '', '', '', '', '', '524CCFF417C559F5E0533906A8C019CC', '', 200, 3, 'SPFNAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', 1, '专项名称', '1', 24, '', '', 0, '0', null, '1', '0', '4BD986CF5E3F6E24E0533A06A8C04FF3', '', '0', '');

end;
end loop;
end;
--修改SPF_T_FBASECFLOW视图

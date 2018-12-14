DECLARE
begin
EXECUTE IMMEDIATE Q'/
create or replace view spf_t_pbasehistflow as
select
   hist.batchid
     ,(select title from BD_T_BATCH where batchid=hist.batchid) title
  ,hist.busitypeid
  ,hist.createuser
  ,hist.datakey
  ,hist.objectid
  ,hist.districtid
  ,hist.remark
  ,hist.sourceagencyid
  ,hist.sourcebatchid
  ,hist.step
  ,hist.targetagencyid
  ,hist.wfdirection
  ,hist.wfstatus
  ,pbase.spfid
  ,pbase.projectid
  ,pbase.agencyid
  ,pstatus.CHECKSTATUS
  ,(case when(pstatus.CHECKSTATUS=1) then '已审核' else '未审核' end ) CHECKSTATUSNAME
  ,pstatus.ISBGT
  ,(case when(pstatus.ISBGT=1) then '已纳入预算' else '未纳入预算' end ) ISBGTNAME
  ,pstatus.ISINDEX
  ,(case when(pstatus.ISINDEX=1) then '已纳入指标' else '未纳入指标' end ) ISINDEXNAME
  ,1 NEEDUPDATE
    ,1 ORDERID
    ,(select busitypename from BD_T_BUSITYPE where busitypeid=hist.busitypeid) busitypename
    ,pbase.startyear FINYEAR
        ,pbase.PROJTYPEID
from spf_t_cflow_hist hist
  ,spf_t_pbaseinfo pbase
  ,spf_t_pbasestatus pstatus
where pbase.projectid = hist.objectid  and hist.OBJECTID=pstatus.PROJECTID
/';
delete P#DICT_T_FACTOR where COLUMNID in ('4F37C7613DDD6608E0533A06A8C0158D','4F37C7613DDF6608E0533A06A8C0158D','4F37C7613DE16608E0533A06A8C0158D');
for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
begin

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '', '', '', '', '', '', '4F37C7613DDD6608E0533A06A8C0158D', '', 6, 3, 'CHECKSTATUSNAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '是否审核', '1', 23, '', '', 0, '0', null, '1', '0', '4C8D65E7BACC04B4E0533A06A8C07EB5', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '', '', '', '', '', '', '4F37C7613DDF6608E0533A06A8C0158D', '', 10, 3, 'ISBGTNAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '是否纳入预算', '1', 24, '', '', 0, '0', null, '1', '0', '4C8D65E7BACC04B4E0533A06A8C07EB5', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '', '', '', '', '', '', '4F37C7613DE16608E0533A06A8C0158D', '', 10, 3, 'ISINDEXNAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '是否纳入指标', '1', 25, '', '', 0, '0', null, '1', '0', '4C8D65E7BACC04B4E0533A06A8C07EB5', '', '0', '');

end;
end loop;
end;--修改专项项目历史视图

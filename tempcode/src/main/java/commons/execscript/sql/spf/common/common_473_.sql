DECLARE
begin
EXECUTE IMMEDIATE Q'/
create or replace view spf_t_spfpejhist as
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
  ,spfej.CHECKSTATUS
  ,(case when(spfej.CHECKSTATUS=1) then '已审核' else '未审核' end ) CHECKSTATUSNAME
  ,spfej.ISBGT
  ,(case when(spfej.ISBGT=1) then '已纳入预算' else '未纳入预算' end ) ISBGTNAME
  ,spfej.ISINDEX
  ,(case when(spfej.ISINDEX=1) then '已纳入指标' else '未纳入指标' end ) ISINDEXNAME
  ,spfej.spfid
  ,spfej.PROJTYPEID
  ,spfej.projectid
  ,spfej.agencyid
  ,1 NEEDUPDATE
    ,1 ORDERID
    ,(select busitypename from BD_T_BUSITYPE where busitypeid=hist.busitypeid) busitypename
    ,TO_CHAR(SYSDATE,'YYYY') FINYEAR
from spf_t_cflow_hist hist
  ,SPF_V_SPFPMAINEJ spfej
where spfej.projectid = hist.objectid
/';
delete P#DICT_T_FACTOR where COLUMNID in ('4F377A29FEFD63A5E0533A06A8C0F7F2','4F377A29FEFF63A5E0533A06A8C0F7F2','4F377A29FF0163A5E0533A06A8C0F7F2');
for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
begin

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '', '', '', '', '', '', '4F377A29FEFD63A5E0533A06A8C0F7F2', '', 6, 3, 'CHECKSTATUSNAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '是否审核', '1', 23, '', '', 0, '0', null, '1', '0', '4CCF7F63AC755A78E0533A06A8C05145', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '', '', '', '', '', '', '4F377A29FEFF63A5E0533A06A8C0F7F2', '', 10, 3, 'ISBGTNAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '是否纳入预算', '1', 24, '', '', 0, '0', null, '1', '0', '4CCF7F63AC755A78E0533A06A8C05145', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid, '', '', '', '', '', '', '4F377A29FF0163A5E0533A06A8C0F7F2', '', 10, 3, 'ISINDEXNAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '是否纳入指标', '1', 25, '', '', 0, '0', null, '1', '0', '4CCF7F63AC755A78E0533A06A8C05145', '', '0', '');

end;
end loop;
end;--修改二级项目历史视图

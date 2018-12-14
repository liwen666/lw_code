DECLARE
begin
EXECUTE IMMEDIATE Q'/
create or replace view spf_t_spfsendpejact as
select
   act.batchid
  ,(select title from BD_T_BATCH where batchid=act.batchid) title
  ,act.busitypeid
  ,act.createuser
  ,act.datakey
  ,act.objectid
  ,act.districtid
  ,act.remark
  ,act.sourceagencyid
  ,act.sourcebatchid
  ,act.step
  ,act.targetagencyid
  ,act.wfdirection
  ,act.wfstatus
  ,spfej.spfid
  ,spfej.projectid
  ,spfej.agencyid
  ,spfej.PROJTYPEID
  ,spfej.CHECKSTATUS
  ,(case when(spfej.CHECKSTATUS=1) then '已审核' else '未审核' end ) CHECKSTATUSNAME
  ,spfej.ISBGT
  ,(case when(spfej.ISBGT=1) then '已纳入预算' else '未纳入预算' end ) ISBGTNAME
  ,spfej.ISINDEX
  ,(case when(spfej.ISINDEX=1) then '已纳入指标' else '未纳入指标' end ) ISINDEXNAME
  ,1 NEEDUPDATE
    ,1 ORDERID
    ,(select busitypename from BD_T_BUSITYPE where busitypeid=act.busitypeid) busitypename
    ,TO_CHAR(SYSDATE,'YYYY') FINYEAR
from spf_t_cflow_act act
  ,SPF_V_SPFPMAINEJ spfej
where spfej.projectid = act.objectid
/';
delete P#DICT_T_FACTOR where COLUMNID in ('4F37F1BCD3B668A9E0533A06A8C0749E','4F37F1BCD3B868A9E0533A06A8C0749E','4F37F1BCD3BA68A9E0533A06A8C0749E');
for v_row in (select * from pub_t_partition_divid t where t.year <> '*') loop
begin

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid,  '', '', '', '', '', '', '4F37F1BCD3B668A9E0533A06A8C0749E', '', 6, 3, 'CHECKSTATUSNAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '是否审核', '1', 23, '', '', 0, '0', null, '1', '0', '4E95F2ED6A4F5498E0533A06A8C050A5', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid,  '', '', '', '', '', '', '4F37F1BCD3B868A9E0533A06A8C0749E', '', 10, 3, 'ISBGTNAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '是否纳入预算', '1', 24, '', '', 0, '0', null, '1', '0', '4E95F2ED6A4F5498E0533A06A8C050A5', '', '0', '');

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year, v_row.districtid,  '', '', '', '', '', '', '4F37F1BCD3BA68A9E0533A06A8C0749E', '', 10, 3, 'ISINDEXNAME', '', '', '0000000000000000000000000000000', '', '', '', '', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', 1, '是否纳入指标', '1', 25, '', '', 0, '0', null, '1', '0', '4E95F2ED6A4F5498E0533A06A8C050A5', '', '0', '');

end;
end loop;
end;--修改二级项目活动轨迹视图

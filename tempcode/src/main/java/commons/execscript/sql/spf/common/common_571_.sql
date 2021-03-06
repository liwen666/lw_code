DECLARE
begin
EXECUTE IMMEDIATE Q'/create or replace view SPF_T_SPFPEJHCCOMT as
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
  ,spfej.spfid
  ,spfej.PROJTYPEID
  ,spfej.projectid
  ,spfej.SPFNAME
  ,spfej.PROJNAME
  ,spfej.CHECKSTATUS
  ,(case when(spfej.CHECKSTATUS=1) then '已审核' else '未审核' end ) CHECKSTATUSNAME
  ,spfej.ISBGT
  ,(case when(spfej.ISBGT=1) then '已纳入预算' else '未纳入预算' end ) ISBGTNAME
  ,spfej.ISINDEX
  ,(case when(spfej.ISINDEX=1) then '已纳入指标' else '未纳入指标' end ) ISINDEXNAME
  ,spfej.agencyid
  ,1 NEEDUPDATE
    ,1 ORDERID
    ,(select busitypename from BD_T_BUSITYPE where busitypeid=hist.busitypeid) busitypename
    ,TO_CHAR(SYSDATE,'YYYY') FINYEAR
from spf_t_cflow_hist hist
  ,SPF_V_SPFPMAINEJ spfej
where spfej.projectid = hist.objectid
and hist.WFDIRECTION in('0'，'1'，'2'，'8')
union all
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
  ,spfej.PROJTYPEID
  ,spfej.projectid
  ,spfej.SPFNAME
  ,spfej.PROJNAME
  ,spfej.CHECKSTATUS
  ,(case when(spfej.CHECKSTATUS=1) then '已审核' else '未审核' end) CHECKSTATUSNAME
  ,spfej.ISBGT
  ,(case when(spfej.ISBGT=1) then '已纳入预算' else '未纳入预算' end) ISBGTNAME
  ,spfej.ISINDEX
  ,(case when(spfej.ISINDEX=1) then '已纳入指标' else '未纳入指标' end) ISINDEXNAME
  ,spfej.agencyid
  ,1 NEEDUPDATE
    ,1 ORDERID
    ,(select busitypename from BD_T_BUSITYPE where busitypeid=act.busitypeid) busitypename
    ,TO_CHAR(SYSDATE,'YYYY') FINYEAR
from spf_t_cflow_act act
  ,SPF_V_SPFPMAINEJ spfej
where spfej.projectid = act.objectid
and act.WFDIRECTION in('0'，'1'，'2')/';

DELETE FROM P#dict_t_Factor WHERE tableid='5067A0248DC44059E0533A06A8C0D07E' AND columnid in ('5067A0248DD44059E0533A06A8C0D07E','525DE592CADB262DE0533906A8C0CCC5','5067A0248DD54059E0533A06A8C0D07E','525DE592CADD262DE0533906A8C0CCC5');

FOR V_ROW IN(SELECT * FROM PUB_T_PARTITION_DIVID T WHERE T.YEAR <> '*') LOOP   
BEGIN
  
insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '5067A0248DD44059E0533A06A8C0D07E', null, '32', '3', 'SPFID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '专项编号', '1', '16', null, null, null, '0', null, '1', '0', '5067A0248DC44059E0533A06A8C0D07E', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '5067A0248DD54059E0533A06A8C0D07E', null, '32', '3', 'PROJECTID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '项目编号', '1', '17', null, null, null, '0', null, '1', '0', '5067A0248DC44059E0533A06A8C0D07E', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '525DE592CADB262DE0533906A8C0CCC5', null, '200', '3', 'SPFNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '1', '专项名称', '1', '29', null, null, '0', '0', null, '1', '0', '5067A0248DC44059E0533A06A8C0D07E', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '525DE592CADD262DE0533906A8C0CCC5', null, '200', '3', 'PROJNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '1', '项目名称', '1', '30', null, null, '0', '0', null, '1', '0', '5067A0248DC44059E0533A06A8C0D07E', null, '0', null);

 end;
 end loop;
end; --SPF_T_SPFPEJHCCOMT
--SPF_T_SPFPEJHCCOMT

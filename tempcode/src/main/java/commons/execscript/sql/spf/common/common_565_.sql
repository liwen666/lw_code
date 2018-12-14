DECLARE
begin
EXECUTE IMMEDIATE Q'/create or replace view SPF_T_FBASEHCCOMT as
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
  ,(case when (fs.SETUPSTATUS=0) then '未设立' when (fs.SETUPSTATUS=1) then '已确认，未发布' else '已设立' end) SETUPSTATUSNAME
  ,1 NEEDUPDATE
    ,1 ORDERID
    ,(select busitypename from BD_T_BUSITYPE where busitypeid=cact.busitypeid) busitypename
    ,fbase.beginyear FINYEAR
    ,fbase.PROJTYPEID PROJTYPEID
from spf_t_cflow_act cact
  ,spf_t_fbaseinfo fbase
  ,spf_t_fbasestatus fs
where fbase.spfid = cact.objectid and fs.SPFID=fbase.SPFID
and cact.WFDIRECTION in('0'，'1'，'2')
union all
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
  ,fbase.spfid
  ,fbase.SPFNAME
  ,fbase.agencyid
  ,fs.SETUPSTATUS
  ,(case when (fs.SETUPSTATUS=0) then '未设立' when (fs.SETUPSTATUS=1) then '已确认，未发布' else '已设立' end) SETUPSTATUSNAME
  ,1 NEEDUPDATE
    ,1 ORDERID
    ,(select busitypename from BD_T_BUSITYPE where busitypeid=hist.busitypeid) busitypename
    ,fbase.beginyear FINYEAR
    ,fbase.PROJTYPEID
from spf_t_cflow_hist hist
  ,spf_t_fbaseinfo fbase
  ,spf_t_fbasestatus fs
where fbase.spfid = hist.objectid and fs.SPFID=fbase.SPFID
and hist.WFDIRECTION in('0'，'1'，'2'，'8')/';

DELETE FROM P#dict_t_Factor WHERE tableid='50678F9804043FEBE0533A06A8C00631' AND columnid in ('50678F9804133FEBE0533A06A8C00631','525E57DC4EC1284DE0533906A8C0FD2C');

FOR V_ROW IN(SELECT * FROM PUB_T_PARTITION_DIVID T WHERE T.YEAR <> '*') LOOP   
BEGIN

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '50678F9804133FEBE0533A06A8C00631', null, '32', '3', 'SPFID', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '专项编号', '1', '15', null, null, null, '0', null, '1', '0', '50678F9804043FEBE0533A06A8C00631', null, '0', null);

insert into P#DICT_T_FACTOR (YEAR, PROVINCE, ALIAS, BANDCOLUMNID, BANDREFDWCOL, BGTLVL, COLFORMAT, COLTIPS, COLUMNID, CSID, DATALENGTH, DATATYPE, DBCOLUMNNAME, DEFAULTVALUE, DEID, EXTPROP, FRMCOLID, FRMTABID, HREFLOC, HREFPARMID, ISBANDCOL, ISHREF, OPENWINDOWTYPE, ISKEY, ISLEAF, ISREGEX, ISRESERVE, ISSUM, ISUPDATE, ISVIRTUAL, ISVISIBLE, LEVELNO, NAME, NULLABLE, ORDERID, REGEXPR, REGEXPRINFO, SCALE, SHOWFORMAT, SHOWWIDTH, STATUS, SUPERID, TABLEID, VIRCONTEXT, PARENTNODECANCHECK, XLSXCOLUMNID)
values (v_row.year,V_ROW.Districtid, null, null, null, null, null, null, '525E57DC4EC1284DE0533906A8C0FD2C', null, '200', '3', 'SPFNAME', null, null, '0000000000000000000000000000000', null, null, null, null, '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '1', '专项名称', '1', '24', null, null, '0', '0', null, '1', '0', '50678F9804043FEBE0533A06A8C00631', null, '0', null);

 end;
 end loop;
end;---SPF_T_FBASEHCCOMT
--SPF_T_FBASEHCCOMT
